package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseVisitor;
import com.riguz.forks.config.DuplicateException;
import com.riguz.forks.config.InvalidValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.Arrays;
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
                        if (property == null)
                            throw new NullPointerException("Property not parsed:" + ctx.getText());
                        logger.debug("Setting shared:{} = {}({})", property.name, property.value, property.value.getClass());
                        if (sharedProperties.containsKey(property.name))
                            throw new DuplicateException("Duplicate property of " + property.name
                                    + " in shared");
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
                if (property == null)
                    throw new NullPointerException("Property not parsed:" + ctx.getText());
                if (properties.containsKey(property.name))
                    throw new DuplicateException("Duplicate property of " + property.name
                            + " in scope " + ctx.NAME().getText());
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
            String name = ctx.NAME().getText();
            Object value = ctx.expression().accept(new ExpressionVisitor());
            if (value == null)
                throw new NullPointerException("Value not evaluated:" + ctx.getText());
            Type type = Type.valueOf(ctx.type().getText().toUpperCase());
            Class<?> valueClass = value.getClass().isArray() ?
                    value.getClass().getComponentType() : value.getClass();
            if (!type.getType().isAssignableFrom(valueClass))
                throw new InvalidValueException("Invalid value found of " + name + ":expected is " + type + " but get:" + value.getClass());
            return new Property(name, value);
        }

        @Override
        public Property visitArrayProperty(CfParser.ArrayPropertyContext ctx) {
            String name = ctx.NAME().getText();
            Type type = Type.valueOf(ctx.type().getText().toUpperCase());
            Object value = Array.newInstance(type.getType(), ctx.expression().size());
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();
            for (int i = 0; i < ctx.expression().size(); i++) {
                Object item = ctx.expression().get(i).accept(expressionVisitor);
                Array.set(value, i, item);
            }
            return new Property(name, value);
        }

        private class ExpressionVisitor extends CfParserBaseVisitor<Object> {
            @Override
            public Object visitBool(CfParser.BoolContext ctx) {
                if ("true".equals(ctx.BOOL_LITERAL().getText()))
                    return true;
                else if ("false".equals(ctx.BOOL_LITERAL().getText()))
                    return false;
                else
                    throw new InvalidValueException("Unrecognized bool literal:" + ctx.getText());
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
            public Object visitFloat(CfParser.FloatContext ctx) {
                return Float.parseFloat(ctx.getText());
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
