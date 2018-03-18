package com.riguz.forks.config.property;

import com.riguz.forks.antlr.CfLexer;
import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.config.AntlrBasedParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PropertyParser extends AntlrBasedParser<CfLexer, CfParser> {
    private static final Logger logger = LoggerFactory.getLogger(PropertyParser.class);

    public PropertyParser(String fileName) throws IOException {
        super(charStream -> new CfLexer(charStream),
                tokenStream -> new CfParser(tokenStream),
                fileName);
    }

    public PropertyParser(boolean ignoreSyntaxError, CharStream source) {
        super(ignoreSyntaxError,
                charStream -> new CfLexer(charStream),
                tokenStream -> new CfParser(tokenStream),
                source);
    }

    Map<String, Object> properties;

    public <T> T get(String name) {
        return (T) properties.get(name);
    }


    @Override
    protected void walk(CfLexer lexer, CfParser parser) {
        this.properties = new HashMap<>();
        PropertyListener rootListener = new PropertyListener(this.properties);
        CfParser.PropertiesContext context = parser.properties();
        ParseTreeWalker.DEFAULT.walk(rootListener, context);
        this.properties.putAll(rootListener.context);
    }
}
