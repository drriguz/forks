package com.riguz.forks.config.route;

import com.riguz.forks.antlr.CfLexer;
import com.riguz.forks.antlr.CfParser;
import com.riguz.forks.antlr.RouteLexer;
import com.riguz.forks.antlr.RouteParser;
import com.riguz.forks.config.AntlrBasedParser;
import com.riguz.forks.config.property.PropertyParser;
import com.riguz.forks.config.property.ScriptVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.TokenStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Function;

public class RouteLoader extends AntlrBasedParser<RouteLexer, RouteParser> {
    private static final Logger logger = LoggerFactory.getLogger(RouteLoader.class);

    public RouteLoader(boolean ignoreSyntaxError, CharStream source) {
        super(ignoreSyntaxError,
                charStream -> new RouteLexer(charStream),
                tokenStream -> new RouteParser(tokenStream));
        this.parse(source);
    }

    private RouteConfig routeConfig;

    @Override
    protected void walk(RouteLexer lexer, RouteParser parser) {
        RouteScriptVisitor visitor = new RouteScriptVisitor();
        this.routeConfig = visitor.visit(parser.routeConfig());
    }

    public RouteConfig getRouteConfig() {
        return this.routeConfig;
    }

    public static RouteLoader fromString(String string) {
        return new RouteLoader(false, CharStreams.fromString(string));
    }
}
