package com.riguz.forks.config.route;

import com.riguz.forks.antlr.RouteBaseVisitor;
import com.riguz.forks.antlr.RouteParser;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RouteScriptVisitor extends RouteBaseVisitor<RouteConfig> {
    private static final Logger logger = LoggerFactory.getLogger(RouteScriptVisitor.class);

    @Override
    public RouteConfig visitRouteConfig(RouteParser.RouteConfigContext ctx) {
        final List<ClassIdentifier> controllers = new LinkedList<>();
        final List<ClassIdentifier> filters = new LinkedList<>();
        final List<RouteRule> rules = new LinkedList<>();
        ctx.controllers().forEach(controllersContext -> {
            controllers.addAll(controllersContext.accept(classNamesVisitor));
        });
        ctx.filters().forEach(filtersContext -> {
            filters.addAll(filtersContext.accept(classNamesVisitor));
        });
        ctx.routes().forEach(routesContext -> {
            rules.addAll(routesContext.accept(routesVisitor));
        });
        return new RouteConfig(controllers, filters, rules);
    }

    static final ClassNamesVisitor classNamesVisitor = new ClassNamesVisitor();

    static final PackageNameVisitor packageNameVisitor = new PackageNameVisitor();
    static final RoutesVisitor routesVisitor = new RoutesVisitor();

    public static class ClassNamesVisitor extends RouteBaseVisitor<List<ClassIdentifier>> {
        @Override
        public List<ClassIdentifier> visitControllers(RouteParser.ControllersContext ctx) {
            return ctx.classNames().accept(this);
        }

        @Override
        public List<ClassIdentifier> visitFilters(RouteParser.FiltersContext ctx) {
            return ctx.classNames().accept(this);
        }

        @Override
        public List<ClassIdentifier> visitClassNames(RouteParser.ClassNamesContext ctx) {
            logger.debug("Accept class names group:{}", ctx.getText());
            List<ClassIdentifier> classes = new LinkedList<>();
            String packageName = ctx.packageName().accept(packageNameVisitor);
            ClassIdentifierVisitor classIdentifierVisitor = new ClassIdentifierVisitor(packageName);
            ctx.className().forEach(classNameContext -> {
                logger.debug("Accept class name:{}", classNameContext);
                classes.add(classNameContext.accept(classIdentifierVisitor));
            });
            return classes;
        }
    }

    static class PackageNameVisitor extends RouteBaseVisitor<String> {
        @Override
        public String visitPackageName(RouteParser.PackageNameContext ctx) {
            return ctx.PACKAGE_NAME().getText();
        }
    }

    static class AppliedFiltersVisitor extends RouteBaseVisitor<List<String>> {
        @Override
        public List<String> visitApplyFilters(RouteParser.ApplyFiltersContext ctx) {
            List<String> filterNames = new LinkedList<>();
            ctx.IDENTIFIER().forEach(name -> {
                filterNames.add(name.getText());
            });
            return filterNames;
        }
    }

    static class ClassIdentifierVisitor extends RouteBaseVisitor<ClassIdentifier> {
        private final String packageName;

        ClassIdentifierVisitor(String packageName) {
            this.packageName = packageName;
        }

        @Override
        public ClassIdentifier visitClassName(RouteParser.ClassNameContext ctx) {
            String alias = null;
            if (ctx.IDENTIFIER().size() > 1)
                alias = ctx.IDENTIFIER(1).getText();
            String className = ctx.IDENTIFIER(0).getText();
            return ClassIdentifier.of(packageName, className, alias);
        }
    }

    static class RoutesVisitor extends RouteBaseVisitor<List<RouteRule>> {
        @Override
        public List<RouteRule> visitRoutes(RouteParser.RoutesContext ctx) {
            List<RouteRule> rules = new LinkedList<>();
            RouteVisitor routeVisitor = new RouteVisitor();
            ctx.route().forEach(routeContext -> {
                logger.debug("Visit rule:{}", routeContext.getText());
                rules.add(routeContext.accept(routeVisitor));
            });
            return rules;
        }
    }

    static class RouteVisitor extends RouteBaseVisitor<RouteRule> {
        private final MethodVisitor methodVisitor = new MethodVisitor();
        private final PatternVisitor patternVisitor = new PatternVisitor();
        private final FunctionCallVisitor functionVisitor = new FunctionCallVisitor();

        @Override
        public RouteRule visitRoute(RouteParser.RouteContext ctx) {
            String method = ctx.methods().accept(methodVisitor);
            String pattern = ctx.pattern().accept(patternVisitor);
            FunctionCall functionCall = ctx.functionCall().accept(functionVisitor);
            return new RouteRule(Collections.emptyList(), method, pattern, functionCall);
        }

        private class FunctionCallVisitor extends RouteBaseVisitor<FunctionCall> {
            private final ParamVisitor paramVisitor = new ParamVisitor();

            @Override
            public FunctionCall visitFunctionCall(RouteParser.FunctionCallContext ctx) {
                //IDENTIFIER '.' IDENTIFIER '(';
                String expression = ctx.FUNCTION().getText();
                String controllerName = expression.substring(0, expression.indexOf("."));
                String methodName = expression.substring(expression.indexOf(".") + 1, expression.indexOf("("));
                PathParam[] params = ctx.params() == null ? null : ctx.params().accept(this.paramVisitor);

                return new FunctionCall(controllerName, methodName, params);
            }

            private class ParamVisitor extends RouteBaseVisitor<PathParam[]> {
                @Override
                public PathParam[] visitParams(RouteParser.ParamsContext ctx) {
                    PathParam[] params = new PathParam[ctx.pathParamTypes().size()];
                    for (int i = 0; i < ctx.pathParamTypes().size(); i++) {
                        String name = ctx.IDENTIFIER().get(i).getText();
                        Class<?> type = this.getType(ctx.pathParamTypes().get(i));
                        params[i] = new PathParam(name, type);
                    }
                    return params;
                }

                private Class<?> getType(RouteParser.PathParamTypesContext ctx) {
                    if (ctx.INT() != null)
                        return int.class;
                    if (ctx.STRING() != null)
                        return String.class;
                    if (ctx.LONG() != null)
                        return long.class;
                    throw new RuntimeException("Unsupported path param type");
                }
            }
        }

        private class PatternVisitor extends RouteBaseVisitor<String> {
            @Override
            public String visitPattern(RouteParser.PatternContext ctx) {
                if (ctx.INDEX_PATH() != null)
                    return ctx.INDEX_PATH().getText();
                StringBuilder path = new StringBuilder();
                ctx.paths().forEach(pathsContext -> {
                    path.append(pathsContext.getText());
                });
                return path.toString();
            }

            private class PathVisitor extends RouteBaseVisitor<String> {
                @Override
                public String visitPaths(RouteParser.PathsContext ctx) {
                    if (ctx.SUB_PATH() != null)
                        return ctx.SUB_PATH().getText();
                    if (ctx.NAMED_PATH() != null)
                        return ctx.NAMED_PATH().getText();
                    if (ctx.WILDCARD_PATH() != null)
                        return ctx.WILDCARD_PATH().getText();
                    throw new RuntimeException("Unknown path");
                }
            }
        }

        private class MethodVisitor extends RouteBaseVisitor<String> {
            @Override
            public String visitMethods(RouteParser.MethodsContext ctx) {
                if (ctx.GET() != null)
                    return "GET";
                if (ctx.POST() != null)
                    return "POST";
                if (ctx.PUT() != null)
                    return "PUT";
                if (ctx.PATCH() != null)
                    return "PATCH";
                if (ctx.DELETE() != null)
                    return "DELETE";
                throw new RuntimeException("Unknown http method");
            }
        }
    }
}
