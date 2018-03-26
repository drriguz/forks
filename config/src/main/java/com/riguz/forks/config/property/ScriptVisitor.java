package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseVisitor;
import com.riguz.forks.config.InvalidValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScriptVisitor extends CfParserBaseVisitor<Map<String, ScriptVisitor.Scope>> {
    private static final Logger logger = LoggerFactory.getLogger(ScriptVisitor.class);

    @Override
    public Map<String, Scope> visitScript(CfParser.ScriptContext ctx) {
        Map<String, Object> context;
        if (ctx.shared() == null)
            context = Collections.emptyMap();
        else
            context = ctx.shared().accept(new CfParserBaseVisitor<Map<String, Object>>() {
                @Override
                public Map<String, Object> visitShared(CfParser.SharedContext ctx) {
                    logger.debug("Visit script:{}", ctx.getText());
                    Map<String, Object> sharedProperties = new HashMap<>();
                    ctx.property().forEach(propertyContext -> {
                        PropertyVisitor propertyVisitor = PropertyVisitor.withoutReference();
                        Property property = propertyContext.accept(propertyVisitor);
                        logger.debug("Setting shared:{} = {}({})", property.name, property.value, property.value.getClass());
                        sharedProperties.put(property.name, property.value);
                    });
                    return sharedProperties;
                }
            });
        Map<String, Scope> scopes = new HashMap<>();
        ScopeVisitor scopeVisitor = new ScopeVisitor(context);
        ctx.scope().forEach(scopeContext -> {
            logger.debug("Visit scope:{}", scopeContext.getText());
            Scope scope = scopeContext.accept(scopeVisitor);
            scopes.put(scope.name, scope);
        });
        return scopes;
    }

    static class Scope {
        String name;
        Map<String, Object> properties;

        public Scope(String name, Map<String, Object> properties) {
            if (properties == null)
                throw new RuntimeException("Invalid scope detected, name or scopes is empty");
            this.name = name;
            this.properties = Collections.unmodifiableMap(properties);
        }
    }

    class ScopeVisitor extends CfParserBaseVisitor<Scope> {
        private final Map<String, Object> context;

        public ScopeVisitor(Map<String, Object> context) {
            if (context == null)
                throw new RuntimeException("Context should not be null");
            this.context = Collections.unmodifiableMap(context);
        }

        @Override
        public Scope visitScope(CfParser.ScopeContext ctx) {
            Map<String, Object> properties = new HashMap<>();
            ctx.property().forEach(propertyContext -> {
                PropertyVisitor propertyVisitor = PropertyVisitor.withReference(this.context);
                Property property = propertyContext.accept(propertyVisitor);
                logger.debug("Setting shared:{} = {}", property.name, property.value);
                properties.put(property.name, property.value);
            });
            return new Scope(ctx.NAME().getText(), properties);
        }
    }

    private static class PropertyVisitor extends CfParserBaseVisitor<Property> {
        private final boolean allowReference;
        private final Map<String, Object> context;

        private PropertyVisitor(boolean allowReference, Map<String, Object> context) {
            this.allowReference = allowReference;
            this.context = context;
        }

        static PropertyVisitor withoutReference() {
            return new PropertyVisitor(false, null);
        }

        static PropertyVisitor withReference(Map<String, Object> context) {
            return new PropertyVisitor(true, context);
        }

        private Object getReference(String expression) {
            if (!allowReference)
                throw new InvalidValueException("Reference not allowed:" + expression);
            // ${name} -> name
            String name = expression.substring(2, expression.length() - 1);
            Object value = this.context.get(name);
            if (value == null)
                throw new InvalidValueException("Reference not definded:" + name);
            return value;
        }

        @Override
        public Property visitBasicProperty(CfParser.BasicPropertyContext ctx) {
            logger.debug("Visit basic property:{}", ctx.getText());
            Property property = new Property();
            property.name = ctx.NAME().getText();
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();
            property.value = ctx.expression().accept(expressionVisitor);
            if (property.value == null)
                throw new RuntimeException("Value not evaluated, that must be a bug?");
            Type type = Type.valueOf(ctx.type().getText().toUpperCase());
            if (!type.getType().isAssignableFrom(property.value.getClass()))
                throw new InvalidValueException("Invalid value found of "
                        + property
                        + ":expected="
                        + type
                        + " but get:"
                        + property.value.getClass());
            return property;
        }

        @Override
        public Property visitArrayProperty(CfParser.ArrayPropertyContext ctx) {
            return super.visitArrayProperty(ctx);
        }

        private class ExpressionVisitor extends CfParserBaseVisitor<Object> {
            @Override
            public Object visitBool(CfParser.BoolContext ctx) {
                if ("true".equals(ctx.BOOL_LITERAL().getText()))
                    return true;
                else if ("false".equals(ctx.BOOL_LITERAL().getText()))
                    return false;
                else
                    return null;
            }

            @Override
            public Object visitInt(CfParser.IntContext ctx) {
                return Integer.parseInt(ctx.getText());
            }

            @Override
            public Object visitHex(CfParser.HexContext ctx) {
                return Integer.parseInt(ctx.getText());
            }

            @Override
            public Object visitDecimal(CfParser.DecimalContext ctx) {
                return Double.parseDouble(ctx.getText());
            }

            @Override
            public Object visitString(CfParser.StringContext ctx) {
                return ctx.stringExpression().accept(new StringVisitor());
            }

            @Override
            public Object visitReference(CfParser.ReferenceContext ctx) {
                return getReference(ctx.REFERENCE().getText());
            }

            private class StringVisitor extends CfParserBaseVisitor<String> {
                @Override
                public String visitStringExpression(CfParser.StringExpressionContext ctx) {
                    String value = "";
                    if (ctx.STRING_LITERAL() != null) {
                        String str = ctx.STRING_LITERAL().getText();
                        value += str.substring(1, str.length() - 1);
                    } else if (ctx.REFERENCE() != null) {
                        value += getReference(ctx.REFERENCE().getText());
                    }
                    if (ctx.stringExpression() != null) {
                        StringVisitor nestedVisitor = new StringVisitor();
                        value += ctx.stringExpression().accept(nestedVisitor);
                    }
                    return value;
                }
            }
        }
    }
}
