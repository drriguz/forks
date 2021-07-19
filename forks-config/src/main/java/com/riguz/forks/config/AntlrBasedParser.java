package com.riguz.forks.config;

import com.riguz.forks.antlr.CfParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.function.Function;

public abstract class AntlrBasedParser<L extends Lexer, P extends Parser> {
    private static final Logger logger = LoggerFactory.getLogger(AntlrBasedParser.class);

    protected final boolean ignoreSyntaxError;
    protected final Function<CharStream, L> lexerMaker;
    protected final Function<TokenStream, P> parserMaker;

    public AntlrBasedParser(Function<CharStream, L> lexerMaker,
                            Function<TokenStream, P> parserMaker) {
        this(false, lexerMaker, parserMaker);
    }

    public AntlrBasedParser(boolean ignoreSyntaxError,
                            Function<CharStream, L> lexerMaker,
                            Function<TokenStream, P> parserMaker) {
        this.ignoreSyntaxError = ignoreSyntaxError;
        this.parserMaker = parserMaker;
        this.lexerMaker = lexerMaker;
    }

    protected void parse(CharStream source) {
        logger.debug("Start to parse file: ignoreError?{}", this.ignoreSyntaxError);
        L lexer = this.lexerMaker.apply(source);
        if (!ignoreSyntaxError) {
            lexer.removeErrorListeners();
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        }
        TokenStream tokens = new CommonTokenStream(lexer);

        P parser = this.parserMaker.apply(tokens);
        if (!ignoreSyntaxError) {
            parser.removeErrorListeners();
            parser.addErrorListener(ThrowingErrorListener.INSTANCE);
        }
        logger.debug("Lexer done, start to walk tokens");
        this.walk(lexer, parser);
    }

    protected abstract void walk(L lexer, P parser);
}
