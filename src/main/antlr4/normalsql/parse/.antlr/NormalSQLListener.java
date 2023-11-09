// Generated from /Users/jasonosgood/Projects/normalsql/src/main/antlr4/normalsql/parse/NormalSQL.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NormalSQLParser}.
 */
public interface NormalSQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(NormalSQLParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(NormalSQLParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(NormalSQLParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(NormalSQLParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#explain}.
	 * @param ctx the parse tree
	 */
	void enterExplain(NormalSQLParser.ExplainContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#explain}.
	 * @param ctx the parse tree
	 */
	void exitExplain(NormalSQLParser.ExplainContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(NormalSQLParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(NormalSQLParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#alter}.
	 * @param ctx the parse tree
	 */
	void enterAlter(NormalSQLParser.AlterContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#alter}.
	 * @param ctx the parse tree
	 */
	void exitAlter(NormalSQLParser.AlterContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(NormalSQLParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(NormalSQLParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#drop}.
	 * @param ctx the parse tree
	 */
	void enterDrop(NormalSQLParser.DropContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#drop}.
	 * @param ctx the parse tree
	 */
	void exitDrop(NormalSQLParser.DropContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#dropsicle}.
	 * @param ctx the parse tree
	 */
	void enterDropsicle(NormalSQLParser.DropsicleContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#dropsicle}.
	 * @param ctx the parse tree
	 */
	void exitDropsicle(NormalSQLParser.DropsicleContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#createTable}.
	 * @param ctx the parse tree
	 */
	void enterCreateTable(NormalSQLParser.CreateTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#createTable}.
	 * @param ctx the parse tree
	 */
	void exitCreateTable(NormalSQLParser.CreateTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#createVirtualTable}.
	 * @param ctx the parse tree
	 */
	void enterCreateVirtualTable(NormalSQLParser.CreateVirtualTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#createVirtualTable}.
	 * @param ctx the parse tree
	 */
	void exitCreateVirtualTable(NormalSQLParser.CreateVirtualTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#moduleArgument}.
	 * @param ctx the parse tree
	 */
	void enterModuleArgument(NormalSQLParser.ModuleArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#moduleArgument}.
	 * @param ctx the parse tree
	 */
	void exitModuleArgument(NormalSQLParser.ModuleArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#createTrigger}.
	 * @param ctx the parse tree
	 */
	void enterCreateTrigger(NormalSQLParser.CreateTriggerContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#createTrigger}.
	 * @param ctx the parse tree
	 */
	void exitCreateTrigger(NormalSQLParser.CreateTriggerContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#temporary}.
	 * @param ctx the parse tree
	 */
	void enterTemporary(NormalSQLParser.TemporaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#temporary}.
	 * @param ctx the parse tree
	 */
	void exitTemporary(NormalSQLParser.TemporaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#createView}.
	 * @param ctx the parse tree
	 */
	void enterCreateView(NormalSQLParser.CreateViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#createView}.
	 * @param ctx the parse tree
	 */
	void exitCreateView(NormalSQLParser.CreateViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(NormalSQLParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(NormalSQLParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#indexedColumn}.
	 * @param ctx the parse tree
	 */
	void enterIndexedColumn(NormalSQLParser.IndexedColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#indexedColumn}.
	 * @param ctx the parse tree
	 */
	void exitIndexedColumn(NormalSQLParser.IndexedColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#indexedColumns}.
	 * @param ctx the parse tree
	 */
	void enterIndexedColumns(NormalSQLParser.IndexedColumnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#indexedColumns}.
	 * @param ctx the parse tree
	 */
	void exitIndexedColumns(NormalSQLParser.IndexedColumnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#ifNotExists}.
	 * @param ctx the parse tree
	 */
	void enterIfNotExists(NormalSQLParser.IfNotExistsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#ifNotExists}.
	 * @param ctx the parse tree
	 */
	void exitIfNotExists(NormalSQLParser.IfNotExistsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#columnDef}.
	 * @param ctx the parse tree
	 */
	void enterColumnDef(NormalSQLParser.ColumnDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#columnDef}.
	 * @param ctx the parse tree
	 */
	void exitColumnDef(NormalSQLParser.ColumnDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#columnStuff}.
	 * @param ctx the parse tree
	 */
	void enterColumnStuff(NormalSQLParser.ColumnStuffContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#columnStuff}.
	 * @param ctx the parse tree
	 */
	void exitColumnStuff(NormalSQLParser.ColumnStuffContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#tableStuff}.
	 * @param ctx the parse tree
	 */
	void enterTableStuff(NormalSQLParser.TableStuffContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#tableStuff}.
	 * @param ctx the parse tree
	 */
	void exitTableStuff(NormalSQLParser.TableStuffContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#onConflict}.
	 * @param ctx the parse tree
	 */
	void enterOnConflict(NormalSQLParser.OnConflictContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#onConflict}.
	 * @param ctx the parse tree
	 */
	void exitOnConflict(NormalSQLParser.OnConflictContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#foreignKey}.
	 * @param ctx the parse tree
	 */
	void enterForeignKey(NormalSQLParser.ForeignKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#foreignKey}.
	 * @param ctx the parse tree
	 */
	void exitForeignKey(NormalSQLParser.ForeignKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#with}.
	 * @param ctx the parse tree
	 */
	void enterWith(NormalSQLParser.WithContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#with}.
	 * @param ctx the parse tree
	 */
	void exitWith(NormalSQLParser.WithContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#cte}.
	 * @param ctx the parse tree
	 */
	void enterCte(NormalSQLParser.CteContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#cte}.
	 * @param ctx the parse tree
	 */
	void exitCte(NormalSQLParser.CteContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(NormalSQLParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(NormalSQLParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#insert}.
	 * @param ctx the parse tree
	 */
	void enterInsert(NormalSQLParser.InsertContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#insert}.
	 * @param ctx the parse tree
	 */
	void exitInsert(NormalSQLParser.InsertContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#overriding}.
	 * @param ctx the parse tree
	 */
	void enterOverriding(NormalSQLParser.OverridingContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#overriding}.
	 * @param ctx the parse tree
	 */
	void exitOverriding(NormalSQLParser.OverridingContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#upsert}.
	 * @param ctx the parse tree
	 */
	void enterUpsert(NormalSQLParser.UpsertContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#upsert}.
	 * @param ctx the parse tree
	 */
	void exitUpsert(NormalSQLParser.UpsertContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#merge}.
	 * @param ctx the parse tree
	 */
	void enterMerge(NormalSQLParser.MergeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#merge}.
	 * @param ctx the parse tree
	 */
	void exitMerge(NormalSQLParser.MergeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#when}.
	 * @param ctx the parse tree
	 */
	void enterWhen(NormalSQLParser.WhenContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#when}.
	 * @param ctx the parse tree
	 */
	void exitWhen(NormalSQLParser.WhenContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#update}.
	 * @param ctx the parse tree
	 */
	void enterUpdate(NormalSQLParser.UpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#update}.
	 * @param ctx the parse tree
	 */
	void exitUpdate(NormalSQLParser.UpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#setter}.
	 * @param ctx the parse tree
	 */
	void enterSetter(NormalSQLParser.SetterContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#setter}.
	 * @param ctx the parse tree
	 */
	void exitSetter(NormalSQLParser.SetterContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#afirr}.
	 * @param ctx the parse tree
	 */
	void enterAfirr(NormalSQLParser.AfirrContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#afirr}.
	 * @param ctx the parse tree
	 */
	void exitAfirr(NormalSQLParser.AfirrContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#indexedBy}.
	 * @param ctx the parse tree
	 */
	void enterIndexedBy(NormalSQLParser.IndexedByContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#indexedBy}.
	 * @param ctx the parse tree
	 */
	void exitIndexedBy(NormalSQLParser.IndexedByContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#returning}.
	 * @param ctx the parse tree
	 */
	void enterReturning(NormalSQLParser.ReturningContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#returning}.
	 * @param ctx the parse tree
	 */
	void exitReturning(NormalSQLParser.ReturningContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#returned}.
	 * @param ctx the parse tree
	 */
	void enterReturned(NormalSQLParser.ReturnedContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#returned}.
	 * @param ctx the parse tree
	 */
	void exitReturned(NormalSQLParser.ReturnedContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#select}.
	 * @param ctx the parse tree
	 */
	void enterSelect(NormalSQLParser.SelectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#select}.
	 * @param ctx the parse tree
	 */
	void exitSelect(NormalSQLParser.SelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#selectCore}.
	 * @param ctx the parse tree
	 */
	void enterSelectCore(NormalSQLParser.SelectCoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#selectCore}.
	 * @param ctx the parse tree
	 */
	void exitSelectCore(NormalSQLParser.SelectCoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#unions}.
	 * @param ctx the parse tree
	 */
	void enterUnions(NormalSQLParser.UnionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#unions}.
	 * @param ctx the parse tree
	 */
	void exitUnions(NormalSQLParser.UnionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#sources}.
	 * @param ctx the parse tree
	 */
	void enterSources(NormalSQLParser.SourcesContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#sources}.
	 * @param ctx the parse tree
	 */
	void exitSources(NormalSQLParser.SourcesContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(NormalSQLParser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(NormalSQLParser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#unnest}.
	 * @param ctx the parse tree
	 */
	void enterUnnest(NormalSQLParser.UnnestContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#unnest}.
	 * @param ctx the parse tree
	 */
	void exitUnnest(NormalSQLParser.UnnestContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#tableFunc}.
	 * @param ctx the parse tree
	 */
	void enterTableFunc(NormalSQLParser.TableFuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#tableFunc}.
	 * @param ctx the parse tree
	 */
	void exitTableFunc(NormalSQLParser.TableFuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#tableFuncParam}.
	 * @param ctx the parse tree
	 */
	void enterTableFuncParam(NormalSQLParser.TableFuncParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#tableFuncParam}.
	 * @param ctx the parse tree
	 */
	void exitTableFuncParam(NormalSQLParser.TableFuncParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#offset}.
	 * @param ctx the parse tree
	 */
	void enterOffset(NormalSQLParser.OffsetContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#offset}.
	 * @param ctx the parse tree
	 */
	void exitOffset(NormalSQLParser.OffsetContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#limit}.
	 * @param ctx the parse tree
	 */
	void enterLimit(NormalSQLParser.LimitContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#limit}.
	 * @param ctx the parse tree
	 */
	void exitLimit(NormalSQLParser.LimitContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#fetch}.
	 * @param ctx the parse tree
	 */
	void enterFetch(NormalSQLParser.FetchContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#fetch}.
	 * @param ctx the parse tree
	 */
	void exitFetch(NormalSQLParser.FetchContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void enterForUpdate(NormalSQLParser.ForUpdateContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#forUpdate}.
	 * @param ctx the parse tree
	 */
	void exitForUpdate(NormalSQLParser.ForUpdateContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void enterQuantifier(NormalSQLParser.QuantifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#quantifier}.
	 * @param ctx the parse tree
	 */
	void exitQuantifier(NormalSQLParser.QuantifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#top}.
	 * @param ctx the parse tree
	 */
	void enterTop(NormalSQLParser.TopContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#top}.
	 * @param ctx the parse tree
	 */
	void exitTop(NormalSQLParser.TopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ItemTableRef}
	 * labeled alternative in {@link NormalSQLParser#item}.
	 * @param ctx the parse tree
	 */
	void enterItemTableRef(NormalSQLParser.ItemTableRefContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ItemTableRef}
	 * labeled alternative in {@link NormalSQLParser#item}.
	 * @param ctx the parse tree
	 */
	void exitItemTableRef(NormalSQLParser.ItemTableRefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ItemTerm}
	 * labeled alternative in {@link NormalSQLParser#item}.
	 * @param ctx the parse tree
	 */
	void enterItemTerm(NormalSQLParser.ItemTermContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ItemTerm}
	 * labeled alternative in {@link NormalSQLParser#item}.
	 * @param ctx the parse tree
	 */
	void exitItemTerm(NormalSQLParser.ItemTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#into}.
	 * @param ctx the parse tree
	 */
	void enterInto(NormalSQLParser.IntoContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#into}.
	 * @param ctx the parse tree
	 */
	void exitInto(NormalSQLParser.IntoContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#join}.
	 * @param ctx the parse tree
	 */
	void enterJoin(NormalSQLParser.JoinContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#join}.
	 * @param ctx the parse tree
	 */
	void exitJoin(NormalSQLParser.JoinContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#pivot}.
	 * @param ctx the parse tree
	 */
	void enterPivot(NormalSQLParser.PivotContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#pivot}.
	 * @param ctx the parse tree
	 */
	void exitPivot(NormalSQLParser.PivotContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#aliasedFunction}.
	 * @param ctx the parse tree
	 */
	void enterAliasedFunction(NormalSQLParser.AliasedFunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#aliasedFunction}.
	 * @param ctx the parse tree
	 */
	void exitAliasedFunction(NormalSQLParser.AliasedFunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#unpivot}.
	 * @param ctx the parse tree
	 */
	void enterUnpivot(NormalSQLParser.UnpivotContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#unpivot}.
	 * @param ctx the parse tree
	 */
	void exitUnpivot(NormalSQLParser.UnpivotContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(NormalSQLParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(NormalSQLParser.WhereContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void enterGroupBy(NormalSQLParser.GroupByContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#groupBy}.
	 * @param ctx the parse tree
	 */
	void exitGroupBy(NormalSQLParser.GroupByContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void enterGroupByItem(NormalSQLParser.GroupByItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#groupByItem}.
	 * @param ctx the parse tree
	 */
	void exitGroupByItem(NormalSQLParser.GroupByItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#having}.
	 * @param ctx the parse tree
	 */
	void enterHaving(NormalSQLParser.HavingContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#having}.
	 * @param ctx the parse tree
	 */
	void exitHaving(NormalSQLParser.HavingContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#windows}.
	 * @param ctx the parse tree
	 */
	void enterWindows(NormalSQLParser.WindowsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#windows}.
	 * @param ctx the parse tree
	 */
	void exitWindows(NormalSQLParser.WindowsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#windowAlias}.
	 * @param ctx the parse tree
	 */
	void enterWindowAlias(NormalSQLParser.WindowAliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#windowAlias}.
	 * @param ctx the parse tree
	 */
	void exitWindowAlias(NormalSQLParser.WindowAliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#qualify}.
	 * @param ctx the parse tree
	 */
	void enterQualify(NormalSQLParser.QualifyContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#qualify}.
	 * @param ctx the parse tree
	 */
	void exitQualify(NormalSQLParser.QualifyContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#xmlTable}.
	 * @param ctx the parse tree
	 */
	void enterXmlTable(NormalSQLParser.XmlTableContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#xmlTable}.
	 * @param ctx the parse tree
	 */
	void exitXmlTable(NormalSQLParser.XmlTableContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#passing}.
	 * @param ctx the parse tree
	 */
	void enterPassing(NormalSQLParser.PassingContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#passing}.
	 * @param ctx the parse tree
	 */
	void exitPassing(NormalSQLParser.PassingContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#xmlColumn}.
	 * @param ctx the parse tree
	 */
	void enterXmlColumn(NormalSQLParser.XmlColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#xmlColumn}.
	 * @param ctx the parse tree
	 */
	void exitXmlColumn(NormalSQLParser.XmlColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#terms}.
	 * @param ctx the parse tree
	 */
	void enterTerms(NormalSQLParser.TermsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#terms}.
	 * @param ctx the parse tree
	 */
	void exitTerms(NormalSQLParser.TermsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#aliasedTerm}.
	 * @param ctx the parse tree
	 */
	void enterAliasedTerm(NormalSQLParser.AliasedTermContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#aliasedTerm}.
	 * @param ctx the parse tree
	 */
	void exitAliasedTerm(NormalSQLParser.AliasedTermContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#aliasedTerms}.
	 * @param ctx the parse tree
	 */
	void enterAliasedTerms(NormalSQLParser.AliasedTermsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#aliasedTerms}.
	 * @param ctx the parse tree
	 */
	void exitAliasedTerms(NormalSQLParser.AliasedTermsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(NormalSQLParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(NormalSQLParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(NormalSQLParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(NormalSQLParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermFunction}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermFunction(NormalSQLParser.SubtermFunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermFunction}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermFunction(NormalSQLParser.SubtermFunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermRow}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermRow(NormalSQLParser.SubtermRowContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermRow}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermRow(NormalSQLParser.SubtermRowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermCast}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermCast(NormalSQLParser.SubtermCastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermCast}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermCast(NormalSQLParser.SubtermCastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermInterval}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermInterval(NormalSQLParser.SubtermIntervalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermInterval}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermInterval(NormalSQLParser.SubtermIntervalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermSequence}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermSequence(NormalSQLParser.SubtermSequenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermSequence}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermSequence(NormalSQLParser.SubtermSequenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermArray}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermArray(NormalSQLParser.SubtermArrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermArray}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermArray(NormalSQLParser.SubtermArrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermBinary}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermBinary(NormalSQLParser.SubtermBinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermBinary}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermBinary(NormalSQLParser.SubtermBinaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermEXISTS}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermEXISTS(NormalSQLParser.SubtermEXISTSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermEXISTS}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermEXISTS(NormalSQLParser.SubtermEXISTSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermTime}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermTime(NormalSQLParser.SubtermTimeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermTime}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermTime(NormalSQLParser.SubtermTimeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermPredicate}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermPredicate(NormalSQLParser.SubtermPredicateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermPredicate}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermPredicate(NormalSQLParser.SubtermPredicateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermOverlaps}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermOverlaps(NormalSQLParser.SubtermOverlapsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermOverlaps}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermOverlaps(NormalSQLParser.SubtermOverlapsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermColumn}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermColumn(NormalSQLParser.SubtermColumnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermColumn}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermColumn(NormalSQLParser.SubtermColumnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermCase}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermCase(NormalSQLParser.SubtermCaseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermCase}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermCase(NormalSQLParser.SubtermCaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermScope}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermScope(NormalSQLParser.SubtermScopeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermScope}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermScope(NormalSQLParser.SubtermScopeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermIndex}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermIndex(NormalSQLParser.SubtermIndexContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermIndex}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermIndex(NormalSQLParser.SubtermIndexContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermUNIQUE}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermUNIQUE(NormalSQLParser.SubtermUNIQUEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermUNIQUE}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermUNIQUE(NormalSQLParser.SubtermUNIQUEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermCOLLATE}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermCOLLATE(NormalSQLParser.SubtermCOLLATEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermCOLLATE}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermCOLLATE(NormalSQLParser.SubtermCOLLATEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermLiteral}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermLiteral(NormalSQLParser.SubtermLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermLiteral}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermLiteral(NormalSQLParser.SubtermLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermQuery}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermQuery(NormalSQLParser.SubtermQueryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermQuery}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermQuery(NormalSQLParser.SubtermQueryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SubtermUnary}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void enterSubtermUnary(NormalSQLParser.SubtermUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SubtermUnary}
	 * labeled alternative in {@link NormalSQLParser#subterm}.
	 * @param ctx the parse tree
	 */
	void exitSubtermUnary(NormalSQLParser.SubtermUnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#case}.
	 * @param ctx the parse tree
	 */
	void enterCase(NormalSQLParser.CaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#case}.
	 * @param ctx the parse tree
	 */
	void exitCase(NormalSQLParser.CaseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateCompare}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateCompare(NormalSQLParser.PredicateCompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateCompare}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateCompare(NormalSQLParser.PredicateCompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateIsNull}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateIsNull(NormalSQLParser.PredicateIsNullContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateIsNull}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateIsNull(NormalSQLParser.PredicateIsNullContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateIsTruth}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateIsTruth(NormalSQLParser.PredicateIsTruthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateIsTruth}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateIsTruth(NormalSQLParser.PredicateIsTruthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateLogical}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateLogical(NormalSQLParser.PredicateLogicalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateLogical}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateLogical(NormalSQLParser.PredicateLogicalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateDistinct}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateDistinct(NormalSQLParser.PredicateDistinctContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateDistinct}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateDistinct(NormalSQLParser.PredicateDistinctContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateOfType}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateOfType(NormalSQLParser.PredicateOfTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateOfType}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateOfType(NormalSQLParser.PredicateOfTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateJSON}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateJSON(NormalSQLParser.PredicateJSONContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateJSON}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateJSON(NormalSQLParser.PredicateJSONContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateIS}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateIS(NormalSQLParser.PredicateISContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateIS}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateIS(NormalSQLParser.PredicateISContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateIN}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateIN(NormalSQLParser.PredicateINContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateIN}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateIN(NormalSQLParser.PredicateINContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateBETWEEN}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateBETWEEN(NormalSQLParser.PredicateBETWEENContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateBETWEEN}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateBETWEEN(NormalSQLParser.PredicateBETWEENContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateLIKE}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateLIKE(NormalSQLParser.PredicateLIKEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateLIKE}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateLIKE(NormalSQLParser.PredicateLIKEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PredicateRaise}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void enterPredicateRaise(NormalSQLParser.PredicateRaiseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PredicateRaise}
	 * labeled alternative in {@link NormalSQLParser#predicate}.
	 * @param ctx the parse tree
	 */
	void exitPredicateRaise(NormalSQLParser.PredicateRaiseContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#compare}.
	 * @param ctx the parse tree
	 */
	void enterCompare(NormalSQLParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#compare}.
	 * @param ctx the parse tree
	 */
	void exitCompare(NormalSQLParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#logicals}.
	 * @param ctx the parse tree
	 */
	void enterLogicals(NormalSQLParser.LogicalsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#logicals}.
	 * @param ctx the parse tree
	 */
	void exitLogicals(NormalSQLParser.LogicalsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#jsonType}.
	 * @param ctx the parse tree
	 */
	void enterJsonType(NormalSQLParser.JsonTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#jsonType}.
	 * @param ctx the parse tree
	 */
	void exitJsonType(NormalSQLParser.JsonTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(NormalSQLParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(NormalSQLParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#scalar}.
	 * @param ctx the parse tree
	 */
	void enterScalar(NormalSQLParser.ScalarContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#scalar}.
	 * @param ctx the parse tree
	 */
	void exitScalar(NormalSQLParser.ScalarContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#chars}.
	 * @param ctx the parse tree
	 */
	void enterChars(NormalSQLParser.CharsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#chars}.
	 * @param ctx the parse tree
	 */
	void exitChars(NormalSQLParser.CharsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#length}.
	 * @param ctx the parse tree
	 */
	void enterLength(NormalSQLParser.LengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#length}.
	 * @param ctx the parse tree
	 */
	void exitLength(NormalSQLParser.LengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#precision}.
	 * @param ctx the parse tree
	 */
	void enterPrecision(NormalSQLParser.PrecisionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#precision}.
	 * @param ctx the parse tree
	 */
	void exitPrecision(NormalSQLParser.PrecisionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code values}
	 * labeled alternative in {@link NormalSQLParser#itemitemsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermpredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicate}.
	 * @param ctx the parse tree
	 */
	void enterValues(NormalSQLParser.ValuesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code values}
	 * labeled alternative in {@link NormalSQLParser#itemitemsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermsubtermpredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicatepredicate}.
	 * @param ctx the parse tree
	 */
	void exitValues(NormalSQLParser.ValuesContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(NormalSQLParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(NormalSQLParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#arrayTerms}.
	 * @param ctx the parse tree
	 */
	void enterArrayTerms(NormalSQLParser.ArrayTermsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#arrayTerms}.
	 * @param ctx the parse tree
	 */
	void exitArrayTerms(NormalSQLParser.ArrayTermsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#arrayNested}.
	 * @param ctx the parse tree
	 */
	void enterArrayNested(NormalSQLParser.ArrayNestedContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#arrayNested}.
	 * @param ctx the parse tree
	 */
	void exitArrayNested(NormalSQLParser.ArrayNestedContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(NormalSQLParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(NormalSQLParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#withinGroup}.
	 * @param ctx the parse tree
	 */
	void enterWithinGroup(NormalSQLParser.WithinGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#withinGroup}.
	 * @param ctx the parse tree
	 */
	void exitWithinGroup(NormalSQLParser.WithinGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(NormalSQLParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(NormalSQLParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#over}.
	 * @param ctx the parse tree
	 */
	void enterOver(NormalSQLParser.OverContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#over}.
	 * @param ctx the parse tree
	 */
	void exitOver(NormalSQLParser.OverContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#xmlAttrib}.
	 * @param ctx the parse tree
	 */
	void enterXmlAttrib(NormalSQLParser.XmlAttribContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#xmlAttrib}.
	 * @param ctx the parse tree
	 */
	void exitXmlAttrib(NormalSQLParser.XmlAttribContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#xmlContent}.
	 * @param ctx the parse tree
	 */
	void enterXmlContent(NormalSQLParser.XmlContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#xmlContent}.
	 * @param ctx the parse tree
	 */
	void exitXmlContent(NormalSQLParser.XmlContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#window}.
	 * @param ctx the parse tree
	 */
	void enterWindow(NormalSQLParser.WindowContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#window}.
	 * @param ctx the parse tree
	 */
	void exitWindow(NormalSQLParser.WindowContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#partitionBy}.
	 * @param ctx the parse tree
	 */
	void enterPartitionBy(NormalSQLParser.PartitionByContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#partitionBy}.
	 * @param ctx the parse tree
	 */
	void exitPartitionBy(NormalSQLParser.PartitionByContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#windowFrame}.
	 * @param ctx the parse tree
	 */
	void enterWindowFrame(NormalSQLParser.WindowFrameContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#windowFrame}.
	 * @param ctx the parse tree
	 */
	void exitWindowFrame(NormalSQLParser.WindowFrameContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#preceding}.
	 * @param ctx the parse tree
	 */
	void enterPreceding(NormalSQLParser.PrecedingContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#preceding}.
	 * @param ctx the parse tree
	 */
	void exitPreceding(NormalSQLParser.PrecedingContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#following}.
	 * @param ctx the parse tree
	 */
	void enterFollowing(NormalSQLParser.FollowingContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#following}.
	 * @param ctx the parse tree
	 */
	void exitFollowing(NormalSQLParser.FollowingContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#orderBy}.
	 * @param ctx the parse tree
	 */
	void enterOrderBy(NormalSQLParser.OrderByContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#orderBy}.
	 * @param ctx the parse tree
	 */
	void exitOrderBy(NormalSQLParser.OrderByContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#orderByItem}.
	 * @param ctx the parse tree
	 */
	void enterOrderByItem(NormalSQLParser.OrderByItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#orderByItem}.
	 * @param ctx the parse tree
	 */
	void exitOrderByItem(NormalSQLParser.OrderByItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#sortDir}.
	 * @param ctx the parse tree
	 */
	void enterSortDir(NormalSQLParser.SortDirContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#sortDir}.
	 * @param ctx the parse tree
	 */
	void exitSortDir(NormalSQLParser.SortDirContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(NormalSQLParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(NormalSQLParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#truth}.
	 * @param ctx the parse tree
	 */
	void enterTruth(NormalSQLParser.TruthContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#truth}.
	 * @param ctx the parse tree
	 */
	void exitTruth(NormalSQLParser.TruthContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#boolean}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(NormalSQLParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#boolean}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(NormalSQLParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#interval}.
	 * @param ctx the parse tree
	 */
	void enterInterval(NormalSQLParser.IntervalContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#interval}.
	 * @param ctx the parse tree
	 */
	void exitInterval(NormalSQLParser.IntervalContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#timeUnit}.
	 * @param ctx the parse tree
	 */
	void enterTimeUnit(NormalSQLParser.TimeUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#timeUnit}.
	 * @param ctx the parse tree
	 */
	void exitTimeUnit(NormalSQLParser.TimeUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#jsonArray}.
	 * @param ctx the parse tree
	 */
	void enterJsonArray(NormalSQLParser.JsonArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#jsonArray}.
	 * @param ctx the parse tree
	 */
	void exitJsonArray(NormalSQLParser.JsonArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#jsonObject}.
	 * @param ctx the parse tree
	 */
	void enterJsonObject(NormalSQLParser.JsonObjectContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#jsonObject}.
	 * @param ctx the parse tree
	 */
	void exitJsonObject(NormalSQLParser.JsonObjectContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#jsonPairs}.
	 * @param ctx the parse tree
	 */
	void enterJsonPairs(NormalSQLParser.JsonPairsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#jsonPairs}.
	 * @param ctx the parse tree
	 */
	void exitJsonPairs(NormalSQLParser.JsonPairsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#jsonPair}.
	 * @param ctx the parse tree
	 */
	void enterJsonPair(NormalSQLParser.JsonPairContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#jsonPair}.
	 * @param ctx the parse tree
	 */
	void exitJsonPair(NormalSQLParser.JsonPairContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#jsonKey}.
	 * @param ctx the parse tree
	 */
	void enterJsonKey(NormalSQLParser.JsonKeyContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#jsonKey}.
	 * @param ctx the parse tree
	 */
	void exitJsonKey(NormalSQLParser.JsonKeyContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#columns}.
	 * @param ctx the parse tree
	 */
	void enterColumns(NormalSQLParser.ColumnsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#columns}.
	 * @param ctx the parse tree
	 */
	void exitColumns(NormalSQLParser.ColumnsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#column}.
	 * @param ctx the parse tree
	 */
	void enterColumn(NormalSQLParser.ColumnContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#column}.
	 * @param ctx the parse tree
	 */
	void exitColumn(NormalSQLParser.ColumnContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(NormalSQLParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(NormalSQLParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#qname}.
	 * @param ctx the parse tree
	 */
	void enterQname(NormalSQLParser.QnameContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#qname}.
	 * @param ctx the parse tree
	 */
	void exitQname(NormalSQLParser.QnameContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(NormalSQLParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(NormalSQLParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#unreserved}.
	 * @param ctx the parse tree
	 */
	void enterUnreserved(NormalSQLParser.UnreservedContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#unreserved}.
	 * @param ctx the parse tree
	 */
	void exitUnreserved(NormalSQLParser.UnreservedContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#allDistinct}.
	 * @param ctx the parse tree
	 */
	void enterAllDistinct(NormalSQLParser.AllDistinctContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#allDistinct}.
	 * @param ctx the parse tree
	 */
	void exitAllDistinct(NormalSQLParser.AllDistinctContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#firstLast}.
	 * @param ctx the parse tree
	 */
	void enterFirstLast(NormalSQLParser.FirstLastContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#firstLast}.
	 * @param ctx the parse tree
	 */
	void exitFirstLast(NormalSQLParser.FirstLastContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#formatJson}.
	 * @param ctx the parse tree
	 */
	void enterFormatJson(NormalSQLParser.FormatJsonContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#formatJson}.
	 * @param ctx the parse tree
	 */
	void exitFormatJson(NormalSQLParser.FormatJsonContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#onNull}.
	 * @param ctx the parse tree
	 */
	void enterOnNull(NormalSQLParser.OnNullContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#onNull}.
	 * @param ctx the parse tree
	 */
	void exitOnNull(NormalSQLParser.OnNullContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#respectIgnore}.
	 * @param ctx the parse tree
	 */
	void enterRespectIgnore(NormalSQLParser.RespectIgnoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#respectIgnore}.
	 * @param ctx the parse tree
	 */
	void exitRespectIgnore(NormalSQLParser.RespectIgnoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#rowRows}.
	 * @param ctx the parse tree
	 */
	void enterRowRows(NormalSQLParser.RowRowsContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#rowRows}.
	 * @param ctx the parse tree
	 */
	void exitRowRows(NormalSQLParser.RowRowsContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#timeZone}.
	 * @param ctx the parse tree
	 */
	void enterTimeZone(NormalSQLParser.TimeZoneContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#timeZone}.
	 * @param ctx the parse tree
	 */
	void exitTimeZone(NormalSQLParser.TimeZoneContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#uniqueKeys}.
	 * @param ctx the parse tree
	 */
	void enterUniqueKeys(NormalSQLParser.UniqueKeysContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#uniqueKeys}.
	 * @param ctx the parse tree
	 */
	void exitUniqueKeys(NormalSQLParser.UniqueKeysContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#withTies}.
	 * @param ctx the parse tree
	 */
	void enterWithTies(NormalSQLParser.WithTiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#withTies}.
	 * @param ctx the parse tree
	 */
	void exitWithTies(NormalSQLParser.WithTiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#withWithout}.
	 * @param ctx the parse tree
	 */
	void enterWithWithout(NormalSQLParser.WithWithoutContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#withWithout}.
	 * @param ctx the parse tree
	 */
	void exitWithWithout(NormalSQLParser.WithWithoutContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void enterAlias(NormalSQLParser.AliasContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#alias}.
	 * @param ctx the parse tree
	 */
	void exitAlias(NormalSQLParser.AliasContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#names}.
	 * @param ctx the parse tree
	 */
	void enterNames(NormalSQLParser.NamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#names}.
	 * @param ctx the parse tree
	 */
	void exitNames(NormalSQLParser.NamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#qnames0}.
	 * @param ctx the parse tree
	 */
	void enterQnames0(NormalSQLParser.Qnames0Context ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#qnames0}.
	 * @param ctx the parse tree
	 */
	void exitQnames0(NormalSQLParser.Qnames0Context ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#qnames}.
	 * @param ctx the parse tree
	 */
	void enterQnames(NormalSQLParser.QnamesContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#qnames}.
	 * @param ctx the parse tree
	 */
	void exitQnames(NormalSQLParser.QnamesContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(NormalSQLParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(NormalSQLParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(NormalSQLParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(NormalSQLParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link NormalSQLParser#uescape}.
	 * @param ctx the parse tree
	 */
	void enterUescape(NormalSQLParser.UescapeContext ctx);
	/**
	 * Exit a parse tree produced by {@link NormalSQLParser#uescape}.
	 * @param ctx the parse tree
	 */
	void exitUescape(NormalSQLParser.UescapeContext ctx);
}