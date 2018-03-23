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
		STRING=1, INT=2, BOOL=3, DECIMAL=4, SHARED=5, SCOPE=6, ASSIGN=7, LBRACE=8, 
		RBRACE=9, LBRACK=10, RBRACK=11, SEMI=12, COMMA=13, LINK=14, BOOL_LITERAL=15, 
		INT_LITERAL=16, HEX_LITERAL=17, DECIMAL_LITERAL=18, STRING_LITERAL=19, 
		NAME=20, REFERENCE=21, COMMENT=22, LINE_COMMENT=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"STRING", "INT", "BOOL", "DECIMAL", "SHARED", "SCOPE", "ASSIGN", "LBRACE", 
		"RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "LINK", "BOOL_LITERAL", 
		"INT_LITERAL", "HEX_LITERAL", "DECIMAL_LITERAL", "STRING_LITERAL", "NAME", 
		"REFERENCE", "Char", "EscapeSequence", "Digit", "HexDigit", "COMMENT", 
		"LINE_COMMENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'string'", "'int'", "'bool'", "'decimal'", "'shared'", "'scope'", 
		"'='", "'{'", "'}'", "'['", "']'", "';'", "','", "'..'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "INT", "BOOL", "DECIMAL", "SHARED", "SCOPE", "ASSIGN", 
		"LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "LINK", "BOOL_LITERAL", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00d4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20{\n\20\3\21\6\21~\n\21\r\21\16"+
		"\21\177\3\22\3\22\3\22\3\22\6\22\u0086\n\22\r\22\16\22\u0087\3\23\6\23"+
		"\u008b\n\23\r\23\16\23\u008c\3\23\3\23\6\23\u0091\n\23\r\23\16\23\u0092"+
		"\3\24\3\24\3\24\7\24\u0098\n\24\f\24\16\24\u009b\13\24\3\24\3\24\3\25"+
		"\3\25\7\25\u00a1\n\25\f\25\16\25\u00a4\13\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\7\33\u00b9\n\33\f\33\16\33\u00bc\13\33\3\33\3\33\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\7\34\u00c7\n\34\f\34\16\34\u00ca\13\34\3\34\3\34\3\35"+
		"\6\35\u00cf\n\35\r\35\16\35\u00d0\3\35\3\35\3\u00ba\2\36\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\2/\2\61\2\63\2\65\30\67\319\32\3\2\n\5\2C\\aac|\6\2"+
		"\62;C\\aac|\6\2\f\f\17\17$$^^\7\2$$^^ppttvv\3\2\62;\4\2\62;ch\4\2\f\f"+
		"\17\17\5\2\13\f\16\17\"\"\2\u00da\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2"+
		"\2\2+\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\3;\3\2\2\2\5B\3\2\2"+
		"\2\7F\3\2\2\2\tK\3\2\2\2\13S\3\2\2\2\rZ\3\2\2\2\17`\3\2\2\2\21b\3\2\2"+
		"\2\23d\3\2\2\2\25f\3\2\2\2\27h\3\2\2\2\31j\3\2\2\2\33l\3\2\2\2\35n\3\2"+
		"\2\2\37z\3\2\2\2!}\3\2\2\2#\u0081\3\2\2\2%\u008a\3\2\2\2\'\u0094\3\2\2"+
		"\2)\u009e\3\2\2\2+\u00a5\3\2\2\2-\u00ab\3\2\2\2/\u00ad\3\2\2\2\61\u00b0"+
		"\3\2\2\2\63\u00b2\3\2\2\2\65\u00b4\3\2\2\2\67\u00c2\3\2\2\29\u00ce\3\2"+
		"\2\2;<\7u\2\2<=\7v\2\2=>\7t\2\2>?\7k\2\2?@\7p\2\2@A\7i\2\2A\4\3\2\2\2"+
		"BC\7k\2\2CD\7p\2\2DE\7v\2\2E\6\3\2\2\2FG\7d\2\2GH\7q\2\2HI\7q\2\2IJ\7"+
		"n\2\2J\b\3\2\2\2KL\7f\2\2LM\7g\2\2MN\7e\2\2NO\7k\2\2OP\7o\2\2PQ\7c\2\2"+
		"QR\7n\2\2R\n\3\2\2\2ST\7u\2\2TU\7j\2\2UV\7c\2\2VW\7t\2\2WX\7g\2\2XY\7"+
		"f\2\2Y\f\3\2\2\2Z[\7u\2\2[\\\7e\2\2\\]\7q\2\2]^\7r\2\2^_\7g\2\2_\16\3"+
		"\2\2\2`a\7?\2\2a\20\3\2\2\2bc\7}\2\2c\22\3\2\2\2de\7\177\2\2e\24\3\2\2"+
		"\2fg\7]\2\2g\26\3\2\2\2hi\7_\2\2i\30\3\2\2\2jk\7=\2\2k\32\3\2\2\2lm\7"+
		".\2\2m\34\3\2\2\2no\7\60\2\2op\7\60\2\2p\36\3\2\2\2qr\7v\2\2rs\7t\2\2"+
		"st\7w\2\2t{\7g\2\2uv\7h\2\2vw\7c\2\2wx\7n\2\2xy\7u\2\2y{\7g\2\2zq\3\2"+
		"\2\2zu\3\2\2\2{ \3\2\2\2|~\5\61\31\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2"+
		"\2\177\u0080\3\2\2\2\u0080\"\3\2\2\2\u0081\u0082\7\62\2\2\u0082\u0083"+
		"\7z\2\2\u0083\u0085\3\2\2\2\u0084\u0086\5\63\32\2\u0085\u0084\3\2\2\2"+
		"\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088$\3"+
		"\2\2\2\u0089\u008b\5\61\31\2\u008a\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c"+
		"\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090\7\60"+
		"\2\2\u008f\u0091\5\61\31\2\u0090\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0092\u0093\3\2\2\2\u0093&\3\2\2\2\u0094\u0099\7$\2\2\u0095"+
		"\u0098\5-\27\2\u0096\u0098\5/\30\2\u0097\u0095\3\2\2\2\u0097\u0096\3\2"+
		"\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c\u009d\7$\2\2\u009d(\3\2\2\2\u009e"+
		"\u00a2\t\2\2\2\u009f\u00a1\t\3\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a4\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3*\3\2\2\2\u00a4\u00a2"+
		"\3\2\2\2\u00a5\u00a6\7&\2\2\u00a6\u00a7\7}\2\2\u00a7\u00a8\3\2\2\2\u00a8"+
		"\u00a9\5)\25\2\u00a9\u00aa\7\177\2\2\u00aa,\3\2\2\2\u00ab\u00ac\n\4\2"+
		"\2\u00ac.\3\2\2\2\u00ad\u00ae\7^\2\2\u00ae\u00af\t\5\2\2\u00af\60\3\2"+
		"\2\2\u00b0\u00b1\t\6\2\2\u00b1\62\3\2\2\2\u00b2\u00b3\t\7\2\2\u00b3\64"+
		"\3\2\2\2\u00b4\u00b5\7\61\2\2\u00b5\u00b6\7,\2\2\u00b6\u00ba\3\2\2\2\u00b7"+
		"\u00b9\13\2\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00bb\3"+
		"\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd"+
		"\u00be\7,\2\2\u00be\u00bf\7\61\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\b\33"+
		"\2\2\u00c1\66\3\2\2\2\u00c2\u00c3\7\61\2\2\u00c3\u00c4\7\61\2\2\u00c4"+
		"\u00c8\3\2\2\2\u00c5\u00c7\n\b\2\2\u00c6\u00c5\3\2\2\2\u00c7\u00ca\3\2"+
		"\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cb\3\2\2\2\u00ca"+
		"\u00c8\3\2\2\2\u00cb\u00cc\b\34\2\2\u00cc8\3\2\2\2\u00cd\u00cf\t\t\2\2"+
		"\u00ce\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1"+
		"\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\b\35\2\2\u00d3:\3\2\2\2\16\2"+
		"z\177\u0087\u008c\u0092\u0097\u0099\u00a2\u00ba\u00c8\u00d0\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}