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
		STRING=1, INT=2, BOOL=3, DECIMAL=4, MAP=5, SHARED=6, ASSIGN=7, LBRACE=8, 
		RBRACE=9, LBRACK=10, RBRACK=11, SEMI=12, COMMA=13, LINK=14, BOOL_LITERAL=15, 
		INT_LITERAL=16, HEX_LITERAL=17, DECIMAL_LITERAL=18, STRING_LITERAL=19, 
		NAME=20, REFERENCE=21, COMMENT=22, LINE_COMMENT=23, WS=24;
	public static final int
		RULE_properties = 0, RULE_sharedProperty = 1, RULE_property = 2, RULE_basicProperty = 3, 
		RULE_arrayProperty = 4, RULE_type = 5, RULE_expression = 6, RULE_basicExpression = 7, 
		RULE_nestedExpression = 8, RULE_stringExpression = 9, RULE_mapExpression = 10;
	public static final String[] ruleNames = {
		"properties", "sharedProperty", "property", "basicProperty", "arrayProperty", 
		"type", "expression", "basicExpression", "nestedExpression", "stringExpression", 
		"mapExpression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'string'", "'int'", "'bool'", "'decimal'", "'map'", "'shared'", 
		"'='", "'{'", "'}'", "'['", "']'", "';'", "','", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "INT", "BOOL", "DECIMAL", "MAP", "SHARED", "ASSIGN", "LBRACE", 
		"RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "LINK", "BOOL_LITERAL", 
		"INT_LITERAL", "HEX_LITERAL", "DECIMAL_LITERAL", "STRING_LITERAL", "NAME", 
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
	public static class PropertiesContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(CfParser.EOF, 0); }
		public List<SharedPropertyContext> sharedProperty() {
			return getRuleContexts(SharedPropertyContext.class);
		}
		public SharedPropertyContext sharedProperty(int i) {
			return getRuleContext(SharedPropertyContext.class,i);
		}
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
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitProperties(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_properties);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SHARED) {
				{
				{
				setState(22);
				sharedProperty();
				setState(23);
				match(SEMI);
				}
				}
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << DECIMAL) | (1L << MAP))) != 0)) {
				{
				{
				setState(30);
				property();
				setState(31);
				match(SEMI);
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
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

	public static class SharedPropertyContext extends ParserRuleContext {
		public TerminalNode SHARED() { return getToken(CfParser.SHARED, 0); }
		public PropertyContext property() {
			return getRuleContext(PropertyContext.class,0);
		}
		public SharedPropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sharedProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterSharedProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitSharedProperty(this);
		}
	}

	public final SharedPropertyContext sharedProperty() throws RecognitionException {
		SharedPropertyContext _localctx = new SharedPropertyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sharedProperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(SHARED);
			setState(41);
			property();
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
		public BasicPropertyContext basicProperty() {
			return getRuleContext(BasicPropertyContext.class,0);
		}
		public ArrayPropertyContext arrayProperty() {
			return getRuleContext(ArrayPropertyContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitProperty(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_property);
		try {
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(43);
				basicProperty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				arrayProperty();
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

	public static class BasicPropertyContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(CfParser.NAME, 0); }
		public TerminalNode ASSIGN() { return getToken(CfParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BasicPropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterBasicProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitBasicProperty(this);
		}
	}

	public final BasicPropertyContext basicProperty() throws RecognitionException {
		BasicPropertyContext _localctx = new BasicPropertyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_basicProperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			type();
			setState(48);
			match(NAME);
			setState(49);
			match(ASSIGN);
			setState(50);
			expression();
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

	public static class ArrayPropertyContext extends ParserRuleContext {
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
		public ArrayPropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayProperty; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterArrayProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitArrayProperty(this);
		}
	}

	public final ArrayPropertyContext arrayProperty() throws RecognitionException {
		ArrayPropertyContext _localctx = new ArrayPropertyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arrayProperty);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			type();
			setState(53);
			match(NAME);
			setState(54);
			match(ASSIGN);
			setState(55);
			match(LBRACK);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LBRACE) | (1L << BOOL_LITERAL) | (1L << INT_LITERAL) | (1L << HEX_LITERAL) | (1L << DECIMAL_LITERAL) | (1L << STRING_LITERAL) | (1L << REFERENCE))) != 0)) {
				{
				{
				setState(56);
				expression();
				setState(57);
				match(COMMA);
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			match(RBRACK);
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
		public TerminalNode DECIMAL() { return getToken(CfParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(CfParser.STRING, 0); }
		public TerminalNode MAP() { return getToken(CfParser.MAP, 0); }
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
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << DECIMAL) | (1L << MAP))) != 0)) ) {
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
	public static class BasicContext extends ExpressionContext {
		public BasicExpressionContext basicExpression() {
			return getRuleContext(BasicExpressionContext.class,0);
		}
		public BasicContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterBasic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitBasic(this);
		}
	}
	public static class NestedContext extends ExpressionContext {
		public NestedExpressionContext nestedExpression() {
			return getRuleContext(NestedExpressionContext.class,0);
		}
		public NestedContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterNested(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitNested(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_expression);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL_LITERAL:
			case INT_LITERAL:
			case HEX_LITERAL:
			case DECIMAL_LITERAL:
				_localctx = new BasicContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(68);
				basicExpression();
				}
				break;
			case LBRACE:
			case STRING_LITERAL:
			case REFERENCE:
				_localctx = new NestedContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				nestedExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class BasicExpressionContext extends ParserRuleContext {
		public BasicExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicExpression; }
	 
		public BasicExpressionContext() { }
		public void copyFrom(BasicExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BoolContext extends BasicExpressionContext {
		public TerminalNode BOOL_LITERAL() { return getToken(CfParser.BOOL_LITERAL, 0); }
		public BoolContext(BasicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterBool(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitBool(this);
		}
	}
	public static class HexContext extends BasicExpressionContext {
		public TerminalNode HEX_LITERAL() { return getToken(CfParser.HEX_LITERAL, 0); }
		public HexContext(BasicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterHex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitHex(this);
		}
	}
	public static class DecimalContext extends BasicExpressionContext {
		public TerminalNode DECIMAL_LITERAL() { return getToken(CfParser.DECIMAL_LITERAL, 0); }
		public DecimalContext(BasicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitDecimal(this);
		}
	}
	public static class IntContext extends BasicExpressionContext {
		public TerminalNode INT_LITERAL() { return getToken(CfParser.INT_LITERAL, 0); }
		public IntContext(BasicExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitInt(this);
		}
	}

	public final BasicExpressionContext basicExpression() throws RecognitionException {
		BasicExpressionContext _localctx = new BasicExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_basicExpression);
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL_LITERAL:
				_localctx = new BoolContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				match(BOOL_LITERAL);
				}
				break;
			case INT_LITERAL:
				_localctx = new IntContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(INT_LITERAL);
				}
				break;
			case HEX_LITERAL:
				_localctx = new HexContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				match(HEX_LITERAL);
				}
				break;
			case DECIMAL_LITERAL:
				_localctx = new DecimalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(75);
				match(DECIMAL_LITERAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class NestedExpressionContext extends ParserRuleContext {
		public NestedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nestedExpression; }
	 
		public NestedExpressionContext() { }
		public void copyFrom(NestedExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringContext extends NestedExpressionContext {
		public StringExpressionContext stringExpression() {
			return getRuleContext(StringExpressionContext.class,0);
		}
		public StringContext(NestedExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitString(this);
		}
	}
	public static class MapContext extends NestedExpressionContext {
		public MapExpressionContext mapExpression() {
			return getRuleContext(MapExpressionContext.class,0);
		}
		public MapContext(NestedExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitMap(this);
		}
	}

	public final NestedExpressionContext nestedExpression() throws RecognitionException {
		NestedExpressionContext _localctx = new NestedExpressionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nestedExpression);
		try {
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
			case REFERENCE:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				stringExpression();
				}
				break;
			case LBRACE:
				_localctx = new MapContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				mapExpression();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	}

	public final StringExpressionContext stringExpression() throws RecognitionException {
		StringExpressionContext _localctx = new StringExpressionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_stringExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_la = _input.LA(1);
			if ( !(_la==STRING_LITERAL || _la==REFERENCE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINK) {
				{
				setState(83);
				match(LINK);
				setState(84);
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

	public static class MapExpressionContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(CfParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CfParser.RBRACE, 0); }
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(CfParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CfParser.SEMI, i);
		}
		public MapExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).enterMapExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CfParserListener ) ((CfParserListener)listener).exitMapExpression(this);
		}
	}

	public final MapExpressionContext mapExpression() throws RecognitionException {
		MapExpressionContext _localctx = new MapExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_mapExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(LBRACE);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << DECIMAL) | (1L << MAP))) != 0)) {
				{
				{
				setState(88);
				property();
				setState(89);
				match(SEMI);
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
			match(RBRACE);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32e\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\2\7\2\34\n\2\f\2\16\2\37\13\2\3\2\3\2\3\2\7\2$\n\2\f"+
		"\2\16\2\'\13\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\5\4\60\n\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6>\n\6\f\6\16\6A\13\6\3\6\3\6\3\7\3"+
		"\7\3\b\3\b\5\bI\n\b\3\t\3\t\3\t\3\t\5\tO\n\t\3\n\3\n\5\nS\n\n\3\13\3\13"+
		"\3\13\5\13X\n\13\3\f\3\f\3\f\3\f\7\f^\n\f\f\f\16\fa\13\f\3\f\3\f\3\f\2"+
		"\2\r\2\4\6\b\n\f\16\20\22\24\26\2\4\3\2\3\7\4\2\25\25\27\27\2d\2\35\3"+
		"\2\2\2\4*\3\2\2\2\6/\3\2\2\2\b\61\3\2\2\2\n\66\3\2\2\2\fD\3\2\2\2\16H"+
		"\3\2\2\2\20N\3\2\2\2\22R\3\2\2\2\24T\3\2\2\2\26Y\3\2\2\2\30\31\5\4\3\2"+
		"\31\32\7\16\2\2\32\34\3\2\2\2\33\30\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2"+
		"\2\35\36\3\2\2\2\36%\3\2\2\2\37\35\3\2\2\2 !\5\6\4\2!\"\7\16\2\2\"$\3"+
		"\2\2\2# \3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&(\3\2\2\2\'%\3\2\2\2("+
		")\7\2\2\3)\3\3\2\2\2*+\7\b\2\2+,\5\6\4\2,\5\3\2\2\2-\60\5\b\5\2.\60\5"+
		"\n\6\2/-\3\2\2\2/.\3\2\2\2\60\7\3\2\2\2\61\62\5\f\7\2\62\63\7\26\2\2\63"+
		"\64\7\t\2\2\64\65\5\16\b\2\65\t\3\2\2\2\66\67\5\f\7\2\678\7\26\2\289\7"+
		"\t\2\29?\7\f\2\2:;\5\16\b\2;<\7\17\2\2<>\3\2\2\2=:\3\2\2\2>A\3\2\2\2?"+
		"=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7\r\2\2C\13\3\2\2\2DE\t\2\2"+
		"\2E\r\3\2\2\2FI\5\20\t\2GI\5\22\n\2HF\3\2\2\2HG\3\2\2\2I\17\3\2\2\2JO"+
		"\7\21\2\2KO\7\22\2\2LO\7\23\2\2MO\7\24\2\2NJ\3\2\2\2NK\3\2\2\2NL\3\2\2"+
		"\2NM\3\2\2\2O\21\3\2\2\2PS\5\24\13\2QS\5\26\f\2RP\3\2\2\2RQ\3\2\2\2S\23"+
		"\3\2\2\2TW\t\3\2\2UV\7\20\2\2VX\5\24\13\2WU\3\2\2\2WX\3\2\2\2X\25\3\2"+
		"\2\2Y_\7\n\2\2Z[\5\6\4\2[\\\7\16\2\2\\^\3\2\2\2]Z\3\2\2\2^a\3\2\2\2_]"+
		"\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2bc\7\13\2\2c\27\3\2\2\2\13\35%/"+
		"?HNRW_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}