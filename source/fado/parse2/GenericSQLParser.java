// Generated from /Users/jasonosgood/Projects/fado/source/fado/parse2/GenericSQL.g4 by ANTLR 4.x
package fado.parse2;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GenericSQLParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__2=1, T__1=2, T__0=3, ALL=4, AND=5, ANY=6, AS=7, ASC=8, BETWEEN=9, BY=10, 
		CASE=11, DELETE=12, DESC=13, DISTINCT=14, ELSE=15, END=16, EXISTS=17, 
		FALSE=18, FROM=19, FULL=20, GROUP=21, HAVING=22, IN=23, INNER=24, INSERT=25, 
		INTO=26, IS=27, JOIN=28, LEFT=29, LIKE=30, NATURAL=31, NOT=32, NULL=33, 
		ON=34, OR=35, ORDER=36, OUTER=37, PERCENT=38, RIGHT=39, SELECT=40, SET=41, 
		SOME=42, THEN=43, TRUE=44, TOP=45, UNION=46, UNIQUE=47, UPDATE=48, USING=49, 
		VALUES=50, WHEN=51, WHERE=52, MAX=53, MIN=54, COUNT=55, Integer=56, Float=57, 
		String=58, Timestamp=59, Identifier=60, Comment=61, Whitespace=62, DOT=63, 
		COMMA=64, LPAREN=65, RPAREN=66, LCURLY=67, RCURLY=68, STRCAT=69, QUESTION=70, 
		COLON=71, SEMI=72, EQ=73, NEQ1=74, NEQ2=75, LTE=76, LT=77, GTE=78, GT=79, 
		PLUS=80, MINUS=81, DIVIDE=82, STAR=83, MOD=84;
	public static final String[] tokenNames = {
		"<INVALID>", "'{ts'", "'{t'", "'{d'", "ALL", "AND", "ANY", "AS", "ASC", 
		"BETWEEN", "BY", "CASE", "DELETE", "DESC", "DISTINCT", "ELSE", "END", 
		"EXISTS", "FALSE", "FROM", "FULL", "GROUP", "HAVING", "IN", "INNER", "INSERT", 
		"INTO", "IS", "JOIN", "LEFT", "LIKE", "NATURAL", "NOT", "NULL", "ON", 
		"OR", "ORDER", "OUTER", "PERCENT", "RIGHT", "SELECT", "SET", "SOME", "THEN", 
		"TRUE", "TOP", "UNION", "UNIQUE", "UPDATE", "USING", "VALUES", "WHEN", 
		"WHERE", "MAX", "MIN", "COUNT", "Integer", "Float", "String", "Timestamp", 
		"Identifier", "Comment", "Whitespace", "'.'", "','", "'('", "')'", "'{'", 
		"'}'", "'||'", "'?'", "':'", "';'", "'='", "'<>'", "'!='", "'<='", "'<'", 
		"'>='", "'>'", "'+'", "'-'", "'/'", "'*'", "'%'"
	};
	public static final int
		RULE_statement = 0, RULE_subSelect = 1, RULE_select = 2, RULE_insert = 3, 
		RULE_update = 4, RULE_setter = 5, RULE_into = 6, RULE_columnList = 7, 
		RULE_values = 8, RULE_itemList = 9, RULE_item = 10, RULE_allColumns = 11, 
		RULE_alias = 12, RULE_function = 13, RULE_from = 14, RULE_fromItem = 15, 
		RULE_join = 16, RULE_where = 17, RULE_groupBy = 18, RULE_having = 19, 
		RULE_orderBy = 20, RULE_orderByItem = 21, RULE_nestedCondition = 22, RULE_conditionList = 23, 
		RULE_condition = 24, RULE_in = 25, RULE_between = 26, RULE_isNull = 27, 
		RULE_exists = 28, RULE_like = 29, RULE_comparison = 30, RULE_comparator = 31, 
		RULE_quantifier = 32, RULE_expressionList = 33, RULE_nestedExpression = 34, 
		RULE_expression = 35, RULE_multiply = 36, RULE_value = 37, RULE_literal = 38, 
		RULE_date = 39, RULE_unary = 40, RULE_caseExpression = 41, RULE_tableRef = 42, 
		RULE_columnRef = 43, RULE_databaseName = 44, RULE_tableName = 45, RULE_tableAlias = 46, 
		RULE_columnName = 47;
	public static final String[] ruleNames = {
		"statement", "subSelect", "select", "insert", "update", "setter", "into", 
		"columnList", "values", "itemList", "item", "allColumns", "alias", "function", 
		"from", "fromItem", "join", "where", "groupBy", "having", "orderBy", "orderByItem", 
		"nestedCondition", "conditionList", "condition", "in", "between", "isNull", 
		"exists", "like", "comparison", "comparator", "quantifier", "expressionList", 
		"nestedExpression", "expression", "multiply", "value", "literal", "date", 
		"unary", "caseExpression", "tableRef", "columnRef", "databaseName", "tableName", 
		"tableAlias", "columnName"
	};

	@Override
	public String getGrammarFileName() { return "GenericSQL.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GenericSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StatementContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(GenericSQLParser.EOF, 0); }
		public UpdateContext update() {
			return getRuleContext(UpdateContext.class,0);
		}
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(GenericSQLParser.SEMI, 0); }
		public InsertContext insert() {
			return getRuleContext(InsertContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_statement);
		int _la;
		try {
			setState(114);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(96); select();
				setState(98);
				_la = _input.LA(1);
				if (_la==SEMI) {
					{
					setState(97); match(SEMI);
					}
				}

				setState(100); match(EOF);
				}
				break;
			case INSERT:
				enterOuterAlt(_localctx, 2);
				{
				setState(102); insert();
				setState(104);
				_la = _input.LA(1);
				if (_la==SEMI) {
					{
					setState(103); match(SEMI);
					}
				}

				setState(106); match(EOF);
				}
				break;
			case UPDATE:
				enterOuterAlt(_localctx, 3);
				{
				setState(108); update();
				setState(110);
				_la = _input.LA(1);
				if (_la==SEMI) {
					{
					setState(109); match(SEMI);
					}
				}

				setState(112); match(EOF);
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

	public static class SubSelectContext extends ParserRuleContext {
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(GenericSQLParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(GenericSQLParser.LPAREN, 0); }
		public SubSelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subSelect; }
	}

	public final SubSelectContext subSelect() throws RecognitionException {
		SubSelectContext _localctx = new SubSelectContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_subSelect);
		try {
			setState(121);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(116); select();
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(117); match(LPAREN);
				setState(118); select();
				setState(119); match(RPAREN);
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

	public static class SelectContext extends ParserRuleContext {
		public HavingContext having() {
			return getRuleContext(HavingContext.class,0);
		}
		public TerminalNode Integer() { return getToken(GenericSQLParser.Integer, 0); }
		public FromContext from() {
			return getRuleContext(FromContext.class,0);
		}
		public OrderByContext orderBy() {
			return getRuleContext(OrderByContext.class,0);
		}
		public TerminalNode ALL() { return getToken(GenericSQLParser.ALL, 0); }
		public JoinContext join(int i) {
			return getRuleContext(JoinContext.class,i);
		}
		public TerminalNode PERCENT() { return getToken(GenericSQLParser.PERCENT, 0); }
		public ItemListContext itemList() {
			return getRuleContext(ItemListContext.class,0);
		}
		public TerminalNode TOP() { return getToken(GenericSQLParser.TOP, 0); }
		public IntoContext into() {
			return getRuleContext(IntoContext.class,0);
		}
		public TerminalNode SELECT() { return getToken(GenericSQLParser.SELECT, 0); }
		public TerminalNode UNIQUE() { return getToken(GenericSQLParser.UNIQUE, 0); }
		public List<JoinContext> join() {
			return getRuleContexts(JoinContext.class);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public TerminalNode DISTINCT() { return getToken(GenericSQLParser.DISTINCT, 0); }
		public GroupByContext groupBy() {
			return getRuleContext(GroupByContext.class,0);
		}
		public SelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select; }
	}

	public final SelectContext select() throws RecognitionException {
		SelectContext _localctx = new SelectContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_select);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123); match(SELECT);
			setState(125);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << DISTINCT) | (1L << UNIQUE))) != 0)) {
				{
				setState(124);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << DISTINCT) | (1L << UNIQUE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
			}

			setState(132);
			_la = _input.LA(1);
			if (_la==TOP) {
				{
				setState(127); match(TOP);
				setState(128); match(Integer);
				setState(130);
				_la = _input.LA(1);
				if (_la==PERCENT) {
					{
					setState(129); match(PERCENT);
					}
				}

				}
			}

			setState(134); itemList();
			setState(136);
			_la = _input.LA(1);
			if (_la==INTO) {
				{
				setState(135); into();
				}
			}

			setState(138); from();
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INNER) | (1L << JOIN) | (1L << LEFT) | (1L << NATURAL) | (1L << OUTER) | (1L << RIGHT))) != 0)) {
				{
				{
				setState(139); join();
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(145); where();
				}
			}

			setState(149);
			_la = _input.LA(1);
			if (_la==GROUP) {
				{
				setState(148); groupBy();
				}
			}

			setState(152);
			_la = _input.LA(1);
			if (_la==HAVING) {
				{
				setState(151); having();
				}
			}

			setState(155);
			_la = _input.LA(1);
			if (_la==ORDER) {
				{
				setState(154); orderBy();
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

	public static class InsertContext extends ParserRuleContext {
		public ColumnListContext columnList() {
			return getRuleContext(ColumnListContext.class,0);
		}
		public TerminalNode INSERT() { return getToken(GenericSQLParser.INSERT, 0); }
		public ValuesContext values() {
			return getRuleContext(ValuesContext.class,0);
		}
		public IntoContext into() {
			return getRuleContext(IntoContext.class,0);
		}
		public InsertContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert; }
	}

	public final InsertContext insert() throws RecognitionException {
		InsertContext _localctx = new InsertContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_insert);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(INSERT);
			setState(158); into();
			setState(160);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(159); columnList();
				}
			}

			{
			setState(162); values();
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

	public static class UpdateContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(GenericSQLParser.SET, 0); }
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public TerminalNode UPDATE() { return getToken(GenericSQLParser.UPDATE, 0); }
		public List<SetterContext> setter() {
			return getRuleContexts(SetterContext.class);
		}
		public TableRefContext tableRef() {
			return getRuleContext(TableRefContext.class,0);
		}
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public SetterContext setter(int i) {
			return getRuleContext(SetterContext.class,i);
		}
		public UpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update; }
	}

	public final UpdateContext update() throws RecognitionException {
		UpdateContext _localctx = new UpdateContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_update);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164); match(UPDATE);
			setState(165); tableRef();
			setState(166); match(SET);
			setState(167); setter();
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(168); match(COMMA);
				setState(169); setter();
				}
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(176);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(175); where();
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

	public static class SetterContext extends ParserRuleContext {
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode EQ() { return getToken(GenericSQLParser.EQ, 0); }
		public SetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setter; }
	}

	public final SetterContext setter() throws RecognitionException {
		SetterContext _localctx = new SetterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_setter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178); columnName();
			setState(179); match(EQ);
			setState(180); literal();
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

	public static class IntoContext extends ParserRuleContext {
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public TableRefContext tableRef(int i) {
			return getRuleContext(TableRefContext.class,i);
		}
		public TerminalNode INTO() { return getToken(GenericSQLParser.INTO, 0); }
		public List<TableRefContext> tableRef() {
			return getRuleContexts(TableRefContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public IntoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_into; }
	}

	public final IntoContext into() throws RecognitionException {
		IntoContext _localctx = new IntoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_into);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182); match(INTO);
			setState(183); tableRef();
			setState(188);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(184); match(COMMA);
				setState(185); tableRef();
				}
				}
				setState(190);
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

	public static class ColumnListContext extends ParserRuleContext {
		public ColumnNameContext columnName(int i) {
			return getRuleContext(ColumnNameContext.class,i);
		}
		public List<ColumnNameContext> columnName() {
			return getRuleContexts(ColumnNameContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(GenericSQLParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(GenericSQLParser.LPAREN, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public ColumnListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnList; }
	}

	public final ColumnListContext columnList() throws RecognitionException {
		ColumnListContext _localctx = new ColumnListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_columnList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191); match(LPAREN);
			setState(192); columnName();
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(193); match(COMMA);
				setState(194); columnName();
				}
				}
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(200); match(RPAREN);
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

	public static class ValuesContext extends ParserRuleContext {
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(GenericSQLParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(GenericSQLParser.LPAREN, 0); }
		public TerminalNode VALUES() { return getToken(GenericSQLParser.VALUES, 0); }
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public ValuesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_values; }
	}

	public final ValuesContext values() throws RecognitionException {
		ValuesContext _localctx = new ValuesContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_values);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202); match(VALUES);
			setState(203); match(LPAREN);
			setState(204); literal();
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(205); match(COMMA);
				setState(206); literal();
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(212); match(RPAREN);
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

	public static class ItemListContext extends ParserRuleContext {
		public ItemContext item(int i) {
			return getRuleContext(ItemContext.class,i);
		}
		public List<ItemContext> item() {
			return getRuleContexts(ItemContext.class);
		}
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public TerminalNode STAR() { return getToken(GenericSQLParser.STAR, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public ItemListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_itemList; }
	}

	public final ItemListContext itemList() throws RecognitionException {
		ItemListContext _localctx = new ItemListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_itemList);
		int _la;
		try {
			setState(223);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(214); match(STAR);
				}
				break;
			case 1:
			case 2:
			case 3:
			case CASE:
			case FALSE:
			case TRUE:
			case Integer:
			case Float:
			case String:
			case Identifier:
			case LPAREN:
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(215); item();
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(216); match(COMMA);
					setState(217); item();
					}
					}
					setState(222);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class ItemContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(GenericSQLParser.AS, 0); }
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public CaseExpressionContext caseExpression() {
			return getRuleContext(CaseExpressionContext.class,0);
		}
		public AllColumnsContext allColumns() {
			return getRuleContext(AllColumnsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public ItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_item; }
	}

	public final ItemContext item() throws RecognitionException {
		ItemContext _localctx = new ItemContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_item);
		int _la;
		try {
			setState(247);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(225); function();
				setState(230);
				_la = _input.LA(1);
				if (_la==AS || _la==Identifier) {
					{
					setState(227);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(226); match(AS);
						}
					}

					setState(229); alias();
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(232); expression();
				setState(237);
				_la = _input.LA(1);
				if (_la==AS || _la==Identifier) {
					{
					setState(234);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(233); match(AS);
						}
					}

					setState(236); alias();
					}
				}

				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(239); allColumns();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(240); caseExpression();
				setState(245);
				_la = _input.LA(1);
				if (_la==AS || _la==Identifier) {
					{
					setState(242);
					_la = _input.LA(1);
					if (_la==AS) {
						{
						setState(241); match(AS);
						}
					}

					setState(244); alias();
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

	public static class AllColumnsContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(GenericSQLParser.DOT, 0); }
		public TerminalNode STAR() { return getToken(GenericSQLParser.STAR, 0); }
		public TableAliasContext tableAlias() {
			return getRuleContext(TableAliasContext.class,0);
		}
		public AllColumnsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_allColumns; }
	}

	public final AllColumnsContext allColumns() throws RecognitionException {
		AllColumnsContext _localctx = new AllColumnsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_allColumns);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249); tableAlias();
			setState(250); match(DOT);
			setState(251); match(STAR);
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

	public static class AliasContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(GenericSQLParser.Identifier, 0); }
		public AliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alias; }
	}

	public final AliasContext alias() throws RecognitionException {
		AliasContext _localctx = new AliasContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_alias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253); match(Identifier);
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
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public TerminalNode RPAREN() { return getToken(GenericSQLParser.RPAREN, 0); }
		public TerminalNode Identifier() { return getToken(GenericSQLParser.Identifier, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode LPAREN() { return getToken(GenericSQLParser.LPAREN, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255); match(Identifier);
			setState(256); match(LPAREN);
			setState(265);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << 3) | (1L << CASE) | (1L << FALSE) | (1L << TRUE) | (1L << Integer) | (1L << Float) | (1L << String) | (1L << Identifier))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (LPAREN - 65)) | (1L << (PLUS - 65)) | (1L << (MINUS - 65)))) != 0)) {
				{
				setState(257); expression();
				setState(262);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(258); match(COMMA);
					setState(259); expression();
					}
					}
					setState(264);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(267); match(RPAREN);
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

	public static class FromContext extends ParserRuleContext {
		public FromItemContext fromItem(int i) {
			return getRuleContext(FromItemContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public TerminalNode FROM() { return getToken(GenericSQLParser.FROM, 0); }
		public List<FromItemContext> fromItem() {
			return getRuleContexts(FromItemContext.class);
		}
		public FromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from; }
	}

	public final FromContext from() throws RecognitionException {
		FromContext _localctx = new FromContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_from);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269); match(FROM);
			setState(270); fromItem();
			setState(275);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(271); match(COMMA);
				setState(272); fromItem();
				}
				}
				setState(277);
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

	public static class FromItemContext extends ParserRuleContext {
		public TerminalNode AS() { return getToken(GenericSQLParser.AS, 0); }
		public SubSelectContext subSelect() {
			return getRuleContext(SubSelectContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(GenericSQLParser.RPAREN, 0); }
		public TerminalNode LPAREN() { return getToken(GenericSQLParser.LPAREN, 0); }
		public TableRefContext tableRef() {
			return getRuleContext(TableRefContext.class,0);
		}
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public FromItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fromItem; }
	}

	public final FromItemContext fromItem() throws RecognitionException {
		FromItemContext _localctx = new FromItemContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_fromItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				{
				setState(278); match(LPAREN);
				setState(279); subSelect();
				setState(280); match(RPAREN);
				}
				}
				break;
			case Identifier:
				{
				setState(282); tableRef();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(289);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(286);
				_la = _input.LA(1);
				if (_la==AS) {
					{
					setState(285); match(AS);
					}
				}

				setState(288); alias();
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

	public static class JoinContext extends ParserRuleContext {
		public TerminalNode OUTER() { return getToken(GenericSQLParser.OUTER, 0); }
		public TerminalNode JOIN() { return getToken(GenericSQLParser.JOIN, 0); }
		public TerminalNode NATURAL() { return getToken(GenericSQLParser.NATURAL, 0); }
		public TerminalNode ON() { return getToken(GenericSQLParser.ON, 0); }
		public TerminalNode INNER() { return getToken(GenericSQLParser.INNER, 0); }
		public ConditionListContext conditionList() {
			return getRuleContext(ConditionListContext.class,0);
		}
		public TerminalNode LEFT() { return getToken(GenericSQLParser.LEFT, 0); }
		public TerminalNode RIGHT() { return getToken(GenericSQLParser.RIGHT, 0); }
		public AliasContext alias() {
			return getRuleContext(AliasContext.class,0);
		}
		public FromItemContext fromItem() {
			return getRuleContext(FromItemContext.class,0);
		}
		public JoinContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_join; }
	}

	public final JoinContext join() throws RecognitionException {
		JoinContext _localctx = new JoinContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_join);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(291); match(JOIN);
				}
				break;

			case 2:
				{
				setState(292); match(INNER);
				setState(293); match(JOIN);
				}
				break;

			case 3:
				{
				setState(294); match(LEFT);
				setState(295); match(JOIN);
				}
				break;

			case 4:
				{
				setState(296); match(LEFT);
				setState(297); match(OUTER);
				setState(298); match(JOIN);
				}
				break;

			case 5:
				{
				setState(299); match(RIGHT);
				setState(300); match(JOIN);
				}
				break;

			case 6:
				{
				setState(301); match(RIGHT);
				setState(302); match(OUTER);
				setState(303); match(JOIN);
				}
				break;

			case 7:
				{
				setState(304); match(OUTER);
				setState(305); match(JOIN);
				}
				break;

			case 8:
				{
				setState(306); match(NATURAL);
				setState(307); match(JOIN);
				}
				break;
			}
			setState(310); fromItem();
			setState(311); alias();
			setState(312); match(ON);
			setState(313); conditionList();
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

	public static class WhereContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(GenericSQLParser.WHERE, 0); }
		public ConditionListContext conditionList() {
			return getRuleContext(ConditionListContext.class,0);
		}
		public WhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where; }
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315); match(WHERE);
			setState(316); conditionList();
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

	public static class GroupByContext extends ParserRuleContext {
		public List<ColumnRefContext> columnRef() {
			return getRuleContexts(ColumnRefContext.class);
		}
		public ColumnRefContext columnRef(int i) {
			return getRuleContext(ColumnRefContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public TerminalNode BY() { return getToken(GenericSQLParser.BY, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public TerminalNode GROUP() { return getToken(GenericSQLParser.GROUP, 0); }
		public GroupByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_groupBy; }
	}

	public final GroupByContext groupBy() throws RecognitionException {
		GroupByContext _localctx = new GroupByContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_groupBy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318); match(GROUP);
			setState(319); match(BY);
			setState(320); columnRef();
			setState(325);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(321); match(COMMA);
				setState(322); columnRef();
				}
				}
				setState(327);
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

	public static class HavingContext extends ParserRuleContext {
		public TerminalNode HAVING() { return getToken(GenericSQLParser.HAVING, 0); }
		public ConditionListContext conditionList() {
			return getRuleContext(ConditionListContext.class,0);
		}
		public HavingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_having; }
	}

	public final HavingContext having() throws RecognitionException {
		HavingContext _localctx = new HavingContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_having);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(328); match(HAVING);
			setState(329); conditionList();
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

	public static class OrderByContext extends ParserRuleContext {
		public OrderByItemContext orderByItem(int i) {
			return getRuleContext(OrderByItemContext.class,i);
		}
		public List<OrderByItemContext> orderByItem() {
			return getRuleContexts(OrderByItemContext.class);
		}
		public TerminalNode ORDER() { return getToken(GenericSQLParser.ORDER, 0); }
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public TerminalNode BY() { return getToken(GenericSQLParser.BY, 0); }
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public OrderByContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderBy; }
	}

	public final OrderByContext orderBy() throws RecognitionException {
		OrderByContext _localctx = new OrderByContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_orderBy);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(331); match(ORDER);
			setState(332); match(BY);
			setState(333); orderByItem();
			setState(338);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(334); match(COMMA);
				setState(335); orderByItem();
				}
				}
				setState(340);
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

	public static class OrderByItemContext extends ParserRuleContext {
		public TerminalNode DESC() { return getToken(GenericSQLParser.DESC, 0); }
		public ColumnRefContext columnRef() {
			return getRuleContext(ColumnRefContext.class,0);
		}
		public TerminalNode ASC() { return getToken(GenericSQLParser.ASC, 0); }
		public OrderByItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_orderByItem; }
	}

	public final OrderByItemContext orderByItem() throws RecognitionException {
		OrderByItemContext _localctx = new OrderByItemContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_orderByItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(341); columnRef();
			setState(343);
			_la = _input.LA(1);
			if (_la==ASC || _la==DESC) {
				{
				setState(342);
				_la = _input.LA(1);
				if ( !(_la==ASC || _la==DESC) ) {
				_errHandler.recoverInline(this);
				}
				consume();
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

	public static class NestedConditionContext extends ParserRuleContext {
		public TerminalNode RPAREN() { return getToken(GenericSQLParser.RPAREN, 0); }
		public ConditionListContext conditionList() {
			return getRuleContext(ConditionListContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(GenericSQLParser.LPAREN, 0); }
		public NestedConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nestedCondition; }
	}

	public final NestedConditionContext nestedCondition() throws RecognitionException {
		NestedConditionContext _localctx = new NestedConditionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_nestedCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345); match(LPAREN);
			setState(346); conditionList();
			setState(347); match(RPAREN);
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

	public static class ConditionListContext extends ParserRuleContext {
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public List<TerminalNode> AND() { return getTokens(GenericSQLParser.AND); }
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(GenericSQLParser.OR); }
		public TerminalNode AND(int i) {
			return getToken(GenericSQLParser.AND, i);
		}
		public TerminalNode OR(int i) {
			return getToken(GenericSQLParser.OR, i);
		}
		public ConditionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionList; }
	}

	public final ConditionListContext conditionList() throws RecognitionException {
		ConditionListContext _localctx = new ConditionListContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_conditionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349); condition();
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND || _la==OR) {
				{
				{
				setState(350);
				_la = _input.LA(1);
				if ( !(_la==AND || _la==OR) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(351); condition();
				}
				}
				setState(356);
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

	public static class ConditionContext extends ParserRuleContext {
		public ExistsContext exists() {
			return getRuleContext(ExistsContext.class,0);
		}
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public BetweenContext between() {
			return getRuleContext(BetweenContext.class,0);
		}
		public IsNullContext isNull() {
			return getRuleContext(IsNullContext.class,0);
		}
		public QuantifierContext quantifier() {
			return getRuleContext(QuantifierContext.class,0);
		}
		public InContext in() {
			return getRuleContext(InContext.class,0);
		}
		public NestedConditionContext nestedCondition() {
			return getRuleContext(NestedConditionContext.class,0);
		}
		public LikeContext like() {
			return getRuleContext(LikeContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_condition);
		try {
			setState(365);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(357); comparison();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(358); nestedCondition();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(359); in();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(360); between();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(361); isNull();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(362); exists();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(363); like();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(364); quantifier();
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

	public static class InContext extends ParserRuleContext {
		public TerminalNode IN() { return getToken(GenericSQLParser.IN, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public SubSelectContext subSelect() {
			return getRuleContext(SubSelectContext.class,0);
		}
		public TerminalNode NOT() { return getToken(GenericSQLParser.NOT, 0); }
		public TerminalNode RPAREN() { return getToken(GenericSQLParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(GenericSQLParser.LPAREN, 0); }
		public InContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_in; }
	}

	public final InContext in() throws RecognitionException {
		InContext _localctx = new InContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_in);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367); expression();
			setState(369);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(368); match(NOT);
				}
			}

			setState(371); match(IN);
			setState(372); match(LPAREN);
			setState(375);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				setState(373); subSelect();
				}
				break;

			case 2:
				{
				setState(374); expressionList();
				}
				break;
			}
			setState(377); match(RPAREN);
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

	public static class BetweenContext extends ParserRuleContext {
		public TerminalNode BETWEEN() { return getToken(GenericSQLParser.BETWEEN, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(GenericSQLParser.AND, 0); }
		public TerminalNode NOT() { return getToken(GenericSQLParser.NOT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public BetweenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_between; }
	}

	public final BetweenContext between() throws RecognitionException {
		BetweenContext _localctx = new BetweenContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_between);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379); expression();
			setState(381);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(380); match(NOT);
				}
			}

			setState(383); match(BETWEEN);
			setState(384); expression();
			setState(385); match(AND);
			setState(386); expression();
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

	public static class IsNullContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(GenericSQLParser.NOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode IS() { return getToken(GenericSQLParser.IS, 0); }
		public TerminalNode NULL() { return getToken(GenericSQLParser.NULL, 0); }
		public IsNullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isNull; }
	}

	public final IsNullContext isNull() throws RecognitionException {
		IsNullContext _localctx = new IsNullContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_isNull);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388); expression();
			setState(389); match(IS);
			setState(391);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(390); match(NOT);
				}
			}

			setState(393); match(NULL);
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

	public static class ExistsContext extends ParserRuleContext {
		public TerminalNode EXISTS() { return getToken(GenericSQLParser.EXISTS, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExistsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exists; }
	}

	public final ExistsContext exists() throws RecognitionException {
		ExistsContext _localctx = new ExistsContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_exists);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(395); match(EXISTS);
			setState(396); expression();
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

	public static class LikeContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode NOT() { return getToken(GenericSQLParser.NOT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode LIKE() { return getToken(GenericSQLParser.LIKE, 0); }
		public LikeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_like; }
	}

	public final LikeContext like() throws RecognitionException {
		LikeContext _localctx = new LikeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_like);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398); expression();
			setState(400);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(399); match(NOT);
				}
			}

			setState(402); match(LIKE);
			setState(403); expression();
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

	public static class ComparisonContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_comparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405); expression();
			setState(406); comparator();
			setState(407); expression();
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

	public static class ComparatorContext extends ParserRuleContext {
		public TerminalNode GTE() { return getToken(GenericSQLParser.GTE, 0); }
		public TerminalNode LT() { return getToken(GenericSQLParser.LT, 0); }
		public TerminalNode NEQ2() { return getToken(GenericSQLParser.NEQ2, 0); }
		public TerminalNode LTE() { return getToken(GenericSQLParser.LTE, 0); }
		public TerminalNode GT() { return getToken(GenericSQLParser.GT, 0); }
		public TerminalNode NEQ1() { return getToken(GenericSQLParser.NEQ1, 0); }
		public TerminalNode EQ() { return getToken(GenericSQLParser.EQ, 0); }
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			_la = _input.LA(1);
			if ( !(((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & ((1L << (EQ - 73)) | (1L << (NEQ1 - 73)) | (1L << (NEQ2 - 73)) | (1L << (LTE - 73)) | (1L << (LT - 73)) | (1L << (GTE - 73)) | (1L << (GT - 73)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class QuantifierContext extends ParserRuleContext {
		public TerminalNode SOME() { return getToken(GenericSQLParser.SOME, 0); }
		public TerminalNode ALL() { return getToken(GenericSQLParser.ALL, 0); }
		public SubSelectContext subSelect() {
			return getRuleContext(SubSelectContext.class,0);
		}
		public TerminalNode ANY() { return getToken(GenericSQLParser.ANY, 0); }
		public TerminalNode RPAREN() { return getToken(GenericSQLParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(GenericSQLParser.LPAREN, 0); }
		public QuantifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quantifier; }
	}

	public final QuantifierContext quantifier() throws RecognitionException {
		QuantifierContext _localctx = new QuantifierContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_quantifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411); expression();
			setState(412);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << ANY) | (1L << SOME))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			setState(413); match(LPAREN);
			setState(414); subSelect();
			setState(415); match(RPAREN);
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

	public static class ExpressionListContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(GenericSQLParser.COMMA); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode COMMA(int i) {
			return getToken(GenericSQLParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417); expression();
			setState(422);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(418); match(COMMA);
				setState(419); expression();
				}
				}
				setState(424);
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

	public static class NestedExpressionContext extends ParserRuleContext {
		public TerminalNode RPAREN() { return getToken(GenericSQLParser.RPAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(GenericSQLParser.LPAREN, 0); }
		public NestedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nestedExpression; }
	}

	public final NestedExpressionContext nestedExpression() throws RecognitionException {
		NestedExpressionContext _localctx = new NestedExpressionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_nestedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(425); match(LPAREN);
			setState(426); expression();
			setState(427); match(RPAREN);
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
		public TerminalNode MINUS(int i) {
			return getToken(GenericSQLParser.MINUS, i);
		}
		public List<MultiplyContext> multiply() {
			return getRuleContexts(MultiplyContext.class);
		}
		public List<TerminalNode> MINUS() { return getTokens(GenericSQLParser.MINUS); }
		public MultiplyContext multiply(int i) {
			return getRuleContext(MultiplyContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(GenericSQLParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(GenericSQLParser.PLUS, i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429); multiply();
			setState(434);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(430);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(431); multiply();
				}
				}
				setState(436);
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

	public static class MultiplyContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public TerminalNode DIVIDE(int i) {
			return getToken(GenericSQLParser.DIVIDE, i);
		}
		public List<TerminalNode> STAR() { return getTokens(GenericSQLParser.STAR); }
		public TerminalNode STAR(int i) {
			return getToken(GenericSQLParser.STAR, i);
		}
		public List<TerminalNode> DIVIDE() { return getTokens(GenericSQLParser.DIVIDE); }
		public MultiplyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiply; }
	}

	public final MultiplyContext multiply() throws RecognitionException {
		MultiplyContext _localctx = new MultiplyContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_multiply);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(437); value();
			setState(442);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DIVIDE || _la==STAR) {
				{
				{
				setState(438);
				_la = _input.LA(1);
				if ( !(_la==DIVIDE || _la==STAR) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(439); value();
				}
				}
				setState(444);
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

	public static class ValueContext extends ParserRuleContext {
		public NestedExpressionContext nestedExpression() {
			return getRuleContext(NestedExpressionContext.class,0);
		}
		public ColumnRefContext columnRef() {
			return getRuleContext(ColumnRefContext.class,0);
		}
		public CaseExpressionContext caseExpression() {
			return getRuleContext(CaseExpressionContext.class,0);
		}
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_value);
		int _la;
		try {
			setState(454);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(445); literal();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(446); caseExpression();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(448);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(447); unary();
					}
				}

				setState(452);
				switch (_input.LA(1)) {
				case Identifier:
					{
					setState(450); columnRef();
					}
					break;
				case LPAREN:
					{
					setState(451); nestedExpression();
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

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode String() { return getToken(GenericSQLParser.String, 0); }
		public TerminalNode Integer() { return getToken(GenericSQLParser.Integer, 0); }
		public TerminalNode TRUE() { return getToken(GenericSQLParser.TRUE, 0); }
		public TerminalNode Float() { return getToken(GenericSQLParser.Float, 0); }
		public UnaryContext unary() {
			return getRuleContext(UnaryContext.class,0);
		}
		public DateContext date() {
			return getRuleContext(DateContext.class,0);
		}
		public TerminalNode FALSE() { return getToken(GenericSQLParser.FALSE, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_literal);
		int _la;
		try {
			setState(468);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(457);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(456); unary();
					}
				}

				setState(459); match(Float);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(461);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(460); unary();
					}
				}

				setState(463); match(Integer);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(464); match(String);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(465); match(TRUE);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(466); match(FALSE);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(467); date();
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

	public static class DateContext extends ParserRuleContext {
		public TerminalNode Timestamp() { return getToken(GenericSQLParser.Timestamp, 0); }
		public DateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date; }
	}

	public final DateContext date() throws RecognitionException {
		DateContext _localctx = new DateContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_date);
		try {
			setState(479);
			switch (_input.LA(1)) {
			case 3:
				enterOuterAlt(_localctx, 1);
				{
				setState(470); match(3);
				setState(471); match(Timestamp);
				setState(472); match(RCURLY);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(473); match(2);
				setState(474); match(Timestamp);
				setState(475); match(RCURLY);
				}
				break;
			case 1:
				enterOuterAlt(_localctx, 3);
				{
				setState(476); match(1);
				setState(477); match(Timestamp);
				setState(478); match(RCURLY);
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

	public static class UnaryContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(GenericSQLParser.MINUS, 0); }
		public TerminalNode PLUS() { return getToken(GenericSQLParser.PLUS, 0); }
		public UnaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary; }
	}

	public final UnaryContext unary() throws RecognitionException {
		UnaryContext _localctx = new UnaryContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_unary);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(481);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class CaseExpressionContext extends ParserRuleContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> THEN() { return getTokens(GenericSQLParser.THEN); }
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public TerminalNode WHEN(int i) {
			return getToken(GenericSQLParser.WHEN, i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public TerminalNode THEN(int i) {
			return getToken(GenericSQLParser.THEN, i);
		}
		public TerminalNode ELSE() { return getToken(GenericSQLParser.ELSE, 0); }
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public TerminalNode CASE() { return getToken(GenericSQLParser.CASE, 0); }
		public List<TerminalNode> WHEN() { return getTokens(GenericSQLParser.WHEN); }
		public TerminalNode END() { return getToken(GenericSQLParser.END, 0); }
		public CaseExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseExpression; }
	}

	public final CaseExpressionContext caseExpression() throws RecognitionException {
		CaseExpressionContext _localctx = new CaseExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_caseExpression);
		int _la;
		try {
			setState(516);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(483); match(CASE);
				setState(489); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(484); match(WHEN);
					setState(485); condition();
					setState(486); match(THEN);
					setState(487); value();
					}
					}
					setState(491); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHEN );
				setState(495);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(493); match(ELSE);
					setState(494); value();
					}
				}

				setState(497); match(END);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(499); match(CASE);
				setState(500); value();
				setState(506); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(501); match(WHEN);
					setState(502); value();
					setState(503); match(THEN);
					setState(504); value();
					}
					}
					setState(508); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHEN );
				setState(512);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(510); match(ELSE);
					setState(511); value();
					}
				}

				setState(514); match(END);
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

	public static class TableRefContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(GenericSQLParser.DOT, 0); }
		public DatabaseNameContext databaseName() {
			return getRuleContext(DatabaseNameContext.class,0);
		}
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public TableRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableRef; }
	}

	public final TableRefContext tableRef() throws RecognitionException {
		TableRefContext _localctx = new TableRefContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_tableRef);
		try {
			setState(523);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(518); tableName();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(519); databaseName();
				setState(520); match(DOT);
				setState(521); tableName();
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

	public static class ColumnRefContext extends ParserRuleContext {
		public TerminalNode DOT() { return getToken(GenericSQLParser.DOT, 0); }
		public ColumnNameContext columnName() {
			return getRuleContext(ColumnNameContext.class,0);
		}
		public TableAliasContext tableAlias() {
			return getRuleContext(TableAliasContext.class,0);
		}
		public ColumnRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnRef; }
	}

	public final ColumnRefContext columnRef() throws RecognitionException {
		ColumnRefContext _localctx = new ColumnRefContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_columnRef);
		try {
			setState(530);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(525); columnName();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(526); tableAlias();
				setState(527); match(DOT);
				setState(528); columnName();
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

	public static class DatabaseNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(GenericSQLParser.Identifier, 0); }
		public DatabaseNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_databaseName; }
	}

	public final DatabaseNameContext databaseName() throws RecognitionException {
		DatabaseNameContext _localctx = new DatabaseNameContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_databaseName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(532); match(Identifier);
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

	public static class TableNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(GenericSQLParser.Identifier, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(534); match(Identifier);
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

	public static class TableAliasContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(GenericSQLParser.Identifier, 0); }
		public TableAliasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableAlias; }
	}

	public final TableAliasContext tableAlias() throws RecognitionException {
		TableAliasContext _localctx = new TableAliasContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_tableAlias);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(536); match(Identifier);
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

	public static class ColumnNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(GenericSQLParser.Identifier, 0); }
		public ColumnNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_columnName; }
	}

	public final ColumnNameContext columnName() throws RecognitionException {
		ColumnNameContext _localctx = new ColumnNameContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_columnName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538); match(Identifier);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3V\u021f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\5\2e\n\2\3\2\3\2\3"+
		"\2\3\2\5\2k\n\2\3\2\3\2\3\2\3\2\5\2q\n\2\3\2\3\2\5\2u\n\2\3\3\3\3\3\3"+
		"\3\3\3\3\5\3|\n\3\3\4\3\4\5\4\u0080\n\4\3\4\3\4\3\4\5\4\u0085\n\4\5\4"+
		"\u0087\n\4\3\4\3\4\5\4\u008b\n\4\3\4\3\4\7\4\u008f\n\4\f\4\16\4\u0092"+
		"\13\4\3\4\5\4\u0095\n\4\3\4\5\4\u0098\n\4\3\4\5\4\u009b\n\4\3\4\5\4\u009e"+
		"\n\4\3\5\3\5\3\5\5\5\u00a3\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00ad"+
		"\n\6\f\6\16\6\u00b0\13\6\3\6\5\6\u00b3\n\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\7\b\u00bd\n\b\f\b\16\b\u00c0\13\b\3\t\3\t\3\t\3\t\7\t\u00c6\n\t\f"+
		"\t\16\t\u00c9\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\n\u00d2\n\n\f\n\16\n"+
		"\u00d5\13\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13\u00dd\n\13\f\13\16\13\u00e0"+
		"\13\13\5\13\u00e2\n\13\3\f\3\f\5\f\u00e6\n\f\3\f\5\f\u00e9\n\f\3\f\3\f"+
		"\5\f\u00ed\n\f\3\f\5\f\u00f0\n\f\3\f\3\f\3\f\5\f\u00f5\n\f\3\f\5\f\u00f8"+
		"\n\f\5\f\u00fa\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\7\17\u0107\n\17\f\17\16\17\u010a\13\17\5\17\u010c\n\17\3\17\3\17\3\20"+
		"\3\20\3\20\3\20\7\20\u0114\n\20\f\20\16\20\u0117\13\20\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u011e\n\21\3\21\5\21\u0121\n\21\3\21\5\21\u0124\n\21\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\5\22\u0137\n\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\24\3\24\3\24\3\24\3\24\7\24\u0146\n\24\f\24\16\24\u0149\13\24\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\7\26\u0153\n\26\f\26\16\26\u0156\13"+
		"\26\3\27\3\27\5\27\u015a\n\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\7\31"+
		"\u0163\n\31\f\31\16\31\u0166\13\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\5\32\u0170\n\32\3\33\3\33\5\33\u0174\n\33\3\33\3\33\3\33\3\33\5"+
		"\33\u017a\n\33\3\33\3\33\3\34\3\34\5\34\u0180\n\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\5\35\u018a\n\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37"+
		"\5\37\u0193\n\37\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3#\3#\3#\7#\u01a7\n#\f#\16#\u01aa\13#\3$\3$\3$\3$\3%\3%\3%\7%\u01b3"+
		"\n%\f%\16%\u01b6\13%\3&\3&\3&\7&\u01bb\n&\f&\16&\u01be\13&\3\'\3\'\3\'"+
		"\5\'\u01c3\n\'\3\'\3\'\5\'\u01c7\n\'\5\'\u01c9\n\'\3(\5(\u01cc\n(\3(\3"+
		"(\5(\u01d0\n(\3(\3(\3(\3(\3(\5(\u01d7\n(\3)\3)\3)\3)\3)\3)\3)\3)\3)\5"+
		")\u01e2\n)\3*\3*\3+\3+\3+\3+\3+\3+\6+\u01ec\n+\r+\16+\u01ed\3+\3+\5+\u01f2"+
		"\n+\3+\3+\3+\3+\3+\3+\3+\3+\3+\6+\u01fd\n+\r+\16+\u01fe\3+\3+\5+\u0203"+
		"\n+\3+\3+\5+\u0207\n+\3,\3,\3,\3,\3,\5,\u020e\n,\3-\3-\3-\3-\3-\5-\u0215"+
		"\n-\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\61\2\2\62\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`\2\t\5\2\6\6"+
		"\20\20\61\61\4\2\n\n\17\17\4\2\7\7%%\3\2KQ\5\2\6\6\b\b,,\3\2RS\3\2TU\u0242"+
		"\2t\3\2\2\2\4{\3\2\2\2\6}\3\2\2\2\b\u009f\3\2\2\2\n\u00a6\3\2\2\2\f\u00b4"+
		"\3\2\2\2\16\u00b8\3\2\2\2\20\u00c1\3\2\2\2\22\u00cc\3\2\2\2\24\u00e1\3"+
		"\2\2\2\26\u00f9\3\2\2\2\30\u00fb\3\2\2\2\32\u00ff\3\2\2\2\34\u0101\3\2"+
		"\2\2\36\u010f\3\2\2\2 \u011d\3\2\2\2\"\u0136\3\2\2\2$\u013d\3\2\2\2&\u0140"+
		"\3\2\2\2(\u014a\3\2\2\2*\u014d\3\2\2\2,\u0157\3\2\2\2.\u015b\3\2\2\2\60"+
		"\u015f\3\2\2\2\62\u016f\3\2\2\2\64\u0171\3\2\2\2\66\u017d\3\2\2\28\u0186"+
		"\3\2\2\2:\u018d\3\2\2\2<\u0190\3\2\2\2>\u0197\3\2\2\2@\u019b\3\2\2\2B"+
		"\u019d\3\2\2\2D\u01a3\3\2\2\2F\u01ab\3\2\2\2H\u01af\3\2\2\2J\u01b7\3\2"+
		"\2\2L\u01c8\3\2\2\2N\u01d6\3\2\2\2P\u01e1\3\2\2\2R\u01e3\3\2\2\2T\u0206"+
		"\3\2\2\2V\u020d\3\2\2\2X\u0214\3\2\2\2Z\u0216\3\2\2\2\\\u0218\3\2\2\2"+
		"^\u021a\3\2\2\2`\u021c\3\2\2\2bd\5\6\4\2ce\7J\2\2dc\3\2\2\2de\3\2\2\2"+
		"ef\3\2\2\2fg\7\2\2\3gu\3\2\2\2hj\5\b\5\2ik\7J\2\2ji\3\2\2\2jk\3\2\2\2"+
		"kl\3\2\2\2lm\7\2\2\3mu\3\2\2\2np\5\n\6\2oq\7J\2\2po\3\2\2\2pq\3\2\2\2"+
		"qr\3\2\2\2rs\7\2\2\3su\3\2\2\2tb\3\2\2\2th\3\2\2\2tn\3\2\2\2u\3\3\2\2"+
		"\2v|\5\6\4\2wx\7C\2\2xy\5\6\4\2yz\7D\2\2z|\3\2\2\2{v\3\2\2\2{w\3\2\2\2"+
		"|\5\3\2\2\2}\177\7*\2\2~\u0080\t\2\2\2\177~\3\2\2\2\177\u0080\3\2\2\2"+
		"\u0080\u0086\3\2\2\2\u0081\u0082\7/\2\2\u0082\u0084\7:\2\2\u0083\u0085"+
		"\7(\2\2\u0084\u0083\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086"+
		"\u0081\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\5\24"+
		"\13\2\u0089\u008b\5\16\b\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u0090\5\36\20\2\u008d\u008f\5\"\22\2\u008e\u008d"+
		"\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0095\5$\23\2\u0094\u0093\3\2"+
		"\2\2\u0094\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0098\5&\24\2\u0097"+
		"\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3\2\2\2\u0099\u009b\5("+
		"\25\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2\2\2\u009c"+
		"\u009e\5*\26\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e\7\3\2\2\2"+
		"\u009f\u00a0\7\33\2\2\u00a0\u00a2\5\16\b\2\u00a1\u00a3\5\20\t\2\u00a2"+
		"\u00a1\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\5\22"+
		"\n\2\u00a5\t\3\2\2\2\u00a6\u00a7\7\62\2\2\u00a7\u00a8\5V,\2\u00a8\u00a9"+
		"\7+\2\2\u00a9\u00ae\5\f\7\2\u00aa\u00ab\7B\2\2\u00ab\u00ad\5\f\7\2\u00ac"+
		"\u00aa\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2"+
		"\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b3\5$\23\2\u00b2"+
		"\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\13\3\2\2\2\u00b4\u00b5\5`\61"+
		"\2\u00b5\u00b6\7K\2\2\u00b6\u00b7\5N(\2\u00b7\r\3\2\2\2\u00b8\u00b9\7"+
		"\34\2\2\u00b9\u00be\5V,\2\u00ba\u00bb\7B\2\2\u00bb\u00bd\5V,\2\u00bc\u00ba"+
		"\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\17\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7C\2\2\u00c2\u00c7\5`\61\2"+
		"\u00c3\u00c4\7B\2\2\u00c4\u00c6\5`\61\2\u00c5\u00c3\3\2\2\2\u00c6\u00c9"+
		"\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9"+
		"\u00c7\3\2\2\2\u00ca\u00cb\7D\2\2\u00cb\21\3\2\2\2\u00cc\u00cd\7\64\2"+
		"\2\u00cd\u00ce\7C\2\2\u00ce\u00d3\5N(\2\u00cf\u00d0\7B\2\2\u00d0\u00d2"+
		"\5N(\2\u00d1\u00cf\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d7\7D"+
		"\2\2\u00d7\23\3\2\2\2\u00d8\u00e2\7U\2\2\u00d9\u00de\5\26\f\2\u00da\u00db"+
		"\7B\2\2\u00db\u00dd\5\26\f\2\u00dc\u00da\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e1\u00d8\3\2\2\2\u00e1\u00d9\3\2\2\2\u00e2\25\3\2\2\2\u00e3\u00e8"+
		"\5\34\17\2\u00e4\u00e6\7\t\2\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2"+
		"\u00e6\u00e7\3\2\2\2\u00e7\u00e9\5\32\16\2\u00e8\u00e5\3\2\2\2\u00e8\u00e9"+
		"\3\2\2\2\u00e9\u00fa\3\2\2\2\u00ea\u00ef\5H%\2\u00eb\u00ed\7\t\2\2\u00ec"+
		"\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\5\32"+
		"\16\2\u00ef\u00ec\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00fa\3\2\2\2\u00f1"+
		"\u00fa\5\30\r\2\u00f2\u00f7\5T+\2\u00f3\u00f5\7\t\2\2\u00f4\u00f3\3\2"+
		"\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f8\5\32\16\2\u00f7"+
		"\u00f4\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00e3\3\2"+
		"\2\2\u00f9\u00ea\3\2\2\2\u00f9\u00f1\3\2\2\2\u00f9\u00f2\3\2\2\2\u00fa"+
		"\27\3\2\2\2\u00fb\u00fc\5^\60\2\u00fc\u00fd\7A\2\2\u00fd\u00fe\7U\2\2"+
		"\u00fe\31\3\2\2\2\u00ff\u0100\7>\2\2\u0100\33\3\2\2\2\u0101\u0102\7>\2"+
		"\2\u0102\u010b\7C\2\2\u0103\u0108\5H%\2\u0104\u0105\7B\2\2\u0105\u0107"+
		"\5H%\2\u0106\u0104\3\2\2\2\u0107\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010b\u0103\3\2"+
		"\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\7D\2\2\u010e"+
		"\35\3\2\2\2\u010f\u0110\7\25\2\2\u0110\u0115\5 \21\2\u0111\u0112\7B\2"+
		"\2\u0112\u0114\5 \21\2\u0113\u0111\3\2\2\2\u0114\u0117\3\2\2\2\u0115\u0113"+
		"\3\2\2\2\u0115\u0116\3\2\2\2\u0116\37\3\2\2\2\u0117\u0115\3\2\2\2\u0118"+
		"\u0119\7C\2\2\u0119\u011a\5\4\3\2\u011a\u011b\7D\2\2\u011b\u011e\3\2\2"+
		"\2\u011c\u011e\5V,\2\u011d\u0118\3\2\2\2\u011d\u011c\3\2\2\2\u011e\u0123"+
		"\3\2\2\2\u011f\u0121\7\t\2\2\u0120\u011f\3\2\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\u0122\3\2\2\2\u0122\u0124\5\32\16\2\u0123\u0120\3\2\2\2\u0123\u0124\3"+
		"\2\2\2\u0124!\3\2\2\2\u0125\u0137\7\36\2\2\u0126\u0127\7\32\2\2\u0127"+
		"\u0137\7\36\2\2\u0128\u0129\7\37\2\2\u0129\u0137\7\36\2\2\u012a\u012b"+
		"\7\37\2\2\u012b\u012c\7\'\2\2\u012c\u0137\7\36\2\2\u012d\u012e\7)\2\2"+
		"\u012e\u0137\7\36\2\2\u012f\u0130\7)\2\2\u0130\u0131\7\'\2\2\u0131\u0137"+
		"\7\36\2\2\u0132\u0133\7\'\2\2\u0133\u0137\7\36\2\2\u0134\u0135\7!\2\2"+
		"\u0135\u0137\7\36\2\2\u0136\u0125\3\2\2\2\u0136\u0126\3\2\2\2\u0136\u0128"+
		"\3\2\2\2\u0136\u012a\3\2\2\2\u0136\u012d\3\2\2\2\u0136\u012f\3\2\2\2\u0136"+
		"\u0132\3\2\2\2\u0136\u0134\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\5 "+
		"\21\2\u0139\u013a\5\32\16\2\u013a\u013b\7$\2\2\u013b\u013c\5\60\31\2\u013c"+
		"#\3\2\2\2\u013d\u013e\7\66\2\2\u013e\u013f\5\60\31\2\u013f%\3\2\2\2\u0140"+
		"\u0141\7\27\2\2\u0141\u0142\7\f\2\2\u0142\u0147\5X-\2\u0143\u0144\7B\2"+
		"\2\u0144\u0146\5X-\2\u0145\u0143\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145"+
		"\3\2\2\2\u0147\u0148\3\2\2\2\u0148\'\3\2\2\2\u0149\u0147\3\2\2\2\u014a"+
		"\u014b\7\30\2\2\u014b\u014c\5\60\31\2\u014c)\3\2\2\2\u014d\u014e\7&\2"+
		"\2\u014e\u014f\7\f\2\2\u014f\u0154\5,\27\2\u0150\u0151\7B\2\2\u0151\u0153"+
		"\5,\27\2\u0152\u0150\3\2\2\2\u0153\u0156\3\2\2\2\u0154\u0152\3\2\2\2\u0154"+
		"\u0155\3\2\2\2\u0155+\3\2\2\2\u0156\u0154\3\2\2\2\u0157\u0159\5X-\2\u0158"+
		"\u015a\t\3\2\2\u0159\u0158\3\2\2\2\u0159\u015a\3\2\2\2\u015a-\3\2\2\2"+
		"\u015b\u015c\7C\2\2\u015c\u015d\5\60\31\2\u015d\u015e\7D\2\2\u015e/\3"+
		"\2\2\2\u015f\u0164\5\62\32\2\u0160\u0161\t\4\2\2\u0161\u0163\5\62\32\2"+
		"\u0162\u0160\3\2\2\2\u0163\u0166\3\2\2\2\u0164\u0162\3\2\2\2\u0164\u0165"+
		"\3\2\2\2\u0165\61\3\2\2\2\u0166\u0164\3\2\2\2\u0167\u0170\5> \2\u0168"+
		"\u0170\5.\30\2\u0169\u0170\5\64\33\2\u016a\u0170\5\66\34\2\u016b\u0170"+
		"\58\35\2\u016c\u0170\5:\36\2\u016d\u0170\5<\37\2\u016e\u0170\5B\"\2\u016f"+
		"\u0167\3\2\2\2\u016f\u0168\3\2\2\2\u016f\u0169\3\2\2\2\u016f\u016a\3\2"+
		"\2\2\u016f\u016b\3\2\2\2\u016f\u016c\3\2\2\2\u016f\u016d\3\2\2\2\u016f"+
		"\u016e\3\2\2\2\u0170\63\3\2\2\2\u0171\u0173\5H%\2\u0172\u0174\7\"\2\2"+
		"\u0173\u0172\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0176"+
		"\7\31\2\2\u0176\u0179\7C\2\2\u0177\u017a\5\4\3\2\u0178\u017a\5D#\2\u0179"+
		"\u0177\3\2\2\2\u0179\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017c\7D"+
		"\2\2\u017c\65\3\2\2\2\u017d\u017f\5H%\2\u017e\u0180\7\"\2\2\u017f\u017e"+
		"\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0182\7\13\2\2"+
		"\u0182\u0183\5H%\2\u0183\u0184\7\7\2\2\u0184\u0185\5H%\2\u0185\67\3\2"+
		"\2\2\u0186\u0187\5H%\2\u0187\u0189\7\35\2\2\u0188\u018a\7\"\2\2\u0189"+
		"\u0188\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\7#"+
		"\2\2\u018c9\3\2\2\2\u018d\u018e\7\23\2\2\u018e\u018f\5H%\2\u018f;\3\2"+
		"\2\2\u0190\u0192\5H%\2\u0191\u0193\7\"\2\2\u0192\u0191\3\2\2\2\u0192\u0193"+
		"\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0195\7 \2\2\u0195\u0196\5H%\2\u0196"+
		"=\3\2\2\2\u0197\u0198\5H%\2\u0198\u0199\5@!\2\u0199\u019a\5H%\2\u019a"+
		"?\3\2\2\2\u019b\u019c\t\5\2\2\u019cA\3\2\2\2\u019d\u019e\5H%\2\u019e\u019f"+
		"\t\6\2\2\u019f\u01a0\7C\2\2\u01a0\u01a1\5\4\3\2\u01a1\u01a2\7D\2\2\u01a2"+
		"C\3\2\2\2\u01a3\u01a8\5H%\2\u01a4\u01a5\7B\2\2\u01a5\u01a7\5H%\2\u01a6"+
		"\u01a4\3\2\2\2\u01a7\u01aa\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a8\u01a9\3\2"+
		"\2\2\u01a9E\3\2\2\2\u01aa\u01a8\3\2\2\2\u01ab\u01ac\7C\2\2\u01ac\u01ad"+
		"\5H%\2\u01ad\u01ae\7D\2\2\u01aeG\3\2\2\2\u01af\u01b4\5J&\2\u01b0\u01b1"+
		"\t\7\2\2\u01b1\u01b3\5J&\2\u01b2\u01b0\3\2\2\2\u01b3\u01b6\3\2\2\2\u01b4"+
		"\u01b2\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5I\3\2\2\2\u01b6\u01b4\3\2\2\2"+
		"\u01b7\u01bc\5L\'\2\u01b8\u01b9\t\b\2\2\u01b9\u01bb\5L\'\2\u01ba\u01b8"+
		"\3\2\2\2\u01bb\u01be\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd"+
		"K\3\2\2\2\u01be\u01bc\3\2\2\2\u01bf\u01c9\5N(\2\u01c0\u01c9\5T+\2\u01c1"+
		"\u01c3\5R*\2\u01c2\u01c1\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c6\3\2\2"+
		"\2\u01c4\u01c7\5X-\2\u01c5\u01c7\5F$\2\u01c6\u01c4\3\2\2\2\u01c6\u01c5"+
		"\3\2\2\2\u01c7\u01c9\3\2\2\2\u01c8\u01bf\3\2\2\2\u01c8\u01c0\3\2\2\2\u01c8"+
		"\u01c2\3\2\2\2\u01c9M\3\2\2\2\u01ca\u01cc\5R*\2\u01cb\u01ca\3\2\2\2\u01cb"+
		"\u01cc\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01d7\7;\2\2\u01ce\u01d0\5R*"+
		"\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01d7"+
		"\7:\2\2\u01d2\u01d7\7<\2\2\u01d3\u01d7\7.\2\2\u01d4\u01d7\7\24\2\2\u01d5"+
		"\u01d7\5P)\2\u01d6\u01cb\3\2\2\2\u01d6\u01cf\3\2\2\2\u01d6\u01d2\3\2\2"+
		"\2\u01d6\u01d3\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d6\u01d5\3\2\2\2\u01d7O"+
		"\3\2\2\2\u01d8\u01d9\7\5\2\2\u01d9\u01da\7=\2\2\u01da\u01e2\7F\2\2\u01db"+
		"\u01dc\7\4\2\2\u01dc\u01dd\7=\2\2\u01dd\u01e2\7F\2\2\u01de\u01df\7\3\2"+
		"\2\u01df\u01e0\7=\2\2\u01e0\u01e2\7F\2\2\u01e1\u01d8\3\2\2\2\u01e1\u01db"+
		"\3\2\2\2\u01e1\u01de\3\2\2\2\u01e2Q\3\2\2\2\u01e3\u01e4\t\7\2\2\u01e4"+
		"S\3\2\2\2\u01e5\u01eb\7\r\2\2\u01e6\u01e7\7\65\2\2\u01e7\u01e8\5\62\32"+
		"\2\u01e8\u01e9\7-\2\2\u01e9\u01ea\5L\'\2\u01ea\u01ec\3\2\2\2\u01eb\u01e6"+
		"\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee"+
		"\u01f1\3\2\2\2\u01ef\u01f0\7\21\2\2\u01f0\u01f2\5L\'\2\u01f1\u01ef\3\2"+
		"\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f4\7\22\2\2\u01f4"+
		"\u0207\3\2\2\2\u01f5\u01f6\7\r\2\2\u01f6\u01fc\5L\'\2\u01f7\u01f8\7\65"+
		"\2\2\u01f8\u01f9\5L\'\2\u01f9\u01fa\7-\2\2\u01fa\u01fb\5L\'\2\u01fb\u01fd"+
		"\3\2\2\2\u01fc\u01f7\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01fc\3\2\2\2\u01fe"+
		"\u01ff\3\2\2\2\u01ff\u0202\3\2\2\2\u0200\u0201\7\21\2\2\u0201\u0203\5"+
		"L\'\2\u0202\u0200\3\2\2\2\u0202\u0203\3\2\2\2\u0203\u0204\3\2\2\2\u0204"+
		"\u0205\7\22\2\2\u0205\u0207\3\2\2\2\u0206\u01e5\3\2\2\2\u0206\u01f5\3"+
		"\2\2\2\u0207U\3\2\2\2\u0208\u020e\5\\/\2\u0209\u020a\5Z.\2\u020a\u020b"+
		"\7A\2\2\u020b\u020c\5\\/\2\u020c\u020e\3\2\2\2\u020d\u0208\3\2\2\2\u020d"+
		"\u0209\3\2\2\2\u020eW\3\2\2\2\u020f\u0215\5`\61\2\u0210\u0211\5^\60\2"+
		"\u0211\u0212\7A\2\2\u0212\u0213\5`\61\2\u0213\u0215\3\2\2\2\u0214\u020f"+
		"\3\2\2\2\u0214\u0210\3\2\2\2\u0215Y\3\2\2\2\u0216\u0217\7>\2\2\u0217["+
		"\3\2\2\2\u0218\u0219\7>\2\2\u0219]\3\2\2\2\u021a\u021b\7>\2\2\u021b_\3"+
		"\2\2\2\u021c\u021d\7>\2\2\u021da\3\2\2\2Adjpt{\177\u0084\u0086\u008a\u0090"+
		"\u0094\u0097\u009a\u009d\u00a2\u00ae\u00b2\u00be\u00c7\u00d3\u00de\u00e1"+
		"\u00e5\u00e8\u00ec\u00ef\u00f4\u00f7\u00f9\u0108\u010b\u0115\u011d\u0120"+
		"\u0123\u0136\u0147\u0154\u0159\u0164\u016f\u0173\u0179\u017f\u0189\u0192"+
		"\u01a8\u01b4\u01bc\u01c2\u01c6\u01c8\u01cb\u01cf\u01d6\u01e1\u01ed\u01f1"+
		"\u01fe\u0202\u0206\u020d\u0214";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}