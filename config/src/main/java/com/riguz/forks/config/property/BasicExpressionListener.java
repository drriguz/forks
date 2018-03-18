package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.config.AntlrBasedParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BasicExpressionListener extends CfParserBaseListener {
    private static final Logger logger = LoggerFactory.getLogger(BasicExpressionListener.class);
    Object value = null;

    @Override
    public void enterBasic(CfParser.BasicContext ctx) {
        super.enterBasic(ctx);
    }

    @Override
    public void enterBool(CfParser.BoolContext ctx) {
        if ("true".equals(ctx.getText())) {
            this.value = true;
        } else if ("false".equals(ctx.getText())) {
            this.value = false;
        }
    }

    @Override
    public void enterInt(CfParser.IntContext ctx) {
        this.value = Integer.valueOf(ctx.getText());
    }

    @Override
    public void enterHex(CfParser.HexContext ctx) {
        this.value = Integer.valueOf(ctx.getText(), 16);
    }

    @Override
    public void enterDecimal(CfParser.DecimalContext ctx) {
        this.value = Double.valueOf(ctx.getText());
    }
}