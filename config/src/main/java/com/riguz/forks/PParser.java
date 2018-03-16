// Generated from PParser.g4 by ANTLR 4.7.1
 package com.riguz.forks; 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, INT=2, BOOL=3, DECIMAL=4, MAP=5, NAME=6, REFERENCE=7, ASSIGN=8, 
		LBRACE=9, RBRACE=10, LBRACK=11, RBRACK=12, SEMI=13, COMMA=14, BOOL_LITERAL=15, 
		INT_LITERAL=16, HEX_LITERAL=17, DECIMAL_LITERAL=18, STRING_LITERAL=19, 
		COMMENT=20, LINE_COMMENT=21, WS=22;
	public static final int
		RULE_definations = 0, RULE_defination = 1, RULE_basicDefination = 2, RULE_arrayDefination = 3, 
		RULE_type = 4, RULE_expression = 5, RULE_string = 6, RULE_mapExpression = 7;
	public static final String[] ruleNames = {
		"definations", "defination", "basicDefination", "arrayDefination", "type", 
		"expression", "string", "mapExpression"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'string'", "'int'", "'bool'", "'decimal'", "'map'", null, null, 
		"'='", "'{'", "'}'", "'['", "']'", "';'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "INT", "BOOL", "DECIMAL", "MAP", "NAME", "REFERENCE", 
		"ASSIGN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "BOOL_LITERAL", 
		"INT_LITERAL", "HEX_LITERAL", "DECIMAL_LITERAL", "STRING_LITERAL", "COMMENT", 
		"LINE_COMMENT", "WS"
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
	public String getGrammarFileName() { return "PParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class DefinationsContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PParser.EOF, 0); }
		public List<DefinationContext> defination() {
			return getRuleContexts(DefinationContext.class);
		}
		public DefinationContext defination(int i) {
			return getRuleContext(DefinationContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PParser.SEMI, i);
		}
		public DefinationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_definations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).enterDefinations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).exitDefinations(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PParserVisitor ) return ((PParserVisitor<? extends T>)visitor).visitDefinations(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinationsContext definations() throws RecognitionException {
		DefinationsContext _localctx = new DefinationsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_definations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << DECIMAL) | (1L << MAP))) != 0)) {
				{
				{
				setState(16);
				defination();
				setState(17);
				match(SEMI);
				}
				}
				setState(23);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(24);
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

	public static class DefinationContext extends ParserRuleContext {
		public BasicDefinationContext basicDefination() {
			return getRuleContext(BasicDefinationContext.class,0);
		}
		public ArrayDefinationContext arrayDefination() {
			return getRuleContext(ArrayDefinationContext.class,0);
		}
		public DefinationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defination; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).enterDefination(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).exitDefination(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PParserVisitor ) return ((PParserVisitor<? extends T>)visitor).visitDefination(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefinationContext defination() throws RecognitionException {
		DefinationContext _localctx = new DefinationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_defination);
		try {
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(26);
				basicDefination();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(27);
				arrayDefination();
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

	public static class BasicDefinationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(PParser.NAME, 0); }
		public TerminalNode ASSIGN() { return getToken(PParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BasicDefinationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicDefination; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).enterBasicDefination(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).exitBasicDefination(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PParserVisitor ) return ((PParserVisitor<? extends T>)visitor).visitBasicDefination(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BasicDefinationContext basicDefination() throws RecognitionException {
		BasicDefinationContext _localctx = new BasicDefinationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_basicDefination);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			type();
			setState(31);
			match(NAME);
			setState(32);
			match(ASSIGN);
			setState(33);
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

	public static class ArrayDefinationContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(PParser.NAME, 0); }
		public TerminalNode ASSIGN() { return getToken(PParser.ASSIGN, 0); }
		public TerminalNode LBRACK() { return getToken(PParser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(PParser.RBRACK, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(PParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(PParser.COMMA, i);
		}
		public ArrayDefinationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayDefination; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).enterArrayDefination(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).exitArrayDefination(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PParserVisitor ) return ((PParserVisitor<? extends T>)visitor).visitArrayDefination(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayDefinationContext arrayDefination() throws RecognitionException {
		ArrayDefinationContext _localctx = new ArrayDefinationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_arrayDefination);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			type();
			setState(36);
			match(NAME);
			setState(37);
			match(ASSIGN);
			setState(38);
			match(LBRACK);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << REFERENCE) | (1L << LBRACE) | (1L << BOOL_LITERAL) | (1L << INT_LITERAL) | (1L << HEX_LITERAL) | (1L << DECIMAL_LITERAL) | (1L << STRING_LITERAL))) != 0)) {
				{
				{
				setState(39);
				expression();
				setState(40);
				match(COMMA);
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
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
		public TerminalNode BOOL() { return getToken(PParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(PParser.INT, 0); }
		public TerminalNode DECIMAL() { return getToken(PParser.DECIMAL, 0); }
		public TerminalNode STRING() { return getToken(PParser.STRING, 0); }
		public TerminalNode MAP() { return getToken(PParser.MAP, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PParserVisitor ) return ((PParserVisitor<? extends T>)visitor).visitType(this);
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
			setState(49);
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
		public TerminalNode BOOL_LITERAL() { return getToken(PParser.BOOL_LITERAL, 0); }
		public TerminalNode INT_LITERAL() { return getToken(PParser.INT_LITERAL, 0); }
		public TerminalNode HEX_LITERAL() { return getToken(PParser.HEX_LITERAL, 0); }
		public TerminalNode DECIMAL_LITERAL() { return getToken(PParser.DECIMAL_LITERAL, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public MapExpressionContext mapExpression() {
			return getRuleContext(MapExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PParserVisitor ) return ((PParserVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
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
				enterOuterAlt(_localctx, 1);
				{
				setState(51);
				match(BOOL_LITERAL);
				}
				break;
			case INT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(52);
				match(INT_LITERAL);
				}
				break;
			case HEX_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(53);
				match(HEX_LITERAL);
				}
				break;
			case DECIMAL_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(54);
				match(DECIMAL_LITERAL);
				}
				break;
			case REFERENCE:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 5);
				{
				setState(55);
				string();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 6);
				{
				setState(56);
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

	public static class StringContext extends ParserRuleContext {
		public List<TerminalNode> STRING_LITERAL() { return getTokens(PParser.STRING_LITERAL); }
		public TerminalNode STRING_LITERAL(int i) {
			return getToken(PParser.STRING_LITERAL, i);
		}
		public List<TerminalNode> REFERENCE() { return getTokens(PParser.REFERENCE); }
		public TerminalNode REFERENCE(int i) {
			return getToken(PParser.REFERENCE, i);
		}
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PParserVisitor ) return ((PParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(59);
				_la = _input.LA(1);
				if ( !(_la==REFERENCE || _la==STRING_LITERAL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(62); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==REFERENCE || _la==STRING_LITERAL );
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
		public TerminalNode LBRACE() { return getToken(PParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(PParser.RBRACE, 0); }
		public List<DefinationContext> defination() {
			return getRuleContexts(DefinationContext.class);
		}
		public DefinationContext defination(int i) {
			return getRuleContext(DefinationContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(PParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(PParser.SEMI, i);
		}
		public MapExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).enterMapExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PParserListener ) ((PParserListener)listener).exitMapExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PParserVisitor ) return ((PParserVisitor<? extends T>)visitor).visitMapExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapExpressionContext mapExpression() throws RecognitionException {
		MapExpressionContext _localctx = new MapExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_mapExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(LBRACE);
			setState(68); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				defination();
				setState(66);
				match(SEMI);
				}
				}
				setState(70); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << STRING) | (1L << INT) | (1L << BOOL) | (1L << DECIMAL) | (1L << MAP))) != 0) );
			setState(72);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30M\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\7\2\26"+
		"\n\2\f\2\16\2\31\13\2\3\2\3\2\3\3\3\3\5\3\37\n\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5-\n\5\f\5\16\5\60\13\5\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\5\7<\n\7\3\b\6\b?\n\b\r\b\16\b@\3\t\3\t\3\t\3\t"+
		"\6\tG\n\t\r\t\16\tH\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\4\3\2\3\7\4"+
		"\2\t\t\25\25\2N\2\27\3\2\2\2\4\36\3\2\2\2\6 \3\2\2\2\b%\3\2\2\2\n\63\3"+
		"\2\2\2\f;\3\2\2\2\16>\3\2\2\2\20B\3\2\2\2\22\23\5\4\3\2\23\24\7\17\2\2"+
		"\24\26\3\2\2\2\25\22\3\2\2\2\26\31\3\2\2\2\27\25\3\2\2\2\27\30\3\2\2\2"+
		"\30\32\3\2\2\2\31\27\3\2\2\2\32\33\7\2\2\3\33\3\3\2\2\2\34\37\5\6\4\2"+
		"\35\37\5\b\5\2\36\34\3\2\2\2\36\35\3\2\2\2\37\5\3\2\2\2 !\5\n\6\2!\"\7"+
		"\b\2\2\"#\7\n\2\2#$\5\f\7\2$\7\3\2\2\2%&\5\n\6\2&\'\7\b\2\2\'(\7\n\2\2"+
		"(.\7\r\2\2)*\5\f\7\2*+\7\20\2\2+-\3\2\2\2,)\3\2\2\2-\60\3\2\2\2.,\3\2"+
		"\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2\61\62\7\16\2\2\62\t\3\2\2\2\63"+
		"\64\t\2\2\2\64\13\3\2\2\2\65<\7\21\2\2\66<\7\22\2\2\67<\7\23\2\28<\7\24"+
		"\2\29<\5\16\b\2:<\5\20\t\2;\65\3\2\2\2;\66\3\2\2\2;\67\3\2\2\2;8\3\2\2"+
		"\2;9\3\2\2\2;:\3\2\2\2<\r\3\2\2\2=?\t\3\2\2>=\3\2\2\2?@\3\2\2\2@>\3\2"+
		"\2\2@A\3\2\2\2A\17\3\2\2\2BF\7\13\2\2CD\5\4\3\2DE\7\17\2\2EG\3\2\2\2F"+
		"C\3\2\2\2GH\3\2\2\2HF\3\2\2\2HI\3\2\2\2IJ\3\2\2\2JK\7\f\2\2K\21\3\2\2"+
		"\2\b\27\36.;@H";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}