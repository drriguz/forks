package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.config.DuplicateException;
import com.riguz.forks.config.InvalidValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

class CfListener extends CfParserBaseListener {
    private static final Logger logger = LoggerFactory.getLogger(CfListener.class);

    final Map<String, Object> properties = new HashMap<>();
    Map<String, Object> sharedProperties;

    private void putShared(String name, Object value) {
        put(this.sharedProperties, name, value);
    }

    private void putProperty(String name, Object value) {
        put(this.properties, name, value);
    }

    private static void put(final Map<String, Object> target, String name, Object value) {
        if (target.containsKey(name))
            throw new DuplicateException("Duplicate defination of :" + name);
        logger.info("Setting ({}) to [{}]", name, value);
        target.put(name, value);
    }

    @Override
    public void enterProperties(CfParser.PropertiesContext ctx) {
        logger.debug("Parse properties rule:{}", ctx.getText());
        ctx.sharedProperty().forEach(sharedPropertyContext -> {

        });
        ctx.property().forEach(sharedPropertyContext -> {

        });
    }

    class PropertyListener extends BasicExpressionListener {
        @Override
        public void enterBasicProperty(CfParser.BasicPropertyContext ctx) {
            logger.debug("Parse basic rule:{}", ctx.getText());
        }

        @Override
        public void enterArrayProperty(CfParser.ArrayPropertyContext ctx) {
            logger.debug("Parse array rule:{}", ctx.getText());
        }
    }

    class ExpressionListener extends CfParserBaseListener {
        private final String name;
        private final Type type;

        Object value = null;

        public ExpressionListener(String name, Type type) {
            this.name = name;
            this.type = type;
        }

        public void setValue(Object value) {
            if (value == null)
                throw new InvalidValueException("Got null value of :" + name);
            if (!type.getType().isAssignableFrom(value.getClass()))
                throw new InvalidValueException("Invalid type of value:" + name
                        + " ,expected to be " + type
                        + " but got " + value.getClass());
            this.value = value;
        }

        @Override
        public void enterBasic(CfParser.BasicContext ctx) {
            BasicExpressionListener listener = new BasicExpressionListener();
            ctx.basicExpression().enterRule(listener);
            this.setValue(listener.value);
        }

        @Override
        public void enterNested(CfParser.NestedContext ctx) {
            logger.debug("Parsing nested expression:{}", ctx.getText());
            NestedExpressionListener listener = new NestedExpressionListener();
            ctx.nestedExpression().enterRule(listener);
            this.setValue(listener.value);
        }
    }

    class StringListener extends CfParserBaseListener {
        String value = "";

        @Override
        public void enterStringExpression(CfParser.StringExpressionContext ctx) {
            if (ctx.STRING_LITERAL() != null) {
                String str = ctx.STRING_LITERAL().getText();
                this.value += str.substring(1, str.length() - 1);
            } else if (ctx.REFERENCE() != null) {
                String reference = ctx.REFERENCE().getText();
                String name = reference.substring(2, reference.length() - 1);
                Object value = sharedProperties.get(name);
                if (value == null)
                    throw new InvalidValueException("Reference not defined:" + reference);
                this.value += String.valueOf(value);
            }
            if (ctx.stringExpression() != null) {
                StringListener anotherListener = new StringListener();
                ctx.stringExpression().enterRule(anotherListener);
                this.value += anotherListener.value;
            }
        }
    }

    class NestedExpressionListener extends CfParserBaseListener {
        Object value = null;

        @Override
        public void enterString(CfParser.StringContext ctx) {
            StringListener listener = new StringListener();
            ctx.stringExpression().enterRule(listener);
            this.value = listener.value;
        }

        @Override
        public void enterMap(CfParser.MapContext ctx) {
            MapListener listener = new MapListener();
            ctx.mapExpression().enterRule(listener);
            this.value = listener.value;
        }
    }

    class MapListener extends CfParserBaseListener {
        Object value = null;

        @Override
        public void enterMapExpression(CfParser.MapExpressionContext ctx) {
            logger.debug("Parsing map rule:{}", ctx.getText());
            Map<String, Object> newContext = new HashMap<>();
            ctx.property().forEach(propertyContext -> {
                CfListener listener = new CfListener();
                propertyContext.enterRule(listener);
            });
            this.value = newContext;
        }
    }
    //    @Override
//    public void enterBasicProperty(CfParser.BasicPropertyContext ctx) {
//        logger.debug("Parse basic rule:{}", ctx.getText());
////        String name = ctx.NAME().getText();
////        Type type = Type.valueOf(ctx.type().getText().toUpperCase());
////        ExpressionListener expressionListener = new ExpressionListener(context, name, type);
////        ctx.expression().enterRule(expressionListener);
////        putProperty(name, expressionListener.value);
//    }
//
//    @Override
//    public void enterArrayProperty(CfParser.ArrayPropertyContext ctx) {
//        logger.debug("Parse array rule:{}", ctx.getText());
////        String name = ctx.NAME().getText();
////        Type type = Type.valueOf(ctx.type().getText().toUpperCase());
////        Object result = Array.newInstance(type.getType(), ctx.expression().size());
////        for (int i = 0; i < ctx.expression().size(); i++) {
////            ExpressionListener listener = new ExpressionListener(this.context, name, type);
////            ctx.expression().get(i).enterRule(listener);
////            Array.set(result, i, listener.value);
////        }
////        putProperty(name, result);
//    }
}