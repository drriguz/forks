package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfLexer;
import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.config.AntlrBasedParser;
import com.riguz.forks.config.DuplicateException;
import com.riguz.forks.config.InvalidValueException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyParser extends AntlrBasedParser<CfLexer, CfParser> {

    public PropertyParser(String fileName) throws IOException {
        super(charStream -> new CfLexer(charStream),
                tokenStream -> new CfParser(tokenStream),
                fileName);
    }

    public PropertyParser(boolean ignoreSyntaxError, CharStream source) {
        super(ignoreSyntaxError,
                charStream -> new CfLexer(charStream),
                tokenStream -> new CfParser(tokenStream),
                source);
    }

    private Map<String, Object> properties;

    public <T> T get(String name) {
        return (T) properties.get(name);
    }


    @Override
    protected void walk(CfLexer lexer, CfParser parser) {
        this.properties = new HashMap<>();
        PropertiesListener rootListener = new PropertiesListener();
        CfParser.PropertiesContext context = parser.properties();
        ParseTreeWalker.DEFAULT.walk(rootListener, context);
        this.properties.putAll(rootListener.properties);
    }

    private class PropertiesListener extends CfParserBaseListener {
        private Map<String, Object> properties = new HashMap<>();

        private void put(String name, Object value) {
            if (properties.containsKey(name))
                throw new DuplicateException("Duplicate defination of :" + name);
            properties.put(name, value);
        }

        @Override
        public void enterBasicProperty(CfParser.BasicPropertyContext ctx) {
            String name = ctx.NAME().getText();
            Type type = Type.valueOf(ctx.type().getText().toUpperCase());
            ExpressionListener expressionListener = new ExpressionListener(name, type);
            ctx.expression().enterRule(expressionListener);
            put(name, expressionListener.value);
        }

        @Override
        public void enterArrayProperty(CfParser.ArrayPropertyContext ctx) {
            String name = ctx.NAME().getText();
            Type type = Type.valueOf(ctx.type().getText().toUpperCase());
            Object result = Array.newInstance(type.getType(), ctx.expression().size());
            for (int i = 0; i < ctx.expression().size(); i++) {
                ExpressionListener expressionListener = new ExpressionListener(name, type);
                ctx.expression().get(i).enterRule(expressionListener);
                Array.set(result, i, expressionListener.value);
            }
            put(name, result);
        }
    }

    private class ExpressionListener extends CfParserBaseListener {
        private final Type type;
        private final String name;
        private Object value = null;

        private ExpressionListener(String name, Type type) {
            this.name = name;
            this.type = type;
        }

        public void setValue(Object value) {
            if (value == null || value.getClass() != type.getType())
                throw new InvalidValueException("Invalid type of value:" + name
                        + " ,expected to be " + type
                        + " but got " + value);
            this.value = value;
        }

        @Override
        public void enterBool(CfParser.BoolContext ctx) {
            if ("true".equals(ctx.getText())) {
                this.setValue(true);
            } else if ("false".equals(ctx.getText())) {
                this.setValue(false);
            }
        }

        @Override
        public void enterInt(CfParser.IntContext ctx) {
            this.setValue(Integer.valueOf(ctx.getText()));
        }

        @Override
        public void enterHex(CfParser.HexContext ctx) {
            this.setValue(Integer.valueOf(ctx.getText(), 16));
        }

        @Override
        public void enterDecimal(CfParser.DecimalContext ctx) {
            this.setValue(Double.valueOf(ctx.getText()));
        }

        @Override
        public void enterString(CfParser.StringContext ctx) {
            StringListener stringListener = new StringListener();
            ctx.stringExpression().enterRule(stringListener);
            this.setValue(stringListener.value);
        }

        @Override
        public void enterMap(CfParser.MapContext ctx) {
            ctx.enterRule(this);
        }

        @Override
        public void enterMapExpression(CfParser.MapExpressionContext ctx) {
            PropertiesListener propertiesListener = new PropertiesListener();
            ctx.enterRule(propertiesListener);
            this.setValue(propertiesListener.properties);
        }
    }

    private class StringListener extends CfParserBaseListener {
        private String value = "";

        @Override
        public void enterStringExpression(CfParser.StringExpressionContext ctx) {
            if (ctx.STRING_LITERAL() != null) {
                String str = ctx.STRING_LITERAL().getText();
                this.value += str.substring(1, str.length() - 1);
            } else if (ctx.REFERENCE() != null) {
                String reference = ctx.REFERENCE().getText();
                String name = reference.substring(2, reference.length() - 1);
                Object value = properties.get(name);
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

}
