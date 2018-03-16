package com.riguz.forks.config;

import com.riguz.forks.PParser;
import com.riguz.forks.PParserBaseListener;
import com.riguz.forks.XLexer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Test {

    static class StateListener extends PParserBaseListener {
        @Override
        public void enterStat(PParser.StatContext ctx) {
            super.enterStat(ctx);
            AssignmentListener assignmentListener = new AssignmentListener();
            ctx.assignment().forEach(assignmentContext -> assignmentContext.enterRule(assignmentListener));
        }
    }

    static class AssignmentListener extends PParserBaseListener {
        @Override
        public void enterAssignment(PParser.AssignmentContext ctx) {
            super.enterAssignment(ctx);
            ExpressionListener expressionListener = new ExpressionListener();
            ctx.expression().enterRule(expressionListener);
            System.out.println(ctx.NAME() + " => " + expressionListener.getValue());
        }
    }

    static class ExpressionListener extends PParserBaseListener {
        private String value;

        public String getValue() {
            return value;
        }

        @Override
        public void enterString(PParser.StringContext ctx) {
            super.enterString(ctx);
            this.value = ctx.getText();
        }
    }


    public static void main(String[] args) throws IOException {
        XLexer lexer = new XLexer(
            CharStreams.fromStream(
                Thread
                    .currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("test.cf")));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        tokens.getTokens().forEach(token -> {
            System.out.println(token);
        });
        PParser parser = new PParser(tokens);
        PParser.StatContext context = parser.stat();
        StateListener listener = new StateListener();
        ParseTreeWalker.DEFAULT.walk(listener, context);
    }
}
