// Generated from Route.g4 by ANTLR 4.7.1
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
public class RouteLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, CONTROLLERS=8, 
		FILTERS=9, ROUTES=10, PACKAGE=11, GET=12, POST=13, PUT=14, DELETE=15, 
		PATCH=16, INT=17, LONG=18, STRING=19, IDENTIFIER=20, PACKAGE_NAME=21, 
		FUNCTION=22, INDEX_PATH=23, SUB_PATH=24, NAMED_PATH=25, WILDCARD_PATH=26, 
		COMMENT=27, LINE_COMMENT=28, WS=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "CONTROLLERS", 
		"FILTERS", "ROUTES", "PACKAGE", "GET", "POST", "PUT", "DELETE", "PATCH", 
		"INT", "LONG", "STRING", "IDENTIFIER", "PACKAGE_NAME", "FUNCTION", "INDEX_PATH", 
		"SUB_PATH", "NAMED_PATH", "WILDCARD_PATH", "COMMENT", "LINE_COMMENT", 
		"WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "'}'", "'->'", "'+'", "')'", "':'", "','", "'controllers'", 
		"'filters'", "'routes'", "'package'", "'get'", "'post'", "'put'", "'delete'", 
		"'patch'", "'Integer'", "'Long'", "'String'", null, null, null, "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "CONTROLLERS", "FILTERS", 
		"ROUTES", "PACKAGE", "GET", "POST", "PUT", "DELETE", "PATCH", "INT", "LONG", 
		"STRING", "IDENTIFIER", "PACKAGE_NAME", "FUNCTION", "INDEX_PATH", "SUB_PATH", 
		"NAMED_PATH", "WILDCARD_PATH", "COMMENT", "LINE_COMMENT", "WS"
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


	public RouteLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Route.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u00e0\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\25\3\25\7\25\u00a0\n\25\f\25\16\25\u00a3\13\25\3\26\3\26\3\26\7\26\u00a8"+
		"\n\26\f\26\16\26\u00ab\13\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3"+
		"\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\7\34\u00c5\n\34\f\34\16\34\u00c8\13\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\35\3\35\3\35\3\35\7\35\u00d3\n\35\f\35\16\35\u00d6\13\35\3\35\3"+
		"\35\3\36\6\36\u00db\n\36\r\36\16\36\u00dc\3\36\3\36\3\u00c6\2\37\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37\3\2"+
		"\6\5\2C\\aac|\6\2\62;C\\aac|\4\2\f\f\17\17\5\2\13\f\17\17\"\"\2\u00e4"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\3=\3\2\2\2\5?\3\2\2\2\7A\3\2\2\2\tD\3\2\2\2\13F\3\2\2\2\rH\3\2"+
		"\2\2\17J\3\2\2\2\21L\3\2\2\2\23X\3\2\2\2\25`\3\2\2\2\27g\3\2\2\2\31o\3"+
		"\2\2\2\33s\3\2\2\2\35x\3\2\2\2\37|\3\2\2\2!\u0083\3\2\2\2#\u0089\3\2\2"+
		"\2%\u0091\3\2\2\2\'\u0096\3\2\2\2)\u009d\3\2\2\2+\u00a4\3\2\2\2-\u00ac"+
		"\3\2\2\2/\u00b1\3\2\2\2\61\u00b3\3\2\2\2\63\u00b6\3\2\2\2\65\u00bb\3\2"+
		"\2\2\67\u00c0\3\2\2\29\u00ce\3\2\2\2;\u00da\3\2\2\2=>\7}\2\2>\4\3\2\2"+
		"\2?@\7\177\2\2@\6\3\2\2\2AB\7/\2\2BC\7@\2\2C\b\3\2\2\2DE\7-\2\2E\n\3\2"+
		"\2\2FG\7+\2\2G\f\3\2\2\2HI\7<\2\2I\16\3\2\2\2JK\7.\2\2K\20\3\2\2\2LM\7"+
		"e\2\2MN\7q\2\2NO\7p\2\2OP\7v\2\2PQ\7t\2\2QR\7q\2\2RS\7n\2\2ST\7n\2\2T"+
		"U\7g\2\2UV\7t\2\2VW\7u\2\2W\22\3\2\2\2XY\7h\2\2YZ\7k\2\2Z[\7n\2\2[\\\7"+
		"v\2\2\\]\7g\2\2]^\7t\2\2^_\7u\2\2_\24\3\2\2\2`a\7t\2\2ab\7q\2\2bc\7w\2"+
		"\2cd\7v\2\2de\7g\2\2ef\7u\2\2f\26\3\2\2\2gh\7r\2\2hi\7c\2\2ij\7e\2\2j"+
		"k\7m\2\2kl\7c\2\2lm\7i\2\2mn\7g\2\2n\30\3\2\2\2op\7i\2\2pq\7g\2\2qr\7"+
		"v\2\2r\32\3\2\2\2st\7r\2\2tu\7q\2\2uv\7u\2\2vw\7v\2\2w\34\3\2\2\2xy\7"+
		"r\2\2yz\7w\2\2z{\7v\2\2{\36\3\2\2\2|}\7f\2\2}~\7g\2\2~\177\7n\2\2\177"+
		"\u0080\7g\2\2\u0080\u0081\7v\2\2\u0081\u0082\7g\2\2\u0082 \3\2\2\2\u0083"+
		"\u0084\7r\2\2\u0084\u0085\7c\2\2\u0085\u0086\7v\2\2\u0086\u0087\7e\2\2"+
		"\u0087\u0088\7j\2\2\u0088\"\3\2\2\2\u0089\u008a\7K\2\2\u008a\u008b\7p"+
		"\2\2\u008b\u008c\7v\2\2\u008c\u008d\7g\2\2\u008d\u008e\7i\2\2\u008e\u008f"+
		"\7g\2\2\u008f\u0090\7t\2\2\u0090$\3\2\2\2\u0091\u0092\7N\2\2\u0092\u0093"+
		"\7q\2\2\u0093\u0094\7p\2\2\u0094\u0095\7i\2\2\u0095&\3\2\2\2\u0096\u0097"+
		"\7U\2\2\u0097\u0098\7v\2\2\u0098\u0099\7t\2\2\u0099\u009a\7k\2\2\u009a"+
		"\u009b\7p\2\2\u009b\u009c\7i\2\2\u009c(\3\2\2\2\u009d\u00a1\t\2\2\2\u009e"+
		"\u00a0\t\3\2\2\u009f\u009e\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2"+
		"\2\2\u00a1\u00a2\3\2\2\2\u00a2*\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a9"+
		"\5)\25\2\u00a5\u00a6\7\60\2\2\u00a6\u00a8\5)\25\2\u00a7\u00a5\3\2\2\2"+
		"\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa,\3"+
		"\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ad\5)\25\2\u00ad\u00ae\7\60\2\2\u00ae"+
		"\u00af\5)\25\2\u00af\u00b0\7*\2\2\u00b0.\3\2\2\2\u00b1\u00b2\7\61\2\2"+
		"\u00b2\60\3\2\2\2\u00b3\u00b4\7\61\2\2\u00b4\u00b5\5)\25\2\u00b5\62\3"+
		"\2\2\2\u00b6\u00b7\7\61\2\2\u00b7\u00b8\7<\2\2\u00b8\u00b9\3\2\2\2\u00b9"+
		"\u00ba\5)\25\2\u00ba\64\3\2\2\2\u00bb\u00bc\7\61\2\2\u00bc\u00bd\7,\2"+
		"\2\u00bd\u00be\3\2\2\2\u00be\u00bf\5)\25\2\u00bf\66\3\2\2\2\u00c0\u00c1"+
		"\7\61\2\2\u00c1\u00c2\7,\2\2\u00c2\u00c6\3\2\2\2\u00c3\u00c5\13\2\2\2"+
		"\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c6\u00c4"+
		"\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\7,\2\2\u00ca"+
		"\u00cb\7\61\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\b\34\2\2\u00cd8\3\2\2"+
		"\2\u00ce\u00cf\7\61\2\2\u00cf\u00d0\7\61\2\2\u00d0\u00d4\3\2\2\2\u00d1"+
		"\u00d3\n\4\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7"+
		"\u00d8\b\35\2\2\u00d8:\3\2\2\2\u00d9\u00db\t\5\2\2\u00da\u00d9\3\2\2\2"+
		"\u00db\u00dc\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de"+
		"\3\2\2\2\u00de\u00df\b\36\2\2\u00df<\3\2\2\2\b\2\u00a1\u00a9\u00c6\u00d4"+
		"\u00dc\3\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}