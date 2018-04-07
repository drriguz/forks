package com.riguz.forks.config.route;

import com.riguz.forks.antlr.RouteBaseVisitor;
import com.riguz.forks.antlr.RouteParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RouteScriptVisitor extends RouteBaseVisitor<RouteConfig> {
    private static final Logger logger = LoggerFactory.getLogger(RouteScriptVisitor.class);

    @Override
    public RouteConfig visitRouteConfig(RouteParser.RouteConfigContext ctx) {
        List<ClassIdentifier> controllers = new LinkedList<>();

        ctx.controllers().forEach(controllersContext -> {
            controllers.addAll(controllersContext.accept(classNamesVisitor));
        });
        return new RouteConfig(controllers, Collections.emptyList(), Collections.emptyList());
    }

    static final ClassNamesVisitor classNamesVisitor = new ClassNamesVisitor();

    static final PackageNameVisitor packageNameVisitor = new PackageNameVisitor();

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
}
