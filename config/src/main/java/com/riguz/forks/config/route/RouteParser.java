package com.riguz.forks.config.route;

import com.riguz.forks.antlr.RouteLexer;
import com.riguz.forks.config.AntlrBasedParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RouteParser extends AntlrBasedParser<RouteLexer, com.riguz.forks.antlr.RouteParser> {
    private static final Logger logger = LoggerFactory.getLogger(RouteParser.class);

    public RouteParser(boolean ignoreSyntaxError, CharStream source) {
        super(ignoreSyntaxError,
                charStream -> new RouteLexer(charStream),
                tokenStream -> new com.riguz.forks.antlr.RouteParser(tokenStream));
        this.parse(source);
    }

    private RouteConfig routeConfig;

    @Override
    protected void walk(RouteLexer lexer, com.riguz.forks.antlr.RouteParser parser) {
        RouteScriptVisitor visitor = new RouteScriptVisitor();
        this.routeConfig = visitor.visit(parser.routeConfig());
    }

    public RouteConfig getRouteConfig() {
        return this.routeConfig;
    }

    public static RouteParser fromString(String string) {
        return new RouteParser(false, CharStreams.fromString(string));
    }

    public static RouteParser fromResource(String name) throws IOException {
        InputStream resource = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        if(resource == null)
            throw new FileNotFoundException("Resource not found:" + name);
        return new RouteParser(false,
                CharStreams.fromReader(
                        new InputStreamReader(resource)));
    }
}
