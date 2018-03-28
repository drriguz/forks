// Generated from Route.g4 by ANTLR 4.7.1
 package com.riguz.forks.antlr.router; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RouteParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RouteVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RouteParser#routeConfig}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRouteConfig(RouteParser.RouteConfigContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#controllers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControllers(RouteParser.ControllersContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#filters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilters(RouteParser.FiltersContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#classNames}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassNames(RouteParser.ClassNamesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#packageName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPackageName(RouteParser.PackageNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#className}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassName(RouteParser.ClassNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#applyFilters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitApplyFilters(RouteParser.ApplyFiltersContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#routes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoutes(RouteParser.RoutesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#route}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoute(RouteParser.RouteContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#methods}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethods(RouteParser.MethodsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#paths}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPaths(RouteParser.PathsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(RouteParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(RouteParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(RouteParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RouteParser#pathParamTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPathParamTypes(RouteParser.PathParamTypesContext ctx);
}