// Generated from PParser.g4 by ANTLR 4.7.1
 package com.riguz.forks; 
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PParser}.
 */
public interface PParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PParser#definations}.
	 * @param ctx the parse tree
	 */
	void enterDefinations(PParser.DefinationsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PParser#definations}.
	 * @param ctx the parse tree
	 */
	void exitDefinations(PParser.DefinationsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PParser#defination}.
	 * @param ctx the parse tree
	 */
	void enterDefination(PParser.DefinationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PParser#defination}.
	 * @param ctx the parse tree
	 */
	void exitDefination(PParser.DefinationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PParser#basicDefination}.
	 * @param ctx the parse tree
	 */
	void enterBasicDefination(PParser.BasicDefinationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PParser#basicDefination}.
	 * @param ctx the parse tree
	 */
	void exitBasicDefination(PParser.BasicDefinationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PParser#arrayDefination}.
	 * @param ctx the parse tree
	 */
	void enterArrayDefination(PParser.ArrayDefinationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PParser#arrayDefination}.
	 * @param ctx the parse tree
	 */
	void exitArrayDefination(PParser.ArrayDefinationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(PParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(PParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(PParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(PParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(PParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link PParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(PParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link PParser#mapExpression}.
	 * @param ctx the parse tree
	 */
	void enterMapExpression(PParser.MapExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PParser#mapExpression}.
	 * @param ctx the parse tree
	 */
	void exitMapExpression(PParser.MapExpressionContext ctx);
}