package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfLexer;
import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.config.AntlrBasedParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PropertyParser extends AntlrBasedParser<CfLexer, CfParser> {
    private static final Logger logger = LoggerFactory.getLogger(PropertyParser.class);

    public PropertyParser(boolean ignoreSyntaxError, CharStream source) {
        super(ignoreSyntaxError,
                charStream -> new CfLexer(charStream),
                tokenStream -> new CfParser(tokenStream));
        this.parse(source);
    }

    Map<String, ScriptVisitor.Scope> scopes = Collections.emptyMap();

    public List<String> getScopes() {
        return this.scopes.keySet().stream().collect(Collectors.toList());
    }

    public <T> T get(String scope, String name) {
        ScriptVisitor.Scope scopeObject = this.scopes.get(scope);
        if (scopeObject == null)
            return null;
        return (T) scopeObject.properties.get(name);
    }

    public static final String DEFAULT_SCOPE = "default";

    public <T> T get(String name) {
        return (T) this.get(DEFAULT_SCOPE, name);
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
        ScriptVisitor contextVisitor = new ScriptVisitor();
        Map<String, ScriptVisitor.Scope> scopes = contextVisitor.visit(parser.script());
        logger.debug("parsed :{}", scopes);
        if (scopes != null)
            this.scopes = scopes;
        else
            throw new RuntimeException("Unexpected null value occurs. this must be a bug");
    }
}
