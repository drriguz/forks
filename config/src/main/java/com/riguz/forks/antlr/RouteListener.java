// Generated from Route.g4 by ANTLR 4.7.1
 package com.riguz.forks.antlr.router; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RouteParser}.
 */
public interface RouteListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RouteParser#routeConfig}.
	 * @param ctx the parse tree
	 */
	void enterRouteConfig(RouteParser.RouteConfigContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#routeConfig}.
	 * @param ctx the parse tree
	 */
	void exitRouteConfig(RouteParser.RouteConfigContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#controllers}.
	 * @param ctx the parse tree
	 */
	void enterControllers(RouteParser.ControllersContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#controllers}.
	 * @param ctx the parse tree
	 */
	void exitControllers(RouteParser.ControllersContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#filters}.
	 * @param ctx the parse tree
	 */
	void enterFilters(RouteParser.FiltersContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#filters}.
	 * @param ctx the parse tree
	 */
	void exitFilters(RouteParser.FiltersContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#classNames}.
	 * @param ctx the parse tree
	 */
	void enterClassNames(RouteParser.ClassNamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#classNames}.
	 * @param ctx the parse tree
	 */
	void exitClassNames(RouteParser.ClassNamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#packageName}.
	 * @param ctx the parse tree
	 */
	void enterPackageName(RouteParser.PackageNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#packageName}.
	 * @param ctx the parse tree
	 */
	void exitPackageName(RouteParser.PackageNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(RouteParser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(RouteParser.ClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#applyFilters}.
	 * @param ctx the parse tree
	 */
	void enterApplyFilters(RouteParser.ApplyFiltersContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#applyFilters}.
	 * @param ctx the parse tree
	 */
	void exitApplyFilters(RouteParser.ApplyFiltersContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#routes}.
	 * @param ctx the parse tree
	 */
	void enterRoutes(RouteParser.RoutesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#routes}.
	 * @param ctx the parse tree
	 */
	void exitRoutes(RouteParser.RoutesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#route}.
	 * @param ctx the parse tree
	 */
	void enterRoute(RouteParser.RouteContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#route}.
	 * @param ctx the parse tree
	 */
	void exitRoute(RouteParser.RouteContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#methods}.
	 * @param ctx the parse tree
	 */
	void enterMethods(RouteParser.MethodsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#methods}.
	 * @param ctx the parse tree
	 */
	void exitMethods(RouteParser.MethodsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#paths}.
	 * @param ctx the parse tree
	 */
	void enterPaths(RouteParser.PathsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#paths}.
	 * @param ctx the parse tree
	 */
	void exitPaths(RouteParser.PathsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(RouteParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(RouteParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(RouteParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(RouteParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(RouteParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(RouteParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RouteParser#pathParamTypes}.
	 * @param ctx the parse tree
	 */
	void enterPathParamTypes(RouteParser.PathParamTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RouteParser#pathParamTypes}.
	 * @param ctx the parse tree
	 */
	void exitPathParamTypes(RouteParser.PathParamTypesContext ctx);
}