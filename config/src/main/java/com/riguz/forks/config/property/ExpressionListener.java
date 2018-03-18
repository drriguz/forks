package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.config.AntlrBasedParser;
import com.riguz.forks.config.InvalidValueException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ExpressionListener extends CfParserBaseListener {
    private static final Logger logger = LoggerFactory.getLogger(ExpressionListener.class);

    private final Map<String, Object> context;
    private final String name;
    private final Type type;

    Object value = null;

    public ExpressionListener(Map<String, Object> context, String name, Type type) {
        this.context = context;
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
        NestedExpressionListener listener = new NestedExpressionListener(this.context);
        ctx.nestedExpression().enterRule(listener);
        this.setValue(listener.value);
    }
}
