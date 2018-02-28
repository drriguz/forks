package com.riguz.forks.config;

import com.riguz.forks.antlr.cfLexer;
import com.riguz.forks.antlr.cfParser;
import com.riguz.forks.antlr.cfParser.ScriptContext;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

public class Parser {

    public void parse(String config) throws IOException {
        cfLexer lexer = new cfLexer(
            CharStreams.fromStream(Thread.currentThread().getContextClassLoader().getResourceAsStream(config)));
        lexer.removeErrorListeners();
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        cfParser parser = new cfParser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(ThrowingErrorListener.INSTANCE);
        ScriptContext context = parser.script();
        ParseTreeWalker walker = new ParseTreeWalker();
        ConfigListener listener = new ConfigListener();
        walker.walk(listener, context);
    }
}
