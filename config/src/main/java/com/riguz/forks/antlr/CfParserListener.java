// Generated from CfParser.g4 by ANTLR 4.7.1
 package com.riguz.forks.antlr; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CfParser}.
 */
public interface CfParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CfParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(CfParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(CfParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CfParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(CfParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(CfParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CfParser#basicProperty}.
	 * @param ctx the parse tree
	 */
	void enterBasicProperty(CfParser.BasicPropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#basicProperty}.
	 * @param ctx the parse tree
	 */
	void exitBasicProperty(CfParser.BasicPropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CfParser#arrayProperty}.
	 * @param ctx the parse tree
	 */
	void enterArrayProperty(CfParser.ArrayPropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#arrayProperty}.
	 * @param ctx the parse tree
	 */
	void exitArrayProperty(CfParser.ArrayPropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CfParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(CfParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(CfParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basic}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBasic(CfParser.BasicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basic}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBasic(CfParser.BasicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nested}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNested(CfParser.NestedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nested}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNested(CfParser.NestedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CfParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void enterBool(CfParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CfParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void exitBool(CfParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link CfParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void enterInt(CfParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CfParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void exitInt(CfParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hex}
	 * labeled alternative in {@link CfParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void enterHex(CfParser.HexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hex}
	 * labeled alternative in {@link CfParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void exitHex(CfParser.HexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decimal}
	 * labeled alternative in {@link CfParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void enterDecimal(CfParser.DecimalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decimal}
	 * labeled alternative in {@link CfParser#basicExpression}.
	 * @param ctx the parse tree
	 */
	void exitDecimal(CfParser.DecimalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link CfParser#nestedExpression}.
	 * @param ctx the parse tree
	 */
	void enterString(CfParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CfParser#nestedExpression}.
	 * @param ctx the parse tree
	 */
	void exitString(CfParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code map}
	 * labeled alternative in {@link CfParser#nestedExpression}.
	 * @param ctx the parse tree
	 */
	void enterMap(CfParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by the {@code map}
	 * labeled alternative in {@link CfParser#nestedExpression}.
	 * @param ctx the parse tree
	 */
	void exitMap(CfParser.MapContext ctx);
	/**
	 * Enter a parse tree produced by {@link CfParser#stringExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(CfParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#stringExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(CfParser.StringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CfParser#mapExpression}.
	 * @param ctx the parse tree
	 */
	void enterMapExpression(CfParser.MapExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#mapExpression}.
	 * @param ctx the parse tree
	 */
	void exitMapExpression(CfParser.MapExpressionContext ctx);
}