// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g 2011-04-04 14:32:03
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SEMI", "LPAREN", "RPAREN", "SELECT", "ALL", "DISTINCT", "UNIQUE", "TOP", "Integer", "PERCENT", "INSERT", "UPDATE", "SET", "COMMA", "EQ", "INTO", "VALUES", "STAR", "AS", "DOT", "Identifier", "FROM", "INNER", "LEFT", "RIGHT", "FULL", "OUTER", "JOIN", "ON", "USING", "WHERE", "GROUP", "BY", "HAVING", "ORDER", "ASC", "DESC", "OR", "AND", "NOT", "IN", "BETWEEN", "IS", "NULL", "EXISTS", "LIKE", "NEQ1", "NEQ2", "LTE", "LT", "GTE", "GT", "ANY", "SOME", "PLUS", "MINUS", "DIVIDE", "Float", "String", "TRUE", "FALSE", "Timestamp", "QuotedIdentifier", "A", "L", "N", "D", "Y", "S", "C", "B", "E", "T", "W", "CASE", "DELETE", "I", "ELSE", "END", "X", "F", "R", "O", "M", "U", "G", "P", "H", "V", "J", "K", "THEN", "UNION", "Q", "WHEN", "Z", "LCURLY", "RCURLY", "STRCAT", "QUESTION", "COLON", "MOD", "Digit", "Exponent", "Comment", "Whitespace", "'{d'", "'{t'", "'{ts'"
    };
    public static final int LT=53;
    public static final int STAR=21;
    public static final int MOD=105;
    public static final int CASE=78;
    public static final int NOT=43;
    public static final int EOF=-1;
    public static final int Identifier=24;
    public static final int RPAREN=6;
    public static final int FULL=29;
    public static final int INSERT=14;
    public static final int USING=33;
    public static final int Comment=108;
    public static final int EQ=18;
    public static final int SELECT=7;
    public static final int INTO=19;
    public static final int DIVIDE=60;
    public static final int D=70;
    public static final int E=75;
    public static final int UNIQUE=10;
    public static final int F=84;
    public static final int G=89;
    public static final int A=67;
    public static final int B=74;
    public static final int ASC=39;
    public static final int C=73;
    public static final int L=68;
    public static final int M=87;
    public static final int N=69;
    public static final int O=86;
    public static final int H=91;
    public static final int NULL=47;
    public static final int I=80;
    public static final int J=93;
    public static final int ELSE=81;
    public static final int K=94;
    public static final int U=88;
    public static final int ON=32;
    public static final int T=76;
    public static final int W=77;
    public static final int LCURLY=100;
    public static final int V=92;
    public static final int Q=97;
    public static final int P=90;
    public static final int DELETE=79;
    public static final int S=72;
    public static final int R=85;
    public static final int Y=71;
    public static final int X=83;
    public static final int Z=99;
    public static final int Float=61;
    public static final int GROUP=35;
    public static final int OR=41;
    public static final int Timestamp=65;
    public static final int GT=55;
    public static final int FROM=25;
    public static final int END=82;
    public static final int FALSE=64;
    public static final int DISTINCT=9;
    public static final int NEQ1=50;
    public static final int WHERE=34;
    public static final int INNER=26;
    public static final int ORDER=38;
    public static final int NEQ2=51;
    public static final int GTE=54;
    public static final int UPDATE=15;
    public static final int Exponent=107;
    public static final int AND=42;
    public static final int LTE=52;
    public static final int LPAREN=5;
    public static final int AS=22;
    public static final int THEN=95;
    public static final int IN=44;
    public static final int COMMA=17;
    public static final int IS=46;
    public static final int LEFT=27;
    public static final int SOME=57;
    public static final int ALL=8;
    public static final int T__111=111;
    public static final int T__110=110;
    public static final int T__112=112;
    public static final int PLUS=58;
    public static final int EXISTS=48;
    public static final int String=62;
    public static final int DOT=23;
    public static final int Whitespace=109;
    public static final int STRCAT=102;
    public static final int LIKE=49;
    public static final int OUTER=30;
    public static final int BY=36;
    public static final int PERCENT=13;
    public static final int VALUES=20;
    public static final int RIGHT=28;
    public static final int SET=16;
    public static final int HAVING=37;
    public static final int MINUS=59;
    public static final int Digit=106;
    public static final int QuotedIdentifier=66;
    public static final int SEMI=4;
    public static final int TRUE=63;
    public static final int JOIN=31;
    public static final int UNION=96;
    public static final int COLON=104;
    public static final int ANY=56;
    public static final int QUESTION=103;
    public static final int WHEN=98;
    public static final int RCURLY=101;
    public static final int DESC=40;
    public static final int TOP=11;
    public static final int BETWEEN=45;
    public static final int Integer=12;

    // delegates
    // delegators

    public static final String[] ruleNames = new String[] {
        "invalidRule", "alias", "columnList", "columnName", "join", "synpred59_GenericSQL", 
        "synpred90_GenericSQL", "synpred77_GenericSQL", "synpred86_GenericSQL", 
        "synpred73_GenericSQL", "synpred58_GenericSQL", "tableRef", "synpred13_GenericSQL", 
        "synpred55_GenericSQL", "select", "synpred21_GenericSQL", "comparison", 
        "insert", "synpred26_GenericSQL", "exists", "synpred14_GenericSQL", 
        "synpred20_GenericSQL", "tableAlias", "synpred34_GenericSQL", "synpred10_GenericSQL", 
        "synpred89_GenericSQL", "synpred84_GenericSQL", "fromItem", "where", 
        "synpred23_GenericSQL", "tableName", "allColumns", "synpred52_GenericSQL", 
        "synpred39_GenericSQL", "synpred2_GenericSQL", "synpred5_GenericSQL", 
        "synpred8_GenericSQL", "synpred71_GenericSQL", "synpred69_GenericSQL", 
        "synpred40_GenericSQL", "synpred50_GenericSQL", "condition", "synpred62_GenericSQL", 
        "synpred49_GenericSQL", "nestedExpression", "synpred76_GenericSQL", 
        "synpred68_GenericSQL", "from", "expressionList", "item", "synpred57_GenericSQL", 
        "synpred28_GenericSQL", "synpred22_GenericSQL", "synpred36_GenericSQL", 
        "synpred82_GenericSQL", "synpred65_GenericSQL", "synpred35_GenericSQL", 
        "into", "synpred87_GenericSQL", "like", "itemList", "synpred31_GenericSQL", 
        "setter", "synpred38_GenericSQL", "values", "orderBy", "synpred7_GenericSQL", 
        "nestedCondition", "synpred33_GenericSQL", "synpred61_GenericSQL", 
        "in", "synpred83_GenericSQL", "synpred44_GenericSQL", "synpred56_GenericSQL", 
        "synpred78_GenericSQL", "synpred85_GenericSQL", "synpred15_GenericSQL", 
        "synpred75_GenericSQL", "synpred88_GenericSQL", "synpred42_GenericSQL", 
        "comparator", "synpred11_GenericSQL", "synpred53_GenericSQL", "date", 
        "between", "conditionList", "update", "synpred12_GenericSQL", "value", 
        "synpred41_GenericSQL", "literal", "synpred27_GenericSQL", "synpred4_GenericSQL", 
        "synpred81_GenericSQL", "synpred6_GenericSQL", "synpred17_GenericSQL", 
        "synpred47_GenericSQL", "having", "synpred72_GenericSQL", "synpred48_GenericSQL", 
        "synpred66_GenericSQL", "synpred18_GenericSQL", "synpred37_GenericSQL", 
        "synpred1_GenericSQL", "synpred16_GenericSQL", "synpred54_GenericSQL", 
        "synpred67_GenericSQL", "synpred70_GenericSQL", "databaseName", 
        "synpred32_GenericSQL", "unary", "groupBy", "synpred74_GenericSQL", 
        "synpred64_GenericSQL", "synpred60_GenericSQL", "synpred51_GenericSQL", 
        "columnRef", "orderByItem", "synpred29_GenericSQL", "multiply", 
        "joinList", "statement", "synpred19_GenericSQL", "synpred3_GenericSQL", 
        "synpred80_GenericSQL", "quantifier", "isNull", "subSelect", "synpred25_GenericSQL", 
        "synpred30_GenericSQL", "synpred79_GenericSQL", "expression", "synpred24_GenericSQL", 
        "synpred45_GenericSQL", "synpred46_GenericSQL", "synpred9_GenericSQL", 
        "synpred43_GenericSQL", "synpred63_GenericSQL"
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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:55:1: statement : ( select ( SEMI )? EOF | insert ( SEMI )? EOF | update ( SEMI )? EOF );
    public final void statement() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "statement");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(55, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:3: ( select ( SEMI )? EOF | insert ( SEMI )? EOF | update ( SEMI )? EOF )
            int alt4=3;
            try { dbg.enterDecision(4);

            switch ( input.LA(1) ) {
            case SELECT:
                {
                alt4=1;
                }
                break;
            case INSERT:
                {
                alt4=2;
                }
                break;
            case UPDATE:
                {
                alt4=3;
                }
                break;
            default:
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
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:58:4: update ( SEMI )? EOF
                    {
                    dbg.location(58,4);
                    pushFollow(FOLLOW_update_in_statement98);
                    update();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(58,11);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:58:11: ( SEMI )?
                    int alt3=2;
                    try { dbg.enterSubRule(3);
                    try { dbg.enterDecision(3);

                    int LA3_0 = input.LA(1);

                    if ( (LA3_0==SEMI) ) {
                        alt3=1;
                    }
                    } finally {dbg.exitDecision(3);}

                    switch (alt3) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:58:13: SEMI
                            {
                            dbg.location(58,13);
                            match(input,SEMI,FOLLOW_SEMI_in_statement102); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(3);}

                    dbg.location(58,21);
                    match(input,EOF,FOLLOW_EOF_in_statement107); if (state.failed) return ;

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
            int alt5=2;
            try { dbg.enterDecision(5);

            int LA5_0 = input.LA(1);

            if ( (LA5_0==SELECT) ) {
                alt5=1;
            }
            else if ( (LA5_0==LPAREN) ) {
                alt5=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(5);}

            switch (alt5) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:63:5: select
                    {
                    dbg.location(63,5);
                    pushFollow(FOLLOW_select_in_subSelect123);
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
                    match(input,LPAREN,FOLLOW_LPAREN_in_subSelect129); if (state.failed) return ;
                    dbg.location(64,12);
                    pushFollow(FOLLOW_select_in_subSelect131);
                    select();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(64,19);
                    match(input,RPAREN,FOLLOW_RPAREN_in_subSelect133); if (state.failed) return ;

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
            match(input,SELECT,FOLLOW_SELECT_in_select148); if (state.failed) return ;
            dbg.location(69,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:69:5: ( ALL | DISTINCT | UNIQUE )?
            int alt6=2;
            try { dbg.enterSubRule(6);
            try { dbg.enterDecision(6);

            int LA6_0 = input.LA(1);

            if ( ((LA6_0>=ALL && LA6_0<=UNIQUE)) ) {
                alt6=1;
            }
            } finally {dbg.exitDecision(6);}

            switch (alt6) {
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
            } finally {dbg.exitSubRule(6);}

            dbg.location(70,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:5: ( TOP Integer ( PERCENT )? )?
            int alt8=2;
            try { dbg.enterSubRule(8);
            try { dbg.enterDecision(8);

            int LA8_0 = input.LA(1);

            if ( (LA8_0==TOP) ) {
                alt8=1;
            }
            } finally {dbg.exitDecision(8);}

            switch (alt8) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:7: TOP Integer ( PERCENT )?
                    {
                    dbg.location(70,7);
                    match(input,TOP,FOLLOW_TOP_in_select175); if (state.failed) return ;
                    dbg.location(70,11);
                    match(input,Integer,FOLLOW_Integer_in_select177); if (state.failed) return ;
                    dbg.location(70,19);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:19: ( PERCENT )?
                    int alt7=2;
                    try { dbg.enterSubRule(7);
                    try { dbg.enterDecision(7);

                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==PERCENT) ) {
                        alt7=1;
                    }
                    } finally {dbg.exitDecision(7);}

                    switch (alt7) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:21: PERCENT
                            {
                            dbg.location(70,21);
                            match(input,PERCENT,FOLLOW_PERCENT_in_select181); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(7);}


                    }
                    break;

            }
            } finally {dbg.exitSubRule(8);}

            dbg.location(71,5);
            pushFollow(FOLLOW_itemList_in_select193);
            itemList();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(72,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:5: ( into )?
            int alt9=2;
            try { dbg.enterSubRule(9);
            try { dbg.enterDecision(9);

            int LA9_0 = input.LA(1);

            if ( (LA9_0==INTO) ) {
                alt9=1;
            }
            } finally {dbg.exitDecision(9);}

            switch (alt9) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:7: into
                    {
                    dbg.location(72,7);
                    pushFollow(FOLLOW_into_in_select201);
                    into();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(9);}

            dbg.location(73,5);
            pushFollow(FOLLOW_from_in_select210);
            from();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(75,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:75:5: ( where )?
            int alt10=2;
            try { dbg.enterSubRule(10);
            try { dbg.enterDecision(10);

            int LA10_0 = input.LA(1);

            if ( (LA10_0==WHERE) ) {
                alt10=1;
            }
            } finally {dbg.exitDecision(10);}

            switch (alt10) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:75:7: where
                    {
                    dbg.location(75,7);
                    pushFollow(FOLLOW_where_in_select219);
                    where();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(10);}

            dbg.location(76,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:76:5: ( groupBy )?
            int alt11=2;
            try { dbg.enterSubRule(11);
            try { dbg.enterDecision(11);

            int LA11_0 = input.LA(1);

            if ( (LA11_0==GROUP) ) {
                alt11=1;
            }
            } finally {dbg.exitDecision(11);}

            switch (alt11) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:76:7: groupBy
                    {
                    dbg.location(76,7);
                    pushFollow(FOLLOW_groupBy_in_select230);
                    groupBy();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(11);}

            dbg.location(77,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:77:5: ( having )?
            int alt12=2;
            try { dbg.enterSubRule(12);
            try { dbg.enterDecision(12);

            int LA12_0 = input.LA(1);

            if ( (LA12_0==HAVING) ) {
                alt12=1;
            }
            } finally {dbg.exitDecision(12);}

            switch (alt12) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:77:7: having
                    {
                    dbg.location(77,7);
                    pushFollow(FOLLOW_having_in_select241);
                    having();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(12);}

            dbg.location(78,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:78:5: ( orderBy )?
            int alt13=2;
            try { dbg.enterSubRule(13);
            try { dbg.enterDecision(13);

            int LA13_0 = input.LA(1);

            if ( (LA13_0==ORDER) ) {
                alt13=1;
            }
            } finally {dbg.exitDecision(13);}

            switch (alt13) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:78:7: orderBy
                    {
                    dbg.location(78,7);
                    pushFollow(FOLLOW_orderBy_in_select252);
                    orderBy();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(13);}


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
            match(input,INSERT,FOLLOW_INSERT_in_insert270); if (state.failed) return ;
            dbg.location(82,12);
            pushFollow(FOLLOW_into_in_insert272);
            into();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(82,17);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:82:17: ( columnList )?
            int alt14=2;
            try { dbg.enterSubRule(14);
            try { dbg.enterDecision(14);

            int LA14_0 = input.LA(1);

            if ( (LA14_0==LPAREN) ) {
                alt14=1;
            }
            } finally {dbg.exitDecision(14);}

            switch (alt14) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:82:19: columnList
                    {
                    dbg.location(82,19);
                    pushFollow(FOLLOW_columnList_in_insert276);
                    columnList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(14);}

            dbg.location(83,3);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:83:3: ( values )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:83:5: values
            {
            dbg.location(83,5);
            pushFollow(FOLLOW_values_in_insert285);
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


    // $ANTLR start "update"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:88:1: update : UPDATE tableRef SET setter ( COMMA setter )* ( where )? ;
    public final void update() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "update");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(88, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:89:3: ( UPDATE tableRef SET setter ( COMMA setter )* ( where )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:89:5: UPDATE tableRef SET setter ( COMMA setter )* ( where )?
            {
            dbg.location(89,5);
            match(input,UPDATE,FOLLOW_UPDATE_in_update305); if (state.failed) return ;
            dbg.location(89,12);
            pushFollow(FOLLOW_tableRef_in_update307);
            tableRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(89,21);
            match(input,SET,FOLLOW_SET_in_update309); if (state.failed) return ;
            dbg.location(89,25);
            pushFollow(FOLLOW_setter_in_update311);
            setter();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(89,32);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:89:32: ( COMMA setter )*
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

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:89:34: COMMA setter
            	    {
            	    dbg.location(89,34);
            	    match(input,COMMA,FOLLOW_COMMA_in_update315); if (state.failed) return ;
            	    dbg.location(89,40);
            	    pushFollow(FOLLOW_setter_in_update317);
            	    setter();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);
            } finally {dbg.exitSubRule(15);}

            dbg.location(90,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:90:5: ( where )?
            int alt16=2;
            try { dbg.enterSubRule(16);
            try { dbg.enterDecision(16);

            int LA16_0 = input.LA(1);

            if ( (LA16_0==WHERE) ) {
                alt16=1;
            }
            } finally {dbg.exitDecision(16);}

            switch (alt16) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:90:7: where
                    {
                    dbg.location(90,7);
                    pushFollow(FOLLOW_where_in_update328);
                    where();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(16);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(91, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "update");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "update"


    // $ANTLR start "setter"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:93:1: setter : columnName EQ literal ;
    public final void setter() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "setter");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(93, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:94:3: ( columnName EQ literal )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:94:5: columnName EQ literal
            {
            dbg.location(94,5);
            pushFollow(FOLLOW_columnName_in_setter346);
            columnName();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(94,16);
            match(input,EQ,FOLLOW_EQ_in_setter348); if (state.failed) return ;
            dbg.location(94,19);
            pushFollow(FOLLOW_literal_in_setter350);
            literal();

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
        dbg.location(95, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "setter");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "setter"


    // $ANTLR start "into"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:97:1: into : INTO tableRef ( COMMA tableRef )* ;
    public final void into() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "into");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(97, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:98:3: ( INTO tableRef ( COMMA tableRef )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:98:5: INTO tableRef ( COMMA tableRef )*
            {
            dbg.location(98,5);
            match(input,INTO,FOLLOW_INTO_in_into365); if (state.failed) return ;
            dbg.location(98,10);
            pushFollow(FOLLOW_tableRef_in_into367);
            tableRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(98,19);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:98:19: ( COMMA tableRef )*
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

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:98:21: COMMA tableRef
            	    {
            	    dbg.location(98,21);
            	    match(input,COMMA,FOLLOW_COMMA_in_into371); if (state.failed) return ;
            	    dbg.location(98,27);
            	    pushFollow(FOLLOW_tableRef_in_into373);
            	    tableRef();

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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(99, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:101:1: columnList : LPAREN columnName ( COMMA columnName )* RPAREN ;
    public final void columnList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(101, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:102:3: ( LPAREN columnName ( COMMA columnName )* RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:102:5: LPAREN columnName ( COMMA columnName )* RPAREN
            {
            dbg.location(102,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_columnList389); if (state.failed) return ;
            dbg.location(102,12);
            pushFollow(FOLLOW_columnName_in_columnList391);
            columnName();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(102,23);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:102:23: ( COMMA columnName )*
            try { dbg.enterSubRule(18);

            loop18:
            do {
                int alt18=2;
                try { dbg.enterDecision(18);

                int LA18_0 = input.LA(1);

                if ( (LA18_0==COMMA) ) {
                    alt18=1;
                }


                } finally {dbg.exitDecision(18);}

                switch (alt18) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:102:25: COMMA columnName
            	    {
            	    dbg.location(102,25);
            	    match(input,COMMA,FOLLOW_COMMA_in_columnList395); if (state.failed) return ;
            	    dbg.location(102,31);
            	    pushFollow(FOLLOW_columnName_in_columnList397);
            	    columnName();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);
            } finally {dbg.exitSubRule(18);}

            dbg.location(102,45);
            match(input,RPAREN,FOLLOW_RPAREN_in_columnList402); if (state.failed) return ;

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
            dbg.exitRule(getGrammarFileName(), "columnList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "columnList"


    // $ANTLR start "values"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:105:1: values : VALUES LPAREN literal ( COMMA literal )* RPAREN ;
    public final void values() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "values");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(105, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:3: ( VALUES LPAREN literal ( COMMA literal )* RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:5: VALUES LPAREN literal ( COMMA literal )* RPAREN
            {
            dbg.location(106,5);
            match(input,VALUES,FOLLOW_VALUES_in_values417); if (state.failed) return ;
            dbg.location(106,12);
            match(input,LPAREN,FOLLOW_LPAREN_in_values419); if (state.failed) return ;
            dbg.location(106,19);
            pushFollow(FOLLOW_literal_in_values421);
            literal();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(106,27);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:27: ( COMMA literal )*
            try { dbg.enterSubRule(19);

            loop19:
            do {
                int alt19=2;
                try { dbg.enterDecision(19);

                int LA19_0 = input.LA(1);

                if ( (LA19_0==COMMA) ) {
                    alt19=1;
                }


                } finally {dbg.exitDecision(19);}

                switch (alt19) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:106:29: COMMA literal
            	    {
            	    dbg.location(106,29);
            	    match(input,COMMA,FOLLOW_COMMA_in_values425); if (state.failed) return ;
            	    dbg.location(106,35);
            	    pushFollow(FOLLOW_literal_in_values427);
            	    literal();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);
            } finally {dbg.exitSubRule(19);}

            dbg.location(106,46);
            match(input,RPAREN,FOLLOW_RPAREN_in_values432); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(107, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:109:1: itemList : ( STAR | item ( COMMA item )* );
    public final void itemList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "itemList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(109, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:110:3: ( STAR | item ( COMMA item )* )
            int alt21=2;
            try { dbg.enterDecision(21);

            int LA21_0 = input.LA(1);

            if ( (LA21_0==STAR) ) {
                alt21=1;
            }
            else if ( (LA21_0==LPAREN||LA21_0==Integer||LA21_0==Identifier||(LA21_0>=PLUS && LA21_0<=MINUS)||(LA21_0>=Float && LA21_0<=FALSE)||LA21_0==QuotedIdentifier||(LA21_0>=110 && LA21_0<=112)) ) {
                alt21=2;
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:110:5: STAR
                    {
                    dbg.location(110,5);
                    match(input,STAR,FOLLOW_STAR_in_itemList447); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:111:5: item ( COMMA item )*
                    {
                    dbg.location(111,5);
                    pushFollow(FOLLOW_item_in_itemList453);
                    item();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(111,10);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:111:10: ( COMMA item )*
                    try { dbg.enterSubRule(20);

                    loop20:
                    do {
                        int alt20=2;
                        try { dbg.enterDecision(20);

                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==COMMA) ) {
                            alt20=1;
                        }


                        } finally {dbg.exitDecision(20);}

                        switch (alt20) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:111:12: COMMA item
                    	    {
                    	    dbg.location(111,12);
                    	    match(input,COMMA,FOLLOW_COMMA_in_itemList457); if (state.failed) return ;
                    	    dbg.location(111,18);
                    	    pushFollow(FOLLOW_item_in_itemList459);
                    	    item();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(20);}


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
        dbg.location(112, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:1: item : ( value ( ( AS )? alias )? | allColumns );
    public final void item() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "item");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(114, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:3: ( value ( ( AS )? alias )? | allColumns )
            int alt24=2;
            try { dbg.enterDecision(24);

            int LA24_0 = input.LA(1);

            if ( (LA24_0==LPAREN||LA24_0==Integer||(LA24_0>=PLUS && LA24_0<=MINUS)||(LA24_0>=Float && LA24_0<=FALSE)||LA24_0==QuotedIdentifier||(LA24_0>=110 && LA24_0<=112)) ) {
                alt24=1;
            }
            else if ( (LA24_0==Identifier) ) {
                int LA24_2 = input.LA(2);

                if ( (LA24_2==DOT) ) {
                    int LA24_3 = input.LA(3);

                    if ( (LA24_3==STAR) ) {
                        alt24=2;
                    }
                    else if ( (LA24_3==Identifier||LA24_3==QuotedIdentifier) ) {
                        alt24=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 24, 3, input);

                        dbg.recognitionException(nvae);
                        throw nvae;
                    }
                }
                else if ( (LA24_2==EOF||LA24_2==COMMA||LA24_2==INTO||LA24_2==AS||(LA24_2>=Identifier && LA24_2<=FROM)) ) {
                    alt24=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 2, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(24);}

            switch (alt24) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:5: value ( ( AS )? alias )?
                    {
                    dbg.location(115,5);
                    pushFollow(FOLLOW_value_in_item477);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(115,11);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:11: ( ( AS )? alias )?
                    int alt23=2;
                    try { dbg.enterSubRule(23);
                    try { dbg.enterDecision(23);

                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==AS||LA23_0==Identifier) ) {
                        alt23=1;
                    }
                    } finally {dbg.exitDecision(23);}

                    switch (alt23) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:13: ( AS )? alias
                            {
                            dbg.location(115,13);
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:13: ( AS )?
                            int alt22=2;
                            try { dbg.enterSubRule(22);
                            try { dbg.enterDecision(22);

                            int LA22_0 = input.LA(1);

                            if ( (LA22_0==AS) ) {
                                alt22=1;
                            }
                            } finally {dbg.exitDecision(22);}

                            switch (alt22) {
                                case 1 :
                                    dbg.enterAlt(1);

                                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:15: AS
                                    {
                                    dbg.location(115,15);
                                    match(input,AS,FOLLOW_AS_in_item483); if (state.failed) return ;

                                    }
                                    break;

                            }
                            } finally {dbg.exitSubRule(22);}

                            dbg.location(115,21);
                            pushFollow(FOLLOW_alias_in_item488);
                            alias();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(23);}


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:116:5: allColumns
                    {
                    dbg.location(116,5);
                    pushFollow(FOLLOW_allColumns_in_item497);
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
        dbg.location(117, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:119:1: allColumns : tableAlias DOT STAR ;
    public final void allColumns() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "allColumns");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(119, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:120:3: ( tableAlias DOT STAR )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:120:5: tableAlias DOT STAR
            {
            dbg.location(120,5);
            pushFollow(FOLLOW_tableAlias_in_allColumns511);
            tableAlias();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(120,16);
            match(input,DOT,FOLLOW_DOT_in_allColumns513); if (state.failed) return ;
            dbg.location(120,20);
            match(input,STAR,FOLLOW_STAR_in_allColumns515); if (state.failed) return ;

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
            dbg.exitRule(getGrammarFileName(), "allColumns");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "allColumns"


    // $ANTLR start "alias"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:123:1: alias : Identifier ;
    public final void alias() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "alias");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(123, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:124:3: ( Identifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:124:5: Identifier
            {
            dbg.location(124,5);
            match(input,Identifier,FOLLOW_Identifier_in_alias528); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(125, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:128:1: from : FROM fromItem ( COMMA fromItem )* ;
    public final void from() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "from");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(128, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:129:3: ( FROM fromItem ( COMMA fromItem )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:129:5: FROM fromItem ( COMMA fromItem )*
            {
            dbg.location(129,5);
            match(input,FROM,FOLLOW_FROM_in_from546); if (state.failed) return ;
            dbg.location(129,10);
            pushFollow(FOLLOW_fromItem_in_from548);
            fromItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(129,19);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:129:19: ( COMMA fromItem )*
            try { dbg.enterSubRule(25);

            loop25:
            do {
                int alt25=2;
                try { dbg.enterDecision(25);

                int LA25_0 = input.LA(1);

                if ( (LA25_0==COMMA) ) {
                    alt25=1;
                }


                } finally {dbg.exitDecision(25);}

                switch (alt25) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:129:21: COMMA fromItem
            	    {
            	    dbg.location(129,21);
            	    match(input,COMMA,FOLLOW_COMMA_in_from552); if (state.failed) return ;
            	    dbg.location(129,27);
            	    pushFollow(FOLLOW_fromItem_in_from554);
            	    fromItem();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);
            } finally {dbg.exitSubRule(25);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(130, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:132:1: fromItem : ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? ;
    public final void fromItem() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "fromItem");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(132, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:133:3: ( ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:133:5: ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )?
            {
            dbg.location(133,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:133:5: ( ( LPAREN subSelect RPAREN ) | tableRef )
            int alt26=2;
            try { dbg.enterSubRule(26);
            try { dbg.enterDecision(26);

            int LA26_0 = input.LA(1);

            if ( (LA26_0==LPAREN) ) {
                alt26=1;
            }
            else if ( (LA26_0==Identifier||LA26_0==QuotedIdentifier) ) {
                alt26=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(26);}

            switch (alt26) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:133:7: ( LPAREN subSelect RPAREN )
                    {
                    dbg.location(133,7);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:133:7: ( LPAREN subSelect RPAREN )
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:133:9: LPAREN subSelect RPAREN
                    {
                    dbg.location(133,9);
                    match(input,LPAREN,FOLLOW_LPAREN_in_fromItem576); if (state.failed) return ;
                    dbg.location(133,16);
                    pushFollow(FOLLOW_subSelect_in_fromItem578);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(133,26);
                    match(input,RPAREN,FOLLOW_RPAREN_in_fromItem580); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:134:7: tableRef
                    {
                    dbg.location(134,7);
                    pushFollow(FOLLOW_tableRef_in_fromItem591);
                    tableRef();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(26);}

            dbg.location(136,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:136:5: ( ( AS )? alias )?
            int alt28=2;
            try { dbg.enterSubRule(28);
            try { dbg.enterDecision(28);

            int LA28_0 = input.LA(1);

            if ( (LA28_0==AS||LA28_0==Identifier) ) {
                alt28=1;
            }
            } finally {dbg.exitDecision(28);}

            switch (alt28) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:136:7: ( AS )? alias
                    {
                    dbg.location(136,7);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:136:7: ( AS )?
                    int alt27=2;
                    try { dbg.enterSubRule(27);
                    try { dbg.enterDecision(27);

                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==AS) ) {
                        alt27=1;
                    }
                    } finally {dbg.exitDecision(27);}

                    switch (alt27) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:136:9: AS
                            {
                            dbg.location(136,9);
                            match(input,AS,FOLLOW_AS_in_fromItem608); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(27);}

                    dbg.location(136,15);
                    pushFollow(FOLLOW_alias_in_fromItem613);
                    alias();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(28);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(137, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:139:1: joinList : ( join )* ;
    public final void joinList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "joinList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(139, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:140:3: ( ( join )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:140:5: ( join )*
            {
            dbg.location(140,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:140:5: ( join )*
            try { dbg.enterSubRule(29);

            loop29:
            do {
                int alt29=2;
                try { dbg.enterDecision(29);

                int LA29_0 = input.LA(1);

                if ( ((LA29_0>=INNER && LA29_0<=FULL)||LA29_0==JOIN) ) {
                    alt29=1;
                }


                } finally {dbg.exitDecision(29);}

                switch (alt29) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:140:7: join
            	    {
            	    dbg.location(140,7);
            	    pushFollow(FOLLOW_join_in_joinList631);
            	    join();

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
            dbg.exitRule(getGrammarFileName(), "joinList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "joinList"


    // $ANTLR start "join"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:143:1: join : ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )? JOIN ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )? ;
    public final void join() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "join");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(143, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:144:3: ( ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )? JOIN ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:144:5: ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )? JOIN ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )?
            {
            dbg.location(144,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:144:5: ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )?
            int alt31=3;
            try { dbg.enterSubRule(31);
            try { dbg.enterDecision(31);

            int LA31_0 = input.LA(1);

            if ( (LA31_0==INNER) ) {
                alt31=1;
            }
            else if ( ((LA31_0>=LEFT && LA31_0<=FULL)) ) {
                alt31=2;
            }
            } finally {dbg.exitDecision(31);}

            switch (alt31) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:144:7: INNER
                    {
                    dbg.location(144,7);
                    match(input,INNER,FOLLOW_INNER_in_join651); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:145:7: ( LEFT | RIGHT | FULL ) ( OUTER )?
                    {
                    dbg.location(145,7);
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

                    dbg.location(145,31);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:145:31: ( OUTER )?
                    int alt30=2;
                    try { dbg.enterSubRule(30);
                    try { dbg.enterDecision(30);

                    int LA30_0 = input.LA(1);

                    if ( (LA30_0==OUTER) ) {
                        alt30=1;
                    }
                    } finally {dbg.exitDecision(30);}

                    switch (alt30) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:145:33: OUTER
                            {
                            dbg.location(145,33);
                            match(input,OUTER,FOLLOW_OUTER_in_join675); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(30);}


                    }
                    break;

            }
            } finally {dbg.exitSubRule(31);}

            dbg.location(146,8);
            match(input,JOIN,FOLLOW_JOIN_in_join688); if (state.failed) return ;
            dbg.location(147,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:147:5: ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )?
            int alt33=3;
            try { dbg.enterSubRule(33);
            try { dbg.enterDecision(33);

            int LA33_0 = input.LA(1);

            if ( (LA33_0==ON) ) {
                alt33=1;
            }
            else if ( (LA33_0==USING) ) {
                alt33=2;
            }
            } finally {dbg.exitDecision(33);}

            switch (alt33) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:147:7: ON conditionList
                    {
                    dbg.location(147,7);
                    match(input,ON,FOLLOW_ON_in_join696); if (state.failed) return ;
                    dbg.location(147,10);
                    pushFollow(FOLLOW_conditionList_in_join698);
                    conditionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:148:7: USING LPAREN columnRef ( COMMA columnRef )*
                    {
                    dbg.location(148,7);
                    match(input,USING,FOLLOW_USING_in_join706); if (state.failed) return ;
                    dbg.location(148,13);
                    match(input,LPAREN,FOLLOW_LPAREN_in_join708); if (state.failed) return ;
                    dbg.location(148,20);
                    pushFollow(FOLLOW_columnRef_in_join710);
                    columnRef();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(148,30);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:148:30: ( COMMA columnRef )*
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

                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:148:32: COMMA columnRef
                    	    {
                    	    dbg.location(148,32);
                    	    match(input,COMMA,FOLLOW_COMMA_in_join714); if (state.failed) return ;
                    	    dbg.location(148,38);
                    	    pushFollow(FOLLOW_columnRef_in_join716);
                    	    columnRef();

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
        dbg.location(150, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:158:1: where : WHERE conditionList ;
    public final void where() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "where");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(158, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:159:3: ( WHERE conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:159:5: WHERE conditionList
            {
            dbg.location(159,5);
            match(input,WHERE,FOLLOW_WHERE_in_where747); if (state.failed) return ;
            dbg.location(159,11);
            pushFollow(FOLLOW_conditionList_in_where749);
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
        dbg.location(160, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:1: groupBy : GROUP BY columnRef ( COMMA columnRef )* ;
    public final void groupBy() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "groupBy");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(162, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:163:3: ( GROUP BY columnRef ( COMMA columnRef )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:163:5: GROUP BY columnRef ( COMMA columnRef )*
            {
            dbg.location(163,5);
            match(input,GROUP,FOLLOW_GROUP_in_groupBy764); if (state.failed) return ;
            dbg.location(163,11);
            match(input,BY,FOLLOW_BY_in_groupBy766); if (state.failed) return ;
            dbg.location(163,14);
            pushFollow(FOLLOW_columnRef_in_groupBy768);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(163,24);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:163:24: ( COMMA columnRef )*
            try { dbg.enterSubRule(34);

            loop34:
            do {
                int alt34=2;
                try { dbg.enterDecision(34);

                int LA34_0 = input.LA(1);

                if ( (LA34_0==COMMA) ) {
                    alt34=1;
                }


                } finally {dbg.exitDecision(34);}

                switch (alt34) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:163:26: COMMA columnRef
            	    {
            	    dbg.location(163,26);
            	    match(input,COMMA,FOLLOW_COMMA_in_groupBy772); if (state.failed) return ;
            	    dbg.location(163,32);
            	    pushFollow(FOLLOW_columnRef_in_groupBy774);
            	    columnRef();

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
        dbg.location(164, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:166:1: having : HAVING conditionList ;
    public final void having() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "having");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(166, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:167:3: ( HAVING conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:167:5: HAVING conditionList
            {
            dbg.location(167,5);
            match(input,HAVING,FOLLOW_HAVING_in_having792); if (state.failed) return ;
            dbg.location(167,12);
            pushFollow(FOLLOW_conditionList_in_having794);
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
        dbg.location(168, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:170:1: orderBy : ORDER BY orderByItem ( COMMA orderByItem )* ;
    public final void orderBy() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "orderBy");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(170, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:171:3: ( ORDER BY orderByItem ( COMMA orderByItem )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:171:6: ORDER BY orderByItem ( COMMA orderByItem )*
            {
            dbg.location(171,6);
            match(input,ORDER,FOLLOW_ORDER_in_orderBy810); if (state.failed) return ;
            dbg.location(171,12);
            match(input,BY,FOLLOW_BY_in_orderBy812); if (state.failed) return ;
            dbg.location(171,15);
            pushFollow(FOLLOW_orderByItem_in_orderBy814);
            orderByItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(171,27);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:171:27: ( COMMA orderByItem )*
            try { dbg.enterSubRule(35);

            loop35:
            do {
                int alt35=2;
                try { dbg.enterDecision(35);

                int LA35_0 = input.LA(1);

                if ( (LA35_0==COMMA) ) {
                    alt35=1;
                }


                } finally {dbg.exitDecision(35);}

                switch (alt35) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:171:29: COMMA orderByItem
            	    {
            	    dbg.location(171,29);
            	    match(input,COMMA,FOLLOW_COMMA_in_orderBy818); if (state.failed) return ;
            	    dbg.location(171,35);
            	    pushFollow(FOLLOW_orderByItem_in_orderBy820);
            	    orderByItem();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);
            } finally {dbg.exitSubRule(35);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(172, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:174:1: orderByItem : columnRef ( ASC | DESC )? ;
    public final void orderByItem() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "orderByItem");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(174, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:3: ( columnRef ( ASC | DESC )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:5: columnRef ( ASC | DESC )?
            {
            dbg.location(175,5);
            pushFollow(FOLLOW_columnRef_in_orderByItem838);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(175,15);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:15: ( ASC | DESC )?
            int alt36=2;
            try { dbg.enterSubRule(36);
            try { dbg.enterDecision(36);

            int LA36_0 = input.LA(1);

            if ( ((LA36_0>=ASC && LA36_0<=DESC)) ) {
                alt36=1;
            }
            } finally {dbg.exitDecision(36);}

            switch (alt36) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
                    {
                    dbg.location(175,15);
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
            } finally {dbg.exitSubRule(36);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(176, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:178:1: nestedCondition : LPAREN conditionList RPAREN ;
    public final void nestedCondition() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "nestedCondition");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(178, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:3: ( LPAREN conditionList RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:5: LPAREN conditionList RPAREN
            {
            dbg.location(179,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedCondition864); if (state.failed) return ;
            dbg.location(179,12);
            pushFollow(FOLLOW_conditionList_in_nestedCondition866);
            conditionList();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(179,26);
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedCondition868); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(180, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:182:1: conditionList : condition ( ( OR | AND ) condition )* ;
    public final void conditionList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "conditionList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(182, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:3: ( condition ( ( OR | AND ) condition )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:5: condition ( ( OR | AND ) condition )*
            {
            dbg.location(183,5);
            pushFollow(FOLLOW_condition_in_conditionList883);
            condition();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(183,15);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:15: ( ( OR | AND ) condition )*
            try { dbg.enterSubRule(37);

            loop37:
            do {
                int alt37=2;
                try { dbg.enterDecision(37);

                int LA37_0 = input.LA(1);

                if ( ((LA37_0>=OR && LA37_0<=AND)) ) {
                    alt37=1;
                }


                } finally {dbg.exitDecision(37);}

                switch (alt37) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:17: ( OR | AND ) condition
            	    {
            	    dbg.location(183,17);
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

            	    dbg.location(183,30);
            	    pushFollow(FOLLOW_condition_in_conditionList897);
            	    condition();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);
            } finally {dbg.exitSubRule(37);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(184, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:186:1: condition : ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison ) ;
    public final void condition() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "condition");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(186, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:187:3: ( ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison ) )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:187:5: ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )
            {
            dbg.location(187,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:187:5: ( NOT )?
            int alt38=2;
            try { dbg.enterSubRule(38);
            try { dbg.enterDecision(38);

            int LA38_0 = input.LA(1);

            if ( (LA38_0==NOT) ) {
                alt38=1;
            }
            } finally {dbg.exitDecision(38);}

            switch (alt38) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:187:7: NOT
                    {
                    dbg.location(187,7);
                    match(input,NOT,FOLLOW_NOT_in_condition917); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(38);}

            dbg.location(188,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:188:5: ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )
            int alt39=8;
            try { dbg.enterSubRule(39);
            try { dbg.enterDecision(39);

            try {
                isCyclicDecision = true;
                alt39 = dfa39.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(39);}

            switch (alt39) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:188:7: nestedCondition
                    {
                    dbg.location(188,7);
                    pushFollow(FOLLOW_nestedCondition_in_condition928);
                    nestedCondition();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:189:7: in
                    {
                    dbg.location(189,7);
                    pushFollow(FOLLOW_in_in_condition936);
                    in();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:190:7: between
                    {
                    dbg.location(190,7);
                    pushFollow(FOLLOW_between_in_condition944);
                    between();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:7: isNull
                    {
                    dbg.location(191,7);
                    pushFollow(FOLLOW_isNull_in_condition952);
                    isNull();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:192:7: exists
                    {
                    dbg.location(192,7);
                    pushFollow(FOLLOW_exists_in_condition960);
                    exists();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:193:7: like
                    {
                    dbg.location(193,7);
                    pushFollow(FOLLOW_like_in_condition968);
                    like();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:194:7: quantifier
                    {
                    dbg.location(194,7);
                    pushFollow(FOLLOW_quantifier_in_condition976);
                    quantifier();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:195:6: comparison
                    {
                    dbg.location(195,6);
                    pushFollow(FOLLOW_comparison_in_condition983);
                    comparison();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(39);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(197, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:199:1: in : expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN ;
    public final void in() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "in");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(199, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:3: ( expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:5: expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN
            {
            dbg.location(200,5);
            pushFollow(FOLLOW_expression_in_in1002);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(200,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:16: ( NOT )?
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:18: NOT
                    {
                    dbg.location(200,18);
                    match(input,NOT,FOLLOW_NOT_in_in1006); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(40);}

            dbg.location(200,25);
            match(input,IN,FOLLOW_IN_in_in1011); if (state.failed) return ;
            dbg.location(200,28);
            match(input,LPAREN,FOLLOW_LPAREN_in_in1013); if (state.failed) return ;
            dbg.location(200,35);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:35: ( subSelect | expressionList )
            int alt41=2;
            try { dbg.enterSubRule(41);
            try { dbg.enterDecision(41);

            switch ( input.LA(1) ) {
            case SELECT:
                {
                alt41=1;
                }
                break;
            case LPAREN:
                {
                int LA41_2 = input.LA(2);

                if ( (LA41_2==LPAREN||LA41_2==Integer||LA41_2==Identifier||(LA41_2>=PLUS && LA41_2<=MINUS)||(LA41_2>=Float && LA41_2<=FALSE)||LA41_2==QuotedIdentifier||(LA41_2>=110 && LA41_2<=112)) ) {
                    alt41=2;
                }
                else if ( (LA41_2==SELECT) ) {
                    alt41=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 41, 2, input);

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
            case 110:
            case 111:
            case 112:
                {
                alt41=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(41);}

            switch (alt41) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:37: subSelect
                    {
                    dbg.location(200,37);
                    pushFollow(FOLLOW_subSelect_in_in1017);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:49: expressionList
                    {
                    dbg.location(200,49);
                    pushFollow(FOLLOW_expressionList_in_in1021);
                    expressionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(41);}

            dbg.location(200,66);
            match(input,RPAREN,FOLLOW_RPAREN_in_in1025); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(201, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:203:1: between : expression ( NOT )? BETWEEN expression AND expression ;
    public final void between() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "between");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(203, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:204:3: ( expression ( NOT )? BETWEEN expression AND expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:204:5: expression ( NOT )? BETWEEN expression AND expression
            {
            dbg.location(204,5);
            pushFollow(FOLLOW_expression_in_between1040);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(204,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:204:16: ( NOT )?
            int alt42=2;
            try { dbg.enterSubRule(42);
            try { dbg.enterDecision(42);

            int LA42_0 = input.LA(1);

            if ( (LA42_0==NOT) ) {
                alt42=1;
            }
            } finally {dbg.exitDecision(42);}

            switch (alt42) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:204:18: NOT
                    {
                    dbg.location(204,18);
                    match(input,NOT,FOLLOW_NOT_in_between1044); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(42);}

            dbg.location(204,25);
            match(input,BETWEEN,FOLLOW_BETWEEN_in_between1049); if (state.failed) return ;
            dbg.location(204,33);
            pushFollow(FOLLOW_expression_in_between1051);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(204,44);
            match(input,AND,FOLLOW_AND_in_between1053); if (state.failed) return ;
            dbg.location(204,48);
            pushFollow(FOLLOW_expression_in_between1055);
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
        dbg.location(205, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:207:1: isNull : expression IS ( NOT )? NULL ;
    public final void isNull() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "isNull");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(207, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:208:3: ( expression IS ( NOT )? NULL )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:208:5: expression IS ( NOT )? NULL
            {
            dbg.location(208,5);
            pushFollow(FOLLOW_expression_in_isNull1070);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(208,16);
            match(input,IS,FOLLOW_IS_in_isNull1072); if (state.failed) return ;
            dbg.location(208,19);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:208:19: ( NOT )?
            int alt43=2;
            try { dbg.enterSubRule(43);
            try { dbg.enterDecision(43);

            int LA43_0 = input.LA(1);

            if ( (LA43_0==NOT) ) {
                alt43=1;
            }
            } finally {dbg.exitDecision(43);}

            switch (alt43) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:208:21: NOT
                    {
                    dbg.location(208,21);
                    match(input,NOT,FOLLOW_NOT_in_isNull1076); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(43);}

            dbg.location(208,28);
            match(input,NULL,FOLLOW_NULL_in_isNull1081); if (state.failed) return ;

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
            dbg.exitRule(getGrammarFileName(), "isNull");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "isNull"


    // $ANTLR start "exists"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:211:1: exists : EXISTS expression ;
    public final void exists() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "exists");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(211, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:212:3: ( EXISTS expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:212:5: EXISTS expression
            {
            dbg.location(212,5);
            match(input,EXISTS,FOLLOW_EXISTS_in_exists1096); if (state.failed) return ;
            dbg.location(212,12);
            pushFollow(FOLLOW_expression_in_exists1098);
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
            dbg.exitRule(getGrammarFileName(), "exists");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "exists"


    // $ANTLR start "like"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:215:1: like : expression ( NOT )? LIKE expression ;
    public final void like() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "like");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(215, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:216:3: ( expression ( NOT )? LIKE expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:216:5: expression ( NOT )? LIKE expression
            {
            dbg.location(216,5);
            pushFollow(FOLLOW_expression_in_like1113);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(216,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:216:16: ( NOT )?
            int alt44=2;
            try { dbg.enterSubRule(44);
            try { dbg.enterDecision(44);

            int LA44_0 = input.LA(1);

            if ( (LA44_0==NOT) ) {
                alt44=1;
            }
            } finally {dbg.exitDecision(44);}

            switch (alt44) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:216:18: NOT
                    {
                    dbg.location(216,18);
                    match(input,NOT,FOLLOW_NOT_in_like1117); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(44);}

            dbg.location(216,25);
            match(input,LIKE,FOLLOW_LIKE_in_like1122); if (state.failed) return ;
            dbg.location(216,30);
            pushFollow(FOLLOW_expression_in_like1124);
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
        dbg.location(218, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:220:1: comparison : expression comparator expression ;
    public final void comparison() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "comparison");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(220, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:221:3: ( expression comparator expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:221:5: expression comparator expression
            {
            dbg.location(221,5);
            pushFollow(FOLLOW_expression_in_comparison1142);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(221,16);
            pushFollow(FOLLOW_comparator_in_comparison1144);
            comparator();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(221,27);
            pushFollow(FOLLOW_expression_in_comparison1146);
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
        dbg.location(222, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:224:1: comparator : ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT );
    public final void comparator() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "comparator");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(224, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:225:3: ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(225,3);
            if ( input.LA(1)==EQ||(input.LA(1)>=NEQ1 && input.LA(1)<=GT) ) {
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
        dbg.location(232, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:234:1: quantifier : expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN ;
    public final void quantifier() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "quantifier");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(234, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:235:3: ( expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:235:5: expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN
            {
            dbg.location(235,5);
            pushFollow(FOLLOW_expression_in_quantifier1211);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(235,16);
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

            dbg.location(235,37);
            match(input,LPAREN,FOLLOW_LPAREN_in_quantifier1227); if (state.failed) return ;
            dbg.location(235,44);
            pushFollow(FOLLOW_subSelect_in_quantifier1229);
            subSelect();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(235,54);
            match(input,RPAREN,FOLLOW_RPAREN_in_quantifier1231); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(236, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:238:1: expressionList : expression ( COMMA expression )* ;
    public final void expressionList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expressionList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(238, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:239:3: ( expression ( COMMA expression )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:239:5: expression ( COMMA expression )*
            {
            dbg.location(239,5);
            pushFollow(FOLLOW_expression_in_expressionList1246);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(239,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:239:16: ( COMMA expression )*
            try { dbg.enterSubRule(45);

            loop45:
            do {
                int alt45=2;
                try { dbg.enterDecision(45);

                int LA45_0 = input.LA(1);

                if ( (LA45_0==COMMA) ) {
                    alt45=1;
                }


                } finally {dbg.exitDecision(45);}

                switch (alt45) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:239:18: COMMA expression
            	    {
            	    dbg.location(239,18);
            	    match(input,COMMA,FOLLOW_COMMA_in_expressionList1250); if (state.failed) return ;
            	    dbg.location(239,24);
            	    pushFollow(FOLLOW_expression_in_expressionList1252);
            	    expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);
            } finally {dbg.exitSubRule(45);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(240, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:242:1: nestedExpression : LPAREN expression RPAREN ;
    public final void nestedExpression() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "nestedExpression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(242, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:243:3: ( LPAREN expression RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:243:5: LPAREN expression RPAREN
            {
            dbg.location(243,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedExpression1268); if (state.failed) return ;
            dbg.location(243,12);
            pushFollow(FOLLOW_expression_in_nestedExpression1270);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(243,23);
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedExpression1272); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(244, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:246:1: expression : multiply ( ( PLUS | MINUS ) multiply )* ;
    public final void expression() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(246, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:247:3: ( multiply ( ( PLUS | MINUS ) multiply )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:247:5: multiply ( ( PLUS | MINUS ) multiply )*
            {
            dbg.location(247,5);
            pushFollow(FOLLOW_multiply_in_expression1287);
            multiply();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(247,14);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:247:14: ( ( PLUS | MINUS ) multiply )*
            try { dbg.enterSubRule(46);

            loop46:
            do {
                int alt46=2;
                try { dbg.enterDecision(46);

                int LA46_0 = input.LA(1);

                if ( ((LA46_0>=PLUS && LA46_0<=MINUS)) ) {
                    alt46=1;
                }


                } finally {dbg.exitDecision(46);}

                switch (alt46) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:247:16: ( PLUS | MINUS ) multiply
            	    {
            	    dbg.location(247,16);
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

            	    dbg.location(247,33);
            	    pushFollow(FOLLOW_multiply_in_expression1301);
            	    multiply();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop46;
                }
            } while (true);
            } finally {dbg.exitSubRule(46);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(248, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:250:1: multiply : value ( ( STAR | DIVIDE ) value )* ;
    public final void multiply() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "multiply");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(250, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:251:3: ( value ( ( STAR | DIVIDE ) value )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:251:5: value ( ( STAR | DIVIDE ) value )*
            {
            dbg.location(251,5);
            pushFollow(FOLLOW_value_in_multiply1318);
            value();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(251,11);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:251:11: ( ( STAR | DIVIDE ) value )*
            try { dbg.enterSubRule(47);

            loop47:
            do {
                int alt47=2;
                try { dbg.enterDecision(47);

                int LA47_0 = input.LA(1);

                if ( (LA47_0==STAR||LA47_0==DIVIDE) ) {
                    alt47=1;
                }


                } finally {dbg.exitDecision(47);}

                switch (alt47) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:251:13: ( STAR | DIVIDE ) value
            	    {
            	    dbg.location(251,13);
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

            	    dbg.location(251,31);
            	    pushFollow(FOLLOW_value_in_multiply1332);
            	    value();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);
            } finally {dbg.exitSubRule(47);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(252, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:269:1: value : ( literal | ( unary )? ( columnRef | nestedExpression ) );
    public final void value() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "value");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(269, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:270:3: ( literal | ( unary )? ( columnRef | nestedExpression ) )
            int alt50=2;
            try { dbg.enterDecision(50);

            switch ( input.LA(1) ) {
            case PLUS:
            case MINUS:
                {
                int LA50_1 = input.LA(2);

                if ( (LA50_1==Integer||LA50_1==Float) ) {
                    alt50=1;
                }
                else if ( (LA50_1==LPAREN||LA50_1==Identifier||LA50_1==QuotedIdentifier) ) {
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
            case Integer:
            case Float:
            case String:
            case TRUE:
            case FALSE:
            case 110:
            case 111:
            case 112:
                {
                alt50=1;
                }
                break;
            case LPAREN:
            case Identifier:
            case QuotedIdentifier:
                {
                alt50=2;
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:270:5: literal
                    {
                    dbg.location(270,5);
                    pushFollow(FOLLOW_literal_in_value1371);
                    literal();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:272:5: ( unary )? ( columnRef | nestedExpression )
                    {
                    dbg.location(272,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:272:5: ( unary )?
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

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:272:7: unary
                            {
                            dbg.location(272,7);
                            pushFollow(FOLLOW_unary_in_value1381);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(48);}

                    dbg.location(273,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:273:5: ( columnRef | nestedExpression )
                    int alt49=2;
                    try { dbg.enterSubRule(49);
                    try { dbg.enterDecision(49);

                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==Identifier||LA49_0==QuotedIdentifier) ) {
                        alt49=1;
                    }
                    else if ( (LA49_0==LPAREN) ) {
                        alt49=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 49, 0, input);

                        dbg.recognitionException(nvae);
                        throw nvae;
                    }
                    } finally {dbg.exitDecision(49);}

                    switch (alt49) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:273:7: columnRef
                            {
                            dbg.location(273,7);
                            pushFollow(FOLLOW_columnRef_in_value1392);
                            columnRef();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            dbg.enterAlt(2);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:274:7: nestedExpression
                            {
                            dbg.location(274,7);
                            pushFollow(FOLLOW_nestedExpression_in_value1400);
                            nestedExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(49);}


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
            dbg.exitRule(getGrammarFileName(), "value");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "value"


    // $ANTLR start "literal"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:279:1: literal : ( ( unary )? Float | ( unary )? Integer | String | TRUE | FALSE | date );
    public final void literal() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "literal");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(279, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:280:3: ( ( unary )? Float | ( unary )? Integer | String | TRUE | FALSE | date )
            int alt53=6;
            try { dbg.enterDecision(53);

            switch ( input.LA(1) ) {
            case PLUS:
            case MINUS:
                {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==Integer) ) {
                    alt53=2;
                }
                else if ( (LA53_1==Float) ) {
                    alt53=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 53, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case Float:
                {
                alt53=1;
                }
                break;
            case Integer:
                {
                alt53=2;
                }
                break;
            case String:
                {
                alt53=3;
                }
                break;
            case TRUE:
                {
                alt53=4;
                }
                break;
            case FALSE:
                {
                alt53=5;
                }
                break;
            case 110:
            case 111:
            case 112:
                {
                alt53=6;
                }
                break;
            default:
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:280:5: ( unary )? Float
                    {
                    dbg.location(280,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:280:5: ( unary )?
                    int alt51=2;
                    try { dbg.enterSubRule(51);
                    try { dbg.enterDecision(51);

                    int LA51_0 = input.LA(1);

                    if ( ((LA51_0>=PLUS && LA51_0<=MINUS)) ) {
                        alt51=1;
                    }
                    } finally {dbg.exitDecision(51);}

                    switch (alt51) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:280:7: unary
                            {
                            dbg.location(280,7);
                            pushFollow(FOLLOW_unary_in_literal1424);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(51);}

                    dbg.location(280,16);
                    match(input,Float,FOLLOW_Float_in_literal1429); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:281:5: ( unary )? Integer
                    {
                    dbg.location(281,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:281:5: ( unary )?
                    int alt52=2;
                    try { dbg.enterSubRule(52);
                    try { dbg.enterDecision(52);

                    int LA52_0 = input.LA(1);

                    if ( ((LA52_0>=PLUS && LA52_0<=MINUS)) ) {
                        alt52=1;
                    }
                    } finally {dbg.exitDecision(52);}

                    switch (alt52) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:281:7: unary
                            {
                            dbg.location(281,7);
                            pushFollow(FOLLOW_unary_in_literal1437);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(52);}

                    dbg.location(281,16);
                    match(input,Integer,FOLLOW_Integer_in_literal1442); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:282:5: String
                    {
                    dbg.location(282,5);
                    match(input,String,FOLLOW_String_in_literal1448); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:283:5: TRUE
                    {
                    dbg.location(283,5);
                    match(input,TRUE,FOLLOW_TRUE_in_literal1454); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:284:5: FALSE
                    {
                    dbg.location(284,5);
                    match(input,FALSE,FOLLOW_FALSE_in_literal1460); if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:285:5: date
                    {
                    dbg.location(285,5);
                    pushFollow(FOLLOW_date_in_literal1466);
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
        dbg.location(286, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:288:1: date : ( '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' );
    public final void date() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "date");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(288, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:289:3: ( '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' )
            int alt54=3;
            try { dbg.enterDecision(54);

            switch ( input.LA(1) ) {
            case 110:
                {
                alt54=1;
                }
                break;
            case 111:
                {
                alt54=2;
                }
                break;
            case 112:
                {
                alt54=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(54);}

            switch (alt54) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:289:5: '{d' Timestamp '}'
                    {
                    dbg.location(289,5);
                    match(input,110,FOLLOW_110_in_date1479); if (state.failed) return ;
                    dbg.location(289,10);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1481); if (state.failed) return ;
                    dbg.location(289,20);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1483); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:290:5: '{t' Timestamp '}'
                    {
                    dbg.location(290,5);
                    match(input,111,FOLLOW_111_in_date1490); if (state.failed) return ;
                    dbg.location(290,10);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1492); if (state.failed) return ;
                    dbg.location(290,20);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1494); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:291:5: '{ts' Timestamp '}'
                    {
                    dbg.location(291,5);
                    match(input,112,FOLLOW_112_in_date1501); if (state.failed) return ;
                    dbg.location(291,11);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1503); if (state.failed) return ;
                    dbg.location(291,21);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1505); if (state.failed) return ;

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
        dbg.location(292, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:294:1: unary : ( MINUS | PLUS );
    public final void unary() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "unary");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(294, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:295:3: ( MINUS | PLUS )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(295,3);
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
        dbg.location(297, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:316:1: tableRef : ( tableName | databaseName DOT tableName );
    public final void tableRef() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableRef");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(316, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:317:3: ( tableName | databaseName DOT tableName )
            int alt55=2;
            try { dbg.enterDecision(55);

            int LA55_0 = input.LA(1);

            if ( (LA55_0==Identifier||LA55_0==QuotedIdentifier) ) {
                int LA55_1 = input.LA(2);

                if ( (LA55_1==DOT) ) {
                    alt55=2;
                }
                else if ( (LA55_1==EOF||(LA55_1>=SEMI && LA55_1<=RPAREN)||(LA55_1>=SET && LA55_1<=COMMA)||LA55_1==VALUES||LA55_1==AS||(LA55_1>=Identifier && LA55_1<=FROM)||(LA55_1>=WHERE && LA55_1<=GROUP)||(LA55_1>=HAVING && LA55_1<=ORDER)) ) {
                    alt55=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 55, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(55);}

            switch (alt55) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:317:5: tableName
                    {
                    dbg.location(317,5);
                    pushFollow(FOLLOW_tableName_in_tableRef1560);
                    tableName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:318:5: databaseName DOT tableName
                    {
                    dbg.location(318,5);
                    pushFollow(FOLLOW_databaseName_in_tableRef1566);
                    databaseName();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(318,18);
                    match(input,DOT,FOLLOW_DOT_in_tableRef1568); if (state.failed) return ;
                    dbg.location(318,22);
                    pushFollow(FOLLOW_tableName_in_tableRef1570);
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
        dbg.location(319, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:321:1: columnRef : ( columnName | tableAlias DOT columnName );
    public final void columnRef() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnRef");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(321, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:322:3: ( columnName | tableAlias DOT columnName )
            int alt56=2;
            try { dbg.enterDecision(56);

            int LA56_0 = input.LA(1);

            if ( (LA56_0==Identifier) ) {
                int LA56_1 = input.LA(2);

                if ( (LA56_1==DOT) ) {
                    alt56=2;
                }
                else if ( (LA56_1==EOF||LA56_1==SEMI||LA56_1==RPAREN||LA56_1==ALL||(LA56_1>=COMMA && LA56_1<=INTO)||(LA56_1>=STAR && LA56_1<=AS)||(LA56_1>=Identifier && LA56_1<=FULL)||LA56_1==JOIN||LA56_1==GROUP||(LA56_1>=HAVING && LA56_1<=IS)||(LA56_1>=LIKE && LA56_1<=DIVIDE)) ) {
                    alt56=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 56, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else if ( (LA56_0==QuotedIdentifier) ) {
                alt56=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(56);}

            switch (alt56) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:322:5: columnName
                    {
                    dbg.location(322,5);
                    pushFollow(FOLLOW_columnName_in_columnRef1585);
                    columnName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:323:5: tableAlias DOT columnName
                    {
                    dbg.location(323,5);
                    pushFollow(FOLLOW_tableAlias_in_columnRef1592);
                    tableAlias();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(323,16);
                    match(input,DOT,FOLLOW_DOT_in_columnRef1594); if (state.failed) return ;
                    dbg.location(323,20);
                    pushFollow(FOLLOW_columnName_in_columnRef1596);
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
        dbg.location(324, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:326:1: databaseName : ( Identifier | QuotedIdentifier );
    public final void databaseName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "databaseName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(326, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:327:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(327,3);
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
        dbg.location(329, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:331:1: tableName : ( Identifier | QuotedIdentifier );
    public final void tableName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableName");
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
            dbg.exitRule(getGrammarFileName(), "tableName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "tableName"


    // $ANTLR start "tableAlias"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:336:1: tableAlias : Identifier ;
    public final void tableAlias() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableAlias");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(336, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:337:3: ( Identifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:337:5: Identifier
            {
            dbg.location(337,5);
            match(input,Identifier,FOLLOW_Identifier_in_tableAlias1651); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(338, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:340:1: columnName : ( Identifier | QuotedIdentifier );
    public final void columnName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(340, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:341:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(341,3);
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
        dbg.location(343, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "columnName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "columnName"

    // $ANTLR start synpred48_GenericSQL
    public final void synpred48_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:188:7: ( nestedCondition )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:188:7: nestedCondition
        {
        dbg.location(188,7);
        pushFollow(FOLLOW_nestedCondition_in_synpred48_GenericSQL928);
        nestedCondition();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred48_GenericSQL

    // $ANTLR start synpred49_GenericSQL
    public final void synpred49_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:189:7: ( in )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:189:7: in
        {
        dbg.location(189,7);
        pushFollow(FOLLOW_in_in_synpred49_GenericSQL936);
        in();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred49_GenericSQL

    // $ANTLR start synpred50_GenericSQL
    public final void synpred50_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:190:7: ( between )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:190:7: between
        {
        dbg.location(190,7);
        pushFollow(FOLLOW_between_in_synpred50_GenericSQL944);
        between();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred50_GenericSQL

    // $ANTLR start synpred51_GenericSQL
    public final void synpred51_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:7: ( isNull )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:7: isNull
        {
        dbg.location(191,7);
        pushFollow(FOLLOW_isNull_in_synpred51_GenericSQL952);
        isNull();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred51_GenericSQL

    // $ANTLR start synpred53_GenericSQL
    public final void synpred53_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:193:7: ( like )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:193:7: like
        {
        dbg.location(193,7);
        pushFollow(FOLLOW_like_in_synpred53_GenericSQL968);
        like();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred53_GenericSQL

    // $ANTLR start synpred54_GenericSQL
    public final void synpred54_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:194:7: ( quantifier )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:194:7: quantifier
        {
        dbg.location(194,7);
        pushFollow(FOLLOW_quantifier_in_synpred54_GenericSQL976);
        quantifier();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred54_GenericSQL

    // Delegated rules

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
    public final boolean synpred51_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred51_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred53_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred53_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred54_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred54_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred48_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred48_GenericSQL_fragment(); // can never throw exception
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


    protected DFA39 dfa39 = new DFA39(this);
    static final String DFA39_eotS =
        "\25\uffff";
    static final String DFA39_eofS =
        "\25\uffff";
    static final String DFA39_minS =
        "\1\5\14\0\10\uffff";
    static final String DFA39_maxS =
        "\1\160\14\0\10\uffff";
    static final String DFA39_acceptS =
        "\15\uffff\1\5\1\1\1\2\1\3\1\4\1\6\1\7\1\10";
    static final String DFA39_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\10"+
        "\uffff}>";
    static final String[] DFA39_transitionS = {
            "\1\1\6\uffff\1\4\13\uffff\1\13\27\uffff\1\15\11\uffff\2\2\1"+
            "\uffff\1\3\1\5\1\6\1\7\1\uffff\1\14\53\uffff\1\10\1\11\1\12",
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

    static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);
    static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);
    static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);
    static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);
    static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);
    static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);
    static final short[][] DFA39_transition;

    static {
        int numStates = DFA39_transitionS.length;
        DFA39_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
        }
    }

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = DFA39_eot;
            this.eof = DFA39_eof;
            this.min = DFA39_min;
            this.max = DFA39_max;
            this.accept = DFA39_accept;
            this.special = DFA39_special;
            this.transition = DFA39_transition;
        }
        public String getDescription() {
            return "188:5: ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA39_1 = input.LA(1);

                         
                        int index39_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred48_GenericSQL()) ) {s = 14;}

                        else if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA39_2 = input.LA(1);

                         
                        int index39_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA39_3 = input.LA(1);

                         
                        int index39_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA39_4 = input.LA(1);

                         
                        int index39_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA39_5 = input.LA(1);

                         
                        int index39_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA39_6 = input.LA(1);

                         
                        int index39_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA39_7 = input.LA(1);

                         
                        int index39_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA39_8 = input.LA(1);

                         
                        int index39_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA39_9 = input.LA(1);

                         
                        int index39_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA39_10 = input.LA(1);

                         
                        int index39_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA39_11 = input.LA(1);

                         
                        int index39_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA39_12 = input.LA(1);

                         
                        int index39_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred49_GenericSQL()) ) {s = 15;}

                        else if ( (synpred50_GenericSQL()) ) {s = 16;}

                        else if ( (synpred51_GenericSQL()) ) {s = 17;}

                        else if ( (synpred53_GenericSQL()) ) {s = 18;}

                        else if ( (synpred54_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index39_12);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 39, _s, input);
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
    public static final BitSet FOLLOW_update_in_statement98 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_statement102 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_statement107 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_subSelect123 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_subSelect129 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_select_in_subSelect131 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_subSelect133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_in_select148 = new BitSet(new long[]{0xEC00000001201F20L,0x0001C00000000005L});
    public static final BitSet FOLLOW_set_in_select154 = new BitSet(new long[]{0xEC00000001201F20L,0x0001C00000000005L});
    public static final BitSet FOLLOW_TOP_in_select175 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Integer_in_select177 = new BitSet(new long[]{0xEC00000001203F20L,0x0001C00000000005L});
    public static final BitSet FOLLOW_PERCENT_in_select181 = new BitSet(new long[]{0xEC00000001201F20L,0x0001C00000000005L});
    public static final BitSet FOLLOW_itemList_in_select193 = new BitSet(new long[]{0x0000000002080000L});
    public static final BitSet FOLLOW_into_in_select201 = new BitSet(new long[]{0x0000000002080000L});
    public static final BitSet FOLLOW_from_in_select210 = new BitSet(new long[]{0x0000006C00000002L});
    public static final BitSet FOLLOW_where_in_select219 = new BitSet(new long[]{0x0000006800000002L});
    public static final BitSet FOLLOW_groupBy_in_select230 = new BitSet(new long[]{0x0000006000000002L});
    public static final BitSet FOLLOW_having_in_select241 = new BitSet(new long[]{0x0000004000000002L});
    public static final BitSet FOLLOW_orderBy_in_select252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INSERT_in_insert270 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_into_in_insert272 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_columnList_in_insert276 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_values_in_insert285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPDATE_in_update305 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_tableRef_in_update307 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_SET_in_update309 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_setter_in_update311 = new BitSet(new long[]{0x0000000400020002L});
    public static final BitSet FOLLOW_COMMA_in_update315 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_setter_in_update317 = new BitSet(new long[]{0x0000000400020002L});
    public static final BitSet FOLLOW_where_in_update328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_columnName_in_setter346 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQ_in_setter348 = new BitSet(new long[]{0xEC00000000001000L,0x0001C00000000001L});
    public static final BitSet FOLLOW_literal_in_setter350 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTO_in_into365 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_tableRef_in_into367 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_into371 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_tableRef_in_into373 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_LPAREN_in_columnList389 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_columnName_in_columnList391 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_COMMA_in_columnList395 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_columnName_in_columnList397 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_RPAREN_in_columnList402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUES_in_values417 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_values419 = new BitSet(new long[]{0xEC00000000001000L,0x0001C00000000001L});
    public static final BitSet FOLLOW_literal_in_values421 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_COMMA_in_values425 = new BitSet(new long[]{0xEC00000000001000L,0x0001C00000000001L});
    public static final BitSet FOLLOW_literal_in_values427 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_RPAREN_in_values432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_itemList447 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_item_in_itemList453 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_itemList457 = new BitSet(new long[]{0xEC00000001201F20L,0x0001C00000000005L});
    public static final BitSet FOLLOW_item_in_itemList459 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_value_in_item477 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_item483 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_item488 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_allColumns_in_item497 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_allColumns511 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_DOT_in_allColumns513 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_STAR_in_allColumns515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_alias528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_in_from546 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000004L});
    public static final BitSet FOLLOW_fromItem_in_from548 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_from552 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000004L});
    public static final BitSet FOLLOW_fromItem_in_from554 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_LPAREN_in_fromItem576 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_fromItem578 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_fromItem580 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_tableRef_in_fromItem591 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_fromItem608 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_fromItem613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_join_in_joinList631 = new BitSet(new long[]{0x00000000BC000002L});
    public static final BitSet FOLLOW_INNER_in_join651 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_set_in_join659 = new BitSet(new long[]{0x00000000C0000000L});
    public static final BitSet FOLLOW_OUTER_in_join675 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_JOIN_in_join688 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_ON_in_join696 = new BitSet(new long[]{0xEC01080001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_conditionList_in_join698 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USING_in_join706 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_join708 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_columnRef_in_join710 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_join714 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_columnRef_in_join716 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_WHERE_in_where747 = new BitSet(new long[]{0xEC01080001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_conditionList_in_where749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GROUP_in_groupBy764 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_BY_in_groupBy766 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_columnRef_in_groupBy768 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_groupBy772 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_columnRef_in_groupBy774 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_HAVING_in_having792 = new BitSet(new long[]{0xEC01080001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_conditionList_in_having794 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORDER_in_orderBy810 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_BY_in_orderBy812 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy814 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_orderBy818 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy820 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_columnRef_in_orderByItem838 = new BitSet(new long[]{0x0000018000000002L});
    public static final BitSet FOLLOW_set_in_orderByItem840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedCondition864 = new BitSet(new long[]{0xEC01080001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_conditionList_in_nestedCondition866 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedCondition868 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditionList883 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_set_in_conditionList887 = new BitSet(new long[]{0xEC01080001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_condition_in_conditionList897 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_NOT_in_condition917 = new BitSet(new long[]{0xEC01080001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_nestedCondition_in_condition928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_condition936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_condition944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_condition952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exists_in_condition960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_condition968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_condition976 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_condition983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_in1002 = new BitSet(new long[]{0x0000180000000000L});
    public static final BitSet FOLLOW_NOT_in_in1006 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_IN_in_in1011 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_in1013 = new BitSet(new long[]{0xEC000000010010A0L,0x0001C00000000005L});
    public static final BitSet FOLLOW_subSelect_in_in1017 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_expressionList_in_in1021 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_in1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_between1040 = new BitSet(new long[]{0x0000280000000000L});
    public static final BitSet FOLLOW_NOT_in_between1044 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_BETWEEN_in_between1049 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_expression_in_between1051 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_AND_in_between1053 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_expression_in_between1055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_isNull1070 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_IS_in_isNull1072 = new BitSet(new long[]{0x0000880000000000L});
    public static final BitSet FOLLOW_NOT_in_isNull1076 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_NULL_in_isNull1081 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXISTS_in_exists1096 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_expression_in_exists1098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_like1113 = new BitSet(new long[]{0x0002080000000000L});
    public static final BitSet FOLLOW_NOT_in_like1117 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_LIKE_in_like1122 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_expression_in_like1124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_comparison1142 = new BitSet(new long[]{0x00FC000000040000L});
    public static final BitSet FOLLOW_comparator_in_comparison1144 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_expression_in_comparison1146 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_comparator0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_quantifier1211 = new BitSet(new long[]{0x0300000000000100L});
    public static final BitSet FOLLOW_set_in_quantifier1213 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_quantifier1227 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_quantifier1229 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_quantifier1231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList1246 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_expressionList1250 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_expression_in_expressionList1252 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedExpression1268 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_expression_in_nestedExpression1270 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedExpression1272 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiply_in_expression1287 = new BitSet(new long[]{0x0C00000000000002L});
    public static final BitSet FOLLOW_set_in_expression1291 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_multiply_in_expression1301 = new BitSet(new long[]{0x0C00000000000002L});
    public static final BitSet FOLLOW_value_in_multiply1318 = new BitSet(new long[]{0x1000000000200002L});
    public static final BitSet FOLLOW_set_in_multiply1322 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_value_in_multiply1332 = new BitSet(new long[]{0x1000000000200002L});
    public static final BitSet FOLLOW_literal_in_value1371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_value1381 = new BitSet(new long[]{0xEC00000001001020L,0x0001C00000000005L});
    public static final BitSet FOLLOW_columnRef_in_value1392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedExpression_in_value1400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_literal1424 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_Float_in_literal1429 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_literal1437 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Integer_in_literal1442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_literal1448 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literal1454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literal1460 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_in_literal1466 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_110_in_date1479 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_Timestamp_in_date1481 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1483 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_111_in_date1490 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_Timestamp_in_date1492 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_112_in_date1501 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_Timestamp_in_date1503 = new BitSet(new long[]{0x0000000000000000L,0x0000002000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_unary0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableName_in_tableRef1560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_databaseName_in_tableRef1566 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_DOT_in_tableRef1568 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_tableName_in_tableRef1570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_columnName_in_columnRef1585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_columnRef1592 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_DOT_in_columnRef1594 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_columnName_in_columnRef1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_databaseName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_tableName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_tableAlias1651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_columnName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedCondition_in_synpred48_GenericSQL928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_synpred49_GenericSQL936 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_synpred50_GenericSQL944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_synpred51_GenericSQL952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_synpred53_GenericSQL968 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_synpred54_GenericSQL976 = new BitSet(new long[]{0x0000000000000002L});

}