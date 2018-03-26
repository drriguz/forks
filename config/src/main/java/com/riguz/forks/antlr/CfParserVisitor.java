// Generated from CfParser.g4 by ANTLR 4.7.1
 package com.riguz.forks.antlr; 
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CfParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CfParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CfParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(CfParser.ScriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link CfParser#scope}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScope(CfParser.ScopeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CfParser#shared}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShared(CfParser.SharedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code basicProperty}
	 * labeled alternative in {@link CfParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicProperty(CfParser.BasicPropertyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayProperty}
	 * labeled alternative in {@link CfParser#property}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayProperty(CfParser.ArrayPropertyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CfParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(CfParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bool}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(CfParser.BoolContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(CfParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hex}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHex(CfParser.HexContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decimal}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal(CfParser.DecimalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code reference}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReference(CfParser.ReferenceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code string}
	 * labeled alternative in {@link CfParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(CfParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link CfParser#stringExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpression(CfParser.StringExpressionContext ctx);
}