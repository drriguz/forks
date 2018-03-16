// Generated from XLexer.g4 by ANTLR 4.7.1
 package com.riguz.forks; 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STRING=1, INT=2, BOOL=3, DECIMAL=4, MAP=5, NAME=6, REFERENCE=7, ASSIGN=8, 
		LBRACE=9, RBRACE=10, LBRACK=11, RBRACK=12, SEMI=13, COMMA=14, BOOL_LITERAL=15, 
		INT_LITERAL=16, HEX_LITERAL=17, DECIMAL_LITERAL=18, STRING_LITERAL=19, 
		COMMENT=20, LINE_COMMENT=21, WS=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"STRING", "INT", "BOOL", "DECIMAL", "MAP", "NAME", "REFERENCE", "ASSIGN", 
		"LBRACE", "RBRACE", "LBRACK", "RBRACK", "SEMI", "COMMA", "BOOL_LITERAL", 
		"INT_LITERAL", "HEX_LITERAL", "DECIMAL_LITERAL", "STRING_LITERAL", "Char", 
		"EscapeSequence", "Digit", "HexDigit", "COMMENT", "LINE_COMMENT", "WS"
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


	public XLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00c3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\7\7V\n\7\f\7\16\7Y\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\5\20x\n\20\3\21\6\21{\n\21\r\21\16\21|\3\22\3\22"+
		"\3\22\3\22\6\22\u0083\n\22\r\22\16\22\u0084\3\23\6\23\u0088\n\23\r\23"+
		"\16\23\u0089\3\23\3\23\6\23\u008e\n\23\r\23\16\23\u008f\3\24\3\24\3\24"+
		"\6\24\u0095\n\24\r\24\16\24\u0096\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3"+
		"\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u00a8\n\31\f\31\16\31\u00ab"+
		"\13\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u00b6\n\32\f"+
		"\32\16\32\u00b9\13\32\3\32\3\32\3\33\6\33\u00be\n\33\r\33\16\33\u00bf"+
		"\3\33\3\33\3\u00a9\2\34\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\2+\2-\2/\2\61\26\63\27"+
		"\65\30\3\2\n\5\2C\\aac|\6\2\62;C\\aac|\6\2\f\f\17\17$$^^\7\2$$^^ppttv"+
		"v\3\2\62;\4\2\62;ch\4\2\f\f\17\17\5\2\13\f\16\17\"\"\2\u00c9\2\3\3\2\2"+
		"\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3"+
		"\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2"+
		"\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2"+
		"\2\2\2\'\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\5"+
		">\3\2\2\2\7B\3\2\2\2\tG\3\2\2\2\13O\3\2\2\2\rS\3\2\2\2\17Z\3\2\2\2\21"+
		"`\3\2\2\2\23b\3\2\2\2\25d\3\2\2\2\27f\3\2\2\2\31h\3\2\2\2\33j\3\2\2\2"+
		"\35l\3\2\2\2\37w\3\2\2\2!z\3\2\2\2#~\3\2\2\2%\u0087\3\2\2\2\'\u0091\3"+
		"\2\2\2)\u009a\3\2\2\2+\u009c\3\2\2\2-\u009f\3\2\2\2/\u00a1\3\2\2\2\61"+
		"\u00a3\3\2\2\2\63\u00b1\3\2\2\2\65\u00bd\3\2\2\2\678\7u\2\289\7v\2\29"+
		":\7t\2\2:;\7k\2\2;<\7p\2\2<=\7i\2\2=\4\3\2\2\2>?\7k\2\2?@\7p\2\2@A\7v"+
		"\2\2A\6\3\2\2\2BC\7d\2\2CD\7q\2\2DE\7q\2\2EF\7n\2\2F\b\3\2\2\2GH\7f\2"+
		"\2HI\7g\2\2IJ\7e\2\2JK\7k\2\2KL\7o\2\2LM\7c\2\2MN\7n\2\2N\n\3\2\2\2OP"+
		"\7o\2\2PQ\7c\2\2QR\7r\2\2R\f\3\2\2\2SW\t\2\2\2TV\t\3\2\2UT\3\2\2\2VY\3"+
		"\2\2\2WU\3\2\2\2WX\3\2\2\2X\16\3\2\2\2YW\3\2\2\2Z[\7&\2\2[\\\7}\2\2\\"+
		"]\3\2\2\2]^\5\r\7\2^_\7\177\2\2_\20\3\2\2\2`a\7?\2\2a\22\3\2\2\2bc\7}"+
		"\2\2c\24\3\2\2\2de\7\177\2\2e\26\3\2\2\2fg\7]\2\2g\30\3\2\2\2hi\7_\2\2"+
		"i\32\3\2\2\2jk\7=\2\2k\34\3\2\2\2lm\7.\2\2m\36\3\2\2\2no\7v\2\2op\7t\2"+
		"\2pq\7w\2\2qx\7g\2\2rs\7h\2\2st\7c\2\2tu\7n\2\2uv\7u\2\2vx\7g\2\2wn\3"+
		"\2\2\2wr\3\2\2\2x \3\2\2\2y{\5-\27\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3"+
		"\2\2\2}\"\3\2\2\2~\177\7\62\2\2\177\u0080\7z\2\2\u0080\u0082\3\2\2\2\u0081"+
		"\u0083\5/\30\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2"+
		"\2\2\u0084\u0085\3\2\2\2\u0085$\3\2\2\2\u0086\u0088\5-\27\2\u0087\u0086"+
		"\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u0087\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u008d\7\60\2\2\u008c\u008e\5-\27\2\u008d\u008c\3"+
		"\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"&\3\2\2\2\u0091\u0094\7$\2\2\u0092\u0095\5)\25\2\u0093\u0095\5+\26\2\u0094"+
		"\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\7$\2\2\u0099"+
		"(\3\2\2\2\u009a\u009b\n\4\2\2\u009b*\3\2\2\2\u009c\u009d\7^\2\2\u009d"+
		"\u009e\t\5\2\2\u009e,\3\2\2\2\u009f\u00a0\t\6\2\2\u00a0.\3\2\2\2\u00a1"+
		"\u00a2\t\7\2\2\u00a2\60\3\2\2\2\u00a3\u00a4\7\61\2\2\u00a4\u00a5\7,\2"+
		"\2\u00a5\u00a9\3\2\2\2\u00a6\u00a8\13\2\2\2\u00a7\u00a6\3\2\2\2\u00a8"+
		"\u00ab\3\2\2\2\u00a9\u00aa\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ac\3\2"+
		"\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ad\7,\2\2\u00ad\u00ae\7\61\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b0\b\31\2\2\u00b0\62\3\2\2\2\u00b1\u00b2\7\61"+
		"\2\2\u00b2\u00b3\7\61\2\2\u00b3\u00b7\3\2\2\2\u00b4\u00b6\n\b\2\2\u00b5"+
		"\u00b4\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2"+
		"\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bb\b\32\2\2\u00bb"+
		"\64\3\2\2\2\u00bc\u00be\t\t\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2\2"+
		"\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2"+
		"\b\33\2\2\u00c2\66\3\2\2\2\16\2Ww|\u0084\u0089\u008f\u0094\u0096\u00a9"+
		"\u00b7\u00bf\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}