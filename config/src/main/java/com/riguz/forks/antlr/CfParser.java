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
		STRING=1, INT=2, BOOL=3, DECIMAL=4, MAP=5, ASSIGN=6, LBRACE=7, RBRACE=8, 
		LBRACK=9, RBRACK=10, SEMI=11, COMMA=12, LINK=13, BOOL_LITERAL=14, INT_LITERAL=15, 
		HEX_LITERAL=16, DECIMAL_LITERAL=17, STRING_LITERAL=18, NAME=19, REFERENCE=20, 
		COMMENT=21, LINE_COMMENT=22, WS=23;
	public static final int
		RULE_properties = 0, RULE_property = 1, RULE_basicProperty = 2, RULE_arrayProperty = 3, 
		RULE_type = 4, RULE_expression = 5, RULE_basicExpression = 6, RULE_nestedExpression = 7, 
		RULE_stringExpression = 8, RULE_mapExpression = 9;
	public static final String[] ruleNames = {
		"properties", "property", "basicProperty", "arrayProperty", "type", "expression", 
		"basicExpression", "nestedExpression", "stringExpression", "mapExpression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'string'", "'int'", "'bool'", "'decimal'", "'map'", "'='", "'{'", 
		"'}'", "'['", "']'", "';'", "','", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "INT", "BOOL", "DECIMAL", "MAP", "ASSIGN", "LBRACE", "RBRACE", 
		"LBRACK", "RBRACK", "SEMI", "COMMA", "LINK", "BOOL_LITERAL", "INT_LITERAL", 
		"HEX_LITERAL", "DECIMAL_LITERAL", "STRING_LITERAL", "NAME", "REFERENCE", 
		"COMMENT", "LINE_COMMENT", "WS"
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
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << DECIMAL) | (1L << MAP))) != 0)) {
				{
				{
				setState(20);
				property();
				setState(21);
				match(SEMI);
				}
				}
				setState(27);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(28);
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
		enterRule(_localctx, 2, RULE_property);
		try {
			setState(32);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				basicProperty();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(31);
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
		enterRule(_localctx, 4, RULE_basicProperty);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			type();
			setState(35);
			match(NAME);
			setState(36);
			match(ASSIGN);
			setState(37);
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
		enterRule(_localctx, 6, RULE_arrayProperty);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			type();
			setState(40);
			match(NAME);
			setState(41);
			match(ASSIGN);
			setState(42);
			match(LBRACK);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LBRACE) | (1L << BOOL_LITERAL) | (1L << INT_LITERAL) | (1L << HEX_LITERAL) | (1L << DECIMAL_LITERAL) | (1L << STRING_LITERAL) | (1L << REFERENCE))) != 0)) {
				{
				{
				setState(43);
				expression();
				setState(44);
				match(COMMA);
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
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
		enterRule(_localctx, 8, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
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
		enterRule(_localctx, 10, RULE_expression);
		try {
			setState(57);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL_LITERAL:
			case INT_LITERAL:
			case HEX_LITERAL:
			case DECIMAL_LITERAL:
				_localctx = new BasicContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				basicExpression();
				}
				break;
			case LBRACE:
			case STRING_LITERAL:
			case REFERENCE:
				_localctx = new NestedContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
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
		enterRule(_localctx, 12, RULE_basicExpression);
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL_LITERAL:
				_localctx = new BoolContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				match(BOOL_LITERAL);
				}
				break;
			case INT_LITERAL:
				_localctx = new IntContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(60);
				match(INT_LITERAL);
				}
				break;
			case HEX_LITERAL:
				_localctx = new HexContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(61);
				match(HEX_LITERAL);
				}
				break;
			case DECIMAL_LITERAL:
				_localctx = new DecimalContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(62);
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
		enterRule(_localctx, 14, RULE_nestedExpression);
		try {
			setState(67);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING_LITERAL:
			case REFERENCE:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				stringExpression();
				}
				break;
			case LBRACE:
				_localctx = new MapContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
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
		enterRule(_localctx, 16, RULE_stringExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_la = _input.LA(1);
			if ( !(_la==STRING_LITERAL || _la==REFERENCE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(72);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINK) {
				{
				setState(70);
				match(LINK);
				setState(71);
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
		enterRule(_localctx, 18, RULE_mapExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(LBRACE);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << DECIMAL) | (1L << MAP))) != 0)) {
				{
				{
				setState(75);
				property();
				setState(76);
				match(SEMI);
				}
				}
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(83);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31X\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\3\2\3\2\7\2\32\n\2\f\2\16\2\35\13\2\3\2\3\2\3\3\3\3\5\3#\n\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\61\n\5\f\5\16\5\64\13\5"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\5\7<\n\7\3\b\3\b\3\b\3\b\5\bB\n\b\3\t\3\t\5\t"+
		"F\n\t\3\n\3\n\3\n\5\nK\n\n\3\13\3\13\3\13\3\13\7\13Q\n\13\f\13\16\13T"+
		"\13\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\4\3\2\3\7\4\2\24"+
		"\24\26\26\2W\2\33\3\2\2\2\4\"\3\2\2\2\6$\3\2\2\2\b)\3\2\2\2\n\67\3\2\2"+
		"\2\f;\3\2\2\2\16A\3\2\2\2\20E\3\2\2\2\22G\3\2\2\2\24L\3\2\2\2\26\27\5"+
		"\4\3\2\27\30\7\r\2\2\30\32\3\2\2\2\31\26\3\2\2\2\32\35\3\2\2\2\33\31\3"+
		"\2\2\2\33\34\3\2\2\2\34\36\3\2\2\2\35\33\3\2\2\2\36\37\7\2\2\3\37\3\3"+
		"\2\2\2 #\5\6\4\2!#\5\b\5\2\" \3\2\2\2\"!\3\2\2\2#\5\3\2\2\2$%\5\n\6\2"+
		"%&\7\25\2\2&\'\7\b\2\2\'(\5\f\7\2(\7\3\2\2\2)*\5\n\6\2*+\7\25\2\2+,\7"+
		"\b\2\2,\62\7\13\2\2-.\5\f\7\2./\7\16\2\2/\61\3\2\2\2\60-\3\2\2\2\61\64"+
		"\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62\3\2\2\2\65\66"+
		"\7\f\2\2\66\t\3\2\2\2\678\t\2\2\28\13\3\2\2\29<\5\16\b\2:<\5\20\t\2;9"+
		"\3\2\2\2;:\3\2\2\2<\r\3\2\2\2=B\7\20\2\2>B\7\21\2\2?B\7\22\2\2@B\7\23"+
		"\2\2A=\3\2\2\2A>\3\2\2\2A?\3\2\2\2A@\3\2\2\2B\17\3\2\2\2CF\5\22\n\2DF"+
		"\5\24\13\2EC\3\2\2\2ED\3\2\2\2F\21\3\2\2\2GJ\t\3\2\2HI\7\17\2\2IK\5\22"+
		"\n\2JH\3\2\2\2JK\3\2\2\2K\23\3\2\2\2LR\7\t\2\2MN\5\4\3\2NO\7\r\2\2OQ\3"+
		"\2\2\2PM\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SU\3\2\2\2TR\3\2\2\2UV\7"+
		"\n\2\2V\25\3\2\2\2\n\33\"\62;AEJR";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}