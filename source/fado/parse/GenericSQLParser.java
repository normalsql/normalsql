// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g 2011-03-09 18:39:47
 
package fado.parse;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public class GenericSQLParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "SEMI", "LPAREN", "RPAREN", "SELECT", "ALL", "DISTINCT", "UNIQUE", "TOP", "Integer", "PERCENT", "STAR", "COMMA", "AS", "DOT", "Identifier", "INTO", "FROM", "INNER", "LEFT", "RIGHT", "FULL", "OUTER", "JOIN", "ON", "USING", "WHERE", "GROUP", "BY", "HAVING", "ORDER", "ASC", "DESC", "OR", "AND", "NOT", "IN", "BETWEEN", "IS", "NULL", "EXISTS", "LIKE", "EQ", "NEQ1", "NEQ2", "LTE", "LT", "GTE", "GT", "ANY", "SOME", "PLUS", "MINUS", "DIVIDE", "Float", "String", "Timestamp", "QuotedIdentifier", "A", "L", "N", "D", "Y", "S", "C", "B", "E", "T", "W", "CASE", "DELETE", "I", "ELSE", "END", "X", "F", "R", "O", "M", "U", "G", "P", "H", "V", "INSERT", "J", "K", "THEN", "TRUE", "UNION", "Q", "UPDATE", "WHEN", "Z", "LCURLY", "RCURLY", "STRCAT", "QUESTION", "COLON", "MOD", "Digit", "Exponent", "Comment", "Whitespace", "'{d'", "'{t'", "'{ts'"
    };
    public static final int LT=49;
    public static final int STAR=14;
    public static final int MOD=102;
    public static final int CASE=72;
    public static final int NOT=38;
    public static final int EOF=-1;
    public static final int Identifier=18;
    public static final int RPAREN=6;
    public static final int FULL=24;
    public static final int INSERT=87;
    public static final int USING=28;
    public static final int Comment=105;
    public static final int EQ=45;
    public static final int SELECT=7;
    public static final int INTO=19;
    public static final int DIVIDE=56;
    public static final int D=64;
    public static final int E=69;
    public static final int UNIQUE=10;
    public static final int F=78;
    public static final int G=83;
    public static final int A=61;
    public static final int B=68;
    public static final int ASC=34;
    public static final int C=67;
    public static final int L=62;
    public static final int M=81;
    public static final int N=63;
    public static final int O=80;
    public static final int H=85;
    public static final int NULL=42;
    public static final int I=74;
    public static final int ELSE=75;
    public static final int J=88;
    public static final int K=89;
    public static final int U=82;
    public static final int ON=27;
    public static final int T=70;
    public static final int W=71;
    public static final int LCURLY=97;
    public static final int V=86;
    public static final int Q=93;
    public static final int DELETE=73;
    public static final int P=84;
    public static final int S=66;
    public static final int R=79;
    public static final int Y=65;
    public static final int X=77;
    public static final int Z=96;
    public static final int Float=57;
    public static final int GROUP=30;
    public static final int OR=36;
    public static final int Timestamp=59;
    public static final int GT=51;
    public static final int FROM=20;
    public static final int END=76;
    public static final int DISTINCT=9;
    public static final int NEQ1=46;
    public static final int WHERE=29;
    public static final int INNER=21;
    public static final int ORDER=33;
    public static final int NEQ2=47;
    public static final int GTE=50;
    public static final int UPDATE=94;
    public static final int Exponent=104;
    public static final int AND=37;
    public static final int LTE=48;
    public static final int LPAREN=5;
    public static final int AS=16;
    public static final int THEN=90;
    public static final int IN=39;
    public static final int T__107=107;
    public static final int T__108=108;
    public static final int COMMA=15;
    public static final int T__109=109;
    public static final int IS=41;
    public static final int LEFT=22;
    public static final int SOME=53;
    public static final int ALL=8;
    public static final int PLUS=54;
    public static final int EXISTS=43;
    public static final int String=58;
    public static final int DOT=17;
    public static final int Whitespace=106;
    public static final int STRCAT=99;
    public static final int LIKE=44;
    public static final int OUTER=25;
    public static final int BY=31;
    public static final int PERCENT=13;
    public static final int RIGHT=23;
    public static final int HAVING=32;
    public static final int MINUS=55;
    public static final int Digit=103;
    public static final int QuotedIdentifier=60;
    public static final int TRUE=91;
    public static final int SEMI=4;
    public static final int JOIN=26;
    public static final int UNION=92;
    public static final int COLON=101;
    public static final int ANY=52;
    public static final int QUESTION=100;
    public static final int WHEN=95;
    public static final int RCURLY=98;
    public static final int DESC=35;
    public static final int TOP=11;
    public static final int BETWEEN=40;
    public static final int Integer=12;

    // delegates
    // delegators


        public GenericSQLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public GenericSQLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return GenericSQLParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g"; }





    // $ANTLR start "statement"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:55:1: statement : select ( SEMI )? EOF ;
    public final void statement() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:3: ( select ( SEMI )? EOF )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:5: select ( SEMI )? EOF
            {
            pushFollow(FOLLOW_select_in_statement69);
            select();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:12: ( SEMI )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==SEMI) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:56:14: SEMI
                    {
                    match(input,SEMI,FOLLOW_SEMI_in_statement73); if (state.failed) return ;

                    }
                    break;

            }

            match(input,EOF,FOLLOW_EOF_in_statement78); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "statement"


    // $ANTLR start "subSelect"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:62:1: subSelect : ( select | LPAREN select RPAREN );
    public final void subSelect() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:63:3: ( select | LPAREN select RPAREN )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==SELECT) ) {
                alt2=1;
            }
            else if ( (LA2_0==LPAREN) ) {
                alt2=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:63:5: select
                    {
                    pushFollow(FOLLOW_select_in_subSelect96);
                    select();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:64:5: LPAREN select RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_subSelect102); if (state.failed) return ;
                    pushFollow(FOLLOW_select_in_subSelect104);
                    select();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,RPAREN,FOLLOW_RPAREN_in_subSelect106); if (state.failed) return ;

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
        return ;
    }
    // $ANTLR end "subSelect"


    // $ANTLR start "select"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:67:1: select : SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( where )? ( groupBy )? ( having )? ( orderBy )? ;
    public final void select() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:68:3: ( SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( where )? ( groupBy )? ( having )? ( orderBy )? )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:68:5: SELECT ( ALL | DISTINCT | UNIQUE )? ( TOP Integer ( PERCENT )? )? itemList ( into )? from ( where )? ( groupBy )? ( having )? ( orderBy )?
            {
            match(input,SELECT,FOLLOW_SELECT_in_select121); if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:69:5: ( ALL | DISTINCT | UNIQUE )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( ((LA3_0>=ALL && LA3_0<=UNIQUE)) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
                    {
                    if ( (input.LA(1)>=ALL && input.LA(1)<=UNIQUE) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:5: ( TOP Integer ( PERCENT )? )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==TOP) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:7: TOP Integer ( PERCENT )?
                    {
                    match(input,TOP,FOLLOW_TOP_in_select148); if (state.failed) return ;
                    match(input,Integer,FOLLOW_Integer_in_select150); if (state.failed) return ;
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:19: ( PERCENT )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==PERCENT) ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:70:21: PERCENT
                            {
                            match(input,PERCENT,FOLLOW_PERCENT_in_select154); if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

            }

            pushFollow(FOLLOW_itemList_in_select166);
            itemList();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:5: ( into )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==INTO) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:72:7: into
                    {
                    pushFollow(FOLLOW_into_in_select174);
                    into();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            pushFollow(FOLLOW_from_in_select183);
            from();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:75:5: ( where )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==WHERE) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:75:7: where
                    {
                    pushFollow(FOLLOW_where_in_select192);
                    where();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:76:5: ( groupBy )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==GROUP) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:76:7: groupBy
                    {
                    pushFollow(FOLLOW_groupBy_in_select203);
                    groupBy();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:77:5: ( having )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==HAVING) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:77:7: having
                    {
                    pushFollow(FOLLOW_having_in_select214);
                    having();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:78:5: ( orderBy )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==ORDER) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:78:7: orderBy
                    {
                    pushFollow(FOLLOW_orderBy_in_select225);
                    orderBy();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "select"


    // $ANTLR start "itemList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:81:1: itemList : ( STAR | item ( COMMA item )* );
    public final void itemList() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:82:3: ( STAR | item ( COMMA item )* )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==STAR) ) {
                alt12=1;
            }
            else if ( (LA12_0==LPAREN||LA12_0==Integer||LA12_0==Identifier||(LA12_0>=PLUS && LA12_0<=MINUS)||(LA12_0>=Float && LA12_0<=String)||LA12_0==QuotedIdentifier||(LA12_0>=107 && LA12_0<=109)) ) {
                alt12=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:82:5: STAR
                    {
                    match(input,STAR,FOLLOW_STAR_in_itemList241); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:83:5: item ( COMMA item )*
                    {
                    pushFollow(FOLLOW_item_in_itemList247);
                    item();

                    state._fsp--;
                    if (state.failed) return ;
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:83:10: ( COMMA item )*
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==COMMA) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:83:12: COMMA item
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_itemList251); if (state.failed) return ;
                    	    pushFollow(FOLLOW_item_in_itemList253);
                    	    item();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);


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
        return ;
    }
    // $ANTLR end "itemList"


    // $ANTLR start "item"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:86:1: item : ( value ( ( AS )? alias )? | allColumns );
    public final void item() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:3: ( value ( ( AS )? alias )? | allColumns )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==LPAREN||LA15_0==Integer||(LA15_0>=PLUS && LA15_0<=MINUS)||(LA15_0>=Float && LA15_0<=String)||LA15_0==QuotedIdentifier||(LA15_0>=107 && LA15_0<=109)) ) {
                alt15=1;
            }
            else if ( (LA15_0==Identifier) ) {
                int LA15_2 = input.LA(2);

                if ( (LA15_2==DOT) ) {
                    int LA15_3 = input.LA(3);

                    if ( (LA15_3==STAR) ) {
                        alt15=2;
                    }
                    else if ( (LA15_3==Identifier||LA15_3==QuotedIdentifier) ) {
                        alt15=1;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 3, input);

                        throw nvae;
                    }
                }
                else if ( (LA15_2==EOF||(LA15_2>=COMMA && LA15_2<=AS)||(LA15_2>=Identifier && LA15_2<=FROM)) ) {
                    alt15=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 2, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:5: value ( ( AS )? alias )?
                    {
                    pushFollow(FOLLOW_value_in_item271);
                    value();

                    state._fsp--;
                    if (state.failed) return ;
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:11: ( ( AS )? alias )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==AS||LA14_0==Identifier) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:13: ( AS )? alias
                            {
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:13: ( AS )?
                            int alt13=2;
                            int LA13_0 = input.LA(1);

                            if ( (LA13_0==AS) ) {
                                alt13=1;
                            }
                            switch (alt13) {
                                case 1 :
                                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:87:15: AS
                                    {
                                    match(input,AS,FOLLOW_AS_in_item277); if (state.failed) return ;

                                    }
                                    break;

                            }

                            pushFollow(FOLLOW_alias_in_item282);
                            alias();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:88:5: allColumns
                    {
                    pushFollow(FOLLOW_allColumns_in_item291);
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
        return ;
    }
    // $ANTLR end "item"


    // $ANTLR start "allColumns"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:91:1: allColumns : tableAlias DOT STAR ;
    public final void allColumns() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:92:3: ( tableAlias DOT STAR )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:92:5: tableAlias DOT STAR
            {
            pushFollow(FOLLOW_tableAlias_in_allColumns305);
            tableAlias();

            state._fsp--;
            if (state.failed) return ;
            match(input,DOT,FOLLOW_DOT_in_allColumns307); if (state.failed) return ;
            match(input,STAR,FOLLOW_STAR_in_allColumns309); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "allColumns"


    // $ANTLR start "alias"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:95:1: alias : Identifier ;
    public final void alias() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:96:3: ( Identifier )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:96:5: Identifier
            {
            match(input,Identifier,FOLLOW_Identifier_in_alias322); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "alias"


    // $ANTLR start "into"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:99:1: into : INTO tableRef ( COMMA tableRef )* ;
    public final void into() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:100:3: ( INTO tableRef ( COMMA tableRef )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:100:5: INTO tableRef ( COMMA tableRef )*
            {
            match(input,INTO,FOLLOW_INTO_in_into337); if (state.failed) return ;
            pushFollow(FOLLOW_tableRef_in_into339);
            tableRef();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:100:19: ( COMMA tableRef )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==COMMA) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:100:21: COMMA tableRef
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_into343); if (state.failed) return ;
            	    pushFollow(FOLLOW_tableRef_in_into345);
            	    tableRef();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "into"


    // $ANTLR start "from"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:103:1: from : FROM fromItem ( COMMA fromItem )* ;
    public final void from() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:104:3: ( FROM fromItem ( COMMA fromItem )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:104:5: FROM fromItem ( COMMA fromItem )*
            {
            match(input,FROM,FOLLOW_FROM_in_from363); if (state.failed) return ;
            pushFollow(FOLLOW_fromItem_in_from365);
            fromItem();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:104:19: ( COMMA fromItem )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==COMMA) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:104:21: COMMA fromItem
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_from369); if (state.failed) return ;
            	    pushFollow(FOLLOW_fromItem_in_from371);
            	    fromItem();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "from"


    // $ANTLR start "fromItem"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:107:1: fromItem : ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? ;
    public final void fromItem() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:108:3: ( ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )? )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:108:5: ( ( LPAREN subSelect RPAREN ) | tableRef ) ( ( AS )? alias )?
            {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:108:5: ( ( LPAREN subSelect RPAREN ) | tableRef )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==LPAREN) ) {
                alt18=1;
            }
            else if ( (LA18_0==Identifier||LA18_0==QuotedIdentifier) ) {
                alt18=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:108:7: ( LPAREN subSelect RPAREN )
                    {
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:108:7: ( LPAREN subSelect RPAREN )
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:108:9: LPAREN subSelect RPAREN
                    {
                    match(input,LPAREN,FOLLOW_LPAREN_in_fromItem393); if (state.failed) return ;
                    pushFollow(FOLLOW_subSelect_in_fromItem395);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,RPAREN,FOLLOW_RPAREN_in_fromItem397); if (state.failed) return ;

                    }


                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:109:7: tableRef
                    {
                    pushFollow(FOLLOW_tableRef_in_fromItem408);
                    tableRef();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:111:5: ( ( AS )? alias )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==AS||LA20_0==Identifier) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:111:7: ( AS )? alias
                    {
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:111:7: ( AS )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0==AS) ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:111:9: AS
                            {
                            match(input,AS,FOLLOW_AS_in_fromItem425); if (state.failed) return ;

                            }
                            break;

                    }

                    pushFollow(FOLLOW_alias_in_fromItem430);
                    alias();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "fromItem"


    // $ANTLR start "joinList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:114:1: joinList : ( join )* ;
    public final void joinList() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:3: ( ( join )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:5: ( join )*
            {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:5: ( join )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=INNER && LA21_0<=FULL)||LA21_0==JOIN) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:115:7: join
            	    {
            	    pushFollow(FOLLOW_join_in_joinList450);
            	    join();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "joinList"


    // $ANTLR start "join"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:118:1: join : ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )? JOIN ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )? ;
    public final void join() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:119:3: ( ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )? JOIN ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )? )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:119:5: ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )? JOIN ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )?
            {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:119:5: ( INNER | ( LEFT | RIGHT | FULL ) ( OUTER )? )?
            int alt23=3;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==INNER) ) {
                alt23=1;
            }
            else if ( ((LA23_0>=LEFT && LA23_0<=FULL)) ) {
                alt23=2;
            }
            switch (alt23) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:119:7: INNER
                    {
                    match(input,INNER,FOLLOW_INNER_in_join470); if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:120:7: ( LEFT | RIGHT | FULL ) ( OUTER )?
                    {
                    if ( (input.LA(1)>=LEFT && input.LA(1)<=FULL) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:120:31: ( OUTER )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==OUTER) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:120:33: OUTER
                            {
                            match(input,OUTER,FOLLOW_OUTER_in_join494); if (state.failed) return ;

                            }
                            break;

                    }


                    }
                    break;

            }

            match(input,JOIN,FOLLOW_JOIN_in_join507); if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:122:5: ( ON conditionList | USING LPAREN columnRef ( COMMA columnRef )* )?
            int alt25=3;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==ON) ) {
                alt25=1;
            }
            else if ( (LA25_0==USING) ) {
                alt25=2;
            }
            switch (alt25) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:122:7: ON conditionList
                    {
                    match(input,ON,FOLLOW_ON_in_join515); if (state.failed) return ;
                    pushFollow(FOLLOW_conditionList_in_join517);
                    conditionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:123:7: USING LPAREN columnRef ( COMMA columnRef )*
                    {
                    match(input,USING,FOLLOW_USING_in_join525); if (state.failed) return ;
                    match(input,LPAREN,FOLLOW_LPAREN_in_join527); if (state.failed) return ;
                    pushFollow(FOLLOW_columnRef_in_join529);
                    columnRef();

                    state._fsp--;
                    if (state.failed) return ;
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:123:30: ( COMMA columnRef )*
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==COMMA) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:123:32: COMMA columnRef
                    	    {
                    	    match(input,COMMA,FOLLOW_COMMA_in_join533); if (state.failed) return ;
                    	    pushFollow(FOLLOW_columnRef_in_join535);
                    	    columnRef();

                    	    state._fsp--;
                    	    if (state.failed) return ;

                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "join"


    // $ANTLR start "where"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:133:1: where : WHERE conditionList ;
    public final void where() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:134:3: ( WHERE conditionList )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:134:5: WHERE conditionList
            {
            match(input,WHERE,FOLLOW_WHERE_in_where566); if (state.failed) return ;
            pushFollow(FOLLOW_conditionList_in_where568);
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
        return ;
    }
    // $ANTLR end "where"


    // $ANTLR start "groupBy"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:137:1: groupBy : GROUP BY columnRef ( COMMA columnRef )* ;
    public final void groupBy() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:138:3: ( GROUP BY columnRef ( COMMA columnRef )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:138:5: GROUP BY columnRef ( COMMA columnRef )*
            {
            match(input,GROUP,FOLLOW_GROUP_in_groupBy583); if (state.failed) return ;
            match(input,BY,FOLLOW_BY_in_groupBy585); if (state.failed) return ;
            pushFollow(FOLLOW_columnRef_in_groupBy587);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:138:24: ( COMMA columnRef )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==COMMA) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:138:26: COMMA columnRef
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_groupBy591); if (state.failed) return ;
            	    pushFollow(FOLLOW_columnRef_in_groupBy593);
            	    columnRef();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "groupBy"


    // $ANTLR start "having"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:141:1: having : HAVING conditionList ;
    public final void having() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:142:3: ( HAVING conditionList )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:142:5: HAVING conditionList
            {
            match(input,HAVING,FOLLOW_HAVING_in_having611); if (state.failed) return ;
            pushFollow(FOLLOW_conditionList_in_having613);
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
        return ;
    }
    // $ANTLR end "having"


    // $ANTLR start "orderBy"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:145:1: orderBy : ORDER BY orderByItem ( COMMA orderByItem )* ;
    public final void orderBy() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:146:3: ( ORDER BY orderByItem ( COMMA orderByItem )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:146:6: ORDER BY orderByItem ( COMMA orderByItem )*
            {
            match(input,ORDER,FOLLOW_ORDER_in_orderBy629); if (state.failed) return ;
            match(input,BY,FOLLOW_BY_in_orderBy631); if (state.failed) return ;
            pushFollow(FOLLOW_orderByItem_in_orderBy633);
            orderByItem();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:146:27: ( COMMA orderByItem )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( (LA27_0==COMMA) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:146:29: COMMA orderByItem
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_orderBy637); if (state.failed) return ;
            	    pushFollow(FOLLOW_orderByItem_in_orderBy639);
            	    orderByItem();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "orderBy"


    // $ANTLR start "orderByItem"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:149:1: orderByItem : columnRef ( ASC | DESC )? ;
    public final void orderByItem() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:150:3: ( columnRef ( ASC | DESC )? )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:150:5: columnRef ( ASC | DESC )?
            {
            pushFollow(FOLLOW_columnRef_in_orderByItem657);
            columnRef();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:150:15: ( ASC | DESC )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=ASC && LA28_0<=DESC)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
                    {
                    if ( (input.LA(1)>=ASC && input.LA(1)<=DESC) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "orderByItem"


    // $ANTLR start "nestedCondition"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:153:1: nestedCondition : LPAREN conditionList RPAREN ;
    public final void nestedCondition() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:154:3: ( LPAREN conditionList RPAREN )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:154:5: LPAREN conditionList RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedCondition683); if (state.failed) return ;
            pushFollow(FOLLOW_conditionList_in_nestedCondition685);
            conditionList();

            state._fsp--;
            if (state.failed) return ;
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedCondition687); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "nestedCondition"


    // $ANTLR start "conditionList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:157:1: conditionList : condition ( ( OR | AND ) condition )* ;
    public final void conditionList() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:158:3: ( condition ( ( OR | AND ) condition )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:158:5: condition ( ( OR | AND ) condition )*
            {
            pushFollow(FOLLOW_condition_in_conditionList702);
            condition();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:158:15: ( ( OR | AND ) condition )*
            loop29:
            do {
                int alt29=2;
                int LA29_0 = input.LA(1);

                if ( ((LA29_0>=OR && LA29_0<=AND)) ) {
                    alt29=1;
                }


                switch (alt29) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:158:17: ( OR | AND ) condition
            	    {
            	    if ( (input.LA(1)>=OR && input.LA(1)<=AND) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_condition_in_conditionList716);
            	    condition();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop29;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "conditionList"


    // $ANTLR start "condition"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:161:1: condition : ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison ) ;
    public final void condition() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:3: ( ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:5: ( NOT )? ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )
            {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:5: ( NOT )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==NOT) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:162:7: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_condition736); if (state.failed) return ;

                    }
                    break;

            }

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:163:5: ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )
            int alt31=8;
            alt31 = dfa31.predict(input);
            switch (alt31) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:163:7: nestedCondition
                    {
                    pushFollow(FOLLOW_nestedCondition_in_condition747);
                    nestedCondition();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:164:7: in
                    {
                    pushFollow(FOLLOW_in_in_condition755);
                    in();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:165:7: between
                    {
                    pushFollow(FOLLOW_between_in_condition763);
                    between();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:166:7: isNull
                    {
                    pushFollow(FOLLOW_isNull_in_condition771);
                    isNull();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 5 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:167:7: exists
                    {
                    pushFollow(FOLLOW_exists_in_condition779);
                    exists();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 6 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:168:7: like
                    {
                    pushFollow(FOLLOW_like_in_condition787);
                    like();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 7 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:169:7: quantifier
                    {
                    pushFollow(FOLLOW_quantifier_in_condition795);
                    quantifier();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 8 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:170:6: comparison
                    {
                    pushFollow(FOLLOW_comparison_in_condition802);
                    comparison();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "condition"


    // $ANTLR start "in"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:174:1: in : expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN ;
    public final void in() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:3: ( expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:5: expression ( NOT )? IN LPAREN ( subSelect | expressionList ) RPAREN
            {
            pushFollow(FOLLOW_expression_in_in821);
            expression();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:16: ( NOT )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==NOT) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:18: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_in825); if (state.failed) return ;

                    }
                    break;

            }

            match(input,IN,FOLLOW_IN_in_in830); if (state.failed) return ;
            match(input,LPAREN,FOLLOW_LPAREN_in_in832); if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:35: ( subSelect | expressionList )
            int alt33=2;
            switch ( input.LA(1) ) {
            case SELECT:
                {
                alt33=1;
                }
                break;
            case LPAREN:
                {
                int LA33_2 = input.LA(2);

                if ( (LA33_2==LPAREN||LA33_2==Integer||LA33_2==Identifier||(LA33_2>=PLUS && LA33_2<=MINUS)||(LA33_2>=Float && LA33_2<=String)||LA33_2==QuotedIdentifier||(LA33_2>=107 && LA33_2<=109)) ) {
                    alt33=2;
                }
                else if ( (LA33_2==SELECT) ) {
                    alt33=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 2, input);

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
            case QuotedIdentifier:
            case 107:
            case 108:
            case 109:
                {
                alt33=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;
            }

            switch (alt33) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:37: subSelect
                    {
                    pushFollow(FOLLOW_subSelect_in_in836);
                    subSelect();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:175:49: expressionList
                    {
                    pushFollow(FOLLOW_expressionList_in_in840);
                    expressionList();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;

            }

            match(input,RPAREN,FOLLOW_RPAREN_in_in844); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "in"


    // $ANTLR start "between"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:178:1: between : expression ( NOT )? BETWEEN expression AND expression ;
    public final void between() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:3: ( expression ( NOT )? BETWEEN expression AND expression )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:5: expression ( NOT )? BETWEEN expression AND expression
            {
            pushFollow(FOLLOW_expression_in_between859);
            expression();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:16: ( NOT )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==NOT) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:179:18: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_between863); if (state.failed) return ;

                    }
                    break;

            }

            match(input,BETWEEN,FOLLOW_BETWEEN_in_between868); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_between870);
            expression();

            state._fsp--;
            if (state.failed) return ;
            match(input,AND,FOLLOW_AND_in_between872); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_between874);
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
        return ;
    }
    // $ANTLR end "between"


    // $ANTLR start "isNull"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:182:1: isNull : expression IS ( NOT )? NULL ;
    public final void isNull() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:3: ( expression IS ( NOT )? NULL )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:5: expression IS ( NOT )? NULL
            {
            pushFollow(FOLLOW_expression_in_isNull889);
            expression();

            state._fsp--;
            if (state.failed) return ;
            match(input,IS,FOLLOW_IS_in_isNull891); if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:19: ( NOT )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==NOT) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:183:21: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_isNull895); if (state.failed) return ;

                    }
                    break;

            }

            match(input,NULL,FOLLOW_NULL_in_isNull900); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "isNull"


    // $ANTLR start "exists"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:186:1: exists : EXISTS expression ;
    public final void exists() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:187:3: ( EXISTS expression )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:187:5: EXISTS expression
            {
            match(input,EXISTS,FOLLOW_EXISTS_in_exists915); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_exists917);
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
        return ;
    }
    // $ANTLR end "exists"


    // $ANTLR start "like"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:190:1: like : expression ( NOT )? LIKE expression ;
    public final void like() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:3: ( expression ( NOT )? LIKE expression )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:5: expression ( NOT )? LIKE expression
            {
            pushFollow(FOLLOW_expression_in_like932);
            expression();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:16: ( NOT )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==NOT) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:191:18: NOT
                    {
                    match(input,NOT,FOLLOW_NOT_in_like936); if (state.failed) return ;

                    }
                    break;

            }

            match(input,LIKE,FOLLOW_LIKE_in_like941); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_like943);
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
        return ;
    }
    // $ANTLR end "like"


    // $ANTLR start "comparison"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:195:1: comparison : expression comparator expression ;
    public final void comparison() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:196:3: ( expression comparator expression )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:196:5: expression comparator expression
            {
            pushFollow(FOLLOW_expression_in_comparison961);
            expression();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_comparator_in_comparison963);
            comparator();

            state._fsp--;
            if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_comparison965);
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
        return ;
    }
    // $ANTLR end "comparison"


    // $ANTLR start "comparator"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:199:1: comparator : ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT );
    public final void comparator() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:200:3: ( EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            if ( (input.LA(1)>=EQ && input.LA(1)<=GT) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
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
        return ;
    }
    // $ANTLR end "comparator"


    // $ANTLR start "quantifier"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:209:1: quantifier : expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN ;
    public final void quantifier() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:210:3: ( expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:210:5: expression ( ALL | ANY | SOME ) LPAREN subSelect RPAREN
            {
            pushFollow(FOLLOW_expression_in_quantifier1030);
            expression();

            state._fsp--;
            if (state.failed) return ;
            if ( input.LA(1)==ALL||(input.LA(1)>=ANY && input.LA(1)<=SOME) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }

            match(input,LPAREN,FOLLOW_LPAREN_in_quantifier1046); if (state.failed) return ;
            pushFollow(FOLLOW_subSelect_in_quantifier1048);
            subSelect();

            state._fsp--;
            if (state.failed) return ;
            match(input,RPAREN,FOLLOW_RPAREN_in_quantifier1050); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "quantifier"


    // $ANTLR start "expressionList"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:213:1: expressionList : expression ( COMMA expression )* ;
    public final void expressionList() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:214:3: ( expression ( COMMA expression )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:214:5: expression ( COMMA expression )*
            {
            pushFollow(FOLLOW_expression_in_expressionList1065);
            expression();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:214:16: ( COMMA expression )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==COMMA) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:214:18: COMMA expression
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_expressionList1069); if (state.failed) return ;
            	    pushFollow(FOLLOW_expression_in_expressionList1071);
            	    expression();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expressionList"


    // $ANTLR start "nestedExpression"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:217:1: nestedExpression : LPAREN expression RPAREN ;
    public final void nestedExpression() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:218:3: ( LPAREN expression RPAREN )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:218:5: LPAREN expression RPAREN
            {
            match(input,LPAREN,FOLLOW_LPAREN_in_nestedExpression1087); if (state.failed) return ;
            pushFollow(FOLLOW_expression_in_nestedExpression1089);
            expression();

            state._fsp--;
            if (state.failed) return ;
            match(input,RPAREN,FOLLOW_RPAREN_in_nestedExpression1091); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "nestedExpression"


    // $ANTLR start "expression"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:221:1: expression : multiply ( ( PLUS | MINUS ) multiply )* ;
    public final void expression() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:3: ( multiply ( ( PLUS | MINUS ) multiply )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:5: multiply ( ( PLUS | MINUS ) multiply )*
            {
            pushFollow(FOLLOW_multiply_in_expression1106);
            multiply();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:14: ( ( PLUS | MINUS ) multiply )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( ((LA38_0>=PLUS && LA38_0<=MINUS)) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:222:16: ( PLUS | MINUS ) multiply
            	    {
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_multiply_in_expression1120);
            	    multiply();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expression"


    // $ANTLR start "multiply"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:225:1: multiply : value ( ( STAR | DIVIDE ) value )* ;
    public final void multiply() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:3: ( value ( ( STAR | DIVIDE ) value )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:5: value ( ( STAR | DIVIDE ) value )*
            {
            pushFollow(FOLLOW_value_in_multiply1137);
            value();

            state._fsp--;
            if (state.failed) return ;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:11: ( ( STAR | DIVIDE ) value )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==STAR||LA39_0==DIVIDE) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:226:13: ( STAR | DIVIDE ) value
            	    {
            	    if ( input.LA(1)==STAR||input.LA(1)==DIVIDE ) {
            	        input.consume();
            	        state.errorRecovery=false;state.failed=false;
            	    }
            	    else {
            	        if (state.backtracking>0) {state.failed=true; return ;}
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_value_in_multiply1151);
            	    value();

            	    state._fsp--;
            	    if (state.failed) return ;

            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "multiply"


    // $ANTLR start "value"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:244:1: value : ( literal | ( unary )? ( columnRef | nestedExpression ) );
    public final void value() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:245:3: ( literal | ( unary )? ( columnRef | nestedExpression ) )
            int alt42=2;
            switch ( input.LA(1) ) {
            case PLUS:
            case MINUS:
                {
                int LA42_1 = input.LA(2);

                if ( (LA42_1==LPAREN||LA42_1==Identifier||LA42_1==QuotedIdentifier) ) {
                    alt42=2;
                }
                else if ( (LA42_1==Integer||(LA42_1>=Float && LA42_1<=String)) ) {
                    alt42=1;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 42, 1, input);

                    throw nvae;
                }
                }
                break;
            case Integer:
            case Float:
            case String:
            case 107:
            case 108:
            case 109:
                {
                alt42=1;
                }
                break;
            case LPAREN:
            case Identifier:
            case QuotedIdentifier:
                {
                alt42=2;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;
            }

            switch (alt42) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:245:5: literal
                    {
                    pushFollow(FOLLOW_literal_in_value1190);
                    literal();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:247:5: ( unary )? ( columnRef | nestedExpression )
                    {
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:247:5: ( unary )?
                    int alt40=2;
                    int LA40_0 = input.LA(1);

                    if ( ((LA40_0>=PLUS && LA40_0<=MINUS)) ) {
                        alt40=1;
                    }
                    switch (alt40) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:247:7: unary
                            {
                            pushFollow(FOLLOW_unary_in_value1200);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:248:5: ( columnRef | nestedExpression )
                    int alt41=2;
                    int LA41_0 = input.LA(1);

                    if ( (LA41_0==Identifier||LA41_0==QuotedIdentifier) ) {
                        alt41=1;
                    }
                    else if ( (LA41_0==LPAREN) ) {
                        alt41=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 41, 0, input);

                        throw nvae;
                    }
                    switch (alt41) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:248:7: columnRef
                            {
                            pushFollow(FOLLOW_columnRef_in_value1211);
                            columnRef();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;
                        case 2 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:249:7: nestedExpression
                            {
                            pushFollow(FOLLOW_nestedExpression_in_value1219);
                            nestedExpression();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }


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
        return ;
    }
    // $ANTLR end "value"


    // $ANTLR start "literal"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:254:1: literal : ( ( unary )? ( Float | Integer | String ) | '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' );
    public final void literal() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:255:3: ( ( unary )? ( Float | Integer | String ) | '{d' Timestamp '}' | '{t' Timestamp '}' | '{ts' Timestamp '}' )
            int alt44=4;
            switch ( input.LA(1) ) {
            case Integer:
            case PLUS:
            case MINUS:
            case Float:
            case String:
                {
                alt44=1;
                }
                break;
            case 107:
                {
                alt44=2;
                }
                break;
            case 108:
                {
                alt44=3;
                }
                break;
            case 109:
                {
                alt44=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 44, 0, input);

                throw nvae;
            }

            switch (alt44) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:255:5: ( unary )? ( Float | Integer | String )
                    {
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:255:5: ( unary )?
                    int alt43=2;
                    int LA43_0 = input.LA(1);

                    if ( ((LA43_0>=PLUS && LA43_0<=MINUS)) ) {
                        alt43=1;
                    }
                    switch (alt43) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:255:7: unary
                            {
                            pushFollow(FOLLOW_unary_in_literal1243);
                            unary();

                            state._fsp--;
                            if (state.failed) return ;

                            }
                            break;

                    }

                    if ( input.LA(1)==Integer||(input.LA(1)>=Float && input.LA(1)<=String) ) {
                        input.consume();
                        state.errorRecovery=false;state.failed=false;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return ;}
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:260:5: '{d' Timestamp '}'
                    {
                    match(input,107,FOLLOW_107_in_literal1282); if (state.failed) return ;
                    match(input,Timestamp,FOLLOW_Timestamp_in_literal1284); if (state.failed) return ;
                    match(input,RCURLY,FOLLOW_RCURLY_in_literal1286); if (state.failed) return ;

                    }
                    break;
                case 3 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:261:5: '{t' Timestamp '}'
                    {
                    match(input,108,FOLLOW_108_in_literal1293); if (state.failed) return ;
                    match(input,Timestamp,FOLLOW_Timestamp_in_literal1295); if (state.failed) return ;
                    match(input,RCURLY,FOLLOW_RCURLY_in_literal1297); if (state.failed) return ;

                    }
                    break;
                case 4 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:262:5: '{ts' Timestamp '}'
                    {
                    match(input,109,FOLLOW_109_in_literal1304); if (state.failed) return ;
                    match(input,Timestamp,FOLLOW_Timestamp_in_literal1306); if (state.failed) return ;
                    match(input,RCURLY,FOLLOW_RCURLY_in_literal1308); if (state.failed) return ;

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
        return ;
    }
    // $ANTLR end "literal"


    // $ANTLR start "unary"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:265:1: unary : ( MINUS | PLUS );
    public final void unary() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:266:3: ( MINUS | PLUS )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
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
        return ;
    }
    // $ANTLR end "unary"


    // $ANTLR start "tableRef"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:287:1: tableRef : ( tableName | databaseName DOT tableName );
    public final void tableRef() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:288:3: ( tableName | databaseName DOT tableName )
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==Identifier||LA45_0==QuotedIdentifier) ) {
                int LA45_1 = input.LA(2);

                if ( (LA45_1==EOF||LA45_1==SEMI||LA45_1==RPAREN||(LA45_1>=COMMA && LA45_1<=AS)||LA45_1==Identifier||LA45_1==FROM||(LA45_1>=WHERE && LA45_1<=GROUP)||(LA45_1>=HAVING && LA45_1<=ORDER)) ) {
                    alt45=1;
                }
                else if ( (LA45_1==DOT) ) {
                    alt45=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 45, 1, input);

                    throw nvae;
                }
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 45, 0, input);

                throw nvae;
            }
            switch (alt45) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:288:5: tableName
                    {
                    pushFollow(FOLLOW_tableName_in_tableRef1363);
                    tableName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:289:5: databaseName DOT tableName
                    {
                    pushFollow(FOLLOW_databaseName_in_tableRef1369);
                    databaseName();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,DOT,FOLLOW_DOT_in_tableRef1371); if (state.failed) return ;
                    pushFollow(FOLLOW_tableName_in_tableRef1373);
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
        return ;
    }
    // $ANTLR end "tableRef"


    // $ANTLR start "columnRef"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:292:1: columnRef : ( columnName | tableAlias DOT columnName );
    public final void columnRef() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:293:3: ( columnName | tableAlias DOT columnName )
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==Identifier) ) {
                int LA46_1 = input.LA(2);

                if ( (LA46_1==EOF||LA46_1==SEMI||LA46_1==RPAREN||LA46_1==ALL||(LA46_1>=STAR && LA46_1<=AS)||(LA46_1>=Identifier && LA46_1<=FULL)||LA46_1==JOIN||LA46_1==GROUP||(LA46_1>=HAVING && LA46_1<=IS)||(LA46_1>=LIKE && LA46_1<=DIVIDE)) ) {
                    alt46=1;
                }
                else if ( (LA46_1==DOT) ) {
                    alt46=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return ;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 46, 1, input);

                    throw nvae;
                }
            }
            else if ( (LA46_0==QuotedIdentifier) ) {
                alt46=1;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                NoViableAltException nvae =
                    new NoViableAltException("", 46, 0, input);

                throw nvae;
            }
            switch (alt46) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:293:5: columnName
                    {
                    pushFollow(FOLLOW_columnName_in_columnRef1388);
                    columnName();

                    state._fsp--;
                    if (state.failed) return ;

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:294:5: tableAlias DOT columnName
                    {
                    pushFollow(FOLLOW_tableAlias_in_columnRef1395);
                    tableAlias();

                    state._fsp--;
                    if (state.failed) return ;
                    match(input,DOT,FOLLOW_DOT_in_columnRef1397); if (state.failed) return ;
                    pushFollow(FOLLOW_columnName_in_columnRef1399);
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
        return ;
    }
    // $ANTLR end "columnRef"


    // $ANTLR start "databaseName"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:297:1: databaseName : ( Identifier | QuotedIdentifier );
    public final void databaseName() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:298:3: ( Identifier | QuotedIdentifier )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            if ( input.LA(1)==Identifier||input.LA(1)==QuotedIdentifier ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
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
        return ;
    }
    // $ANTLR end "databaseName"


    // $ANTLR start "tableName"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:302:1: tableName : ( Identifier | QuotedIdentifier );
    public final void tableName() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:303:3: ( Identifier | QuotedIdentifier )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            if ( input.LA(1)==Identifier||input.LA(1)==QuotedIdentifier ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
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
        return ;
    }
    // $ANTLR end "tableName"


    // $ANTLR start "tableAlias"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:307:1: tableAlias : Identifier ;
    public final void tableAlias() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:308:3: ( Identifier )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:308:5: Identifier
            {
            match(input,Identifier,FOLLOW_Identifier_in_tableAlias1454); if (state.failed) return ;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "tableAlias"


    // $ANTLR start "columnName"
    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:311:1: columnName : ( Identifier | QuotedIdentifier );
    public final void columnName() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:312:3: ( Identifier | QuotedIdentifier )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
            {
            if ( input.LA(1)==Identifier||input.LA(1)==QuotedIdentifier ) {
                input.consume();
                state.errorRecovery=false;state.failed=false;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return ;}
                MismatchedSetException mse = new MismatchedSetException(null,input);
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
        return ;
    }
    // $ANTLR end "columnName"

    // $ANTLR start synpred39_GenericSQL
    public final void synpred39_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:163:7: ( nestedCondition )
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:163:7: nestedCondition
        {
        pushFollow(FOLLOW_nestedCondition_in_synpred39_GenericSQL747);
        nestedCondition();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred39_GenericSQL

    // $ANTLR start synpred40_GenericSQL
    public final void synpred40_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:164:7: ( in )
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:164:7: in
        {
        pushFollow(FOLLOW_in_in_synpred40_GenericSQL755);
        in();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred40_GenericSQL

    // $ANTLR start synpred41_GenericSQL
    public final void synpred41_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:165:7: ( between )
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:165:7: between
        {
        pushFollow(FOLLOW_between_in_synpred41_GenericSQL763);
        between();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred41_GenericSQL

    // $ANTLR start synpred42_GenericSQL
    public final void synpred42_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:166:7: ( isNull )
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:166:7: isNull
        {
        pushFollow(FOLLOW_isNull_in_synpred42_GenericSQL771);
        isNull();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred42_GenericSQL

    // $ANTLR start synpred44_GenericSQL
    public final void synpred44_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:168:7: ( like )
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:168:7: like
        {
        pushFollow(FOLLOW_like_in_synpred44_GenericSQL787);
        like();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred44_GenericSQL

    // $ANTLR start synpred45_GenericSQL
    public final void synpred45_GenericSQL_fragment() throws RecognitionException {   
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:169:7: ( quantifier )
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:169:7: quantifier
        {
        pushFollow(FOLLOW_quantifier_in_synpred45_GenericSQL795);
        quantifier();

        state._fsp--;
        if (state.failed) return ;

        }
    }
    // $ANTLR end synpred45_GenericSQL

    // Delegated rules

    public final boolean synpred44_GenericSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred44_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred40_GenericSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred40_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred39_GenericSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred39_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred42_GenericSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred42_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred45_GenericSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred45_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred41_GenericSQL() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred41_GenericSQL_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA31 dfa31 = new DFA31(this);
    static final String DFA31_eotS =
        "\21\uffff";
    static final String DFA31_eofS =
        "\21\uffff";
    static final String DFA31_minS =
        "\1\5\10\0\10\uffff";
    static final String DFA31_maxS =
        "\1\155\10\0\10\uffff";
    static final String DFA31_acceptS =
        "\11\uffff\1\5\1\1\1\2\1\3\1\4\1\6\1\7\1\10";
    static final String DFA31_specialS =
        "\1\uffff\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\10\uffff}>";
    static final String[] DFA31_transitionS = {
            "\1\1\6\uffff\1\3\5\uffff\1\7\30\uffff\1\11\12\uffff\2\2\1\uffff"+
            "\2\3\1\uffff\1\10\56\uffff\1\4\1\5\1\6",
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

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "163:5: ( nestedCondition | in | between | isNull | exists | like | quantifier | comparison )";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA31_1 = input.LA(1);

                         
                        int index31_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred39_GenericSQL()) ) {s = 10;}

                        else if ( (synpred40_GenericSQL()) ) {s = 11;}

                        else if ( (synpred41_GenericSQL()) ) {s = 12;}

                        else if ( (synpred42_GenericSQL()) ) {s = 13;}

                        else if ( (synpred44_GenericSQL()) ) {s = 14;}

                        else if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index31_1);
                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA31_2 = input.LA(1);

                         
                        int index31_2 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_GenericSQL()) ) {s = 11;}

                        else if ( (synpred41_GenericSQL()) ) {s = 12;}

                        else if ( (synpred42_GenericSQL()) ) {s = 13;}

                        else if ( (synpred44_GenericSQL()) ) {s = 14;}

                        else if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index31_2);
                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA31_3 = input.LA(1);

                         
                        int index31_3 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_GenericSQL()) ) {s = 11;}

                        else if ( (synpred41_GenericSQL()) ) {s = 12;}

                        else if ( (synpred42_GenericSQL()) ) {s = 13;}

                        else if ( (synpred44_GenericSQL()) ) {s = 14;}

                        else if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index31_3);
                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA31_4 = input.LA(1);

                         
                        int index31_4 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_GenericSQL()) ) {s = 11;}

                        else if ( (synpred41_GenericSQL()) ) {s = 12;}

                        else if ( (synpred42_GenericSQL()) ) {s = 13;}

                        else if ( (synpred44_GenericSQL()) ) {s = 14;}

                        else if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index31_4);
                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA31_5 = input.LA(1);

                         
                        int index31_5 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_GenericSQL()) ) {s = 11;}

                        else if ( (synpred41_GenericSQL()) ) {s = 12;}

                        else if ( (synpred42_GenericSQL()) ) {s = 13;}

                        else if ( (synpred44_GenericSQL()) ) {s = 14;}

                        else if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index31_5);
                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA31_6 = input.LA(1);

                         
                        int index31_6 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_GenericSQL()) ) {s = 11;}

                        else if ( (synpred41_GenericSQL()) ) {s = 12;}

                        else if ( (synpred42_GenericSQL()) ) {s = 13;}

                        else if ( (synpred44_GenericSQL()) ) {s = 14;}

                        else if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index31_6);
                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA31_7 = input.LA(1);

                         
                        int index31_7 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_GenericSQL()) ) {s = 11;}

                        else if ( (synpred41_GenericSQL()) ) {s = 12;}

                        else if ( (synpred42_GenericSQL()) ) {s = 13;}

                        else if ( (synpred44_GenericSQL()) ) {s = 14;}

                        else if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index31_7);
                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA31_8 = input.LA(1);

                         
                        int index31_8 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred40_GenericSQL()) ) {s = 11;}

                        else if ( (synpred41_GenericSQL()) ) {s = 12;}

                        else if ( (synpred42_GenericSQL()) ) {s = 13;}

                        else if ( (synpred44_GenericSQL()) ) {s = 14;}

                        else if ( (synpred45_GenericSQL()) ) {s = 15;}

                        else if ( (true) ) {s = 16;}

                         
                        input.seek(index31_8);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 31, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_select_in_statement69 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_SEMI_in_statement73 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_statement78 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_select_in_subSelect96 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_subSelect102 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_select_in_subSelect104 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_subSelect106 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SELECT_in_select121 = new BitSet(new long[]{0x16C0000000045F20L,0x0000380000000000L});
    public static final BitSet FOLLOW_set_in_select127 = new BitSet(new long[]{0x16C0000000045F20L,0x0000380000000000L});
    public static final BitSet FOLLOW_TOP_in_select148 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_Integer_in_select150 = new BitSet(new long[]{0x16C0000000047F20L,0x0000380000000000L});
    public static final BitSet FOLLOW_PERCENT_in_select154 = new BitSet(new long[]{0x16C0000000045F20L,0x0000380000000000L});
    public static final BitSet FOLLOW_itemList_in_select166 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_into_in_select174 = new BitSet(new long[]{0x0000000000180000L});
    public static final BitSet FOLLOW_from_in_select183 = new BitSet(new long[]{0x0000000360000002L});
    public static final BitSet FOLLOW_where_in_select192 = new BitSet(new long[]{0x0000000340000002L});
    public static final BitSet FOLLOW_groupBy_in_select203 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_having_in_select214 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_orderBy_in_select225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STAR_in_itemList241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_item_in_itemList247 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_itemList251 = new BitSet(new long[]{0x16C0000000045F20L,0x0000380000000000L});
    public static final BitSet FOLLOW_item_in_itemList253 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_value_in_item271 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_AS_in_item277 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_alias_in_item282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_allColumns_in_item291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_allColumns305 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DOT_in_allColumns307 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_STAR_in_allColumns309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_alias322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTO_in_into337 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_tableRef_in_into339 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_into343 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_tableRef_in_into345 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_FROM_in_from363 = new BitSet(new long[]{0x1000000000040020L});
    public static final BitSet FOLLOW_fromItem_in_from365 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_from369 = new BitSet(new long[]{0x1000000000040020L});
    public static final BitSet FOLLOW_fromItem_in_from371 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_LPAREN_in_fromItem393 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_fromItem395 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_fromItem397 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_tableRef_in_fromItem408 = new BitSet(new long[]{0x0000000000050002L});
    public static final BitSet FOLLOW_AS_in_fromItem425 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_alias_in_fromItem430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_join_in_joinList450 = new BitSet(new long[]{0x0000000005E00002L});
    public static final BitSet FOLLOW_INNER_in_join470 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_set_in_join478 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_OUTER_in_join494 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_JOIN_in_join507 = new BitSet(new long[]{0x0000000018000002L});
    public static final BitSet FOLLOW_ON_in_join515 = new BitSet(new long[]{0x16C0084000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_conditionList_in_join517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_USING_in_join525 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_join527 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_columnRef_in_join529 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_join533 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_columnRef_in_join535 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_WHERE_in_where566 = new BitSet(new long[]{0x16C0084000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_conditionList_in_where568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GROUP_in_groupBy583 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_BY_in_groupBy585 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_columnRef_in_groupBy587 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_groupBy591 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_columnRef_in_groupBy593 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_HAVING_in_having611 = new BitSet(new long[]{0x16C0084000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_conditionList_in_having613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ORDER_in_orderBy629 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_BY_in_orderBy631 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy633 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_orderBy637 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_orderByItem_in_orderBy639 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_columnRef_in_orderByItem657 = new BitSet(new long[]{0x0000000C00000002L});
    public static final BitSet FOLLOW_set_in_orderByItem659 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedCondition683 = new BitSet(new long[]{0x16C0084000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_conditionList_in_nestedCondition685 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedCondition687 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_condition_in_conditionList702 = new BitSet(new long[]{0x0000003000000002L});
    public static final BitSet FOLLOW_set_in_conditionList706 = new BitSet(new long[]{0x16C0084000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_condition_in_conditionList716 = new BitSet(new long[]{0x0000003000000002L});
    public static final BitSet FOLLOW_NOT_in_condition736 = new BitSet(new long[]{0x16C0084000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_nestedCondition_in_condition747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_condition755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_condition763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_condition771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_exists_in_condition779 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_condition787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_condition795 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_comparison_in_condition802 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_in821 = new BitSet(new long[]{0x000000C000000000L});
    public static final BitSet FOLLOW_NOT_in_in825 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_IN_in_in830 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_in832 = new BitSet(new long[]{0x16C00000000410A0L,0x0000380000000000L});
    public static final BitSet FOLLOW_subSelect_in_in836 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_expressionList_in_in840 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_in844 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_between859 = new BitSet(new long[]{0x0000014000000000L});
    public static final BitSet FOLLOW_NOT_in_between863 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_BETWEEN_in_between868 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_expression_in_between870 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_AND_in_between872 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_expression_in_between874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_isNull889 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_IS_in_isNull891 = new BitSet(new long[]{0x0000044000000000L});
    public static final BitSet FOLLOW_NOT_in_isNull895 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_NULL_in_isNull900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_EXISTS_in_exists915 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_expression_in_exists917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_like932 = new BitSet(new long[]{0x0000104000000000L});
    public static final BitSet FOLLOW_NOT_in_like936 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_LIKE_in_like941 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_expression_in_like943 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_comparison961 = new BitSet(new long[]{0x000FE00000000000L});
    public static final BitSet FOLLOW_comparator_in_comparison963 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_expression_in_comparison965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_comparator0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_quantifier1030 = new BitSet(new long[]{0x0030000000000100L});
    public static final BitSet FOLLOW_set_in_quantifier1032 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_LPAREN_in_quantifier1046 = new BitSet(new long[]{0x00000000000000A0L});
    public static final BitSet FOLLOW_subSelect_in_quantifier1048 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_quantifier1050 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expression_in_expressionList1065 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_COMMA_in_expressionList1069 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_expression_in_expressionList1071 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_LPAREN_in_nestedExpression1087 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_expression_in_nestedExpression1089 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RPAREN_in_nestedExpression1091 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_multiply_in_expression1106 = new BitSet(new long[]{0x00C0000000000002L});
    public static final BitSet FOLLOW_set_in_expression1110 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_multiply_in_expression1120 = new BitSet(new long[]{0x00C0000000000002L});
    public static final BitSet FOLLOW_value_in_multiply1137 = new BitSet(new long[]{0x0100000000004002L});
    public static final BitSet FOLLOW_set_in_multiply1141 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_value_in_multiply1151 = new BitSet(new long[]{0x0100000000004002L});
    public static final BitSet FOLLOW_literal_in_value1190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_value1200 = new BitSet(new long[]{0x16C0000000041020L,0x0000380000000000L});
    public static final BitSet FOLLOW_columnRef_in_value1211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedExpression_in_value1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unary_in_literal1243 = new BitSet(new long[]{0x0600000000001000L});
    public static final BitSet FOLLOW_set_in_literal1252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_107_in_literal1282 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_Timestamp_in_literal1284 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_literal1286 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_108_in_literal1293 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_Timestamp_in_literal1295 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_literal1297 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_109_in_literal1304 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_Timestamp_in_literal1306 = new BitSet(new long[]{0x0000000000000000L,0x0000000400000000L});
    public static final BitSet FOLLOW_RCURLY_in_literal1308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_unary0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableName_in_tableRef1363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_databaseName_in_tableRef1369 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DOT_in_tableRef1371 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_tableName_in_tableRef1373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_columnName_in_columnRef1388 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_tableAlias_in_columnRef1395 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_DOT_in_columnRef1397 = new BitSet(new long[]{0x1000000000040000L});
    public static final BitSet FOLLOW_columnName_in_columnRef1399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_databaseName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_tableName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_Identifier_in_tableAlias1454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_columnName0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_nestedCondition_in_synpred39_GenericSQL747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_in_in_synpred40_GenericSQL755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_between_in_synpred41_GenericSQL763 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_isNull_in_synpred42_GenericSQL771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_like_in_synpred44_GenericSQL787 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_quantifier_in_synpred45_GenericSQL795 = new BitSet(new long[]{0x0000000000000002L});

}