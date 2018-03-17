package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfLexer;
import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.config.AntlrBasedParser;
import com.riguz.forks.config.InvalidValueException;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.HashMap;
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
    private PropertiesListener listener;

    public <T> T get(String name) {
        return (T) properties.get(name);
    }

    @Override
    protected void walk(CfLexer lexer, CfParser parser) {
        this.properties = new HashMap<>();
        this.listener = new PropertiesListener();
        CfParser.PropertiesContext context = parser.properties();
        ParseTreeWalker.DEFAULT.walk(this.listener, context);
    }

    private class PropertiesListener extends CfParserBaseListener {
        @Override
        public void enterBasicProperty(CfParser.BasicPropertyContext ctx) {
            String name = ctx.NAME().getText();
            Type type = Type.valueOf(ctx.type().getText().toUpperCase());
            ExpressionListener expressionListener = new ExpressionListener(name, type);
            ctx.expression().enterRule(expressionListener);
            properties.put(name, expressionListener.getValue());
        }

        @Override
        public void enterArrayProperty(CfParser.ArrayPropertyContext ctx) {
            String name = ctx.NAME().getText();
        }

        public Map<String, Object> getProperties() {
            return properties;
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

        public Object getValue() {
            return this.value;
        }

        public void setValue(Object value) {
            if (value == null || value.getClass() != type.getType())
                throw new InvalidValueException("Invalid type of value:" + name + " ,expected to be " + type);
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
        public void enterStringExpression(CfParser.StringExpressionContext ctx) {

        }

        @Override
        public void enterMapExpression(CfParser.MapExpressionContext ctx) {
        }
    }
}
