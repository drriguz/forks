// Generated from CfLexer.g4 by ANTLR 4.7.1
 package com.riguz.forks.antlr; 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CfLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, INT=2, BOOL=3, FLOAT=4, SHARED=5, SCOPE=6, ASSIGN=7, LBRACE=8, 
		RBRACE=9, LBRACK=10, RBRACK=11, SEMI=12, COMMA=13, LINK=14, BOOL_LITERAL=15, 
		INT_LITERAL=16, HEX_LITERAL=17, FLOAT_LITERAL=18, STRING_LITERAL=19, NAME=20, 
		REFERENCE=21, COMMENT=22, LINE_COMMENT=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"STRING", "INT", "BOOL", "FLOAT", "SHARED", "SCOPE", "ASSIGN", "LBRACE", 
		"RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "LINK", "BOOL_LITERAL", 
		"INT_LITERAL", "HEX_LITERAL", "FLOAT_LITERAL", "STRING_LITERAL", "NAME", 
		"REFERENCE", "Char", "EscapeSequence", "Digit", "HexDigit", "COMMENT", 
		"LINE_COMMENT", "WS"
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


	public CfLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CfLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00d2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\5\20y\n\20\3\21\6\21|\n\21\r\21\16\21}\3\22"+
		"\3\22\3\22\3\22\6\22\u0084\n\22\r\22\16\22\u0085\3\23\6\23\u0089\n\23"+
		"\r\23\16\23\u008a\3\23\3\23\6\23\u008f\n\23\r\23\16\23\u0090\3\24\3\24"+
		"\3\24\7\24\u0096\n\24\f\24\16\24\u0099\13\24\3\24\3\24\3\25\3\25\7\25"+
		"\u009f\n\25\f\25\16\25\u00a2\13\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\7\33\u00b7"+
		"\n\33\f\33\16\33\u00ba\13\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\7\34\u00c5\n\34\f\34\16\34\u00c8\13\34\3\34\3\34\3\35\6\35\u00cd\n"+
		"\35\r\35\16\35\u00ce\3\35\3\35\3\u00b8\2\36\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\2/\2\61\2\63\2\65\30\67\319\32\3\2\n\5\2C\\aac|\6\2\62;C\\aac|\6"+
		"\2\f\f\17\17$$^^\7\2$$^^ppttvv\3\2\62;\4\2\62;ch\4\2\f\f\17\17\5\2\13"+
		"\f\16\17\"\"\2\u00d8\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2"+
		"\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5B\3\2\2\2\7F\3\2\2\2"+
		"\tK\3\2\2\2\13Q\3\2\2\2\rX\3\2\2\2\17^\3\2\2\2\21`\3\2\2\2\23b\3\2\2\2"+
		"\25d\3\2\2\2\27f\3\2\2\2\31h\3\2\2\2\33j\3\2\2\2\35l\3\2\2\2\37x\3\2\2"+
		"\2!{\3\2\2\2#\177\3\2\2\2%\u0088\3\2\2\2\'\u0092\3\2\2\2)\u009c\3\2\2"+
		"\2+\u00a3\3\2\2\2-\u00a9\3\2\2\2/\u00ab\3\2\2\2\61\u00ae\3\2\2\2\63\u00b0"+
		"\3\2\2\2\65\u00b2\3\2\2\2\67\u00c0\3\2\2\29\u00cc\3\2\2\2;<\7u\2\2<=\7"+
		"v\2\2=>\7t\2\2>?\7k\2\2?@\7p\2\2@A\7i\2\2A\4\3\2\2\2BC\7k\2\2CD\7p\2\2"+
		"DE\7v\2\2E\6\3\2\2\2FG\7d\2\2GH\7q\2\2HI\7q\2\2IJ\7n\2\2J\b\3\2\2\2KL"+
		"\7h\2\2LM\7n\2\2MN\7q\2\2NO\7c\2\2OP\7v\2\2P\n\3\2\2\2QR\7u\2\2RS\7j\2"+
		"\2ST\7c\2\2TU\7t\2\2UV\7g\2\2VW\7f\2\2W\f\3\2\2\2XY\7u\2\2YZ\7e\2\2Z["+
		"\7q\2\2[\\\7r\2\2\\]\7g\2\2]\16\3\2\2\2^_\7?\2\2_\20\3\2\2\2`a\7}\2\2"+
		"a\22\3\2\2\2bc\7\177\2\2c\24\3\2\2\2de\7]\2\2e\26\3\2\2\2fg\7_\2\2g\30"+
		"\3\2\2\2hi\7=\2\2i\32\3\2\2\2jk\7.\2\2k\34\3\2\2\2lm\7\60\2\2mn\7\60\2"+
		"\2n\36\3\2\2\2op\7v\2\2pq\7t\2\2qr\7w\2\2ry\7g\2\2st\7h\2\2tu\7c\2\2u"+
		"v\7n\2\2vw\7u\2\2wy\7g\2\2xo\3\2\2\2xs\3\2\2\2y \3\2\2\2z|\5\61\31\2{"+
		"z\3\2\2\2|}\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\"\3\2\2\2\177\u0080\7\62\2\2"+
		"\u0080\u0081\7z\2\2\u0081\u0083\3\2\2\2\u0082\u0084\5\63\32\2\u0083\u0082"+
		"\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086"+
		"$\3\2\2\2\u0087\u0089\5\61\31\2\u0088\u0087\3\2\2\2\u0089\u008a\3\2\2"+
		"\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e"+
		"\7\60\2\2\u008d\u008f\5\61\31\2\u008e\u008d\3\2\2\2\u008f\u0090\3\2\2"+
		"\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091&\3\2\2\2\u0092\u0097"+
		"\7$\2\2\u0093\u0096\5-\27\2\u0094\u0096\5/\30\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0094\3\2\2\2\u0096\u0099\3\2\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2"+
		"\2\2\u0098\u009a\3\2\2\2\u0099\u0097\3\2\2\2\u009a\u009b\7$\2\2\u009b"+
		"(\3\2\2\2\u009c\u00a0\t\2\2\2\u009d\u009f\t\3\2\2\u009e\u009d\3\2\2\2"+
		"\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1*\3"+
		"\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\7&\2\2\u00a4\u00a5\7}\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a7\5)\25\2\u00a7\u00a8\7\177\2\2\u00a8,\3\2\2"+
		"\2\u00a9\u00aa\n\4\2\2\u00aa.\3\2\2\2\u00ab\u00ac\7^\2\2\u00ac\u00ad\t"+
		"\5\2\2\u00ad\60\3\2\2\2\u00ae\u00af\t\6\2\2\u00af\62\3\2\2\2\u00b0\u00b1"+
		"\t\7\2\2\u00b1\64\3\2\2\2\u00b2\u00b3\7\61\2\2\u00b3\u00b4\7,\2\2\u00b4"+
		"\u00b8\3\2\2\2\u00b5\u00b7\13\2\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba\3"+
		"\2\2\2\u00b8\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00bc\7,\2\2\u00bc\u00bd\7\61\2\2\u00bd\u00be\3\2"+
		"\2\2\u00be\u00bf\b\33\2\2\u00bf\66\3\2\2\2\u00c0\u00c1\7\61\2\2\u00c1"+
		"\u00c2\7\61\2\2\u00c2\u00c6\3\2\2\2\u00c3\u00c5\n\b\2\2\u00c4\u00c3\3"+
		"\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\b\34\2\2\u00ca8\3\2\2\2"+
		"\u00cb\u00cd\t\t\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc"+
		"\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\b\35\2\2"+
		"\u00d1:\3\2\2\2\16\2x}\u0085\u008a\u0090\u0095\u0097\u00a0\u00b8\u00c6"+
		"\u00ce\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}