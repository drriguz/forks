package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.config.AntlrBasedParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class MapListener extends CfParserBaseListener {
    private static final Logger logger = LoggerFactory.getLogger(MapListener.class);
    final Map<String, Object> context;
    Object value = null;

    public MapListener(Map<String, Object> context) {
        this.context = context;
    }

    @Override
    public void enterMapExpression(CfParser.MapExpressionContext ctx) {
        logger.debug("Parsing map rule:{}", ctx.getText());
        Map<String, Object> newContext = new HashMap<>();
        ctx.property().forEach(propertyContext -> {
            PropertyListener listener = new PropertyListener(newContext);
            propertyContext.enterRule(listener);
        });
        this.value = newContext;
    }
}
