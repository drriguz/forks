// Generated from CfParser.g4 by ANTLR 4.7.1
 package com.riguz.forks.antlr; 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CfParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, INT=2, BOOL=3, FLOAT=4, SHARED=5, SCOPE=6, ASSIGN=7, LBRACE=8, 
		RBRACE=9, LBRACK=10, RBRACK=11, SEMI=12, COMMA=13, LINK=14, BOOL_LITERAL=15, 
		INT_LITERAL=16, HEX_LITERAL=17, FLOAT_LITERAL=18, STRING_LITERAL=19, NAME=20, 
		REFERENCE=21, COMMENT=22, LINE_COMMENT=23, WS=24;
	public static final int
		RULE_script = 0, RULE_scope = 1, RULE_shared = 2, RULE_property = 3, RULE_type = 4, 
		RULE_expression = 5, RULE_stringExpression = 6;
	public static final String[] ruleNames = {
		"script", "scope", "shared", "property", "type", "expression", "stringExpression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'string'", "'int'", "'bool'", "'float'", "'shared'", "'scope'", 
		"'='", "'{'", "'}'", "'['", "']'", "';'", "','", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "INT", "BOOL", "FLOAT", "SHARED", "SCOPE", "ASSIGN", "LBRACE", 
		"RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "LINK", "BOOL_LITERAL", 
		"INT_LITERAL", "HEX_LITERAL", "FLOAT_LITERAL", "STRING_LITERAL", "NAME", 
		"REFERENCE", "COMMENT", "LINE_COMMENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CfParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CfParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ScriptContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CfParser.EOF, 0); }
		public SharedContext shared() {
			return getRuleContext(SharedContext.class,0);
		}
		public List<ScopeContext> scope() {
			return getRuleContexts(ScopeContext.class);
		}
		public ScopeContext scope(int i) {
			return getRuleContext(ScopeContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SHARED) {
				{
				setState(14);
				shared();
				}
			}

			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SCOPE) {
				{
				{
				setState(17);
				scope();
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScopeContext extends ParserRuleContext {
		public TerminalNode SCOPE() { return getToken(CfParser.SCOPE, 0); }
		public TerminalNode NAME() { return getToken(CfParser.NAME, 0); }
		public TerminalNode LBRACE() { return getToken(CfParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CfParser.RBRACE, 0); }
		public List<TerminalNode> SEMI() { return getTokens(CfParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CfParser.SEMI, i);
		}
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public ScopeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scope; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterScope(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitScope(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitScope(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScopeContext scope() throws RecognitionException {
		ScopeContext _localctx = new ScopeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_scope);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(SCOPE);
			setState(26);
			match(NAME);
			setState(27);
			match(LBRACE);
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << FLOAT))) != 0)) {
				{
				{
				setState(28);
				property();
				setState(29);
				match(SEMI);
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
			match(RBRACE);
			setState(37);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SharedContext extends ParserRuleContext {
		public TerminalNode SHARED() { return getToken(CfParser.SHARED, 0); }
		public TerminalNode LBRACE() { return getToken(CfParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CfParser.RBRACE, 0); }
		public List<TerminalNode> SEMI() { return getTokens(CfParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CfParser.SEMI, i);
		}
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public SharedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shared; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterShared(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitShared(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitShared(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SharedContext shared() throws RecognitionException {
		SharedContext _localctx = new SharedContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_shared);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(SHARED);
			setState(40);
			match(LBRACE);
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << FLOAT))) != 0)) {
				{
				{
				setState(41);
				property();
				setState(42);
				match(SEMI);
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(RBRACE);
			setState(50);
			match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyContext extends ParserRuleContext {
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
	 
		public PropertyContext() { }
		public void copyFrom(PropertyContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BasicPropertyContext extends PropertyContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(CfParser.NAME, 0); }
		public TerminalNode ASSIGN() { return getToken(CfParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BasicPropertyContext(PropertyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterBasicProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitBasicProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitBasicProperty(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayPropertyContext extends PropertyContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(CfParser.NAME, 0); }
		public TerminalNode ASSIGN() { return getToken(CfParser.ASSIGN, 0); }
		public TerminalNode LBRACK() { return getToken(CfParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(CfParser.RBRACK, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CfParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CfParser.COMMA, i);
		}
		public ArrayPropertyContext(PropertyContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterArrayProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitArrayProperty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitArrayProperty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_property);
		int _la;
		try {
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new BasicPropertyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				type();
				setState(53);
				match(NAME);
				setState(54);
				match(ASSIGN);
				setState(55);
				expression();
				}
				break;
			case 2:
				_localctx = new ArrayPropertyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				type();
				setState(58);
				match(NAME);
				setState(59);
				match(ASSIGN);
				setState(60);
				match(LBRACK);
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOL_LITERAL) | (1L << INT_LITERAL) | (1L << HEX_LITERAL) | (1L << FLOAT_LITERAL) | (1L << STRING_LITERAL) | (1L << REFERENCE))) != 0)) {
					{
					setState(61);
					expression();
					}
				}

				setState(68);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(64);
					match(COMMA);
					setState(65);
					expression();
					}
					}
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(71);
				match(RBRACK);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(CfParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(CfParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(CfParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(CfParser.STRING, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << FLOAT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ReferenceContext extends ExpressionContext {
		public TerminalNode REFERENCE() { return getToken(CfParser.REFERENCE, 0); }
		public ReferenceContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitReference(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitReference(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolContext extends ExpressionContext {
		public TerminalNode BOOL_LITERAL() { return getToken(CfParser.BOOL_LITERAL, 0); }
		public BoolContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitBool(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitBool(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringContext extends ExpressionContext {
		public StringExpressionContext stringExpression() {
			return getRuleContext(StringExpressionContext.class,0);
		}
		public StringContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class HexContext extends ExpressionContext {
		public TerminalNode HEX_LITERAL() { return getToken(CfParser.HEX_LITERAL, 0); }
		public HexContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterHex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitHex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitHex(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FloatContext extends ExpressionContext {
		public TerminalNode FLOAT_LITERAL() { return getToken(CfParser.FLOAT_LITERAL, 0); }
		public FloatContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitFloat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitFloat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntContext extends ExpressionContext {
		public TerminalNode INT_LITERAL() { return getToken(CfParser.INT_LITERAL, 0); }
		public IntContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_expression);
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				_localctx = new BoolContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				match(BOOL_LITERAL);
				}
				break;
			case 2:
				_localctx = new IntContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				match(INT_LITERAL);
				}
				break;
			case 3:
				_localctx = new HexContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(79);
				match(HEX_LITERAL);
				}
				break;
			case 4:
				_localctx = new FloatContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(80);
				match(FLOAT_LITERAL);
				}
				break;
			case 5:
				_localctx = new ReferenceContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(81);
				match(REFERENCE);
				}
				break;
			case 6:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(82);
				stringExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StringExpressionContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(CfParser.STRING_LITERAL, 0); }
		public TerminalNode REFERENCE() { return getToken(CfParser.REFERENCE, 0); }
		public TerminalNode LINK() { return getToken(CfParser.LINK, 0); }
		public StringExpressionContext stringExpression() {
			return getRuleContext(StringExpressionContext.class,0);
		}
		public StringExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterStringExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitStringExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CfParserVisitor ) return ((CfParserVisitor<? extends T>)visitor).visitStringExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringExpressionContext stringExpression() throws RecognitionException {
		StringExpressionContext _localctx = new StringExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_stringExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			_la = _input.LA(1);
			if ( !(_la==STRING_LITERAL || _la==REFERENCE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINK) {
				{
				setState(86);
				match(LINK);
				setState(87);
				stringExpression();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32]\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\5\2\22\n\2\3\2\7\2\25\n"+
		"\2\f\2\16\2\30\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\7\3\"\n\3\f\3\16\3"+
		"%\13\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\7\4/\n\4\f\4\16\4\62\13\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5A\n\5\3\5\3\5\7\5E\n"+
		"\5\f\5\16\5H\13\5\3\5\3\5\5\5L\n\5\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7"+
		"V\n\7\3\b\3\b\3\b\5\b[\n\b\3\b\2\2\t\2\4\6\b\n\f\16\2\4\3\2\3\6\4\2\25"+
		"\25\27\27\2b\2\21\3\2\2\2\4\33\3\2\2\2\6)\3\2\2\2\bK\3\2\2\2\nM\3\2\2"+
		"\2\fU\3\2\2\2\16W\3\2\2\2\20\22\5\6\4\2\21\20\3\2\2\2\21\22\3\2\2\2\22"+
		"\26\3\2\2\2\23\25\5\4\3\2\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26"+
		"\27\3\2\2\2\27\31\3\2\2\2\30\26\3\2\2\2\31\32\7\2\2\3\32\3\3\2\2\2\33"+
		"\34\7\b\2\2\34\35\7\26\2\2\35#\7\n\2\2\36\37\5\b\5\2\37 \7\16\2\2 \"\3"+
		"\2\2\2!\36\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$&\3\2\2\2%#\3\2\2\2"+
		"&\'\7\13\2\2\'(\7\16\2\2(\5\3\2\2\2)*\7\7\2\2*\60\7\n\2\2+,\5\b\5\2,-"+
		"\7\16\2\2-/\3\2\2\2.+\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61"+
		"\63\3\2\2\2\62\60\3\2\2\2\63\64\7\13\2\2\64\65\7\16\2\2\65\7\3\2\2\2\66"+
		"\67\5\n\6\2\678\7\26\2\289\7\t\2\29:\5\f\7\2:L\3\2\2\2;<\5\n\6\2<=\7\26"+
		"\2\2=>\7\t\2\2>@\7\f\2\2?A\5\f\7\2@?\3\2\2\2@A\3\2\2\2AF\3\2\2\2BC\7\17"+
		"\2\2CE\5\f\7\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2"+
		"\2\2IJ\7\r\2\2JL\3\2\2\2K\66\3\2\2\2K;\3\2\2\2L\t\3\2\2\2MN\t\2\2\2N\13"+
		"\3\2\2\2OV\7\21\2\2PV\7\22\2\2QV\7\23\2\2RV\7\24\2\2SV\7\27\2\2TV\5\16"+
		"\b\2UO\3\2\2\2UP\3\2\2\2UQ\3\2\2\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2V\r\3"+
		"\2\2\2WZ\t\3\2\2XY\7\20\2\2Y[\5\16\b\2ZX\3\2\2\2Z[\3\2\2\2[\17\3\2\2\2"+
		"\13\21\26#\60@FKUZ";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}