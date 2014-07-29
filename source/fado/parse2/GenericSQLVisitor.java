// Generated from /Users/jasonosgood/Projects/fado/source/fado/parse2/GenericSQL.g4 by ANTLR 4.x
package fado.parse2;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GenericSQLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GenericSQLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull GenericSQLParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdate(@NotNull GenericSQLParser.UpdateContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#orderByItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderByItem(@NotNull GenericSQLParser.OrderByItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#nestedCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedCondition(@NotNull GenericSQLParser.NestedConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#isNull}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsNull(@NotNull GenericSQLParser.IsNullContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#orderBy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrderBy(@NotNull GenericSQLParser.OrderByContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#allColumns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAllColumns(@NotNull GenericSQLParser.AllColumnsContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(@NotNull GenericSQLParser.ExpressionListContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#subSelect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubSelect(@NotNull GenericSQLParser.SubSelectContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#conditionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionList(@NotNull GenericSQLParser.ConditionListContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#from}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrom(@NotNull GenericSQLParser.FromContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate(@NotNull GenericSQLParser.DateContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#where}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere(@NotNull GenericSQLParser.WhereContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#columnRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnRef(@NotNull GenericSQLParser.ColumnRefContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(@NotNull GenericSQLParser.FunctionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(@NotNull GenericSQLParser.UnaryContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#quantifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuantifier(@NotNull GenericSQLParser.QuantifierContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#values}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValues(@NotNull GenericSQLParser.ValuesContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#caseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpression(@NotNull GenericSQLParser.CaseExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#nestedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNestedExpression(@NotNull GenericSQLParser.NestedExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#tableName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableName(@NotNull GenericSQLParser.TableNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(@NotNull GenericSQLParser.ValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#columnName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnName(@NotNull GenericSQLParser.ColumnNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#setter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetter(@NotNull GenericSQLParser.SetterContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#between}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBetween(@NotNull GenericSQLParser.BetweenContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlias(@NotNull GenericSQLParser.AliasContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#condition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondition(@NotNull GenericSQLParser.ConditionContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelect(@NotNull GenericSQLParser.SelectContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#into}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInto(@NotNull GenericSQLParser.IntoContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#tableRef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableRef(@NotNull GenericSQLParser.TableRefContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#multiply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiply(@NotNull GenericSQLParser.MultiplyContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#comparator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparator(@NotNull GenericSQLParser.ComparatorContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#join}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoin(@NotNull GenericSQLParser.JoinContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#like}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLike(@NotNull GenericSQLParser.LikeContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIn(@NotNull GenericSQLParser.InContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#columnList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitColumnList(@NotNull GenericSQLParser.ColumnListContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(@NotNull GenericSQLParser.ComparisonContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull GenericSQLParser.StatementContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#groupBy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGroupBy(@NotNull GenericSQLParser.GroupByContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#databaseName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatabaseName(@NotNull GenericSQLParser.DatabaseNameContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#item}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItem(@NotNull GenericSQLParser.ItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#itemList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItemList(@NotNull GenericSQLParser.ItemListContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#exists}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExists(@NotNull GenericSQLParser.ExistsContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#tableAlias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTableAlias(@NotNull GenericSQLParser.TableAliasContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#having}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHaving(@NotNull GenericSQLParser.HavingContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#insert}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInsert(@NotNull GenericSQLParser.InsertContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#fromItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFromItem(@NotNull GenericSQLParser.FromItemContext ctx);

	/**
	 * Visit a parse tree produced by {@link GenericSQLParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(@NotNull GenericSQLParser.LiteralContext ctx);
}