// Generated from PParser.g4 by ANTLR 4.7.1
 package com.riguz.forks; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PParser#definations}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinations(PParser.DefinationsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PParser#defination}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefination(PParser.DefinationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PParser#basicDefination}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicDefination(PParser.BasicDefinationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PParser#arrayDefination}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayDefination(PParser.ArrayDefinationContext ctx);
	/**
	 * Visit a parse tree produced by {@link PParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(PParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link PParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(PParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(PParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link PParser#mapExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapExpression(PParser.MapExpressionContext ctx);
}