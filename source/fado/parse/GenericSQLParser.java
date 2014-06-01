// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g 2014-04-21 13:42:45
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SEMI", "LPAREN", "RPAREN", "SELECT", "ALL", "DISTINCT", "UNIQUE", "TOP", "Integer", "PERCENT", "INSERT", "UPDATE", "SET", "COMMA", "EQ", "INTO", "VALUES", "STAR", "AS", "DOT", "Identifier", "FROM", "JOIN", "INNER", "LEFT", "OUTER", "RIGHT", "NATURAL", "ON", "WHERE", "GROUP", "BY", "HAVING", "ORDER", "ASC", "DESC", "OR", "AND", "NOT", "IN", "BETWEEN", "IS", "NULL", "EXISTS", "LIKE", "NEQ1", "NEQ2", "LTE", "LT", "GTE", "GT", "ANY", "SOME", "PLUS", "MINUS", "DIVIDE", "Float", "String", "TRUE", "FALSE", "Timestamp", "CASE", "WHEN", "THEN", "ELSE", "END", "QuotedIdentifier", "A", "L", "N", "D", "Y", "S", "C", "B", "E", "T", "W", "DELETE", "I", "X", "F", "R", "O", "M", "U", "FULL", "G", "P", "H", "V", "J", "K", "UNION", "Q", "USING", "MAX", "MIN", "COUNT", "Z", "LCURLY", "RCURLY", "STRCAT", "QUESTION", "COLON", "MOD", "Digit", "Exponent", "Comment", "Whitespace", "'{d'", "'{t'", "'{ts'"
    };
    public static final int LT=52;
    public static final int STAR=21;
    public static final int MOD=109;
    public static final int CASE=65;
    public static final int COUNT=102;
    public static final int NOT=42;
    public static final int EOF=-1;
    public static final int Identifier=24;
    public static final int RPAREN=6;
    public static final int FULL=90;
    public static final int INSERT=14;
    public static final int USING=99;
    public static final int Comment=112;
    public static final int EQ=18;
    public static final int SELECT=7;
    public static final int INTO=19;
    public static final int DIVIDE=59;
    public static final int D=74;
    public static final int E=79;
    public static final int UNIQUE=10;
    public static final int F=85;
    public static final int G=91;
    public static final int A=71;
    public static final int B=78;
    public static final int ASC=38;
    public static final int C=77;
    public static final int L=72;
    public static final int M=88;
    public static final int N=73;
    public static final int O=87;
    public static final int H=93;
    public static final int NULL=46;
    public static final int I=83;
    public static final int J=95;
    public static final int ELSE=68;
    public static final int K=96;
    public static final int U=89;
    public static final int ON=32;
    public static final int T=80;
    public static final int W=81;
    public static final int LCURLY=104;
    public static final int V=94;
    public static final int Q=98;
    public static final int P=92;
    public static final int DELETE=82;
    public static final int S=76;
    public static final int R=86;
    public static final int Y=75;
    public static final int X=84;
    public static final int Z=103;
    public static final int Float=60;
    public static final int GROUP=34;
    public static final int OR=40;
    public static final int Timestamp=64;
    public static final int GT=54;
    public static final int FROM=25;
    public static final int END=69;
    public static final int FALSE=63;
    public static final int DISTINCT=9;
    public static final int NEQ1=49;
    public static final int WHERE=33;
    public static final int INNER=27;
    public static final int ORDER=37;
    public static final int T__116=116;
    public static final int T__114=114;
    public static final int NEQ2=50;
    public static final int GTE=53;
    public static final int T__115=115;
    public static final int MAX=100;
    public static final int UPDATE=15;
    public static final int Exponent=111;
    public static final int AND=41;
    public static final int LTE=51;
    public static final int LPAREN=5;
    public static final int AS=22;
    public static final int IN=43;
    public static final int THEN=67;
    public static final int COMMA=17;
    public static final int IS=45;
    public static final int LEFT=28;
    public static final int SOME=56;
    public static final int ALL=8;
    public static final int PLUS=57;
    public static final int EXISTS=47;
    public static final int String=61;
    public static final int DOT=23;
    public static final int Whitespace=113;
    public static final int STRCAT=106;
    public static final int LIKE=48;
    public static final int OUTER=29;
    public static final int BY=35;
    public static final int PERCENT=13;
    public static final int VALUES=20;
    public static final int RIGHT=30;
    public static final int SET=16;
    public static final int HAVING=36;
    public static final int MIN=101;
    public static final int MINUS=58;
    public static final int Digit=110;
    public static final int QuotedIdentifier=70;
    public static final int SEMI=4;
    public static final int TRUE=62;
    public static final int JOIN=26;
    public static final int UNION=97;
    public static final int COLON=108;
    public static final int ANY=55;
    public static final int QUESTION=107;
    public static final int WHEN=66;
    public static final int RCURLY=105;
    public static final int NATURAL=31;
    public static final int DESC=39;
    public static final int TOP=11;
    public static final int BETWEEN=44;
    public static final int Integer=12;

    // delegates
    // delegators

    public static final String[] ruleNames = new String[] {
        "invalidRule", "synpred84_GenericSQL", "synpred13_GenericSQL", "synpred92_GenericSQL", 
        "synpred57_GenericSQL", "nestedCondition", "synpred10_GenericSQL", 
        "synpred83_GenericSQL", "synpred4_GenericSQL", "comparator", "synpred64_GenericSQL", 
        "into", "orderBy", "synpred44_GenericSQL", "having", "columnRef", 
        "synpred100_GenericSQL", "synpred81_GenericSQL", "isNull", "synpred29_GenericSQL", 
        "tableRef", "synpred91_GenericSQL", "orderByItem", "synpred21_GenericSQL", 
        "update", "synpred60_GenericSQL", "synpred89_GenericSQL", "synpred69_GenericSQL", 
        "synpred3_GenericSQL", "values", "synpred63_GenericSQL", "value", 
        "synpred23_GenericSQL", "like", "synpred49_GenericSQL", "nestedExpression", 
        "conditionList", "synpred32_GenericSQL", "setter", "synpred76_GenericSQL", 
        "synpred20_GenericSQL", "date", "synpred47_GenericSQL", "synpred43_GenericSQL", 
        "synpred48_GenericSQL", "synpred85_GenericSQL", "synpred8_GenericSQL", 
        "select", "condition", "synpred14_GenericSQL", "synpred26_GenericSQL", 
        "synpred103_GenericSQL", "groupBy", "synpred68_GenericSQL", "synpred31_GenericSQL", 
        "tableAlias", "synpred61_GenericSQL", "synpred80_GenericSQL", "synpred25_GenericSQL", 
        "synpred79_GenericSQL", "synpred97_GenericSQL", "statement", "synpred98_GenericSQL", 
        "synpred51_GenericSQL", "synpred59_GenericSQL", "synpred101_GenericSQL", 
        "synpred30_GenericSQL", "synpred42_GenericSQL", "item", "quantifier", 
        "between", "comparison", "synpred41_GenericSQL", "synpred67_GenericSQL", 
        "synpred24_GenericSQL", "synpred17_GenericSQL", "synpred73_GenericSQL", 
        "synpred55_GenericSQL", "synpred12_GenericSQL", "synpred18_GenericSQL", 
        "synpred9_GenericSQL", "synpred75_GenericSQL", "synpred87_GenericSQL", 
        "synpred50_GenericSQL", "expressionList", "in", "synpred15_GenericSQL", 
        "databaseName", "synpred11_GenericSQL", "joinList", "synpred72_GenericSQL", 
        "synpred34_GenericSQL", "synpred38_GenericSQL", "synpred93_GenericSQL", 
        "synpred40_GenericSQL", "columnName", "synpred5_GenericSQL", "columnList", 
        "synpred36_GenericSQL", "synpred77_GenericSQL", "allColumns", "synpred16_GenericSQL", 
        "synpred62_GenericSQL", "synpred28_GenericSQL", "unary", "synpred86_GenericSQL", 
        "synpred53_GenericSQL", "synpred96_GenericSQL", "synpred99_GenericSQL", 
        "itemList", "synpred70_GenericSQL", "fromItem", "synpred66_GenericSQL", 
        "synpred58_GenericSQL", "synpred27_GenericSQL", "synpred1_GenericSQL", 
        "synpred94_GenericSQL", "expression", "literal", "synpred82_GenericSQL", 
        "synpred46_GenericSQL", "synpred78_GenericSQL", "synpred22_GenericSQL", 
        "synpred19_GenericSQL", "synpred37_GenericSQL", "synpred88_GenericSQL", 
        "synpred90_GenericSQL", "synpred65_GenericSQL", "synpred6_GenericSQL", 
        "caseExpression", "synpred45_GenericSQL", "synpred74_GenericSQL", 
        "synpred54_GenericSQL", "alias", "subSelect", "multiply", "insert", 
        "join", "function", "synpred95_GenericSQL", "where", "exists", "synpred102_GenericSQL", 
        "synpred71_GenericSQL", "from", "synpred35_GenericSQL", "synpred52_GenericSQL", 
        "synpred7_GenericSQL", "synpred39_GenericSQL", "synpred2_GenericSQL", 
        "synpred56_GenericSQL", "synpred33_GenericSQL", "tableName"
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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:53:1: statement : ( select ( SEMI )? EOF | insert ( SEMI )? EOF | update ( SEMI )? EOF );
    public final void statement() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "statement");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(53, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:54:3: ( select ( SEMI )? EOF | insert ( SEMI )? EOF | update ( SEMI )? EOF )
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:54:5: select ( SEMI )? EOF
                    {
                    dbg.location(54,5);
                    pushFollow(FOLLOW_select_in_statement67);
                    select();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(54,12);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:54:12: ( SEMI )?
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

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:54:14: SEMI
                            {
                            dbg.location(54,14);
                            match(input,SEMI,FOLLOW_SEMI_in_statement71); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(1);}

                    dbg.location(54,22);
                    match(input,EOF,FOLLOW_EOF_in_statement76); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:55:5: insert ( SEMI )? EOF
                    {
                    dbg.location(55,5);
                    pushFollow(FOLLOW_insert_in_statement82);
                    insert();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(55,12);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:55:12: ( SEMI )?
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

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:55:14: SEMI
                            {
                            dbg.location(55,14);
                            match(input,SEMI,FOLLOW_SEMI_in_statement86); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(2);}

                    dbg.location(55,22);
                    match(input,EOF,FOLLOW_EOF_in_statement91); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:5: update ( SEMI )? EOF
                    {
                    dbg.location(56,5);
                    pushFollow(FOLLOW_update_in_statement97);
                    update();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(56,12);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:12: ( SEMI )?
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

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:14: SEMI
                            {
                            dbg.location(56,14);
                            match(input,SEMI,FOLLOW_SEMI_in_statement101); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(3);}

                    dbg.location(56,22);
                    match(input,EOF,FOLLOW_EOF_in_statement106); if (state.failed) return ;

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
        dbg.location(58, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:60:1: subSelect : ( select | LPAREN select RPAREN );
    public final void subSelect() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "subSelect");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(60, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:61:3: ( select | LPAREN select RPAREN )
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:61:5: select
                    {
                    dbg.location(61,5);
                    pushFollow(FOLLOW_select_in_subSelect122);
                    select();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:62:5: LPAREN select RPAREN
                    {
                    dbg.location(62,5);
                    match(input,LPAREN,FOLLOW_LPAREN_in_subSelect128); if (state.failed) return ;
                    dbg.location(62,12);
                    pushFollow(FOLLOW_select_in_subSelect130);
                    select();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(62,19);
                    match(input,RPAREN,FOLLOW_RPAREN_in_subSelect132); if (state.failed) return ;

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
        dbg.location(63, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:65:1: select : SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( joinList )? ( where )? ( groupBy )? ( having )? ( orderBy )? ;
    public final void select() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "select");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(65, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:66:3: ( SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( joinList )? ( where )? ( groupBy )? ( having )? ( orderBy )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:66:5: SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( joinList )? ( where )? ( groupBy )? ( having )? ( orderBy )?
            {
            dbg.location(66,5);
            match(input,SELECT,FOLLOW_SELECT_in_select147); if (state.failed) return ;
            dbg.location(67,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:67:5: ( ALL | DISTINCT | UNIQUE )?
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
                    dbg.location(67,5);
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

            dbg.location(68,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:68:5: ( TOP Integer ( PERCENT )? )?
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:68:7: TOP Integer ( PERCENT )?
                    {
                    dbg.location(68,7);
                    match(input,TOP,FOLLOW_TOP_in_select174); if (state.failed) return ;
                    dbg.location(68,11);
                    match(input,Integer,FOLLOW_Integer_in_select176); if (state.failed) return ;
                    dbg.location(68,19);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:68:19: ( PERCENT )?
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

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:68:21: PERCENT
                            {
                            dbg.location(68,21);
                            match(input,PERCENT,FOLLOW_PERCENT_in_select180); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(7);}


                    }
                    break;

            }
            } finally {dbg.exitSubRule(8);}

            dbg.location(69,5);
            pushFollow(FOLLOW_itemList_in_select192);
            itemList();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(70,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:5: ( into )?
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:7: into
                    {
                    dbg.location(70,7);
                    pushFollow(FOLLOW_into_in_select200);
                    into();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(9);}

            dbg.location(71,5);
            pushFollow(FOLLOW_from_in_select209);
            from();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(72,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:5: ( joinList )?
            int alt10=2;
            try { dbg.enterSubRule(10);
            try { dbg.enterDecision(10);

            try {
                isCyclicDecision = true;
                alt10 = dfa10.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(10);}

            switch (alt10) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:7: joinList
                    {
                    dbg.location(72,7);
                    pushFollow(FOLLOW_joinList_in_select217);
                    joinList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(10);}

            dbg.location(73,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:73:5: ( where )?
            int alt11=2;
            try { dbg.enterSubRule(11);
            try { dbg.enterDecision(11);

            int LA11_0 = input.LA(1);

            if ( (LA11_0==WHERE) ) {
                alt11=1;
            }
            } finally {dbg.exitDecision(11);}

            switch (alt11) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:73:7: where
                    {
                    dbg.location(73,7);
                    pushFollow(FOLLOW_where_in_select228);
                    where();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(11);}

            dbg.location(74,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:74:5: ( groupBy )?
            int alt12=2;
            try { dbg.enterSubRule(12);
            try { dbg.enterDecision(12);

            int LA12_0 = input.LA(1);

            if ( (LA12_0==GROUP) ) {
                alt12=1;
            }
            } finally {dbg.exitDecision(12);}

            switch (alt12) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:74:7: groupBy
                    {
                    dbg.location(74,7);
                    pushFollow(FOLLOW_groupBy_in_select239);
                    groupBy();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(12);}

            dbg.location(75,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:75:5: ( having )?
            int alt13=2;
            try { dbg.enterSubRule(13);
            try { dbg.enterDecision(13);

            int LA13_0 = input.LA(1);

            if ( (LA13_0==HAVING) ) {
                alt13=1;
            }
            } finally {dbg.exitDecision(13);}

            switch (alt13) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:75:7: having
                    {
                    dbg.location(75,7);
                    pushFollow(FOLLOW_having_in_select250);
                    having();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(13);}

            dbg.location(76,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:76:5: ( orderBy )?
            int alt14=2;
            try { dbg.enterSubRule(14);
            try { dbg.enterDecision(14);

            int LA14_0 = input.LA(1);

            if ( (LA14_0==ORDER) ) {
                alt14=1;
            }
            } finally {dbg.exitDecision(14);}

            switch (alt14) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:76:7: orderBy
                    {
                    dbg.location(76,7);
                    pushFollow(FOLLOW_orderBy_in_select261);
                    orderBy();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(14);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(77, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:79:1: insert : INSERT into ( columnList )? ( values ) ;
    public final void insert() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "insert");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(79, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:80:3: ( INSERT into ( columnList )? ( values ) )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:80:5: INSERT into ( columnList )? ( values )
            {
            dbg.location(80,5);
            match(input,INSERT,FOLLOW_INSERT_in_insert279); if (state.failed) return ;
            dbg.location(80,12);
            pushFollow(FOLLOW_into_in_insert281);
            into();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(80,17);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:80:17: ( columnList )?
            int alt15=2;
            try { dbg.enterSubRule(15);
            try { dbg.enterDecision(15);

            int LA15_0 = input.LA(1);

            if ( (LA15_0==LPAREN) ) {
                alt15=1;
            }
            } finally {dbg.exitDecision(15);}

            switch (alt15) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:80:19: columnList
                    {
                    dbg.location(80,19);
                    pushFollow(FOLLOW_columnList_in_insert285);
                    columnList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(15);}

            dbg.location(81,3);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:81:3: ( values )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:81:5: values
            {
            dbg.location(81,5);
            pushFollow(FOLLOW_values_in_insert294);
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
        dbg.location(84, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:86:1: update : UPDATE tableRef SET setter ( COMMA setter )* ( where )? ;
    public final void update() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "update");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(86, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:3: ( UPDATE tableRef SET setter ( COMMA setter )* ( where )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:5: UPDATE tableRef SET setter ( COMMA setter )* ( where )?
            {
            dbg.location(87,5);
            match(input,UPDATE,FOLLOW_UPDATE_in_update314); if (state.failed) return ;
            dbg.location(87,12);
            pushFollow(FOLLOW_tableRef_in_update316);
            tableRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(87,21);
            match(input,SET,FOLLOW_SET_in_update318); if (state.failed) return ;
            dbg.location(87,25);
            pushFollow(FOLLOW_setter_in_update320);
            setter();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(87,32);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:32: ( COMMA setter )*
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

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:34: COMMA setter
            	    {
            	    dbg.location(87,34);
            	    match(input,COMMA,FOLLOW_COMMA_in_update324); if (state.failed) return ;
            	    dbg.location(87,40);
            	    pushFollow(FOLLOW_setter_in_update326);
            	    setter();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);
            } finally {dbg.exitSubRule(16);}

            dbg.location(88,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:88:5: ( where )?
            int alt17=2;
            try { dbg.enterSubRule(17);
            try { dbg.enterDecision(17);

            int LA17_0 = input.LA(1);

            if ( (LA17_0==WHERE) ) {
                alt17=1;
            }
            } finally {dbg.exitDecision(17);}

            switch (alt17) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:88:7: where
                    {
                    dbg.location(88,7);
                    pushFollow(FOLLOW_where_in_update337);
                    where();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(17);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(89, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:91:1: setter : columnName EQ literal ;
    public final void setter() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "setter");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(91, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:92:3: ( columnName EQ literal )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:92:5: columnName EQ literal
            {
            dbg.location(92,5);
            pushFollow(FOLLOW_columnName_in_setter355);
            columnName();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(92,16);
            match(input,EQ,FOLLOW_EQ_in_setter357); if (state.failed) return ;
            dbg.location(92,19);
            pushFollow(FOLLOW_literal_in_setter359);
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
        dbg.location(93, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:95:1: into : INTO tableRef ( COMMA tableRef )* ;
    public final void into() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "into");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(95, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:96:3: ( INTO tableRef ( COMMA tableRef )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:96:5: INTO tableRef ( COMMA tableRef )*
            {
            dbg.location(96,5);
            match(input,INTO,FOLLOW_INTO_in_into374); if (state.failed) return ;
            dbg.location(96,10);
            pushFollow(FOLLOW_tableRef_in_into376);
            tableRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(96,19);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:96:19: ( COMMA tableRef )*
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

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:96:21: COMMA tableRef
            	    {
            	    dbg.location(96,21);
            	    match(input,COMMA,FOLLOW_COMMA_in_into380); if (state.failed) return ;
            	    dbg.location(96,27);
            	    pushFollow(FOLLOW_tableRef_in_into382);
            	    tableRef();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);
            } finally {dbg.exitSubRule(18);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(97, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:99:1: columnList : LPAREN columnName ( COMMA columnName )* RPAREN ;
    public final void columnList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(99, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:100:3: ( LPAREN columnName ( COMMA columnName )* RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:100:5: LPAREN columnName ( COMMA columnName )* RPAREN
            {
            dbg.location(100,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_columnList398); if (state.failed) return ;
            dbg.location(100,12);
            pushFollow(FOLLOW_columnName_in_columnList400);
            columnName();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(100,23);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:100:23: ( COMMA columnName )*
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

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:100:25: COMMA columnName
            	    {
            	    dbg.location(100,25);
            	    match(input,COMMA,FOLLOW_COMMA_in_columnList404); if (state.failed) return ;
            	    dbg.location(100,31);
            	    pushFollow(FOLLOW_columnName_in_columnList406);
            	    columnName();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);
            } finally {dbg.exitSubRule(19);}

            dbg.location(100,45);
            match(input,RPAREN,FOLLOW_RPAREN_in_columnList411); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(101, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:103:1: values : VALUES LPAREN literal ( COMMA literal )* RPAREN ;
    public final void values() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "values");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(103, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:104:3: ( VALUES LPAREN literal ( COMMA literal )* RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:104:5: VALUES LPAREN literal ( COMMA literal )* RPAREN
            {
            dbg.location(104,5);
            match(input,VALUES,FOLLOW_VALUES_in_values426); if (state.failed) return ;
            dbg.location(104,12);
            match(input,LPAREN,FOLLOW_LPAREN_in_values428); if (state.failed) return ;
            dbg.location(104,19);
            pushFollow(FOLLOW_literal_in_values430);
            literal();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(104,27);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:104:27: ( COMMA literal )*
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

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:104:29: COMMA literal
            	    {
            	    dbg.location(104,29);
            	    match(input,COMMA,FOLLOW_COMMA_in_values434); if (state.failed) return ;
            	    dbg.location(104,35);
            	    pushFollow(FOLLOW_literal_in_values436);
            	    literal();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);
            } finally {dbg.exitSubRule(20);}

            dbg.location(104,46);
            match(input,RPAREN,FOLLOW_RPAREN_in_values441); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(105, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:107:1: itemList : ( STAR | item ( COMMA item )* );
    public final void itemList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "itemList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(107, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:108:3: ( STAR | item ( COMMA item )* )
            int alt22=2;
            try { dbg.enterDecision(22);

            int LA22_0 = input.LA(1);

            if ( (LA22_0==STAR) ) {
                alt22=1;
            }
            else if ( (LA22_0==LPAREN||LA22_0==Integer||LA22_0==Identifier||(LA22_0>=PLUS && LA22_0<=MINUS)||(LA22_0>=Float && LA22_0<=FALSE)||LA22_0==CASE||LA22_0==QuotedIdentifier||(LA22_0>=114 && LA22_0<=116)) ) {
                alt22=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(22);}

            switch (alt22) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:108:5: STAR
                    {
                    dbg.location(108,5);
                    match(input,STAR,FOLLOW_STAR_in_itemList456); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:109:5: item ( COMMA item )*
                    {
                    dbg.location(109,5);
                    pushFollow(FOLLOW_item_in_itemList462);
                    item();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(109,10);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:109:10: ( COMMA item )*
                    try { dbg.enterSubRule(21);

                    loop21:
                    do {
                        int alt21=2;
                        try { dbg.enterDecision(21);

                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==COMMA) ) {
                            alt21=1;
                        }


                        } finally {dbg.exitDecision(21);}

                        switch (alt21) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:109:12: COMMA item
                    	    {
                    	    dbg.location(109,12);
                    	    match(input,COMMA,FOLLOW_COMMA_in_itemList466); if (state.failed) return ;
                    	    dbg.location(109,18);
                    	    pushFollow(FOLLOW_item_in_itemList468);
                    	    item();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop21;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(21);}


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
        dbg.location(110, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:112:1: item : ( function ( ( AS )? alias )? | expression ( ( AS )? alias )? | allColumns | caseExpression ( ( AS )? alias )? );
    public final void item() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "item");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(112, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:3: ( function ( ( AS )? alias )? | expression ( ( AS )? alias )? | allColumns | caseExpression ( ( AS )? alias )? )
            int alt29=4;
            try { dbg.enterDecision(29);

            try {
                isCyclicDecision = true;
                alt29 = dfa29.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(29);}

            switch (alt29) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:5: function ( ( AS )? alias )?
                    {
                    dbg.location(113,5);
                    pushFollow(FOLLOW_function_in_item486);
                    function();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(113,14);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:14: ( ( AS )? alias )?
                    int alt24=2;
                    try { dbg.enterSubRule(24);
                    try { dbg.enterDecision(24);

                    int LA24_0 = input.LA(1);

                    if ( (LA24_0==AS||LA24_0==Identifier) ) {
                        alt24=1;
                    }
                    } finally {dbg.exitDecision(24);}

                    switch (alt24) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:16: ( AS )? alias
                            {
                            dbg.location(113,16);
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:16: ( AS )?
                            int alt23=2;
                            try { dbg.enterSubRule(23);
                            try { dbg.enterDecision(23);

                            int LA23_0 = input.LA(1);

                            if ( (LA23_0==AS) ) {
                                alt23=1;
                            }
                            } finally {dbg.exitDecision(23);}

                            switch (alt23) {
                                case 1 :
                                    dbg.enterAlt(1);

                                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:0:0: AS
                                    {
                                    dbg.location(113,16);
                                    match(input,AS,FOLLOW_AS_in_item490); if (state.failed) return ;

                                    }
                                    break;

                            }
                            } finally {dbg.exitSubRule(23);}

                            dbg.location(113,20);
                            pushFollow(FOLLOW_alias_in_item493);
                            alias();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(24);}


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:5: expression ( ( AS )? alias )?
                    {
                    dbg.location(114,5);
                    pushFollow(FOLLOW_expression_in_item503);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(114,16);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:16: ( ( AS )? alias )?
                    int alt26=2;
                    try { dbg.enterSubRule(26);
                    try { dbg.enterDecision(26);

                    int LA26_0 = input.LA(1);

                    if ( (LA26_0==AS||LA26_0==Identifier) ) {
                        alt26=1;
                    }
                    } finally {dbg.exitDecision(26);}

                    switch (alt26) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:18: ( AS )? alias
                            {
                            dbg.location(114,18);
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:18: ( AS )?
                            int alt25=2;
                            try { dbg.enterSubRule(25);
                            try { dbg.enterDecision(25);

                            int LA25_0 = input.LA(1);

                            if ( (LA25_0==AS) ) {
                                alt25=1;
                            }
                            } finally {dbg.exitDecision(25);}

                            switch (alt25) {
                                case 1 :
                                    dbg.enterAlt(1);

                                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:20: AS
                                    {
                                    dbg.location(114,20);
                                    match(input,AS,FOLLOW_AS_in_item509); if (state.failed) return ;

                                    }
                                    break;

                            }
                            } finally {dbg.exitSubRule(25);}

                            dbg.location(114,26);
                            pushFollow(FOLLOW_alias_in_item514);
                            alias();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(26);}


                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:5: allColumns
                    {
                    dbg.location(115,5);
                    pushFollow(FOLLOW_allColumns_in_item523);
                    allColumns();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:116:5: caseExpression ( ( AS )? alias )?
                    {
                    dbg.location(116,5);
                    pushFollow(FOLLOW_caseExpression_in_item529);
                    caseExpression();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(116,20);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:116:20: ( ( AS )? alias )?
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

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:116:22: ( AS )? alias
                            {
                            dbg.location(116,22);
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:116:22: ( AS )?
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

                                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:116:24: AS
                                    {
                                    dbg.location(116,24);
                                    match(input,AS,FOLLOW_AS_in_item535); if (state.failed) return ;

                                    }
                                    break;

                            }
                            } finally {dbg.exitSubRule(27);}

                            dbg.location(116,30);
                            pushFollow(FOLLOW_alias_in_item540);
                            alias();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(28);}


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
            pushFollow(FOLLOW_tableAlias_in_allColumns556);
            tableAlias();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(120,16);
            match(input,DOT,FOLLOW_DOT_in_allColumns558); if (state.failed) return ;
            dbg.location(120,20);
            match(input,STAR,FOLLOW_STAR_in_allColumns560); if (state.failed) return ;

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
            match(input,Identifier,FOLLOW_Identifier_in_alias573); if (state.failed) return ;

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


    // $ANTLR start "function"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:127:1: function : Identifier LPAREN ( expression ( COMMA expression )* )? RPAREN ;
    public final void function() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "function");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(127, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:128:3: ( Identifier LPAREN ( expression ( COMMA expression )* )? RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:128:5: Identifier LPAREN ( expression ( COMMA expression )* )? RPAREN
            {
            dbg.location(128,5);
            match(input,Identifier,FOLLOW_Identifier_in_function588); if (state.failed) return ;
            dbg.location(128,16);
            match(input,LPAREN,FOLLOW_LPAREN_in_function590); if (state.failed) return ;
            dbg.location(128,23);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:128:23: ( expression ( COMMA expression )* )?
            int alt31=2;
            try { dbg.enterSubRule(31);
            try { dbg.enterDecision(31);

            int LA31_0 = input.LA(1);

            if ( (LA31_0==LPAREN||LA31_0==Integer||LA31_0==Identifier||(LA31_0>=PLUS && LA31_0<=MINUS)||(LA31_0>=Float && LA31_0<=FALSE)||LA31_0==CASE||LA31_0==QuotedIdentifier||(LA31_0>=114 && LA31_0<=116)) ) {
                alt31=1;
            }
            } finally {dbg.exitDecision(31);}

            switch (alt31) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:128:25: expression ( COMMA expression )*
                    {
                    dbg.location(128,25);
                    pushFollow(FOLLOW_expression_in_function594);
                    expression();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(128,36);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:128:36: ( COMMA expression )*
                    try { dbg.enterSubRule(30);

                    loop30:
                    do {
                        int alt30=2;
                        try { dbg.enterDecision(30);

                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==COMMA) ) {
                            alt30=1;
                        }


                        } finally {dbg.exitDecision(30);}

                        switch (alt30) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:128:38: COMMA expression
                    	    {
                    	    dbg.location(128,38);
                    	    match(input,COMMA,FOLLOW_COMMA_in_function598); if (state.failed) return ;
                    	    dbg.location(128,44);
                    	    pushFollow(FOLLOW_expression_in_function600);
                    	    expression();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop30;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(30);}


                    }
                    break;

            }
            } finally {dbg.exitSubRule(31);}

            dbg.location(128,61);
            match(input,RPAREN,FOLLOW_RPAREN_in_function608); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(129, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "function");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "function"


    // $ANTLR start "from"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:138:1: from : FROM fromItem ( COMMA fromItem )* ;
    public final void from() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "from");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(138, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:139:3: ( FROM fromItem ( COMMA fromItem )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:139:5: FROM fromItem ( COMMA fromItem )*
            {
            dbg.location(139,5);
            match(input,FROM,FOLLOW_FROM_in_from632); if (state.failed) return ;
            dbg.location(139,10);
            pushFollow(FOLLOW_fromItem_in_from634);
            fromItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(139,19);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:139:19: ( COMMA fromItem )*
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

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:139:21: COMMA fromItem
            	    {
            	    dbg.location(139,21);
            	    match(input,COMMA,FOLLOW_COMMA_in_from638); if (state.failed) return ;
            	    dbg.location(139,27);
            	    pushFollow(FOLLOW_fromItem_in_from640);
            	    fromItem();

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
        dbg.location(140, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:142:1: fromItem : ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? ;
    public final void fromItem() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "fromItem");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(142, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:143:3: ( ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:143:5: ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )?
            {
            dbg.location(143,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:143:5: ( ( LPAREN subSelect RPAREN ) | tableRef )
            int alt33=2;
            try { dbg.enterSubRule(33);
            try { dbg.enterDecision(33);

            int LA33_0 = input.LA(1);

            if ( (LA33_0==LPAREN) ) {
                alt33=1;
            }
            else if ( (LA33_0==Identifier||LA33_0==QuotedIdentifier) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(33);}

            switch (alt33) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:143:7: ( LPAREN subSelect RPAREN )
                    {
                    dbg.location(143,7);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:143:7: ( LPAREN subSelect RPAREN )
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:143:9: LPAREN subSelect RPAREN
                    {
                    dbg.location(143,9);
                    match(input,LPAREN,FOLLOW_LPAREN_in_fromItem662); if (state.failed) return ;
                    dbg.location(143,16);
                    pushFollow(FOLLOW_subSelect_in_fromItem664);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(143,26);
                    match(input,RPAREN,FOLLOW_RPAREN_in_fromItem666); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:144:7: tableRef
                    {
                    dbg.location(144,7);
                    pushFollow(FOLLOW_tableRef_in_fromItem677);
                    tableRef();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(33);}

            dbg.location(146,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:146:5: ( ( AS )? alias )?
            int alt35=2;
            try { dbg.enterSubRule(35);
            try { dbg.enterDecision(35);

            int LA35_0 = input.LA(1);

            if ( (LA35_0==AS) ) {
                alt35=1;
            }
            else if ( (LA35_0==Identifier) ) {
                int LA35_2 = input.LA(2);

                if ( (LA35_2==EOF||LA35_2==SEMI||LA35_2==RPAREN||LA35_2==COMMA||LA35_2==Identifier||(LA35_2>=JOIN && LA35_2<=NATURAL)||(LA35_2>=WHERE && LA35_2<=GROUP)||(LA35_2>=HAVING && LA35_2<=ORDER)) ) {
                    alt35=1;
                }
            }
            } finally {dbg.exitDecision(35);}

            switch (alt35) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:146:7: ( AS )? alias
                    {
                    dbg.location(146,7);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:146:7: ( AS )?
                    int alt34=2;
                    try { dbg.enterSubRule(34);
                    try { dbg.enterDecision(34);

                    int LA34_0 = input.LA(1);

                    if ( (LA34_0==AS) ) {
                        alt34=1;
                    }
                    } finally {dbg.exitDecision(34);}

                    switch (alt34) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:146:9: AS
                            {
                            dbg.location(146,9);
                            match(input,AS,FOLLOW_AS_in_fromItem694); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(34);}

                    dbg.location(146,15);
                    pushFollow(FOLLOW_alias_in_fromItem699);
                    alias();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(35);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(147, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:149:1: joinList : ( join )* ;
    public final void joinList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "joinList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(149, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:150:3: ( ( join )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:150:5: ( join )*
            {
            dbg.location(150,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:150:5: ( join )*
            try { dbg.enterSubRule(36);

            loop36:
            do {
                int alt36=2;
                try { dbg.enterDecision(36);

                int LA36_0 = input.LA(1);

                if ( ((LA36_0>=JOIN && LA36_0<=NATURAL)) ) {
                    alt36=1;
                }


                } finally {dbg.exitDecision(36);}

                switch (alt36) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:150:7: join
            	    {
            	    dbg.location(150,7);
            	    pushFollow(FOLLOW_join_in_joinList717);
            	    join();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);
            } finally {dbg.exitSubRule(36);}


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
            dbg.exitRule(getGrammarFileName(), "joinList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "joinList"


    // $ANTLR start "join"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:153:1: join : ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN ) fromItem alias ON conditionList ;
    public final void join() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "join");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(153, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:154:3: ( ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN ) fromItem alias ON conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:155:5: ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN ) fromItem alias ON conditionList
            {
            dbg.location(155,5);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:155:5: ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN )
            int alt37=8;
            try { dbg.enterSubRule(37);
            try { dbg.enterDecision(37);

            try {
                isCyclicDecision = true;
                alt37 = dfa37.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(37);}

            switch (alt37) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:155:7: JOIN
                    {
                    dbg.location(155,7);
                    match(input,JOIN,FOLLOW_JOIN_in_join742); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:156:7: INNER JOIN
                    {
                    dbg.location(156,7);
                    match(input,INNER,FOLLOW_INNER_in_join750); if (state.failed) return ;
                    dbg.location(156,13);
                    match(input,JOIN,FOLLOW_JOIN_in_join752); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:157:7: LEFT JOIN
                    {
                    dbg.location(157,7);
                    match(input,LEFT,FOLLOW_LEFT_in_join760); if (state.failed) return ;
                    dbg.location(157,12);
                    match(input,JOIN,FOLLOW_JOIN_in_join762); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:158:7: LEFT OUTER JOIN
                    {
                    dbg.location(158,7);
                    match(input,LEFT,FOLLOW_LEFT_in_join770); if (state.failed) return ;
                    dbg.location(158,12);
                    match(input,OUTER,FOLLOW_OUTER_in_join772); if (state.failed) return ;
                    dbg.location(158,18);
                    match(input,JOIN,FOLLOW_JOIN_in_join774); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:159:7: RIGHT JOIN
                    {
                    dbg.location(159,7);
                    match(input,RIGHT,FOLLOW_RIGHT_in_join782); if (state.failed) return ;
                    dbg.location(159,13);
                    match(input,JOIN,FOLLOW_JOIN_in_join784); if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:160:7: RIGHT OUTER JOIN
                    {
                    dbg.location(160,7);
                    match(input,RIGHT,FOLLOW_RIGHT_in_join793); if (state.failed) return ;
                    dbg.location(160,13);
                    match(input,OUTER,FOLLOW_OUTER_in_join795); if (state.failed) return ;
                    dbg.location(160,19);
                    match(input,JOIN,FOLLOW_JOIN_in_join797); if (state.failed) return ;

                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:161:7: OUTER JOIN
                    {
                    dbg.location(161,7);
                    match(input,OUTER,FOLLOW_OUTER_in_join805); if (state.failed) return ;
                    dbg.location(161,13);
                    match(input,JOIN,FOLLOW_JOIN_in_join807); if (state.failed) return ;

                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:7: NATURAL JOIN
                    {
                    dbg.location(162,7);
                    match(input,NATURAL,FOLLOW_NATURAL_in_join816); if (state.failed) return ;
                    dbg.location(162,15);
                    match(input,JOIN,FOLLOW_JOIN_in_join818); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(37);}

            dbg.location(164,3);
            pushFollow(FOLLOW_fromItem_in_join829);
            fromItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(164,12);
            pushFollow(FOLLOW_alias_in_join831);
            alias();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(170,3);
            match(input,ON,FOLLOW_ON_in_join839); if (state.failed) return ;
            dbg.location(170,6);
            pushFollow(FOLLOW_conditionList_in_join841);
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
        dbg.location(171, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:1: where : WHERE conditionList ;
    public final void where() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "where");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(179, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:180:3: ( WHERE conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:180:5: WHERE conditionList
            {
            dbg.location(180,5);
            match(input,WHERE,FOLLOW_WHERE_in_where862); if (state.failed) return ;
            dbg.location(180,11);
            pushFollow(FOLLOW_conditionList_in_where864);
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
        dbg.location(181, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:1: groupBy : GROUP BY columnRef ( COMMA columnRef )* ;
    public final void groupBy() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "groupBy");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(183, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:184:3: ( GROUP BY columnRef ( COMMA columnRef )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:184:5: GROUP BY columnRef ( COMMA columnRef )*
            {
            dbg.location(184,5);
            match(input,GROUP,FOLLOW_GROUP_in_groupBy879); if (state.failed) return ;
            dbg.location(184,11);
            match(input,BY,FOLLOW_BY_in_groupBy881); if (state.failed) return ;
            dbg.location(184,14);
            pushFollow(FOLLOW_columnRef_in_groupBy883);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(184,24);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:184:24: ( COMMA columnRef )*
            try { dbg.enterSubRule(38);

            loop38:
            do {
                int alt38=2;
                try { dbg.enterDecision(38);

                int LA38_0 = input.LA(1);

                if ( (LA38_0==COMMA) ) {
                    alt38=1;
                }


                } finally {dbg.exitDecision(38);}

                switch (alt38) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:184:26: COMMA columnRef
            	    {
            	    dbg.location(184,26);
            	    match(input,COMMA,FOLLOW_COMMA_in_groupBy887); if (state.failed) return ;
            	    dbg.location(184,32);
            	    pushFollow(FOLLOW_columnRef_in_groupBy889);
            	    columnRef();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);
            } finally {dbg.exitSubRule(38);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(185, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:187:1: having : HAVING conditionList ;
    public final void having() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "having");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(187, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:188:3: ( HAVING conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:188:5: HAVING conditionList
            {
            dbg.location(188,5);
            match(input,HAVING,FOLLOW_HAVING_in_having907); if (state.failed) return ;
            dbg.location(188,12);
            pushFollow(FOLLOW_conditionList_in_having909);
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
        dbg.location(189, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:1: orderBy : ORDER BY orderByItem ( COMMA orderByItem )* ;
    public final void orderBy() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "orderBy");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(191, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:192:3: ( ORDER BY orderByItem ( COMMA orderByItem )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:192:5: ORDER BY orderByItem ( COMMA orderByItem )*
            {
            dbg.location(192,5);
            match(input,ORDER,FOLLOW_ORDER_in_orderBy924); if (state.failed) return ;
            dbg.location(192,11);
            match(input,BY,FOLLOW_BY_in_orderBy926); if (state.failed) return ;
            dbg.location(192,14);
            pushFollow(FOLLOW_orderByItem_in_orderBy928);
            orderByItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(192,26);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:192:26: ( COMMA orderByItem )*
            try { dbg.enterSubRule(39);

            loop39:
            do {
                int alt39=2;
                try { dbg.enterDecision(39);

                int LA39_0 = input.LA(1);

                if ( (LA39_0==COMMA) ) {
                    alt39=1;
                }


                } finally {dbg.exitDecision(39);}

                switch (alt39) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:192:28: COMMA orderByItem
            	    {
            	    dbg.location(192,28);
            	    match(input,COMMA,FOLLOW_COMMA_in_orderBy932); if (state.failed) return ;
            	    dbg.location(192,34);
            	    pushFollow(FOLLOW_orderByItem_in_orderBy934);
            	    orderByItem();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);
            } finally {dbg.exitSubRule(39);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(193, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:195:1: orderByItem : columnRef ( ASC | DESC )? ;
    public final void orderByItem() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "orderByItem");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(195, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:196:3: ( columnRef ( ASC | DESC )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:196:5: columnRef ( ASC | DESC )?
            {
            dbg.location(196,5);
            pushFollow(FOLLOW_columnRef_in_orderByItem952);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(196,15);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:196:15: ( ASC | DESC )?
            int alt40=2;
            try { dbg.enterSubRule(40);
            try { dbg.enterDecision(40);

            int LA40_0 = input.LA(1);

            if ( ((LA40_0>=ASC && LA40_0<=DESC)) ) {
                alt40=1;
            }
            } finally {dbg.exitDecision(40);}

            switch (alt40) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
                    {
                    dbg.location(196,15);
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
            } finally {dbg.exitSubRule(40);}


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
            dbg.exitRule(getGrammarFileName(), "orderByItem");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "orderByItem"


    // $ANTLR start "nestedCondition"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:199:1: nestedCondition : LPAREN conditionList RPAREN ;
    public final void nestedCondition() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "nestedCondition");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(199, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:3: ( LPAREN conditionList RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:5: LPAREN conditionList RPAREN
            {
            dbg.location(200,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedCondition978); if (state.failed) return ;
            dbg.location(200,12);
            pushFollow(FOLLOW_conditionList_in_nestedCondition980);
            conditionList();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(200,26);
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedCondition982); if (state.failed) return ;

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
            dbg.exitRule(getGrammarFileName(), "nestedCondition");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "nestedCondition"


    // $ANTLR start "conditionList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:203:1: conditionList : condition ( ( OR | AND ) condition )* ;
    public final void conditionList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "conditionList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(203, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:204:3: ( condition ( ( OR | AND ) condition )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:204:5: condition ( ( OR | AND ) condition )*
            {
            dbg.location(204,5);
            pushFollow(FOLLOW_condition_in_conditionList997);
            condition();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(204,15);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:204:15: ( ( OR | AND ) condition )*
            try { dbg.enterSubRule(41);

            loop41:
            do {
                int alt41=2;
                try { dbg.enterDecision(41);

                int LA41_0 = input.LA(1);

                if ( ((LA41_0>=OR && LA41_0<=AND)) ) {
                    alt41=1;
                }


                } finally {dbg.exitDecision(41);}

                switch (alt41) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:204:17: ( OR | AND ) condition
            	    {
            	    dbg.location(204,17);
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

            	    dbg.location(204,30);
            	    pushFollow(FOLLOW_condition_in_conditionList1011);
            	    condition();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);
            } finally {dbg.exitSubRule(41);}


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
            dbg.exitRule(getGrammarFileName(), "conditionList");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "conditionList"


    // $ANTLR start "condition"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:207:1: condition : ( comparison | nestedCondition | in | between | isNull | exists | like | quantifier );
    public final void condition() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "condition");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(207, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:210:5: ( comparison | nestedCondition | in | between | isNull | exists | like | quantifier )
            int alt42=8;
            try { dbg.enterDecision(42);

            try {
                isCyclicDecision = true;
                alt42 = dfa42.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(42);}

            switch (alt42) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:210:7: comparison
                    {
                    dbg.location(210,7);
                    pushFollow(FOLLOW_comparison_in_condition1036);
                    comparison();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:211:7: nestedCondition
                    {
                    dbg.location(211,7);
                    pushFollow(FOLLOW_nestedCondition_in_condition1044);
                    nestedCondition();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:212:7: in
                    {
                    dbg.location(212,7);
                    pushFollow(FOLLOW_in_in_condition1052);
                    in();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:213:7: between
                    {
                    dbg.location(213,7);
                    pushFollow(FOLLOW_between_in_condition1060);
                    between();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:214:7: isNull
                    {
                    dbg.location(214,7);
                    pushFollow(FOLLOW_isNull_in_condition1068);
                    isNull();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:215:7: exists
                    {
                    dbg.location(215,7);
                    pushFollow(FOLLOW_exists_in_condition1076);
                    exists();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:216:7: like
                    {
                    dbg.location(216,7);
                    pushFollow(FOLLOW_like_in_condition1084);
                    like();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:217:7: quantifier
                    {
                    dbg.location(217,7);
                    pushFollow(FOLLOW_quantifier_in_condition1092);
                    quantifier();

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
        dbg.location(219, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:221:1: in : expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN ;
    public final void in() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "in");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(221, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:3: ( expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:5: expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN
            {
            dbg.location(222,5);
            pushFollow(FOLLOW_expression_in_in1106);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(222,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:16: ( NOT )?
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:18: NOT
                    {
                    dbg.location(222,18);
                    match(input,NOT,FOLLOW_NOT_in_in1110); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(43);}

            dbg.location(222,25);
            match(input,IN,FOLLOW_IN_in_in1115); if (state.failed) return ;
            dbg.location(222,28);
            match(input,LPAREN,FOLLOW_LPAREN_in_in1117); if (state.failed) return ;
            dbg.location(222,35);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:35: ( subSelect | expressionList )
            int alt44=2;
            try { dbg.enterSubRule(44);
            try { dbg.enterDecision(44);

            switch ( input.LA(1) ) {
            case SELECT:
                {
                alt44=1;
                }
                break;
            case LPAREN:
                {
                int LA44_2 = input.LA(2);

                if ( (LA44_2==LPAREN||LA44_2==Integer||LA44_2==Identifier||(LA44_2>=PLUS && LA44_2<=MINUS)||(LA44_2>=Float && LA44_2<=FALSE)||LA44_2==CASE||LA44_2==QuotedIdentifier||(LA44_2>=114 && LA44_2<=116)) ) {
                    alt44=2;
                }
                else if ( (LA44_2==SELECT) ) {
                    alt44=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 44, 2, input);

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
            case CASE:
            case QuotedIdentifier:
            case 114:
            case 115:
            case 116:
                {
                alt44=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(44);}

            switch (alt44) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:37: subSelect
                    {
                    dbg.location(222,37);
                    pushFollow(FOLLOW_subSelect_in_in1121);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:49: expressionList
                    {
                    dbg.location(222,49);
                    pushFollow(FOLLOW_expressionList_in_in1125);
                    expressionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(44);}

            dbg.location(222,66);
            match(input,RPAREN,FOLLOW_RPAREN_in_in1129); if (state.failed) return ;

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
            dbg.exitRule(getGrammarFileName(), "in");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "in"


    // $ANTLR start "between"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:225:1: between : expression ( NOT )? BETWEEN expression AND expression ;
    public final void between() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "between");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(225, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:3: ( expression ( NOT )? BETWEEN expression AND expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:5: expression ( NOT )? BETWEEN expression AND expression
            {
            dbg.location(226,5);
            pushFollow(FOLLOW_expression_in_between1144);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(226,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:16: ( NOT )?
            int alt45=2;
            try { dbg.enterSubRule(45);
            try { dbg.enterDecision(45);

            int LA45_0 = input.LA(1);

            if ( (LA45_0==NOT) ) {
                alt45=1;
            }
            } finally {dbg.exitDecision(45);}

            switch (alt45) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:18: NOT
                    {
                    dbg.location(226,18);
                    match(input,NOT,FOLLOW_NOT_in_between1148); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(45);}

            dbg.location(226,25);
            match(input,BETWEEN,FOLLOW_BETWEEN_in_between1153); if (state.failed) return ;
            dbg.location(226,33);
            pushFollow(FOLLOW_expression_in_between1155);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(226,44);
            match(input,AND,FOLLOW_AND_in_between1157); if (state.failed) return ;
            dbg.location(226,48);
            pushFollow(FOLLOW_expression_in_between1159);
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
        dbg.location(227, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:229:1: isNull : expression IS ( NOT )? NULL ;
    public final void isNull() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "isNull");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(229, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:230:3: ( expression IS ( NOT )? NULL )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:230:5: expression IS ( NOT )? NULL
            {
            dbg.location(230,5);
            pushFollow(FOLLOW_expression_in_isNull1174);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(230,16);
            match(input,IS,FOLLOW_IS_in_isNull1176); if (state.failed) return ;
            dbg.location(230,19);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:230:19: ( NOT )?
            int alt46=2;
            try { dbg.enterSubRule(46);
            try { dbg.enterDecision(46);

            int LA46_0 = input.LA(1);

            if ( (LA46_0==NOT) ) {
                alt46=1;
            }
            } finally {dbg.exitDecision(46);}

            switch (alt46) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:230:21: NOT
                    {
                    dbg.location(230,21);
                    match(input,NOT,FOLLOW_NOT_in_isNull1180); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(46);}

            dbg.location(230,28);
            match(input,NULL,FOLLOW_NULL_in_isNull1185); if (state.failed) return ;

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
            dbg.exitRule(getGrammarFileName(), "isNull");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "isNull"


    // $ANTLR start "exists"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:233:1: exists : EXISTS expression ;
    public final void exists() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "exists");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(233, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:234:3: ( EXISTS expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:234:5: EXISTS expression
            {
            dbg.location(234,5);
            match(input,EXISTS,FOLLOW_EXISTS_in_exists1200); if (state.failed) return ;
            dbg.location(234,12);
            pushFollow(FOLLOW_expression_in_exists1202);
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
        dbg.location(235, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:237:1: like : expression ( NOT )? LIKE expression ;
    public final void like() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "like");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(237, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:240:3: ( expression ( NOT )? LIKE expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:240:5: expression ( NOT )? LIKE expression
            {
            dbg.location(240,5);
            pushFollow(FOLLOW_expression_in_like1221);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(240,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:240:16: ( NOT )?
            int alt47=2;
            try { dbg.enterSubRule(47);
            try { dbg.enterDecision(47);

            int LA47_0 = input.LA(1);

            if ( (LA47_0==NOT) ) {
                alt47=1;
            }
            } finally {dbg.exitDecision(47);}

            switch (alt47) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:240:18: NOT
                    {
                    dbg.location(240,18);
                    match(input,NOT,FOLLOW_NOT_in_like1225); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(47);}

            dbg.location(240,25);
            match(input,LIKE,FOLLOW_LIKE_in_like1230); if (state.failed) return ;
            dbg.location(240,30);
            pushFollow(FOLLOW_expression_in_like1232);
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
        dbg.location(242, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:244:1: comparison : expression comparator expression ;
    public final void comparison() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "comparison");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(244, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:245:3: ( expression comparator expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:245:5: expression comparator expression
            {
            dbg.location(245,5);
            pushFollow(FOLLOW_expression_in_comparison1250);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(245,16);
            pushFollow(FOLLOW_comparator_in_comparison1252);
            comparator();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(245,27);
            pushFollow(FOLLOW_expression_in_comparison1254);
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
        dbg.location(246, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:248:1: comparator : ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT );
    public final void comparator() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "comparator");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(248, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:249:3: ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(249,3);
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
        dbg.location(256, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:258:1: quantifier : expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN ;
    public final void quantifier() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "quantifier");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(258, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:259:3: ( expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:259:5: expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN
            {
            dbg.location(259,5);
            pushFollow(FOLLOW_expression_in_quantifier1319);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(259,16);
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

            dbg.location(259,37);
            match(input,LPAREN,FOLLOW_LPAREN_in_quantifier1335); if (state.failed) return ;
            dbg.location(259,44);
            pushFollow(FOLLOW_subSelect_in_quantifier1337);
            subSelect();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(259,54);
            match(input,RPAREN,FOLLOW_RPAREN_in_quantifier1339); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(260, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:262:1: expressionList : expression ( COMMA expression )* ;
    public final void expressionList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expressionList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(262, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:263:3: ( expression ( COMMA expression )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:263:5: expression ( COMMA expression )*
            {
            dbg.location(263,5);
            pushFollow(FOLLOW_expression_in_expressionList1354);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(263,16);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:263:16: ( COMMA expression )*
            try { dbg.enterSubRule(48);

            loop48:
            do {
                int alt48=2;
                try { dbg.enterDecision(48);

                int LA48_0 = input.LA(1);

                if ( (LA48_0==COMMA) ) {
                    alt48=1;
                }


                } finally {dbg.exitDecision(48);}

                switch (alt48) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:263:18: COMMA expression
            	    {
            	    dbg.location(263,18);
            	    match(input,COMMA,FOLLOW_COMMA_in_expressionList1358); if (state.failed) return ;
            	    dbg.location(263,24);
            	    pushFollow(FOLLOW_expression_in_expressionList1360);
            	    expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop48;
                }
            } while (true);
            } finally {dbg.exitSubRule(48);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(264, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:266:1: nestedExpression : LPAREN expression RPAREN ;
    public final void nestedExpression() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "nestedExpression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(266, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:267:3: ( LPAREN expression RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:267:5: LPAREN expression RPAREN
            {
            dbg.location(267,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedExpression1376); if (state.failed) return ;
            dbg.location(267,12);
            pushFollow(FOLLOW_expression_in_nestedExpression1378);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(267,23);
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedExpression1380); if (state.failed) return ;

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
            dbg.exitRule(getGrammarFileName(), "nestedExpression");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "nestedExpression"


    // $ANTLR start "expression"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:270:1: expression : multiply ( ( PLUS | MINUS ) multiply )* ;
    public final void expression() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(270, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:271:3: ( multiply ( ( PLUS | MINUS ) multiply )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:271:5: multiply ( ( PLUS | MINUS ) multiply )*
            {
            dbg.location(271,5);
            pushFollow(FOLLOW_multiply_in_expression1395);
            multiply();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(271,14);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:271:14: ( ( PLUS | MINUS ) multiply )*
            try { dbg.enterSubRule(49);

            loop49:
            do {
                int alt49=2;
                try { dbg.enterDecision(49);

                int LA49_0 = input.LA(1);

                if ( ((LA49_0>=PLUS && LA49_0<=MINUS)) ) {
                    alt49=1;
                }


                } finally {dbg.exitDecision(49);}

                switch (alt49) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:271:16: ( PLUS | MINUS ) multiply
            	    {
            	    dbg.location(271,16);
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

            	    dbg.location(271,33);
            	    pushFollow(FOLLOW_multiply_in_expression1409);
            	    multiply();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop49;
                }
            } while (true);
            } finally {dbg.exitSubRule(49);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(272, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:274:1: multiply : value ( ( STAR | DIVIDE ) value )* ;
    public final void multiply() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "multiply");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(274, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:275:3: ( value ( ( STAR | DIVIDE ) value )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:275:5: value ( ( STAR | DIVIDE ) value )*
            {
            dbg.location(275,5);
            pushFollow(FOLLOW_value_in_multiply1426);
            value();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(275,11);
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:275:11: ( ( STAR | DIVIDE ) value )*
            try { dbg.enterSubRule(50);

            loop50:
            do {
                int alt50=2;
                try { dbg.enterDecision(50);

                int LA50_0 = input.LA(1);

                if ( (LA50_0==STAR||LA50_0==DIVIDE) ) {
                    alt50=1;
                }


                } finally {dbg.exitDecision(50);}

                switch (alt50) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:275:13: ( STAR | DIVIDE ) value
            	    {
            	    dbg.location(275,13);
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

            	    dbg.location(275,31);
            	    pushFollow(FOLLOW_value_in_multiply1440);
            	    value();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop50;
                }
            } while (true);
            } finally {dbg.exitSubRule(50);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(276, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:293:1: value : ( literal | caseExpression | ( unary )? ( columnRef | nestedExpression ) );
    public final void value() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "value");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(293, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:294:3: ( literal | caseExpression | ( unary )? ( columnRef | nestedExpression ) )
            int alt53=3;
            try { dbg.enterDecision(53);

            switch ( input.LA(1) ) {
            case PLUS:
            case MINUS:
                {
                int LA53_1 = input.LA(2);

                if ( (LA53_1==Integer||LA53_1==Float) ) {
                    alt53=1;
                }
                else if ( (LA53_1==LPAREN||LA53_1==Identifier||LA53_1==QuotedIdentifier) ) {
                    alt53=3;
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
            case Integer:
            case Float:
            case String:
            case TRUE:
            case FALSE:
            case 114:
            case 115:
            case 116:
                {
                alt53=1;
                }
                break;
            case CASE:
                {
                alt53=2;
                }
                break;
            case LPAREN:
            case Identifier:
            case QuotedIdentifier:
                {
                alt53=3;
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:294:5: literal
                    {
                    dbg.location(294,5);
                    pushFollow(FOLLOW_literal_in_value1479);
                    literal();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:295:5: caseExpression
                    {
                    dbg.location(295,5);
                    pushFollow(FOLLOW_caseExpression_in_value1486);
                    caseExpression();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:296:5: ( unary )? ( columnRef | nestedExpression )
                    {
                    dbg.location(296,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:296:5: ( unary )?
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

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:296:7: unary
                            {
                            dbg.location(296,7);
                            pushFollow(FOLLOW_unary_in_value1494);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(51);}

                    dbg.location(297,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:297:5: ( columnRef | nestedExpression )
                    int alt52=2;
                    try { dbg.enterSubRule(52);
                    try { dbg.enterDecision(52);

                    int LA52_0 = input.LA(1);

                    if ( (LA52_0==Identifier||LA52_0==QuotedIdentifier) ) {
                        alt52=1;
                    }
                    else if ( (LA52_0==LPAREN) ) {
                        alt52=2;
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

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:297:7: columnRef
                            {
                            dbg.location(297,7);
                            pushFollow(FOLLOW_columnRef_in_value1505);
                            columnRef();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            dbg.enterAlt(2);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:298:7: nestedExpression
                            {
                            dbg.location(298,7);
                            pushFollow(FOLLOW_nestedExpression_in_value1513);
                            nestedExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(52);}


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
        dbg.location(301, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:303:1: literal : ( ( unary )? Float | ( unary )? Integer | String | TRUE | FALSE | date );
    public final void literal() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "literal");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(303, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:304:3: ( ( unary )? Float | ( unary )? Integer | String | TRUE | FALSE | date )
            int alt56=6;
            try { dbg.enterDecision(56);

            switch ( input.LA(1) ) {
            case PLUS:
            case MINUS:
                {
                int LA56_1 = input.LA(2);

                if ( (LA56_1==Integer) ) {
                    alt56=2;
                }
                else if ( (LA56_1==Float) ) {
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
                break;
            case Float:
                {
                alt56=1;
                }
                break;
            case Integer:
                {
                alt56=2;
                }
                break;
            case String:
                {
                alt56=3;
                }
                break;
            case TRUE:
                {
                alt56=4;
                }
                break;
            case FALSE:
                {
                alt56=5;
                }
                break;
            case 114:
            case 115:
            case 116:
                {
                alt56=6;
                }
                break;
            default:
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:304:5: ( unary )? Float
                    {
                    dbg.location(304,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:304:5: ( unary )?
                    int alt54=2;
                    try { dbg.enterSubRule(54);
                    try { dbg.enterDecision(54);

                    int LA54_0 = input.LA(1);

                    if ( ((LA54_0>=PLUS && LA54_0<=MINUS)) ) {
                        alt54=1;
                    }
                    } finally {dbg.exitDecision(54);}

                    switch (alt54) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:304:7: unary
                            {
                            dbg.location(304,7);
                            pushFollow(FOLLOW_unary_in_literal1537);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(54);}

                    dbg.location(304,16);
                    match(input,Float,FOLLOW_Float_in_literal1542); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:305:5: ( unary )? Integer
                    {
                    dbg.location(305,5);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:305:5: ( unary )?
                    int alt55=2;
                    try { dbg.enterSubRule(55);
                    try { dbg.enterDecision(55);

                    int LA55_0 = input.LA(1);

                    if ( ((LA55_0>=PLUS && LA55_0<=MINUS)) ) {
                        alt55=1;
                    }
                    } finally {dbg.exitDecision(55);}

                    switch (alt55) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:305:7: unary
                            {
                            dbg.location(305,7);
                            pushFollow(FOLLOW_unary_in_literal1550);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(55);}

                    dbg.location(305,16);
                    match(input,Integer,FOLLOW_Integer_in_literal1555); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:306:5: String
                    {
                    dbg.location(306,5);
                    match(input,String,FOLLOW_String_in_literal1561); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:307:5: TRUE
                    {
                    dbg.location(307,5);
                    match(input,TRUE,FOLLOW_TRUE_in_literal1567); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:308:5: FALSE
                    {
                    dbg.location(308,5);
                    match(input,FALSE,FOLLOW_FALSE_in_literal1573); if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:309:5: date
                    {
                    dbg.location(309,5);
                    pushFollow(FOLLOW_date_in_literal1579);
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
        dbg.location(310, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:312:1: date : ( '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' );
    public final void date() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "date");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(312, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:313:3: ( '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' )
            int alt57=3;
            try { dbg.enterDecision(57);

            switch ( input.LA(1) ) {
            case 114:
                {
                alt57=1;
                }
                break;
            case 115:
                {
                alt57=2;
                }
                break;
            case 116:
                {
                alt57=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(57);}

            switch (alt57) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:313:5: '{d' Timestamp '}'
                    {
                    dbg.location(313,5);
                    match(input,114,FOLLOW_114_in_date1592); if (state.failed) return ;
                    dbg.location(313,10);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1594); if (state.failed) return ;
                    dbg.location(313,20);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1596); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:314:5: '{t' Timestamp '}'
                    {
                    dbg.location(314,5);
                    match(input,115,FOLLOW_115_in_date1603); if (state.failed) return ;
                    dbg.location(314,10);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1605); if (state.failed) return ;
                    dbg.location(314,20);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1607); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:315:5: '{ts' Timestamp '}'
                    {
                    dbg.location(315,5);
                    match(input,116,FOLLOW_116_in_date1614); if (state.failed) return ;
                    dbg.location(315,11);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1616); if (state.failed) return ;
                    dbg.location(315,21);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1618); if (state.failed) return ;

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
        dbg.location(316, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:318:1: unary : ( MINUS | PLUS );
    public final void unary() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "unary");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(318, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:319:3: ( MINUS | PLUS )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(319,3);
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
        dbg.location(321, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "unary");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "unary"


    // $ANTLR start "caseExpression"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:323:1: caseExpression : ( CASE ( WHEN condition THEN value )+ ( ELSE value )? END | CASE value ( WHEN value THEN value )+ ( ELSE value )? END );
    public final void caseExpression() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "caseExpression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(323, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:324:3: ( CASE ( WHEN condition THEN value )+ ( ELSE value )? END | CASE value ( WHEN value THEN value )+ ( ELSE value )? END )
            int alt62=2;
            try { dbg.enterDecision(62);

            int LA62_0 = input.LA(1);

            if ( (LA62_0==CASE) ) {
                int LA62_1 = input.LA(2);

                if ( (LA62_1==WHEN) ) {
                    alt62=1;
                }
                else if ( (LA62_1==LPAREN||LA62_1==Integer||LA62_1==Identifier||(LA62_1>=PLUS && LA62_1<=MINUS)||(LA62_1>=Float && LA62_1<=FALSE)||LA62_1==CASE||LA62_1==QuotedIdentifier||(LA62_1>=114 && LA62_1<=116)) ) {
                    alt62=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 62, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(62);}

            switch (alt62) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:324:5: CASE ( WHEN condition THEN value )+ ( ELSE value )? END
                    {
                    dbg.location(324,5);
                    match(input,CASE,FOLLOW_CASE_in_caseExpression1656); if (state.failed) return ;
                    dbg.location(324,10);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:324:10: ( WHEN condition THEN value )+
                    int cnt58=0;
                    try { dbg.enterSubRule(58);

                    loop58:
                    do {
                        int alt58=2;
                        try { dbg.enterDecision(58);

                        int LA58_0 = input.LA(1);

                        if ( (LA58_0==WHEN) ) {
                            alt58=1;
                        }


                        } finally {dbg.exitDecision(58);}

                        switch (alt58) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:324:12: WHEN condition THEN value
                    	    {
                    	    dbg.location(324,12);
                    	    match(input,WHEN,FOLLOW_WHEN_in_caseExpression1660); if (state.failed) return ;
                    	    dbg.location(324,17);
                    	    pushFollow(FOLLOW_condition_in_caseExpression1662);
                    	    condition();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    dbg.location(324,27);
                    	    match(input,THEN,FOLLOW_THEN_in_caseExpression1664); if (state.failed) return ;
                    	    dbg.location(324,32);
                    	    pushFollow(FOLLOW_value_in_caseExpression1666);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt58 >= 1 ) break loop58;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(58, input);
                                dbg.recognitionException(eee);

                                throw eee;
                        }
                        cnt58++;
                    } while (true);
                    } finally {dbg.exitSubRule(58);}

                    dbg.location(324,41);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:324:41: ( ELSE value )?
                    int alt59=2;
                    try { dbg.enterSubRule(59);
                    try { dbg.enterDecision(59);

                    int LA59_0 = input.LA(1);

                    if ( (LA59_0==ELSE) ) {
                        alt59=1;
                    }
                    } finally {dbg.exitDecision(59);}

                    switch (alt59) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:324:43: ELSE value
                            {
                            dbg.location(324,43);
                            match(input,ELSE,FOLLOW_ELSE_in_caseExpression1673); if (state.failed) return ;
                            dbg.location(324,48);
                            pushFollow(FOLLOW_value_in_caseExpression1675);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(59);}

                    dbg.location(324,57);
                    match(input,END,FOLLOW_END_in_caseExpression1680); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:325:5: CASE value ( WHEN value THEN value )+ ( ELSE value )? END
                    {
                    dbg.location(325,5);
                    match(input,CASE,FOLLOW_CASE_in_caseExpression1686); if (state.failed) return ;
                    dbg.location(325,10);
                    pushFollow(FOLLOW_value_in_caseExpression1688);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(325,16);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:325:16: ( WHEN value THEN value )+
                    int cnt60=0;
                    try { dbg.enterSubRule(60);

                    loop60:
                    do {
                        int alt60=2;
                        try { dbg.enterDecision(60);

                        int LA60_0 = input.LA(1);

                        if ( (LA60_0==WHEN) ) {
                            alt60=1;
                        }


                        } finally {dbg.exitDecision(60);}

                        switch (alt60) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:325:18: WHEN value THEN value
                    	    {
                    	    dbg.location(325,18);
                    	    match(input,WHEN,FOLLOW_WHEN_in_caseExpression1692); if (state.failed) return ;
                    	    dbg.location(325,23);
                    	    pushFollow(FOLLOW_value_in_caseExpression1694);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;
                    	    dbg.location(325,29);
                    	    match(input,THEN,FOLLOW_THEN_in_caseExpression1696); if (state.failed) return ;
                    	    dbg.location(325,34);
                    	    pushFollow(FOLLOW_value_in_caseExpression1698);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt60 >= 1 ) break loop60;
                    	    if (state.backtracking>0) {state.failed=true; return ;}
                                EarlyExitException eee =
                                    new EarlyExitException(60, input);
                                dbg.recognitionException(eee);

                                throw eee;
                        }
                        cnt60++;
                    } while (true);
                    } finally {dbg.exitSubRule(60);}

                    dbg.location(325,43);
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:325:43: ( ELSE value )?
                    int alt61=2;
                    try { dbg.enterSubRule(61);
                    try { dbg.enterDecision(61);

                    int LA61_0 = input.LA(1);

                    if ( (LA61_0==ELSE) ) {
                        alt61=1;
                    }
                    } finally {dbg.exitDecision(61);}

                    switch (alt61) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:325:45: ELSE value
                            {
                            dbg.location(325,45);
                            match(input,ELSE,FOLLOW_ELSE_in_caseExpression1705); if (state.failed) return ;
                            dbg.location(325,50);
                            pushFollow(FOLLOW_value_in_caseExpression1707);
                            value();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(61);}

                    dbg.location(325,59);
                    match(input,END,FOLLOW_END_in_caseExpression1712); if (state.failed) return ;

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
        dbg.location(326, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "caseExpression");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "caseExpression"


    // $ANTLR start "tableRef"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:328:1: tableRef : ( tableName | databaseName DOT tableName );
    public final void tableRef() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableRef");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(328, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:329:3: ( tableName | databaseName DOT tableName )
            int alt63=2;
            try { dbg.enterDecision(63);

            int LA63_0 = input.LA(1);

            if ( (LA63_0==Identifier||LA63_0==QuotedIdentifier) ) {
                int LA63_1 = input.LA(2);

                if ( (LA63_1==DOT) ) {
                    alt63=2;
                }
                else if ( (LA63_1==EOF||(LA63_1>=SEMI && LA63_1<=RPAREN)||(LA63_1>=SET && LA63_1<=COMMA)||LA63_1==VALUES||LA63_1==AS||(LA63_1>=Identifier && LA63_1<=NATURAL)||(LA63_1>=WHERE && LA63_1<=GROUP)||(LA63_1>=HAVING && LA63_1<=ORDER)) ) {
                    alt63=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 63, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(63);}

            switch (alt63) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:329:5: tableName
                    {
                    dbg.location(329,5);
                    pushFollow(FOLLOW_tableName_in_tableRef1725);
                    tableName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:330:5: databaseName DOT tableName
                    {
                    dbg.location(330,5);
                    pushFollow(FOLLOW_databaseName_in_tableRef1731);
                    databaseName();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(330,18);
                    match(input,DOT,FOLLOW_DOT_in_tableRef1733); if (state.failed) return ;
                    dbg.location(330,22);
                    pushFollow(FOLLOW_tableName_in_tableRef1735);
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
        dbg.location(331, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:333:1: columnRef : ( columnName | tableAlias DOT columnName );
    public final void columnRef() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnRef");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(333, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:334:3: ( columnName | tableAlias DOT columnName )
            int alt64=2;
            try { dbg.enterDecision(64);

            int LA64_0 = input.LA(1);

            if ( (LA64_0==Identifier) ) {
                int LA64_1 = input.LA(2);

                if ( (LA64_1==DOT) ) {
                    alt64=2;
                }
                else if ( (LA64_1==EOF||LA64_1==SEMI||LA64_1==RPAREN||LA64_1==ALL||(LA64_1>=COMMA && LA64_1<=INTO)||(LA64_1>=STAR && LA64_1<=AS)||(LA64_1>=Identifier && LA64_1<=NATURAL)||(LA64_1>=WHERE && LA64_1<=GROUP)||(LA64_1>=HAVING && LA64_1<=IS)||(LA64_1>=LIKE && LA64_1<=DIVIDE)||(LA64_1>=WHEN && LA64_1<=END)) ) {
                    alt64=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 64, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else if ( (LA64_0==QuotedIdentifier) ) {
                alt64=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(64);}

            switch (alt64) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:334:5: columnName
                    {
                    dbg.location(334,5);
                    pushFollow(FOLLOW_columnName_in_columnRef1750);
                    columnName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:335:5: tableAlias DOT columnName
                    {
                    dbg.location(335,5);
                    pushFollow(FOLLOW_tableAlias_in_columnRef1757);
                    tableAlias();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(335,16);
                    match(input,DOT,FOLLOW_DOT_in_columnRef1759); if (state.failed) return ;
                    dbg.location(335,20);
                    pushFollow(FOLLOW_columnName_in_columnRef1761);
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
        dbg.location(336, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:338:1: databaseName : ( Identifier | QuotedIdentifier );
    public final void databaseName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "databaseName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(338, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:339:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(339,3);
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
        dbg.location(341, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:343:1: tableName : ( Identifier | QuotedIdentifier );
    public final void tableName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(343, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:344:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(344,3);
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
        dbg.location(346, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:348:1: tableAlias : Identifier ;
    public final void tableAlias() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableAlias");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(348, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:349:3: ( Identifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:349:5: Identifier
            {
            dbg.location(349,5);
            match(input,Identifier,FOLLOW_Identifier_in_tableAlias1816); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(350, 3);

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
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:352:1: columnName : ( Identifier | QuotedIdentifier );
    public final void columnName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(352, 1);

        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:353:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(353,3);
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
        dbg.location(355, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "columnName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "columnName"

    // $ANTLR start synpred13_GenericSQL
    public final void synpred13_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:7: ( joinList )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:7: joinList
        {
        dbg.location(72,7);
        pushFollow(FOLLOW_joinList_in_synpred13_GenericSQL217);
        joinList();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_GenericSQL

    // $ANTLR start synpred28_GenericSQL
    public final void synpred28_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:5: ( function ( ( AS )? alias )? )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:5: function ( ( AS )? alias )?
        {
        dbg.location(113,5);
        pushFollow(FOLLOW_function_in_synpred28_GenericSQL486);
        function();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(113,14);
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:14: ( ( AS )? alias )?
        int alt70=2;
        try { dbg.enterSubRule(70);
        try { dbg.enterDecision(70);

        int LA70_0 = input.LA(1);

        if ( (LA70_0==AS||LA70_0==Identifier) ) {
            alt70=1;
        }
        } finally {dbg.exitDecision(70);}

        switch (alt70) {
            case 1 :
                dbg.enterAlt(1);

                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:16: ( AS )? alias
                {
                dbg.location(113,16);
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:113:16: ( AS )?
                int alt69=2;
                try { dbg.enterSubRule(69);
                try { dbg.enterDecision(69);

                int LA69_0 = input.LA(1);

                if ( (LA69_0==AS) ) {
                    alt69=1;
                }
                } finally {dbg.exitDecision(69);}

                switch (alt69) {
                    case 1 :
                        dbg.enterAlt(1);

                        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:0:0: AS
                        {
                        dbg.location(113,16);
                        match(input,AS,FOLLOW_AS_in_synpred28_GenericSQL490); if (state.failed) return ;

                        }
                        break;

                }
                } finally {dbg.exitSubRule(69);}

                dbg.location(113,20);
                pushFollow(FOLLOW_alias_in_synpred28_GenericSQL493);
                alias();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }
        } finally {dbg.exitSubRule(70);}


        }
    }
    // $ANTLR end synpred28_GenericSQL

    // $ANTLR start synpred31_GenericSQL
    public final void synpred31_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:5: ( expression ( ( AS )? alias )? )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:5: expression ( ( AS )? alias )?
        {
        dbg.location(114,5);
        pushFollow(FOLLOW_expression_in_synpred31_GenericSQL503);
        expression();

        state._fsp--;
        if (state.failed) return ;
        dbg.location(114,16);
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:16: ( ( AS )? alias )?
        int alt73=2;
        try { dbg.enterSubRule(73);
        try { dbg.enterDecision(73);

        int LA73_0 = input.LA(1);

        if ( (LA73_0==AS||LA73_0==Identifier) ) {
            alt73=1;
        }
        } finally {dbg.exitDecision(73);}

        switch (alt73) {
            case 1 :
                dbg.enterAlt(1);

                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:18: ( AS )? alias
                {
                dbg.location(114,18);
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:18: ( AS )?
                int alt72=2;
                try { dbg.enterSubRule(72);
                try { dbg.enterDecision(72);

                int LA72_0 = input.LA(1);

                if ( (LA72_0==AS) ) {
                    alt72=1;
                }
                } finally {dbg.exitDecision(72);}

                switch (alt72) {
                    case 1 :
                        dbg.enterAlt(1);

                        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:20: AS
                        {
                        dbg.location(114,20);
                        match(input,AS,FOLLOW_AS_in_synpred31_GenericSQL509); if (state.failed) return ;

                        }
                        break;

                }
                } finally {dbg.exitSubRule(72);}

                dbg.location(114,26);
                pushFollow(FOLLOW_alias_in_synpred31_GenericSQL514);
                alias();

                state._fsp--;
                if (state.failed) return ;

                }
                break;

        }
        } finally {dbg.exitSubRule(73);}


        }
    }
    // $ANTLR end synpred31_GenericSQL

    // $ANTLR start synpred32_GenericSQL
    public final void synpred32_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:5: ( allColumns )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:5: allColumns
        {
        dbg.location(115,5);
        pushFollow(FOLLOW_allColumns_in_synpred32_GenericSQL523);
        allColumns();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred32_GenericSQL

    // $ANTLR start synpred55_GenericSQL
    public final void synpred55_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:210:7: ( comparison )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:210:7: comparison
        {
        dbg.location(210,7);
        pushFollow(FOLLOW_comparison_in_synpred55_GenericSQL1036);
        comparison();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred55_GenericSQL

    // $ANTLR start synpred56_GenericSQL
    public final void synpred56_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:211:7: ( nestedCondition )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:211:7: nestedCondition
        {
        dbg.location(211,7);
        pushFollow(FOLLOW_nestedCondition_in_synpred56_GenericSQL1044);
        nestedCondition();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred56_GenericSQL

    // $ANTLR start synpred57_GenericSQL
    public final void synpred57_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:212:7: ( in )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:212:7: in
        {
        dbg.location(212,7);
        pushFollow(FOLLOW_in_in_synpred57_GenericSQL1052);
        in();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred57_GenericSQL

    // $ANTLR start synpred58_GenericSQL
    public final void synpred58_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:213:7: ( between )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:213:7: between
        {
        dbg.location(213,7);
        pushFollow(FOLLOW_between_in_synpred58_GenericSQL1060);
        between();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred58_GenericSQL

    // $ANTLR start synpred59_GenericSQL
    public final void synpred59_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:214:7: ( isNull )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:214:7: isNull
        {
        dbg.location(214,7);
        pushFollow(FOLLOW_isNull_in_synpred59_GenericSQL1068);
        isNull();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred59_GenericSQL

    // $ANTLR start synpred61_GenericSQL
    public final void synpred61_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:216:7: ( like )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:216:7: like
        {
        dbg.location(216,7);
        pushFollow(FOLLOW_like_in_synpred61_GenericSQL1084);
        like();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred61_GenericSQL

    // Delegated rules

    public final boolean synpred32_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred32_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred31_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred31_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred13_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred13_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred58_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred58_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred61_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred61_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred56_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred56_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred59_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred59_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred28_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred28_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred57_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred57_GenericSQL_fragment(); // can never throw exception
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
    public final boolean synpred55_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred55_GenericSQL_fragment(); // can never throw exception
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


    protected DFA10 dfa10 = new DFA10(this);
    protected DFA29 dfa29 = new DFA29(this);
    protected DFA37 dfa37 = new DFA37(this);
    protected DFA42 dfa42 = new DFA42(this);
    static final String DFA10_eotS =
        "\12\uffff";
    static final String DFA10_eofS =
        "\1\7\11\uffff";
    static final String DFA10_minS =
        "\1\4\1\uffff\7\0\1\uffff";
    static final String DFA10_maxS =
        "\1\45\1\uffff\7\0\1\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\1\7\uffff\1\2";
    static final String DFA10_specialS =
        "\2\uffff\1\3\1\4\1\6\1\0\1\1\1\2\1\5\1\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\6\1\uffff\1\10\23\uffff\6\1\1\uffff\1\2\1\3\1\uffff\1\4\1"+
            "\5",
            "",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            "\1\uffff",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "72:5: ( joinList )?";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA10_5 = input.LA(1);

                         
                        int index10_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_5);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA10_6 = input.LA(1);

                         
                        int index10_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_6);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA10_7 = input.LA(1);

                         
                        int index10_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_7);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA10_2 = input.LA(1);

                         
                        int index10_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_2);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA10_3 = input.LA(1);

                         
                        int index10_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_3);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA10_8 = input.LA(1);

                         
                        int index10_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_8);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA10_4 = input.LA(1);

                         
                        int index10_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_4);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 10, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA29_eotS =
        "\21\uffff";
    static final String DFA29_eofS =
        "\21\uffff";
    static final String DFA29_minS =
        "\1\5\1\0\11\uffff\1\0\5\uffff";
    static final String DFA29_maxS =
        "\1\164\1\0\11\uffff\1\0\5\uffff";
    static final String DFA29_acceptS =
        "\2\uffff\1\2\13\uffff\1\1\1\3\1\4";
    static final String DFA29_specialS =
        "\1\uffff\1\0\11\uffff\1\1\5\uffff}>";
    static final String[] DFA29_transitionS = {
            "\1\2\6\uffff\1\2\13\uffff\1\1\40\uffff\2\2\1\uffff\4\2\1\uffff"+
            "\1\13\4\uffff\1\2\53\uffff\3\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\uffff",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA29_eot = DFA.unpackEncodedString(DFA29_eotS);
    static final short[] DFA29_eof = DFA.unpackEncodedString(DFA29_eofS);
    static final char[] DFA29_min = DFA.unpackEncodedStringToUnsignedChars(DFA29_minS);
    static final char[] DFA29_max = DFA.unpackEncodedStringToUnsignedChars(DFA29_maxS);
    static final short[] DFA29_accept = DFA.unpackEncodedString(DFA29_acceptS);
    static final short[] DFA29_special = DFA.unpackEncodedString(DFA29_specialS);
    static final short[][] DFA29_transition;

    static {
        int numStates = DFA29_transitionS.length;
        DFA29_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA29_transition[i] = DFA.unpackEncodedString(DFA29_transitionS[i]);
        }
    }

    class DFA29 extends DFA {

        public DFA29(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 29;
            this.eot = DFA29_eot;
            this.eof = DFA29_eof;
            this.min = DFA29_min;
            this.max = DFA29_max;
            this.accept = DFA29_accept;
            this.special = DFA29_special;
            this.transition = DFA29_transition;
        }
        public String getDescription() {
            return "112:1: item : ( function ( ( AS )? alias )? | expression ( ( AS )? alias )? | allColumns | caseExpression ( ( AS )? alias )? );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA29_1 = input.LA(1);

                         
                        int index29_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred28_GenericSQL()) ) {s = 14;}

                        else if ( (synpred31_GenericSQL()) ) {s = 2;}

                        else if ( (synpred32_GenericSQL()) ) {s = 15;}

                         
                        input.seek(index29_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA29_11 = input.LA(1);

                         
                        int index29_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred31_GenericSQL()) ) {s = 2;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index29_11);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 29, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA37_eotS =
        "\13\uffff";
    static final String DFA37_eofS =
        "\13\uffff";
    static final String DFA37_minS =
        "\1\32\2\uffff\2\32\6\uffff";
    static final String DFA37_maxS =
        "\1\37\2\uffff\2\35\6\uffff";
    static final String DFA37_acceptS =
        "\1\uffff\1\1\1\2\2\uffff\1\7\1\10\1\3\1\4\1\5\1\6";
    static final String DFA37_specialS =
        "\13\uffff}>";
    static final String[] DFA37_transitionS = {
            "\1\1\1\2\1\3\1\5\1\4\1\6",
            "",
            "",
            "\1\7\2\uffff\1\10",
            "\1\11\2\uffff\1\12",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA37_eot = DFA.unpackEncodedString(DFA37_eotS);
    static final short[] DFA37_eof = DFA.unpackEncodedString(DFA37_eofS);
    static final char[] DFA37_min = DFA.unpackEncodedStringToUnsignedChars(DFA37_minS);
    static final char[] DFA37_max = DFA.unpackEncodedStringToUnsignedChars(DFA37_maxS);
    static final short[] DFA37_accept = DFA.unpackEncodedString(DFA37_acceptS);
    static final short[] DFA37_special = DFA.unpackEncodedString(DFA37_specialS);
    static final short[][] DFA37_transition;

    static {
        int numStates = DFA37_transitionS.length;
        DFA37_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA37_transition[i] = DFA.unpackEncodedString(DFA37_transitionS[i]);
        }
    }

    class DFA37 extends DFA {

        public DFA37(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 37;
            this.eot = DFA37_eot;
            this.eof = DFA37_eof;
            this.min = DFA37_min;
            this.max = DFA37_max;
            this.accept = DFA37_accept;
            this.special = DFA37_special;
            this.transition = DFA37_transition;
        }
        public String getDescription() {
            return "155:5: ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN )";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
    static final String DFA42_eotS =
        "\26\uffff";
    static final String DFA42_eofS =
        "\26\uffff";
    static final String DFA42_minS =
        "\1\5\15\0\10\uffff";
    static final String DFA42_maxS =
        "\1\164\15\0\10\uffff";
    static final String DFA42_acceptS =
        "\16\uffff\1\6\1\1\1\3\1\4\1\5\1\7\1\10\1\2";
    static final String DFA42_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
        "\10\uffff}>";
    static final String[] DFA42_transitionS = {
            "\1\15\6\uffff\1\3\13\uffff\1\13\26\uffff\1\16\11\uffff\2\1\1"+
            "\uffff\1\2\1\4\1\5\1\6\1\uffff\1\12\4\uffff\1\14\53\uffff\1"+
            "\7\1\10\1\11",
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

    static final short[] DFA42_eot = DFA.unpackEncodedString(DFA42_eotS);
    static final short[] DFA42_eof = DFA.unpackEncodedString(DFA42_eofS);
    static final char[] DFA42_min = DFA.unpackEncodedStringToUnsignedChars(DFA42_minS);
    static final char[] DFA42_max = DFA.unpackEncodedStringToUnsignedChars(DFA42_maxS);
    static final short[] DFA42_accept = DFA.unpackEncodedString(DFA42_acceptS);
    static final short[] DFA42_special = DFA.unpackEncodedString(DFA42_specialS);
    static final short[][] DFA42_transition;

    static {
        int numStates = DFA42_transitionS.length;
        DFA42_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA42_transition[i] = DFA.unpackEncodedString(DFA42_transitionS[i]);
        }
    }

    class DFA42 extends DFA {

        public DFA42(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 42;
            this.eot = DFA42_eot;
            this.eof = DFA42_eof;
            this.min = DFA42_min;
            this.max = DFA42_max;
            this.accept = DFA42_accept;
            this.special = DFA42_special;
            this.transition = DFA42_transition;
        }
        public String getDescription() {
            return "207:1: condition : ( comparison | nestedCondition | in | between | isNull | exists | like | quantifier );";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA42_1 = input.LA(1);

                         
                        int index42_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA42_2 = input.LA(1);

                         
                        int index42_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA42_3 = input.LA(1);

                         
                        int index42_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA42_4 = input.LA(1);

                         
                        int index42_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA42_5 = input.LA(1);

                         
                        int index42_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA42_6 = input.LA(1);

                         
                        int index42_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA42_7 = input.LA(1);

                         
                        int index42_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA42_8 = input.LA(1);

                         
                        int index42_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA42_9 = input.LA(1);

                         
                        int index42_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA42_10 = input.LA(1);

                         
                        int index42_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA42_11 = input.LA(1);

                         
                        int index42_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA42_12 = input.LA(1);

                         
                        int index42_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_12);
                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA42_13 = input.LA(1);

                         
                        int index42_13 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 15;}

                        else if ( (synpred56_GenericSQL()) ) {s = 21;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred59_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index42_13);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 42, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_select_in_statement67 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_statement71 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_statement76 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_insert_in_statement82 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_statement86 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_statement91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_update_in_statement97 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_statement101 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_statement106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_subSelect122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_subSelect128 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_select_in_subSelect130 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_subSelect132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_in_select147 = new BitSet(new long[]{0xF600000001201F20L,0x001C000000000042L});
    public static final BitSet FOLLOW_set_in_select153 = new BitSet(new long[]{0xF600000001201F20L,0x001C000000000042L});
    public static final BitSet FOLLOW_TOP_in_select174 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Integer_in_select176 = new BitSet(new long[]{0xF600000001203F20L,0x001C000000000042L});
    public static final BitSet FOLLOW_PERCENT_in_select180 = new BitSet(new long[]{0xF600000001201F20L,0x001C000000000042L});
    public static final BitSet FOLLOW_itemList_in_select192 = new BitSet(new long[]{0x0000000002080000L});
    public static final BitSet FOLLOW_into_in_select200 = new BitSet(new long[]{0x0000000002080000L});
    public static final BitSet FOLLOW_from_in_select209 = new BitSet(new long[]{0x00000036FC000000L});
    public static final BitSet FOLLOW_joinList_in_select217 = new BitSet(new long[]{0x0000003600000002L});
    public static final BitSet FOLLOW_where_in_select228 = new BitSet(new long[]{0x0000003400000002L});
    public static final BitSet FOLLOW_groupBy_in_select239 = new BitSet(new long[]{0x0000003000000002L});
    public static final BitSet FOLLOW_having_in_select250 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_orderBy_in_select261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INSERT_in_insert279 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_into_in_insert281 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_columnList_in_insert285 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_values_in_insert294 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPDATE_in_update314 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tableRef_in_update316 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_SET_in_update318 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_setter_in_update320 = new BitSet(new long[]{0x0000000200020002L});
    public static final BitSet FOLLOW_COMMA_in_update324 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_setter_in_update326 = new BitSet(new long[]{0x0000000200020002L});
    public static final BitSet FOLLOW_where_in_update337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_columnName_in_setter355 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQ_in_setter357 = new BitSet(new long[]{0xF600000000001000L,0x001C000000000000L});
    public static final BitSet FOLLOW_literal_in_setter359 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTO_in_into374 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tableRef_in_into376 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_into380 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tableRef_in_into382 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_LPAREN_in_columnList398 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_columnName_in_columnList400 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_COMMA_in_columnList404 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_columnName_in_columnList406 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_RPAREN_in_columnList411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUES_in_values426 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_values428 = new BitSet(new long[]{0xF600000000001000L,0x001C000000000000L});
    public static final BitSet FOLLOW_literal_in_values430 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_COMMA_in_values434 = new BitSet(new long[]{0xF600000000001000L,0x001C000000000000L});
    public static final BitSet FOLLOW_literal_in_values436 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_RPAREN_in_values441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_itemList456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_item_in_itemList462 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_itemList466 = new BitSet(new long[]{0xF600000001201F20L,0x001C000000000042L});
    public static final BitSet FOLLOW_item_in_itemList468 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_function_in_item486 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_item490 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_item493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_item503 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_item509 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_item514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_allColumns_in_item523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_caseExpression_in_item529 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_item535 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_item540 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_allColumns556 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_DOT_in_allColumns558 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_STAR_in_allColumns560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_alias573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_function588 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_function590 = new BitSet(new long[]{0xF600000001001060L,0x001C000000000042L});
    public static final BitSet FOLLOW_expression_in_function594 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_COMMA_in_function598 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_expression_in_function600 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_RPAREN_in_function608 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_in_from632 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_fromItem_in_from634 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_from638 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_fromItem_in_from640 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_LPAREN_in_fromItem662 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_fromItem664 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_fromItem666 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_tableRef_in_fromItem677 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_fromItem694 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_fromItem699 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_join_in_joinList717 = new BitSet(new long[]{0x00000000FC000002L});
    public static final BitSet FOLLOW_JOIN_in_join742 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_INNER_in_join750 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_JOIN_in_join752 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_LEFT_in_join760 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_JOIN_in_join762 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_LEFT_in_join770 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_OUTER_in_join772 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_JOIN_in_join774 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_RIGHT_in_join782 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_JOIN_in_join784 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_RIGHT_in_join793 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_OUTER_in_join795 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_JOIN_in_join797 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_OUTER_in_join805 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_JOIN_in_join807 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_NATURAL_in_join816 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_JOIN_in_join818 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000040L});
    public static final BitSet FOLLOW_fromItem_in_join829 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_join831 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_ON_in_join839 = new BitSet(new long[]{0xF600800001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_conditionList_in_join841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHERE_in_where862 = new BitSet(new long[]{0xF600800001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_conditionList_in_where864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GROUP_in_groupBy879 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_BY_in_groupBy881 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_columnRef_in_groupBy883 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_groupBy887 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_columnRef_in_groupBy889 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_HAVING_in_having907 = new BitSet(new long[]{0xF600800001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_conditionList_in_having909 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORDER_in_orderBy924 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_BY_in_orderBy926 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy928 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_orderBy932 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy934 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_columnRef_in_orderByItem952 = new BitSet(new long[]{0x000000C000000002L});
    public static final BitSet FOLLOW_set_in_orderByItem954 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedCondition978 = new BitSet(new long[]{0xF600800001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_conditionList_in_nestedCondition980 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedCondition982 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditionList997 = new BitSet(new long[]{0x0000030000000002L});
    public static final BitSet FOLLOW_set_in_conditionList1001 = new BitSet(new long[]{0xF600800001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_condition_in_conditionList1011 = new BitSet(new long[]{0x0000030000000002L});
    public static final BitSet FOLLOW_comparison_in_condition1036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedCondition_in_condition1044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_condition1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_condition1060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_condition1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exists_in_condition1076 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_condition1084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_condition1092 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_in1106 = new BitSet(new long[]{0x00000C0000000000L});
    public static final BitSet FOLLOW_NOT_in_in1110 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_IN_in_in1115 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_in1117 = new BitSet(new long[]{0xF6000000010010A0L,0x001C000000000042L});
    public static final BitSet FOLLOW_subSelect_in_in1121 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_expressionList_in_in1125 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_in1129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_between1144 = new BitSet(new long[]{0x0000140000000000L});
    public static final BitSet FOLLOW_NOT_in_between1148 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_BETWEEN_in_between1153 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_expression_in_between1155 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_AND_in_between1157 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_expression_in_between1159 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_isNull1174 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_IS_in_isNull1176 = new BitSet(new long[]{0x0000440000000000L});
    public static final BitSet FOLLOW_NOT_in_isNull1180 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_NULL_in_isNull1185 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXISTS_in_exists1200 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_expression_in_exists1202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_like1221 = new BitSet(new long[]{0x0001040000000000L});
    public static final BitSet FOLLOW_NOT_in_like1225 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_LIKE_in_like1230 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_expression_in_like1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_comparison1250 = new BitSet(new long[]{0x007E000000040000L});
    public static final BitSet FOLLOW_comparator_in_comparison1252 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_expression_in_comparison1254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_comparator0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_quantifier1319 = new BitSet(new long[]{0x0180000000000100L});
    public static final BitSet FOLLOW_set_in_quantifier1321 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_quantifier1335 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_quantifier1337 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_quantifier1339 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList1354 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_expressionList1358 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_expression_in_expressionList1360 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedExpression1376 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_expression_in_nestedExpression1378 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedExpression1380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiply_in_expression1395 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_set_in_expression1399 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_multiply_in_expression1409 = new BitSet(new long[]{0x0600000000000002L});
    public static final BitSet FOLLOW_value_in_multiply1426 = new BitSet(new long[]{0x0800000000200002L});
    public static final BitSet FOLLOW_set_in_multiply1430 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_value_in_multiply1440 = new BitSet(new long[]{0x0800000000200002L});
    public static final BitSet FOLLOW_literal_in_value1479 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_caseExpression_in_value1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_value1494 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_columnRef_in_value1505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedExpression_in_value1513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_literal1537 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_Float_in_literal1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_literal1550 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Integer_in_literal1555 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_literal1561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literal1567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literal1573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_in_literal1579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_date1592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_Timestamp_in_date1594 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1596 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_115_in_date1603 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_Timestamp_in_date1605 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_date1614 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_Timestamp_in_date1616 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1618 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_unary0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASE_in_caseExpression1656 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_WHEN_in_caseExpression1660 = new BitSet(new long[]{0xF600800001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_condition_in_caseExpression1662 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_THEN_in_caseExpression1664 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_value_in_caseExpression1666 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000034L});
    public static final BitSet FOLLOW_ELSE_in_caseExpression1673 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_value_in_caseExpression1675 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_END_in_caseExpression1680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CASE_in_caseExpression1686 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_value_in_caseExpression1688 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000004L});
    public static final BitSet FOLLOW_WHEN_in_caseExpression1692 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_value_in_caseExpression1694 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_THEN_in_caseExpression1696 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_value_in_caseExpression1698 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000034L});
    public static final BitSet FOLLOW_ELSE_in_caseExpression1705 = new BitSet(new long[]{0xF600000001001020L,0x001C000000000042L});
    public static final BitSet FOLLOW_value_in_caseExpression1707 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_END_in_caseExpression1712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableName_in_tableRef1725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_databaseName_in_tableRef1731 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_DOT_in_tableRef1733 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_tableName_in_tableRef1735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_columnName_in_columnRef1750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_columnRef1757 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_DOT_in_columnRef1759 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_columnName_in_columnRef1761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_databaseName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_tableName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_tableAlias1816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_columnName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_joinList_in_synpred13_GenericSQL217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_synpred28_GenericSQL486 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_synpred28_GenericSQL490 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_synpred28_GenericSQL493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_synpred31_GenericSQL503 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_synpred31_GenericSQL509 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_synpred31_GenericSQL514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_allColumns_in_synpred32_GenericSQL523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_synpred55_GenericSQL1036 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedCondition_in_synpred56_GenericSQL1044 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_synpred57_GenericSQL1052 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_synpred58_GenericSQL1060 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_synpred59_GenericSQL1068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_synpred61_GenericSQL1084 = new BitSet(new long[]{0x0000000000000002L});

}