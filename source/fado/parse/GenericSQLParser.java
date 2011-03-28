// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g 2011-03-27 00:05:50
 
package fado.parse;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.antlr.runtime.debug.*;
import java.io.IOException;
public class GenericSQLParser extends DebugParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SEMI", "LPAREN", "RPAREN", "SELECT", "ALL", "DISTINCT", "UNIQUE", "TOP", "Integer", "PERCENT", "INSERT", "INTO", "COMMA", "VALUES", "STAR", "AS", "DOT", "Identifier", "FROM", "INNER", "LEFT", "RIGHT", "FULL", "OUTER", "JOIN", "ON", "USING", "WHERE", "GROUP", "BY", "HAVING", "ORDER", "ASC", "DESC", "OR", "AND", "NOT", "IN", "BETWEEN", "IS", "NULL", "EXISTS", "LIKE", "EQ", "NEQ1", "NEQ2", "LTE", "LT", "GTE", "GT", "ANY", "SOME", "PLUS", "MINUS", "DIVIDE", "Float", "String", "TRUE", "FALSE", "Timestamp", "QuotedIdentifier", "A", "L", "N", "D", "Y", "S", "C", "B", "E", "T", "W", "CASE", "DELETE", "I", "ELSE", "END", "X", "F", "R", "O", "M", "U", "G", "P", "H", "V", "J", "K", "THEN", "UNION", "Q", "UPDATE", "WHEN", "Z", "LCURLY", "RCURLY", "STRCAT", "QUESTION", "COLON", "MOD", "Digit", "Exponent", "Comment", "Whitespace", "'{d'", "'{t'", "'{ts'"
    };
    public static final int LT=51;
    public static final int STAR=18;
    public static final int MOD=104;
    public static final int CASE=76;
    public static final int NOT=40;
    public static final int EOF=-1;
    public static final int Identifier=21;
    public static final int RPAREN=6;
    public static final int FULL=26;
    public static final int INSERT=14;
    public static final int USING=30;
    public static final int Comment=107;
    public static final int EQ=47;
    public static final int SELECT=7;
    public static final int INTO=15;
    public static final int DIVIDE=58;
    public static final int D=68;
    public static final int E=73;
    public static final int UNIQUE=10;
    public static final int F=82;
    public static final int G=87;
    public static final int A=65;
    public static final int B=72;
    public static final int ASC=36;
    public static final int C=71;
    public static final int L=66;
    public static final int M=85;
    public static final int N=67;
    public static final int O=84;
    public static final int H=89;
    public static final int NULL=44;
    public static final int I=78;
    public static final int J=91;
    public static final int ELSE=79;
    public static final int K=92;
    public static final int U=86;
    public static final int ON=29;
    public static final int T=74;
    public static final int W=75;
    public static final int LCURLY=99;
    public static final int V=90;
    public static final int Q=95;
    public static final int DELETE=77;
    public static final int P=88;
    public static final int S=70;
    public static final int R=83;
    public static final int Y=69;
    public static final int X=81;
    public static final int Z=98;
    public static final int Float=59;
    public static final int GROUP=32;
    public static final int OR=38;
    public static final int Timestamp=63;
    public static final int GT=53;
    public static final int FROM=22;
    public static final int END=80;
    public static final int FALSE=62;
    public static final int DISTINCT=9;
    public static final int NEQ1=48;
    public static final int WHERE=31;
    public static final int INNER=23;
    public static final int ORDER=35;
    public static final int NEQ2=49;
    public static final int GTE=52;
    public static final int UPDATE=96;
    public static final int Exponent=106;
    public static final int AND=39;
    public static final int LTE=50;
    public static final int LPAREN=5;
    public static final int AS=19;
    public static final int THEN=93;
    public static final int IN=41;
    public static final int COMMA=16;
    public static final int T__109=109;
    public static final int IS=43;
    public static final int LEFT=24;
    public static final int SOME=55;
    public static final int ALL=8;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int PLUS=56;
    public static final int EXISTS=45;
    public static final int String=60;
    public static final int DOT=20;
    public static final int Whitespace=108;
    public static final int STRCAT=101;
    public static final int LIKE=46;
    public static final int OUTER=27;
    public static final int BY=33;
    public static final int PERCENT=13;
    public static final int VALUES=17;
    public static final int RIGHT=25;
    public static final int HAVING=34;
    public static final int MINUS=57;
    public static final int Digit=105;
    public static final int QuotedIdentifier=64;
    public static final int SEMI=4;
    public static final int TRUE=61;
    public static final int JOIN=28;
    public static final int UNION=94;
    public static final int COLON=103;
    public static final int ANY=54;
    public static final int QUESTION=102;
    public static final int WHEN=97;
    public static final int RCURLY=100;
    public static final int DESC=37;
    public static final int TOP=11;
    public static final int BETWEEN=42;
    public static final int Integer=12;

    // delegates
    // delegators

    public static final String[] ruleNames = new String[] {
        "invalidRule", "synpred15_GenericSQL", "tableName", "expressionList", 
        "synpred29_GenericSQL", "synpred41_GenericSQL", "synpred56_GenericSQL", 
        "synpred20_GenericSQL", "synpred8_GenericSQL", "synpred66_GenericSQL", 
        "into", "subSelect", "synpred1_GenericSQL", "quantifier", "synpred69_GenericSQL", 
        "synpred54_GenericSQL", "synpred27_GenericSQL", "databaseName", 
        "synpred58_GenericSQL", "from", "synpred25_GenericSQL", "synpred57_GenericSQL", 
        "synpred17_GenericSQL", "synpred77_GenericSQL", "synpred43_GenericSQL", 
        "date", "nestedExpression", "synpred19_GenericSQL", "synpred42_GenericSQL", 
        "where", "nestedCondition", "synpred33_GenericSQL", "synpred84_GenericSQL", 
        "select", "condition", "synpred24_GenericSQL", "conditionList", 
        "synpred85_GenericSQL", "synpred37_GenericSQL", "columnList", "synpred5_GenericSQL", 
        "tableAlias", "synpred13_GenericSQL", "synpred72_GenericSQL", "fromItem", 
        "synpred64_GenericSQL", "groupBy", "synpred81_GenericSQL", "synpred75_GenericSQL", 
        "synpred2_GenericSQL", "columnRef", "synpred35_GenericSQL", "synpred48_GenericSQL", 
        "synpred16_GenericSQL", "synpred7_GenericSQL", "synpred70_GenericSQL", 
        "synpred61_GenericSQL", "insert", "in", "multiply", "synpred71_GenericSQL", 
        "synpred83_GenericSQL", "synpred6_GenericSQL", "synpred46_GenericSQL", 
        "join", "synpred40_GenericSQL", "synpred3_GenericSQL", "allColumns", 
        "like", "synpred51_GenericSQL", "synpred86_GenericSQL", "synpred82_GenericSQL", 
        "joinList", "synpred68_GenericSQL", "having", "synpred31_GenericSQL", 
        "synpred18_GenericSQL", "synpred39_GenericSQL", "synpred45_GenericSQL", 
        "synpred60_GenericSQL", "synpred63_GenericSQL", "tableRef", "synpred67_GenericSQL", 
        "exists", "synpred21_GenericSQL", "synpred34_GenericSQL", "synpred32_GenericSQL", 
        "alias", "synpred28_GenericSQL", "synpred59_GenericSQL", "synpred30_GenericSQL", 
        "literal", "synpred9_GenericSQL", "synpred36_GenericSQL", "between", 
        "synpred73_GenericSQL", "value", "values", "synpred11_GenericSQL", 
        "synpred80_GenericSQL", "synpred10_GenericSQL", "synpred79_GenericSQL", 
        "comparator", "synpred38_GenericSQL", "synpred52_GenericSQL", "synpred49_GenericSQL", 
        "expression", "itemList", "orderByItem", "synpred65_GenericSQL", 
        "synpred47_GenericSQL", "synpred76_GenericSQL", "synpred55_GenericSQL", 
        "synpred53_GenericSQL", "comparison", "synpred4_GenericSQL", "synpred74_GenericSQL", 
        "unary", "item", "synpred12_GenericSQL", "statement", "synpred23_GenericSQL", 
        "synpred22_GenericSQL", "synpred78_GenericSQL", "synpred50_GenericSQL", 
        "synpred26_GenericSQL", "columnName", "orderBy", "synpred14_GenericSQL", 
        "synpred44_GenericSQL", "isNull", "synpred62_GenericSQL"
    };
     
        public int ruleLevel = 0;
        public int getRuleLevel() { return ruleLevel; }
        public void incRuleLevel() { ruleLevel++; }
        public void decRuleLevel() { ruleLevel--; }
        public GenericSQLParser(TokenStream input) {
            this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT, new RecognizerSharedState());
        }
        public GenericSQLParser(TokenStream input, int port, RecognizerSharedState state) {
            super(input, state);
            DebugEventSocketProxy proxy =
                new DebugEventSocketProxy(this, port, null);
            setDebugListener(proxy);
            try {
                proxy.handshake();
            }
            catch (IOException ioe) {
                reportError(ioe);
            }
        }
    public GenericSQLParser(TokenStream input, DebugEventListener dbg) {
        super(input, dbg, new RecognizerSharedState());

    }
    protected boolean evalPredicate(boolean result, String predicate) {
        dbg.semanticPredicate(result, predicate);
        return result;
    }


    public String[] getTokenNames() { return GenericSQLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g"; }





    // $ANTLR start "statement"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:55:1: statement : ( select ( SEMI )? EOF | insert ( SEMI )? EOF );
    public final void statement() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "statement");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(55, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:3: ( select ( SEMI )? EOF | insert ( SEMI )? EOF )
            int alt3=2;
            try { dbg.enterDecision(3);

            int LA3_0 = input.LA(1);

            if ( (LA3_0==SELECT) ) {
                alt3=1;
            }
            else if ( (LA3_0==INSERT) ) {
                alt3=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(3);}

            switch (alt3) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:5: select ( SEMI )? EOF
                    {
                    dbg.location(56,5);
                    pushFollow(FOLLOW_select_in_statement69);
                    select();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(56,12);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:12: ( SEMI )?
                    int alt1=2;
                    try { dbg.enterSubRule(1);
                    try { dbg.enterDecision(1);

                    int LA1_0 = input.LA(1);

                    if ( (LA1_0==SEMI) ) {
                        alt1=1;
                    }
                    } finally {dbg.exitDecision(1);}

                    switch (alt1) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:14: SEMI
                            {
                            dbg.location(56,14);
                            match(input,SEMI,FOLLOW_SEMI_in_statement73); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(1);}

                    dbg.location(56,22);
                    match(input,EOF,FOLLOW_EOF_in_statement78); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:57:5: insert ( SEMI )? EOF
                    {
                    dbg.location(57,5);
                    pushFollow(FOLLOW_insert_in_statement84);
                    insert();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(57,12);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:57:12: ( SEMI )?
                    int alt2=2;
                    try { dbg.enterSubRule(2);
                    try { dbg.enterDecision(2);

                    int LA2_0 = input.LA(1);

                    if ( (LA2_0==SEMI) ) {
                        alt2=1;
                    }
                    } finally {dbg.exitDecision(2);}

                    switch (alt2) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:57:14: SEMI
                            {
                            dbg.location(57,14);
                            match(input,SEMI,FOLLOW_SEMI_in_statement88); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(2);}

                    dbg.location(57,22);
                    match(input,EOF,FOLLOW_EOF_in_statement93); if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(60, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "statement");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "statement"


    // $ANTLR start "subSelect"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:62:1: subSelect : ( select | LPAREN select RPAREN );
    public final void subSelect() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "subSelect");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(62, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:63:3: ( select | LPAREN select RPAREN )
            int alt4=2;
            try { dbg.enterDecision(4);

            int LA4_0 = input.LA(1);

            if ( (LA4_0==SELECT) ) {
                alt4=1;
            }
            else if ( (LA4_0==LPAREN) ) {
                alt4=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(4);}

            switch (alt4) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:63:5: select
                    {
                    dbg.location(63,5);
                    pushFollow(FOLLOW_select_in_subSelect110);
                    select();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:64:5: LPAREN select RPAREN
                    {
                    dbg.location(64,5);
                    match(input,LPAREN,FOLLOW_LPAREN_in_subSelect116); if (state.failed) return ;
                    dbg.location(64,12);
                    pushFollow(FOLLOW_select_in_subSelect118);
                    select();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(64,19);
                    match(input,RPAREN,FOLLOW_RPAREN_in_subSelect120); if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(65, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "subSelect");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "subSelect"


    // $ANTLR start "select"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:67:1: select : SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( where )? ( groupBy )? ( having )? ( orderBy )? ;
    public final void select() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "select");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(67, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:68:3: ( SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( where )? ( groupBy )? ( having )? ( orderBy )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:68:5: SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( where )? ( groupBy )? ( having )? ( orderBy )?
            {
            dbg.location(68,5);
            match(input,SELECT,FOLLOW_SELECT_in_select135); if (state.failed) return ;
            dbg.location(69,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:69:5: ( ALL | DISTINCT | UNIQUE )?
            int alt5=2;
            try { dbg.enterSubRule(5);
            try { dbg.enterDecision(5);

            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=ALL && LA5_0<=UNIQUE)) ) {
                alt5=1;
            }
            } finally {dbg.exitDecision(5);}

            switch (alt5) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
                    {
                    dbg.location(69,5);
                    if ( (input.LA(1)>=ALL && input.LA(1)<=UNIQUE) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }


                    }
                    break;

            }
            } finally {dbg.exitSubRule(5);}

            dbg.location(70,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:5: ( TOP Integer ( PERCENT )? )?
            int alt7=2;
            try { dbg.enterSubRule(7);
            try { dbg.enterDecision(7);

            int LA7_0 = input.LA(1);

            if ( (LA7_0==TOP) ) {
                alt7=1;
            }
            } finally {dbg.exitDecision(7);}

            switch (alt7) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:7: TOP Integer ( PERCENT )?
                    {
                    dbg.location(70,7);
                    match(input,TOP,FOLLOW_TOP_in_select162); if (state.failed) return ;
                    dbg.location(70,11);
                    match(input,Integer,FOLLOW_Integer_in_select164); if (state.failed) return ;
                    dbg.location(70,19);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:19: ( PERCENT )?
                    int alt6=2;
                    try { dbg.enterSubRule(6);
                    try { dbg.enterDecision(6);

                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==PERCENT) ) {
                        alt6=1;
                    }
                    } finally {dbg.exitDecision(6);}

                    switch (alt6) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:21: PERCENT
                            {
                            dbg.location(70,21);
                            match(input,PERCENT,FOLLOW_PERCENT_in_select168); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(6);}


                    }
                    break;

            }
            } finally {dbg.exitSubRule(7);}

            dbg.location(71,5);
            pushFollow(FOLLOW_itemList_in_select180);
            itemList();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(72,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:5: ( into )?
            int alt8=2;
            try { dbg.enterSubRule(8);
            try { dbg.enterDecision(8);

            int LA8_0 = input.LA(1);

            if ( (LA8_0==INTO) ) {
                alt8=1;
            }
            } finally {dbg.exitDecision(8);}

            switch (alt8) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:7: into
                    {
                    dbg.location(72,7);
                    pushFollow(FOLLOW_into_in_select188);
                    into();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(8);}

            dbg.location(73,5);
            pushFollow(FOLLOW_from_in_select197);
            from();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(75,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:75:5: ( where )?
            int alt9=2;
            try { dbg.enterSubRule(9);
            try { dbg.enterDecision(9);

            int LA9_0 = input.LA(1);

            if ( (LA9_0==WHERE) ) {
                alt9=1;
            }
            } finally {dbg.exitDecision(9);}

            switch (alt9) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:75:7: where
                    {
                    dbg.location(75,7);
                    pushFollow(FOLLOW_where_in_select206);
                    where();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(9);}

            dbg.location(76,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:76:5: ( groupBy )?
            int alt10=2;
            try { dbg.enterSubRule(10);
            try { dbg.enterDecision(10);

            int LA10_0 = input.LA(1);

            if ( (LA10_0==GROUP) ) {
                alt10=1;
            }
            } finally {dbg.exitDecision(10);}

            switch (alt10) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:76:7: groupBy
                    {
                    dbg.location(76,7);
                    pushFollow(FOLLOW_groupBy_in_select217);
                    groupBy();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(10);}

            dbg.location(77,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:77:5: ( having )?
            int alt11=2;
            try { dbg.enterSubRule(11);
            try { dbg.enterDecision(11);

            int LA11_0 = input.LA(1);

            if ( (LA11_0==HAVING) ) {
                alt11=1;
            }
            } finally {dbg.exitDecision(11);}

            switch (alt11) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:77:7: having
                    {
                    dbg.location(77,7);
                    pushFollow(FOLLOW_having_in_select228);
                    having();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(11);}

            dbg.location(78,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:78:5: ( orderBy )?
            int alt12=2;
            try { dbg.enterSubRule(12);
            try { dbg.enterDecision(12);

            int LA12_0 = input.LA(1);

            if ( (LA12_0==ORDER) ) {
                alt12=1;
            }
            } finally {dbg.exitDecision(12);}

            switch (alt12) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:78:7: orderBy
                    {
                    dbg.location(78,7);
                    pushFollow(FOLLOW_orderBy_in_select239);
                    orderBy();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(12);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(79, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "select");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "select"


    // $ANTLR start "insert"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:81:1: insert : INSERT into ( columnList )? ( values ) ;
    public final void insert() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "insert");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(81, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:82:3: ( INSERT into ( columnList )? ( values ) )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:82:5: INSERT into ( columnList )? ( values )
            {
            dbg.location(82,5);
            match(input,INSERT,FOLLOW_INSERT_in_insert257); if (state.failed) return ;
            dbg.location(82,12);
            pushFollow(FOLLOW_into_in_insert259);
            into();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(82,17);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:82:17: ( columnList )?
            int alt13=2;
            try { dbg.enterSubRule(13);
            try { dbg.enterDecision(13);

            int LA13_0 = input.LA(1);

            if ( (LA13_0==LPAREN) ) {
                alt13=1;
            }
            } finally {dbg.exitDecision(13);}

            switch (alt13) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:82:19: columnList
                    {
                    dbg.location(82,19);
                    pushFollow(FOLLOW_columnList_in_insert263);
                    columnList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(13);}

            dbg.location(83,3);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:83:3: ( values )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:83:5: values
            {
            dbg.location(83,5);
            pushFollow(FOLLOW_values_in_insert272);
            values();

            state._fsp--;
            if (state.failed) return ;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(86, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "insert");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "insert"


    // $ANTLR start "into"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:88:1: into : INTO tableRef ( COMMA tableRef )* ;
    public final void into() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "into");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(88, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:89:3: ( INTO tableRef ( COMMA tableRef )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:89:5: INTO tableRef ( COMMA tableRef )*
            {
            dbg.location(89,5);
            match(input,INTO,FOLLOW_INTO_in_into292); if (state.failed) return ;
            dbg.location(89,10);
            pushFollow(FOLLOW_tableRef_in_into294);
            tableRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(89,19);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:89:19: ( COMMA tableRef )*
            try { dbg.enterSubRule(14);

            loop14:
            do {
                int alt14=2;
                try { dbg.enterDecision(14);

                int LA14_0 = input.LA(1);

                if ( (LA14_0==COMMA) ) {
                    alt14=1;
                }


                } finally {dbg.exitDecision(14);}

                switch (alt14) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:89:21: COMMA tableRef
            	    {
            	    dbg.location(89,21);
            	    match(input,COMMA,FOLLOW_COMMA_in_into298); if (state.failed) return ;
            	    dbg.location(89,27);
            	    pushFollow(FOLLOW_tableRef_in_into300);
            	    tableRef();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);
            } finally {dbg.exitSubRule(14);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(90, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "into");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "into"


    // $ANTLR start "columnList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:92:1: columnList : LPAREN columnName ( COMMA columnName )* RPAREN ;
    public final void columnList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(92, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:93:3: ( LPAREN columnName ( COMMA columnName )* RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:93:5: LPAREN columnName ( COMMA columnName )* RPAREN
            {
            dbg.location(93,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_columnList316); if (state.failed) return ;
            dbg.location(93,12);
            pushFollow(FOLLOW_columnName_in_columnList318);
            columnName();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(93,23);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:93:23: ( COMMA columnName )*
            try { dbg.enterSubRule(15);

            loop15:
            do {
                int alt15=2;
                try { dbg.enterDecision(15);

                int LA15_0 = input.LA(1);

                if ( (LA15_0==COMMA) ) {
                    alt15=1;
                }


                } finally {dbg.exitDecision(15);}

                switch (alt15) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:93:25: COMMA columnName
            	    {
            	    dbg.location(93,25);
            	    match(input,COMMA,FOLLOW_COMMA_in_columnList322); if (state.failed) return ;
            	    dbg.location(93,31);
            	    pushFollow(FOLLOW_columnName_in_columnList324);
            	    columnName();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);
            } finally {dbg.exitSubRule(15);}

            dbg.location(93,45);
            match(input,RPAREN,FOLLOW_RPAREN_in_columnList329); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(94, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "columnList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "columnList"


    // $ANTLR start "values"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:96:1: values : VALUES LPAREN literal ( COMMA literal )* RPAREN ;
    public final void values() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "values");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(96, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:97:3: ( VALUES LPAREN literal ( COMMA literal )* RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:97:5: VALUES LPAREN literal ( COMMA literal )* RPAREN
            {
            dbg.location(97,5);
            match(input,VALUES,FOLLOW_VALUES_in_values344); if (state.failed) return ;
            dbg.location(97,12);
            match(input,LPAREN,FOLLOW_LPAREN_in_values346); if (state.failed) return ;
            dbg.location(97,19);
            pushFollow(FOLLOW_literal_in_values348);
            literal();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(97,27);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:97:27: ( COMMA literal )*
            try { dbg.enterSubRule(16);

            loop16:
            do {
                int alt16=2;
                try { dbg.enterDecision(16);

                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                } finally {dbg.exitDecision(16);}

                switch (alt16) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:97:29: COMMA literal
            	    {
            	    dbg.location(97,29);
            	    match(input,COMMA,FOLLOW_COMMA_in_values352); if (state.failed) return ;
            	    dbg.location(97,35);
            	    pushFollow(FOLLOW_literal_in_values354);
            	    literal();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);
            } finally {dbg.exitSubRule(16);}

            dbg.location(97,46);
            match(input,RPAREN,FOLLOW_RPAREN_in_values359); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(98, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "values");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "values"


    // $ANTLR start "itemList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:100:1: itemList : ( STAR | item ( COMMA item )* );
    public final void itemList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "itemList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(100, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:101:3: ( STAR | item ( COMMA item )* )
            int alt18=2;
            try { dbg.enterDecision(18);

            int LA18_0 = input.LA(1);

            if ( (LA18_0==STAR) ) {
                alt18=1;
            }
            else if ( (LA18_0==LPAREN||LA18_0==Integer||LA18_0==Identifier||(LA18_0>=PLUS && LA18_0<=MINUS)||(LA18_0>=Float && LA18_0<=FALSE)||LA18_0==QuotedIdentifier||(LA18_0>=109 && LA18_0<=111)) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(18);}

            switch (alt18) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:101:5: STAR
                    {
                    dbg.location(101,5);
                    match(input,STAR,FOLLOW_STAR_in_itemList374); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:102:5: item ( COMMA item )*
                    {
                    dbg.location(102,5);
                    pushFollow(FOLLOW_item_in_itemList380);
                    item();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(102,10);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:102:10: ( COMMA item )*
                    try { dbg.enterSubRule(17);

                    loop17:
                    do {
                        int alt17=2;
                        try { dbg.enterDecision(17);

                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==COMMA) ) {
                            alt17=1;
                        }


                        } finally {dbg.exitDecision(17);}

                        switch (alt17) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:102:12: COMMA item
                    	    {
                    	    dbg.location(102,12);
                    	    match(input,COMMA,FOLLOW_COMMA_in_itemList384); if (state.failed) return ;
                    	    dbg.location(102,18);
                    	    pushFollow(FOLLOW_item_in_itemList386);
                    	    item();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(17);}


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(103, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "itemList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "itemList"


    // $ANTLR start "item"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:105:1: item : ( value ( ( AS )? alias )? | allColumns );
    public final void item() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "item");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(105, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:3: ( value ( ( AS )? alias )? | allColumns )
            int alt21=2;
            try { dbg.enterDecision(21);

            int LA21_0 = input.LA(1);

            if ( (LA21_0==LPAREN||LA21_0==Integer||(LA21_0>=PLUS && LA21_0<=MINUS)||(LA21_0>=Float && LA21_0<=FALSE)||LA21_0==QuotedIdentifier||(LA21_0>=109 && LA21_0<=111)) ) {
                alt21=1;
            }
            else if ( (LA21_0==Identifier) ) {
                int LA21_2 = input.LA(2);

                if ( (LA21_2==DOT) ) {
                    int LA21_3 = input.LA(3);

                    if ( (LA21_3==STAR) ) {
                        alt21=2;
                    }
                    else if ( (LA21_3==Identifier||LA21_3==QuotedIdentifier) ) {
                        alt21=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 21, 3, input);

                        dbg.recognitionException(nvae);
                        throw nvae;
                    }
                }
                else if ( (LA21_2==EOF||(LA21_2>=INTO && LA21_2<=COMMA)||LA21_2==AS||(LA21_2>=Identifier && LA21_2<=FROM)) ) {
                    alt21=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 2, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(21);}

            switch (alt21) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:5: value ( ( AS )? alias )?
                    {
                    dbg.location(106,5);
                    pushFollow(FOLLOW_value_in_item404);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(106,11);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:11: ( ( AS )? alias )?
                    int alt20=2;
                    try { dbg.enterSubRule(20);
                    try { dbg.enterDecision(20);

                    int LA20_0 = input.LA(1);

                    if ( (LA20_0==AS||LA20_0==Identifier) ) {
                        alt20=1;
                    }
                    } finally {dbg.exitDecision(20);}

                    switch (alt20) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:13: ( AS )? alias
                            {
                            dbg.location(106,13);
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:13: ( AS )?
                            int alt19=2;
                            try { dbg.enterSubRule(19);
                            try { dbg.enterDecision(19);

                            int LA19_0 = input.LA(1);

                            if ( (LA19_0==AS) ) {
                                alt19=1;
                            }
                            } finally {dbg.exitDecision(19);}

                            switch (alt19) {
                                case 1 :
                                    dbg.enterAlt(1);

                                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:15: AS
                                    {
                                    dbg.location(106,15);
                                    match(input,AS,FOLLOW_AS_in_item410); if (state.failed) return ;

                                    }
                                    break;

                            }
                            } finally {dbg.exitSubRule(19);}

                            dbg.location(106,21);
                            pushFollow(FOLLOW_alias_in_item415);
                            alias();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(20);}


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:107:5: allColumns
                    {
                    dbg.location(107,5);
                    pushFollow(FOLLOW_allColumns_in_item424);
                    allColumns();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(108, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "item");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "item"


    // $ANTLR start "allColumns"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:110:1: allColumns : tableAlias DOT STAR ;
    public final void allColumns() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "allColumns");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(110, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:111:3: ( tableAlias DOT STAR )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:111:5: tableAlias DOT STAR
            {
            dbg.location(111,5);
            pushFollow(FOLLOW_tableAlias_in_allColumns438);
            tableAlias();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(111,16);
            match(input,DOT,FOLLOW_DOT_in_allColumns440); if (state.failed) return ;
            dbg.location(111,20);
            match(input,STAR,FOLLOW_STAR_in_allColumns442); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(112, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "allColumns");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "allColumns"


    // $ANTLR start "alias"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:1: alias : Identifier ;
    public final void alias() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "alias");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(114, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:3: ( Identifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:5: Identifier
            {
            dbg.location(115,5);
            match(input,Identifier,FOLLOW_Identifier_in_alias455); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(116, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "alias");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "alias"


    // $ANTLR start "from"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:119:1: from : FROM fromItem ( COMMA fromItem )* ;
    public final void from() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "from");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(119, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:120:3: ( FROM fromItem ( COMMA fromItem )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:120:5: FROM fromItem ( COMMA fromItem )*
            {
            dbg.location(120,5);
            match(input,FROM,FOLLOW_FROM_in_from473); if (state.failed) return ;
            dbg.location(120,10);
            pushFollow(FOLLOW_fromItem_in_from475);
            fromItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(120,19);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:120:19: ( COMMA fromItem )*
            try { dbg.enterSubRule(22);

            loop22:
            do {
                int alt22=2;
                try { dbg.enterDecision(22);

                int LA22_0 = input.LA(1);

                if ( (LA22_0==COMMA) ) {
                    alt22=1;
                }


                } finally {dbg.exitDecision(22);}

                switch (alt22) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:120:21: COMMA fromItem
            	    {
            	    dbg.location(120,21);
            	    match(input,COMMA,FOLLOW_COMMA_in_from479); if (state.failed) return ;
            	    dbg.location(120,27);
            	    pushFollow(FOLLOW_fromItem_in_from481);
            	    fromItem();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);
            } finally {dbg.exitSubRule(22);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(121, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "from");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "from"


    // $ANTLR start "fromItem"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:123:1: fromItem : ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? ;
    public final void fromItem() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "fromItem");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(123, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:124:3: ( ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:124:5: ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )?
            {
            dbg.location(124,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:124:5: ( ( LPAREN subSelect RPAREN ) | tableRef )
            int alt23=2;
            try { dbg.enterSubRule(23);
            try { dbg.enterDecision(23);

            int LA23_0 = input.LA(1);

            if ( (LA23_0==LPAREN) ) {
                alt23=1;
            }
            else if ( (LA23_0==Identifier||LA23_0==QuotedIdentifier) ) {
                alt23=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(23);}

            switch (alt23) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:124:7: ( LPAREN subSelect RPAREN )
                    {
                    dbg.location(124,7);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:124:7: ( LPAREN subSelect RPAREN )
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:124:9: LPAREN subSelect RPAREN
                    {
                    dbg.location(124,9);
                    match(input,LPAREN,FOLLOW_LPAREN_in_fromItem503); if (state.failed) return ;
                    dbg.location(124,16);
                    pushFollow(FOLLOW_subSelect_in_fromItem505);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(124,26);
                    match(input,RPAREN,FOLLOW_RPAREN_in_fromItem507); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:125:7: tableRef
                    {
                    dbg.location(125,7);
                    pushFollow(FOLLOW_tableRef_in_fromItem518);
                    tableRef();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(23);}

            dbg.location(127,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:127:5: ( ( AS )? alias )?
            int alt25=2;
            try { dbg.enterSubRule(25);
            try { dbg.enterDecision(25);

            int LA25_0 = input.LA(1);

            if ( (LA25_0==AS||LA25_0==Identifier) ) {
                alt25=1;
            }
            } finally {dbg.exitDecision(25);}

            switch (alt25) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:127:7: ( AS )? alias
                    {
                    dbg.location(127,7);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:127:7: ( AS )?
                    int alt24=2;
                    try { dbg.enterSubRule(24);
                    try { dbg.enterDecision(24);

                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==AS) ) {
                        alt24=1;
                    }
                    } finally {dbg.exitDecision(24);}

                    switch (alt24) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:127:9: AS
                            {
                            dbg.location(127,9);
                            match(input,AS,FOLLOW_AS_in_fromItem535); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(24);}

                    dbg.location(127,15);
                    pushFollow(FOLLOW_alias_in_fromItem540);
                    alias();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(25);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(128, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "fromItem");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "fromItem"


    // $ANTLR start "joinList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:130:1: joinList : ( join )* ;
    public final void joinList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "joinList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(130, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:131:3: ( ( join )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:131:5: ( join )*
            {
            dbg.location(131,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:131:5: ( join )*
            try { dbg.enterSubRule(26);

            loop26:
            do {
                int alt26=2;
                try { dbg.enterDecision(26);

                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=INNER && LA26_0<=FULL)||LA26_0==JOIN) ) {
                    alt26=1;
                }


                } finally {dbg.exitDecision(26);}

                switch (alt26) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:131:7: join
            	    {
            	    dbg.location(131,7);
            	    pushFollow(FOLLOW_join_in_joinList558);
            	    join();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);
            } finally {dbg.exitSubRule(26);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(132, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "joinList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "joinList"


    // $ANTLR start "join"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:134:1: join : ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )? JOIN ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )? ;
    public final void join() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "join");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(134, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:135:3: ( ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )? JOIN ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:135:5: ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )? JOIN ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )?
            {
            dbg.location(135,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:135:5: ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )?
            int alt28=3;
            try { dbg.enterSubRule(28);
            try { dbg.enterDecision(28);

            int LA28_0 = input.LA(1);

            if ( (LA28_0==INNER) ) {
                alt28=1;
            }
            else if ( ((LA28_0>=LEFT && LA28_0<=FULL)) ) {
                alt28=2;
            }
            } finally {dbg.exitDecision(28);}

            switch (alt28) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:135:7: INNER
                    {
                    dbg.location(135,7);
                    match(input,INNER,FOLLOW_INNER_in_join578); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:136:7: ( LEFT | RIGHT | FULL ) ( OUTER )?
                    {
                    dbg.location(136,7);
                    if ( (input.LA(1)>=LEFT && input.LA(1)<=FULL) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }

                    dbg.location(136,31);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:136:31: ( OUTER )?
                    int alt27=2;
                    try { dbg.enterSubRule(27);
                    try { dbg.enterDecision(27);

                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==OUTER) ) {
                        alt27=1;
                    }
                    } finally {dbg.exitDecision(27);}

                    switch (alt27) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:136:33: OUTER
                            {
                            dbg.location(136,33);
                            match(input,OUTER,FOLLOW_OUTER_in_join602); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(27);}


                    }
                    break;

            }
            } finally {dbg.exitSubRule(28);}

            dbg.location(137,8);
            match(input,JOIN,FOLLOW_JOIN_in_join615); if (state.failed) return ;
            dbg.location(138,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:138:5: ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )?
            int alt30=3;
            try { dbg.enterSubRule(30);
            try { dbg.enterDecision(30);

            int LA30_0 = input.LA(1);

            if ( (LA30_0==ON) ) {
                alt30=1;
            }
            else if ( (LA30_0==USING) ) {
                alt30=2;
            }
            } finally {dbg.exitDecision(30);}

            switch (alt30) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:138:7: ON conditionList
                    {
                    dbg.location(138,7);
                    match(input,ON,FOLLOW_ON_in_join623); if (state.failed) return ;
                    dbg.location(138,10);
                    pushFollow(FOLLOW_conditionList_in_join625);
                    conditionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:139:7: USING LPAREN columnRef ( COMMA columnRef )*
                    {
                    dbg.location(139,7);
                    match(input,USING,FOLLOW_USING_in_join633); if (state.failed) return ;
                    dbg.location(139,13);
                    match(input,LPAREN,FOLLOW_LPAREN_in_join635); if (state.failed) return ;
                    dbg.location(139,20);
                    pushFollow(FOLLOW_columnRef_in_join637);
                    columnRef();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(139,30);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:139:30: ( COMMA columnRef )*
                    try { dbg.enterSubRule(29);

                    loop29:
                    do {
                        int alt29=2;
                        try { dbg.enterDecision(29);

                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==COMMA) ) {
                            alt29=1;
                        }


                        } finally {dbg.exitDecision(29);}

                        switch (alt29) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:139:32: COMMA columnRef
                    	    {
                    	    dbg.location(139,32);
                    	    match(input,COMMA,FOLLOW_COMMA_in_join641); if (state.failed) return ;
                    	    dbg.location(139,38);
                    	    pushFollow(FOLLOW_columnRef_in_join643);
                    	    columnRef();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(29);}


                    }
                    break;

            }
            } finally {dbg.exitSubRule(30);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(141, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "join");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "join"


    // $ANTLR start "where"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:149:1: where : WHERE conditionList ;
    public final void where() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "where");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(149, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:150:3: ( WHERE conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:150:5: WHERE conditionList
            {
            dbg.location(150,5);
            match(input,WHERE,FOLLOW_WHERE_in_where674); if (state.failed) return ;
            dbg.location(150,11);
            pushFollow(FOLLOW_conditionList_in_where676);
            conditionList();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(151, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "where");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "where"


    // $ANTLR start "groupBy"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:153:1: groupBy : GROUP BY columnRef ( COMMA columnRef )* ;
    public final void groupBy() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "groupBy");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(153, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:154:3: ( GROUP BY columnRef ( COMMA columnRef )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:154:5: GROUP BY columnRef ( COMMA columnRef )*
            {
            dbg.location(154,5);
            match(input,GROUP,FOLLOW_GROUP_in_groupBy691); if (state.failed) return ;
            dbg.location(154,11);
            match(input,BY,FOLLOW_BY_in_groupBy693); if (state.failed) return ;
            dbg.location(154,14);
            pushFollow(FOLLOW_columnRef_in_groupBy695);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(154,24);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:154:24: ( COMMA columnRef )*
            try { dbg.enterSubRule(31);

            loop31:
            do {
                int alt31=2;
                try { dbg.enterDecision(31);

                int LA31_0 = input.LA(1);

                if ( (LA31_0==COMMA) ) {
                    alt31=1;
                }


                } finally {dbg.exitDecision(31);}

                switch (alt31) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:154:26: COMMA columnRef
            	    {
            	    dbg.location(154,26);
            	    match(input,COMMA,FOLLOW_COMMA_in_groupBy699); if (state.failed) return ;
            	    dbg.location(154,32);
            	    pushFollow(FOLLOW_columnRef_in_groupBy701);
            	    columnRef();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);
            } finally {dbg.exitSubRule(31);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(155, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "groupBy");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "groupBy"


    // $ANTLR start "having"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:157:1: having : HAVING conditionList ;
    public final void having() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "having");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(157, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:158:3: ( HAVING conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:158:5: HAVING conditionList
            {
            dbg.location(158,5);
            match(input,HAVING,FOLLOW_HAVING_in_having719); if (state.failed) return ;
            dbg.location(158,12);
            pushFollow(FOLLOW_conditionList_in_having721);
            conditionList();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(159, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "having");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "having"


    // $ANTLR start "orderBy"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:161:1: orderBy : ORDER BY orderByItem ( COMMA orderByItem )* ;
    public final void orderBy() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "orderBy");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(161, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:3: ( ORDER BY orderByItem ( COMMA orderByItem )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:6: ORDER BY orderByItem ( COMMA orderByItem )*
            {
            dbg.location(162,6);
            match(input,ORDER,FOLLOW_ORDER_in_orderBy737); if (state.failed) return ;
            dbg.location(162,12);
            match(input,BY,FOLLOW_BY_in_orderBy739); if (state.failed) return ;
            dbg.location(162,15);
            pushFollow(FOLLOW_orderByItem_in_orderBy741);
            orderByItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(162,27);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:27: ( COMMA orderByItem )*
            try { dbg.enterSubRule(32);

            loop32:
            do {
                int alt32=2;
                try { dbg.enterDecision(32);

                int LA32_0 = input.LA(1);

                if ( (LA32_0==COMMA) ) {
                    alt32=1;
                }


                } finally {dbg.exitDecision(32);}

                switch (alt32) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:29: COMMA orderByItem
            	    {
            	    dbg.location(162,29);
            	    match(input,COMMA,FOLLOW_COMMA_in_orderBy745); if (state.failed) return ;
            	    dbg.location(162,35);
            	    pushFollow(FOLLOW_orderByItem_in_orderBy747);
            	    orderByItem();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);
            } finally {dbg.exitSubRule(32);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(163, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "orderBy");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "orderBy"


    // $ANTLR start "orderByItem"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:165:1: orderByItem : columnRef ( ASC | DESC )? ;
    public final void orderByItem() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "orderByItem");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(165, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:166:3: ( columnRef ( ASC | DESC )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:166:5: columnRef ( ASC | DESC )?
            {
            dbg.location(166,5);
            pushFollow(FOLLOW_columnRef_in_orderByItem765);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(166,15);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:166:15: ( ASC | DESC )?
            int alt33=2;
            try { dbg.enterSubRule(33);
            try { dbg.enterDecision(33);

            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=ASC && LA33_0<=DESC)) ) {
                alt33=1;
            }
            } finally {dbg.exitDecision(33);}

            switch (alt33) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
                    {
                    dbg.location(166,15);
                    if ( (input.LA(1)>=ASC && input.LA(1)<=DESC) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        dbg.recognitionException(mse);
                        throw mse;
                    }


                    }
                    break;

            }
            } finally {dbg.exitSubRule(33);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(167, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "orderByItem");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "orderByItem"


    // $ANTLR start "nestedCondition"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:169:1: nestedCondition : LPAREN conditionList RPAREN ;
    public final void nestedCondition() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "nestedCondition");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(169, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:170:3: ( LPAREN conditionList RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:170:5: LPAREN conditionList RPAREN
            {
            dbg.location(170,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedCondition791); if (state.failed) return ;
            dbg.location(170,12);
            pushFollow(FOLLOW_conditionList_in_nestedCondition793);
            conditionList();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(170,26);
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedCondition795); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(171, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "nestedCondition");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "nestedCondition"


    // $ANTLR start "conditionList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:173:1: conditionList : condition ( ( OR | AND ) condition )* ;
    public final void conditionList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "conditionList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(173, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:174:3: ( condition ( ( OR | AND ) condition )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:174:5: condition ( ( OR | AND ) condition )*
            {
            dbg.location(174,5);
            pushFollow(FOLLOW_condition_in_conditionList810);
            condition();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(174,15);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:174:15: ( ( OR | AND ) condition )*
            try { dbg.enterSubRule(34);

            loop34:
            do {
                int alt34=2;
                try { dbg.enterDecision(34);

                int LA34_0 = input.LA(1);

                if ( ((LA34_0>=OR && LA34_0<=AND)) ) {
                    alt34=1;
                }


                } finally {dbg.exitDecision(34);}

                switch (alt34) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:174:17: ( OR | AND ) condition
            	    {
            	    dbg.location(174,17);
            	    if ( (input.LA(1)>=OR && input.LA(1)<=AND) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(174,30);
            	    pushFollow(FOLLOW_condition_in_conditionList824);
            	    condition();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);
            } finally {dbg.exitSubRule(34);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(175, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "conditionList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "conditionList"


    // $ANTLR start "condition"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:177:1: condition : ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison ) ;
    public final void condition() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "condition");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(177, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:178:3: ( ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison ) )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:178:5: ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )
            {
            dbg.location(178,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:178:5: ( NOT )?
            int alt35=2;
            try { dbg.enterSubRule(35);
            try { dbg.enterDecision(35);

            int LA35_0 = input.LA(1);

            if ( (LA35_0==NOT) ) {
                alt35=1;
            }
            } finally {dbg.exitDecision(35);}

            switch (alt35) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:178:7: NOT
                    {
                    dbg.location(178,7);
                    match(input,NOT,FOLLOW_NOT_in_condition844); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(35);}

            dbg.location(179,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:5: ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )
            int alt36=8;
            try { dbg.enterSubRule(36);
            try { dbg.enterDecision(36);

            try {
                isCyclicDecision = true;
                alt36 = dfa36.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(36);}

            switch (alt36) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:7: nestedCondition
                    {
                    dbg.location(179,7);
                    pushFollow(FOLLOW_nestedCondition_in_condition855);
                    nestedCondition();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:180:7: in
                    {
                    dbg.location(180,7);
                    pushFollow(FOLLOW_in_in_condition863);
                    in();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:181:7: between
                    {
                    dbg.location(181,7);
                    pushFollow(FOLLOW_between_in_condition871);
                    between();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:182:7: isNull
                    {
                    dbg.location(182,7);
                    pushFollow(FOLLOW_isNull_in_condition879);
                    isNull();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:7: exists
                    {
                    dbg.location(183,7);
                    pushFollow(FOLLOW_exists_in_condition887);
                    exists();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:184:7: like
                    {
                    dbg.location(184,7);
                    pushFollow(FOLLOW_like_in_condition895);
                    like();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:185:7: quantifier
                    {
                    dbg.location(185,7);
                    pushFollow(FOLLOW_quantifier_in_condition903);
                    quantifier();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:186:6: comparison
                    {
                    dbg.location(186,6);
                    pushFollow(FOLLOW_comparison_in_condition910);
                    comparison();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(36);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(188, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "condition");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "condition"


    // $ANTLR start "in"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:190:1: in : expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN ;
    public final void in() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "in");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(190, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:3: ( expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:5: expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN
            {
            dbg.location(191,5);
            pushFollow(FOLLOW_expression_in_in929);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(191,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:16: ( NOT )?
            int alt37=2;
            try { dbg.enterSubRule(37);
            try { dbg.enterDecision(37);

            int LA37_0 = input.LA(1);

            if ( (LA37_0==NOT) ) {
                alt37=1;
            }
            } finally {dbg.exitDecision(37);}

            switch (alt37) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:18: NOT
                    {
                    dbg.location(191,18);
                    match(input,NOT,FOLLOW_NOT_in_in933); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(37);}

            dbg.location(191,25);
            match(input,IN,FOLLOW_IN_in_in938); if (state.failed) return ;
            dbg.location(191,28);
            match(input,LPAREN,FOLLOW_LPAREN_in_in940); if (state.failed) return ;
            dbg.location(191,35);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:35: ( subSelect | expressionList )
            int alt38=2;
            try { dbg.enterSubRule(38);
            try { dbg.enterDecision(38);

            switch ( input.LA(1) ) {
            case SELECT:
                {
                alt38=1;
                }
                break;
            case LPAREN:
                {
                int LA38_2 = input.LA(2);

                if ( (LA38_2==SELECT) ) {
                    alt38=1;
                }
                else if ( (LA38_2==LPAREN||LA38_2==Integer||LA38_2==Identifier||(LA38_2>=PLUS && LA38_2<=MINUS)||(LA38_2>=Float && LA38_2<=FALSE)||LA38_2==QuotedIdentifier||(LA38_2>=109 && LA38_2<=111)) ) {
                    alt38=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 2, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case Integer:
            case Identifier:
            case PLUS:
            case MINUS:
            case Float:
            case String:
            case TRUE:
            case FALSE:
            case QuotedIdentifier:
            case 109:
            case 110:
            case 111:
                {
                alt38=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(38);}

            switch (alt38) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:37: subSelect
                    {
                    dbg.location(191,37);
                    pushFollow(FOLLOW_subSelect_in_in944);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:49: expressionList
                    {
                    dbg.location(191,49);
                    pushFollow(FOLLOW_expressionList_in_in948);
                    expressionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(38);}

            dbg.location(191,66);
            match(input,RPAREN,FOLLOW_RPAREN_in_in952); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(192, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "in");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "in"


    // $ANTLR start "between"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:194:1: between : expression ( NOT )? BETWEEN expression AND expression ;
    public final void between() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "between");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(194, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:195:3: ( expression ( NOT )? BETWEEN expression AND expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:195:5: expression ( NOT )? BETWEEN expression AND expression
            {
            dbg.location(195,5);
            pushFollow(FOLLOW_expression_in_between967);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(195,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:195:16: ( NOT )?
            int alt39=2;
            try { dbg.enterSubRule(39);
            try { dbg.enterDecision(39);

            int LA39_0 = input.LA(1);

            if ( (LA39_0==NOT) ) {
                alt39=1;
            }
            } finally {dbg.exitDecision(39);}

            switch (alt39) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:195:18: NOT
                    {
                    dbg.location(195,18);
                    match(input,NOT,FOLLOW_NOT_in_between971); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(39);}

            dbg.location(195,25);
            match(input,BETWEEN,FOLLOW_BETWEEN_in_between976); if (state.failed) return ;
            dbg.location(195,33);
            pushFollow(FOLLOW_expression_in_between978);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(195,44);
            match(input,AND,FOLLOW_AND_in_between980); if (state.failed) return ;
            dbg.location(195,48);
            pushFollow(FOLLOW_expression_in_between982);
            expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(196, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "between");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "between"


    // $ANTLR start "isNull"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:198:1: isNull : expression IS ( NOT )? NULL ;
    public final void isNull() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "isNull");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(198, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:199:3: ( expression IS ( NOT )? NULL )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:199:5: expression IS ( NOT )? NULL
            {
            dbg.location(199,5);
            pushFollow(FOLLOW_expression_in_isNull997);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(199,16);
            match(input,IS,FOLLOW_IS_in_isNull999); if (state.failed) return ;
            dbg.location(199,19);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:199:19: ( NOT )?
            int alt40=2;
            try { dbg.enterSubRule(40);
            try { dbg.enterDecision(40);

            int LA40_0 = input.LA(1);

            if ( (LA40_0==NOT) ) {
                alt40=1;
            }
            } finally {dbg.exitDecision(40);}

            switch (alt40) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:199:21: NOT
                    {
                    dbg.location(199,21);
                    match(input,NOT,FOLLOW_NOT_in_isNull1003); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(40);}

            dbg.location(199,28);
            match(input,NULL,FOLLOW_NULL_in_isNull1008); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(200, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "isNull");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "isNull"


    // $ANTLR start "exists"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:202:1: exists : EXISTS expression ;
    public final void exists() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "exists");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(202, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:203:3: ( EXISTS expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:203:5: EXISTS expression
            {
            dbg.location(203,5);
            match(input,EXISTS,FOLLOW_EXISTS_in_exists1023); if (state.failed) return ;
            dbg.location(203,12);
            pushFollow(FOLLOW_expression_in_exists1025);
            expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(204, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "exists");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "exists"


    // $ANTLR start "like"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:206:1: like : expression ( NOT )? LIKE expression ;
    public final void like() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "like");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(206, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:207:3: ( expression ( NOT )? LIKE expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:207:5: expression ( NOT )? LIKE expression
            {
            dbg.location(207,5);
            pushFollow(FOLLOW_expression_in_like1040);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(207,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:207:16: ( NOT )?
            int alt41=2;
            try { dbg.enterSubRule(41);
            try { dbg.enterDecision(41);

            int LA41_0 = input.LA(1);

            if ( (LA41_0==NOT) ) {
                alt41=1;
            }
            } finally {dbg.exitDecision(41);}

            switch (alt41) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:207:18: NOT
                    {
                    dbg.location(207,18);
                    match(input,NOT,FOLLOW_NOT_in_like1044); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(41);}

            dbg.location(207,25);
            match(input,LIKE,FOLLOW_LIKE_in_like1049); if (state.failed) return ;
            dbg.location(207,30);
            pushFollow(FOLLOW_expression_in_like1051);
            expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(209, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "like");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "like"


    // $ANTLR start "comparison"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:211:1: comparison : expression comparator expression ;
    public final void comparison() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "comparison");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(211, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:212:3: ( expression comparator expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:212:5: expression comparator expression
            {
            dbg.location(212,5);
            pushFollow(FOLLOW_expression_in_comparison1069);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(212,16);
            pushFollow(FOLLOW_comparator_in_comparison1071);
            comparator();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(212,27);
            pushFollow(FOLLOW_expression_in_comparison1073);
            expression();

            state._fsp--;
            if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(213, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "comparison");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "comparison"


    // $ANTLR start "comparator"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:215:1: comparator : ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT );
    public final void comparator() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "comparator");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(215, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:216:3: ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(216,3);
            if ( (input.LA(1)>=EQ && input.LA(1)<=GT) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(223, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "comparator");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "comparator"


    // $ANTLR start "quantifier"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:225:1: quantifier : expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN ;
    public final void quantifier() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "quantifier");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(225, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:3: ( expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:5: expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN
            {
            dbg.location(226,5);
            pushFollow(FOLLOW_expression_in_quantifier1138);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(226,16);
            if ( input.LA(1)==ALL||(input.LA(1)>=ANY && input.LA(1)<=SOME) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }

            dbg.location(226,37);
            match(input,LPAREN,FOLLOW_LPAREN_in_quantifier1154); if (state.failed) return ;
            dbg.location(226,44);
            pushFollow(FOLLOW_subSelect_in_quantifier1156);
            subSelect();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(226,54);
            match(input,RPAREN,FOLLOW_RPAREN_in_quantifier1158); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(227, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "quantifier");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "quantifier"


    // $ANTLR start "expressionList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:229:1: expressionList : expression ( COMMA expression )* ;
    public final void expressionList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expressionList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(229, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:230:3: ( expression ( COMMA expression )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:230:5: expression ( COMMA expression )*
            {
            dbg.location(230,5);
            pushFollow(FOLLOW_expression_in_expressionList1173);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(230,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:230:16: ( COMMA expression )*
            try { dbg.enterSubRule(42);

            loop42:
            do {
                int alt42=2;
                try { dbg.enterDecision(42);

                int LA42_0 = input.LA(1);

                if ( (LA42_0==COMMA) ) {
                    alt42=1;
                }


                } finally {dbg.exitDecision(42);}

                switch (alt42) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:230:18: COMMA expression
            	    {
            	    dbg.location(230,18);
            	    match(input,COMMA,FOLLOW_COMMA_in_expressionList1177); if (state.failed) return ;
            	    dbg.location(230,24);
            	    pushFollow(FOLLOW_expression_in_expressionList1179);
            	    expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);
            } finally {dbg.exitSubRule(42);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(231, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expressionList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "expressionList"


    // $ANTLR start "nestedExpression"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:233:1: nestedExpression : LPAREN expression RPAREN ;
    public final void nestedExpression() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "nestedExpression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(233, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:234:3: ( LPAREN expression RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:234:5: LPAREN expression RPAREN
            {
            dbg.location(234,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedExpression1195); if (state.failed) return ;
            dbg.location(234,12);
            pushFollow(FOLLOW_expression_in_nestedExpression1197);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(234,23);
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedExpression1199); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(235, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "nestedExpression");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "nestedExpression"


    // $ANTLR start "expression"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:237:1: expression : multiply ( ( PLUS | MINUS ) multiply )* ;
    public final void expression() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(237, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:238:3: ( multiply ( ( PLUS | MINUS ) multiply )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:238:5: multiply ( ( PLUS | MINUS ) multiply )*
            {
            dbg.location(238,5);
            pushFollow(FOLLOW_multiply_in_expression1214);
            multiply();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(238,14);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:238:14: ( ( PLUS | MINUS ) multiply )*
            try { dbg.enterSubRule(43);

            loop43:
            do {
                int alt43=2;
                try { dbg.enterDecision(43);

                int LA43_0 = input.LA(1);

                if ( ((LA43_0>=PLUS && LA43_0<=MINUS)) ) {
                    alt43=1;
                }


                } finally {dbg.exitDecision(43);}

                switch (alt43) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:238:16: ( PLUS | MINUS ) multiply
            	    {
            	    dbg.location(238,16);
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(238,33);
            	    pushFollow(FOLLOW_multiply_in_expression1228);
            	    multiply();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);
            } finally {dbg.exitSubRule(43);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(239, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "expression");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "expression"


    // $ANTLR start "multiply"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:241:1: multiply : value ( ( STAR | DIVIDE ) value )* ;
    public final void multiply() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "multiply");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(241, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:242:3: ( value ( ( STAR | DIVIDE ) value )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:242:5: value ( ( STAR | DIVIDE ) value )*
            {
            dbg.location(242,5);
            pushFollow(FOLLOW_value_in_multiply1245);
            value();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(242,11);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:242:11: ( ( STAR | DIVIDE ) value )*
            try { dbg.enterSubRule(44);

            loop44:
            do {
                int alt44=2;
                try { dbg.enterDecision(44);

                int LA44_0 = input.LA(1);

                if ( (LA44_0==STAR||LA44_0==DIVIDE) ) {
                    alt44=1;
                }


                } finally {dbg.exitDecision(44);}

                switch (alt44) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:242:13: ( STAR | DIVIDE ) value
            	    {
            	    dbg.location(242,13);
            	    if ( input.LA(1)==STAR||input.LA(1)==DIVIDE ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        dbg.recognitionException(mse);
            	        throw mse;
            	    }

            	    dbg.location(242,31);
            	    pushFollow(FOLLOW_value_in_multiply1259);
            	    value();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop44;
                }
            } while (true);
            } finally {dbg.exitSubRule(44);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(243, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "multiply");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "multiply"


    // $ANTLR start "value"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:260:1: value : ( literal | ( unary )? ( columnRef | nestedExpression ) );
    public final void value() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "value");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(260, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:261:3: ( literal | ( unary )? ( columnRef | nestedExpression ) )
            int alt47=2;
            try { dbg.enterDecision(47);

            switch ( input.LA(1) ) {
            case PLUS:
            case MINUS:
                {
                int LA47_1 = input.LA(2);

                if ( (LA47_1==Integer||LA47_1==Float) ) {
                    alt47=1;
                }
                else if ( (LA47_1==LPAREN||LA47_1==Identifier||LA47_1==QuotedIdentifier) ) {
                    alt47=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 47, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case Integer:
            case Float:
            case String:
            case TRUE:
            case FALSE:
            case 109:
            case 110:
            case 111:
                {
                alt47=1;
                }
                break;
            case LPAREN:
            case Identifier:
            case QuotedIdentifier:
                {
                alt47=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 47, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(47);}

            switch (alt47) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:261:5: literal
                    {
                    dbg.location(261,5);
                    pushFollow(FOLLOW_literal_in_value1298);
                    literal();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:263:5: ( unary )? ( columnRef | nestedExpression )
                    {
                    dbg.location(263,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:263:5: ( unary )?
                    int alt45=2;
                    try { dbg.enterSubRule(45);
                    try { dbg.enterDecision(45);

                    int LA45_0 = input.LA(1);

                    if ( ((LA45_0>=PLUS && LA45_0<=MINUS)) ) {
                        alt45=1;
                    }
                    } finally {dbg.exitDecision(45);}

                    switch (alt45) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:263:7: unary
                            {
                            dbg.location(263,7);
                            pushFollow(FOLLOW_unary_in_value1308);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(45);}

                    dbg.location(264,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:264:5: ( columnRef | nestedExpression )
                    int alt46=2;
                    try { dbg.enterSubRule(46);
                    try { dbg.enterDecision(46);

                    int LA46_0 = input.LA(1);

                    if ( (LA46_0==Identifier||LA46_0==QuotedIdentifier) ) {
                        alt46=1;
                    }
                    else if ( (LA46_0==LPAREN) ) {
                        alt46=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 46, 0, input);

                        dbg.recognitionException(nvae);
                        throw nvae;
                    }
                    } finally {dbg.exitDecision(46);}

                    switch (alt46) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:264:7: columnRef
                            {
                            dbg.location(264,7);
                            pushFollow(FOLLOW_columnRef_in_value1319);
                            columnRef();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            dbg.enterAlt(2);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:265:7: nestedExpression
                            {
                            dbg.location(265,7);
                            pushFollow(FOLLOW_nestedExpression_in_value1327);
                            nestedExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(46);}


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(268, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "value");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "value"


    // $ANTLR start "literal"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:270:1: literal : ( ( unary )? Float | ( unary )? Integer | String | TRUE | FALSE | date );
    public final void literal() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "literal");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(270, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:271:3: ( ( unary )? Float | ( unary )? Integer | String | TRUE | FALSE | date )
            int alt50=6;
            try { dbg.enterDecision(50);

            switch ( input.LA(1) ) {
            case PLUS:
            case MINUS:
                {
                int LA50_1 = input.LA(2);

                if ( (LA50_1==Float) ) {
                    alt50=1;
                }
                else if ( (LA50_1==Integer) ) {
                    alt50=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 50, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case Float:
                {
                alt50=1;
                }
                break;
            case Integer:
                {
                alt50=2;
                }
                break;
            case String:
                {
                alt50=3;
                }
                break;
            case TRUE:
                {
                alt50=4;
                }
                break;
            case FALSE:
                {
                alt50=5;
                }
                break;
            case 109:
            case 110:
            case 111:
                {
                alt50=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 50, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(50);}

            switch (alt50) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:271:5: ( unary )? Float
                    {
                    dbg.location(271,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:271:5: ( unary )?
                    int alt48=2;
                    try { dbg.enterSubRule(48);
                    try { dbg.enterDecision(48);

                    int LA48_0 = input.LA(1);

                    if ( ((LA48_0>=PLUS && LA48_0<=MINUS)) ) {
                        alt48=1;
                    }
                    } finally {dbg.exitDecision(48);}

                    switch (alt48) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:271:7: unary
                            {
                            dbg.location(271,7);
                            pushFollow(FOLLOW_unary_in_literal1351);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(48);}

                    dbg.location(271,16);
                    match(input,Float,FOLLOW_Float_in_literal1356); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:272:5: ( unary )? Integer
                    {
                    dbg.location(272,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:272:5: ( unary )?
                    int alt49=2;
                    try { dbg.enterSubRule(49);
                    try { dbg.enterDecision(49);

                    int LA49_0 = input.LA(1);

                    if ( ((LA49_0>=PLUS && LA49_0<=MINUS)) ) {
                        alt49=1;
                    }
                    } finally {dbg.exitDecision(49);}

                    switch (alt49) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:272:7: unary
                            {
                            dbg.location(272,7);
                            pushFollow(FOLLOW_unary_in_literal1364);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(49);}

                    dbg.location(272,16);
                    match(input,Integer,FOLLOW_Integer_in_literal1369); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:273:5: String
                    {
                    dbg.location(273,5);
                    match(input,String,FOLLOW_String_in_literal1375); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:274:5: TRUE
                    {
                    dbg.location(274,5);
                    match(input,TRUE,FOLLOW_TRUE_in_literal1381); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:275:5: FALSE
                    {
                    dbg.location(275,5);
                    match(input,FALSE,FOLLOW_FALSE_in_literal1387); if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:276:5: date
                    {
                    dbg.location(276,5);
                    pushFollow(FOLLOW_date_in_literal1393);
                    date();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(277, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "literal");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "literal"


    // $ANTLR start "date"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:279:1: date : ( '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' );
    public final void date() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "date");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(279, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:280:3: ( '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' )
            int alt51=3;
            try { dbg.enterDecision(51);

            switch ( input.LA(1) ) {
            case 109:
                {
                alt51=1;
                }
                break;
            case 110:
                {
                alt51=2;
                }
                break;
            case 111:
                {
                alt51=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 51, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(51);}

            switch (alt51) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:280:5: '{d' Timestamp '}'
                    {
                    dbg.location(280,5);
                    match(input,109,FOLLOW_109_in_date1406); if (state.failed) return ;
                    dbg.location(280,10);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1408); if (state.failed) return ;
                    dbg.location(280,20);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1410); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:281:5: '{t' Timestamp '}'
                    {
                    dbg.location(281,5);
                    match(input,110,FOLLOW_110_in_date1417); if (state.failed) return ;
                    dbg.location(281,10);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1419); if (state.failed) return ;
                    dbg.location(281,20);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1421); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:282:5: '{ts' Timestamp '}'
                    {
                    dbg.location(282,5);
                    match(input,111,FOLLOW_111_in_date1428); if (state.failed) return ;
                    dbg.location(282,11);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1430); if (state.failed) return ;
                    dbg.location(282,21);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1432); if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(283, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "date");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "date"


    // $ANTLR start "unary"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:285:1: unary : ( MINUS | PLUS );
    public final void unary() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "unary");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(285, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:286:3: ( MINUS | PLUS )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(286,3);
            if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(288, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "unary");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "unary"


    // $ANTLR start "tableRef"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:307:1: tableRef : ( tableName | databaseName DOT tableName );
    public final void tableRef() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableRef");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(307, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:308:3: ( tableName | databaseName DOT tableName )
            int alt52=2;
            try { dbg.enterDecision(52);

            int LA52_0 = input.LA(1);

            if ( (LA52_0==Identifier||LA52_0==QuotedIdentifier) ) {
                int LA52_1 = input.LA(2);

                if ( (LA52_1==EOF||(LA52_1>=SEMI && LA52_1<=RPAREN)||(LA52_1>=COMMA && LA52_1<=VALUES)||LA52_1==AS||(LA52_1>=Identifier && LA52_1<=FROM)||(LA52_1>=WHERE && LA52_1<=GROUP)||(LA52_1>=HAVING && LA52_1<=ORDER)) ) {
                    alt52=1;
                }
                else if ( (LA52_1==DOT) ) {
                    alt52=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 52, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(52);}

            switch (alt52) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:308:5: tableName
                    {
                    dbg.location(308,5);
                    pushFollow(FOLLOW_tableName_in_tableRef1487);
                    tableName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:309:5: databaseName DOT tableName
                    {
                    dbg.location(309,5);
                    pushFollow(FOLLOW_databaseName_in_tableRef1493);
                    databaseName();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(309,18);
                    match(input,DOT,FOLLOW_DOT_in_tableRef1495); if (state.failed) return ;
                    dbg.location(309,22);
                    pushFollow(FOLLOW_tableName_in_tableRef1497);
                    tableName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(310, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "tableRef");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "tableRef"


    // $ANTLR start "columnRef"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:312:1: columnRef : ( columnName | tableAlias DOT columnName );
    public final void columnRef() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnRef");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(312, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:313:3: ( columnName | tableAlias DOT columnName )
            int alt53=2;
            try { dbg.enterDecision(53);

            int LA53_0 = input.LA(1);

            if ( (LA53_0==Identifier) ) {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==EOF||LA53_1==SEMI||LA53_1==RPAREN||LA53_1==ALL||(LA53_1>=INTO && LA53_1<=COMMA)||(LA53_1>=STAR && LA53_1<=AS)||(LA53_1>=Identifier && LA53_1<=FULL)||LA53_1==JOIN||LA53_1==GROUP||(LA53_1>=HAVING && LA53_1<=IS)||(LA53_1>=LIKE && LA53_1<=DIVIDE)) ) {
                    alt53=1;
                }
                else if ( (LA53_1==DOT) ) {
                    alt53=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else if ( (LA53_0==QuotedIdentifier) ) {
                alt53=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(53);}

            switch (alt53) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:313:5: columnName
                    {
                    dbg.location(313,5);
                    pushFollow(FOLLOW_columnName_in_columnRef1512);
                    columnName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:314:5: tableAlias DOT columnName
                    {
                    dbg.location(314,5);
                    pushFollow(FOLLOW_tableAlias_in_columnRef1519);
                    tableAlias();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(314,16);
                    match(input,DOT,FOLLOW_DOT_in_columnRef1521); if (state.failed) return ;
                    dbg.location(314,20);
                    pushFollow(FOLLOW_columnName_in_columnRef1523);
                    columnName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(315, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "columnRef");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "columnRef"


    // $ANTLR start "databaseName"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:317:1: databaseName : ( Identifier | QuotedIdentifier );
    public final void databaseName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "databaseName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(317, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:318:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(318,3);
            if ( input.LA(1)==Identifier||input.LA(1)==QuotedIdentifier ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(320, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "databaseName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "databaseName"


    // $ANTLR start "tableName"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:322:1: tableName : ( Identifier | QuotedIdentifier );
    public final void tableName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(322, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:323:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(323,3);
            if ( input.LA(1)==Identifier||input.LA(1)==QuotedIdentifier ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(325, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "tableName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "tableName"


    // $ANTLR start "tableAlias"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:327:1: tableAlias : Identifier ;
    public final void tableAlias() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableAlias");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(327, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:328:3: ( Identifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:328:5: Identifier
            {
            dbg.location(328,5);
            match(input,Identifier,FOLLOW_Identifier_in_tableAlias1578); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(329, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "tableAlias");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "tableAlias"


    // $ANTLR start "columnName"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:331:1: columnName : ( Identifier | QuotedIdentifier );
    public final void columnName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(331, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:332:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(332,3);
            if ( input.LA(1)==Identifier||input.LA(1)==QuotedIdentifier ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                dbg.recognitionException(mse);
                throw mse;
            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(334, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "columnName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "columnName"

    // $ANTLR start synpred44_GenericSQL
    public final void synpred44_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:7: ( nestedCondition )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:7: nestedCondition
        {
        dbg.location(179,7);
        pushFollow(FOLLOW_nestedCondition_in_synpred44_GenericSQL855);
        nestedCondition();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred44_GenericSQL

    // $ANTLR start synpred45_GenericSQL
    public final void synpred45_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:180:7: ( in )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:180:7: in
        {
        dbg.location(180,7);
        pushFollow(FOLLOW_in_in_synpred45_GenericSQL863);
        in();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred45_GenericSQL

    // $ANTLR start synpred46_GenericSQL
    public final void synpred46_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:181:7: ( between )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:181:7: between
        {
        dbg.location(181,7);
        pushFollow(FOLLOW_between_in_synpred46_GenericSQL871);
        between();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred46_GenericSQL

    // $ANTLR start synpred47_GenericSQL
    public final void synpred47_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:182:7: ( isNull )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:182:7: isNull
        {
        dbg.location(182,7);
        pushFollow(FOLLOW_isNull_in_synpred47_GenericSQL879);
        isNull();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred47_GenericSQL

    // $ANTLR start synpred49_GenericSQL
    public final void synpred49_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:184:7: ( like )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:184:7: like
        {
        dbg.location(184,7);
        pushFollow(FOLLOW_like_in_synpred49_GenericSQL895);
        like();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred49_GenericSQL

    // $ANTLR start synpred50_GenericSQL
    public final void synpred50_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:185:7: ( quantifier )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:185:7: quantifier
        {
        dbg.location(185,7);
        pushFollow(FOLLOW_quantifier_in_synpred50_GenericSQL903);
        quantifier();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred50_GenericSQL

    // Delegated rules

    public final boolean synpred46_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred46_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        dbg.endBacktrack(state.backtracking, success);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred50_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred50_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        dbg.endBacktrack(state.backtracking, success);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred44_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred44_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        dbg.endBacktrack(state.backtracking, success);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred49_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred49_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        dbg.endBacktrack(state.backtracking, success);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred45_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred45_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        dbg.endBacktrack(state.backtracking, success);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred47_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred47_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        dbg.endBacktrack(state.backtracking, success);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA36 dfa36 = new DFA36(this);
    static final String DFA36_eotS =
        "\25\uffff";
    static final String DFA36_eofS =
        "\25\uffff";
    static final String DFA36_minS =
        "\1\5\14\0\10\uffff";
    static final String DFA36_maxS =
        "\1\157\14\0\10\uffff";
    static final String DFA36_acceptS =
        "\15\uffff\1\5\1\1\1\2\1\3\1\4\1\6\1\7\1\10";
    static final String DFA36_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\10"+
        "\uffff}>";
    static final String[] DFA36_transitionS = {
            "\1\1\6\uffff\1\4\10\uffff\1\13\27\uffff\1\15\12\uffff\2\2\1"+
            "\uffff\1\3\1\5\1\6\1\7\1\uffff\1\14\54\uffff\1\10\1\11\1\12",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA36_eot = DFA.unpackEncodedString(DFA36_eotS);
    static final short[] DFA36_eof = DFA.unpackEncodedString(DFA36_eofS);
    static final char[] DFA36_min = DFA.unpackEncodedStringToUnsignedChars(DFA36_minS);
    static final char[] DFA36_max = DFA.unpackEncodedStringToUnsignedChars(DFA36_maxS);
    static final short[] DFA36_accept = DFA.unpackEncodedString(DFA36_acceptS);
    static final short[] DFA36_special = DFA.unpackEncodedString(DFA36_specialS);
    static final short[][] DFA36_transition;

    static {
        int numStates = DFA36_transitionS.length;
        DFA36_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA36_transition[i] = DFA.unpackEncodedString(DFA36_transitionS[i]);
        }
    }

    class DFA36 extends DFA {

        public DFA36(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 36;
            this.eot = DFA36_eot;
            this.eof = DFA36_eof;
            this.min = DFA36_min;
            this.max = DFA36_max;
            this.accept = DFA36_accept;
            this.special = DFA36_special;
            this.transition = DFA36_transition;
        }
        public String getDescription() {
            return "179:5: ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA36_1 = input.LA(1);

                         
                        int index36_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred44_GenericSQL()) ) {s = 14;}

                        else if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA36_2 = input.LA(1);

                         
                        int index36_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA36_3 = input.LA(1);

                         
                        int index36_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA36_4 = input.LA(1);

                         
                        int index36_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA36_5 = input.LA(1);

                         
                        int index36_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA36_6 = input.LA(1);

                         
                        int index36_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA36_7 = input.LA(1);

                         
                        int index36_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA36_8 = input.LA(1);

                         
                        int index36_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA36_9 = input.LA(1);

                         
                        int index36_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA36_10 = input.LA(1);

                         
                        int index36_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA36_11 = input.LA(1);

                         
                        int index36_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA36_12 = input.LA(1);

                         
                        int index36_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (synpred46_GenericSQL()) ) {s = 16;}

                        else if ( (synpred47_GenericSQL()) ) {s = 17;}

                        else if ( (synpred49_GenericSQL()) ) {s = 18;}

                        else if ( (synpred50_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index36_12);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 36, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_select_in_statement69 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_statement73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_statement78 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_insert_in_statement84 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_statement88 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_statement93 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_subSelect110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_subSelect116 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_select_in_subSelect118 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_subSelect120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_in_select135 = new BitSet(new long[]{0x7B00000000241F20L,0x0000E00000000001L});
    public static final BitSet FOLLOW_set_in_select141 = new BitSet(new long[]{0x7B00000000241F20L,0x0000E00000000001L});
    public static final BitSet FOLLOW_TOP_in_select162 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Integer_in_select164 = new BitSet(new long[]{0x7B00000000243F20L,0x0000E00000000001L});
    public static final BitSet FOLLOW_PERCENT_in_select168 = new BitSet(new long[]{0x7B00000000241F20L,0x0000E00000000001L});
    public static final BitSet FOLLOW_itemList_in_select180 = new BitSet(new long[]{0x0000000000408000L});
    public static final BitSet FOLLOW_into_in_select188 = new BitSet(new long[]{0x0000000000408000L});
    public static final BitSet FOLLOW_from_in_select197 = new BitSet(new long[]{0x0000000D80000002L});
    public static final BitSet FOLLOW_where_in_select206 = new BitSet(new long[]{0x0000000D00000002L});
    public static final BitSet FOLLOW_groupBy_in_select217 = new BitSet(new long[]{0x0000000C00000002L});
    public static final BitSet FOLLOW_having_in_select228 = new BitSet(new long[]{0x0000000800000002L});
    public static final BitSet FOLLOW_orderBy_in_select239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INSERT_in_insert257 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_into_in_insert259 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_columnList_in_insert263 = new BitSet(new long[]{0x0000000000020020L});
    public static final BitSet FOLLOW_values_in_insert272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTO_in_into292 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_tableRef_in_into294 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_COMMA_in_into298 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_tableRef_in_into300 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_LPAREN_in_columnList316 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_columnName_in_columnList318 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_COMMA_in_columnList322 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_columnName_in_columnList324 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_RPAREN_in_columnList329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUES_in_values344 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_values346 = new BitSet(new long[]{0x7B00000000001000L,0x0000E00000000000L});
    public static final BitSet FOLLOW_literal_in_values348 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_COMMA_in_values352 = new BitSet(new long[]{0x7B00000000001000L,0x0000E00000000000L});
    public static final BitSet FOLLOW_literal_in_values354 = new BitSet(new long[]{0x0000000000010040L});
    public static final BitSet FOLLOW_RPAREN_in_values359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_itemList374 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_item_in_itemList380 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_COMMA_in_itemList384 = new BitSet(new long[]{0x7B00000000241F20L,0x0000E00000000001L});
    public static final BitSet FOLLOW_item_in_itemList386 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_value_in_item404 = new BitSet(new long[]{0x0000000000280002L});
    public static final BitSet FOLLOW_AS_in_item410 = new BitSet(new long[]{0x0000000000280000L});
    public static final BitSet FOLLOW_alias_in_item415 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_allColumns_in_item424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_allColumns438 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_allColumns440 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_STAR_in_allColumns442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_alias455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_in_from473 = new BitSet(new long[]{0x0000000000200020L,0x0000000000000001L});
    public static final BitSet FOLLOW_fromItem_in_from475 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_COMMA_in_from479 = new BitSet(new long[]{0x0000000000200020L,0x0000000000000001L});
    public static final BitSet FOLLOW_fromItem_in_from481 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_LPAREN_in_fromItem503 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_fromItem505 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_fromItem507 = new BitSet(new long[]{0x0000000000280002L});
    public static final BitSet FOLLOW_tableRef_in_fromItem518 = new BitSet(new long[]{0x0000000000280002L});
    public static final BitSet FOLLOW_AS_in_fromItem535 = new BitSet(new long[]{0x0000000000280000L});
    public static final BitSet FOLLOW_alias_in_fromItem540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_join_in_joinList558 = new BitSet(new long[]{0x0000000017800002L});
    public static final BitSet FOLLOW_INNER_in_join578 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_set_in_join586 = new BitSet(new long[]{0x0000000018000000L});
    public static final BitSet FOLLOW_OUTER_in_join602 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_JOIN_in_join615 = new BitSet(new long[]{0x0000000060000002L});
    public static final BitSet FOLLOW_ON_in_join623 = new BitSet(new long[]{0x7B00210000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_conditionList_in_join625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USING_in_join633 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_join635 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_columnRef_in_join637 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_COMMA_in_join641 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_columnRef_in_join643 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_WHERE_in_where674 = new BitSet(new long[]{0x7B00210000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_conditionList_in_where676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GROUP_in_groupBy691 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_BY_in_groupBy693 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_columnRef_in_groupBy695 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_COMMA_in_groupBy699 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_columnRef_in_groupBy701 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_HAVING_in_having719 = new BitSet(new long[]{0x7B00210000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_conditionList_in_having721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORDER_in_orderBy737 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_BY_in_orderBy739 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy741 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_COMMA_in_orderBy745 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy747 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_columnRef_in_orderByItem765 = new BitSet(new long[]{0x0000003000000002L});
    public static final BitSet FOLLOW_set_in_orderByItem767 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedCondition791 = new BitSet(new long[]{0x7B00210000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_conditionList_in_nestedCondition793 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedCondition795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditionList810 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_set_in_conditionList814 = new BitSet(new long[]{0x7B00210000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_condition_in_conditionList824 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_NOT_in_condition844 = new BitSet(new long[]{0x7B00210000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_nestedCondition_in_condition855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_condition863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_condition871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_condition879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exists_in_condition887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_condition895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_condition903 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_condition910 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_in929 = new BitSet(new long[]{0x0000030000000000L});
    public static final BitSet FOLLOW_NOT_in_in933 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_IN_in_in938 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_in940 = new BitSet(new long[]{0x7B000000002010A0L,0x0000E00000000001L});
    public static final BitSet FOLLOW_subSelect_in_in944 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_expressionList_in_in948 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_in952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_between967 = new BitSet(new long[]{0x0000050000000000L});
    public static final BitSet FOLLOW_NOT_in_between971 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_BETWEEN_in_between976 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_expression_in_between978 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_AND_in_between980 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_expression_in_between982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_isNull997 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_IS_in_isNull999 = new BitSet(new long[]{0x0000110000000000L});
    public static final BitSet FOLLOW_NOT_in_isNull1003 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_NULL_in_isNull1008 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXISTS_in_exists1023 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_expression_in_exists1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_like1040 = new BitSet(new long[]{0x0000410000000000L});
    public static final BitSet FOLLOW_NOT_in_like1044 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_LIKE_in_like1049 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_expression_in_like1051 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_comparison1069 = new BitSet(new long[]{0x003F800000000000L});
    public static final BitSet FOLLOW_comparator_in_comparison1071 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_expression_in_comparison1073 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_comparator0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_quantifier1138 = new BitSet(new long[]{0x00C0000000000100L});
    public static final BitSet FOLLOW_set_in_quantifier1140 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_quantifier1154 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_quantifier1156 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_quantifier1158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList1173 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_COMMA_in_expressionList1177 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_expression_in_expressionList1179 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedExpression1195 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_expression_in_nestedExpression1197 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedExpression1199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiply_in_expression1214 = new BitSet(new long[]{0x0300000000000002L});
    public static final BitSet FOLLOW_set_in_expression1218 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_multiply_in_expression1228 = new BitSet(new long[]{0x0300000000000002L});
    public static final BitSet FOLLOW_value_in_multiply1245 = new BitSet(new long[]{0x0400000000040002L});
    public static final BitSet FOLLOW_set_in_multiply1249 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_value_in_multiply1259 = new BitSet(new long[]{0x0400000000040002L});
    public static final BitSet FOLLOW_literal_in_value1298 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_value1308 = new BitSet(new long[]{0x7B00000000201020L,0x0000E00000000001L});
    public static final BitSet FOLLOW_columnRef_in_value1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedExpression_in_value1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_literal1351 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_Float_in_literal1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_literal1364 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Integer_in_literal1369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_literal1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literal1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literal1387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_in_literal1393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_109_in_date1406 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_Timestamp_in_date1408 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_110_in_date1417 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_Timestamp_in_date1419 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1421 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_111_in_date1428 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_Timestamp_in_date1430 = new BitSet(new long[]{0x0000000000000000L,0x0000001000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_unary0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableName_in_tableRef1487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_databaseName_in_tableRef1493 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_tableRef1495 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_tableName_in_tableRef1497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_columnName_in_columnRef1512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_columnRef1519 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_DOT_in_columnRef1521 = new BitSet(new long[]{0x0000000000200000L,0x0000000000000001L});
    public static final BitSet FOLLOW_columnName_in_columnRef1523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_databaseName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_tableName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_tableAlias1578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_columnName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedCondition_in_synpred44_GenericSQL855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_synpred45_GenericSQL863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_synpred46_GenericSQL871 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_synpred47_GenericSQL879 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_synpred49_GenericSQL895 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_synpred50_GenericSQL903 = new BitSet(new long[]{0x0000000000000002L});

}