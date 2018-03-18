package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.config.AntlrBasedParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class NestedExpressionListener extends CfParserBaseListener {
    private static final Logger logger = LoggerFactory.getLogger(NestedExpressionListener.class);
    final Map<String, Object> context;
    Object value = null;

    public NestedExpressionListener(Map<String, Object> context) {
        this.context = context;
    }

    @Override
    public void enterString(CfParser.StringContext ctx) {
        StringListener listener = new StringListener(this.context);
        ctx.stringExpression().enterRule(listener);
        this.value = listener.value;
    }

    @Override
    public void enterMap(CfParser.MapContext ctx) {
        MapListener listener = new MapListener(this.context);
        ctx.mapExpression().enterRule(listener);
        this.value = listener.value;
    }
}
