package com.riguz.forks.config;

import com.riguz.forks.antlr.cfBaseListener;
import com.riguz.forks.antlr.cfParser.AssignmentContext;

public class ConfigListener extends cfBaseListener {

    @Override
    public void enterAssignment(AssignmentContext ctx) {
        System.out.println(ctx.getText());
    }

}
