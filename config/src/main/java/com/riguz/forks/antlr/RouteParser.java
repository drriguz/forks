// Generated from Route.g4 by ANTLR 4.7.1
 package com.riguz.forks.antlr.router; 
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RouteParser extends Parser {
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
	public static final int
		RULE_routeConfig = 0, RULE_controllers = 1, RULE_filters = 2, RULE_classNames = 3, 
		RULE_packageName = 4, RULE_className = 5, RULE_applyFilters = 6, RULE_routes = 7, 
		RULE_route = 8, RULE_methods = 9, RULE_paths = 10, RULE_pattern = 11, 
		RULE_functionCall = 12, RULE_params = 13, RULE_pathParamTypes = 14;
	public static final String[] ruleNames = {
		"routeConfig", "controllers", "filters", "classNames", "packageName", 
		"className", "applyFilters", "routes", "route", "methods", "paths", "pattern", 
		"functionCall", "params", "pathParamTypes"
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

	@Override
	public String getGrammarFileName() { return "Route.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RouteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RouteConfigContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(RouteParser.EOF, 0); }
		public List<ControllersContext> controllers() {
			return getRuleContexts(ControllersContext.class);
		}
		public ControllersContext controllers(int i) {
			return getRuleContext(ControllersContext.class,i);
		}
		public List<FiltersContext> filters() {
			return getRuleContexts(FiltersContext.class);
		}
		public FiltersContext filters(int i) {
			return getRuleContext(FiltersContext.class,i);
		}
		public List<RoutesContext> routes() {
			return getRuleContexts(RoutesContext.class);
		}
		public RoutesContext routes(int i) {
			return getRuleContext(RoutesContext.class,i);
		}
		public RouteConfigContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_routeConfig; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterRouteConfig(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitRouteConfig(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitRouteConfig(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RouteConfigContext routeConfig() throws RecognitionException {
		RouteConfigContext _localctx = new RouteConfigContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_routeConfig);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(30);
				controllers();
				}
				}
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CONTROLLERS );
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FILTERS) {
				{
				{
				setState(35);
				filters();
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(42); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(41);
				routes();
				}
				}
				setState(44); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ROUTES );
			setState(46);
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

	public static class ControllersContext extends ParserRuleContext {
		public TerminalNode CONTROLLERS() { return getToken(RouteParser.CONTROLLERS, 0); }
		public ClassNamesContext classNames() {
			return getRuleContext(ClassNamesContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(RouteParser.IDENTIFIER, 0); }
		public ControllersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controllers; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterControllers(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitControllers(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitControllers(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ControllersContext controllers() throws RecognitionException {
		ControllersContext _localctx = new ControllersContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_controllers);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(CONTROLLERS);
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(49);
				match(IDENTIFIER);
				}
			}

			setState(52);
			match(T__0);
			setState(53);
			classNames();
			setState(54);
			match(T__1);
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

	public static class FiltersContext extends ParserRuleContext {
		public TerminalNode FILTERS() { return getToken(RouteParser.FILTERS, 0); }
		public ClassNamesContext classNames() {
			return getRuleContext(ClassNamesContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(RouteParser.IDENTIFIER, 0); }
		public FiltersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterFilters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitFilters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitFilters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FiltersContext filters() throws RecognitionException {
		FiltersContext _localctx = new FiltersContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_filters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(FILTERS);
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(57);
				match(IDENTIFIER);
				}
			}

			setState(60);
			match(T__0);
			setState(61);
			classNames();
			setState(62);
			match(T__1);
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

	public static class ClassNamesContext extends ParserRuleContext {
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public List<ClassNameContext> className() {
			return getRuleContexts(ClassNameContext.class);
		}
		public ClassNameContext className(int i) {
			return getRuleContext(ClassNameContext.class,i);
		}
		public ClassNamesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classNames; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterClassNames(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitClassNames(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitClassNames(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassNamesContext classNames() throws RecognitionException {
		ClassNamesContext _localctx = new ClassNamesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_classNames);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			packageName();
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(65);
				className();
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
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

	public static class PackageNameContext extends ParserRuleContext {
		public TerminalNode PACKAGE() { return getToken(RouteParser.PACKAGE, 0); }
		public TerminalNode PACKAGE_NAME() { return getToken(RouteParser.PACKAGE_NAME, 0); }
		public PackageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterPackageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitPackageName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitPackageName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PackageNameContext packageName() throws RecognitionException {
		PackageNameContext _localctx = new PackageNameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_packageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(PACKAGE);
			setState(71);
			match(PACKAGE_NAME);
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

	public static class ClassNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(RouteParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RouteParser.IDENTIFIER, i);
		}
		public ClassNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_className; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterClassName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitClassName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitClassName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassNameContext className() throws RecognitionException {
		ClassNameContext _localctx = new ClassNameContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_className);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(IDENTIFIER);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(74);
				match(T__2);
				setState(75);
				match(IDENTIFIER);
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

	public static class ApplyFiltersContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(RouteParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RouteParser.IDENTIFIER, i);
		}
		public ApplyFiltersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_applyFilters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterApplyFilters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitApplyFilters(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitApplyFilters(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ApplyFiltersContext applyFilters() throws RecognitionException {
		ApplyFiltersContext _localctx = new ApplyFiltersContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_applyFilters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				match(T__3);
				setState(79);
				match(IDENTIFIER);
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
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

	public static class RoutesContext extends ParserRuleContext {
		public TerminalNode ROUTES() { return getToken(RouteParser.ROUTES, 0); }
		public TerminalNode IDENTIFIER() { return getToken(RouteParser.IDENTIFIER, 0); }
		public ApplyFiltersContext applyFilters() {
			return getRuleContext(ApplyFiltersContext.class,0);
		}
		public List<RouteContext> route() {
			return getRuleContexts(RouteContext.class);
		}
		public RouteContext route(int i) {
			return getRuleContext(RouteContext.class,i);
		}
		public RoutesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_routes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterRoutes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitRoutes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitRoutes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoutesContext routes() throws RecognitionException {
		RoutesContext _localctx = new RoutesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_routes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(ROUTES);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(85);
				match(IDENTIFIER);
				}
			}

			setState(88);
			match(T__0);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(89);
				applyFilters();
				}
			}

			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GET) | (1L << POST) | (1L << PUT) | (1L << DELETE) | (1L << PATCH))) != 0)) {
				{
				{
				setState(92);
				route();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98);
			match(T__1);
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

	public static class RouteContext extends ParserRuleContext {
		public MethodsContext methods() {
			return getRuleContext(MethodsContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public RouteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_route; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterRoute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitRoute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitRoute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RouteContext route() throws RecognitionException {
		RouteContext _localctx = new RouteContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_route);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			methods();
			setState(101);
			pattern();
			setState(102);
			functionCall();
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

	public static class MethodsContext extends ParserRuleContext {
		public TerminalNode GET() { return getToken(RouteParser.GET, 0); }
		public TerminalNode POST() { return getToken(RouteParser.POST, 0); }
		public TerminalNode PUT() { return getToken(RouteParser.PUT, 0); }
		public TerminalNode PATCH() { return getToken(RouteParser.PATCH, 0); }
		public TerminalNode DELETE() { return getToken(RouteParser.DELETE, 0); }
		public MethodsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methods; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterMethods(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitMethods(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitMethods(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MethodsContext methods() throws RecognitionException {
		MethodsContext _localctx = new MethodsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_methods);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GET) | (1L << POST) | (1L << PUT) | (1L << DELETE) | (1L << PATCH))) != 0)) ) {
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

	public static class PathsContext extends ParserRuleContext {
		public TerminalNode SUB_PATH() { return getToken(RouteParser.SUB_PATH, 0); }
		public TerminalNode NAMED_PATH() { return getToken(RouteParser.NAMED_PATH, 0); }
		public TerminalNode WILDCARD_PATH() { return getToken(RouteParser.WILDCARD_PATH, 0); }
		public PathsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paths; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterPaths(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitPaths(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitPaths(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathsContext paths() throws RecognitionException {
		PathsContext _localctx = new PathsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_paths);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB_PATH) | (1L << NAMED_PATH) | (1L << WILDCARD_PATH))) != 0)) ) {
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

	public static class PatternContext extends ParserRuleContext {
		public TerminalNode INDEX_PATH() { return getToken(RouteParser.INDEX_PATH, 0); }
		public List<PathsContext> paths() {
			return getRuleContexts(PathsContext.class);
		}
		public PathsContext paths(int i) {
			return getRuleContext(PathsContext.class,i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pattern);
		int _la;
		try {
			setState(114);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INDEX_PATH:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				match(INDEX_PATH);
				}
				break;
			case SUB_PATH:
			case NAMED_PATH:
			case WILDCARD_PATH:
				enterOuterAlt(_localctx, 2);
				{
				setState(110); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(109);
					paths();
					}
					}
					setState(112); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUB_PATH) | (1L << NAMED_PATH) | (1L << WILDCARD_PATH))) != 0) );
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

	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(RouteParser.FUNCTION, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(FUNCTION);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(117);
				params();
				}
			}

			setState(120);
			match(T__4);
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

	public static class ParamsContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(RouteParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(RouteParser.IDENTIFIER, i);
		}
		public List<PathParamTypesContext> pathParamTypes() {
			return getRuleContexts(PathParamTypesContext.class);
		}
		public PathParamTypesContext pathParamTypes(int i) {
			return getRuleContext(PathParamTypesContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitParams(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitParams(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(122);
			match(IDENTIFIER);
			setState(123);
			match(T__5);
			setState(124);
			pathParamTypes();
			}
			setState(132);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(126);
				match(T__6);
				setState(127);
				match(IDENTIFIER);
				setState(128);
				match(T__5);
				setState(129);
				pathParamTypes();
				}
				}
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class PathParamTypesContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(RouteParser.INT, 0); }
		public TerminalNode LONG() { return getToken(RouteParser.LONG, 0); }
		public TerminalNode STRING() { return getToken(RouteParser.STRING, 0); }
		public PathParamTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathParamTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).enterPathParamTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RouteListener ) ((RouteListener)listener).exitPathParamTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RouteVisitor ) return ((RouteVisitor<? extends T>)visitor).visitPathParamTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathParamTypesContext pathParamTypes() throws RecognitionException {
		PathParamTypesContext _localctx = new PathParamTypesContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_pathParamTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << LONG) | (1L << STRING))) != 0)) ) {
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\37\u008c\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\6\2\"\n\2\r\2"+
		"\16\2#\3\2\7\2\'\n\2\f\2\16\2*\13\2\3\2\6\2-\n\2\r\2\16\2.\3\2\3\2\3\3"+
		"\3\3\5\3\65\n\3\3\3\3\3\3\3\3\3\3\4\3\4\5\4=\n\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\6\5E\n\5\r\5\16\5F\3\6\3\6\3\6\3\7\3\7\3\7\5\7O\n\7\3\b\3\b\6\bS\n"+
		"\b\r\b\16\bT\3\t\3\t\5\tY\n\t\3\t\3\t\5\t]\n\t\3\t\7\t`\n\t\f\t\16\tc"+
		"\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\6\rq\n\r\r\r\16"+
		"\rr\5\ru\n\r\3\16\3\16\5\16y\n\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\7\17\u0085\n\17\f\17\16\17\u0088\13\17\3\20\3\20\3\20\2"+
		"\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\5\3\2\16\22\3\2\32\34\3"+
		"\2\23\25\2\u008b\2!\3\2\2\2\4\62\3\2\2\2\6:\3\2\2\2\bB\3\2\2\2\nH\3\2"+
		"\2\2\fK\3\2\2\2\16R\3\2\2\2\20V\3\2\2\2\22f\3\2\2\2\24j\3\2\2\2\26l\3"+
		"\2\2\2\30t\3\2\2\2\32v\3\2\2\2\34|\3\2\2\2\36\u0089\3\2\2\2 \"\5\4\3\2"+
		"! \3\2\2\2\"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$(\3\2\2\2%\'\5\6\4\2&%\3\2\2"+
		"\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2),\3\2\2\2*(\3\2\2\2+-\5\20\t\2,+\3\2"+
		"\2\2-.\3\2\2\2.,\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\2\2\3\61\3\3\2"+
		"\2\2\62\64\7\n\2\2\63\65\7\26\2\2\64\63\3\2\2\2\64\65\3\2\2\2\65\66\3"+
		"\2\2\2\66\67\7\3\2\2\678\5\b\5\289\7\4\2\29\5\3\2\2\2:<\7\13\2\2;=\7\26"+
		"\2\2<;\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\7\3\2\2?@\5\b\5\2@A\7\4\2\2A\7\3"+
		"\2\2\2BD\5\n\6\2CE\5\f\7\2DC\3\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\t"+
		"\3\2\2\2HI\7\r\2\2IJ\7\27\2\2J\13\3\2\2\2KN\7\26\2\2LM\7\5\2\2MO\7\26"+
		"\2\2NL\3\2\2\2NO\3\2\2\2O\r\3\2\2\2PQ\7\6\2\2QS\7\26\2\2RP\3\2\2\2ST\3"+
		"\2\2\2TR\3\2\2\2TU\3\2\2\2U\17\3\2\2\2VX\7\f\2\2WY\7\26\2\2XW\3\2\2\2"+
		"XY\3\2\2\2YZ\3\2\2\2Z\\\7\3\2\2[]\5\16\b\2\\[\3\2\2\2\\]\3\2\2\2]a\3\2"+
		"\2\2^`\5\22\n\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3"+
		"\2\2\2de\7\4\2\2e\21\3\2\2\2fg\5\24\13\2gh\5\30\r\2hi\5\32\16\2i\23\3"+
		"\2\2\2jk\t\2\2\2k\25\3\2\2\2lm\t\3\2\2m\27\3\2\2\2nu\7\31\2\2oq\5\26\f"+
		"\2po\3\2\2\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2su\3\2\2\2tn\3\2\2\2tp\3\2\2"+
		"\2u\31\3\2\2\2vx\7\30\2\2wy\5\34\17\2xw\3\2\2\2xy\3\2\2\2yz\3\2\2\2z{"+
		"\7\7\2\2{\33\3\2\2\2|}\7\26\2\2}~\7\b\2\2~\177\5\36\20\2\177\u0086\3\2"+
		"\2\2\u0080\u0081\7\t\2\2\u0081\u0082\7\26\2\2\u0082\u0083\7\b\2\2\u0083"+
		"\u0085\5\36\20\2\u0084\u0080\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0084\3"+
		"\2\2\2\u0086\u0087\3\2\2\2\u0087\35\3\2\2\2\u0088\u0086\3\2\2\2\u0089"+
		"\u008a\t\4\2\2\u008a\37\3\2\2\2\21#(.\64<FNTX\\artx\u0086";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}