// Generated from query.g4 by ANTLR 4.5

package supersql.parser;

import java.util.*;
import java.io.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class queryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, K_ABORT=21, K_ADD=22, K_ALL=23, K_AND=24, 
		K_AS=25, K_ASC=26, K_BETWEEN=27, K_BY=28, K_CASE=29, K_CAST=30, K_COLLATE=31, 
		K_CROSS=32, K_CURRENT_DATE=33, K_CURRENT_TIME=34, K_CURRENT_TIMESTAMP=35, 
		K_DESC=36, K_DISTINCT=37, K_ELSE=38, K_END=39, K_ESCAPE=40, K_EXCEPT=41, 
		K_EXISTS=42, K_FAIL=43, K_FULL=44, K_FROM=45, K_GLOB=46, K_GROUP=47, K_HAVING=48, 
		K_IF=49, K_IGNORE=50, K_IN=51, K_INDEXED=52, K_INNER=53, K_INTERSECT=54, 
		K_IS=55, K_ISNULL=56, K_JOIN=57, K_LEFT=58, K_LIKE=59, K_LIMIT=60, K_MATCH=61, 
		K_NATURAL=62, K_NO=63, K_NOT=64, K_NOTNULL=65, K_NULL=66, K_OFFSET=67, 
		K_ON=68, K_OR=69, K_ORDER=70, K_OUTER=71, K_RAISE=72, K_RECURSIVE=73, 
		K_REGEXP=74, K_RIGHT=75, K_ROLLBACK=76, K_SELECT=77, K_THEN=78, K_UNION=79, 
		K_USING=80, K_VALUES=81, K_WHEN=82, K_WHERE=83, K_WITH=84, K_GENERATE=85, 
		K_MAX=86, K_MIN=87, K_AVG=88, K_COUNT=89, K_SUM=90, C0=91, C1=92, C2=93, 
		C3=94, DOT=95, OPEN_PARENTHESE=96, CLOSE_PARENTHESE=97, OPEN_BRACKET=98, 
		CLOSE_BRACKET=99, OPEN_BRACE=100, CLOSE_BRACE=101, SEMICOLON=102, DECORATOR=103, 
		NUMERIC_LITERAL=104, BLOB_LITERAL=105, BIND_PARAMETER=106, IDENTIFIER=107, 
		STRING_LITERAL=108, MULTI_LINE_COMMENT=109, SINGLE_LINE_COMMENT=110, WS=111, 
		UNEXPECTED_CHAR=112;
	public static final int
		RULE_query = 0, RULE_root = 1, RULE_media = 2, RULE_operand = 3, RULE_attribute = 4, 
		RULE_join_string = 5, RULE_grouper = 6, RULE_composite_iterator = 7, RULE_exp = 8, 
		RULE_d_exp = 9, RULE_v_exp = 10, RULE_h_exp = 11, RULE_n_exp = 12, RULE_sorting = 13, 
		RULE_function = 14, RULE_sqlfunc = 15, RULE_aggregate = 16, RULE_if_then_else = 17, 
		RULE_arithmetics = 18, RULE_arith = 19, RULE_from_where = 20, RULE_error = 21, 
		RULE_sql_stmt_list = 22, RULE_sql_stmt = 23, RULE_factored_select_stmt = 24, 
		RULE_select_core = 25, RULE_where_clause = 26, RULE_result_column = 27, 
		RULE_table_or_subquery = 28, RULE_keyword = 29, RULE_select_stmt = 30, 
		RULE_select_or_values = 31, RULE_compound_operator = 32, RULE_join_clause = 33, 
		RULE_join_operator = 34, RULE_join_constraint = 35, RULE_common_table_expression = 36, 
		RULE_ordering_term = 37, RULE_expr = 38, RULE_literal_value = 39, RULE_unary_operator = 40, 
		RULE_name = 41, RULE_type_name = 42, RULE_function_name = 43, RULE_ag_function_name = 44, 
		RULE_ag_keyword = 45, RULE_collation_name = 46, RULE_database_name = 47, 
		RULE_table_name = 48, RULE_column_alias = 49, RULE_column_name = 50, RULE_table_alias = 51, 
		RULE_index_name = 52, RULE_any_name = 53, RULE_sl = 54, RULE_signed_number = 55, 
		RULE_raise_function = 56, RULE_error_message = 57;
	public static final String[] ruleNames = {
		"query", "root", "media", "operand", "attribute", "join_string", "grouper", 
		"composite_iterator", "exp", "d_exp", "v_exp", "h_exp", "n_exp", "sorting", 
		"function", "sqlfunc", "aggregate", "if_then_else", "arithmetics", "arith", 
		"from_where", "error", "sql_stmt_list", "sql_stmt", "factored_select_stmt", 
		"select_core", "where_clause", "result_column", "table_or_subquery", "keyword", 
		"select_stmt", "select_or_values", "compound_operator", "join_clause", 
		"join_operator", "join_constraint", "common_table_expression", "ordering_term", 
		"expr", "literal_value", "unary_operator", "name", "type_name", "function_name", 
		"ag_function_name", "ag_keyword", "collation_name", "database_name", "table_name", 
		"column_alias", "column_name", "table_alias", "index_name", "any_name", 
		"sl", "signed_number", "raise_function", "error_message"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'||'", "'$'", "'&'", "':'", "'*'", "'/'", "'+'", "'-'", "'<<'", 
		"'>>'", "'|'", "'<'", "'<='", "'>'", "'>='", "'='", "'=='", "'!='", "'<>'", 
		"'~'", null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, "'?'", 
		"'!'", "','", "'%'", "'.'", "'('", "')'", "'['", "']'", "'{'", "'}'", 
		"';'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, "K_ABORT", "K_ADD", 
		"K_ALL", "K_AND", "K_AS", "K_ASC", "K_BETWEEN", "K_BY", "K_CASE", "K_CAST", 
		"K_COLLATE", "K_CROSS", "K_CURRENT_DATE", "K_CURRENT_TIME", "K_CURRENT_TIMESTAMP", 
		"K_DESC", "K_DISTINCT", "K_ELSE", "K_END", "K_ESCAPE", "K_EXCEPT", "K_EXISTS", 
		"K_FAIL", "K_FULL", "K_FROM", "K_GLOB", "K_GROUP", "K_HAVING", "K_IF", 
		"K_IGNORE", "K_IN", "K_INDEXED", "K_INNER", "K_INTERSECT", "K_IS", "K_ISNULL", 
		"K_JOIN", "K_LEFT", "K_LIKE", "K_LIMIT", "K_MATCH", "K_NATURAL", "K_NO", 
		"K_NOT", "K_NOTNULL", "K_NULL", "K_OFFSET", "K_ON", "K_OR", "K_ORDER", 
		"K_OUTER", "K_RAISE", "K_RECURSIVE", "K_REGEXP", "K_RIGHT", "K_ROLLBACK", 
		"K_SELECT", "K_THEN", "K_UNION", "K_USING", "K_VALUES", "K_WHEN", "K_WHERE", 
		"K_WITH", "K_GENERATE", "K_MAX", "K_MIN", "K_AVG", "K_COUNT", "K_SUM", 
		"C0", "C1", "C2", "C3", "DOT", "OPEN_PARENTHESE", "CLOSE_PARENTHESE", 
		"OPEN_BRACKET", "CLOSE_BRACKET", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
		"DECORATOR", "NUMERIC_LITERAL", "BLOB_LITERAL", "BIND_PARAMETER", "IDENTIFIER", 
		"STRING_LITERAL", "MULTI_LINE_COMMENT", "SINGLE_LINE_COMMENT", "WS", "UNEXPECTED_CHAR"
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
	public String getGrammarFileName() { return "query.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public queryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class QueryContext extends ParserRuleContext {
		public MediaContext media() {
			return getRuleContext(MediaContext.class,0);
		}
		public RootContext root() {
			return getRuleContext(RootContext.class,0);
		}
		public From_whereContext from_where() {
			return getRuleContext(From_whereContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitQuery(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_query);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			media();
			setState(117);
			root();
			setState(119);
			_la = _input.LA(1);
			if (_la==K_FROM || ((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (K_SELECT - 77)) | (1L << (K_WITH - 77)) | (1L << (UNEXPECTED_CHAR - 77)))) != 0)) {
				{
				setState(118);
				from_where();
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

	public static class RootContext extends ParserRuleContext {
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode DECORATOR() { return getToken(queryParser.DECORATOR, 0); }
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(121);
				operand();
				}
				break;
			case 2:
				{
				setState(122);
				exp();
				}
				break;
			}
			setState(126);
			_la = _input.LA(1);
			if (_la==DECORATOR) {
				{
				setState(125);
				match(DECORATOR);
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

	public static class MediaContext extends ParserRuleContext {
		public TerminalNode K_GENERATE() { return getToken(queryParser.K_GENERATE, 0); }
		public TerminalNode IDENTIFIER() { return getToken(queryParser.IDENTIFIER, 0); }
		public MediaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_media; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterMedia(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitMedia(this);
		}
	}

	public final MediaContext media() throws RecognitionException {
		MediaContext _localctx = new MediaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_media);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(K_GENERATE);
			setState(129);
			match(IDENTIFIER);
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

	public static class OperandContext extends ParserRuleContext {
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public Join_stringContext join_string() {
			return getRuleContext(Join_stringContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public SqlfuncContext sqlfunc() {
			return getRuleContext(SqlfuncContext.class,0);
		}
		public TerminalNode OPEN_BRACE() { return getToken(queryParser.OPEN_BRACE, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(queryParser.CLOSE_BRACE, 0); }
		public GrouperContext grouper() {
			return getRuleContext(GrouperContext.class,0);
		}
		public Composite_iteratorContext composite_iterator() {
			return getRuleContext(Composite_iteratorContext.class,0);
		}
		public If_then_elseContext if_then_else() {
			return getRuleContext(If_then_elseContext.class,0);
		}
		public TerminalNode NUMERIC_LITERAL() { return getToken(queryParser.NUMERIC_LITERAL, 0); }
		public AggregateContext aggregate() {
			return getRuleContext(AggregateContext.class,0);
		}
		public ArithmeticsContext arithmetics() {
			return getRuleContext(ArithmeticsContext.class,0);
		}
		public SlContext sl() {
			return getRuleContext(SlContext.class,0);
		}
		public TerminalNode DECORATOR() { return getToken(queryParser.DECORATOR, 0); }
		public SortingContext sorting() {
			return getRuleContext(SortingContext.class,0);
		}
		public OperandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operand; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterOperand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitOperand(this);
		}
	}

	public final OperandContext operand() throws RecognitionException {
		OperandContext _localctx = new OperandContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_operand);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(132);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(131);
					sorting();
					}
				}

				setState(134);
				attribute();
				}
				break;
			case 2:
				{
				setState(136);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(135);
					sorting();
					}
					break;
				}
				setState(138);
				join_string();
				}
				break;
			case 3:
				{
				setState(139);
				function();
				}
				break;
			case 4:
				{
				setState(140);
				sqlfunc();
				}
				break;
			case 5:
				{
				setState(141);
				match(OPEN_BRACE);
				setState(142);
				exp();
				setState(143);
				match(CLOSE_BRACE);
				}
				break;
			case 6:
				{
				setState(145);
				grouper();
				}
				break;
			case 7:
				{
				setState(146);
				composite_iterator();
				}
				break;
			case 8:
				{
				setState(147);
				if_then_else();
				}
				break;
			case 9:
				{
				setState(148);
				match(NUMERIC_LITERAL);
				}
				break;
			case 10:
				{
				setState(150);
				_la = _input.LA(1);
				if (_la==OPEN_PARENTHESE) {
					{
					setState(149);
					sorting();
					}
				}

				setState(152);
				aggregate();
				}
				break;
			case 11:
				{
				setState(153);
				arithmetics(0);
				}
				break;
			case 12:
				{
				setState(154);
				sl();
				}
				break;
			}
			setState(158);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(157);
				match(DECORATOR);
				}
				break;
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

	public static class AttributeContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(160);
				table_alias();
				setState(161);
				match(DOT);
				}
				break;
			}
			setState(165);
			column_name();
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

	public static class Join_stringContext extends ParserRuleContext {
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<TerminalNode> NUMERIC_LITERAL() { return getTokens(queryParser.NUMERIC_LITERAL); }
		public TerminalNode NUMERIC_LITERAL(int i) {
			return getToken(queryParser.NUMERIC_LITERAL, i);
		}
		public List<ArithmeticsContext> arithmetics() {
			return getRuleContexts(ArithmeticsContext.class);
		}
		public ArithmeticsContext arithmetics(int i) {
			return getRuleContext(ArithmeticsContext.class,i);
		}
		public List<SlContext> sl() {
			return getRuleContexts(SlContext.class);
		}
		public SlContext sl(int i) {
			return getRuleContext(SlContext.class,i);
		}
		public Join_stringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterJoin_string(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitJoin_string(this);
		}
	}

	public final Join_stringContext join_string() throws RecognitionException {
		Join_stringContext _localctx = new Join_stringContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_join_string);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(171);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(167);
				attribute();
				}
				break;
			case 2:
				{
				setState(168);
				match(NUMERIC_LITERAL);
				}
				break;
			case 3:
				{
				setState(169);
				arithmetics(0);
				}
				break;
			case 4:
				{
				setState(170);
				sl();
				}
				break;
			}
			setState(180); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(173);
					match(T__0);
					setState(178);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						setState(174);
						attribute();
						}
						break;
					case 2:
						{
						setState(175);
						match(NUMERIC_LITERAL);
						}
						break;
					case 3:
						{
						setState(176);
						arithmetics(0);
						}
						break;
					case 4:
						{
						setState(177);
						sl();
						}
						break;
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(182); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class GrouperContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(queryParser.OPEN_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(queryParser.CLOSE_BRACKET, 0); }
		public TerminalNode C1() { return getToken(queryParser.C1, 0); }
		public TerminalNode C2() { return getToken(queryParser.C2, 0); }
		public TerminalNode C3() { return getToken(queryParser.C3, 0); }
		public GrouperContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grouper; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterGrouper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitGrouper(this);
		}
	}

	public final GrouperContext grouper() throws RecognitionException {
		GrouperContext _localctx = new GrouperContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_grouper);
		try {
			setState(199);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(184);
				match(OPEN_BRACKET);
				setState(185);
				exp();
				setState(186);
				match(CLOSE_BRACKET);
				setState(187);
				match(C1);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				match(OPEN_BRACKET);
				setState(190);
				exp();
				setState(191);
				match(CLOSE_BRACKET);
				setState(192);
				match(C2);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(194);
				match(OPEN_BRACKET);
				setState(195);
				exp();
				setState(196);
				match(CLOSE_BRACKET);
				setState(197);
				match(C3);
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

	public static class Composite_iteratorContext extends ParserRuleContext {
		public TerminalNode OPEN_BRACKET() { return getToken(queryParser.OPEN_BRACKET, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(queryParser.CLOSE_BRACKET, 0); }
		public TerminalNode C1() { return getToken(queryParser.C1, 0); }
		public List<TerminalNode> NUMERIC_LITERAL() { return getTokens(queryParser.NUMERIC_LITERAL); }
		public TerminalNode NUMERIC_LITERAL(int i) {
			return getToken(queryParser.NUMERIC_LITERAL, i);
		}
		public TerminalNode C3() { return getToken(queryParser.C3, 0); }
		public TerminalNode C2() { return getToken(queryParser.C2, 0); }
		public Composite_iteratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_composite_iterator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterComposite_iterator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitComposite_iterator(this);
		}
	}

	public final Composite_iteratorContext composite_iterator() throws RecognitionException {
		Composite_iteratorContext _localctx = new Composite_iteratorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_composite_iterator);
		try {
			setState(227);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(201);
				match(OPEN_BRACKET);
				setState(202);
				exp();
				setState(203);
				match(CLOSE_BRACKET);
				setState(204);
				match(C1);
				{
				setState(205);
				match(NUMERIC_LITERAL);
				setState(212);
				switch (_input.LA(1)) {
				case C3:
					{
					setState(206);
					match(C3);
					}
					break;
				case C2:
					{
					{
					setState(207);
					match(C2);
					setState(210);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						setState(208);
						match(NUMERIC_LITERAL);
						setState(209);
						match(C3);
						}
						break;
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(214);
				match(OPEN_BRACKET);
				setState(215);
				exp();
				setState(216);
				match(CLOSE_BRACKET);
				setState(217);
				match(C2);
				{
				setState(218);
				match(NUMERIC_LITERAL);
				setState(225);
				switch (_input.LA(1)) {
				case C3:
					{
					setState(219);
					match(C3);
					}
					break;
				case C1:
					{
					{
					setState(220);
					match(C1);
					setState(223);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						setState(221);
						match(NUMERIC_LITERAL);
						setState(222);
						match(C3);
						}
						break;
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
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

	public static class ExpContext extends ParserRuleContext {
		public D_expContext d_exp() {
			return getRuleContext(D_expContext.class,0);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_exp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			d_exp();
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

	public static class D_expContext extends ParserRuleContext {
		public List<V_expContext> v_exp() {
			return getRuleContexts(V_expContext.class);
		}
		public V_expContext v_exp(int i) {
			return getRuleContext(V_expContext.class,i);
		}
		public List<TerminalNode> C3() { return getTokens(queryParser.C3); }
		public TerminalNode C3(int i) {
			return getToken(queryParser.C3, i);
		}
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public D_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_d_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterD_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitD_exp(this);
		}
	}

	public final D_expContext d_exp() throws RecognitionException {
		D_expContext _localctx = new D_expContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_d_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			v_exp();
			setState(239);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(232);
					match(C3);
					setState(235);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
					case 1:
						{
						setState(233);
						v_exp();
						}
						break;
					case 2:
						{
						setState(234);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(241);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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

	public static class V_expContext extends ParserRuleContext {
		public List<H_expContext> h_exp() {
			return getRuleContexts(H_expContext.class);
		}
		public H_expContext h_exp(int i) {
			return getRuleContext(H_expContext.class,i);
		}
		public List<TerminalNode> C2() { return getTokens(queryParser.C2); }
		public TerminalNode C2(int i) {
			return getToken(queryParser.C2, i);
		}
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public V_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_v_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterV_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitV_exp(this);
		}
	}

	public final V_expContext v_exp() throws RecognitionException {
		V_expContext _localctx = new V_expContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_v_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			h_exp();
			setState(250);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(243);
					match(C2);
					setState(246);
					switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
					case 1:
						{
						setState(244);
						h_exp();
						}
						break;
					case 2:
						{
						setState(245);
						operand();
						}
						break;
					}
					}
					} 
				}
				setState(252);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
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

	public static class H_expContext extends ParserRuleContext {
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public List<N_expContext> n_exp() {
			return getRuleContexts(N_expContext.class);
		}
		public N_expContext n_exp(int i) {
			return getRuleContext(N_expContext.class,i);
		}
		public List<TerminalNode> C1() { return getTokens(queryParser.C1); }
		public TerminalNode C1(int i) {
			return getToken(queryParser.C1, i);
		}
		public H_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_h_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterH_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitH_exp(this);
		}
	}

	public final H_expContext h_exp() throws RecognitionException {
		H_expContext _localctx = new H_expContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_h_exp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(253);
				operand();
				}
				break;
			case 2:
				{
				setState(254);
				n_exp();
				}
				break;
			}
			setState(264);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(257);
					match(C1);
					setState(260);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						setState(258);
						operand();
						}
						break;
					case 2:
						{
						setState(259);
						n_exp();
						}
						break;
					}
					}
					} 
				}
				setState(266);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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

	public static class N_expContext extends ParserRuleContext {
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public TerminalNode C0() { return getToken(queryParser.C0, 0); }
		public N_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_n_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterN_exp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitN_exp(this);
		}
	}

	public final N_expContext n_exp() throws RecognitionException {
		N_expContext _localctx = new N_expContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_n_exp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(267);
			operand();
			setState(268);
			match(C0);
			setState(269);
			operand();
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

	public static class SortingContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENTHESE() { return getToken(queryParser.OPEN_PARENTHESE, 0); }
		public TerminalNode K_ASC() { return getToken(queryParser.K_ASC, 0); }
		public TerminalNode CLOSE_PARENTHESE() { return getToken(queryParser.CLOSE_PARENTHESE, 0); }
		public TerminalNode K_DESC() { return getToken(queryParser.K_DESC, 0); }
		public SortingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sorting; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSorting(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSorting(this);
		}
	}

	public final SortingContext sorting() throws RecognitionException {
		SortingContext _localctx = new SortingContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_sorting);
		try {
			setState(277);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(271);
				match(OPEN_PARENTHESE);
				setState(272);
				match(K_ASC);
				setState(273);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				match(OPEN_PARENTHESE);
				setState(275);
				match(K_DESC);
				setState(276);
				match(CLOSE_PARENTHESE);
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

	public static class FunctionContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESE() { return getToken(queryParser.OPEN_PARENTHESE, 0); }
		public TerminalNode CLOSE_PARENTHESE() { return getToken(queryParser.CLOSE_PARENTHESE, 0); }
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(280);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(279);
				match(T__1);
				}
			}

			setState(282);
			function_name();
			setState(283);
			match(OPEN_PARENTHESE);
			{
			setState(286);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				{
				setState(284);
				operand();
				}
				break;
			case 2:
				{
				setState(285);
				exp();
				}
				break;
			}
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==C2) {
				{
				{
				{
				setState(288);
				match(C2);
				}
				setState(291);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(289);
					operand();
					}
					break;
				case 2:
					{
					setState(290);
					exp();
					}
					break;
				}
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
			setState(298);
			match(CLOSE_PARENTHESE);
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

	public static class SqlfuncContext extends ParserRuleContext {
		public Function_nameContext function_name() {
			return getRuleContext(Function_nameContext.class,0);
		}
		public TerminalNode OPEN_PARENTHESE() { return getToken(queryParser.OPEN_PARENTHESE, 0); }
		public TerminalNode CLOSE_PARENTHESE() { return getToken(queryParser.CLOSE_PARENTHESE, 0); }
		public List<OperandContext> operand() {
			return getRuleContexts(OperandContext.class);
		}
		public OperandContext operand(int i) {
			return getRuleContext(OperandContext.class,i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public SqlfuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlfunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSqlfunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSqlfunc(this);
		}
	}

	public final SqlfuncContext sqlfunc() throws RecognitionException {
		SqlfuncContext _localctx = new SqlfuncContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_sqlfunc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(300);
			match(T__2);
			setState(301);
			function_name();
			setState(302);
			match(OPEN_PARENTHESE);
			setState(319);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__2) | (1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (OPEN_PARENTHESE - 64)) | (1L << (OPEN_BRACKET - 64)) | (1L << (OPEN_BRACE - 64)) | (1L << (NUMERIC_LITERAL - 64)) | (1L << (IDENTIFIER - 64)) | (1L << (STRING_LITERAL - 64)))) != 0)) {
				{
				{
				setState(305);
				switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
				case 1:
					{
					setState(303);
					operand();
					}
					break;
				case 2:
					{
					setState(304);
					exp();
					}
					break;
				}
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(307);
					match(C2);
					setState(310);
					switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
					case 1:
						{
						setState(308);
						operand();
						}
						break;
					case 2:
						{
						setState(309);
						exp();
						}
						break;
					}
					}
					}
					setState(316);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(321);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(322);
			match(CLOSE_PARENTHESE);
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

	public static class AggregateContext extends ParserRuleContext {
		public Ag_function_nameContext ag_function_name() {
			return getRuleContext(Ag_function_nameContext.class,0);
		}
		public TerminalNode OPEN_BRACKET() { return getToken(queryParser.OPEN_BRACKET, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public TerminalNode CLOSE_BRACKET() { return getToken(queryParser.CLOSE_BRACKET, 0); }
		public AggregateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_aggregate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAggregate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAggregate(this);
		}
	}

	public final AggregateContext aggregate() throws RecognitionException {
		AggregateContext _localctx = new AggregateContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_aggregate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			ag_function_name();
			setState(325);
			match(OPEN_BRACKET);
			setState(326);
			attribute();
			setState(327);
			match(CLOSE_BRACKET);
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

	public static class If_then_elseContext extends ParserRuleContext {
		public TerminalNode K_IF() { return getToken(queryParser.K_IF, 0); }
		public List<TerminalNode> OPEN_PARENTHESE() { return getTokens(queryParser.OPEN_PARENTHESE); }
		public TerminalNode OPEN_PARENTHESE(int i) {
			return getToken(queryParser.OPEN_PARENTHESE, i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> CLOSE_PARENTHESE() { return getTokens(queryParser.CLOSE_PARENTHESE); }
		public TerminalNode CLOSE_PARENTHESE(int i) {
			return getToken(queryParser.CLOSE_PARENTHESE, i);
		}
		public TerminalNode K_THEN() { return getToken(queryParser.K_THEN, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode K_ELSE() { return getToken(queryParser.K_ELSE, 0); }
		public If_then_elseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_then_else; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterIf_then_else(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitIf_then_else(this);
		}
	}

	public final If_then_elseContext if_then_else() throws RecognitionException {
		If_then_elseContext _localctx = new If_then_elseContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_if_then_else);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			switch (_input.LA(1)) {
			case K_IF:
				{
				{
				setState(329);
				match(K_IF);
				setState(330);
				match(OPEN_PARENTHESE);
				setState(331);
				expr(0);
				setState(332);
				match(CLOSE_PARENTHESE);
				setState(333);
				match(K_THEN);
				setState(334);
				match(OPEN_PARENTHESE);
				setState(335);
				exp();
				setState(336);
				match(CLOSE_PARENTHESE);
				setState(337);
				match(K_ELSE);
				setState(338);
				match(OPEN_PARENTHESE);
				setState(339);
				exp();
				setState(340);
				match(CLOSE_PARENTHESE);
				}
				}
				break;
			case OPEN_PARENTHESE:
				{
				{
				setState(342);
				match(OPEN_PARENTHESE);
				setState(343);
				expr(0);
				setState(344);
				match(CLOSE_PARENTHESE);
				setState(345);
				match(C0);
				setState(346);
				exp();
				setState(347);
				match(T__3);
				setState(348);
				exp();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ArithmeticsContext extends ParserRuleContext {
		public TerminalNode OPEN_PARENTHESE() { return getToken(queryParser.OPEN_PARENTHESE, 0); }
		public List<ArithmeticsContext> arithmetics() {
			return getRuleContexts(ArithmeticsContext.class);
		}
		public ArithmeticsContext arithmetics(int i) {
			return getRuleContext(ArithmeticsContext.class,i);
		}
		public TerminalNode CLOSE_PARENTHESE() { return getToken(queryParser.CLOSE_PARENTHESE, 0); }
		public ArithContext arith() {
			return getRuleContext(ArithContext.class,0);
		}
		public ArithmeticsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetics; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterArithmetics(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitArithmetics(this);
		}
	}

	public final ArithmeticsContext arithmetics() throws RecognitionException {
		return arithmetics(0);
	}

	private ArithmeticsContext arithmetics(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ArithmeticsContext _localctx = new ArithmeticsContext(_ctx, _parentState);
		ArithmeticsContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_arithmetics, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			switch (_input.LA(1)) {
			case OPEN_PARENTHESE:
				{
				setState(353);
				match(OPEN_PARENTHESE);
				setState(354);
				arithmetics(0);
				setState(355);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0) || _la==C3) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(356);
				arithmetics(0);
				setState(357);
				match(CLOSE_PARENTHESE);
				}
				break;
			case K_ABORT:
			case K_ALL:
			case K_AND:
			case K_AS:
			case K_ASC:
			case K_BETWEEN:
			case K_BY:
			case K_CASE:
			case K_CAST:
			case K_COLLATE:
			case K_CROSS:
			case K_CURRENT_DATE:
			case K_CURRENT_TIME:
			case K_CURRENT_TIMESTAMP:
			case K_DESC:
			case K_DISTINCT:
			case K_ELSE:
			case K_END:
			case K_ESCAPE:
			case K_EXCEPT:
			case K_EXISTS:
			case K_FAIL:
			case K_FULL:
			case K_FROM:
			case K_GLOB:
			case K_GROUP:
			case K_HAVING:
			case K_IF:
			case K_IGNORE:
			case K_IN:
			case K_INDEXED:
			case K_INNER:
			case K_INTERSECT:
			case K_IS:
			case K_ISNULL:
			case K_JOIN:
			case K_LEFT:
			case K_LIKE:
			case K_LIMIT:
			case K_MATCH:
			case K_NATURAL:
			case K_NO:
			case K_NOT:
			case K_NOTNULL:
			case K_NULL:
			case K_OFFSET:
			case K_ON:
			case K_OR:
			case K_ORDER:
			case K_OUTER:
			case K_RAISE:
			case K_RECURSIVE:
			case K_REGEXP:
			case K_RIGHT:
			case K_ROLLBACK:
			case K_SELECT:
			case K_THEN:
			case K_UNION:
			case K_USING:
			case K_VALUES:
			case K_WHEN:
			case K_WHERE:
			case K_WITH:
			case K_GENERATE:
			case K_MAX:
			case K_MIN:
			case K_AVG:
			case K_COUNT:
			case K_SUM:
			case NUMERIC_LITERAL:
			case IDENTIFIER:
				{
				setState(359);
				arith();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(367);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ArithmeticsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_arithmetics);
					setState(362);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(363);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7))) != 0) || _la==C3) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(364);
					arithmetics(3);
					}
					} 
				}
				setState(369);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ArithContext extends ParserRuleContext {
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public TerminalNode NUMERIC_LITERAL() { return getToken(queryParser.NUMERIC_LITERAL, 0); }
		public ArithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arith; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterArith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitArith(this);
		}
	}

	public final ArithContext arith() throws RecognitionException {
		ArithContext _localctx = new ArithContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_arith);
		try {
			setState(372);
			switch (_input.LA(1)) {
			case K_ABORT:
			case K_ALL:
			case K_AND:
			case K_AS:
			case K_ASC:
			case K_BETWEEN:
			case K_BY:
			case K_CASE:
			case K_CAST:
			case K_COLLATE:
			case K_CROSS:
			case K_CURRENT_DATE:
			case K_CURRENT_TIME:
			case K_CURRENT_TIMESTAMP:
			case K_DESC:
			case K_DISTINCT:
			case K_ELSE:
			case K_END:
			case K_ESCAPE:
			case K_EXCEPT:
			case K_EXISTS:
			case K_FAIL:
			case K_FULL:
			case K_FROM:
			case K_GLOB:
			case K_GROUP:
			case K_HAVING:
			case K_IF:
			case K_IGNORE:
			case K_IN:
			case K_INDEXED:
			case K_INNER:
			case K_INTERSECT:
			case K_IS:
			case K_ISNULL:
			case K_JOIN:
			case K_LEFT:
			case K_LIKE:
			case K_LIMIT:
			case K_MATCH:
			case K_NATURAL:
			case K_NO:
			case K_NOT:
			case K_NOTNULL:
			case K_NULL:
			case K_OFFSET:
			case K_ON:
			case K_OR:
			case K_ORDER:
			case K_OUTER:
			case K_RAISE:
			case K_RECURSIVE:
			case K_REGEXP:
			case K_RIGHT:
			case K_ROLLBACK:
			case K_SELECT:
			case K_THEN:
			case K_UNION:
			case K_USING:
			case K_VALUES:
			case K_WHEN:
			case K_WHERE:
			case K_WITH:
			case K_GENERATE:
			case K_MAX:
			case K_MIN:
			case K_AVG:
			case K_COUNT:
			case K_SUM:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(370);
				attribute();
				}
				break;
			case NUMERIC_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(371);
				match(NUMERIC_LITERAL);
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

	public static class From_whereContext extends ParserRuleContext {
		public Sql_stmt_listContext sql_stmt_list() {
			return getRuleContext(Sql_stmt_listContext.class,0);
		}
		public ErrorContext error() {
			return getRuleContext(ErrorContext.class,0);
		}
		public From_whereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from_where; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterFrom_where(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitFrom_where(this);
		}
	}

	public final From_whereContext from_where() throws RecognitionException {
		From_whereContext _localctx = new From_whereContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_from_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			switch (_input.LA(1)) {
			case K_FROM:
			case K_SELECT:
			case K_WITH:
				{
				setState(374);
				sql_stmt_list();
				}
				break;
			case UNEXPECTED_CHAR:
				{
				setState(375);
				error();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ErrorContext extends ParserRuleContext {
		public Token UNEXPECTED_CHAR;
		public TerminalNode UNEXPECTED_CHAR() { return getToken(queryParser.UNEXPECTED_CHAR, 0); }
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitError(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			((ErrorContext)_localctx).UNEXPECTED_CHAR = match(UNEXPECTED_CHAR);
			 
			  
			  throw new RuntimeException("UNEXPECTED_CHAR=" + (((ErrorContext)_localctx).UNEXPECTED_CHAR!=null?((ErrorContext)_localctx).UNEXPECTED_CHAR.getText():null)); 
			  
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

	public static class Sql_stmt_listContext extends ParserRuleContext {
		public List<Sql_stmtContext> sql_stmt() {
			return getRuleContexts(Sql_stmtContext.class);
		}
		public Sql_stmtContext sql_stmt(int i) {
			return getRuleContext(Sql_stmtContext.class,i);
		}
		public Sql_stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSql_stmt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSql_stmt_list(this);
		}
	}

	public final Sql_stmt_listContext sql_stmt_list() throws RecognitionException {
		Sql_stmt_listContext _localctx = new Sql_stmt_listContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_sql_stmt_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			sql_stmt();
			setState(390);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(383); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(382);
						match(SEMICOLON);
						}
						}
						setState(385); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==SEMICOLON );
					setState(387);
					sql_stmt();
					}
					} 
				}
				setState(392);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			}
			setState(396);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(393);
				match(SEMICOLON);
				}
				}
				setState(398);
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

	public static class Sql_stmtContext extends ParserRuleContext {
		public Factored_select_stmtContext factored_select_stmt() {
			return getRuleContext(Factored_select_stmtContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Sql_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSql_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSql_stmt(this);
		}
	}

	public final Sql_stmtContext sql_stmt() throws RecognitionException {
		Sql_stmtContext _localctx = new Sql_stmtContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_sql_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(399);
				factored_select_stmt();
				}
				break;
			case 2:
				{
				setState(400);
				select_stmt();
				}
				break;
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

	public static class Factored_select_stmtContext extends ParserRuleContext {
		public List<Select_coreContext> select_core() {
			return getRuleContexts(Select_coreContext.class);
		}
		public Select_coreContext select_core(int i) {
			return getRuleContext(Select_coreContext.class,i);
		}
		public TerminalNode K_WITH() { return getToken(queryParser.K_WITH, 0); }
		public List<Common_table_expressionContext> common_table_expression() {
			return getRuleContexts(Common_table_expressionContext.class);
		}
		public Common_table_expressionContext common_table_expression(int i) {
			return getRuleContext(Common_table_expressionContext.class,i);
		}
		public List<Compound_operatorContext> compound_operator() {
			return getRuleContexts(Compound_operatorContext.class);
		}
		public Compound_operatorContext compound_operator(int i) {
			return getRuleContext(Compound_operatorContext.class,i);
		}
		public TerminalNode K_ORDER() { return getToken(queryParser.K_ORDER, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public List<Ordering_termContext> ordering_term() {
			return getRuleContexts(Ordering_termContext.class);
		}
		public Ordering_termContext ordering_term(int i) {
			return getRuleContext(Ordering_termContext.class,i);
		}
		public TerminalNode K_LIMIT() { return getToken(queryParser.K_LIMIT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_RECURSIVE() { return getToken(queryParser.K_RECURSIVE, 0); }
		public TerminalNode K_OFFSET() { return getToken(queryParser.K_OFFSET, 0); }
		public Factored_select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factored_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterFactored_select_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitFactored_select_stmt(this);
		}
	}

	public final Factored_select_stmtContext factored_select_stmt() throws RecognitionException {
		Factored_select_stmtContext _localctx = new Factored_select_stmtContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_factored_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(403);
				match(K_WITH);
				setState(405);
				switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(404);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(407);
				common_table_expression();
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(408);
					match(C2);
					setState(409);
					common_table_expression();
					}
					}
					setState(414);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(417);
			select_core();
			setState(423);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & ((1L << (K_EXCEPT - 41)) | (1L << (K_INTERSECT - 41)) | (1L << (K_UNION - 41)))) != 0)) {
				{
				{
				setState(418);
				compound_operator();
				setState(419);
				select_core();
				}
				}
				setState(425);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(436);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(426);
				match(K_ORDER);
				setState(427);
				match(K_BY);
				setState(428);
				ordering_term();
				setState(433);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(429);
					match(C2);
					setState(430);
					ordering_term();
					}
					}
					setState(435);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(444);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(438);
				match(K_LIMIT);
				setState(439);
				expr(0);
				setState(442);
				_la = _input.LA(1);
				if (_la==K_OFFSET || _la==C2) {
					{
					setState(440);
					_la = _input.LA(1);
					if ( !(_la==K_OFFSET || _la==C2) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(441);
					expr(0);
					}
				}

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

	public static class Select_coreContext extends ParserRuleContext {
		public TerminalNode K_FROM() { return getToken(queryParser.K_FROM, 0); }
		public TerminalNode K_SELECT() { return getToken(queryParser.K_SELECT, 0); }
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public TerminalNode K_DISTINCT() { return getToken(queryParser.K_DISTINCT, 0); }
		public TerminalNode K_ALL() { return getToken(queryParser.K_ALL, 0); }
		public Select_coreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_core; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSelect_core(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSelect_core(this);
		}
	}

	public final Select_coreContext select_core() throws RecognitionException {
		Select_coreContext _localctx = new Select_coreContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_select_core);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			_la = _input.LA(1);
			if (_la==K_SELECT) {
				{
				setState(446);
				match(K_SELECT);
				setState(448);
				switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(447);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(450);
				result_column();
				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(451);
					match(C2);
					setState(452);
					result_column();
					}
					}
					setState(457);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			{
			setState(460);
			match(K_FROM);
			setState(470);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(461);
				table_or_subquery();
				setState(466);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(462);
					match(C2);
					setState(463);
					table_or_subquery();
					}
					}
					setState(468);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				setState(469);
				join_clause();
				}
				break;
			}
			}
			setState(473);
			_la = _input.LA(1);
			if (((((_la - 47)) & ~0x3f) == 0 && ((1L << (_la - 47)) & ((1L << (K_GROUP - 47)) | (1L << (K_VALUES - 47)) | (1L << (K_WHERE - 47)))) != 0)) {
				{
				setState(472);
				where_clause();
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

	public static class Where_clauseContext extends ParserRuleContext {
		public TerminalNode K_VALUES() { return getToken(queryParser.K_VALUES, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_WHERE() { return getToken(queryParser.K_WHERE, 0); }
		public TerminalNode K_GROUP() { return getToken(queryParser.K_GROUP, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public TerminalNode K_HAVING() { return getToken(queryParser.K_HAVING, 0); }
		public Where_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterWhere_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitWhere_clause(this);
		}
	}

	public final Where_clauseContext where_clause() throws RecognitionException {
		Where_clauseContext _localctx = new Where_clauseContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_where_clause);
		int _la;
		try {
			setState(572);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(522);
				switch (_input.LA(1)) {
				case K_WHERE:
					{
					{
					setState(475);
					match(K_WHERE);
					setState(476);
					expr(0);
					}
					setState(492);
					_la = _input.LA(1);
					if (_la==K_GROUP) {
						{
						setState(478);
						match(K_GROUP);
						setState(479);
						match(K_BY);
						setState(480);
						expr(0);
						setState(485);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C2) {
							{
							{
							setState(481);
							match(C2);
							setState(482);
							expr(0);
							}
							}
							setState(487);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(490);
						_la = _input.LA(1);
						if (_la==K_HAVING) {
							{
							setState(488);
							match(K_HAVING);
							setState(489);
							expr(0);
							}
						}

						}
					}

					}
					break;
				case K_VALUES:
					{
					setState(494);
					match(K_VALUES);
					setState(495);
					match(OPEN_PARENTHESE);
					setState(496);
					expr(0);
					setState(501);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C2) {
						{
						{
						setState(497);
						match(C2);
						setState(498);
						expr(0);
						}
						}
						setState(503);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(504);
					match(CLOSE_PARENTHESE);
					setState(519);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C2) {
						{
						{
						setState(505);
						match(C2);
						setState(506);
						match(OPEN_PARENTHESE);
						setState(507);
						expr(0);
						setState(512);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C2) {
							{
							{
							setState(508);
							match(C2);
							setState(509);
							expr(0);
							}
							}
							setState(514);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(515);
						match(CLOSE_PARENTHESE);
						}
						}
						setState(521);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(570);
				switch (_input.LA(1)) {
				case K_GROUP:
				case K_WHERE:
					{
					setState(526);
					_la = _input.LA(1);
					if (_la==K_WHERE) {
						{
						setState(524);
						match(K_WHERE);
						setState(525);
						expr(0);
						}
					}

					{
					setState(528);
					match(K_GROUP);
					setState(529);
					match(K_BY);
					setState(530);
					expr(0);
					setState(535);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C2) {
						{
						{
						setState(531);
						match(C2);
						setState(532);
						expr(0);
						}
						}
						setState(537);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(540);
					_la = _input.LA(1);
					if (_la==K_HAVING) {
						{
						setState(538);
						match(K_HAVING);
						setState(539);
						expr(0);
						}
					}

					}
					}
					break;
				case K_VALUES:
					{
					setState(542);
					match(K_VALUES);
					setState(543);
					match(OPEN_PARENTHESE);
					setState(544);
					expr(0);
					setState(549);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C2) {
						{
						{
						setState(545);
						match(C2);
						setState(546);
						expr(0);
						}
						}
						setState(551);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(552);
					match(CLOSE_PARENTHESE);
					setState(567);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C2) {
						{
						{
						setState(553);
						match(C2);
						setState(554);
						match(OPEN_PARENTHESE);
						setState(555);
						expr(0);
						setState(560);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==C2) {
							{
							{
							setState(556);
							match(C2);
							setState(557);
							expr(0);
							}
							}
							setState(562);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(563);
						match(CLOSE_PARENTHESE);
						}
						}
						setState(569);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
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

	public static class Result_columnContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Column_aliasContext column_alias() {
			return getRuleContext(Column_aliasContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public Result_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_result_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterResult_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitResult_column(this);
		}
	}

	public final Result_columnContext result_column() throws RecognitionException {
		Result_columnContext _localctx = new Result_columnContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_result_column);
		int _la;
		try {
			setState(586);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(574);
				match(T__4);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(575);
				table_name();
				setState(576);
				match(DOT);
				setState(577);
				match(T__4);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(579);
				expr(0);
				setState(584);
				_la = _input.LA(1);
				if (_la==K_AS || _la==IDENTIFIER || _la==STRING_LITERAL) {
					{
					setState(581);
					_la = _input.LA(1);
					if (_la==K_AS) {
						{
						setState(580);
						match(K_AS);
						}
					}

					setState(583);
					column_alias();
					}
				}

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

	public static class Table_or_subqueryContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public TerminalNode K_INDEXED() { return getToken(queryParser.K_INDEXED, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public Index_nameContext index_name() {
			return getRuleContext(Index_nameContext.class,0);
		}
		public TerminalNode K_NOT() { return getToken(queryParser.K_NOT, 0); }
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Table_or_subqueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_or_subquery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterTable_or_subquery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitTable_or_subquery(this);
		}
	}

	public final Table_or_subqueryContext table_or_subquery() throws RecognitionException {
		Table_or_subqueryContext _localctx = new Table_or_subqueryContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_table_or_subquery);
		int _la;
		try {
			setState(635);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(591);
				switch ( getInterpreter().adaptivePredict(_input,75,_ctx) ) {
				case 1:
					{
					setState(588);
					database_name();
					setState(589);
					match(DOT);
					}
					break;
				}
				setState(593);
				table_name();
				setState(598);
				switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
				case 1:
					{
					setState(595);
					switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
					case 1:
						{
						setState(594);
						match(K_AS);
						}
						break;
					}
					setState(597);
					table_alias();
					}
					break;
				}
				setState(605);
				switch (_input.LA(1)) {
				case K_INDEXED:
					{
					setState(600);
					match(K_INDEXED);
					setState(601);
					match(K_BY);
					setState(602);
					index_name();
					}
					break;
				case K_NOT:
					{
					setState(603);
					match(K_NOT);
					setState(604);
					match(K_INDEXED);
					}
					break;
				case EOF:
				case K_CROSS:
				case K_EXCEPT:
				case K_FULL:
				case K_GROUP:
				case K_INNER:
				case K_INTERSECT:
				case K_JOIN:
				case K_LEFT:
				case K_LIMIT:
				case K_NATURAL:
				case K_ON:
				case K_ORDER:
				case K_RIGHT:
				case K_UNION:
				case K_USING:
				case K_VALUES:
				case K_WHERE:
				case C2:
				case CLOSE_PARENTHESE:
				case SEMICOLON:
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(607);
				match(OPEN_PARENTHESE);
				setState(617);
				switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
				case 1:
					{
					setState(608);
					table_or_subquery();
					setState(613);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==C2) {
						{
						{
						setState(609);
						match(C2);
						setState(610);
						table_or_subquery();
						}
						}
						setState(615);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				case 2:
					{
					setState(616);
					join_clause();
					}
					break;
				}
				setState(619);
				match(CLOSE_PARENTHESE);
				setState(624);
				switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
				case 1:
					{
					setState(621);
					switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
					case 1:
						{
						setState(620);
						match(K_AS);
						}
						break;
					}
					setState(623);
					table_alias();
					}
					break;
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(626);
				match(OPEN_PARENTHESE);
				setState(627);
				select_stmt();
				setState(628);
				match(CLOSE_PARENTHESE);
				setState(633);
				switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
				case 1:
					{
					setState(630);
					switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
					case 1:
						{
						setState(629);
						match(K_AS);
						}
						break;
					}
					setState(632);
					table_alias();
					}
					break;
				}
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

	public static class KeywordContext extends ParserRuleContext {
		public TerminalNode K_ABORT() { return getToken(queryParser.K_ABORT, 0); }
		public TerminalNode K_ALL() { return getToken(queryParser.K_ALL, 0); }
		public TerminalNode K_AND() { return getToken(queryParser.K_AND, 0); }
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public TerminalNode K_ASC() { return getToken(queryParser.K_ASC, 0); }
		public TerminalNode K_BETWEEN() { return getToken(queryParser.K_BETWEEN, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public TerminalNode K_CASE() { return getToken(queryParser.K_CASE, 0); }
		public TerminalNode K_CAST() { return getToken(queryParser.K_CAST, 0); }
		public TerminalNode K_COLLATE() { return getToken(queryParser.K_COLLATE, 0); }
		public TerminalNode K_CROSS() { return getToken(queryParser.K_CROSS, 0); }
		public TerminalNode K_CURRENT_DATE() { return getToken(queryParser.K_CURRENT_DATE, 0); }
		public TerminalNode K_CURRENT_TIME() { return getToken(queryParser.K_CURRENT_TIME, 0); }
		public TerminalNode K_CURRENT_TIMESTAMP() { return getToken(queryParser.K_CURRENT_TIMESTAMP, 0); }
		public TerminalNode K_DESC() { return getToken(queryParser.K_DESC, 0); }
		public TerminalNode K_DISTINCT() { return getToken(queryParser.K_DISTINCT, 0); }
		public TerminalNode K_ELSE() { return getToken(queryParser.K_ELSE, 0); }
		public TerminalNode K_END() { return getToken(queryParser.K_END, 0); }
		public TerminalNode K_ESCAPE() { return getToken(queryParser.K_ESCAPE, 0); }
		public TerminalNode K_EXCEPT() { return getToken(queryParser.K_EXCEPT, 0); }
		public TerminalNode K_EXISTS() { return getToken(queryParser.K_EXISTS, 0); }
		public TerminalNode K_FAIL() { return getToken(queryParser.K_FAIL, 0); }
		public TerminalNode K_FROM() { return getToken(queryParser.K_FROM, 0); }
		public TerminalNode K_FULL() { return getToken(queryParser.K_FULL, 0); }
		public TerminalNode K_GLOB() { return getToken(queryParser.K_GLOB, 0); }
		public TerminalNode K_GROUP() { return getToken(queryParser.K_GROUP, 0); }
		public TerminalNode K_HAVING() { return getToken(queryParser.K_HAVING, 0); }
		public TerminalNode K_IF() { return getToken(queryParser.K_IF, 0); }
		public TerminalNode K_IGNORE() { return getToken(queryParser.K_IGNORE, 0); }
		public TerminalNode K_IN() { return getToken(queryParser.K_IN, 0); }
		public TerminalNode K_INDEXED() { return getToken(queryParser.K_INDEXED, 0); }
		public TerminalNode K_INNER() { return getToken(queryParser.K_INNER, 0); }
		public TerminalNode K_INTERSECT() { return getToken(queryParser.K_INTERSECT, 0); }
		public TerminalNode K_IS() { return getToken(queryParser.K_IS, 0); }
		public TerminalNode K_ISNULL() { return getToken(queryParser.K_ISNULL, 0); }
		public TerminalNode K_JOIN() { return getToken(queryParser.K_JOIN, 0); }
		public TerminalNode K_LEFT() { return getToken(queryParser.K_LEFT, 0); }
		public TerminalNode K_LIKE() { return getToken(queryParser.K_LIKE, 0); }
		public TerminalNode K_LIMIT() { return getToken(queryParser.K_LIMIT, 0); }
		public TerminalNode K_MATCH() { return getToken(queryParser.K_MATCH, 0); }
		public TerminalNode K_NATURAL() { return getToken(queryParser.K_NATURAL, 0); }
		public TerminalNode K_NO() { return getToken(queryParser.K_NO, 0); }
		public TerminalNode K_NOT() { return getToken(queryParser.K_NOT, 0); }
		public TerminalNode K_NOTNULL() { return getToken(queryParser.K_NOTNULL, 0); }
		public TerminalNode K_NULL() { return getToken(queryParser.K_NULL, 0); }
		public TerminalNode K_OFFSET() { return getToken(queryParser.K_OFFSET, 0); }
		public TerminalNode K_ON() { return getToken(queryParser.K_ON, 0); }
		public TerminalNode K_OR() { return getToken(queryParser.K_OR, 0); }
		public TerminalNode K_ORDER() { return getToken(queryParser.K_ORDER, 0); }
		public TerminalNode K_OUTER() { return getToken(queryParser.K_OUTER, 0); }
		public TerminalNode K_RAISE() { return getToken(queryParser.K_RAISE, 0); }
		public TerminalNode K_RECURSIVE() { return getToken(queryParser.K_RECURSIVE, 0); }
		public TerminalNode K_REGEXP() { return getToken(queryParser.K_REGEXP, 0); }
		public TerminalNode K_RIGHT() { return getToken(queryParser.K_RIGHT, 0); }
		public TerminalNode K_ROLLBACK() { return getToken(queryParser.K_ROLLBACK, 0); }
		public TerminalNode K_SELECT() { return getToken(queryParser.K_SELECT, 0); }
		public TerminalNode K_THEN() { return getToken(queryParser.K_THEN, 0); }
		public TerminalNode K_UNION() { return getToken(queryParser.K_UNION, 0); }
		public TerminalNode K_USING() { return getToken(queryParser.K_USING, 0); }
		public TerminalNode K_VALUES() { return getToken(queryParser.K_VALUES, 0); }
		public TerminalNode K_WHEN() { return getToken(queryParser.K_WHEN, 0); }
		public TerminalNode K_WHERE() { return getToken(queryParser.K_WHERE, 0); }
		public TerminalNode K_WITH() { return getToken(queryParser.K_WITH, 0); }
		public TerminalNode K_GENERATE() { return getToken(queryParser.K_GENERATE, 0); }
		public TerminalNode K_MAX() { return getToken(queryParser.K_MAX, 0); }
		public TerminalNode K_MIN() { return getToken(queryParser.K_MIN, 0); }
		public TerminalNode K_AVG() { return getToken(queryParser.K_AVG, 0); }
		public TerminalNode K_SUM() { return getToken(queryParser.K_SUM, 0); }
		public TerminalNode K_COUNT() { return getToken(queryParser.K_COUNT, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitKeyword(this);
		}
	}

	public final KeywordContext keyword() throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(637);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class Select_stmtContext extends ParserRuleContext {
		public List<Select_or_valuesContext> select_or_values() {
			return getRuleContexts(Select_or_valuesContext.class);
		}
		public Select_or_valuesContext select_or_values(int i) {
			return getRuleContext(Select_or_valuesContext.class,i);
		}
		public TerminalNode K_WITH() { return getToken(queryParser.K_WITH, 0); }
		public List<Common_table_expressionContext> common_table_expression() {
			return getRuleContexts(Common_table_expressionContext.class);
		}
		public Common_table_expressionContext common_table_expression(int i) {
			return getRuleContext(Common_table_expressionContext.class,i);
		}
		public List<Compound_operatorContext> compound_operator() {
			return getRuleContexts(Compound_operatorContext.class);
		}
		public Compound_operatorContext compound_operator(int i) {
			return getRuleContext(Compound_operatorContext.class,i);
		}
		public TerminalNode K_ORDER() { return getToken(queryParser.K_ORDER, 0); }
		public TerminalNode K_BY() { return getToken(queryParser.K_BY, 0); }
		public List<Ordering_termContext> ordering_term() {
			return getRuleContexts(Ordering_termContext.class);
		}
		public Ordering_termContext ordering_term(int i) {
			return getRuleContext(Ordering_termContext.class,i);
		}
		public TerminalNode K_LIMIT() { return getToken(queryParser.K_LIMIT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode K_RECURSIVE() { return getToken(queryParser.K_RECURSIVE, 0); }
		public TerminalNode K_OFFSET() { return getToken(queryParser.K_OFFSET, 0); }
		public Select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSelect_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSelect_stmt(this);
		}
	}

	public final Select_stmtContext select_stmt() throws RecognitionException {
		Select_stmtContext _localctx = new Select_stmtContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(651);
			_la = _input.LA(1);
			if (_la==K_WITH) {
				{
				setState(639);
				match(K_WITH);
				setState(641);
				switch ( getInterpreter().adaptivePredict(_input,86,_ctx) ) {
				case 1:
					{
					setState(640);
					match(K_RECURSIVE);
					}
					break;
				}
				setState(643);
				common_table_expression();
				setState(648);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(644);
					match(C2);
					setState(645);
					common_table_expression();
					}
					}
					setState(650);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(653);
			select_or_values();
			setState(659);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & ((1L << (K_EXCEPT - 41)) | (1L << (K_INTERSECT - 41)) | (1L << (K_UNION - 41)))) != 0)) {
				{
				{
				setState(654);
				compound_operator();
				setState(655);
				select_or_values();
				}
				}
				setState(661);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(672);
			_la = _input.LA(1);
			if (_la==K_ORDER) {
				{
				setState(662);
				match(K_ORDER);
				setState(663);
				match(K_BY);
				setState(664);
				ordering_term();
				setState(669);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(665);
					match(C2);
					setState(666);
					ordering_term();
					}
					}
					setState(671);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(680);
			_la = _input.LA(1);
			if (_la==K_LIMIT) {
				{
				setState(674);
				match(K_LIMIT);
				setState(675);
				expr(0);
				setState(678);
				_la = _input.LA(1);
				if (_la==K_OFFSET || _la==C2) {
					{
					setState(676);
					_la = _input.LA(1);
					if ( !(_la==K_OFFSET || _la==C2) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(677);
					expr(0);
					}
				}

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

	public static class Select_or_valuesContext extends ParserRuleContext {
		public TerminalNode K_FROM() { return getToken(queryParser.K_FROM, 0); }
		public TerminalNode K_SELECT() { return getToken(queryParser.K_SELECT, 0); }
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public Where_clauseContext where_clause() {
			return getRuleContext(Where_clauseContext.class,0);
		}
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public Join_clauseContext join_clause() {
			return getRuleContext(Join_clauseContext.class,0);
		}
		public TerminalNode K_DISTINCT() { return getToken(queryParser.K_DISTINCT, 0); }
		public TerminalNode K_ALL() { return getToken(queryParser.K_ALL, 0); }
		public Select_or_valuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_or_values; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSelect_or_values(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSelect_or_values(this);
		}
	}

	public final Select_or_valuesContext select_or_values() throws RecognitionException {
		Select_or_valuesContext _localctx = new Select_or_valuesContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_select_or_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(694);
			_la = _input.LA(1);
			if (_la==K_SELECT) {
				{
				setState(682);
				match(K_SELECT);
				setState(684);
				switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
				case 1:
					{
					setState(683);
					_la = _input.LA(1);
					if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					break;
				}
				setState(686);
				result_column();
				setState(691);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(687);
					match(C2);
					setState(688);
					result_column();
					}
					}
					setState(693);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			{
			setState(696);
			match(K_FROM);
			setState(706);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
			case 1:
				{
				setState(697);
				table_or_subquery();
				setState(702);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(698);
					match(C2);
					setState(699);
					table_or_subquery();
					}
					}
					setState(704);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				{
				setState(705);
				join_clause();
				}
				break;
			}
			}
			setState(709);
			_la = _input.LA(1);
			if (((((_la - 47)) & ~0x3f) == 0 && ((1L << (_la - 47)) & ((1L << (K_GROUP - 47)) | (1L << (K_VALUES - 47)) | (1L << (K_WHERE - 47)))) != 0)) {
				{
				setState(708);
				where_clause();
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

	public static class Compound_operatorContext extends ParserRuleContext {
		public TerminalNode K_UNION() { return getToken(queryParser.K_UNION, 0); }
		public TerminalNode K_ALL() { return getToken(queryParser.K_ALL, 0); }
		public TerminalNode K_INTERSECT() { return getToken(queryParser.K_INTERSECT, 0); }
		public TerminalNode K_EXCEPT() { return getToken(queryParser.K_EXCEPT, 0); }
		public Compound_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compound_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterCompound_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitCompound_operator(this);
		}
	}

	public final Compound_operatorContext compound_operator() throws RecognitionException {
		Compound_operatorContext _localctx = new Compound_operatorContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_compound_operator);
		try {
			setState(716);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(711);
				match(K_UNION);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(712);
				match(K_UNION);
				setState(713);
				match(K_ALL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(714);
				match(K_INTERSECT);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(715);
				match(K_EXCEPT);
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

	public static class Join_clauseContext extends ParserRuleContext {
		public List<Table_or_subqueryContext> table_or_subquery() {
			return getRuleContexts(Table_or_subqueryContext.class);
		}
		public Table_or_subqueryContext table_or_subquery(int i) {
			return getRuleContext(Table_or_subqueryContext.class,i);
		}
		public List<Join_operatorContext> join_operator() {
			return getRuleContexts(Join_operatorContext.class);
		}
		public Join_operatorContext join_operator(int i) {
			return getRuleContext(Join_operatorContext.class,i);
		}
		public List<Join_constraintContext> join_constraint() {
			return getRuleContexts(Join_constraintContext.class);
		}
		public Join_constraintContext join_constraint(int i) {
			return getRuleContext(Join_constraintContext.class,i);
		}
		public List<Join_clauseContext> join_clause() {
			return getRuleContexts(Join_clauseContext.class);
		}
		public Join_clauseContext join_clause(int i) {
			return getRuleContext(Join_clauseContext.class,i);
		}
		public Join_clauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterJoin_clause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitJoin_clause(this);
		}
	}

	public final Join_clauseContext join_clause() throws RecognitionException {
		Join_clauseContext _localctx = new Join_clauseContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_join_clause);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(718);
			table_or_subquery();
			setState(728);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(719);
					join_operator();
					setState(722);
					switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
					case 1:
						{
						setState(720);
						join_clause();
						}
						break;
					case 2:
						{
						setState(721);
						table_or_subquery();
						}
						break;
					}
					setState(724);
					join_constraint();
					}
					} 
				}
				setState(730);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
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

	public static class Join_operatorContext extends ParserRuleContext {
		public TerminalNode K_JOIN() { return getToken(queryParser.K_JOIN, 0); }
		public TerminalNode K_NATURAL() { return getToken(queryParser.K_NATURAL, 0); }
		public TerminalNode K_LEFT() { return getToken(queryParser.K_LEFT, 0); }
		public TerminalNode K_RIGHT() { return getToken(queryParser.K_RIGHT, 0); }
		public TerminalNode K_FULL() { return getToken(queryParser.K_FULL, 0); }
		public TerminalNode K_INNER() { return getToken(queryParser.K_INNER, 0); }
		public TerminalNode K_CROSS() { return getToken(queryParser.K_CROSS, 0); }
		public TerminalNode K_OUTER() { return getToken(queryParser.K_OUTER, 0); }
		public Join_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterJoin_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitJoin_operator(this);
		}
	}

	public final Join_operatorContext join_operator() throws RecognitionException {
		Join_operatorContext _localctx = new Join_operatorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_join_operator);
		int _la;
		try {
			setState(752);
			switch (_input.LA(1)) {
			case C2:
				enterOuterAlt(_localctx, 1);
				{
				setState(731);
				match(C2);
				}
				break;
			case K_CROSS:
			case K_FULL:
			case K_INNER:
			case K_JOIN:
			case K_LEFT:
			case K_NATURAL:
			case K_RIGHT:
				enterOuterAlt(_localctx, 2);
				{
				setState(733);
				_la = _input.LA(1);
				if (_la==K_NATURAL) {
					{
					setState(732);
					match(K_NATURAL);
					}
				}

				setState(749);
				switch (_input.LA(1)) {
				case K_LEFT:
					{
					setState(735);
					match(K_LEFT);
					setState(737);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(736);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_RIGHT:
					{
					setState(739);
					match(K_RIGHT);
					setState(741);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(740);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_FULL:
					{
					setState(743);
					match(K_FULL);
					setState(745);
					_la = _input.LA(1);
					if (_la==K_OUTER) {
						{
						setState(744);
						match(K_OUTER);
						}
					}

					}
					break;
				case K_INNER:
					{
					setState(747);
					match(K_INNER);
					}
					break;
				case K_CROSS:
					{
					setState(748);
					match(K_CROSS);
					}
					break;
				case K_JOIN:
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(751);
				match(K_JOIN);
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

	public static class Join_constraintContext extends ParserRuleContext {
		public TerminalNode K_ON() { return getToken(queryParser.K_ON, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode K_USING() { return getToken(queryParser.K_USING, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Join_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterJoin_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitJoin_constraint(this);
		}
	}

	public final Join_constraintContext join_constraint() throws RecognitionException {
		Join_constraintContext _localctx = new Join_constraintContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_join_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(768);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				{
				setState(754);
				match(K_ON);
				setState(755);
				expr(0);
				}
				break;
			case 2:
				{
				setState(756);
				match(K_USING);
				setState(757);
				match(OPEN_PARENTHESE);
				setState(758);
				column_name();
				setState(763);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(759);
					match(C2);
					setState(760);
					column_name();
					}
					}
					setState(765);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(766);
				match(CLOSE_PARENTHESE);
				}
				break;
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

	public static class Common_table_expressionContext extends ParserRuleContext {
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Common_table_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_common_table_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterCommon_table_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitCommon_table_expression(this);
		}
	}

	public final Common_table_expressionContext common_table_expression() throws RecognitionException {
		Common_table_expressionContext _localctx = new Common_table_expressionContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_common_table_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(770);
			table_name();
			setState(782);
			_la = _input.LA(1);
			if (_la==OPEN_PARENTHESE) {
				{
				setState(771);
				match(OPEN_PARENTHESE);
				setState(772);
				column_name();
				setState(777);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==C2) {
					{
					{
					setState(773);
					match(C2);
					setState(774);
					column_name();
					}
					}
					setState(779);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(780);
				match(CLOSE_PARENTHESE);
				}
			}

			setState(784);
			match(K_AS);
			setState(785);
			match(OPEN_PARENTHESE);
			setState(786);
			select_stmt();
			setState(787);
			match(CLOSE_PARENTHESE);
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

	public static class Ordering_termContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode K_COLLATE() { return getToken(queryParser.K_COLLATE, 0); }
		public Collation_nameContext collation_name() {
			return getRuleContext(Collation_nameContext.class,0);
		}
		public TerminalNode K_ASC() { return getToken(queryParser.K_ASC, 0); }
		public TerminalNode K_DESC() { return getToken(queryParser.K_DESC, 0); }
		public Ordering_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ordering_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterOrdering_term(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitOrdering_term(this);
		}
	}

	public final Ordering_termContext ordering_term() throws RecognitionException {
		Ordering_termContext _localctx = new Ordering_termContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_ordering_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(789);
			expr(0);
			setState(792);
			_la = _input.LA(1);
			if (_la==K_COLLATE) {
				{
				setState(790);
				match(K_COLLATE);
				setState(791);
				collation_name();
				}
			}

			setState(795);
			_la = _input.LA(1);
			if (_la==K_ASC || _la==K_DESC) {
				{
				setState(794);
				_la = _input.LA(1);
				if ( !(_la==K_ASC || _la==K_DESC) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
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

	public static class ExprContext extends ParserRuleContext {
		public Unary_operatorContext unary_operator() {
			return getRuleContext(Unary_operatorContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OperandContext operand() {
			return getRuleContext(OperandContext.class,0);
		}
		public TerminalNode BIND_PARAMETER() { return getToken(queryParser.BIND_PARAMETER, 0); }
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_aliasContext table_alias() {
			return getRuleContext(Table_aliasContext.class,0);
		}
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public TerminalNode K_CAST() { return getToken(queryParser.K_CAST, 0); }
		public TerminalNode K_AS() { return getToken(queryParser.K_AS, 0); }
		public Type_nameContext type_name() {
			return getRuleContext(Type_nameContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public TerminalNode K_EXISTS() { return getToken(queryParser.K_EXISTS, 0); }
		public TerminalNode K_NOT() { return getToken(queryParser.K_NOT, 0); }
		public TerminalNode K_CASE() { return getToken(queryParser.K_CASE, 0); }
		public TerminalNode K_END() { return getToken(queryParser.K_END, 0); }
		public List<TerminalNode> K_WHEN() { return getTokens(queryParser.K_WHEN); }
		public TerminalNode K_WHEN(int i) {
			return getToken(queryParser.K_WHEN, i);
		}
		public List<TerminalNode> K_THEN() { return getTokens(queryParser.K_THEN); }
		public TerminalNode K_THEN(int i) {
			return getToken(queryParser.K_THEN, i);
		}
		public TerminalNode K_ELSE() { return getToken(queryParser.K_ELSE, 0); }
		public Raise_functionContext raise_function() {
			return getRuleContext(Raise_functionContext.class,0);
		}
		public TerminalNode K_IS() { return getToken(queryParser.K_IS, 0); }
		public TerminalNode K_IN() { return getToken(queryParser.K_IN, 0); }
		public TerminalNode K_LIKE() { return getToken(queryParser.K_LIKE, 0); }
		public TerminalNode K_GLOB() { return getToken(queryParser.K_GLOB, 0); }
		public TerminalNode K_MATCH() { return getToken(queryParser.K_MATCH, 0); }
		public TerminalNode K_REGEXP() { return getToken(queryParser.K_REGEXP, 0); }
		public TerminalNode K_AND() { return getToken(queryParser.K_AND, 0); }
		public TerminalNode K_OR() { return getToken(queryParser.K_OR, 0); }
		public TerminalNode K_BETWEEN() { return getToken(queryParser.K_BETWEEN, 0); }
		public TerminalNode K_COLLATE() { return getToken(queryParser.K_COLLATE, 0); }
		public Collation_nameContext collation_name() {
			return getRuleContext(Collation_nameContext.class,0);
		}
		public TerminalNode K_ESCAPE() { return getToken(queryParser.K_ESCAPE, 0); }
		public TerminalNode K_ISNULL() { return getToken(queryParser.K_ISNULL, 0); }
		public TerminalNode K_NOTNULL() { return getToken(queryParser.K_NOTNULL, 0); }
		public TerminalNode K_NULL() { return getToken(queryParser.K_NULL, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 76;
		enterRecursionRule(_localctx, 76, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(855);
			switch ( getInterpreter().adaptivePredict(_input,122,_ctx) ) {
			case 1:
				{
				setState(798);
				unary_operator();
				setState(799);
				expr(20);
				}
				break;
			case 2:
				{
				setState(801);
				operand();
				}
				break;
			case 3:
				{
				setState(802);
				match(BIND_PARAMETER);
				}
				break;
			case 4:
				{
				setState(811);
				switch ( getInterpreter().adaptivePredict(_input,116,_ctx) ) {
				case 1:
					{
					setState(806);
					switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
					case 1:
						{
						setState(803);
						database_name();
						setState(804);
						match(DOT);
						}
						break;
					}
					setState(808);
					table_alias();
					setState(809);
					match(DOT);
					}
					break;
				}
				setState(813);
				column_name();
				}
				break;
			case 5:
				{
				setState(814);
				match(OPEN_PARENTHESE);
				setState(815);
				expr(0);
				setState(816);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 6:
				{
				setState(818);
				match(K_CAST);
				setState(819);
				match(OPEN_PARENTHESE);
				setState(820);
				expr(0);
				setState(821);
				match(K_AS);
				setState(822);
				type_name();
				setState(823);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 7:
				{
				setState(829);
				_la = _input.LA(1);
				if (_la==K_EXISTS || _la==K_NOT) {
					{
					setState(826);
					_la = _input.LA(1);
					if (_la==K_NOT) {
						{
						setState(825);
						match(K_NOT);
						}
					}

					setState(828);
					match(K_EXISTS);
					}
				}

				setState(831);
				match(OPEN_PARENTHESE);
				setState(832);
				select_stmt();
				setState(833);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 8:
				{
				setState(835);
				match(K_CASE);
				setState(837);
				switch ( getInterpreter().adaptivePredict(_input,119,_ctx) ) {
				case 1:
					{
					setState(836);
					expr(0);
					}
					break;
				}
				setState(844); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(839);
					match(K_WHEN);
					setState(840);
					expr(0);
					setState(841);
					match(K_THEN);
					setState(842);
					expr(0);
					}
					}
					setState(846); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==K_WHEN );
				setState(850);
				_la = _input.LA(1);
				if (_la==K_ELSE) {
					{
					setState(848);
					match(K_ELSE);
					setState(849);
					expr(0);
					}
				}

				setState(852);
				match(K_END);
				}
				break;
			case 9:
				{
				setState(854);
				raise_function();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(957);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,135,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(955);
					switch ( getInterpreter().adaptivePredict(_input,134,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(857);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(858);
						match(T__0);
						setState(859);
						expr(20);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(860);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(861);
						_la = _input.LA(1);
						if ( !(_la==T__4 || _la==T__5 || _la==C3) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(862);
						expr(19);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(863);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(864);
						_la = _input.LA(1);
						if ( !(_la==T__6 || _la==T__7) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(865);
						expr(18);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(866);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(867);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__8) | (1L << T__9) | (1L << T__10))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(868);
						expr(17);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(869);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(870);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__11) | (1L << T__12) | (1L << T__13) | (1L << T__14))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(871);
						expr(16);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(872);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(885);
						switch ( getInterpreter().adaptivePredict(_input,123,_ctx) ) {
						case 1:
							{
							setState(873);
							match(T__15);
							}
							break;
						case 2:
							{
							setState(874);
							match(T__16);
							}
							break;
						case 3:
							{
							setState(875);
							match(T__17);
							}
							break;
						case 4:
							{
							setState(876);
							match(T__18);
							}
							break;
						case 5:
							{
							setState(877);
							match(K_IS);
							}
							break;
						case 6:
							{
							setState(878);
							match(K_IS);
							setState(879);
							match(K_NOT);
							}
							break;
						case 7:
							{
							setState(880);
							match(K_IN);
							}
							break;
						case 8:
							{
							setState(881);
							match(K_LIKE);
							}
							break;
						case 9:
							{
							setState(882);
							match(K_GLOB);
							}
							break;
						case 10:
							{
							setState(883);
							match(K_MATCH);
							}
							break;
						case 11:
							{
							setState(884);
							match(K_REGEXP);
							}
							break;
						}
						setState(887);
						expr(15);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(888);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(889);
						match(K_AND);
						setState(890);
						expr(14);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(891);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(892);
						match(K_OR);
						setState(893);
						expr(13);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(894);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(895);
						match(K_IS);
						setState(897);
						switch ( getInterpreter().adaptivePredict(_input,124,_ctx) ) {
						case 1:
							{
							setState(896);
							match(K_NOT);
							}
							break;
						}
						setState(899);
						expr(7);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(900);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(902);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(901);
							match(K_NOT);
							}
						}

						setState(904);
						match(K_BETWEEN);
						setState(905);
						expr(0);
						setState(906);
						match(K_AND);
						setState(907);
						expr(6);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(909);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(910);
						match(K_COLLATE);
						setState(911);
						collation_name();
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(912);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(914);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(913);
							match(K_NOT);
							}
						}

						setState(916);
						_la = _input.LA(1);
						if ( !(((((_la - 46)) & ~0x3f) == 0 && ((1L << (_la - 46)) & ((1L << (K_GLOB - 46)) | (1L << (K_LIKE - 46)) | (1L << (K_MATCH - 46)) | (1L << (K_REGEXP - 46)))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(917);
						expr(0);
						setState(920);
						switch ( getInterpreter().adaptivePredict(_input,127,_ctx) ) {
						case 1:
							{
							setState(918);
							match(K_ESCAPE);
							setState(919);
							expr(0);
							}
							break;
						}
						}
						break;
					case 13:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(922);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(927);
						switch (_input.LA(1)) {
						case K_ISNULL:
							{
							setState(923);
							match(K_ISNULL);
							}
							break;
						case K_NOTNULL:
							{
							setState(924);
							match(K_NOTNULL);
							}
							break;
						case K_NOT:
							{
							setState(925);
							match(K_NOT);
							setState(926);
							match(K_NULL);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					case 14:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(929);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(931);
						_la = _input.LA(1);
						if (_la==K_NOT) {
							{
							setState(930);
							match(K_NOT);
							}
						}

						setState(933);
						match(K_IN);
						setState(953);
						switch (_input.LA(1)) {
						case OPEN_PARENTHESE:
							{
							setState(934);
							match(OPEN_PARENTHESE);
							setState(944);
							switch ( getInterpreter().adaptivePredict(_input,131,_ctx) ) {
							case 1:
								{
								setState(935);
								select_stmt();
								}
								break;
							case 2:
								{
								setState(936);
								expr(0);
								setState(941);
								_errHandler.sync(this);
								_la = _input.LA(1);
								while (_la==C2) {
									{
									{
									setState(937);
									match(C2);
									setState(938);
									expr(0);
									}
									}
									setState(943);
									_errHandler.sync(this);
									_la = _input.LA(1);
								}
								}
								break;
							}
							setState(946);
							match(CLOSE_PARENTHESE);
							}
							break;
						case K_ABORT:
						case K_ALL:
						case K_AND:
						case K_AS:
						case K_ASC:
						case K_BETWEEN:
						case K_BY:
						case K_CASE:
						case K_CAST:
						case K_COLLATE:
						case K_CROSS:
						case K_CURRENT_DATE:
						case K_CURRENT_TIME:
						case K_CURRENT_TIMESTAMP:
						case K_DESC:
						case K_DISTINCT:
						case K_ELSE:
						case K_END:
						case K_ESCAPE:
						case K_EXCEPT:
						case K_EXISTS:
						case K_FAIL:
						case K_FULL:
						case K_FROM:
						case K_GLOB:
						case K_GROUP:
						case K_HAVING:
						case K_IF:
						case K_IGNORE:
						case K_IN:
						case K_INDEXED:
						case K_INNER:
						case K_INTERSECT:
						case K_IS:
						case K_ISNULL:
						case K_JOIN:
						case K_LEFT:
						case K_LIKE:
						case K_LIMIT:
						case K_MATCH:
						case K_NATURAL:
						case K_NO:
						case K_NOT:
						case K_NOTNULL:
						case K_NULL:
						case K_OFFSET:
						case K_ON:
						case K_OR:
						case K_ORDER:
						case K_OUTER:
						case K_RAISE:
						case K_RECURSIVE:
						case K_REGEXP:
						case K_RIGHT:
						case K_ROLLBACK:
						case K_SELECT:
						case K_THEN:
						case K_UNION:
						case K_USING:
						case K_VALUES:
						case K_WHEN:
						case K_WHERE:
						case K_WITH:
						case K_GENERATE:
						case K_MAX:
						case K_MIN:
						case K_AVG:
						case K_COUNT:
						case K_SUM:
						case IDENTIFIER:
							{
							setState(950);
							switch ( getInterpreter().adaptivePredict(_input,132,_ctx) ) {
							case 1:
								{
								setState(947);
								database_name();
								setState(948);
								match(DOT);
								}
								break;
							}
							setState(952);
							table_name();
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						}
						break;
					}
					} 
				}
				setState(959);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,135,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Literal_valueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(queryParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(queryParser.STRING_LITERAL, 0); }
		public TerminalNode BLOB_LITERAL() { return getToken(queryParser.BLOB_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(queryParser.K_NULL, 0); }
		public TerminalNode K_CURRENT_TIME() { return getToken(queryParser.K_CURRENT_TIME, 0); }
		public TerminalNode K_CURRENT_DATE() { return getToken(queryParser.K_CURRENT_DATE, 0); }
		public TerminalNode K_CURRENT_TIMESTAMP() { return getToken(queryParser.K_CURRENT_TIMESTAMP, 0); }
		public Literal_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterLiteral_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitLiteral_value(this);
		}
	}

	public final Literal_valueContext literal_value() throws RecognitionException {
		Literal_valueContext _localctx = new Literal_valueContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(960);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (K_NULL - 66)) | (1L << (NUMERIC_LITERAL - 66)) | (1L << (BLOB_LITERAL - 66)) | (1L << (STRING_LITERAL - 66)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class Unary_operatorContext extends ParserRuleContext {
		public TerminalNode K_NOT() { return getToken(queryParser.K_NOT, 0); }
		public Unary_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterUnary_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitUnary_operator(this);
		}
	}

	public final Unary_operatorContext unary_operator() throws RecognitionException {
		Unary_operatorContext _localctx = new Unary_operatorContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_unary_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(962);
			_la = _input.LA(1);
			if ( !(((((_la - 7)) & ~0x3f) == 0 && ((1L << (_la - 7)) & ((1L << (T__6 - 7)) | (1L << (T__7 - 7)) | (1L << (T__19 - 7)) | (1L << (K_NOT - 7)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class NameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitName(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(964);
			any_name();
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

	public static class Type_nameContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<Signed_numberContext> signed_number() {
			return getRuleContexts(Signed_numberContext.class);
		}
		public Signed_numberContext signed_number(int i) {
			return getRuleContext(Signed_numberContext.class,i);
		}
		public Type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterType_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitType_name(this);
		}
	}

	public final Type_nameContext type_name() throws RecognitionException {
		Type_nameContext _localctx = new Type_nameContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_type_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(967); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(966);
				name();
				}
				}
				setState(969); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_ABORT) | (1L << K_ALL) | (1L << K_AND) | (1L << K_AS) | (1L << K_ASC) | (1L << K_BETWEEN) | (1L << K_BY) | (1L << K_CASE) | (1L << K_CAST) | (1L << K_COLLATE) | (1L << K_CROSS) | (1L << K_CURRENT_DATE) | (1L << K_CURRENT_TIME) | (1L << K_CURRENT_TIMESTAMP) | (1L << K_DESC) | (1L << K_DISTINCT) | (1L << K_ELSE) | (1L << K_END) | (1L << K_ESCAPE) | (1L << K_EXCEPT) | (1L << K_EXISTS) | (1L << K_FAIL) | (1L << K_FULL) | (1L << K_FROM) | (1L << K_GLOB) | (1L << K_GROUP) | (1L << K_HAVING) | (1L << K_IF) | (1L << K_IGNORE) | (1L << K_IN) | (1L << K_INDEXED) | (1L << K_INNER) | (1L << K_INTERSECT) | (1L << K_IS) | (1L << K_ISNULL) | (1L << K_JOIN) | (1L << K_LEFT) | (1L << K_LIKE) | (1L << K_LIMIT) | (1L << K_MATCH) | (1L << K_NATURAL) | (1L << K_NO))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (K_NOT - 64)) | (1L << (K_NOTNULL - 64)) | (1L << (K_NULL - 64)) | (1L << (K_OFFSET - 64)) | (1L << (K_ON - 64)) | (1L << (K_OR - 64)) | (1L << (K_ORDER - 64)) | (1L << (K_OUTER - 64)) | (1L << (K_RAISE - 64)) | (1L << (K_RECURSIVE - 64)) | (1L << (K_REGEXP - 64)) | (1L << (K_RIGHT - 64)) | (1L << (K_ROLLBACK - 64)) | (1L << (K_SELECT - 64)) | (1L << (K_THEN - 64)) | (1L << (K_UNION - 64)) | (1L << (K_USING - 64)) | (1L << (K_VALUES - 64)) | (1L << (K_WHEN - 64)) | (1L << (K_WHERE - 64)) | (1L << (K_WITH - 64)) | (1L << (K_GENERATE - 64)) | (1L << (K_MAX - 64)) | (1L << (K_MIN - 64)) | (1L << (K_AVG - 64)) | (1L << (K_COUNT - 64)) | (1L << (K_SUM - 64)) | (1L << (IDENTIFIER - 64)))) != 0) );
			setState(981);
			switch ( getInterpreter().adaptivePredict(_input,137,_ctx) ) {
			case 1:
				{
				setState(971);
				match(OPEN_PARENTHESE);
				setState(972);
				signed_number();
				setState(973);
				match(CLOSE_PARENTHESE);
				}
				break;
			case 2:
				{
				setState(975);
				match(OPEN_PARENTHESE);
				setState(976);
				signed_number();
				setState(977);
				match(C2);
				setState(978);
				signed_number();
				setState(979);
				match(CLOSE_PARENTHESE);
				}
				break;
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

	public static class Function_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterFunction_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitFunction_name(this);
		}
	}

	public final Function_nameContext function_name() throws RecognitionException {
		Function_nameContext _localctx = new Function_nameContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(983);
			any_name();
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

	public static class Ag_function_nameContext extends ParserRuleContext {
		public Ag_keywordContext ag_keyword() {
			return getRuleContext(Ag_keywordContext.class,0);
		}
		public Ag_function_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ag_function_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAg_function_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAg_function_name(this);
		}
	}

	public final Ag_function_nameContext ag_function_name() throws RecognitionException {
		Ag_function_nameContext _localctx = new Ag_function_nameContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_ag_function_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(985);
			ag_keyword();
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

	public static class Ag_keywordContext extends ParserRuleContext {
		public TerminalNode K_MAX() { return getToken(queryParser.K_MAX, 0); }
		public TerminalNode K_MIN() { return getToken(queryParser.K_MIN, 0); }
		public TerminalNode K_SUM() { return getToken(queryParser.K_SUM, 0); }
		public TerminalNode K_AVG() { return getToken(queryParser.K_AVG, 0); }
		public TerminalNode K_COUNT() { return getToken(queryParser.K_COUNT, 0); }
		public Ag_keywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ag_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAg_keyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAg_keyword(this);
		}
	}

	public final Ag_keywordContext ag_keyword() throws RecognitionException {
		Ag_keywordContext _localctx = new Ag_keywordContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_ag_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(987);
			_la = _input.LA(1);
			if ( !(((((_la - 86)) & ~0x3f) == 0 && ((1L << (_la - 86)) & ((1L << (K_MAX - 86)) | (1L << (K_MIN - 86)) | (1L << (K_AVG - 86)) | (1L << (K_COUNT - 86)) | (1L << (K_SUM - 86)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class Collation_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Collation_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collation_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterCollation_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitCollation_name(this);
		}
	}

	public final Collation_nameContext collation_name() throws RecognitionException {
		Collation_nameContext _localctx = new Collation_nameContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_collation_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(989);
			any_name();
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

	public static class Database_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Database_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_database_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterDatabase_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitDatabase_name(this);
		}
	}

	public final Database_nameContext database_name() throws RecognitionException {
		Database_nameContext _localctx = new Database_nameContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_database_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(991);
			any_name();
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

	public static class Table_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitTable_name(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(993);
			any_name();
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

	public static class Column_aliasContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(queryParser.IDENTIFIER, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(queryParser.STRING_LITERAL, 0); }
		public Column_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterColumn_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitColumn_alias(this);
		}
	}

	public final Column_aliasContext column_alias() throws RecognitionException {
		Column_aliasContext _localctx = new Column_aliasContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_column_alias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(995);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==STRING_LITERAL) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class Column_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitColumn_name(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(997);
			any_name();
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

	public static class Table_aliasContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Table_aliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_alias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterTable_alias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitTable_alias(this);
		}
	}

	public final Table_aliasContext table_alias() throws RecognitionException {
		Table_aliasContext _localctx = new Table_aliasContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_table_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(999);
			any_name();
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

	public static class Index_nameContext extends ParserRuleContext {
		public Any_nameContext any_name() {
			return getRuleContext(Any_nameContext.class,0);
		}
		public Index_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterIndex_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitIndex_name(this);
		}
	}

	public final Index_nameContext index_name() throws RecognitionException {
		Index_nameContext _localctx = new Index_nameContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_index_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1001);
			any_name();
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

	public static class Any_nameContext extends ParserRuleContext {
		public KeywordContext keyword() {
			return getRuleContext(KeywordContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(queryParser.IDENTIFIER, 0); }
		public Any_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_any_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterAny_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitAny_name(this);
		}
	}

	public final Any_nameContext any_name() throws RecognitionException {
		Any_nameContext _localctx = new Any_nameContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_any_name);
		try {
			setState(1005);
			switch (_input.LA(1)) {
			case K_ABORT:
			case K_ALL:
			case K_AND:
			case K_AS:
			case K_ASC:
			case K_BETWEEN:
			case K_BY:
			case K_CASE:
			case K_CAST:
			case K_COLLATE:
			case K_CROSS:
			case K_CURRENT_DATE:
			case K_CURRENT_TIME:
			case K_CURRENT_TIMESTAMP:
			case K_DESC:
			case K_DISTINCT:
			case K_ELSE:
			case K_END:
			case K_ESCAPE:
			case K_EXCEPT:
			case K_EXISTS:
			case K_FAIL:
			case K_FULL:
			case K_FROM:
			case K_GLOB:
			case K_GROUP:
			case K_HAVING:
			case K_IF:
			case K_IGNORE:
			case K_IN:
			case K_INDEXED:
			case K_INNER:
			case K_INTERSECT:
			case K_IS:
			case K_ISNULL:
			case K_JOIN:
			case K_LEFT:
			case K_LIKE:
			case K_LIMIT:
			case K_MATCH:
			case K_NATURAL:
			case K_NO:
			case K_NOT:
			case K_NOTNULL:
			case K_NULL:
			case K_OFFSET:
			case K_ON:
			case K_OR:
			case K_ORDER:
			case K_OUTER:
			case K_RAISE:
			case K_RECURSIVE:
			case K_REGEXP:
			case K_RIGHT:
			case K_ROLLBACK:
			case K_SELECT:
			case K_THEN:
			case K_UNION:
			case K_USING:
			case K_VALUES:
			case K_WHEN:
			case K_WHERE:
			case K_WITH:
			case K_GENERATE:
			case K_MAX:
			case K_MIN:
			case K_AVG:
			case K_COUNT:
			case K_SUM:
				enterOuterAlt(_localctx, 1);
				{
				setState(1003);
				keyword();
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(1004);
				match(IDENTIFIER);
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

	public static class SlContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(queryParser.STRING_LITERAL, 0); }
		public SlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSl(this);
		}
	}

	public final SlContext sl() throws RecognitionException {
		SlContext _localctx = new SlContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_sl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1007);
			match(STRING_LITERAL);
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

	public static class Signed_numberContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(queryParser.NUMERIC_LITERAL, 0); }
		public Signed_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterSigned_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitSigned_number(this);
		}
	}

	public final Signed_numberContext signed_number() throws RecognitionException {
		Signed_numberContext _localctx = new Signed_numberContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_signed_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1010);
			_la = _input.LA(1);
			if (_la==T__6 || _la==T__7) {
				{
				setState(1009);
				_la = _input.LA(1);
				if ( !(_la==T__6 || _la==T__7) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(1012);
			match(NUMERIC_LITERAL);
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

	public static class Raise_functionContext extends ParserRuleContext {
		public TerminalNode K_RAISE() { return getToken(queryParser.K_RAISE, 0); }
		public TerminalNode K_IGNORE() { return getToken(queryParser.K_IGNORE, 0); }
		public Error_messageContext error_message() {
			return getRuleContext(Error_messageContext.class,0);
		}
		public TerminalNode K_ROLLBACK() { return getToken(queryParser.K_ROLLBACK, 0); }
		public TerminalNode K_ABORT() { return getToken(queryParser.K_ABORT, 0); }
		public TerminalNode K_FAIL() { return getToken(queryParser.K_FAIL, 0); }
		public Raise_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_raise_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterRaise_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitRaise_function(this);
		}
	}

	public final Raise_functionContext raise_function() throws RecognitionException {
		Raise_functionContext _localctx = new Raise_functionContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_raise_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1014);
			match(K_RAISE);
			setState(1015);
			match(OPEN_PARENTHESE);
			setState(1020);
			switch (_input.LA(1)) {
			case K_IGNORE:
				{
				setState(1016);
				match(K_IGNORE);
				}
				break;
			case K_ABORT:
			case K_FAIL:
			case K_ROLLBACK:
				{
				setState(1017);
				_la = _input.LA(1);
				if ( !(((((_la - 21)) & ~0x3f) == 0 && ((1L << (_la - 21)) & ((1L << (K_ABORT - 21)) | (1L << (K_FAIL - 21)) | (1L << (K_ROLLBACK - 21)))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(1018);
				match(C2);
				setState(1019);
				error_message();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1022);
			match(CLOSE_PARENTHESE);
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

	public static class Error_messageContext extends ParserRuleContext {
		public TerminalNode STRING_LITERAL() { return getToken(queryParser.STRING_LITERAL, 0); }
		public Error_messageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).enterError_message(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof queryListener ) ((queryListener)listener).exitError_message(this);
		}
	}

	public final Error_messageContext error_message() throws RecognitionException {
		Error_messageContext _localctx = new Error_messageContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_error_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1024);
			match(STRING_LITERAL);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 18:
			return arithmetics_sempred((ArithmeticsContext)_localctx, predIndex);
		case 38:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmetics_sempred(ArithmeticsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 19);
		case 2:
			return precpred(_ctx, 18);
		case 3:
			return precpred(_ctx, 17);
		case 4:
			return precpred(_ctx, 16);
		case 5:
			return precpred(_ctx, 15);
		case 6:
			return precpred(_ctx, 14);
		case 7:
			return precpred(_ctx, 13);
		case 8:
			return precpred(_ctx, 12);
		case 9:
			return precpred(_ctx, 6);
		case 10:
			return precpred(_ctx, 5);
		case 11:
			return precpred(_ctx, 9);
		case 12:
			return precpred(_ctx, 8);
		case 13:
			return precpred(_ctx, 7);
		case 14:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3r\u0405\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\3\2\3\2\3\2"+
		"\5\2z\n\2\3\3\3\3\5\3~\n\3\3\3\5\3\u0081\n\3\3\4\3\4\3\4\3\5\5\5\u0087"+
		"\n\5\3\5\3\5\5\5\u008b\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5\u0099\n\5\3\5\3\5\3\5\5\5\u009e\n\5\3\5\5\5\u00a1\n\5\3\6\3\6"+
		"\3\6\5\6\u00a6\n\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7\u00ae\n\7\3\7\3\7\3\7\3"+
		"\7\3\7\5\7\u00b5\n\7\6\7\u00b7\n\7\r\7\16\7\u00b8\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00ca\n\b\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\5\t\u00d5\n\t\5\t\u00d7\n\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\t\u00e2\n\t\5\t\u00e4\n\t\5\t\u00e6\n\t\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\5\13\u00ee\n\13\7\13\u00f0\n\13\f\13\16\13\u00f3\13\13"+
		"\3\f\3\f\3\f\3\f\5\f\u00f9\n\f\7\f\u00fb\n\f\f\f\16\f\u00fe\13\f\3\r\3"+
		"\r\5\r\u0102\n\r\3\r\3\r\3\r\5\r\u0107\n\r\7\r\u0109\n\r\f\r\16\r\u010c"+
		"\13\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0118\n\17"+
		"\3\20\5\20\u011b\n\20\3\20\3\20\3\20\3\20\5\20\u0121\n\20\3\20\3\20\3"+
		"\20\5\20\u0126\n\20\7\20\u0128\n\20\f\20\16\20\u012b\13\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\21\5\21\u0134\n\21\3\21\3\21\3\21\5\21\u0139\n\21"+
		"\7\21\u013b\n\21\f\21\16\21\u013e\13\21\7\21\u0140\n\21\f\21\16\21\u0143"+
		"\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\5\23\u0161\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u016b"+
		"\n\24\3\24\3\24\3\24\7\24\u0170\n\24\f\24\16\24\u0173\13\24\3\25\3\25"+
		"\5\25\u0177\n\25\3\26\3\26\5\26\u017b\n\26\3\27\3\27\3\27\3\30\3\30\6"+
		"\30\u0182\n\30\r\30\16\30\u0183\3\30\7\30\u0187\n\30\f\30\16\30\u018a"+
		"\13\30\3\30\7\30\u018d\n\30\f\30\16\30\u0190\13\30\3\31\3\31\5\31\u0194"+
		"\n\31\3\32\3\32\5\32\u0198\n\32\3\32\3\32\3\32\7\32\u019d\n\32\f\32\16"+
		"\32\u01a0\13\32\5\32\u01a2\n\32\3\32\3\32\3\32\3\32\7\32\u01a8\n\32\f"+
		"\32\16\32\u01ab\13\32\3\32\3\32\3\32\3\32\3\32\7\32\u01b2\n\32\f\32\16"+
		"\32\u01b5\13\32\5\32\u01b7\n\32\3\32\3\32\3\32\3\32\5\32\u01bd\n\32\5"+
		"\32\u01bf\n\32\3\33\3\33\5\33\u01c3\n\33\3\33\3\33\3\33\7\33\u01c8\n\33"+
		"\f\33\16\33\u01cb\13\33\5\33\u01cd\n\33\3\33\3\33\3\33\3\33\7\33\u01d3"+
		"\n\33\f\33\16\33\u01d6\13\33\3\33\5\33\u01d9\n\33\3\33\5\33\u01dc\n\33"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u01e6\n\34\f\34\16\34\u01e9"+
		"\13\34\3\34\3\34\5\34\u01ed\n\34\5\34\u01ef\n\34\3\34\3\34\3\34\3\34\3"+
		"\34\7\34\u01f6\n\34\f\34\16\34\u01f9\13\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\7\34\u0201\n\34\f\34\16\34\u0204\13\34\3\34\3\34\7\34\u0208\n\34\f"+
		"\34\16\34\u020b\13\34\5\34\u020d\n\34\3\34\3\34\5\34\u0211\n\34\3\34\3"+
		"\34\3\34\3\34\3\34\7\34\u0218\n\34\f\34\16\34\u021b\13\34\3\34\3\34\5"+
		"\34\u021f\n\34\3\34\3\34\3\34\3\34\3\34\7\34\u0226\n\34\f\34\16\34\u0229"+
		"\13\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u0231\n\34\f\34\16\34\u0234"+
		"\13\34\3\34\3\34\7\34\u0238\n\34\f\34\16\34\u023b\13\34\5\34\u023d\n\34"+
		"\5\34\u023f\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\5\35\u0248\n\35\3"+
		"\35\5\35\u024b\n\35\5\35\u024d\n\35\3\36\3\36\3\36\5\36\u0252\n\36\3\36"+
		"\3\36\5\36\u0256\n\36\3\36\5\36\u0259\n\36\3\36\3\36\3\36\3\36\3\36\5"+
		"\36\u0260\n\36\3\36\3\36\3\36\3\36\7\36\u0266\n\36\f\36\16\36\u0269\13"+
		"\36\3\36\5\36\u026c\n\36\3\36\3\36\5\36\u0270\n\36\3\36\5\36\u0273\n\36"+
		"\3\36\3\36\3\36\3\36\5\36\u0279\n\36\3\36\5\36\u027c\n\36\5\36\u027e\n"+
		"\36\3\37\3\37\3 \3 \5 \u0284\n \3 \3 \3 \7 \u0289\n \f \16 \u028c\13 "+
		"\5 \u028e\n \3 \3 \3 \3 \7 \u0294\n \f \16 \u0297\13 \3 \3 \3 \3 \3 \7"+
		" \u029e\n \f \16 \u02a1\13 \5 \u02a3\n \3 \3 \3 \3 \5 \u02a9\n \5 \u02ab"+
		"\n \3!\3!\5!\u02af\n!\3!\3!\3!\7!\u02b4\n!\f!\16!\u02b7\13!\5!\u02b9\n"+
		"!\3!\3!\3!\3!\7!\u02bf\n!\f!\16!\u02c2\13!\3!\5!\u02c5\n!\3!\5!\u02c8"+
		"\n!\3\"\3\"\3\"\3\"\3\"\5\"\u02cf\n\"\3#\3#\3#\3#\5#\u02d5\n#\3#\3#\7"+
		"#\u02d9\n#\f#\16#\u02dc\13#\3$\3$\5$\u02e0\n$\3$\3$\5$\u02e4\n$\3$\3$"+
		"\5$\u02e8\n$\3$\3$\5$\u02ec\n$\3$\3$\5$\u02f0\n$\3$\5$\u02f3\n$\3%\3%"+
		"\3%\3%\3%\3%\3%\7%\u02fc\n%\f%\16%\u02ff\13%\3%\3%\5%\u0303\n%\3&\3&\3"+
		"&\3&\3&\7&\u030a\n&\f&\16&\u030d\13&\3&\3&\5&\u0311\n&\3&\3&\3&\3&\3&"+
		"\3\'\3\'\3\'\5\'\u031b\n\'\3\'\5\'\u031e\n\'\3(\3(\3(\3(\3(\3(\3(\3(\3"+
		"(\5(\u0329\n(\3(\3(\3(\5(\u032e\n(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3"+
		"(\3(\5(\u033d\n(\3(\5(\u0340\n(\3(\3(\3(\3(\3(\3(\5(\u0348\n(\3(\3(\3"+
		"(\3(\3(\6(\u034f\n(\r(\16(\u0350\3(\3(\5(\u0355\n(\3(\3(\3(\5(\u035a\n"+
		"(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3"+
		"(\3(\3(\3(\3(\3(\5(\u0378\n(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\5(\u0384\n"+
		"(\3(\3(\3(\5(\u0389\n(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\5(\u0395\n(\3(\3"+
		"(\3(\3(\5(\u039b\n(\3(\3(\3(\3(\3(\5(\u03a2\n(\3(\3(\5(\u03a6\n(\3(\3"+
		"(\3(\3(\3(\3(\7(\u03ae\n(\f(\16(\u03b1\13(\5(\u03b3\n(\3(\3(\3(\3(\5("+
		"\u03b9\n(\3(\5(\u03bc\n(\7(\u03be\n(\f(\16(\u03c1\13(\3)\3)\3*\3*\3+\3"+
		"+\3,\6,\u03ca\n,\r,\16,\u03cb\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\5,\u03d8\n"+
		",\3-\3-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64"+
		"\3\65\3\65\3\66\3\66\3\67\3\67\5\67\u03f0\n\67\38\38\39\59\u03f5\n9\3"+
		"9\39\3:\3:\3:\3:\3:\3:\5:\u03ff\n:\3:\3:\3;\3;\3;\2\4&N<\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bd"+
		"fhjlnprt\2\21\4\2\7\n``\4\2EE__\4\2\31\31\'\'\4\2\27\27\31\\\4\2\34\34"+
		"&&\4\2\7\b``\3\2\t\n\4\2\5\5\13\r\3\2\16\21\6\2\60\60==??LL\6\2#%DDjk"+
		"nn\5\2\t\n\26\26BB\3\2X\\\3\2mn\5\2\27\27--NN\u048f\2v\3\2\2\2\4}\3\2"+
		"\2\2\6\u0082\3\2\2\2\b\u009d\3\2\2\2\n\u00a5\3\2\2\2\f\u00ad\3\2\2\2\16"+
		"\u00c9\3\2\2\2\20\u00e5\3\2\2\2\22\u00e7\3\2\2\2\24\u00e9\3\2\2\2\26\u00f4"+
		"\3\2\2\2\30\u0101\3\2\2\2\32\u010d\3\2\2\2\34\u0117\3\2\2\2\36\u011a\3"+
		"\2\2\2 \u012e\3\2\2\2\"\u0146\3\2\2\2$\u0160\3\2\2\2&\u016a\3\2\2\2(\u0176"+
		"\3\2\2\2*\u017a\3\2\2\2,\u017c\3\2\2\2.\u017f\3\2\2\2\60\u0193\3\2\2\2"+
		"\62\u01a1\3\2\2\2\64\u01cc\3\2\2\2\66\u023e\3\2\2\28\u024c\3\2\2\2:\u027d"+
		"\3\2\2\2<\u027f\3\2\2\2>\u028d\3\2\2\2@\u02b8\3\2\2\2B\u02ce\3\2\2\2D"+
		"\u02d0\3\2\2\2F\u02f2\3\2\2\2H\u0302\3\2\2\2J\u0304\3\2\2\2L\u0317\3\2"+
		"\2\2N\u0359\3\2\2\2P\u03c2\3\2\2\2R\u03c4\3\2\2\2T\u03c6\3\2\2\2V\u03c9"+
		"\3\2\2\2X\u03d9\3\2\2\2Z\u03db\3\2\2\2\\\u03dd\3\2\2\2^\u03df\3\2\2\2"+
		"`\u03e1\3\2\2\2b\u03e3\3\2\2\2d\u03e5\3\2\2\2f\u03e7\3\2\2\2h\u03e9\3"+
		"\2\2\2j\u03eb\3\2\2\2l\u03ef\3\2\2\2n\u03f1\3\2\2\2p\u03f4\3\2\2\2r\u03f8"+
		"\3\2\2\2t\u0402\3\2\2\2vw\5\6\4\2wy\5\4\3\2xz\5*\26\2yx\3\2\2\2yz\3\2"+
		"\2\2z\3\3\2\2\2{~\5\b\5\2|~\5\22\n\2}{\3\2\2\2}|\3\2\2\2~\u0080\3\2\2"+
		"\2\177\u0081\7i\2\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\5\3\2"+
		"\2\2\u0082\u0083\7W\2\2\u0083\u0084\7m\2\2\u0084\7\3\2\2\2\u0085\u0087"+
		"\5\34\17\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2"+
		"\u0088\u009e\5\n\6\2\u0089\u008b\5\34\17\2\u008a\u0089\3\2\2\2\u008a\u008b"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u009e\5\f\7\2\u008d\u009e\5\36\20\2"+
		"\u008e\u009e\5 \21\2\u008f\u0090\7f\2\2\u0090\u0091\5\22\n\2\u0091\u0092"+
		"\7g\2\2\u0092\u009e\3\2\2\2\u0093\u009e\5\16\b\2\u0094\u009e\5\20\t\2"+
		"\u0095\u009e\5$\23\2\u0096\u009e\7j\2\2\u0097\u0099\5\34\17\2\u0098\u0097"+
		"\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009e\5\"\22\2"+
		"\u009b\u009e\5&\24\2\u009c\u009e\5n8\2\u009d\u0086\3\2\2\2\u009d\u008a"+
		"\3\2\2\2\u009d\u008d\3\2\2\2\u009d\u008e\3\2\2\2\u009d\u008f\3\2\2\2\u009d"+
		"\u0093\3\2\2\2\u009d\u0094\3\2\2\2\u009d\u0095\3\2\2\2\u009d\u0096\3\2"+
		"\2\2\u009d\u0098\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009c\3\2\2\2\u009e"+
		"\u00a0\3\2\2\2\u009f\u00a1\7i\2\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\t\3\2\2\2\u00a2\u00a3\5h\65\2\u00a3\u00a4\7a\2\2\u00a4\u00a6"+
		"\3\2\2\2\u00a5\u00a2\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00a8\5f\64\2\u00a8\13\3\2\2\2\u00a9\u00ae\5\n\6\2\u00aa\u00ae\7j\2\2"+
		"\u00ab\u00ae\5&\24\2\u00ac\u00ae\5n8\2\u00ad\u00a9\3\2\2\2\u00ad\u00aa"+
		"\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b6\3\2\2\2\u00af"+
		"\u00b4\7\3\2\2\u00b0\u00b5\5\n\6\2\u00b1\u00b5\7j\2\2\u00b2\u00b5\5&\24"+
		"\2\u00b3\u00b5\5n8\2\u00b4\u00b0\3\2\2\2\u00b4\u00b1\3\2\2\2\u00b4\u00b2"+
		"\3\2\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00af\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\r\3\2\2\2"+
		"\u00ba\u00bb\7d\2\2\u00bb\u00bc\5\22\n\2\u00bc\u00bd\7e\2\2\u00bd\u00be"+
		"\7^\2\2\u00be\u00ca\3\2\2\2\u00bf\u00c0\7d\2\2\u00c0\u00c1\5\22\n\2\u00c1"+
		"\u00c2\7e\2\2\u00c2\u00c3\7_\2\2\u00c3\u00ca\3\2\2\2\u00c4\u00c5\7d\2"+
		"\2\u00c5\u00c6\5\22\n\2\u00c6\u00c7\7e\2\2\u00c7\u00c8\7`\2\2\u00c8\u00ca"+
		"\3\2\2\2\u00c9\u00ba\3\2\2\2\u00c9\u00bf\3\2\2\2\u00c9\u00c4\3\2\2\2\u00ca"+
		"\17\3\2\2\2\u00cb\u00cc\7d\2\2\u00cc\u00cd\5\22\n\2\u00cd\u00ce\7e\2\2"+
		"\u00ce\u00cf\7^\2\2\u00cf\u00d6\7j\2\2\u00d0\u00d7\7`\2\2\u00d1\u00d4"+
		"\7_\2\2\u00d2\u00d3\7j\2\2\u00d3\u00d5\7`\2\2\u00d4\u00d2\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d0\3\2\2\2\u00d6\u00d1\3\2"+
		"\2\2\u00d7\u00e6\3\2\2\2\u00d8\u00d9\7d\2\2\u00d9\u00da\5\22\n\2\u00da"+
		"\u00db\7e\2\2\u00db\u00dc\7_\2\2\u00dc\u00e3\7j\2\2\u00dd\u00e4\7`\2\2"+
		"\u00de\u00e1\7^\2\2\u00df\u00e0\7j\2\2\u00e0\u00e2\7`\2\2\u00e1\u00df"+
		"\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e4\3\2\2\2\u00e3\u00dd\3\2\2\2\u00e3"+
		"\u00de\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00cb\3\2\2\2\u00e5\u00d8\3\2"+
		"\2\2\u00e6\21\3\2\2\2\u00e7\u00e8\5\24\13\2\u00e8\23\3\2\2\2\u00e9\u00f1"+
		"\5\26\f\2\u00ea\u00ed\7`\2\2\u00eb\u00ee\5\26\f\2\u00ec\u00ee\5\b\5\2"+
		"\u00ed\u00eb\3\2\2\2\u00ed\u00ec\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00ea"+
		"\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2"+
		"\25\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00fc\5\30\r\2\u00f5\u00f8\7_\2"+
		"\2\u00f6\u00f9\5\30\r\2\u00f7\u00f9\5\b\5\2\u00f8\u00f6\3\2\2\2\u00f8"+
		"\u00f7\3\2\2\2\u00f9\u00fb\3\2\2\2\u00fa\u00f5\3\2\2\2\u00fb\u00fe\3\2"+
		"\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\27\3\2\2\2\u00fe\u00fc"+
		"\3\2\2\2\u00ff\u0102\5\b\5\2\u0100\u0102\5\32\16\2\u0101\u00ff\3\2\2\2"+
		"\u0101\u0100\3\2\2\2\u0102\u010a\3\2\2\2\u0103\u0106\7^\2\2\u0104\u0107"+
		"\5\b\5\2\u0105\u0107\5\32\16\2\u0106\u0104\3\2\2\2\u0106\u0105\3\2\2\2"+
		"\u0107\u0109\3\2\2\2\u0108\u0103\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108"+
		"\3\2\2\2\u010a\u010b\3\2\2\2\u010b\31\3\2\2\2\u010c\u010a\3\2\2\2\u010d"+
		"\u010e\5\b\5\2\u010e\u010f\7]\2\2\u010f\u0110\5\b\5\2\u0110\33\3\2\2\2"+
		"\u0111\u0112\7b\2\2\u0112\u0113\7\34\2\2\u0113\u0118\7c\2\2\u0114\u0115"+
		"\7b\2\2\u0115\u0116\7&\2\2\u0116\u0118\7c\2\2\u0117\u0111\3\2\2\2\u0117"+
		"\u0114\3\2\2\2\u0118\35\3\2\2\2\u0119\u011b\7\4\2\2\u011a\u0119\3\2\2"+
		"\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\5X-\2\u011d\u0120"+
		"\7b\2\2\u011e\u0121\5\b\5\2\u011f\u0121\5\22\n\2\u0120\u011e\3\2\2\2\u0120"+
		"\u011f\3\2\2\2\u0121\u0129\3\2\2\2\u0122\u0125\7_\2\2\u0123\u0126\5\b"+
		"\5\2\u0124\u0126\5\22\n\2\u0125\u0123\3\2\2\2\u0125\u0124\3\2\2\2\u0126"+
		"\u0128\3\2\2\2\u0127\u0122\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2"+
		"\2\2\u0129\u012a\3\2\2\2\u012a\u012c\3\2\2\2\u012b\u0129\3\2\2\2\u012c"+
		"\u012d\7c\2\2\u012d\37\3\2\2\2\u012e\u012f\7\5\2\2\u012f\u0130\5X-\2\u0130"+
		"\u0141\7b\2\2\u0131\u0134\5\b\5\2\u0132\u0134\5\22\n\2\u0133\u0131\3\2"+
		"\2\2\u0133\u0132\3\2\2\2\u0134\u013c\3\2\2\2\u0135\u0138\7_\2\2\u0136"+
		"\u0139\5\b\5\2\u0137\u0139\5\22\n\2\u0138\u0136\3\2\2\2\u0138\u0137\3"+
		"\2\2\2\u0139\u013b\3\2\2\2\u013a\u0135\3\2\2\2\u013b\u013e\3\2\2\2\u013c"+
		"\u013a\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u0140\3\2\2\2\u013e\u013c\3\2"+
		"\2\2\u013f\u0133\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141"+
		"\u0142\3\2\2\2\u0142\u0144\3\2\2\2\u0143\u0141\3\2\2\2\u0144\u0145\7c"+
		"\2\2\u0145!\3\2\2\2\u0146\u0147\5Z.\2\u0147\u0148\7d\2\2\u0148\u0149\5"+
		"\n\6\2\u0149\u014a\7e\2\2\u014a#\3\2\2\2\u014b\u014c\7\63\2\2\u014c\u014d"+
		"\7b\2\2\u014d\u014e\5N(\2\u014e\u014f\7c\2\2\u014f\u0150\7P\2\2\u0150"+
		"\u0151\7b\2\2\u0151\u0152\5\22\n\2\u0152\u0153\7c\2\2\u0153\u0154\7(\2"+
		"\2\u0154\u0155\7b\2\2\u0155\u0156\5\22\n\2\u0156\u0157\7c\2\2\u0157\u0161"+
		"\3\2\2\2\u0158\u0159\7b\2\2\u0159\u015a\5N(\2\u015a\u015b\7c\2\2\u015b"+
		"\u015c\7]\2\2\u015c\u015d\5\22\n\2\u015d\u015e\7\6\2\2\u015e\u015f\5\22"+
		"\n\2\u015f\u0161\3\2\2\2\u0160\u014b\3\2\2\2\u0160\u0158\3\2\2\2\u0161"+
		"%\3\2\2\2\u0162\u0163\b\24\1\2\u0163\u0164\7b\2\2\u0164\u0165\5&\24\2"+
		"\u0165\u0166\t\2\2\2\u0166\u0167\5&\24\2\u0167\u0168\7c\2\2\u0168\u016b"+
		"\3\2\2\2\u0169\u016b\5(\25\2\u016a\u0162\3\2\2\2\u016a\u0169\3\2\2\2\u016b"+
		"\u0171\3\2\2\2\u016c\u016d\f\4\2\2\u016d\u016e\t\2\2\2\u016e\u0170\5&"+
		"\24\5\u016f\u016c\3\2\2\2\u0170\u0173\3\2\2\2\u0171\u016f\3\2\2\2\u0171"+
		"\u0172\3\2\2\2\u0172\'\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0177\5\n\6\2"+
		"\u0175\u0177\7j\2\2\u0176\u0174\3\2\2\2\u0176\u0175\3\2\2\2\u0177)\3\2"+
		"\2\2\u0178\u017b\5.\30\2\u0179\u017b\5,\27\2\u017a\u0178\3\2\2\2\u017a"+
		"\u0179\3\2\2\2\u017b+\3\2\2\2\u017c\u017d\7r\2\2\u017d\u017e\b\27\1\2"+
		"\u017e-\3\2\2\2\u017f\u0188\5\60\31\2\u0180\u0182\7h\2\2\u0181\u0180\3"+
		"\2\2\2\u0182\u0183\3\2\2\2\u0183\u0181\3\2\2\2\u0183\u0184\3\2\2\2\u0184"+
		"\u0185\3\2\2\2\u0185\u0187\5\60\31\2\u0186\u0181\3\2\2\2\u0187\u018a\3"+
		"\2\2\2\u0188\u0186\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018e\3\2\2\2\u018a"+
		"\u0188\3\2\2\2\u018b\u018d\7h\2\2\u018c\u018b\3\2\2\2\u018d\u0190\3\2"+
		"\2\2\u018e\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018f/\3\2\2\2\u0190\u018e"+
		"\3\2\2\2\u0191\u0194\5\62\32\2\u0192\u0194\5> \2\u0193\u0191\3\2\2\2\u0193"+
		"\u0192\3\2\2\2\u0194\61\3\2\2\2\u0195\u0197\7V\2\2\u0196\u0198\7K\2\2"+
		"\u0197\u0196\3\2\2\2\u0197\u0198\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019e"+
		"\5J&\2\u019a\u019b\7_\2\2\u019b\u019d\5J&\2\u019c\u019a\3\2\2\2\u019d"+
		"\u01a0\3\2\2\2\u019e\u019c\3\2\2\2\u019e\u019f\3\2\2\2\u019f\u01a2\3\2"+
		"\2\2\u01a0\u019e\3\2\2\2\u01a1\u0195\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2"+
		"\u01a3\3\2\2\2\u01a3\u01a9\5\64\33\2\u01a4\u01a5\5B\"\2\u01a5\u01a6\5"+
		"\64\33\2\u01a6\u01a8\3\2\2\2\u01a7\u01a4\3\2\2\2\u01a8\u01ab\3\2\2\2\u01a9"+
		"\u01a7\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01b6\3\2\2\2\u01ab\u01a9\3\2"+
		"\2\2\u01ac\u01ad\7H\2\2\u01ad\u01ae\7\36\2\2\u01ae\u01b3\5L\'\2\u01af"+
		"\u01b0\7_\2\2\u01b0\u01b2\5L\'\2\u01b1\u01af\3\2\2\2\u01b2\u01b5\3\2\2"+
		"\2\u01b3\u01b1\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4\u01b7\3\2\2\2\u01b5\u01b3"+
		"\3\2\2\2\u01b6\u01ac\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01be\3\2\2\2\u01b8"+
		"\u01b9\7>\2\2\u01b9\u01bc\5N(\2\u01ba\u01bb\t\3\2\2\u01bb\u01bd\5N(\2"+
		"\u01bc\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01bf\3\2\2\2\u01be\u01b8"+
		"\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\63\3\2\2\2\u01c0\u01c2\7O\2\2\u01c1"+
		"\u01c3\t\4\2\2\u01c2\u01c1\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c4\3\2"+
		"\2\2\u01c4\u01c9\58\35\2\u01c5\u01c6\7_\2\2\u01c6\u01c8\58\35\2\u01c7"+
		"\u01c5\3\2\2\2\u01c8\u01cb\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9\u01ca\3\2"+
		"\2\2\u01ca\u01cd\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cc\u01c0\3\2\2\2\u01cc"+
		"\u01cd\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01d8\7/\2\2\u01cf\u01d4\5:\36"+
		"\2\u01d0\u01d1\7_\2\2\u01d1\u01d3\5:\36\2\u01d2\u01d0\3\2\2\2\u01d3\u01d6"+
		"\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\u01d9\3\2\2\2\u01d6"+
		"\u01d4\3\2\2\2\u01d7\u01d9\5D#\2\u01d8\u01cf\3\2\2\2\u01d8\u01d7\3\2\2"+
		"\2\u01d9\u01db\3\2\2\2\u01da\u01dc\5\66\34\2\u01db\u01da\3\2\2\2\u01db"+
		"\u01dc\3\2\2\2\u01dc\65\3\2\2\2\u01dd\u01de\7U\2\2\u01de\u01df\5N(\2\u01df"+
		"\u01ee\3\2\2\2\u01e0\u01e1\7\61\2\2\u01e1\u01e2\7\36\2\2\u01e2\u01e7\5"+
		"N(\2\u01e3\u01e4\7_\2\2\u01e4\u01e6\5N(\2\u01e5\u01e3\3\2\2\2\u01e6\u01e9"+
		"\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8\u01ec\3\2\2\2\u01e9"+
		"\u01e7\3\2\2\2\u01ea\u01eb\7\62\2\2\u01eb\u01ed\5N(\2\u01ec\u01ea\3\2"+
		"\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ef\3\2\2\2\u01ee\u01e0\3\2\2\2\u01ee"+
		"\u01ef\3\2\2\2\u01ef\u020d\3\2\2\2\u01f0\u01f1\7S\2\2\u01f1\u01f2\7b\2"+
		"\2\u01f2\u01f7\5N(\2\u01f3\u01f4\7_\2\2\u01f4\u01f6\5N(\2\u01f5\u01f3"+
		"\3\2\2\2\u01f6\u01f9\3\2\2\2\u01f7\u01f5\3\2\2\2\u01f7\u01f8\3\2\2\2\u01f8"+
		"\u01fa\3\2\2\2\u01f9\u01f7\3\2\2\2\u01fa\u0209\7c\2\2\u01fb\u01fc\7_\2"+
		"\2\u01fc\u01fd\7b\2\2\u01fd\u0202\5N(\2\u01fe\u01ff\7_\2\2\u01ff\u0201"+
		"\5N(\2\u0200\u01fe\3\2\2\2\u0201\u0204\3\2\2\2\u0202\u0200\3\2\2\2\u0202"+
		"\u0203\3\2\2\2\u0203\u0205\3\2\2\2\u0204\u0202\3\2\2\2\u0205\u0206\7c"+
		"\2\2\u0206\u0208\3\2\2\2\u0207\u01fb\3\2\2\2\u0208\u020b\3\2\2\2\u0209"+
		"\u0207\3\2\2\2\u0209\u020a\3\2\2\2\u020a\u020d\3\2\2\2\u020b\u0209\3\2"+
		"\2\2\u020c\u01dd\3\2\2\2\u020c\u01f0\3\2\2\2\u020d\u023f\3\2\2\2\u020e"+
		"\u020f\7U\2\2\u020f\u0211\5N(\2\u0210\u020e\3\2\2\2\u0210\u0211\3\2\2"+
		"\2\u0211\u0212\3\2\2\2\u0212\u0213\7\61\2\2\u0213\u0214\7\36\2\2\u0214"+
		"\u0219\5N(\2\u0215\u0216\7_\2\2\u0216\u0218\5N(\2\u0217\u0215\3\2\2\2"+
		"\u0218\u021b\3\2\2\2\u0219\u0217\3\2\2\2\u0219\u021a\3\2\2\2\u021a\u021e"+
		"\3\2\2\2\u021b\u0219\3\2\2\2\u021c\u021d\7\62\2\2\u021d\u021f\5N(\2\u021e"+
		"\u021c\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u023d\3\2\2\2\u0220\u0221\7S"+
		"\2\2\u0221\u0222\7b\2\2\u0222\u0227\5N(\2\u0223\u0224\7_\2\2\u0224\u0226"+
		"\5N(\2\u0225\u0223\3\2\2\2\u0226\u0229\3\2\2\2\u0227\u0225\3\2\2\2\u0227"+
		"\u0228\3\2\2\2\u0228\u022a\3\2\2\2\u0229\u0227\3\2\2\2\u022a\u0239\7c"+
		"\2\2\u022b\u022c\7_\2\2\u022c\u022d\7b\2\2\u022d\u0232\5N(\2\u022e\u022f"+
		"\7_\2\2\u022f\u0231\5N(\2\u0230\u022e\3\2\2\2\u0231\u0234\3\2\2\2\u0232"+
		"\u0230\3\2\2\2\u0232\u0233\3\2\2\2\u0233\u0235\3\2\2\2\u0234\u0232\3\2"+
		"\2\2\u0235\u0236\7c\2\2\u0236\u0238\3\2\2\2\u0237\u022b\3\2\2\2\u0238"+
		"\u023b\3\2\2\2\u0239\u0237\3\2\2\2\u0239\u023a\3\2\2\2\u023a\u023d\3\2"+
		"\2\2\u023b\u0239\3\2\2\2\u023c\u0210\3\2\2\2\u023c\u0220\3\2\2\2\u023d"+
		"\u023f\3\2\2\2\u023e\u020c\3\2\2\2\u023e\u023c\3\2\2\2\u023f\67\3\2\2"+
		"\2\u0240\u024d\7\7\2\2\u0241\u0242\5b\62\2\u0242\u0243\7a\2\2\u0243\u0244"+
		"\7\7\2\2\u0244\u024d\3\2\2\2\u0245\u024a\5N(\2\u0246\u0248\7\33\2\2\u0247"+
		"\u0246\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u0249\3\2\2\2\u0249\u024b\5d"+
		"\63\2\u024a\u0247\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u024d\3\2\2\2\u024c"+
		"\u0240\3\2\2\2\u024c\u0241\3\2\2\2\u024c\u0245\3\2\2\2\u024d9\3\2\2\2"+
		"\u024e\u024f\5`\61\2\u024f\u0250\7a\2\2\u0250\u0252\3\2\2\2\u0251\u024e"+
		"\3\2\2\2\u0251\u0252\3\2\2\2\u0252\u0253\3\2\2\2\u0253\u0258\5b\62\2\u0254"+
		"\u0256\7\33\2\2\u0255\u0254\3\2\2\2\u0255\u0256\3\2\2\2\u0256\u0257\3"+
		"\2\2\2\u0257\u0259\5h\65\2\u0258\u0255\3\2\2\2\u0258\u0259\3\2\2\2\u0259"+
		"\u025f\3\2\2\2\u025a\u025b\7\66\2\2\u025b\u025c\7\36\2\2\u025c\u0260\5"+
		"j\66\2\u025d\u025e\7B\2\2\u025e\u0260\7\66\2\2\u025f\u025a\3\2\2\2\u025f"+
		"\u025d\3\2\2\2\u025f\u0260\3\2\2\2\u0260\u027e\3\2\2\2\u0261\u026b\7b"+
		"\2\2\u0262\u0267\5:\36\2\u0263\u0264\7_\2\2\u0264\u0266\5:\36\2\u0265"+
		"\u0263\3\2\2\2\u0266\u0269\3\2\2\2\u0267\u0265\3\2\2\2\u0267\u0268\3\2"+
		"\2\2\u0268\u026c\3\2\2\2\u0269\u0267\3\2\2\2\u026a\u026c\5D#\2\u026b\u0262"+
		"\3\2\2\2\u026b\u026a\3\2\2\2\u026c\u026d\3\2\2\2\u026d\u0272\7c\2\2\u026e"+
		"\u0270\7\33\2\2\u026f\u026e\3\2\2\2\u026f\u0270\3\2\2\2\u0270\u0271\3"+
		"\2\2\2\u0271\u0273\5h\65\2\u0272\u026f\3\2\2\2\u0272\u0273\3\2\2\2\u0273"+
		"\u027e\3\2\2\2\u0274\u0275\7b\2\2\u0275\u0276\5> \2\u0276\u027b\7c\2\2"+
		"\u0277\u0279\7\33\2\2\u0278\u0277\3\2\2\2\u0278\u0279\3\2\2\2\u0279\u027a"+
		"\3\2\2\2\u027a\u027c\5h\65\2\u027b\u0278\3\2\2\2\u027b\u027c\3\2\2\2\u027c"+
		"\u027e\3\2\2\2\u027d\u0251\3\2\2\2\u027d\u0261\3\2\2\2\u027d\u0274\3\2"+
		"\2\2\u027e;\3\2\2\2\u027f\u0280\t\5\2\2\u0280=\3\2\2\2\u0281\u0283\7V"+
		"\2\2\u0282\u0284\7K\2\2\u0283\u0282\3\2\2\2\u0283\u0284\3\2\2\2\u0284"+
		"\u0285\3\2\2\2\u0285\u028a\5J&\2\u0286\u0287\7_\2\2\u0287\u0289\5J&\2"+
		"\u0288\u0286\3\2\2\2\u0289\u028c\3\2\2\2\u028a\u0288\3\2\2\2\u028a\u028b"+
		"\3\2\2\2\u028b\u028e\3\2\2\2\u028c\u028a\3\2\2\2\u028d\u0281\3\2\2\2\u028d"+
		"\u028e\3\2\2\2\u028e\u028f\3\2\2\2\u028f\u0295\5@!\2\u0290\u0291\5B\""+
		"\2\u0291\u0292\5@!\2\u0292\u0294\3\2\2\2\u0293\u0290\3\2\2\2\u0294\u0297"+
		"\3\2\2\2\u0295\u0293\3\2\2\2\u0295\u0296\3\2\2\2\u0296\u02a2\3\2\2\2\u0297"+
		"\u0295\3\2\2\2\u0298\u0299\7H\2\2\u0299\u029a\7\36\2\2\u029a\u029f\5L"+
		"\'\2\u029b\u029c\7_\2\2\u029c\u029e\5L\'\2\u029d\u029b\3\2\2\2\u029e\u02a1"+
		"\3\2\2\2\u029f\u029d\3\2\2\2\u029f\u02a0\3\2\2\2\u02a0\u02a3\3\2\2\2\u02a1"+
		"\u029f\3\2\2\2\u02a2\u0298\3\2\2\2\u02a2\u02a3\3\2\2\2\u02a3\u02aa\3\2"+
		"\2\2\u02a4\u02a5\7>\2\2\u02a5\u02a8\5N(\2\u02a6\u02a7\t\3\2\2\u02a7\u02a9"+
		"\5N(\2\u02a8\u02a6\3\2\2\2\u02a8\u02a9\3\2\2\2\u02a9\u02ab\3\2\2\2\u02aa"+
		"\u02a4\3\2\2\2\u02aa\u02ab\3\2\2\2\u02ab?\3\2\2\2\u02ac\u02ae\7O\2\2\u02ad"+
		"\u02af\t\4\2\2\u02ae\u02ad\3\2\2\2\u02ae\u02af\3\2\2\2\u02af\u02b0\3\2"+
		"\2\2\u02b0\u02b5\58\35\2\u02b1\u02b2\7_\2\2\u02b2\u02b4\58\35\2\u02b3"+
		"\u02b1\3\2\2\2\u02b4\u02b7\3\2\2\2\u02b5\u02b3\3\2\2\2\u02b5\u02b6\3\2"+
		"\2\2\u02b6\u02b9\3\2\2\2\u02b7\u02b5\3\2\2\2\u02b8\u02ac\3\2\2\2\u02b8"+
		"\u02b9\3\2\2\2\u02b9\u02ba\3\2\2\2\u02ba\u02c4\7/\2\2\u02bb\u02c0\5:\36"+
		"\2\u02bc\u02bd\7_\2\2\u02bd\u02bf\5:\36\2\u02be\u02bc\3\2\2\2\u02bf\u02c2"+
		"\3\2\2\2\u02c0\u02be\3\2\2\2\u02c0\u02c1\3\2\2\2\u02c1\u02c5\3\2\2\2\u02c2"+
		"\u02c0\3\2\2\2\u02c3\u02c5\5D#\2\u02c4\u02bb\3\2\2\2\u02c4\u02c3\3\2\2"+
		"\2\u02c5\u02c7\3\2\2\2\u02c6\u02c8\5\66\34\2\u02c7\u02c6\3\2\2\2\u02c7"+
		"\u02c8\3\2\2\2\u02c8A\3\2\2\2\u02c9\u02cf\7Q\2\2\u02ca\u02cb\7Q\2\2\u02cb"+
		"\u02cf\7\31\2\2\u02cc\u02cf\78\2\2\u02cd\u02cf\7+\2\2\u02ce\u02c9\3\2"+
		"\2\2\u02ce\u02ca\3\2\2\2\u02ce\u02cc\3\2\2\2\u02ce\u02cd\3\2\2\2\u02cf"+
		"C\3\2\2\2\u02d0\u02da\5:\36\2\u02d1\u02d4\5F$\2\u02d2\u02d5\5D#\2\u02d3"+
		"\u02d5\5:\36\2\u02d4\u02d2\3\2\2\2\u02d4\u02d3\3\2\2\2\u02d5\u02d6\3\2"+
		"\2\2\u02d6\u02d7\5H%\2\u02d7\u02d9\3\2\2\2\u02d8\u02d1\3\2\2\2\u02d9\u02dc"+
		"\3\2\2\2\u02da\u02d8\3\2\2\2\u02da\u02db\3\2\2\2\u02dbE\3\2\2\2\u02dc"+
		"\u02da\3\2\2\2\u02dd\u02f3\7_\2\2\u02de\u02e0\7@\2\2\u02df\u02de\3\2\2"+
		"\2\u02df\u02e0\3\2\2\2\u02e0\u02ef\3\2\2\2\u02e1\u02e3\7<\2\2\u02e2\u02e4"+
		"\7I\2\2\u02e3\u02e2\3\2\2\2\u02e3\u02e4\3\2\2\2\u02e4\u02f0\3\2\2\2\u02e5"+
		"\u02e7\7M\2\2\u02e6\u02e8\7I\2\2\u02e7\u02e6\3\2\2\2\u02e7\u02e8\3\2\2"+
		"\2\u02e8\u02f0\3\2\2\2\u02e9\u02eb\7.\2\2\u02ea\u02ec\7I\2\2\u02eb\u02ea"+
		"\3\2\2\2\u02eb\u02ec\3\2\2\2\u02ec\u02f0\3\2\2\2\u02ed\u02f0\7\67\2\2"+
		"\u02ee\u02f0\7\"\2\2\u02ef\u02e1\3\2\2\2\u02ef\u02e5\3\2\2\2\u02ef\u02e9"+
		"\3\2\2\2\u02ef\u02ed\3\2\2\2\u02ef\u02ee\3\2\2\2\u02ef\u02f0\3\2\2\2\u02f0"+
		"\u02f1\3\2\2\2\u02f1\u02f3\7;\2\2\u02f2\u02dd\3\2\2\2\u02f2\u02df\3\2"+
		"\2\2\u02f3G\3\2\2\2\u02f4\u02f5\7F\2\2\u02f5\u0303\5N(\2\u02f6\u02f7\7"+
		"R\2\2\u02f7\u02f8\7b\2\2\u02f8\u02fd\5f\64\2\u02f9\u02fa\7_\2\2\u02fa"+
		"\u02fc\5f\64\2\u02fb\u02f9\3\2\2\2\u02fc\u02ff\3\2\2\2\u02fd\u02fb\3\2"+
		"\2\2\u02fd\u02fe\3\2\2\2\u02fe\u0300\3\2\2\2\u02ff\u02fd\3\2\2\2\u0300"+
		"\u0301\7c\2\2\u0301\u0303\3\2\2\2\u0302\u02f4\3\2\2\2\u0302\u02f6\3\2"+
		"\2\2\u0302\u0303\3\2\2\2\u0303I\3\2\2\2\u0304\u0310\5b\62\2\u0305\u0306"+
		"\7b\2\2\u0306\u030b\5f\64\2\u0307\u0308\7_\2\2\u0308\u030a\5f\64\2\u0309"+
		"\u0307\3\2\2\2\u030a\u030d\3\2\2\2\u030b\u0309\3\2\2\2\u030b\u030c\3\2"+
		"\2\2\u030c\u030e\3\2\2\2\u030d\u030b\3\2\2\2\u030e\u030f\7c\2\2\u030f"+
		"\u0311\3\2\2\2\u0310\u0305\3\2\2\2\u0310\u0311\3\2\2\2\u0311\u0312\3\2"+
		"\2\2\u0312\u0313\7\33\2\2\u0313\u0314\7b\2\2\u0314\u0315\5> \2\u0315\u0316"+
		"\7c\2\2\u0316K\3\2\2\2\u0317\u031a\5N(\2\u0318\u0319\7!\2\2\u0319\u031b"+
		"\5^\60\2\u031a\u0318\3\2\2\2\u031a\u031b\3\2\2\2\u031b\u031d\3\2\2\2\u031c"+
		"\u031e\t\6\2\2\u031d\u031c\3\2\2\2\u031d\u031e\3\2\2\2\u031eM\3\2\2\2"+
		"\u031f\u0320\b(\1\2\u0320\u0321\5R*\2\u0321\u0322\5N(\26\u0322\u035a\3"+
		"\2\2\2\u0323\u035a\5\b\5\2\u0324\u035a\7l\2\2\u0325\u0326\5`\61\2\u0326"+
		"\u0327\7a\2\2\u0327\u0329\3\2\2\2\u0328\u0325\3\2\2\2\u0328\u0329\3\2"+
		"\2\2\u0329\u032a\3\2\2\2\u032a\u032b\5h\65\2\u032b\u032c\7a\2\2\u032c"+
		"\u032e\3\2\2\2\u032d\u0328\3\2\2\2\u032d\u032e\3\2\2\2\u032e\u032f\3\2"+
		"\2\2\u032f\u035a\5f\64\2\u0330\u0331\7b\2\2\u0331\u0332\5N(\2\u0332\u0333"+
		"\7c\2\2\u0333\u035a\3\2\2\2\u0334\u0335\7 \2\2\u0335\u0336\7b\2\2\u0336"+
		"\u0337\5N(\2\u0337\u0338\7\33\2\2\u0338\u0339\5V,\2\u0339\u033a\7c\2\2"+
		"\u033a\u035a\3\2\2\2\u033b\u033d\7B\2\2\u033c\u033b\3\2\2\2\u033c\u033d"+
		"\3\2\2\2\u033d\u033e\3\2\2\2\u033e\u0340\7,\2\2\u033f\u033c\3\2\2\2\u033f"+
		"\u0340\3\2\2\2\u0340\u0341\3\2\2\2\u0341\u0342\7b\2\2\u0342\u0343\5> "+
		"\2\u0343\u0344\7c\2\2\u0344\u035a\3\2\2\2\u0345\u0347\7\37\2\2\u0346\u0348"+
		"\5N(\2\u0347\u0346\3\2\2\2\u0347\u0348\3\2\2\2\u0348\u034e\3\2\2\2\u0349"+
		"\u034a\7T\2\2\u034a\u034b\5N(\2\u034b\u034c\7P\2\2\u034c\u034d\5N(\2\u034d"+
		"\u034f\3\2\2\2\u034e\u0349\3\2\2\2\u034f\u0350\3\2\2\2\u0350\u034e\3\2"+
		"\2\2\u0350\u0351\3\2\2\2\u0351\u0354\3\2\2\2\u0352\u0353\7(\2\2\u0353"+
		"\u0355\5N(\2\u0354\u0352\3\2\2\2\u0354\u0355\3\2\2\2\u0355\u0356\3\2\2"+
		"\2\u0356\u0357\7)\2\2\u0357\u035a\3\2\2\2\u0358\u035a\5r:\2\u0359\u031f"+
		"\3\2\2\2\u0359\u0323\3\2\2\2\u0359\u0324\3\2\2\2\u0359\u032d\3\2\2\2\u0359"+
		"\u0330\3\2\2\2\u0359\u0334\3\2\2\2\u0359\u033f\3\2\2\2\u0359\u0345\3\2"+
		"\2\2\u0359\u0358\3\2\2\2\u035a\u03bf\3\2\2\2\u035b\u035c\f\25\2\2\u035c"+
		"\u035d\7\3\2\2\u035d\u03be\5N(\26\u035e\u035f\f\24\2\2\u035f\u0360\t\7"+
		"\2\2\u0360\u03be\5N(\25\u0361\u0362\f\23\2\2\u0362\u0363\t\b\2\2\u0363"+
		"\u03be\5N(\24\u0364\u0365\f\22\2\2\u0365\u0366\t\t\2\2\u0366\u03be\5N"+
		"(\23\u0367\u0368\f\21\2\2\u0368\u0369\t\n\2\2\u0369\u03be\5N(\22\u036a"+
		"\u0377\f\20\2\2\u036b\u0378\7\22\2\2\u036c\u0378\7\23\2\2\u036d\u0378"+
		"\7\24\2\2\u036e\u0378\7\25\2\2\u036f\u0378\79\2\2\u0370\u0371\79\2\2\u0371"+
		"\u0378\7B\2\2\u0372\u0378\7\65\2\2\u0373\u0378\7=\2\2\u0374\u0378\7\60"+
		"\2\2\u0375\u0378\7?\2\2\u0376\u0378\7L\2\2\u0377\u036b\3\2\2\2\u0377\u036c"+
		"\3\2\2\2\u0377\u036d\3\2\2\2\u0377\u036e\3\2\2\2\u0377\u036f\3\2\2\2\u0377"+
		"\u0370\3\2\2\2\u0377\u0372\3\2\2\2\u0377\u0373\3\2\2\2\u0377\u0374\3\2"+
		"\2\2\u0377\u0375\3\2\2\2\u0377\u0376\3\2\2\2\u0378\u0379\3\2\2\2\u0379"+
		"\u03be\5N(\21\u037a\u037b\f\17\2\2\u037b\u037c\7\32\2\2\u037c\u03be\5"+
		"N(\20\u037d\u037e\f\16\2\2\u037e\u037f\7G\2\2\u037f\u03be\5N(\17\u0380"+
		"\u0381\f\b\2\2\u0381\u0383\79\2\2\u0382\u0384\7B\2\2\u0383\u0382\3\2\2"+
		"\2\u0383\u0384\3\2\2\2\u0384\u0385\3\2\2\2\u0385\u03be\5N(\t\u0386\u0388"+
		"\f\7\2\2\u0387\u0389\7B\2\2\u0388\u0387\3\2\2\2\u0388\u0389\3\2\2\2\u0389"+
		"\u038a\3\2\2\2\u038a\u038b\7\35\2\2\u038b\u038c\5N(\2\u038c\u038d\7\32"+
		"\2\2\u038d\u038e\5N(\b\u038e\u03be\3\2\2\2\u038f\u0390\f\13\2\2\u0390"+
		"\u0391\7!\2\2\u0391\u03be\5^\60\2\u0392\u0394\f\n\2\2\u0393\u0395\7B\2"+
		"\2\u0394\u0393\3\2\2\2\u0394\u0395\3\2\2\2\u0395\u0396\3\2\2\2\u0396\u0397"+
		"\t\13\2\2\u0397\u039a\5N(\2\u0398\u0399\7*\2\2\u0399\u039b\5N(\2\u039a"+
		"\u0398\3\2\2\2\u039a\u039b\3\2\2\2\u039b\u03be\3\2\2\2\u039c\u03a1\f\t"+
		"\2\2\u039d\u03a2\7:\2\2\u039e\u03a2\7C\2\2\u039f\u03a0\7B\2\2\u03a0\u03a2"+
		"\7D\2\2\u03a1\u039d\3\2\2\2\u03a1\u039e\3\2\2\2\u03a1\u039f\3\2\2\2\u03a2"+
		"\u03be\3\2\2\2\u03a3\u03a5\f\6\2\2\u03a4\u03a6\7B\2\2\u03a5\u03a4\3\2"+
		"\2\2\u03a5\u03a6\3\2\2\2\u03a6\u03a7\3\2\2\2\u03a7\u03bb\7\65\2\2\u03a8"+
		"\u03b2\7b\2\2\u03a9\u03b3\5> \2\u03aa\u03af\5N(\2\u03ab\u03ac\7_\2\2\u03ac"+
		"\u03ae\5N(\2\u03ad\u03ab\3\2\2\2\u03ae\u03b1\3\2\2\2\u03af\u03ad\3\2\2"+
		"\2\u03af\u03b0\3\2\2\2\u03b0\u03b3\3\2\2\2\u03b1\u03af\3\2\2\2\u03b2\u03a9"+
		"\3\2\2\2\u03b2\u03aa\3\2\2\2\u03b2\u03b3\3\2\2\2\u03b3\u03b4\3\2\2\2\u03b4"+
		"\u03bc\7c\2\2\u03b5\u03b6\5`\61\2\u03b6\u03b7\7a\2\2\u03b7\u03b9\3\2\2"+
		"\2\u03b8\u03b5\3\2\2\2\u03b8\u03b9\3\2\2\2\u03b9\u03ba\3\2\2\2\u03ba\u03bc"+
		"\5b\62\2\u03bb\u03a8\3\2\2\2\u03bb\u03b8\3\2\2\2\u03bc\u03be\3\2\2\2\u03bd"+
		"\u035b\3\2\2\2\u03bd\u035e\3\2\2\2\u03bd\u0361\3\2\2\2\u03bd\u0364\3\2"+
		"\2\2\u03bd\u0367\3\2\2\2\u03bd\u036a\3\2\2\2\u03bd\u037a\3\2\2\2\u03bd"+
		"\u037d\3\2\2\2\u03bd\u0380\3\2\2\2\u03bd\u0386\3\2\2\2\u03bd\u038f\3\2"+
		"\2\2\u03bd\u0392\3\2\2\2\u03bd\u039c\3\2\2\2\u03bd\u03a3\3\2\2\2\u03be"+
		"\u03c1\3\2\2\2\u03bf\u03bd\3\2\2\2\u03bf\u03c0\3\2\2\2\u03c0O\3\2\2\2"+
		"\u03c1\u03bf\3\2\2\2\u03c2\u03c3\t\f\2\2\u03c3Q\3\2\2\2\u03c4\u03c5\t"+
		"\r\2\2\u03c5S\3\2\2\2\u03c6\u03c7\5l\67\2\u03c7U\3\2\2\2\u03c8\u03ca\5"+
		"T+\2\u03c9\u03c8\3\2\2\2\u03ca\u03cb\3\2\2\2\u03cb\u03c9\3\2\2\2\u03cb"+
		"\u03cc\3\2\2\2\u03cc\u03d7\3\2\2\2\u03cd\u03ce\7b\2\2\u03ce\u03cf\5p9"+
		"\2\u03cf\u03d0\7c\2\2\u03d0\u03d8\3\2\2\2\u03d1\u03d2\7b\2\2\u03d2\u03d3"+
		"\5p9\2\u03d3\u03d4\7_\2\2\u03d4\u03d5\5p9\2\u03d5\u03d6\7c\2\2\u03d6\u03d8"+
		"\3\2\2\2\u03d7\u03cd\3\2\2\2\u03d7\u03d1\3\2\2\2\u03d7\u03d8\3\2\2\2\u03d8"+
		"W\3\2\2\2\u03d9\u03da\5l\67\2\u03daY\3\2\2\2\u03db\u03dc\5\\/\2\u03dc"+
		"[\3\2\2\2\u03dd\u03de\t\16\2\2\u03de]\3\2\2\2\u03df\u03e0\5l\67\2\u03e0"+
		"_\3\2\2\2\u03e1\u03e2\5l\67\2\u03e2a\3\2\2\2\u03e3\u03e4\5l\67\2\u03e4"+
		"c\3\2\2\2\u03e5\u03e6\t\17\2\2\u03e6e\3\2\2\2\u03e7\u03e8\5l\67\2\u03e8"+
		"g\3\2\2\2\u03e9\u03ea\5l\67\2\u03eai\3\2\2\2\u03eb\u03ec\5l\67\2\u03ec"+
		"k\3\2\2\2\u03ed\u03f0\5<\37\2\u03ee\u03f0\7m\2\2\u03ef\u03ed\3\2\2\2\u03ef"+
		"\u03ee\3\2\2\2\u03f0m\3\2\2\2\u03f1\u03f2\7n\2\2\u03f2o\3\2\2\2\u03f3"+
		"\u03f5\t\b\2\2\u03f4\u03f3\3\2\2\2\u03f4\u03f5\3\2\2\2\u03f5\u03f6\3\2"+
		"\2\2\u03f6\u03f7\7j\2\2\u03f7q\3\2\2\2\u03f8\u03f9\7J\2\2\u03f9\u03fe"+
		"\7b\2\2\u03fa\u03ff\7\64\2\2\u03fb\u03fc\t\20\2\2\u03fc\u03fd\7_\2\2\u03fd"+
		"\u03ff\5t;\2\u03fe\u03fa\3\2\2\2\u03fe\u03fb\3\2\2\2\u03ff\u0400\3\2\2"+
		"\2\u0400\u0401\7c\2\2\u0401s\3\2\2\2\u0402\u0403\7n\2\2\u0403u\3\2\2\2"+
		"\u008fy}\u0080\u0086\u008a\u0098\u009d\u00a0\u00a5\u00ad\u00b4\u00b8\u00c9"+
		"\u00d4\u00d6\u00e1\u00e3\u00e5\u00ed\u00f1\u00f8\u00fc\u0101\u0106\u010a"+
		"\u0117\u011a\u0120\u0125\u0129\u0133\u0138\u013c\u0141\u0160\u016a\u0171"+
		"\u0176\u017a\u0183\u0188\u018e\u0193\u0197\u019e\u01a1\u01a9\u01b3\u01b6"+
		"\u01bc\u01be\u01c2\u01c9\u01cc\u01d4\u01d8\u01db\u01e7\u01ec\u01ee\u01f7"+
		"\u0202\u0209\u020c\u0210\u0219\u021e\u0227\u0232\u0239\u023c\u023e\u0247"+
		"\u024a\u024c\u0251\u0255\u0258\u025f\u0267\u026b\u026f\u0272\u0278\u027b"+
		"\u027d\u0283\u028a\u028d\u0295\u029f\u02a2\u02a8\u02aa\u02ae\u02b5\u02b8"+
		"\u02c0\u02c4\u02c7\u02ce\u02d4\u02da\u02df\u02e3\u02e7\u02eb\u02ef\u02f2"+
		"\u02fd\u0302\u030b\u0310\u031a\u031d\u0328\u032d\u033c\u033f\u0347\u0350"+
		"\u0354\u0359\u0377\u0383\u0388\u0394\u039a\u03a1\u03a5\u03af\u03b2\u03b8"+
		"\u03bb\u03bd\u03bf\u03cb\u03d7\u03ef\u03f4\u03fe";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}