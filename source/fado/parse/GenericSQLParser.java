// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g 2012-05-11 13:32:20
 
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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SEMI", "LPAREN", "RPAREN", "SELECT", "ALL", "DISTINCT", "UNIQUE", "TOP", "Integer", "PERCENT", "INSERT", "UPDATE", "SET", "COMMA", "EQ", "INTO", "VALUES", "STAR", "AS", "DOT", "Identifier", "COUNT", "MIN", "MAX", "FROM", "JOIN", "INNER", "LEFT", "OUTER", "RIGHT", "NATURAL", "ON", "WHERE", "GROUP", "BY", "HAVING", "ORDER", "ASC", "DESC", "OR", "AND", "NOT", "IN", "BETWEEN", "IS", "NULL", "EXISTS", "LIKE", "NEQ1", "NEQ2", "LTE", "LT", "GTE", "GT", "ANY", "SOME", "PLUS", "MINUS", "DIVIDE", "Float", "String", "TRUE", "FALSE", "Timestamp", "QuotedIdentifier", "A", "L", "N", "D", "Y", "S", "C", "B", "E", "T", "W", "CASE", "DELETE", "I", "ELSE", "END", "X", "F", "R", "O", "M", "U", "FULL", "G", "P", "H", "V", "J", "K", "THEN", "UNION", "Q", "USING", "WHEN", "Z", "LCURLY", "RCURLY", "STRCAT", "QUESTION", "COLON", "MOD", "Digit", "Exponent", "Comment", "Whitespace", "'{d'", "'{t'", "'{ts'"
    };
    public static final int LT=55;
    public static final int STAR=21;
    public static final int MOD=109;
    public static final int CASE=80;
    public static final int COUNT=25;
    public static final int NOT=45;
    public static final int EOF=-1;
    public static final int Identifier=24;
    public static final int RPAREN=6;
    public static final int FULL=91;
    public static final int INSERT=14;
    public static final int USING=101;
    public static final int Comment=112;
    public static final int EQ=18;
    public static final int SELECT=7;
    public static final int INTO=19;
    public static final int DIVIDE=62;
    public static final int D=72;
    public static final int E=77;
    public static final int UNIQUE=10;
    public static final int F=86;
    public static final int G=92;
    public static final int A=69;
    public static final int B=76;
    public static final int ASC=41;
    public static final int C=75;
    public static final int L=70;
    public static final int M=89;
    public static final int N=71;
    public static final int O=88;
    public static final int H=94;
    public static final int NULL=49;
    public static final int I=82;
    public static final int J=96;
    public static final int ELSE=83;
    public static final int K=97;
    public static final int U=90;
    public static final int ON=35;
    public static final int T=78;
    public static final int W=79;
    public static final int LCURLY=104;
    public static final int V=95;
    public static final int Q=100;
    public static final int P=93;
    public static final int DELETE=81;
    public static final int S=74;
    public static final int R=87;
    public static final int Y=73;
    public static final int X=85;
    public static final int Z=103;
    public static final int Float=63;
    public static final int GROUP=37;
    public static final int OR=43;
    public static final int Timestamp=67;
    public static final int GT=57;
    public static final int FROM=28;
    public static final int END=84;
    public static final int FALSE=66;
    public static final int DISTINCT=9;
    public static final int NEQ1=52;
    public static final int WHERE=36;
    public static final int INNER=30;
    public static final int ORDER=40;
    public static final int T__116=116;
    public static final int T__114=114;
    public static final int NEQ2=53;
    public static final int GTE=56;
    public static final int T__115=115;
    public static final int MAX=27;
    public static final int UPDATE=15;
    public static final int Exponent=111;
    public static final int AND=44;
    public static final int LTE=54;
    public static final int LPAREN=5;
    public static final int AS=22;
    public static final int THEN=98;
    public static final int IN=46;
    public static final int COMMA=17;
    public static final int IS=48;
    public static final int LEFT=31;
    public static final int SOME=59;
    public static final int ALL=8;
    public static final int PLUS=60;
    public static final int EXISTS=50;
    public static final int String=64;
    public static final int DOT=23;
    public static final int Whitespace=113;
    public static final int STRCAT=106;
    public static final int LIKE=51;
    public static final int OUTER=32;
    public static final int BY=38;
    public static final int PERCENT=13;
    public static final int VALUES=20;
    public static final int RIGHT=33;
    public static final int SET=16;
    public static final int HAVING=39;
    public static final int MIN=26;
    public static final int MINUS=61;
    public static final int Digit=110;
    public static final int QuotedIdentifier=68;
    public static final int SEMI=4;
    public static final int TRUE=65;
    public static final int JOIN=29;
    public static final int UNION=99;
    public static final int COLON=108;
    public static final int ANY=58;
    public static final int QUESTION=107;
    public static final int WHEN=102;
    public static final int RCURLY=105;
    public static final int NATURAL=34;
    public static final int DESC=42;
    public static final int TOP=11;
    public static final int BETWEEN=47;
    public static final int Integer=12;

    // delegates
    // delegators

    public static final String[] ruleNames = new String[] {
        "invalidRule", "synpred56_GenericSQL", "synpred62_GenericSQL", "having", 
        "synpred20_GenericSQL", "synpred33_GenericSQL", "tableRef", "columnRef", 
        "synpred51_GenericSQL", "synpred57_GenericSQL", "synpred10_GenericSQL", 
        "synpred42_GenericSQL", "orderBy", "synpred68_GenericSQL", "synpred90_GenericSQL", 
        "synpred4_GenericSQL", "synpred64_GenericSQL", "synpred2_GenericSQL", 
        "fromItem", "synpred8_GenericSQL", "synpred96_GenericSQL", "synpred70_GenericSQL", 
        "synpred45_GenericSQL", "synpred79_GenericSQL", "synpred35_GenericSQL", 
        "synpred34_GenericSQL", "synpred22_GenericSQL", "columnName", "like", 
        "synpred48_GenericSQL", "literal", "synpred21_GenericSQL", "functionName", 
        "statement", "synpred11_GenericSQL", "into", "insert", "synpred77_GenericSQL", 
        "synpred75_GenericSQL", "expression", "synpred76_GenericSQL", "unary", 
        "from", "synpred80_GenericSQL", "alias", "synpred6_GenericSQL", 
        "subSelect", "synpred16_GenericSQL", "synpred50_GenericSQL", "synpred92_GenericSQL", 
        "condition", "joinList", "synpred72_GenericSQL", "databaseName", 
        "quantifier", "synpred52_GenericSQL", "synpred93_GenericSQL", "synpred78_GenericSQL", 
        "synpred69_GenericSQL", "synpred94_GenericSQL", "columnList", "synpred40_GenericSQL", 
        "synpred9_GenericSQL", "orderByItem", "synpred54_GenericSQL", "itemList", 
        "synpred14_GenericSQL", "synpred66_GenericSQL", "synpred27_GenericSQL", 
        "synpred46_GenericSQL", "synpred38_GenericSQL", "values", "synpred25_GenericSQL", 
        "synpred88_GenericSQL", "synpred19_GenericSQL", "nestedCondition", 
        "synpred29_GenericSQL", "synpred82_GenericSQL", "synpred84_GenericSQL", 
        "tableAlias", "synpred89_GenericSQL", "synpred97_GenericSQL", "update", 
        "synpred44_GenericSQL", "synpred12_GenericSQL", "synpred55_GenericSQL", 
        "synpred18_GenericSQL", "synpred24_GenericSQL", "synpred37_GenericSQL", 
        "synpred43_GenericSQL", "nestedExpression", "synpred95_GenericSQL", 
        "synpred13_GenericSQL", "synpred47_GenericSQL", "synpred36_GenericSQL", 
        "in", "synpred58_GenericSQL", "synpred49_GenericSQL", "synpred15_GenericSQL", 
        "synpred83_GenericSQL", "synpred71_GenericSQL", "synpred65_GenericSQL", 
        "synpred73_GenericSQL", "synpred60_GenericSQL", "exists", "comparator", 
        "groupBy", "synpred17_GenericSQL", "synpred30_GenericSQL", "item", 
        "tableName", "expressionList", "between", "synpred1_GenericSQL", 
        "synpred81_GenericSQL", "setter", "comparison", "function", "synpred7_GenericSQL", 
        "synpred41_GenericSQL", "synpred26_GenericSQL", "synpred32_GenericSQL", 
        "synpred91_GenericSQL", "synpred87_GenericSQL", "allColumns", "where", 
        "synpred31_GenericSQL", "multiply", "conditionList", "synpred67_GenericSQL", 
        "synpred3_GenericSQL", "synpred86_GenericSQL", "synpred59_GenericSQL", 
        "value", "select", "join", "date", "synpred61_GenericSQL", "synpred74_GenericSQL", 
        "synpred28_GenericSQL", "synpred85_GenericSQL", "isNull", "synpred23_GenericSQL", 
        "synpred5_GenericSQL", "synpred63_GenericSQL", "synpred53_GenericSQL", 
        "synpred39_GenericSQL"
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
    public String getGrammarFileName() { return "/Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g"; }





    // $ANTLR start "statement"
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:53:1: statement : ( select ( SEMI )? EOF | insert ( SEMI )? EOF | update ( SEMI )? EOF );
    public final void statement() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "statement");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(53, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:54:3: ( select ( SEMI )? EOF | insert ( SEMI )? EOF | update ( SEMI )? EOF )
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:54:5: select ( SEMI )? EOF
                    {
                    dbg.location(54,5);
                    pushFollow(FOLLOW_select_in_statement67);
                    select();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(54,12);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:54:12: ( SEMI )?
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

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:54:14: SEMI
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:55:5: insert ( SEMI )? EOF
                    {
                    dbg.location(55,5);
                    pushFollow(FOLLOW_insert_in_statement82);
                    insert();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(55,12);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:55:12: ( SEMI )?
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

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:55:14: SEMI
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:56:4: update ( SEMI )? EOF
                    {
                    dbg.location(56,4);
                    pushFollow(FOLLOW_update_in_statement96);
                    update();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(56,11);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:56:11: ( SEMI )?
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

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:56:13: SEMI
                            {
                            dbg.location(56,13);
                            match(input,SEMI,FOLLOW_SEMI_in_statement100); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(3);}

                    dbg.location(56,21);
                    match(input,EOF,FOLLOW_EOF_in_statement105); if (state.failed) return ;

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:60:1: subSelect : ( select | LPAREN select RPAREN );
    public final void subSelect() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "subSelect");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(60, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:61:3: ( select | LPAREN select RPAREN )
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:61:5: select
                    {
                    dbg.location(61,5);
                    pushFollow(FOLLOW_select_in_subSelect121);
                    select();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:62:5: LPAREN select RPAREN
                    {
                    dbg.location(62,5);
                    match(input,LPAREN,FOLLOW_LPAREN_in_subSelect127); if (state.failed) return ;
                    dbg.location(62,12);
                    pushFollow(FOLLOW_select_in_subSelect129);
                    select();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(62,19);
                    match(input,RPAREN,FOLLOW_RPAREN_in_subSelect131); if (state.failed) return ;

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:65:1: select : SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( joinList )? ( where )? ( groupBy )? ( having )? ( orderBy )? ;
    public final void select() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "select");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(65, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:66:3: ( SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( joinList )? ( where )? ( groupBy )? ( having )? ( orderBy )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:66:5: SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( joinList )? ( where )? ( groupBy )? ( having )? ( orderBy )?
            {
            dbg.location(66,5);
            match(input,SELECT,FOLLOW_SELECT_in_select146); if (state.failed) return ;
            dbg.location(67,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:67:5: ( ALL | DISTINCT | UNIQUE )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:
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
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:68:5: ( TOP Integer ( PERCENT )? )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:68:7: TOP Integer ( PERCENT )?
                    {
                    dbg.location(68,7);
                    match(input,TOP,FOLLOW_TOP_in_select173); if (state.failed) return ;
                    dbg.location(68,11);
                    match(input,Integer,FOLLOW_Integer_in_select175); if (state.failed) return ;
                    dbg.location(68,19);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:68:19: ( PERCENT )?
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

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:68:21: PERCENT
                            {
                            dbg.location(68,21);
                            match(input,PERCENT,FOLLOW_PERCENT_in_select179); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(7);}


                    }
                    break;

            }
            } finally {dbg.exitSubRule(8);}

            dbg.location(69,5);
            pushFollow(FOLLOW_itemList_in_select191);
            itemList();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(70,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:70:5: ( into )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:70:7: into
                    {
                    dbg.location(70,7);
                    pushFollow(FOLLOW_into_in_select199);
                    into();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(9);}

            dbg.location(71,5);
            pushFollow(FOLLOW_from_in_select208);
            from();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(72,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:72:5: ( joinList )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:72:7: joinList
                    {
                    dbg.location(72,7);
                    pushFollow(FOLLOW_joinList_in_select216);
                    joinList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(10);}

            dbg.location(73,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:73:5: ( where )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:73:7: where
                    {
                    dbg.location(73,7);
                    pushFollow(FOLLOW_where_in_select227);
                    where();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(11);}

            dbg.location(74,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:74:5: ( groupBy )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:74:7: groupBy
                    {
                    dbg.location(74,7);
                    pushFollow(FOLLOW_groupBy_in_select238);
                    groupBy();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(12);}

            dbg.location(75,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:75:5: ( having )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:75:7: having
                    {
                    dbg.location(75,7);
                    pushFollow(FOLLOW_having_in_select249);
                    having();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(13);}

            dbg.location(76,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:76:5: ( orderBy )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:76:7: orderBy
                    {
                    dbg.location(76,7);
                    pushFollow(FOLLOW_orderBy_in_select260);
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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:79:1: insert : INSERT into ( columnList )? ( values ) ;
    public final void insert() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "insert");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(79, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:80:3: ( INSERT into ( columnList )? ( values ) )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:80:5: INSERT into ( columnList )? ( values )
            {
            dbg.location(80,5);
            match(input,INSERT,FOLLOW_INSERT_in_insert278); if (state.failed) return ;
            dbg.location(80,12);
            pushFollow(FOLLOW_into_in_insert280);
            into();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(80,17);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:80:17: ( columnList )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:80:19: columnList
                    {
                    dbg.location(80,19);
                    pushFollow(FOLLOW_columnList_in_insert284);
                    columnList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(15);}

            dbg.location(81,3);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:81:3: ( values )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:81:5: values
            {
            dbg.location(81,5);
            pushFollow(FOLLOW_values_in_insert293);
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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:86:1: update : UPDATE tableRef SET setter ( COMMA setter )* ( where )? ;
    public final void update() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "update");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(86, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:87:3: ( UPDATE tableRef SET setter ( COMMA setter )* ( where )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:87:5: UPDATE tableRef SET setter ( COMMA setter )* ( where )?
            {
            dbg.location(87,5);
            match(input,UPDATE,FOLLOW_UPDATE_in_update313); if (state.failed) return ;
            dbg.location(87,12);
            pushFollow(FOLLOW_tableRef_in_update315);
            tableRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(87,21);
            match(input,SET,FOLLOW_SET_in_update317); if (state.failed) return ;
            dbg.location(87,25);
            pushFollow(FOLLOW_setter_in_update319);
            setter();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(87,32);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:87:32: ( COMMA setter )*
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

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:87:34: COMMA setter
            	    {
            	    dbg.location(87,34);
            	    match(input,COMMA,FOLLOW_COMMA_in_update323); if (state.failed) return ;
            	    dbg.location(87,40);
            	    pushFollow(FOLLOW_setter_in_update325);
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
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:88:5: ( where )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:88:7: where
                    {
                    dbg.location(88,7);
                    pushFollow(FOLLOW_where_in_update336);
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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:91:1: setter : columnName EQ literal ;
    public final void setter() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "setter");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(91, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:92:3: ( columnName EQ literal )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:92:5: columnName EQ literal
            {
            dbg.location(92,5);
            pushFollow(FOLLOW_columnName_in_setter354);
            columnName();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(92,16);
            match(input,EQ,FOLLOW_EQ_in_setter356); if (state.failed) return ;
            dbg.location(92,19);
            pushFollow(FOLLOW_literal_in_setter358);
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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:95:1: into : INTO tableRef ( COMMA tableRef )* ;
    public final void into() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "into");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(95, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:96:3: ( INTO tableRef ( COMMA tableRef )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:96:5: INTO tableRef ( COMMA tableRef )*
            {
            dbg.location(96,5);
            match(input,INTO,FOLLOW_INTO_in_into373); if (state.failed) return ;
            dbg.location(96,10);
            pushFollow(FOLLOW_tableRef_in_into375);
            tableRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(96,19);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:96:19: ( COMMA tableRef )*
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

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:96:21: COMMA tableRef
            	    {
            	    dbg.location(96,21);
            	    match(input,COMMA,FOLLOW_COMMA_in_into379); if (state.failed) return ;
            	    dbg.location(96,27);
            	    pushFollow(FOLLOW_tableRef_in_into381);
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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:99:1: columnList : LPAREN columnName ( COMMA columnName )* RPAREN ;
    public final void columnList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(99, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:100:3: ( LPAREN columnName ( COMMA columnName )* RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:100:5: LPAREN columnName ( COMMA columnName )* RPAREN
            {
            dbg.location(100,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_columnList397); if (state.failed) return ;
            dbg.location(100,12);
            pushFollow(FOLLOW_columnName_in_columnList399);
            columnName();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(100,23);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:100:23: ( COMMA columnName )*
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

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:100:25: COMMA columnName
            	    {
            	    dbg.location(100,25);
            	    match(input,COMMA,FOLLOW_COMMA_in_columnList403); if (state.failed) return ;
            	    dbg.location(100,31);
            	    pushFollow(FOLLOW_columnName_in_columnList405);
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
            match(input,RPAREN,FOLLOW_RPAREN_in_columnList410); if (state.failed) return ;

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:103:1: values : VALUES LPAREN literal ( COMMA literal )* RPAREN ;
    public final void values() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "values");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(103, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:104:3: ( VALUES LPAREN literal ( COMMA literal )* RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:104:5: VALUES LPAREN literal ( COMMA literal )* RPAREN
            {
            dbg.location(104,5);
            match(input,VALUES,FOLLOW_VALUES_in_values425); if (state.failed) return ;
            dbg.location(104,12);
            match(input,LPAREN,FOLLOW_LPAREN_in_values427); if (state.failed) return ;
            dbg.location(104,19);
            pushFollow(FOLLOW_literal_in_values429);
            literal();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(104,27);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:104:27: ( COMMA literal )*
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

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:104:29: COMMA literal
            	    {
            	    dbg.location(104,29);
            	    match(input,COMMA,FOLLOW_COMMA_in_values433); if (state.failed) return ;
            	    dbg.location(104,35);
            	    pushFollow(FOLLOW_literal_in_values435);
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
            match(input,RPAREN,FOLLOW_RPAREN_in_values440); if (state.failed) return ;

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:107:1: itemList : ( STAR | item ( COMMA item )* );
    public final void itemList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "itemList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(107, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:108:3: ( STAR | item ( COMMA item )* )
            int alt22=2;
            try { dbg.enterDecision(22);

            int LA22_0 = input.LA(1);

            if ( (LA22_0==STAR) ) {
                alt22=1;
            }
            else if ( (LA22_0==LPAREN||LA22_0==Integer||(LA22_0>=Identifier && LA22_0<=MAX)||(LA22_0>=PLUS && LA22_0<=MINUS)||(LA22_0>=Float && LA22_0<=FALSE)||LA22_0==QuotedIdentifier||(LA22_0>=114 && LA22_0<=116)) ) {
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:108:5: STAR
                    {
                    dbg.location(108,5);
                    match(input,STAR,FOLLOW_STAR_in_itemList455); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:109:5: item ( COMMA item )*
                    {
                    dbg.location(109,5);
                    pushFollow(FOLLOW_item_in_itemList461);
                    item();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(109,10);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:109:10: ( COMMA item )*
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

                    	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:109:12: COMMA item
                    	    {
                    	    dbg.location(109,12);
                    	    match(input,COMMA,FOLLOW_COMMA_in_itemList465); if (state.failed) return ;
                    	    dbg.location(109,18);
                    	    pushFollow(FOLLOW_item_in_itemList467);
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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:112:1: item : ( value ( ( AS )? alias )? | allColumns | function ( ( AS )? alias )? );
    public final void item() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "item");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(112, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:113:3: ( value ( ( AS )? alias )? | allColumns | function ( ( AS )? alias )? )
            int alt27=3;
            try { dbg.enterDecision(27);

            switch ( input.LA(1) ) {
            case LPAREN:
            case Integer:
            case PLUS:
            case MINUS:
            case Float:
            case String:
            case TRUE:
            case FALSE:
            case QuotedIdentifier:
            case 114:
            case 115:
            case 116:
                {
                alt27=1;
                }
                break;
            case Identifier:
                {
                int LA27_2 = input.LA(2);

                if ( (LA27_2==EOF||LA27_2==COMMA||LA27_2==INTO||LA27_2==AS||LA27_2==Identifier||LA27_2==FROM) ) {
                    alt27=1;
                }
                else if ( (LA27_2==DOT) ) {
                    int LA27_4 = input.LA(3);

                    if ( (LA27_4==STAR) ) {
                        alt27=2;
                    }
                    else if ( (LA27_4==Identifier||LA27_4==QuotedIdentifier) ) {
                        alt27=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 4, input);

                        dbg.recognitionException(nvae);
                        throw nvae;
                    }
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 2, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
                }
                break;
            case COUNT:
            case MIN:
            case MAX:
                {
                alt27=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(27);}

            switch (alt27) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:113:5: value ( ( AS )? alias )?
                    {
                    dbg.location(113,5);
                    pushFollow(FOLLOW_value_in_item485);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(113,11);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:113:11: ( ( AS )? alias )?
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

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:113:13: ( AS )? alias
                            {
                            dbg.location(113,13);
                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:113:13: ( AS )?
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

                                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:113:15: AS
                                    {
                                    dbg.location(113,15);
                                    match(input,AS,FOLLOW_AS_in_item491); if (state.failed) return ;

                                    }
                                    break;

                            }
                            } finally {dbg.exitSubRule(23);}

                            dbg.location(113,21);
                            pushFollow(FOLLOW_alias_in_item496);
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:114:5: allColumns
                    {
                    dbg.location(114,5);
                    pushFollow(FOLLOW_allColumns_in_item505);
                    allColumns();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:115:5: function ( ( AS )? alias )?
                    {
                    dbg.location(115,5);
                    pushFollow(FOLLOW_function_in_item511);
                    function();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(115,14);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:115:14: ( ( AS )? alias )?
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

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:115:16: ( AS )? alias
                            {
                            dbg.location(115,16);
                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:115:16: ( AS )?
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

                                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:0:0: AS
                                    {
                                    dbg.location(115,16);
                                    match(input,AS,FOLLOW_AS_in_item515); if (state.failed) return ;

                                    }
                                    break;

                            }
                            } finally {dbg.exitSubRule(25);}

                            dbg.location(115,20);
                            pushFollow(FOLLOW_alias_in_item518);
                            alias();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(26);}


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
        dbg.location(116, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:118:1: allColumns : tableAlias DOT STAR ;
    public final void allColumns() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "allColumns");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(118, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:119:3: ( tableAlias DOT STAR )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:119:5: tableAlias DOT STAR
            {
            dbg.location(119,5);
            pushFollow(FOLLOW_tableAlias_in_allColumns535);
            tableAlias();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(119,16);
            match(input,DOT,FOLLOW_DOT_in_allColumns537); if (state.failed) return ;
            dbg.location(119,20);
            match(input,STAR,FOLLOW_STAR_in_allColumns539); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(120, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:122:1: alias : Identifier ;
    public final void alias() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "alias");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(122, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:123:3: ( Identifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:123:5: Identifier
            {
            dbg.location(123,5);
            match(input,Identifier,FOLLOW_Identifier_in_alias552); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(124, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:126:1: function : functionName LPAREN ( value ( COMMA value )* )? RPAREN ;
    public final void function() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "function");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(126, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:127:3: ( functionName LPAREN ( value ( COMMA value )* )? RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:127:5: functionName LPAREN ( value ( COMMA value )* )? RPAREN
            {
            dbg.location(127,5);
            pushFollow(FOLLOW_functionName_in_function567);
            functionName();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(127,18);
            match(input,LPAREN,FOLLOW_LPAREN_in_function569); if (state.failed) return ;
            dbg.location(127,25);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:127:25: ( value ( COMMA value )* )?
            int alt29=2;
            try { dbg.enterSubRule(29);
            try { dbg.enterDecision(29);

            int LA29_0 = input.LA(1);

            if ( (LA29_0==LPAREN||LA29_0==Integer||LA29_0==Identifier||(LA29_0>=PLUS && LA29_0<=MINUS)||(LA29_0>=Float && LA29_0<=FALSE)||LA29_0==QuotedIdentifier||(LA29_0>=114 && LA29_0<=116)) ) {
                alt29=1;
            }
            } finally {dbg.exitDecision(29);}

            switch (alt29) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:127:27: value ( COMMA value )*
                    {
                    dbg.location(127,27);
                    pushFollow(FOLLOW_value_in_function573);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(127,33);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:127:33: ( COMMA value )*
                    try { dbg.enterSubRule(28);

                    loop28:
                    do {
                        int alt28=2;
                        try { dbg.enterDecision(28);

                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==COMMA) ) {
                            alt28=1;
                        }


                        } finally {dbg.exitDecision(28);}

                        switch (alt28) {
                    	case 1 :
                    	    dbg.enterAlt(1);

                    	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:127:35: COMMA value
                    	    {
                    	    dbg.location(127,35);
                    	    match(input,COMMA,FOLLOW_COMMA_in_function577); if (state.failed) return ;
                    	    dbg.location(127,41);
                    	    pushFollow(FOLLOW_value_in_function579);
                    	    value();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);
                    } finally {dbg.exitSubRule(28);}


                    }
                    break;

            }
            } finally {dbg.exitSubRule(29);}

            dbg.location(127,53);
            match(input,RPAREN,FOLLOW_RPAREN_in_function587); if (state.failed) return ;

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
            dbg.exitRule(getGrammarFileName(), "function");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "function"


    // $ANTLR start "functionName"
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:130:1: functionName : ( COUNT | MIN | MAX );
    public final void functionName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "functionName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(130, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:131:3: ( COUNT | MIN | MAX )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(131,3);
            if ( (input.LA(1)>=COUNT && input.LA(1)<=MAX) ) {
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
        dbg.location(134, 3);

        }
        finally {
            dbg.exitRule(getGrammarFileName(), "functionName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "functionName"


    // $ANTLR start "from"
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:136:1: from : FROM fromItem ( COMMA fromItem )* ;
    public final void from() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "from");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(136, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:137:3: ( FROM fromItem ( COMMA fromItem )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:137:5: FROM fromItem ( COMMA fromItem )*
            {
            dbg.location(137,5);
            match(input,FROM,FOLLOW_FROM_in_from629); if (state.failed) return ;
            dbg.location(137,10);
            pushFollow(FOLLOW_fromItem_in_from631);
            fromItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(137,19);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:137:19: ( COMMA fromItem )*
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

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:137:21: COMMA fromItem
            	    {
            	    dbg.location(137,21);
            	    match(input,COMMA,FOLLOW_COMMA_in_from635); if (state.failed) return ;
            	    dbg.location(137,27);
            	    pushFollow(FOLLOW_fromItem_in_from637);
            	    fromItem();

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

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(138, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:140:1: fromItem : ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? ;
    public final void fromItem() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "fromItem");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(140, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:141:3: ( ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:141:5: ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )?
            {
            dbg.location(141,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:141:5: ( ( LPAREN subSelect RPAREN ) | tableRef )
            int alt31=2;
            try { dbg.enterSubRule(31);
            try { dbg.enterDecision(31);

            int LA31_0 = input.LA(1);

            if ( (LA31_0==LPAREN) ) {
                alt31=1;
            }
            else if ( (LA31_0==Identifier||LA31_0==QuotedIdentifier) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(31);}

            switch (alt31) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:141:7: ( LPAREN subSelect RPAREN )
                    {
                    dbg.location(141,7);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:141:7: ( LPAREN subSelect RPAREN )
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:141:9: LPAREN subSelect RPAREN
                    {
                    dbg.location(141,9);
                    match(input,LPAREN,FOLLOW_LPAREN_in_fromItem659); if (state.failed) return ;
                    dbg.location(141,16);
                    pushFollow(FOLLOW_subSelect_in_fromItem661);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(141,26);
                    match(input,RPAREN,FOLLOW_RPAREN_in_fromItem663); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:142:7: tableRef
                    {
                    dbg.location(142,7);
                    pushFollow(FOLLOW_tableRef_in_fromItem674);
                    tableRef();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(31);}

            dbg.location(144,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:144:5: ( ( AS )? alias )?
            int alt33=2;
            try { dbg.enterSubRule(33);
            try { dbg.enterDecision(33);

            int LA33_0 = input.LA(1);

            if ( (LA33_0==AS) ) {
                alt33=1;
            }
            else if ( (LA33_0==Identifier) ) {
                int LA33_2 = input.LA(2);

                if ( (LA33_2==EOF||LA33_2==SEMI||LA33_2==RPAREN||LA33_2==COMMA||LA33_2==Identifier||(LA33_2>=JOIN && LA33_2<=NATURAL)||(LA33_2>=WHERE && LA33_2<=GROUP)||(LA33_2>=HAVING && LA33_2<=ORDER)) ) {
                    alt33=1;
                }
            }
            } finally {dbg.exitDecision(33);}

            switch (alt33) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:144:7: ( AS )? alias
                    {
                    dbg.location(144,7);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:144:7: ( AS )?
                    int alt32=2;
                    try { dbg.enterSubRule(32);
                    try { dbg.enterDecision(32);

                    int LA32_0 = input.LA(1);

                    if ( (LA32_0==AS) ) {
                        alt32=1;
                    }
                    } finally {dbg.exitDecision(32);}

                    switch (alt32) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:144:9: AS
                            {
                            dbg.location(144,9);
                            match(input,AS,FOLLOW_AS_in_fromItem691); if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(32);}

                    dbg.location(144,15);
                    pushFollow(FOLLOW_alias_in_fromItem696);
                    alias();

                    state._fsp--;
                    if (state.failed) return ;

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
        dbg.location(145, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:147:1: joinList : ( join )* ;
    public final void joinList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "joinList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(147, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:148:3: ( ( join )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:148:5: ( join )*
            {
            dbg.location(148,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:148:5: ( join )*
            try { dbg.enterSubRule(34);

            loop34:
            do {
                int alt34=2;
                try { dbg.enterDecision(34);

                int LA34_0 = input.LA(1);

                if ( ((LA34_0>=JOIN && LA34_0<=NATURAL)) ) {
                    alt34=1;
                }


                } finally {dbg.exitDecision(34);}

                switch (alt34) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:148:7: join
            	    {
            	    dbg.location(148,7);
            	    pushFollow(FOLLOW_join_in_joinList714);
            	    join();

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
        dbg.location(149, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:151:1: join : ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN ) fromItem alias ON conditionList ;
    public final void join() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "join");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(151, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:152:3: ( ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN ) fromItem alias ON conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:153:5: ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN ) fromItem alias ON conditionList
            {
            dbg.location(153,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:153:5: ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN )
            int alt35=8;
            try { dbg.enterSubRule(35);
            try { dbg.enterDecision(35);

            try {
                isCyclicDecision = true;
                alt35 = dfa35.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(35);}

            switch (alt35) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:153:7: JOIN
                    {
                    dbg.location(153,7);
                    match(input,JOIN,FOLLOW_JOIN_in_join739); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:154:7: INNER JOIN
                    {
                    dbg.location(154,7);
                    match(input,INNER,FOLLOW_INNER_in_join747); if (state.failed) return ;
                    dbg.location(154,13);
                    match(input,JOIN,FOLLOW_JOIN_in_join749); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:155:7: LEFT JOIN
                    {
                    dbg.location(155,7);
                    match(input,LEFT,FOLLOW_LEFT_in_join757); if (state.failed) return ;
                    dbg.location(155,12);
                    match(input,JOIN,FOLLOW_JOIN_in_join759); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:156:7: LEFT OUTER JOIN
                    {
                    dbg.location(156,7);
                    match(input,LEFT,FOLLOW_LEFT_in_join767); if (state.failed) return ;
                    dbg.location(156,12);
                    match(input,OUTER,FOLLOW_OUTER_in_join769); if (state.failed) return ;
                    dbg.location(156,18);
                    match(input,JOIN,FOLLOW_JOIN_in_join771); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:157:7: RIGHT JOIN
                    {
                    dbg.location(157,7);
                    match(input,RIGHT,FOLLOW_RIGHT_in_join779); if (state.failed) return ;
                    dbg.location(157,13);
                    match(input,JOIN,FOLLOW_JOIN_in_join781); if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:158:7: RIGHT OUTER JOIN
                    {
                    dbg.location(158,7);
                    match(input,RIGHT,FOLLOW_RIGHT_in_join790); if (state.failed) return ;
                    dbg.location(158,13);
                    match(input,OUTER,FOLLOW_OUTER_in_join792); if (state.failed) return ;
                    dbg.location(158,19);
                    match(input,JOIN,FOLLOW_JOIN_in_join794); if (state.failed) return ;

                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:159:7: OUTER JOIN
                    {
                    dbg.location(159,7);
                    match(input,OUTER,FOLLOW_OUTER_in_join802); if (state.failed) return ;
                    dbg.location(159,13);
                    match(input,JOIN,FOLLOW_JOIN_in_join804); if (state.failed) return ;

                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:160:7: NATURAL JOIN
                    {
                    dbg.location(160,7);
                    match(input,NATURAL,FOLLOW_NATURAL_in_join813); if (state.failed) return ;
                    dbg.location(160,15);
                    match(input,JOIN,FOLLOW_JOIN_in_join815); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(35);}

            dbg.location(162,3);
            pushFollow(FOLLOW_fromItem_in_join826);
            fromItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(162,12);
            pushFollow(FOLLOW_alias_in_join828);
            alias();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(168,3);
            match(input,ON,FOLLOW_ON_in_join836); if (state.failed) return ;
            dbg.location(168,6);
            pushFollow(FOLLOW_conditionList_in_join838);
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
        dbg.location(169, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:177:1: where : WHERE conditionList ;
    public final void where() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "where");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(177, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:178:3: ( WHERE conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:178:5: WHERE conditionList
            {
            dbg.location(178,5);
            match(input,WHERE,FOLLOW_WHERE_in_where859); if (state.failed) return ;
            dbg.location(178,11);
            pushFollow(FOLLOW_conditionList_in_where861);
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
        dbg.location(179, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:181:1: groupBy : GROUP BY columnRef ( COMMA columnRef )* ;
    public final void groupBy() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "groupBy");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(181, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:182:3: ( GROUP BY columnRef ( COMMA columnRef )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:182:5: GROUP BY columnRef ( COMMA columnRef )*
            {
            dbg.location(182,5);
            match(input,GROUP,FOLLOW_GROUP_in_groupBy876); if (state.failed) return ;
            dbg.location(182,11);
            match(input,BY,FOLLOW_BY_in_groupBy878); if (state.failed) return ;
            dbg.location(182,14);
            pushFollow(FOLLOW_columnRef_in_groupBy880);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(182,24);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:182:24: ( COMMA columnRef )*
            try { dbg.enterSubRule(36);

            loop36:
            do {
                int alt36=2;
                try { dbg.enterDecision(36);

                int LA36_0 = input.LA(1);

                if ( (LA36_0==COMMA) ) {
                    alt36=1;
                }


                } finally {dbg.exitDecision(36);}

                switch (alt36) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:182:26: COMMA columnRef
            	    {
            	    dbg.location(182,26);
            	    match(input,COMMA,FOLLOW_COMMA_in_groupBy884); if (state.failed) return ;
            	    dbg.location(182,32);
            	    pushFollow(FOLLOW_columnRef_in_groupBy886);
            	    columnRef();

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
        dbg.location(183, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:185:1: having : HAVING conditionList ;
    public final void having() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "having");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(185, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:186:3: ( HAVING conditionList )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:186:5: HAVING conditionList
            {
            dbg.location(186,5);
            match(input,HAVING,FOLLOW_HAVING_in_having904); if (state.failed) return ;
            dbg.location(186,12);
            pushFollow(FOLLOW_conditionList_in_having906);
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
        dbg.location(187, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:189:1: orderBy : ORDER BY orderByItem ( COMMA orderByItem )* ;
    public final void orderBy() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "orderBy");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(189, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:190:3: ( ORDER BY orderByItem ( COMMA orderByItem )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:190:5: ORDER BY orderByItem ( COMMA orderByItem )*
            {
            dbg.location(190,5);
            match(input,ORDER,FOLLOW_ORDER_in_orderBy921); if (state.failed) return ;
            dbg.location(190,11);
            match(input,BY,FOLLOW_BY_in_orderBy923); if (state.failed) return ;
            dbg.location(190,14);
            pushFollow(FOLLOW_orderByItem_in_orderBy925);
            orderByItem();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(190,26);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:190:26: ( COMMA orderByItem )*
            try { dbg.enterSubRule(37);

            loop37:
            do {
                int alt37=2;
                try { dbg.enterDecision(37);

                int LA37_0 = input.LA(1);

                if ( (LA37_0==COMMA) ) {
                    alt37=1;
                }


                } finally {dbg.exitDecision(37);}

                switch (alt37) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:190:28: COMMA orderByItem
            	    {
            	    dbg.location(190,28);
            	    match(input,COMMA,FOLLOW_COMMA_in_orderBy929); if (state.failed) return ;
            	    dbg.location(190,34);
            	    pushFollow(FOLLOW_orderByItem_in_orderBy931);
            	    orderByItem();

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
        dbg.location(191, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:193:1: orderByItem : columnRef ( ASC | DESC )? ;
    public final void orderByItem() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "orderByItem");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(193, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:194:3: ( columnRef ( ASC | DESC )? )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:194:5: columnRef ( ASC | DESC )?
            {
            dbg.location(194,5);
            pushFollow(FOLLOW_columnRef_in_orderByItem949);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(194,15);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:194:15: ( ASC | DESC )?
            int alt38=2;
            try { dbg.enterSubRule(38);
            try { dbg.enterDecision(38);

            int LA38_0 = input.LA(1);

            if ( ((LA38_0>=ASC && LA38_0<=DESC)) ) {
                alt38=1;
            }
            } finally {dbg.exitDecision(38);}

            switch (alt38) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:
                    {
                    dbg.location(194,15);
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
            } finally {dbg.exitSubRule(38);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(195, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:197:1: nestedCondition : LPAREN conditionList RPAREN ;
    public final void nestedCondition() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "nestedCondition");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(197, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:198:3: ( LPAREN conditionList RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:198:5: LPAREN conditionList RPAREN
            {
            dbg.location(198,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedCondition975); if (state.failed) return ;
            dbg.location(198,12);
            pushFollow(FOLLOW_conditionList_in_nestedCondition977);
            conditionList();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(198,26);
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedCondition979); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(199, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:201:1: conditionList : condition ( ( OR | AND ) condition )* ;
    public final void conditionList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "conditionList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(201, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:202:3: ( condition ( ( OR | AND ) condition )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:202:5: condition ( ( OR | AND ) condition )*
            {
            dbg.location(202,5);
            pushFollow(FOLLOW_condition_in_conditionList994);
            condition();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(202,15);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:202:15: ( ( OR | AND ) condition )*
            try { dbg.enterSubRule(39);

            loop39:
            do {
                int alt39=2;
                try { dbg.enterDecision(39);

                int LA39_0 = input.LA(1);

                if ( ((LA39_0>=OR && LA39_0<=AND)) ) {
                    alt39=1;
                }


                } finally {dbg.exitDecision(39);}

                switch (alt39) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:202:17: ( OR | AND ) condition
            	    {
            	    dbg.location(202,17);
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

            	    dbg.location(202,30);
            	    pushFollow(FOLLOW_condition_in_conditionList1008);
            	    condition();

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
        dbg.location(203, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:205:1: condition : ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison ) ;
    public final void condition() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "condition");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(205, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:206:3: ( ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison ) )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:206:5: ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )
            {
            dbg.location(206,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:206:5: ( NOT )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:206:7: NOT
                    {
                    dbg.location(206,7);
                    match(input,NOT,FOLLOW_NOT_in_condition1028); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(40);}

            dbg.location(207,5);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:207:5: ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )
            int alt41=8;
            try { dbg.enterSubRule(41);
            try { dbg.enterDecision(41);

            try {
                isCyclicDecision = true;
                alt41 = dfa41.predict(input);
            }
            catch (NoViableAltException nvae) {
                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(41);}

            switch (alt41) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:207:7: nestedCondition
                    {
                    dbg.location(207,7);
                    pushFollow(FOLLOW_nestedCondition_in_condition1039);
                    nestedCondition();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:208:7: in
                    {
                    dbg.location(208,7);
                    pushFollow(FOLLOW_in_in_condition1047);
                    in();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:209:7: between
                    {
                    dbg.location(209,7);
                    pushFollow(FOLLOW_between_in_condition1055);
                    between();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:210:7: isNull
                    {
                    dbg.location(210,7);
                    pushFollow(FOLLOW_isNull_in_condition1063);
                    isNull();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:211:7: exists
                    {
                    dbg.location(211,7);
                    pushFollow(FOLLOW_exists_in_condition1071);
                    exists();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:212:7: like
                    {
                    dbg.location(212,7);
                    pushFollow(FOLLOW_like_in_condition1079);
                    like();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    dbg.enterAlt(7);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:213:7: quantifier
                    {
                    dbg.location(213,7);
                    pushFollow(FOLLOW_quantifier_in_condition1087);
                    quantifier();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    dbg.enterAlt(8);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:214:6: comparison
                    {
                    dbg.location(214,6);
                    pushFollow(FOLLOW_comparison_in_condition1094);
                    comparison();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(41);}


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(216, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:218:1: in : expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN ;
    public final void in() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "in");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(218, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:219:3: ( expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:219:5: expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN
            {
            dbg.location(219,5);
            pushFollow(FOLLOW_expression_in_in1113);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(219,16);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:219:16: ( NOT )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:219:18: NOT
                    {
                    dbg.location(219,18);
                    match(input,NOT,FOLLOW_NOT_in_in1117); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(42);}

            dbg.location(219,25);
            match(input,IN,FOLLOW_IN_in_in1122); if (state.failed) return ;
            dbg.location(219,28);
            match(input,LPAREN,FOLLOW_LPAREN_in_in1124); if (state.failed) return ;
            dbg.location(219,35);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:219:35: ( subSelect | expressionList )
            int alt43=2;
            try { dbg.enterSubRule(43);
            try { dbg.enterDecision(43);

            switch ( input.LA(1) ) {
            case SELECT:
                {
                alt43=1;
                }
                break;
            case LPAREN:
                {
                int LA43_2 = input.LA(2);

                if ( (LA43_2==LPAREN||LA43_2==Integer||LA43_2==Identifier||(LA43_2>=PLUS && LA43_2<=MINUS)||(LA43_2>=Float && LA43_2<=FALSE)||LA43_2==QuotedIdentifier||(LA43_2>=114 && LA43_2<=116)) ) {
                    alt43=2;
                }
                else if ( (LA43_2==SELECT) ) {
                    alt43=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 43, 2, input);

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
            case 114:
            case 115:
            case 116:
                {
                alt43=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 43, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }

            } finally {dbg.exitDecision(43);}

            switch (alt43) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:219:37: subSelect
                    {
                    dbg.location(219,37);
                    pushFollow(FOLLOW_subSelect_in_in1128);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:219:49: expressionList
                    {
                    dbg.location(219,49);
                    pushFollow(FOLLOW_expressionList_in_in1132);
                    expressionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(43);}

            dbg.location(219,66);
            match(input,RPAREN,FOLLOW_RPAREN_in_in1136); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(220, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:222:1: between : expression ( NOT )? BETWEEN expression AND expression ;
    public final void between() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "between");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(222, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:223:3: ( expression ( NOT )? BETWEEN expression AND expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:223:5: expression ( NOT )? BETWEEN expression AND expression
            {
            dbg.location(223,5);
            pushFollow(FOLLOW_expression_in_between1151);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(223,16);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:223:16: ( NOT )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:223:18: NOT
                    {
                    dbg.location(223,18);
                    match(input,NOT,FOLLOW_NOT_in_between1155); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(44);}

            dbg.location(223,25);
            match(input,BETWEEN,FOLLOW_BETWEEN_in_between1160); if (state.failed) return ;
            dbg.location(223,33);
            pushFollow(FOLLOW_expression_in_between1162);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(223,44);
            match(input,AND,FOLLOW_AND_in_between1164); if (state.failed) return ;
            dbg.location(223,48);
            pushFollow(FOLLOW_expression_in_between1166);
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
        dbg.location(224, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:226:1: isNull : expression IS ( NOT )? NULL ;
    public final void isNull() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "isNull");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(226, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:227:3: ( expression IS ( NOT )? NULL )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:227:5: expression IS ( NOT )? NULL
            {
            dbg.location(227,5);
            pushFollow(FOLLOW_expression_in_isNull1181);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(227,16);
            match(input,IS,FOLLOW_IS_in_isNull1183); if (state.failed) return ;
            dbg.location(227,19);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:227:19: ( NOT )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:227:21: NOT
                    {
                    dbg.location(227,21);
                    match(input,NOT,FOLLOW_NOT_in_isNull1187); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(45);}

            dbg.location(227,28);
            match(input,NULL,FOLLOW_NULL_in_isNull1192); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(228, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:230:1: exists : EXISTS expression ;
    public final void exists() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "exists");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(230, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:231:3: ( EXISTS expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:231:5: EXISTS expression
            {
            dbg.location(231,5);
            match(input,EXISTS,FOLLOW_EXISTS_in_exists1207); if (state.failed) return ;
            dbg.location(231,12);
            pushFollow(FOLLOW_expression_in_exists1209);
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
        dbg.location(232, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:234:1: like : expression ( NOT )? LIKE expression ;
    public final void like() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "like");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(234, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:237:3: ( expression ( NOT )? LIKE expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:237:5: expression ( NOT )? LIKE expression
            {
            dbg.location(237,5);
            pushFollow(FOLLOW_expression_in_like1228);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(237,16);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:237:16: ( NOT )?
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:237:18: NOT
                    {
                    dbg.location(237,18);
                    match(input,NOT,FOLLOW_NOT_in_like1232); if (state.failed) return ;

                    }
                    break;

            }
            } finally {dbg.exitSubRule(46);}

            dbg.location(237,25);
            match(input,LIKE,FOLLOW_LIKE_in_like1237); if (state.failed) return ;
            dbg.location(237,30);
            pushFollow(FOLLOW_expression_in_like1239);
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
        dbg.location(239, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:241:1: comparison : expression comparator expression ;
    public final void comparison() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "comparison");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(241, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:242:3: ( expression comparator expression )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:242:5: expression comparator expression
            {
            dbg.location(242,5);
            pushFollow(FOLLOW_expression_in_comparison1257);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(242,16);
            pushFollow(FOLLOW_comparator_in_comparison1259);
            comparator();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(242,27);
            pushFollow(FOLLOW_expression_in_comparison1261);
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
        dbg.location(243, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:245:1: comparator : ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT );
    public final void comparator() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "comparator");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(245, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:246:3: ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(246,3);
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
        dbg.location(253, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:255:1: quantifier : expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN ;
    public final void quantifier() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "quantifier");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(255, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:256:3: ( expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:256:5: expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN
            {
            dbg.location(256,5);
            pushFollow(FOLLOW_expression_in_quantifier1326);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(256,16);
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

            dbg.location(256,37);
            match(input,LPAREN,FOLLOW_LPAREN_in_quantifier1342); if (state.failed) return ;
            dbg.location(256,44);
            pushFollow(FOLLOW_subSelect_in_quantifier1344);
            subSelect();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(256,54);
            match(input,RPAREN,FOLLOW_RPAREN_in_quantifier1346); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(257, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:259:1: expressionList : expression ( COMMA expression )* ;
    public final void expressionList() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expressionList");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(259, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:260:3: ( expression ( COMMA expression )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:260:5: expression ( COMMA expression )*
            {
            dbg.location(260,5);
            pushFollow(FOLLOW_expression_in_expressionList1361);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(260,16);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:260:16: ( COMMA expression )*
            try { dbg.enterSubRule(47);

            loop47:
            do {
                int alt47=2;
                try { dbg.enterDecision(47);

                int LA47_0 = input.LA(1);

                if ( (LA47_0==COMMA) ) {
                    alt47=1;
                }


                } finally {dbg.exitDecision(47);}

                switch (alt47) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:260:18: COMMA expression
            	    {
            	    dbg.location(260,18);
            	    match(input,COMMA,FOLLOW_COMMA_in_expressionList1365); if (state.failed) return ;
            	    dbg.location(260,24);
            	    pushFollow(FOLLOW_expression_in_expressionList1367);
            	    expression();

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
        dbg.location(261, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:263:1: nestedExpression : LPAREN expression RPAREN ;
    public final void nestedExpression() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "nestedExpression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(263, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:264:3: ( LPAREN expression RPAREN )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:264:5: LPAREN expression RPAREN
            {
            dbg.location(264,5);
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedExpression1383); if (state.failed) return ;
            dbg.location(264,12);
            pushFollow(FOLLOW_expression_in_nestedExpression1385);
            expression();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(264,23);
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedExpression1387); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(265, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:267:1: expression : multiply ( ( PLUS | MINUS ) multiply )* ;
    public final void expression() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "expression");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(267, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:268:3: ( multiply ( ( PLUS | MINUS ) multiply )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:268:5: multiply ( ( PLUS | MINUS ) multiply )*
            {
            dbg.location(268,5);
            pushFollow(FOLLOW_multiply_in_expression1402);
            multiply();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(268,14);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:268:14: ( ( PLUS | MINUS ) multiply )*
            try { dbg.enterSubRule(48);

            loop48:
            do {
                int alt48=2;
                try { dbg.enterDecision(48);

                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=PLUS && LA48_0<=MINUS)) ) {
                    alt48=1;
                }


                } finally {dbg.exitDecision(48);}

                switch (alt48) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:268:16: ( PLUS | MINUS ) multiply
            	    {
            	    dbg.location(268,16);
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

            	    dbg.location(268,33);
            	    pushFollow(FOLLOW_multiply_in_expression1416);
            	    multiply();

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
        dbg.location(269, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:271:1: multiply : value ( ( STAR | DIVIDE ) value )* ;
    public final void multiply() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "multiply");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(271, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:272:3: ( value ( ( STAR | DIVIDE ) value )* )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:272:5: value ( ( STAR | DIVIDE ) value )*
            {
            dbg.location(272,5);
            pushFollow(FOLLOW_value_in_multiply1433);
            value();

            state._fsp--;
            if (state.failed) return ;
            dbg.location(272,11);
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:272:11: ( ( STAR | DIVIDE ) value )*
            try { dbg.enterSubRule(49);

            loop49:
            do {
                int alt49=2;
                try { dbg.enterDecision(49);

                int LA49_0 = input.LA(1);

                if ( (LA49_0==STAR||LA49_0==DIVIDE) ) {
                    alt49=1;
                }


                } finally {dbg.exitDecision(49);}

                switch (alt49) {
            	case 1 :
            	    dbg.enterAlt(1);

            	    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:272:13: ( STAR | DIVIDE ) value
            	    {
            	    dbg.location(272,13);
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

            	    dbg.location(272,31);
            	    pushFollow(FOLLOW_value_in_multiply1447);
            	    value();

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
        dbg.location(273, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:290:1: value : ( literal | ( unary )? ( columnRef | nestedExpression ) );
    public final void value() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "value");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(290, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:291:3: ( literal | ( unary )? ( columnRef | nestedExpression ) )
            int alt52=2;
            try { dbg.enterDecision(52);

            switch ( input.LA(1) ) {
            case PLUS:
            case MINUS:
                {
                int LA52_1 = input.LA(2);

                if ( (LA52_1==Integer||LA52_1==Float) ) {
                    alt52=1;
                }
                else if ( (LA52_1==LPAREN||LA52_1==Identifier||LA52_1==QuotedIdentifier) ) {
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
                alt52=1;
                }
                break;
            case LPAREN:
            case Identifier:
            case QuotedIdentifier:
                {
                alt52=2;
                }
                break;
            default:
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:291:5: literal
                    {
                    dbg.location(291,5);
                    pushFollow(FOLLOW_literal_in_value1486);
                    literal();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:293:5: ( unary )? ( columnRef | nestedExpression )
                    {
                    dbg.location(293,5);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:293:5: ( unary )?
                    int alt50=2;
                    try { dbg.enterSubRule(50);
                    try { dbg.enterDecision(50);

                    int LA50_0 = input.LA(1);

                    if ( ((LA50_0>=PLUS && LA50_0<=MINUS)) ) {
                        alt50=1;
                    }
                    } finally {dbg.exitDecision(50);}

                    switch (alt50) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:293:7: unary
                            {
                            dbg.location(293,7);
                            pushFollow(FOLLOW_unary_in_value1496);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(50);}

                    dbg.location(294,5);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:294:5: ( columnRef | nestedExpression )
                    int alt51=2;
                    try { dbg.enterSubRule(51);
                    try { dbg.enterDecision(51);

                    int LA51_0 = input.LA(1);

                    if ( (LA51_0==Identifier||LA51_0==QuotedIdentifier) ) {
                        alt51=1;
                    }
                    else if ( (LA51_0==LPAREN) ) {
                        alt51=2;
                    }
                    else {
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

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:294:7: columnRef
                            {
                            dbg.location(294,7);
                            pushFollow(FOLLOW_columnRef_in_value1507);
                            columnRef();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            dbg.enterAlt(2);

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:295:7: nestedExpression
                            {
                            dbg.location(295,7);
                            pushFollow(FOLLOW_nestedExpression_in_value1515);
                            nestedExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(51);}


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
        dbg.location(298, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:300:1: literal : ( ( unary )? Float | ( unary )? Integer | String | TRUE | FALSE | date );
    public final void literal() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "literal");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(300, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:301:3: ( ( unary )? Float | ( unary )? Integer | String | TRUE | FALSE | date )
            int alt55=6;
            try { dbg.enterDecision(55);

            switch ( input.LA(1) ) {
            case PLUS:
            case MINUS:
                {
                int LA55_1 = input.LA(2);

                if ( (LA55_1==Integer) ) {
                    alt55=2;
                }
                else if ( (LA55_1==Float) ) {
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
                break;
            case Float:
                {
                alt55=1;
                }
                break;
            case Integer:
                {
                alt55=2;
                }
                break;
            case String:
                {
                alt55=3;
                }
                break;
            case TRUE:
                {
                alt55=4;
                }
                break;
            case FALSE:
                {
                alt55=5;
                }
                break;
            case 114:
            case 115:
            case 116:
                {
                alt55=6;
                }
                break;
            default:
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:301:5: ( unary )? Float
                    {
                    dbg.location(301,5);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:301:5: ( unary )?
                    int alt53=2;
                    try { dbg.enterSubRule(53);
                    try { dbg.enterDecision(53);

                    int LA53_0 = input.LA(1);

                    if ( ((LA53_0>=PLUS && LA53_0<=MINUS)) ) {
                        alt53=1;
                    }
                    } finally {dbg.exitDecision(53);}

                    switch (alt53) {
                        case 1 :
                            dbg.enterAlt(1);

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:301:7: unary
                            {
                            dbg.location(301,7);
                            pushFollow(FOLLOW_unary_in_literal1539);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(53);}

                    dbg.location(301,16);
                    match(input,Float,FOLLOW_Float_in_literal1544); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:302:5: ( unary )? Integer
                    {
                    dbg.location(302,5);
                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:302:5: ( unary )?
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

                            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:302:7: unary
                            {
                            dbg.location(302,7);
                            pushFollow(FOLLOW_unary_in_literal1552);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }
                    } finally {dbg.exitSubRule(54);}

                    dbg.location(302,16);
                    match(input,Integer,FOLLOW_Integer_in_literal1557); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:303:5: String
                    {
                    dbg.location(303,5);
                    match(input,String,FOLLOW_String_in_literal1563); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    dbg.enterAlt(4);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:304:5: TRUE
                    {
                    dbg.location(304,5);
                    match(input,TRUE,FOLLOW_TRUE_in_literal1569); if (state.failed) return ;

                    }
                    break;
                case 5 :
                    dbg.enterAlt(5);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:305:5: FALSE
                    {
                    dbg.location(305,5);
                    match(input,FALSE,FOLLOW_FALSE_in_literal1575); if (state.failed) return ;

                    }
                    break;
                case 6 :
                    dbg.enterAlt(6);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:306:5: date
                    {
                    dbg.location(306,5);
                    pushFollow(FOLLOW_date_in_literal1581);
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
        dbg.location(307, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:309:1: date : ( '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' );
    public final void date() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "date");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(309, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:310:3: ( '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' )
            int alt56=3;
            try { dbg.enterDecision(56);

            switch ( input.LA(1) ) {
            case 114:
                {
                alt56=1;
                }
                break;
            case 115:
                {
                alt56=2;
                }
                break;
            case 116:
                {
                alt56=3;
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:310:5: '{d' Timestamp '}'
                    {
                    dbg.location(310,5);
                    match(input,114,FOLLOW_114_in_date1594); if (state.failed) return ;
                    dbg.location(310,10);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1596); if (state.failed) return ;
                    dbg.location(310,20);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1598); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:311:5: '{t' Timestamp '}'
                    {
                    dbg.location(311,5);
                    match(input,115,FOLLOW_115_in_date1605); if (state.failed) return ;
                    dbg.location(311,10);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1607); if (state.failed) return ;
                    dbg.location(311,20);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1609); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    dbg.enterAlt(3);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:312:5: '{ts' Timestamp '}'
                    {
                    dbg.location(312,5);
                    match(input,116,FOLLOW_116_in_date1616); if (state.failed) return ;
                    dbg.location(312,11);
                    match(input,Timestamp,FOLLOW_Timestamp_in_date1618); if (state.failed) return ;
                    dbg.location(312,21);
                    match(input,RCURLY,FOLLOW_RCURLY_in_date1620); if (state.failed) return ;

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
        dbg.location(313, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:315:1: unary : ( MINUS | PLUS );
    public final void unary() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "unary");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(315, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:316:3: ( MINUS | PLUS )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(316,3);
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
        dbg.location(318, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:337:1: tableRef : ( tableName | databaseName DOT tableName );
    public final void tableRef() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableRef");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(337, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:338:3: ( tableName | databaseName DOT tableName )
            int alt57=2;
            try { dbg.enterDecision(57);

            int LA57_0 = input.LA(1);

            if ( (LA57_0==Identifier||LA57_0==QuotedIdentifier) ) {
                int LA57_1 = input.LA(2);

                if ( (LA57_1==EOF||(LA57_1>=SEMI && LA57_1<=RPAREN)||(LA57_1>=SET && LA57_1<=COMMA)||LA57_1==VALUES||LA57_1==AS||LA57_1==Identifier||(LA57_1>=FROM && LA57_1<=NATURAL)||(LA57_1>=WHERE && LA57_1<=GROUP)||(LA57_1>=HAVING && LA57_1<=ORDER)) ) {
                    alt57=1;
                }
                else if ( (LA57_1==DOT) ) {
                    alt57=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 57, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else {
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

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:338:5: tableName
                    {
                    dbg.location(338,5);
                    pushFollow(FOLLOW_tableName_in_tableRef1675);
                    tableName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:339:5: databaseName DOT tableName
                    {
                    dbg.location(339,5);
                    pushFollow(FOLLOW_databaseName_in_tableRef1681);
                    databaseName();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(339,18);
                    match(input,DOT,FOLLOW_DOT_in_tableRef1683); if (state.failed) return ;
                    dbg.location(339,22);
                    pushFollow(FOLLOW_tableName_in_tableRef1685);
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
        dbg.location(340, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:342:1: columnRef : ( columnName | tableAlias DOT columnName );
    public final void columnRef() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnRef");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(342, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:343:3: ( columnName | tableAlias DOT columnName )
            int alt58=2;
            try { dbg.enterDecision(58);

            int LA58_0 = input.LA(1);

            if ( (LA58_0==Identifier) ) {
                int LA58_1 = input.LA(2);

                if ( (LA58_1==DOT) ) {
                    alt58=2;
                }
                else if ( (LA58_1==EOF||LA58_1==SEMI||LA58_1==RPAREN||LA58_1==ALL||(LA58_1>=COMMA && LA58_1<=INTO)||(LA58_1>=STAR && LA58_1<=AS)||LA58_1==Identifier||(LA58_1>=FROM && LA58_1<=NATURAL)||(LA58_1>=WHERE && LA58_1<=GROUP)||(LA58_1>=HAVING && LA58_1<=IS)||(LA58_1>=LIKE && LA58_1<=DIVIDE)) ) {
                    alt58=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 58, 1, input);

                    dbg.recognitionException(nvae);
                    throw nvae;
                }
            }
            else if ( (LA58_0==QuotedIdentifier) ) {
                alt58=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                dbg.recognitionException(nvae);
                throw nvae;
            }
            } finally {dbg.exitDecision(58);}

            switch (alt58) {
                case 1 :
                    dbg.enterAlt(1);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:343:5: columnName
                    {
                    dbg.location(343,5);
                    pushFollow(FOLLOW_columnName_in_columnRef1700);
                    columnName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    dbg.enterAlt(2);

                    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:344:5: tableAlias DOT columnName
                    {
                    dbg.location(344,5);
                    pushFollow(FOLLOW_tableAlias_in_columnRef1707);
                    tableAlias();

                    state._fsp--;
                    if (state.failed) return ;
                    dbg.location(344,16);
                    match(input,DOT,FOLLOW_DOT_in_columnRef1709); if (state.failed) return ;
                    dbg.location(344,20);
                    pushFollow(FOLLOW_columnName_in_columnRef1711);
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
        dbg.location(345, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:347:1: databaseName : ( Identifier | QuotedIdentifier );
    public final void databaseName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "databaseName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(347, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:348:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(348,3);
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
        dbg.location(350, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:352:1: tableName : ( Identifier | QuotedIdentifier );
    public final void tableName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(352, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:353:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:
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
            dbg.exitRule(getGrammarFileName(), "tableName");
            decRuleLevel();
            if ( getRuleLevel()==0 ) {dbg.terminate();}
        }

        return ;
    }
    // $ANTLR end "tableName"


    // $ANTLR start "tableAlias"
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:357:1: tableAlias : Identifier ;
    public final void tableAlias() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "tableAlias");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(357, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:358:3: ( Identifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:358:5: Identifier
            {
            dbg.location(358,5);
            match(input,Identifier,FOLLOW_Identifier_in_tableAlias1766); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        dbg.location(359, 3);

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
    // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:361:1: columnName : ( Identifier | QuotedIdentifier );
    public final void columnName() throws RecognitionException {
        try { dbg.enterRule(getGrammarFileName(), "columnName");
        if ( getRuleLevel()==0 ) {dbg.commence();}
        incRuleLevel();
        dbg.location(361, 1);

        try {
            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:362:3: ( Identifier | QuotedIdentifier )
            dbg.enterAlt(1);

            // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:
            {
            dbg.location(362,3);
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
        dbg.location(364, 3);

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
        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:72:7: ( joinList )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:72:7: joinList
        {
        dbg.location(72,7);
        pushFollow(FOLLOW_joinList_in_synpred13_GenericSQL216);
        joinList();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred13_GenericSQL

    // $ANTLR start synpred55_GenericSQL
    public final void synpred55_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:207:7: ( nestedCondition )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:207:7: nestedCondition
        {
        dbg.location(207,7);
        pushFollow(FOLLOW_nestedCondition_in_synpred55_GenericSQL1039);
        nestedCondition();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred55_GenericSQL

    // $ANTLR start synpred56_GenericSQL
    public final void synpred56_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:208:7: ( in )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:208:7: in
        {
        dbg.location(208,7);
        pushFollow(FOLLOW_in_in_synpred56_GenericSQL1047);
        in();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred56_GenericSQL

    // $ANTLR start synpred57_GenericSQL
    public final void synpred57_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:209:7: ( between )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:209:7: between
        {
        dbg.location(209,7);
        pushFollow(FOLLOW_between_in_synpred57_GenericSQL1055);
        between();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred57_GenericSQL

    // $ANTLR start synpred58_GenericSQL
    public final void synpred58_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:210:7: ( isNull )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:210:7: isNull
        {
        dbg.location(210,7);
        pushFollow(FOLLOW_isNull_in_synpred58_GenericSQL1063);
        isNull();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred58_GenericSQL

    // $ANTLR start synpred60_GenericSQL
    public final void synpred60_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:212:7: ( like )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:212:7: like
        {
        dbg.location(212,7);
        pushFollow(FOLLOW_like_in_synpred60_GenericSQL1079);
        like();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred60_GenericSQL

    // $ANTLR start synpred61_GenericSQL
    public final void synpred61_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:213:7: ( quantifier )
        dbg.enterAlt(1);

        // /Users/jasonosgood/Projects/Camper/fado/source/fado/parse/GenericSQL.g:213:7: quantifier
        {
        dbg.location(213,7);
        pushFollow(FOLLOW_quantifier_in_synpred61_GenericSQL1087);
        quantifier();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred61_GenericSQL

    // Delegated rules

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
    public final boolean synpred60_GenericSQL() {
        state.backtracking++;
        dbg.beginBacktrack(state.backtracking);
        int start = input.mark();
        try {
            synpred60_GenericSQL_fragment(); // can never throw exception
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
    protected DFA35 dfa35 = new DFA35(this);
    protected DFA41 dfa41 = new DFA41(this);
    static final String DFA10_eotS =
        "\12\uffff";
    static final String DFA10_eofS =
        "\1\7\11\uffff";
    static final String DFA10_minS =
        "\1\4\1\uffff\7\0\1\uffff";
    static final String DFA10_maxS =
        "\1\50\1\uffff\7\0\1\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\1\7\uffff\1\2";
    static final String DFA10_specialS =
        "\2\uffff\1\3\1\1\1\4\1\6\1\0\1\2\1\5\1\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\6\1\uffff\1\10\26\uffff\6\1\1\uffff\1\2\1\3\1\uffff\1\4\1"+
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
                        int LA10_6 = input.LA(1);

                         
                        int index10_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_6);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA10_3 = input.LA(1);

                         
                        int index10_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_3);
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
                        int LA10_4 = input.LA(1);

                         
                        int index10_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_4);
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
                        int LA10_5 = input.LA(1);

                         
                        int index10_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred13_GenericSQL()) ) {s = 1;}

                        else if ( (true) ) {s = 9;}

                         
                        input.seek(index10_5);
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
    static final String DFA35_eotS =
        "\13\uffff";
    static final String DFA35_eofS =
        "\13\uffff";
    static final String DFA35_minS =
        "\1\35\2\uffff\2\35\6\uffff";
    static final String DFA35_maxS =
        "\1\42\2\uffff\2\40\6\uffff";
    static final String DFA35_acceptS =
        "\1\uffff\1\1\1\2\2\uffff\1\7\1\10\1\3\1\4\1\5\1\6";
    static final String DFA35_specialS =
        "\13\uffff}>";
    static final String[] DFA35_transitionS = {
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

    static final short[] DFA35_eot = DFA.unpackEncodedString(DFA35_eotS);
    static final short[] DFA35_eof = DFA.unpackEncodedString(DFA35_eofS);
    static final char[] DFA35_min = DFA.unpackEncodedStringToUnsignedChars(DFA35_minS);
    static final char[] DFA35_max = DFA.unpackEncodedStringToUnsignedChars(DFA35_maxS);
    static final short[] DFA35_accept = DFA.unpackEncodedString(DFA35_acceptS);
    static final short[] DFA35_special = DFA.unpackEncodedString(DFA35_specialS);
    static final short[][] DFA35_transition;

    static {
        int numStates = DFA35_transitionS.length;
        DFA35_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA35_transition[i] = DFA.unpackEncodedString(DFA35_transitionS[i]);
        }
    }

    class DFA35 extends DFA {

        public DFA35(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 35;
            this.eot = DFA35_eot;
            this.eof = DFA35_eof;
            this.min = DFA35_min;
            this.max = DFA35_max;
            this.accept = DFA35_accept;
            this.special = DFA35_special;
            this.transition = DFA35_transition;
        }
        public String getDescription() {
            return "153:5: ( JOIN | INNER JOIN | LEFT JOIN | LEFT OUTER JOIN | RIGHT JOIN | RIGHT OUTER JOIN | OUTER JOIN | NATURAL JOIN )";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
    }
    static final String DFA41_eotS =
        "\25\uffff";
    static final String DFA41_eofS =
        "\25\uffff";
    static final String DFA41_minS =
        "\1\5\14\0\10\uffff";
    static final String DFA41_maxS =
        "\1\164\14\0\10\uffff";
    static final String DFA41_acceptS =
        "\15\uffff\1\5\1\1\1\2\1\3\1\4\1\6\1\7\1\10";
    static final String DFA41_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\10"+
        "\uffff}>";
    static final String[] DFA41_transitionS = {
            "\1\1\6\uffff\1\4\13\uffff\1\13\31\uffff\1\15\11\uffff\2\2\1"+
            "\uffff\1\3\1\5\1\6\1\7\1\uffff\1\14\55\uffff\1\10\1\11\1\12",
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

    static final short[] DFA41_eot = DFA.unpackEncodedString(DFA41_eotS);
    static final short[] DFA41_eof = DFA.unpackEncodedString(DFA41_eofS);
    static final char[] DFA41_min = DFA.unpackEncodedStringToUnsignedChars(DFA41_minS);
    static final char[] DFA41_max = DFA.unpackEncodedStringToUnsignedChars(DFA41_maxS);
    static final short[] DFA41_accept = DFA.unpackEncodedString(DFA41_acceptS);
    static final short[] DFA41_special = DFA.unpackEncodedString(DFA41_specialS);
    static final short[][] DFA41_transition;

    static {
        int numStates = DFA41_transitionS.length;
        DFA41_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA41_transition[i] = DFA.unpackEncodedString(DFA41_transitionS[i]);
        }
    }

    class DFA41 extends DFA {

        public DFA41(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 41;
            this.eot = DFA41_eot;
            this.eof = DFA41_eof;
            this.min = DFA41_min;
            this.max = DFA41_max;
            this.accept = DFA41_accept;
            this.special = DFA41_special;
            this.transition = DFA41_transition;
        }
        public String getDescription() {
            return "207:5: ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )";
        }
        public void error(NoViableAltException nvae) {
            dbg.recognitionException(nvae);
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA41_1 = input.LA(1);

                         
                        int index41_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred55_GenericSQL()) ) {s = 14;}

                        else if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA41_2 = input.LA(1);

                         
                        int index41_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA41_3 = input.LA(1);

                         
                        int index41_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA41_4 = input.LA(1);

                         
                        int index41_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA41_5 = input.LA(1);

                         
                        int index41_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA41_6 = input.LA(1);

                         
                        int index41_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA41_7 = input.LA(1);

                         
                        int index41_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA41_8 = input.LA(1);

                         
                        int index41_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_8);
                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA41_9 = input.LA(1);

                         
                        int index41_9 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_9);
                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA41_10 = input.LA(1);

                         
                        int index41_10 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_10);
                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA41_11 = input.LA(1);

                         
                        int index41_11 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_11);
                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA41_12 = input.LA(1);

                         
                        int index41_12 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred56_GenericSQL()) ) {s = 15;}

                        else if ( (synpred57_GenericSQL()) ) {s = 16;}

                        else if ( (synpred58_GenericSQL()) ) {s = 17;}

                        else if ( (synpred60_GenericSQL()) ) {s = 18;}

                        else if ( (synpred61_GenericSQL()) ) {s = 19;}

                        else if ( (true) ) {s = 20;}

                         
                        input.seek(index41_12);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 41, _s, input);
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
    public static final BitSet FOLLOW_update_in_statement96 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_statement100 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_statement105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_subSelect121 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_subSelect127 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_select_in_subSelect129 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_subSelect131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_in_select146 = new BitSet(new long[]{0xB00000000F201F20L,0x001C000000000017L});
    public static final BitSet FOLLOW_set_in_select152 = new BitSet(new long[]{0xB00000000F201F20L,0x001C000000000017L});
    public static final BitSet FOLLOW_TOP_in_select173 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Integer_in_select175 = new BitSet(new long[]{0xB00000000F203F20L,0x001C000000000017L});
    public static final BitSet FOLLOW_PERCENT_in_select179 = new BitSet(new long[]{0xB00000000F201F20L,0x001C000000000017L});
    public static final BitSet FOLLOW_itemList_in_select191 = new BitSet(new long[]{0x0000000010080000L});
    public static final BitSet FOLLOW_into_in_select199 = new BitSet(new long[]{0x0000000010080000L});
    public static final BitSet FOLLOW_from_in_select208 = new BitSet(new long[]{0x000001B7E0000000L});
    public static final BitSet FOLLOW_joinList_in_select216 = new BitSet(new long[]{0x000001B000000002L});
    public static final BitSet FOLLOW_where_in_select227 = new BitSet(new long[]{0x000001A000000002L});
    public static final BitSet FOLLOW_groupBy_in_select238 = new BitSet(new long[]{0x0000018000000002L});
    public static final BitSet FOLLOW_having_in_select249 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_orderBy_in_select260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INSERT_in_insert278 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_into_in_insert280 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_columnList_in_insert284 = new BitSet(new long[]{0x0000000000100020L});
    public static final BitSet FOLLOW_values_in_insert293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_UPDATE_in_update313 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_tableRef_in_update315 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_SET_in_update317 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_setter_in_update319 = new BitSet(new long[]{0x0000001000020002L});
    public static final BitSet FOLLOW_COMMA_in_update323 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_setter_in_update325 = new BitSet(new long[]{0x0000001000020002L});
    public static final BitSet FOLLOW_where_in_update336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_columnName_in_setter354 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_EQ_in_setter356 = new BitSet(new long[]{0xB000000000001000L,0x001C000000000007L});
    public static final BitSet FOLLOW_literal_in_setter358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTO_in_into373 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_tableRef_in_into375 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_into379 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_tableRef_in_into381 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_LPAREN_in_columnList397 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_columnName_in_columnList399 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_COMMA_in_columnList403 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_columnName_in_columnList405 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_RPAREN_in_columnList410 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VALUES_in_values425 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_values427 = new BitSet(new long[]{0xB000000000001000L,0x001C000000000007L});
    public static final BitSet FOLLOW_literal_in_values429 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_COMMA_in_values433 = new BitSet(new long[]{0xB000000000001000L,0x001C000000000007L});
    public static final BitSet FOLLOW_literal_in_values435 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_RPAREN_in_values440 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_itemList455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_item_in_itemList461 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_itemList465 = new BitSet(new long[]{0xB00000000F201F20L,0x001C000000000017L});
    public static final BitSet FOLLOW_item_in_itemList467 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_value_in_item485 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_item491 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_item496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_allColumns_in_item505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_in_item511 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_item515 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_item518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_allColumns535 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_DOT_in_allColumns537 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_STAR_in_allColumns539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_alias552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_functionName_in_function567 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_function569 = new BitSet(new long[]{0xB000000001001060L,0x001C000000000017L});
    public static final BitSet FOLLOW_value_in_function573 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_COMMA_in_function577 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_value_in_function579 = new BitSet(new long[]{0x0000000000020040L});
    public static final BitSet FOLLOW_RPAREN_in_function587 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_functionName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FROM_in_from629 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_fromItem_in_from631 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_from635 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_fromItem_in_from637 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_LPAREN_in_fromItem659 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_fromItem661 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_fromItem663 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_tableRef_in_fromItem674 = new BitSet(new long[]{0x0000000001400002L});
    public static final BitSet FOLLOW_AS_in_fromItem691 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_fromItem696 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_join_in_joinList714 = new BitSet(new long[]{0x00000007E0000002L});
    public static final BitSet FOLLOW_JOIN_in_join739 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_INNER_in_join747 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_JOIN_in_join749 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_LEFT_in_join757 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_JOIN_in_join759 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_LEFT_in_join767 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_OUTER_in_join769 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_JOIN_in_join771 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_RIGHT_in_join779 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_JOIN_in_join781 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_RIGHT_in_join790 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_OUTER_in_join792 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_JOIN_in_join794 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_OUTER_in_join802 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_JOIN_in_join804 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_NATURAL_in_join813 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_JOIN_in_join815 = new BitSet(new long[]{0x0000000001000020L,0x0000000000000010L});
    public static final BitSet FOLLOW_fromItem_in_join826 = new BitSet(new long[]{0x0000000001400000L});
    public static final BitSet FOLLOW_alias_in_join828 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_ON_in_join836 = new BitSet(new long[]{0xB004200001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_conditionList_in_join838 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHERE_in_where859 = new BitSet(new long[]{0xB004200001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_conditionList_in_where861 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GROUP_in_groupBy876 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_BY_in_groupBy878 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_columnRef_in_groupBy880 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_groupBy884 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_columnRef_in_groupBy886 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_HAVING_in_having904 = new BitSet(new long[]{0xB004200001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_conditionList_in_having906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORDER_in_orderBy921 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_BY_in_orderBy923 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy925 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_orderBy929 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy931 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_columnRef_in_orderByItem949 = new BitSet(new long[]{0x0000060000000002L});
    public static final BitSet FOLLOW_set_in_orderByItem951 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedCondition975 = new BitSet(new long[]{0xB004200001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_conditionList_in_nestedCondition977 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedCondition979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditionList994 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_set_in_conditionList998 = new BitSet(new long[]{0xB004200001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_condition_in_conditionList1008 = new BitSet(new long[]{0x0000180000000002L});
    public static final BitSet FOLLOW_NOT_in_condition1028 = new BitSet(new long[]{0xB004200001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_nestedCondition_in_condition1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_condition1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_condition1055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_condition1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exists_in_condition1071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_condition1079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_condition1087 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_condition1094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_in1113 = new BitSet(new long[]{0x0000600000000000L});
    public static final BitSet FOLLOW_NOT_in_in1117 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_IN_in_in1122 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_in1124 = new BitSet(new long[]{0xB0000000010010A0L,0x001C000000000017L});
    public static final BitSet FOLLOW_subSelect_in_in1128 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_expressionList_in_in1132 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_in1136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_between1151 = new BitSet(new long[]{0x0000A00000000000L});
    public static final BitSet FOLLOW_NOT_in_between1155 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_BETWEEN_in_between1160 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_expression_in_between1162 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_AND_in_between1164 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_expression_in_between1166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_isNull1181 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_IS_in_isNull1183 = new BitSet(new long[]{0x0002200000000000L});
    public static final BitSet FOLLOW_NOT_in_isNull1187 = new BitSet(new long[]{0x0002000000000000L});
    public static final BitSet FOLLOW_NULL_in_isNull1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXISTS_in_exists1207 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_expression_in_exists1209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_like1228 = new BitSet(new long[]{0x0008200000000000L});
    public static final BitSet FOLLOW_NOT_in_like1232 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_LIKE_in_like1237 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_expression_in_like1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_comparison1257 = new BitSet(new long[]{0x03F0000000040000L});
    public static final BitSet FOLLOW_comparator_in_comparison1259 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_expression_in_comparison1261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_comparator0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_quantifier1326 = new BitSet(new long[]{0x0C00000000000100L});
    public static final BitSet FOLLOW_set_in_quantifier1328 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_quantifier1342 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_quantifier1344 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_quantifier1346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList1361 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_COMMA_in_expressionList1365 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_expression_in_expressionList1367 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedExpression1383 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_expression_in_nestedExpression1385 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedExpression1387 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiply_in_expression1402 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_set_in_expression1406 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_multiply_in_expression1416 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_value_in_multiply1433 = new BitSet(new long[]{0x4000000000200002L});
    public static final BitSet FOLLOW_set_in_multiply1437 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_value_in_multiply1447 = new BitSet(new long[]{0x4000000000200002L});
    public static final BitSet FOLLOW_literal_in_value1486 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_value1496 = new BitSet(new long[]{0xB000000001001020L,0x001C000000000017L});
    public static final BitSet FOLLOW_columnRef_in_value1507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedExpression_in_value1515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_literal1539 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_Float_in_literal1544 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_literal1552 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Integer_in_literal1557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_String_in_literal1563 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_literal1569 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FALSE_in_literal1575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_date_in_literal1581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_114_in_date1594 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_Timestamp_in_date1596 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_115_in_date1605 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_Timestamp_in_date1607 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1609 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_116_in_date1616 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_Timestamp_in_date1618 = new BitSet(new long[]{0x0000000000000000L,0x0000020000000000L});
    public static final BitSet FOLLOW_RCURLY_in_date1620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_unary0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableName_in_tableRef1675 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_databaseName_in_tableRef1681 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_DOT_in_tableRef1683 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_tableName_in_tableRef1685 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_columnName_in_columnRef1700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_columnRef1707 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_DOT_in_columnRef1709 = new BitSet(new long[]{0x0000000001000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_columnName_in_columnRef1711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_databaseName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_tableName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_tableAlias1766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_columnName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_joinList_in_synpred13_GenericSQL216 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedCondition_in_synpred55_GenericSQL1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_synpred56_GenericSQL1047 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_synpred57_GenericSQL1055 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_synpred58_GenericSQL1063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_synpred60_GenericSQL1079 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_synpred61_GenericSQL1087 = new BitSet(new long[]{0x0000000000000002L});

}