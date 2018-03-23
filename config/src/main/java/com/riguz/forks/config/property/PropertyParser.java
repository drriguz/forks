package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfLexer;
import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.CfParserBaseListener;
import com.riguz.forks.antlr.CfParserBaseVisitor;
import com.riguz.forks.config.AntlrBasedParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PropertyParser extends AntlrBasedParser<CfLexer, CfParser> {
    private static final Logger logger = LoggerFactory.getLogger(PropertyParser.class);

    public PropertyParser(boolean ignoreSyntaxError, CharStream source) {
        super(ignoreSyntaxError,
                charStream -> new CfLexer(charStream),
                tokenStream -> new CfParser(tokenStream));
        this.parse(source);
    }

    Map<String, Object> properties = Collections.emptyMap();
    Map<String, Object> sharedProperties = Collections.emptyMap();

    public <T> T get(String name) {
        return (T) this.properties.get(name);
    }

    public <T> T getShared(String name) {
        return (T) this.sharedProperties.get(name);
    }

    public static PropertyParser fromString(String string) {
        return new PropertyParser(false,
                CharStreams.fromString(string));
    }

    public static PropertyParser fromResource(String fileName) throws IOException {
        return new PropertyParser(false,
                CharStreams.fromStream(
                        Thread.currentThread()
                                .getContextClassLoader()
                                .getResourceAsStream(fileName)));
    }

    @Override
    protected void walk(CfLexer lexer, CfParser parser) {
        ScriptVisitor visitor = ScriptVisitor.ofShared();
        Map<String, Object> shared = visitor.visit(parser.shared());
        logger.debug("shared:{}", shared);
        if (shared == null)
            throw new RuntimeException("Unexpected null value occurs. this must be a bug");
        this.sharedProperties = shared;
    }
}
