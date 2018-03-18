package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.config.AntlrBasedParser;
import com.riguz.forks.config.DuplicateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.Map;

class PropertyListener extends CfParserBaseListener {
    private static final Logger logger = LoggerFactory.getLogger(PropertyListener.class);

    final Map<String, Object> context;

    public PropertyListener(Map<String, Object> context) {
        this.context = context;
    }

    private void put(String name, Object value) {
        if (context.containsKey(name))
            throw new DuplicateException("Duplicate defination of :" + name);
        logger.info("Setting ({}) to [{}]", name, value);
        context.put(name, value);
    }

    @Override
    public void enterBasicProperty(CfParser.BasicPropertyContext ctx) {
        logger.debug("Parse basic rule:{}", ctx.getText());
        String name = ctx.NAME().getText();
        Type type = Type.valueOf(ctx.type().getText().toUpperCase());
        ExpressionListener expressionListener = new ExpressionListener(context, name, type);
        ctx.expression().enterRule(expressionListener);
        put(name, expressionListener.value);
    }

    @Override
    public void enterArrayProperty(CfParser.ArrayPropertyContext ctx) {
        logger.debug("Parse array rule:{}", ctx.getText());
        String name = ctx.NAME().getText();
        Type type = Type.valueOf(ctx.type().getText().toUpperCase());
        Object result = Array.newInstance(type.getType(), ctx.expression().size());
        for (int i = 0; i < ctx.expression().size(); i++) {
            ExpressionListener listener = new ExpressionListener(this.context, name, type);
            ctx.expression().get(i).enterRule(listener);
            Array.set(result, i, listener.value);
        }
        put(name, result);
    }
}