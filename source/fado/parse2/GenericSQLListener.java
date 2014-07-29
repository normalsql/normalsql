// Generated from /Users/jasonosgood/Projects/fado/source/fado/parse2/GenericSQL.g4 by ANTLR 4.x
package fado.parse2;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GenericSQLParser}.
 */
public interface GenericSQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(@NotNull GenericSQLParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(@NotNull GenericSQLParser.ExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#update}.
	 * @param ctx the parse tree
	 */
	void enterUpdate(@NotNull GenericSQLParser.UpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#update}.
	 * @param ctx the parse tree
	 */
	void exitUpdate(@NotNull GenericSQLParser.UpdateContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#orderByItem}.
	 * @param ctx the parse tree
	 */
	void enterOrderByItem(@NotNull GenericSQLParser.OrderByItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#orderByItem}.
	 * @param ctx the parse tree
	 */
	void exitOrderByItem(@NotNull GenericSQLParser.OrderByItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#nestedCondition}.
	 * @param ctx the parse tree
	 */
	void enterNestedCondition(@NotNull GenericSQLParser.NestedConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#nestedCondition}.
	 * @param ctx the parse tree
	 */
	void exitNestedCondition(@NotNull GenericSQLParser.NestedConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#isNull}.
	 * @param ctx the parse tree
	 */
	void enterIsNull(@NotNull GenericSQLParser.IsNullContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#isNull}.
	 * @param ctx the parse tree
	 */
	void exitIsNull(@NotNull GenericSQLParser.IsNullContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#orderBy}.
	 * @param ctx the parse tree
	 */
	void enterOrderBy(@NotNull GenericSQLParser.OrderByContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#orderBy}.
	 * @param ctx the parse tree
	 */
	void exitOrderBy(@NotNull GenericSQLParser.OrderByContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#allColumns}.
	 * @param ctx the parse tree
	 */
	void enterAllColumns(@NotNull GenericSQLParser.AllColumnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#allColumns}.
	 * @param ctx the parse tree
	 */
	void exitAllColumns(@NotNull GenericSQLParser.AllColumnsContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(@NotNull GenericSQLParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(@NotNull GenericSQLParser.ExpressionListContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#subSelect}.
	 * @param ctx the parse tree
	 */
	void enterSubSelect(@NotNull GenericSQLParser.SubSelectContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#subSelect}.
	 * @param ctx the parse tree
	 */
	void exitSubSelect(@NotNull GenericSQLParser.SubSelectContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#conditionList}.
	 * @param ctx the parse tree
	 */
	void enterConditionList(@NotNull GenericSQLParser.ConditionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#conditionList}.
	 * @param ctx the parse tree
	 */
	void exitConditionList(@NotNull GenericSQLParser.ConditionListContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#from}.
	 * @param ctx the parse tree
	 */
	void enterFrom(@NotNull GenericSQLParser.FromContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#from}.
	 * @param ctx the parse tree
	 */
	void exitFrom(@NotNull GenericSQLParser.FromContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#date}.
	 * @param ctx the parse tree
	 */
	void enterDate(@NotNull GenericSQLParser.DateContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#date}.
	 * @param ctx the parse tree
	 */
	void exitDate(@NotNull GenericSQLParser.DateContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(@NotNull GenericSQLParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(@NotNull GenericSQLParser.WhereContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#columnRef}.
	 * @param ctx the parse tree
	 */
	void enterColumnRef(@NotNull GenericSQLParser.ColumnRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#columnRef}.
	 * @param ctx the parse tree
	 */
	void exitColumnRef(@NotNull GenericSQLParser.ColumnRefContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(@NotNull GenericSQLParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(@NotNull GenericSQLParser.FunctionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(@NotNull GenericSQLParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(@NotNull GenericSQLParser.UnaryContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier(@NotNull GenericSQLParser.QuantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier(@NotNull GenericSQLParser.QuantifierContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#values}.
	 * @param ctx the parse tree
	 */
	void enterValues(@NotNull GenericSQLParser.ValuesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#values}.
	 * @param ctx the parse tree
	 */
	void exitValues(@NotNull GenericSQLParser.ValuesContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(@NotNull GenericSQLParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(@NotNull GenericSQLParser.CaseExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#nestedExpression}.
	 * @param ctx the parse tree
	 */
	void enterNestedExpression(@NotNull GenericSQLParser.NestedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#nestedExpression}.
	 * @param ctx the parse tree
	 */
	void exitNestedExpression(@NotNull GenericSQLParser.NestedExpressionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void enterTableName(@NotNull GenericSQLParser.TableNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#tableName}.
	 * @param ctx the parse tree
	 */
	void exitTableName(@NotNull GenericSQLParser.TableNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull GenericSQLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull GenericSQLParser.ValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#columnName}.
	 * @param ctx the parse tree
	 */
	void enterColumnName(@NotNull GenericSQLParser.ColumnNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#columnName}.
	 * @param ctx the parse tree
	 */
	void exitColumnName(@NotNull GenericSQLParser.ColumnNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#setter}.
	 * @param ctx the parse tree
	 */
	void enterSetter(@NotNull GenericSQLParser.SetterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#setter}.
	 * @param ctx the parse tree
	 */
	void exitSetter(@NotNull GenericSQLParser.SetterContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#between}.
	 * @param ctx the parse tree
	 */
	void enterBetween(@NotNull GenericSQLParser.BetweenContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#between}.
	 * @param ctx the parse tree
	 */
	void exitBetween(@NotNull GenericSQLParser.BetweenContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(@NotNull GenericSQLParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(@NotNull GenericSQLParser.AliasContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull GenericSQLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull GenericSQLParser.ConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#select}.
	 * @param ctx the parse tree
	 */
	void enterSelect(@NotNull GenericSQLParser.SelectContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#select}.
	 * @param ctx the parse tree
	 */
	void exitSelect(@NotNull GenericSQLParser.SelectContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#into}.
	 * @param ctx the parse tree
	 */
	void enterInto(@NotNull GenericSQLParser.IntoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#into}.
	 * @param ctx the parse tree
	 */
	void exitInto(@NotNull GenericSQLParser.IntoContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#tableRef}.
	 * @param ctx the parse tree
	 */
	void enterTableRef(@NotNull GenericSQLParser.TableRefContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#tableRef}.
	 * @param ctx the parse tree
	 */
	void exitTableRef(@NotNull GenericSQLParser.TableRefContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#multiply}.
	 * @param ctx the parse tree
	 */
	void enterMultiply(@NotNull GenericSQLParser.MultiplyContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#multiply}.
	 * @param ctx the parse tree
	 */
	void exitMultiply(@NotNull GenericSQLParser.MultiplyContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#comparator}.
	 * @param ctx the parse tree
	 */
	void enterComparator(@NotNull GenericSQLParser.ComparatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#comparator}.
	 * @param ctx the parse tree
	 */
	void exitComparator(@NotNull GenericSQLParser.ComparatorContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#join}.
	 * @param ctx the parse tree
	 */
	void enterJoin(@NotNull GenericSQLParser.JoinContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#join}.
	 * @param ctx the parse tree
	 */
	void exitJoin(@NotNull GenericSQLParser.JoinContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#like}.
	 * @param ctx the parse tree
	 */
	void enterLike(@NotNull GenericSQLParser.LikeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#like}.
	 * @param ctx the parse tree
	 */
	void exitLike(@NotNull GenericSQLParser.LikeContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#in}.
	 * @param ctx the parse tree
	 */
	void enterIn(@NotNull GenericSQLParser.InContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#in}.
	 * @param ctx the parse tree
	 */
	void exitIn(@NotNull GenericSQLParser.InContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#columnList}.
	 * @param ctx the parse tree
	 */
	void enterColumnList(@NotNull GenericSQLParser.ColumnListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#columnList}.
	 * @param ctx the parse tree
	 */
	void exitColumnList(@NotNull GenericSQLParser.ColumnListContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(@NotNull GenericSQLParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(@NotNull GenericSQLParser.ComparisonContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(@NotNull GenericSQLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(@NotNull GenericSQLParser.StatementContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void enterGroupBy(@NotNull GenericSQLParser.GroupByContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void exitGroupBy(@NotNull GenericSQLParser.GroupByContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void enterDatabaseName(@NotNull GenericSQLParser.DatabaseNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#databaseName}.
	 * @param ctx the parse tree
	 */
	void exitDatabaseName(@NotNull GenericSQLParser.DatabaseNameContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#item}.
	 * @param ctx the parse tree
	 */
	void enterItem(@NotNull GenericSQLParser.ItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#item}.
	 * @param ctx the parse tree
	 */
	void exitItem(@NotNull GenericSQLParser.ItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#itemList}.
	 * @param ctx the parse tree
	 */
	void enterItemList(@NotNull GenericSQLParser.ItemListContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#itemList}.
	 * @param ctx the parse tree
	 */
	void exitItemList(@NotNull GenericSQLParser.ItemListContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#exists}.
	 * @param ctx the parse tree
	 */
	void enterExists(@NotNull GenericSQLParser.ExistsContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#exists}.
	 * @param ctx the parse tree
	 */
	void exitExists(@NotNull GenericSQLParser.ExistsContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#tableAlias}.
	 * @param ctx the parse tree
	 */
	void enterTableAlias(@NotNull GenericSQLParser.TableAliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#tableAlias}.
	 * @param ctx the parse tree
	 */
	void exitTableAlias(@NotNull GenericSQLParser.TableAliasContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#having}.
	 * @param ctx the parse tree
	 */
	void enterHaving(@NotNull GenericSQLParser.HavingContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#having}.
	 * @param ctx the parse tree
	 */
	void exitHaving(@NotNull GenericSQLParser.HavingContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#insert}.
	 * @param ctx the parse tree
	 */
	void enterInsert(@NotNull GenericSQLParser.InsertContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#insert}.
	 * @param ctx the parse tree
	 */
	void exitInsert(@NotNull GenericSQLParser.InsertContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#fromItem}.
	 * @param ctx the parse tree
	 */
	void enterFromItem(@NotNull GenericSQLParser.FromItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#fromItem}.
	 * @param ctx the parse tree
	 */
	void exitFromItem(@NotNull GenericSQLParser.FromItemContext ctx);

	/**
	 * Enter a parse tree produced by {@link GenericSQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull GenericSQLParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link GenericSQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull GenericSQLParser.LiteralContext ctx);
}