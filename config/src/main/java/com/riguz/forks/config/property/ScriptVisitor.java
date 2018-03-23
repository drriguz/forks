package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseVisitor;
import com.riguz.forks.config.InvalidValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScriptVisitor extends CfParserBaseVisitor<Map<String, Object>> {
    private static final Logger logger = LoggerFactory.getLogger(ScriptVisitor.class);

    private final boolean allowReference;
    private final Map<String, Object> context;

    private ScriptVisitor(boolean allowReference, Map<String, Object> context) {
        this.allowReference = allowReference;
        this.context = context;
    }

    Object getReference(String name) {
        if (!allowReference)
            throw new InvalidValueException("Reference not allowed:" + name);
        Object value = this.context.get(name);
        if (value == null)
            throw new InvalidValueException("Reference not definded:" + name);
        return value;
    }

    public static ScriptVisitor ofShared() {
        return new ScriptVisitor(false, null);
    }

    public static ScriptVisitor of(Map<String, Object> context) {
        return new ScriptVisitor(true, Collections.unmodifiableMap(context));
    }

    @Override
    public Map<String, Object> visitShared(CfParser.SharedContext ctx) {
        logger.debug("Visit script:{}", ctx.getText());
        Map<String, Object> sharedProperties = new HashMap<>();
        ctx.property().forEach(propertyContext -> {
            PropertyVisitor propertyVisitor = new PropertyVisitor();
            Property property = propertyContext.accept(propertyVisitor);
            logger.debug("Setting shared:{} = {}", property.name, property.value);
            sharedProperties.put(property.name, property.value);
        });
        return sharedProperties;
    }

    class PropertyVisitor extends CfParserBaseVisitor<Property> {
        @Override
        public Property visitBasicProperty(CfParser.BasicPropertyContext ctx) {
            logger.debug("Visit basic property:{}", ctx.getText());
            Property property = new Property();
            property.name = ctx.NAME().getText();
            ExpressionVisitor expressionVisitor = new ExpressionVisitor();
            property.value = ctx.expression().accept(expressionVisitor);
            return property;
        }

        @Override
        public Property visitArrayProperty(CfParser.ArrayPropertyContext ctx) {
            return super.visitArrayProperty(ctx);
        }
    }

    class ExpressionVisitor extends CfParserBaseVisitor<Object> {
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
    }

    class StringVisitor extends CfParserBaseVisitor<String> {

        @Override
        public String visitStringExpression(CfParser.StringExpressionContext ctx) {
            String value = "";
            if (ctx.STRING_LITERAL() != null) {
                String str = ctx.STRING_LITERAL().getText();
                value += str.substring(1, str.length() - 1);
            } else if (ctx.REFERENCE() != null) {
                String reference = ctx.REFERENCE().getText();
                String referenceName = reference.substring(2, reference.length() - 1);
                value += getReference(referenceName);
            }
            if (ctx.stringExpression() != null) {
                StringVisitor nestedVisitor = new StringVisitor();
                value += ctx.stringExpression().accept(nestedVisitor);
            }
            return value;
        }
    }
}
