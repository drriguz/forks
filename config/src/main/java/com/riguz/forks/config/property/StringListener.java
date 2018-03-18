package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.config.AntlrBasedParser;
import com.riguz.forks.config.InvalidValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

class StringListener extends CfParserBaseListener {
    private static final Logger logger = LoggerFactory.getLogger(StringListener.class);
    final Map<String, Object> context;
    String value = "";

    public StringListener(Map<String, Object> context) {
        this.context = context;
    }

    @Override
    public void enterStringExpression(CfParser.StringExpressionContext ctx) {
        if (ctx.STRING_LITERAL() != null) {
            String str = ctx.STRING_LITERAL().getText();
            this.value += str.substring(1, str.length() - 1);
        } else if (ctx.REFERENCE() != null) {
            String reference = ctx.REFERENCE().getText();
            String name = reference.substring(2, reference.length() - 1);
            Object value = this.context.get(name);
            if (value == null)
                throw new InvalidValueException("Reference not defined:" + reference);
            this.value += String.valueOf(value);
        }
        if (ctx.stringExpression() != null) {
            StringListener anotherListener = new StringListener(this.context);
            ctx.stringExpression().enterRule(anotherListener);
            this.value += anotherListener.value;
        }
    }
}