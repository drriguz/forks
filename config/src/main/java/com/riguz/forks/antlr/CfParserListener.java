// Generated from CfParser.g4 by ANTLR 4.7.1
 package com.riguz.forks.antlr; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CfParser}.
 */
public interface CfParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CfParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(CfParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(CfParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link CfParser#scope}.
	 * @param ctx the parse tree
	 */
	void enterScope(CfParser.ScopeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#scope}.
	 * @param ctx the parse tree
	 */
	void exitScope(CfParser.ScopeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CfParser#shared}.
	 * @param ctx the parse tree
	 */
	void enterShared(CfParser.SharedContext ctx);
	/**
	 * Exit a parse tree produced by {@link CfParser#shared}.
	 * @param ctx the parse tree
	 */
	void exitShared(CfParser.SharedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicProperty}
	 * labeled alternative in {@link CfParser#property}.
	 * @param ctx the parse tree
	 */
	void enterBasicProperty(CfParser.BasicPropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicProperty}
	 * labeled alternative in {@link CfParser#property}.
	 * @param ctx the parse tree
	 */
	void exitBasicProperty(CfParser.BasicPropertyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayProperty}
	 * labeled alternative in {@link CfParser#property}.
	 * @param ctx the parse tree
	 */
	void enterArrayProperty(CfParser.ArrayPropertyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayProperty}
	 * labeled alternative in {@link CfParser#property}.
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
	 * Enter a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBool(CfParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBool(CfParser.BoolContext ctx);
	/**
	 * Enter a parse tree produced by the {@code int}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterInt(CfParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitInt(CfParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hex}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterHex(CfParser.HexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hex}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitHex(CfParser.HexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code float}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFloat(CfParser.FloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code float}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFloat(CfParser.FloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code reference}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterReference(CfParser.ReferenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code reference}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitReference(CfParser.ReferenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code string}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterString(CfParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitString(CfParser.StringContext ctx);
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
}