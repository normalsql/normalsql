// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g 2013-11-30 19:27:46
 
package fado.parse; 


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class GenericSQLLexer extends Lexer {
    public static final int LT=52;
    public static final int STAR=21;
    public static final int MOD=109;
    public static final int CASE=77;
    public static final int COUNT=102;
    public static final int NOT=42;
    public static final int EOF=-1;
    public static final int Identifier=24;
    public static final int RPAREN=6;
    public static final int FULL=88;
    public static final int INSERT=14;
    public static final int USING=98;
    public static final int Comment=112;
    public static final int EQ=18;
    public static final int SELECT=7;
    public static final int INTO=19;
    public static final int DIVIDE=59;
    public static final int D=69;
    public static final int E=74;
    public static final int F=83;
    public static final int UNIQUE=10;
    public static final int G=89;
    public static final int A=66;
    public static final int B=73;
    public static final int C=72;
    public static final int ASC=38;
    public static final int L=67;
    public static final int M=86;
    public static final int N=68;
    public static final int O=85;
    public static final int H=91;
    public static final int I=79;
    public static final int NULL=46;
    public static final int ELSE=80;
    public static final int J=93;
    public static final int K=94;
    public static final int U=87;
    public static final int T=75;
    public static final int ON=32;
    public static final int W=76;
    public static final int LCURLY=104;
    public static final int V=92;
    public static final int Q=97;
    public static final int P=90;
    public static final int DELETE=78;
    public static final int S=71;
    public static final int R=84;
    public static final int Y=70;
    public static final int X=82;
    public static final int Z=103;
    public static final int Float=60;
    public static final int GROUP=34;
    public static final int OR=40;
    public static final int Timestamp=64;
    public static final int GT=54;
    public static final int FROM=25;
    public static final int END=81;
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
    public static final int THEN=95;
    public static final int IN=43;
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
    public static final int SET=16;
    public static final int RIGHT=30;
    public static final int HAVING=36;
    public static final int MIN=101;
    public static final int MINUS=58;
    public static final int Digit=110;
    public static final int QuotedIdentifier=65;
    public static final int TRUE=62;
    public static final int SEMI=4;
    public static final int JOIN=26;
    public static final int UNION=96;
    public static final int COLON=108;
    public static final int ANY=55;
    public static final int QUESTION=107;
    public static final int WHEN=99;
    public static final int RCURLY=105;
    public static final int NATURAL=31;
    public static final int DESC=39;
    public static final int TOP=11;
    public static final int BETWEEN=44;
    public static final int Integer=12;

    // delegates
    // delegators

    public GenericSQLLexer() {;} 
    public GenericSQLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public GenericSQLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g"; }

    // $ANTLR start "T__114"
    public final void mT__114() throws RecognitionException {
        try {
            int _type = T__114;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:11:8: ( '{d' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:11:10: '{d'
            {
            match("{d"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__114"

    // $ANTLR start "T__115"
    public final void mT__115() throws RecognitionException {
        try {
            int _type = T__115;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:12:8: ( '{t' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:12:10: '{t'
            {
            match("{t"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__115"

    // $ANTLR start "T__116"
    public final void mT__116() throws RecognitionException {
        try {
            int _type = T__116;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:13:8: ( '{ts' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:13:10: '{ts'
            {
            match("{ts"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__116"

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:367:11: ( A L L )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:367:13: A L L
            {
            mA(); 
            mL(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ALL"

    // $ANTLR start "AND"
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:368:11: ( A N D )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:368:13: A N D
            {
            mA(); 
            mN(); 
            mD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AND"

    // $ANTLR start "ANY"
    public final void mANY() throws RecognitionException {
        try {
            int _type = ANY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:369:11: ( A N Y )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:369:13: A N Y
            {
            mA(); 
            mN(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ANY"

    // $ANTLR start "AS"
    public final void mAS() throws RecognitionException {
        try {
            int _type = AS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:370:11: ( A S )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:370:13: A S
            {
            mA(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "AS"

    // $ANTLR start "ASC"
    public final void mASC() throws RecognitionException {
        try {
            int _type = ASC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:371:11: ( A S C )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:371:13: A S C
            {
            mA(); 
            mS(); 
            mC(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ASC"

    // $ANTLR start "BETWEEN"
    public final void mBETWEEN() throws RecognitionException {
        try {
            int _type = BETWEEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:372:11: ( B E T W E E N )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:372:13: B E T W E E N
            {
            mB(); 
            mE(); 
            mT(); 
            mW(); 
            mE(); 
            mE(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BETWEEN"

    // $ANTLR start "BY"
    public final void mBY() throws RecognitionException {
        try {
            int _type = BY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:373:11: ( B Y )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:373:13: B Y
            {
            mB(); 
            mY(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "BY"

    // $ANTLR start "CASE"
    public final void mCASE() throws RecognitionException {
        try {
            int _type = CASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:374:11: ( C A S E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:374:13: C A S E
            {
            mC(); 
            mA(); 
            mS(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CASE"

    // $ANTLR start "DELETE"
    public final void mDELETE() throws RecognitionException {
        try {
            int _type = DELETE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:380:11: ( D E L E T E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:380:13: D E L E T E
            {
            mD(); 
            mE(); 
            mL(); 
            mE(); 
            mT(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DELETE"

    // $ANTLR start "DESC"
    public final void mDESC() throws RecognitionException {
        try {
            int _type = DESC;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:381:11: ( D E S C )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:381:13: D E S C
            {
            mD(); 
            mE(); 
            mS(); 
            mC(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DESC"

    // $ANTLR start "DISTINCT"
    public final void mDISTINCT() throws RecognitionException {
        try {
            int _type = DISTINCT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:382:11: ( D I S T I N C T )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:382:13: D I S T I N C T
            {
            mD(); 
            mI(); 
            mS(); 
            mT(); 
            mI(); 
            mN(); 
            mC(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DISTINCT"

    // $ANTLR start "ELSE"
    public final void mELSE() throws RecognitionException {
        try {
            int _type = ELSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:383:11: ( E L S E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:383:13: E L S E
            {
            mE(); 
            mL(); 
            mS(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ELSE"

    // $ANTLR start "END"
    public final void mEND() throws RecognitionException {
        try {
            int _type = END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:384:11: ( E N D )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:384:13: E N D
            {
            mE(); 
            mN(); 
            mD(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "END"

    // $ANTLR start "EXISTS"
    public final void mEXISTS() throws RecognitionException {
        try {
            int _type = EXISTS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:386:11: ( E X I S T S )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:386:13: E X I S T S
            {
            mE(); 
            mX(); 
            mI(); 
            mS(); 
            mT(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EXISTS"

    // $ANTLR start "FALSE"
    public final void mFALSE() throws RecognitionException {
        try {
            int _type = FALSE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:388:11: ( F A L S E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:388:13: F A L S E
            {
            mF(); 
            mA(); 
            mL(); 
            mS(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FALSE"

    // $ANTLR start "FROM"
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:391:11: ( F R O M )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:391:13: F R O M
            {
            mF(); 
            mR(); 
            mO(); 
            mM(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FROM"

    // $ANTLR start "FULL"
    public final void mFULL() throws RecognitionException {
        try {
            int _type = FULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:392:11: ( F U L L )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:392:13: F U L L
            {
            mF(); 
            mU(); 
            mL(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "FULL"

    // $ANTLR start "GROUP"
    public final void mGROUP() throws RecognitionException {
        try {
            int _type = GROUP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:393:11: ( G R O U P )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:393:13: G R O U P
            {
            mG(); 
            mR(); 
            mO(); 
            mU(); 
            mP(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GROUP"

    // $ANTLR start "HAVING"
    public final void mHAVING() throws RecognitionException {
        try {
            int _type = HAVING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:394:11: ( H A V I N G )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:394:13: H A V I N G
            {
            mH(); 
            mA(); 
            mV(); 
            mI(); 
            mN(); 
            mG(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "HAVING"

    // $ANTLR start "IN"
    public final void mIN() throws RecognitionException {
        try {
            int _type = IN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:396:11: ( I N )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:396:13: I N
            {
            mI(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IN"

    // $ANTLR start "INNER"
    public final void mINNER() throws RecognitionException {
        try {
            int _type = INNER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:397:11: ( I N N E R )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:397:13: I N N E R
            {
            mI(); 
            mN(); 
            mN(); 
            mE(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INNER"

    // $ANTLR start "INSERT"
    public final void mINSERT() throws RecognitionException {
        try {
            int _type = INSERT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:398:11: ( I N S E R T )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:398:13: I N S E R T
            {
            mI(); 
            mN(); 
            mS(); 
            mE(); 
            mR(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INSERT"

    // $ANTLR start "INTO"
    public final void mINTO() throws RecognitionException {
        try {
            int _type = INTO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:400:11: ( I N T O )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:400:13: I N T O
            {
            mI(); 
            mN(); 
            mT(); 
            mO(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INTO"

    // $ANTLR start "IS"
    public final void mIS() throws RecognitionException {
        try {
            int _type = IS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:401:11: ( I S )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:401:13: I S
            {
            mI(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "IS"

    // $ANTLR start "JOIN"
    public final void mJOIN() throws RecognitionException {
        try {
            int _type = JOIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:402:11: ( J O I N )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:402:13: J O I N
            {
            mJ(); 
            mO(); 
            mI(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "JOIN"

    // $ANTLR start "LEFT"
    public final void mLEFT() throws RecognitionException {
        try {
            int _type = LEFT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:403:11: ( L E F T )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:403:13: L E F T
            {
            mL(); 
            mE(); 
            mF(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LEFT"

    // $ANTLR start "LIKE"
    public final void mLIKE() throws RecognitionException {
        try {
            int _type = LIKE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:404:11: ( L I K E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:404:13: L I K E
            {
            mL(); 
            mI(); 
            mK(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LIKE"

    // $ANTLR start "NATURAL"
    public final void mNATURAL() throws RecognitionException {
        try {
            int _type = NATURAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:407:11: ( N A T U R A L )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:407:13: N A T U R A L
            {
            mN(); 
            mA(); 
            mT(); 
            mU(); 
            mR(); 
            mA(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NATURAL"

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:408:11: ( N O T )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:408:13: N O T
            {
            mN(); 
            mO(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NOT"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:409:11: ( N U L L )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:409:13: N U L L
            {
            mN(); 
            mU(); 
            mL(); 
            mL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "ON"
    public final void mON() throws RecognitionException {
        try {
            int _type = ON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:410:11: ( O N )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:410:13: O N
            {
            mO(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ON"

    // $ANTLR start "OR"
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:411:11: ( O R )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:411:13: O R
            {
            mO(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OR"

    // $ANTLR start "ORDER"
    public final void mORDER() throws RecognitionException {
        try {
            int _type = ORDER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:412:11: ( O R D E R )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:412:13: O R D E R
            {
            mO(); 
            mR(); 
            mD(); 
            mE(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "ORDER"

    // $ANTLR start "OUTER"
    public final void mOUTER() throws RecognitionException {
        try {
            int _type = OUTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:413:11: ( O U T E R )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:413:13: O U T E R
            {
            mO(); 
            mU(); 
            mT(); 
            mE(); 
            mR(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "OUTER"

    // $ANTLR start "PERCENT"
    public final void mPERCENT() throws RecognitionException {
        try {
            int _type = PERCENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:414:11: ( P E R C E N T )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:414:13: P E R C E N T
            {
            mP(); 
            mE(); 
            mR(); 
            mC(); 
            mE(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PERCENT"

    // $ANTLR start "RIGHT"
    public final void mRIGHT() throws RecognitionException {
        try {
            int _type = RIGHT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:415:11: ( R I G H T )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:415:13: R I G H T
            {
            mR(); 
            mI(); 
            mG(); 
            mH(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RIGHT"

    // $ANTLR start "SELECT"
    public final void mSELECT() throws RecognitionException {
        try {
            int _type = SELECT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:417:11: ( S E L E C T )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:417:13: S E L E C T
            {
            mS(); 
            mE(); 
            mL(); 
            mE(); 
            mC(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SELECT"

    // $ANTLR start "SET"
    public final void mSET() throws RecognitionException {
        try {
            int _type = SET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:418:11: ( S E T )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:418:13: S E T
            {
            mS(); 
            mE(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SET"

    // $ANTLR start "SOME"
    public final void mSOME() throws RecognitionException {
        try {
            int _type = SOME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:419:11: ( S O M E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:419:13: S O M E
            {
            mS(); 
            mO(); 
            mM(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SOME"

    // $ANTLR start "THEN"
    public final void mTHEN() throws RecognitionException {
        try {
            int _type = THEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:421:11: ( T H E N )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:421:13: T H E N
            {
            mT(); 
            mH(); 
            mE(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "THEN"

    // $ANTLR start "TRUE"
    public final void mTRUE() throws RecognitionException {
        try {
            int _type = TRUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:422:11: ( T R U E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:422:13: T R U E
            {
            mT(); 
            mR(); 
            mU(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TRUE"

    // $ANTLR start "TOP"
    public final void mTOP() throws RecognitionException {
        try {
            int _type = TOP;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:424:11: ( T O P )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:424:13: T O P
            {
            mT(); 
            mO(); 
            mP(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "TOP"

    // $ANTLR start "UNION"
    public final void mUNION() throws RecognitionException {
        try {
            int _type = UNION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:425:11: ( U N I O N )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:425:13: U N I O N
            {
            mU(); 
            mN(); 
            mI(); 
            mO(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNION"

    // $ANTLR start "UNIQUE"
    public final void mUNIQUE() throws RecognitionException {
        try {
            int _type = UNIQUE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:426:11: ( U N I Q U E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:426:13: U N I Q U E
            {
            mU(); 
            mN(); 
            mI(); 
            mQ(); 
            mU(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UNIQUE"

    // $ANTLR start "UPDATE"
    public final void mUPDATE() throws RecognitionException {
        try {
            int _type = UPDATE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:427:11: ( U P D A T E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:427:13: U P D A T E
            {
            mU(); 
            mP(); 
            mD(); 
            mA(); 
            mT(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UPDATE"

    // $ANTLR start "USING"
    public final void mUSING() throws RecognitionException {
        try {
            int _type = USING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:428:11: ( U S I N G )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:428:13: U S I N G
            {
            mU(); 
            mS(); 
            mI(); 
            mN(); 
            mG(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "USING"

    // $ANTLR start "VALUES"
    public final void mVALUES() throws RecognitionException {
        try {
            int _type = VALUES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:429:11: ( V A L U E S )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:429:13: V A L U E S
            {
            mV(); 
            mA(); 
            mL(); 
            mU(); 
            mE(); 
            mS(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "VALUES"

    // $ANTLR start "WHEN"
    public final void mWHEN() throws RecognitionException {
        try {
            int _type = WHEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:430:11: ( W H E N )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:430:13: W H E N
            {
            mW(); 
            mH(); 
            mE(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHEN"

    // $ANTLR start "WHERE"
    public final void mWHERE() throws RecognitionException {
        try {
            int _type = WHERE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:431:11: ( W H E R E )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:431:13: W H E R E
            {
            mW(); 
            mH(); 
            mE(); 
            mR(); 
            mE(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHERE"

    // $ANTLR start "MAX"
    public final void mMAX() throws RecognitionException {
        try {
            int _type = MAX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:434:11: ( M A X )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:434:13: M A X
            {
            mM(); 
            mA(); 
            mX(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MAX"

    // $ANTLR start "MIN"
    public final void mMIN() throws RecognitionException {
        try {
            int _type = MIN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:435:11: ( M I N )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:435:13: M I N
            {
            mM(); 
            mI(); 
            mN(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MIN"

    // $ANTLR start "COUNT"
    public final void mCOUNT() throws RecognitionException {
        try {
            int _type = COUNT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:436:11: ( C O U N T )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:436:13: C O U N T
            {
            mC(); 
            mO(); 
            mU(); 
            mN(); 
            mT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COUNT"

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:438:11: ( ( 'a' | 'A' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:438:12: ( 'a' | 'A' )
            {
            if ( input.LA(1)=='A'||input.LA(1)=='a' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "A"

    // $ANTLR start "B"
    public final void mB() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:439:11: ( ( 'b' | 'B' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:439:12: ( 'b' | 'B' )
            {
            if ( input.LA(1)=='B'||input.LA(1)=='b' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "B"

    // $ANTLR start "C"
    public final void mC() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:440:11: ( ( 'c' | 'C' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:440:12: ( 'c' | 'C' )
            {
            if ( input.LA(1)=='C'||input.LA(1)=='c' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "C"

    // $ANTLR start "D"
    public final void mD() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:441:11: ( ( 'd' | 'D' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:441:12: ( 'd' | 'D' )
            {
            if ( input.LA(1)=='D'||input.LA(1)=='d' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "D"

    // $ANTLR start "E"
    public final void mE() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:442:11: ( ( 'e' | 'E' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:442:12: ( 'e' | 'E' )
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "E"

    // $ANTLR start "F"
    public final void mF() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:443:11: ( ( 'f' | 'F' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:443:12: ( 'f' | 'F' )
            {
            if ( input.LA(1)=='F'||input.LA(1)=='f' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "F"

    // $ANTLR start "G"
    public final void mG() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:444:11: ( ( 'g' | 'G' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:444:12: ( 'g' | 'G' )
            {
            if ( input.LA(1)=='G'||input.LA(1)=='g' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "G"

    // $ANTLR start "H"
    public final void mH() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:445:11: ( ( 'h' | 'H' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:445:12: ( 'h' | 'H' )
            {
            if ( input.LA(1)=='H'||input.LA(1)=='h' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "H"

    // $ANTLR start "I"
    public final void mI() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:446:11: ( ( 'i' | 'I' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:446:12: ( 'i' | 'I' )
            {
            if ( input.LA(1)=='I'||input.LA(1)=='i' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "I"

    // $ANTLR start "J"
    public final void mJ() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:447:11: ( ( 'j' | 'J' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:447:12: ( 'j' | 'J' )
            {
            if ( input.LA(1)=='J'||input.LA(1)=='j' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "J"

    // $ANTLR start "K"
    public final void mK() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:448:11: ( ( 'k' | 'K' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:448:12: ( 'k' | 'K' )
            {
            if ( input.LA(1)=='K'||input.LA(1)=='k' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "K"

    // $ANTLR start "L"
    public final void mL() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:449:11: ( ( 'l' | 'L' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:449:12: ( 'l' | 'L' )
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "L"

    // $ANTLR start "M"
    public final void mM() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:450:11: ( ( 'm' | 'M' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:450:12: ( 'm' | 'M' )
            {
            if ( input.LA(1)=='M'||input.LA(1)=='m' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "M"

    // $ANTLR start "N"
    public final void mN() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:451:11: ( ( 'n' | 'N' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:451:12: ( 'n' | 'N' )
            {
            if ( input.LA(1)=='N'||input.LA(1)=='n' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "N"

    // $ANTLR start "O"
    public final void mO() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:452:11: ( ( 'o' | 'O' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:452:12: ( 'o' | 'O' )
            {
            if ( input.LA(1)=='O'||input.LA(1)=='o' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "O"

    // $ANTLR start "P"
    public final void mP() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:453:11: ( ( 'p' | 'P' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:453:12: ( 'p' | 'P' )
            {
            if ( input.LA(1)=='P'||input.LA(1)=='p' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "P"

    // $ANTLR start "Q"
    public final void mQ() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:454:11: ( ( 'q' | 'Q' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:454:12: ( 'q' | 'Q' )
            {
            if ( input.LA(1)=='Q'||input.LA(1)=='q' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Q"

    // $ANTLR start "R"
    public final void mR() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:455:11: ( ( 'r' | 'R' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:455:12: ( 'r' | 'R' )
            {
            if ( input.LA(1)=='R'||input.LA(1)=='r' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "R"

    // $ANTLR start "S"
    public final void mS() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:456:11: ( ( 's' | 'S' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:456:12: ( 's' | 'S' )
            {
            if ( input.LA(1)=='S'||input.LA(1)=='s' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "S"

    // $ANTLR start "T"
    public final void mT() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:457:11: ( ( 't' | 'T' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:457:12: ( 't' | 'T' )
            {
            if ( input.LA(1)=='T'||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "T"

    // $ANTLR start "U"
    public final void mU() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:458:11: ( ( 'u' | 'U' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:458:12: ( 'u' | 'U' )
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "U"

    // $ANTLR start "V"
    public final void mV() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:459:11: ( ( 'v' | 'V' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:459:12: ( 'v' | 'V' )
            {
            if ( input.LA(1)=='V'||input.LA(1)=='v' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "V"

    // $ANTLR start "W"
    public final void mW() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:460:11: ( ( 'w' | 'W' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:460:12: ( 'w' | 'W' )
            {
            if ( input.LA(1)=='W'||input.LA(1)=='w' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "W"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:461:11: ( ( 'x' | 'X' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:461:12: ( 'x' | 'X' )
            {
            if ( input.LA(1)=='X'||input.LA(1)=='x' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "Y"
    public final void mY() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:462:11: ( ( 'y' | 'Y' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:462:12: ( 'y' | 'Y' )
            {
            if ( input.LA(1)=='Y'||input.LA(1)=='y' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Y"

    // $ANTLR start "Z"
    public final void mZ() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:463:11: ( ( 'z' | 'Z' ) )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:463:12: ( 'z' | 'Z' )
            {
            if ( input.LA(1)=='Z'||input.LA(1)=='z' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "Z"

    // $ANTLR start "DOT"
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:465:10: ( '.' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:465:12: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DOT"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:466:10: ( ',' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:466:12: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "LPAREN"
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:467:10: ( '(' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:467:12: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LPAREN"

    // $ANTLR start "RPAREN"
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:468:10: ( ')' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:468:12: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RPAREN"

    // $ANTLR start "LCURLY"
    public final void mLCURLY() throws RecognitionException {
        try {
            int _type = LCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:469:10: ( '{' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:469:12: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LCURLY"

    // $ANTLR start "RCURLY"
    public final void mRCURLY() throws RecognitionException {
        try {
            int _type = RCURLY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:470:10: ( '}' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:470:12: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RCURLY"

    // $ANTLR start "STRCAT"
    public final void mSTRCAT() throws RecognitionException {
        try {
            int _type = STRCAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:471:10: ( '||' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:471:12: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STRCAT"

    // $ANTLR start "QUESTION"
    public final void mQUESTION() throws RecognitionException {
        try {
            int _type = QUESTION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:472:10: ( '?' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:472:12: '?'
            {
            match('?'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QUESTION"

    // $ANTLR start "COLON"
    public final void mCOLON() throws RecognitionException {
        try {
            int _type = COLON;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:473:10: ( ':' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:473:12: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COLON"

    // $ANTLR start "SEMI"
    public final void mSEMI() throws RecognitionException {
        try {
            int _type = SEMI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:474:10: ( ';' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:474:12: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SEMI"

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:476:10: ( '=' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:476:12: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "NEQ1"
    public final void mNEQ1() throws RecognitionException {
        try {
            int _type = NEQ1;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:477:10: ( '<>' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:477:12: '<>'
            {
            match("<>"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEQ1"

    // $ANTLR start "NEQ2"
    public final void mNEQ2() throws RecognitionException {
        try {
            int _type = NEQ2;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:478:10: ( '!=' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:478:12: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NEQ2"

    // $ANTLR start "LTE"
    public final void mLTE() throws RecognitionException {
        try {
            int _type = LTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:479:10: ( '<=' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:479:12: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LTE"

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:480:10: ( '<' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:480:12: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "GTE"
    public final void mGTE() throws RecognitionException {
        try {
            int _type = GTE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:481:10: ( '>=' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:481:12: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GTE"

    // $ANTLR start "GT"
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:482:10: ( '>' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:482:12: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "GT"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:484:10: ( '+' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:484:12: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:485:10: ( '-' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:485:12: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "DIVIDE"
    public final void mDIVIDE() throws RecognitionException {
        try {
            int _type = DIVIDE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:486:10: ( '/' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:486:12: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIVIDE"

    // $ANTLR start "STAR"
    public final void mSTAR() throws RecognitionException {
        try {
            int _type = STAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:487:10: ( '*' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:487:12: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "STAR"

    // $ANTLR start "MOD"
    public final void mMOD() throws RecognitionException {
        try {
            int _type = MOD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:488:10: ( '%' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:488:12: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MOD"

    // $ANTLR start "Digit"
    public final void mDigit() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:491:7: ( '0' .. '9' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:491:9: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "Digit"

    // $ANTLR start "Integer"
    public final void mInteger() throws RecognitionException {
        try {
            int _type = Integer;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:494:3: ( ( Digit )+ )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:494:5: ( Digit )+
            {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:494:5: ( Digit )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:494:7: Digit
            	    {
            	    mDigit(); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Integer"

    // $ANTLR start "Float"
    public final void mFloat() throws RecognitionException {
        try {
            int _type = Float;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:498:3: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? | '.' ( '0' .. '9' )+ ( Exponent )? | ( '0' .. '9' )+ Exponent )
            int alt8=3;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:498:5: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )?
                    {
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:498:5: ( '0' .. '9' )+
                    int cnt2=0;
                    loop2:
                    do {
                        int alt2=2;
                        int LA2_0 = input.LA(1);

                        if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                            alt2=1;
                        }


                        switch (alt2) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:498:6: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt2 >= 1 ) break loop2;
                                EarlyExitException eee =
                                    new EarlyExitException(2, input);
                                throw eee;
                        }
                        cnt2++;
                    } while (true);

                    match('.'); 
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:498:21: ( '0' .. '9' )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:498:22: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:498:33: ( Exponent )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0=='E'||LA4_0=='e') ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:498:33: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:499:5: '.' ( '0' .. '9' )+ ( Exponent )?
                    {
                    match('.'); 
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:499:9: ( '0' .. '9' )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:499:10: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);

                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:499:21: ( Exponent )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0=='E'||LA6_0=='e') ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:499:21: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:500:5: ( '0' .. '9' )+ Exponent
                    {
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:500:5: ( '0' .. '9' )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:500:6: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt7 >= 1 ) break loop7;
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);

                    mExponent(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Float"

    // $ANTLR start "Exponent"
    public final void mExponent() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:505:3: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:505:5: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:505:15: ( '+' | '-' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='+'||LA9_0=='-') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:505:26: ( '0' .. '9' )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:505:27: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "Exponent"

    // $ANTLR start "String"
    public final void mString() throws RecognitionException {
        try {
            int _type = String;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:509:3: ( '\\'' ( options {greedy=false; } : . )* '\\'' )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:509:5: '\\'' ( options {greedy=false; } : . )* '\\''
            {
            match('\''); 
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:509:10: ( options {greedy=false; } : . )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0=='\'') ) {
                    alt11=2;
                }
                else if ( ((LA11_0>='\u0000' && LA11_0<='&')||(LA11_0>='(' && LA11_0<='\uFFFF')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:509:38: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "String"

    // $ANTLR start "Timestamp"
    public final void mTimestamp() throws RecognitionException {
        try {
            int _type = Timestamp;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:513:3: ( Digit Digit Digit Digit '-' Digit Digit '-' Digit Digit ( 't' | ' ' ) Digit Digit ':' Digit Digit ':' Digit Digit )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:513:5: Digit Digit Digit Digit '-' Digit Digit '-' Digit Digit ( 't' | ' ' ) Digit Digit ':' Digit Digit ':' Digit Digit
            {
            mDigit(); 
            mDigit(); 
            mDigit(); 
            mDigit(); 
            match('-'); 
            mDigit(); 
            mDigit(); 
            match('-'); 
            mDigit(); 
            mDigit(); 
            if ( input.LA(1)==' '||input.LA(1)=='t' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            mDigit(); 
            mDigit(); 
            match(':'); 
            mDigit(); 
            mDigit(); 
            match(':'); 
            mDigit(); 
            mDigit(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Timestamp"

    // $ANTLR start "Identifier"
    public final void mIdentifier() throws RecognitionException {
        try {
            int _type = Identifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:520:3: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '$' )* )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:520:5: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '$' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:520:25: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '$' )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='$'||(LA12_0>='0' && LA12_0<='9')||(LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='z')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:
            	    {
            	    if ( input.LA(1)=='$'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Identifier"

    // $ANTLR start "QuotedIdentifier"
    public final void mQuotedIdentifier() throws RecognitionException {
        try {
            int _type = QuotedIdentifier;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:524:3: ( '[' ( options {greedy=false; } : . )* ']' | '\"' ( options {greedy=false; } : . )* '\"' )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='[') ) {
                alt15=1;
            }
            else if ( (LA15_0=='\"') ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:524:5: '[' ( options {greedy=false; } : . )* ']'
                    {
                    match('['); 
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:524:9: ( options {greedy=false; } : . )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==']') ) {
                            alt13=2;
                        }
                        else if ( ((LA13_0>='\u0000' && LA13_0<='\\')||(LA13_0>='^' && LA13_0<='\uFFFF')) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:524:37: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    match(']'); 

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:525:5: '\"' ( options {greedy=false; } : . )* '\"'
                    {
                    match('\"'); 
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:525:9: ( options {greedy=false; } : . )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0=='\"') ) {
                            alt14=2;
                        }
                        else if ( ((LA14_0>='\u0000' && LA14_0<='!')||(LA14_0>='#' && LA14_0<='\uFFFF')) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:525:37: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "QuotedIdentifier"

    // $ANTLR start "Comment"
    public final void mComment() throws RecognitionException {
        try {
            int _type = Comment;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:529:3: ( '--' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
            int alt21=3;
            int LA21_0 = input.LA(1);

            if ( (LA21_0=='-') ) {
                alt21=1;
            }
            else if ( (LA21_0=='/') ) {
                int LA21_2 = input.LA(2);

                if ( (LA21_2=='/') ) {
                    alt21=2;
                }
                else if ( (LA21_2=='*') ) {
                    alt21=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 21, 2, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }
            switch (alt21) {
                case 1 :
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:529:5: '--' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    match("--"); 

                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:529:10: (~ ( '\\n' | '\\r' ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>='\u0000' && LA16_0<='\t')||(LA16_0>='\u000B' && LA16_0<='\f')||(LA16_0>='\u000E' && LA16_0<='\uFFFF')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:529:10: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop16;
                        }
                    } while (true);

                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:529:24: ( '\\r' )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0=='\r') ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:529:24: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 
                     _channel=HIDDEN; 

                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:530:5: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    match("//"); 

                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:530:10: (~ ( '\\n' | '\\r' ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( ((LA18_0>='\u0000' && LA18_0<='\t')||(LA18_0>='\u000B' && LA18_0<='\f')||(LA18_0>='\u000E' && LA18_0<='\uFFFF')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:530:10: ~ ( '\\n' | '\\r' )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:530:24: ( '\\r' )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0=='\r') ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:530:24: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 
                     _channel=HIDDEN; 

                    }
                    break;
                case 3 :
                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:531:5: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 

                    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:531:10: ( options {greedy=false; } : . )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0=='*') ) {
                            int LA20_1 = input.LA(2);

                            if ( (LA20_1=='/') ) {
                                alt20=2;
                            }
                            else if ( ((LA20_1>='\u0000' && LA20_1<='.')||(LA20_1>='0' && LA20_1<='\uFFFF')) ) {
                                alt20=1;
                            }


                        }
                        else if ( ((LA20_0>='\u0000' && LA20_0<=')')||(LA20_0>='+' && LA20_0<='\uFFFF')) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:531:38: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);

                    match("*/"); 

                     _channel=HIDDEN; 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Comment"

    // $ANTLR start "Whitespace"
    public final void mWhitespace() throws RecognitionException {
        try {
            int _type = Whitespace;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:535:3: ( ( '\\t' | ' ' | '\\r' | '\\n' )+ )
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:535:5: ( '\\t' | ' ' | '\\r' | '\\n' )+
            {
            // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:535:5: ( '\\t' | ' ' | '\\r' | '\\n' )+
            int cnt22=0;
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>='\t' && LA22_0<='\n')||LA22_0=='\r'||LA22_0==' ') ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt22 >= 1 ) break loop22;
                        EarlyExitException eee =
                            new EarlyExitException(22, input);
                        throw eee;
                }
                cnt22++;
            } while (true);

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "Whitespace"

    public void mTokens() throws RecognitionException {
        // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:8: ( T__114 | T__115 | T__116 | ALL | AND | ANY | AS | ASC | BETWEEN | BY | CASE | DELETE | DESC | DISTINCT | ELSE | END | EXISTS | FALSE | FROM | FULL | GROUP | HAVING | IN | INNER | INSERT | INTO | IS | JOIN | LEFT | LIKE | NATURAL | NOT | NULL | ON | OR | ORDER | OUTER | PERCENT | RIGHT | SELECT | SET | SOME | THEN | TRUE | TOP | UNION | UNIQUE | UPDATE | USING | VALUES | WHEN | WHERE | MAX | MIN | COUNT | DOT | COMMA | LPAREN | RPAREN | LCURLY | RCURLY | STRCAT | QUESTION | COLON | SEMI | EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT | PLUS | MINUS | DIVIDE | STAR | MOD | Integer | Float | String | Timestamp | Identifier | QuotedIdentifier | Comment | Whitespace )
        int alt23=85;
        alt23 = dfa23.predict(input);
        switch (alt23) {
            case 1 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:10: T__114
                {
                mT__114(); 

                }
                break;
            case 2 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:17: T__115
                {
                mT__115(); 

                }
                break;
            case 3 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:24: T__116
                {
                mT__116(); 

                }
                break;
            case 4 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:31: ALL
                {
                mALL(); 

                }
                break;
            case 5 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:35: AND
                {
                mAND(); 

                }
                break;
            case 6 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:39: ANY
                {
                mANY(); 

                }
                break;
            case 7 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:43: AS
                {
                mAS(); 

                }
                break;
            case 8 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:46: ASC
                {
                mASC(); 

                }
                break;
            case 9 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:50: BETWEEN
                {
                mBETWEEN(); 

                }
                break;
            case 10 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:58: BY
                {
                mBY(); 

                }
                break;
            case 11 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:61: CASE
                {
                mCASE(); 

                }
                break;
            case 12 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:66: DELETE
                {
                mDELETE(); 

                }
                break;
            case 13 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:73: DESC
                {
                mDESC(); 

                }
                break;
            case 14 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:78: DISTINCT
                {
                mDISTINCT(); 

                }
                break;
            case 15 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:87: ELSE
                {
                mELSE(); 

                }
                break;
            case 16 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:92: END
                {
                mEND(); 

                }
                break;
            case 17 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:96: EXISTS
                {
                mEXISTS(); 

                }
                break;
            case 18 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:103: FALSE
                {
                mFALSE(); 

                }
                break;
            case 19 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:109: FROM
                {
                mFROM(); 

                }
                break;
            case 20 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:114: FULL
                {
                mFULL(); 

                }
                break;
            case 21 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:119: GROUP
                {
                mGROUP(); 

                }
                break;
            case 22 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:125: HAVING
                {
                mHAVING(); 

                }
                break;
            case 23 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:132: IN
                {
                mIN(); 

                }
                break;
            case 24 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:135: INNER
                {
                mINNER(); 

                }
                break;
            case 25 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:141: INSERT
                {
                mINSERT(); 

                }
                break;
            case 26 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:148: INTO
                {
                mINTO(); 

                }
                break;
            case 27 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:153: IS
                {
                mIS(); 

                }
                break;
            case 28 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:156: JOIN
                {
                mJOIN(); 

                }
                break;
            case 29 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:161: LEFT
                {
                mLEFT(); 

                }
                break;
            case 30 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:166: LIKE
                {
                mLIKE(); 

                }
                break;
            case 31 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:171: NATURAL
                {
                mNATURAL(); 

                }
                break;
            case 32 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:179: NOT
                {
                mNOT(); 

                }
                break;
            case 33 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:183: NULL
                {
                mNULL(); 

                }
                break;
            case 34 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:188: ON
                {
                mON(); 

                }
                break;
            case 35 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:191: OR
                {
                mOR(); 

                }
                break;
            case 36 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:194: ORDER
                {
                mORDER(); 

                }
                break;
            case 37 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:200: OUTER
                {
                mOUTER(); 

                }
                break;
            case 38 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:206: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 39 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:214: RIGHT
                {
                mRIGHT(); 

                }
                break;
            case 40 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:220: SELECT
                {
                mSELECT(); 

                }
                break;
            case 41 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:227: SET
                {
                mSET(); 

                }
                break;
            case 42 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:231: SOME
                {
                mSOME(); 

                }
                break;
            case 43 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:236: THEN
                {
                mTHEN(); 

                }
                break;
            case 44 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:241: TRUE
                {
                mTRUE(); 

                }
                break;
            case 45 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:246: TOP
                {
                mTOP(); 

                }
                break;
            case 46 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:250: UNION
                {
                mUNION(); 

                }
                break;
            case 47 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:256: UNIQUE
                {
                mUNIQUE(); 

                }
                break;
            case 48 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:263: UPDATE
                {
                mUPDATE(); 

                }
                break;
            case 49 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:270: USING
                {
                mUSING(); 

                }
                break;
            case 50 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:276: VALUES
                {
                mVALUES(); 

                }
                break;
            case 51 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:283: WHEN
                {
                mWHEN(); 

                }
                break;
            case 52 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:288: WHERE
                {
                mWHERE(); 

                }
                break;
            case 53 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:294: MAX
                {
                mMAX(); 

                }
                break;
            case 54 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:298: MIN
                {
                mMIN(); 

                }
                break;
            case 55 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:302: COUNT
                {
                mCOUNT(); 

                }
                break;
            case 56 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:308: DOT
                {
                mDOT(); 

                }
                break;
            case 57 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:312: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 58 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:318: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 59 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:325: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 60 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:332: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 61 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:339: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 62 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:346: STRCAT
                {
                mSTRCAT(); 

                }
                break;
            case 63 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:353: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 64 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:362: COLON
                {
                mCOLON(); 

                }
                break;
            case 65 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:368: SEMI
                {
                mSEMI(); 

                }
                break;
            case 66 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:373: EQ
                {
                mEQ(); 

                }
                break;
            case 67 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:376: NEQ1
                {
                mNEQ1(); 

                }
                break;
            case 68 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:381: NEQ2
                {
                mNEQ2(); 

                }
                break;
            case 69 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:386: LTE
                {
                mLTE(); 

                }
                break;
            case 70 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:390: LT
                {
                mLT(); 

                }
                break;
            case 71 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:393: GTE
                {
                mGTE(); 

                }
                break;
            case 72 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:397: GT
                {
                mGT(); 

                }
                break;
            case 73 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:400: PLUS
                {
                mPLUS(); 

                }
                break;
            case 74 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:405: MINUS
                {
                mMINUS(); 

                }
                break;
            case 75 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:411: DIVIDE
                {
                mDIVIDE(); 

                }
                break;
            case 76 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:418: STAR
                {
                mSTAR(); 

                }
                break;
            case 77 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:423: MOD
                {
                mMOD(); 

                }
                break;
            case 78 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:427: Integer
                {
                mInteger(); 

                }
                break;
            case 79 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:435: Float
                {
                mFloat(); 

                }
                break;
            case 80 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:441: String
                {
                mString(); 

                }
                break;
            case 81 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:448: Timestamp
                {
                mTimestamp(); 

                }
                break;
            case 82 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:458: Identifier
                {
                mIdentifier(); 

                }
                break;
            case 83 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:469: QuotedIdentifier
                {
                mQuotedIdentifier(); 

                }
                break;
            case 84 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:486: Comment
                {
                mComment(); 

                }
                break;
            case 85 :
                // /Users/jasonosgood/Projects/workspace/fado/source/fado/parse/GenericSQL.g:1:494: Whitespace
                {
                mWhitespace(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    protected DFA23 dfa23 = new DFA23(this);
    static final String DFA8_eotS =
        "\5\uffff";
    static final String DFA8_eofS =
        "\5\uffff";
    static final String DFA8_minS =
        "\2\56\3\uffff";
    static final String DFA8_maxS =
        "\1\71\1\145\3\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\2\1\1\1\3";
    static final String DFA8_specialS =
        "\5\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1\13\uffff\1\4\37\uffff\1\4",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "497:1: Float : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? | '.' ( '0' .. '9' )+ ( Exponent )? | ( '0' .. '9' )+ Exponent );";
        }
    }
    static final String DFA23_eotS =
        "\1\uffff\1\60\25\53\1\133\11\uffff\1\137\1\uffff\1\141\1\uffff\1"+
        "\143\1\144\2\uffff\1\145\5\uffff\1\150\1\uffff\2\53\1\154\1\156"+
        "\15\53\1\175\1\u0081\6\53\1\u0088\1\53\1\u008b\16\53\13\uffff\1"+
        "\145\2\uffff\1\u009c\1\u009d\1\u009e\1\uffff\1\u009f\1\uffff\10"+
        "\53\1\u00a8\5\53\1\uffff\3\53\1\uffff\3\53\1\u00b4\2\53\1\uffff"+
        "\2\53\1\uffff\3\53\1\u00bc\2\53\1\u00bf\6\53\1\u00c8\1\u00c9\1\145"+
        "\4\uffff\2\53\1\u00cd\2\53\1\u00d0\1\53\1\u00d2\1\uffff\1\53\1\u00d4"+
        "\1\u00d5\4\53\1\u00da\1\u00db\1\u00dc\1\u00dd\1\uffff\1\u00de\5"+
        "\53\1\u00e4\1\uffff\1\53\1\u00e6\1\uffff\1\u00e7\5\53\1\u00ed\1"+
        "\53\2\uffff\1\145\1\53\1\u00f2\1\uffff\2\53\1\uffff\1\53\1\uffff"+
        "\1\u00f6\2\uffff\1\u00f7\2\53\1\u00fa\5\uffff\1\53\1\u00fc\1\u00fd"+
        "\1\53\1\u00ff\1\uffff\1\53\2\uffff\1\u0101\1\53\1\u0103\2\53\1\uffff"+
        "\1\u0106\1\145\1\uffff\1\53\1\uffff\1\53\1\u0109\1\u010a\2\uffff"+
        "\1\u010b\1\u010c\1\uffff\1\53\2\uffff\1\53\1\uffff\1\u010f\1\uffff"+
        "\1\u0110\1\uffff\1\u0111\1\u0112\1\uffff\1\u0113\1\53\4\uffff\1"+
        "\u0115\1\u0116\5\uffff\1\u0117\3\uffff";
    static final String DFA23_eofS =
        "\u0118\uffff";
    static final String DFA23_minS =
        "\1\11\1\144\1\114\1\105\1\101\1\105\1\114\1\101\1\122\1\101\1\116"+
        "\1\117\1\105\1\101\1\116\1\105\1\111\1\105\1\110\1\116\1\101\1\110"+
        "\1\101\1\60\11\uffff\1\75\1\uffff\1\75\1\uffff\1\55\1\52\2\uffff"+
        "\1\56\5\uffff\1\163\1\uffff\1\114\1\104\2\44\1\124\1\125\2\123\1"+
        "\114\1\111\1\123\1\104\2\114\2\117\1\126\2\44\1\111\1\106\1\113"+
        "\1\124\1\114\1\124\1\44\1\124\1\44\1\122\1\107\1\115\1\114\1\105"+
        "\1\120\1\125\1\111\1\104\1\111\1\114\1\105\1\116\1\130\13\uffff"+
        "\1\56\2\uffff\3\44\1\uffff\1\44\1\uffff\1\127\1\116\1\105\1\124"+
        "\1\105\1\103\1\123\1\105\1\44\1\123\1\114\1\115\1\125\1\111\1\uffff"+
        "\2\105\1\117\1\uffff\1\116\1\124\1\105\1\44\1\114\1\125\1\uffff"+
        "\2\105\1\uffff\1\103\1\110\1\105\1\44\1\105\1\116\1\44\1\105\1\116"+
        "\1\101\1\117\1\125\1\116\2\44\1\56\4\uffff\1\105\1\124\1\44\1\111"+
        "\1\124\1\44\1\124\1\44\1\uffff\1\105\2\44\1\120\1\116\2\122\4\44"+
        "\1\uffff\1\44\3\122\1\105\1\124\1\44\1\uffff\1\103\1\44\1\uffff"+
        "\1\44\1\107\1\124\1\116\1\125\1\105\1\44\1\105\2\uffff\1\55\1\105"+
        "\1\44\1\uffff\1\116\1\105\1\uffff\1\123\1\uffff\1\44\2\uffff\1\44"+
        "\1\107\1\124\1\44\5\uffff\1\101\2\44\1\116\1\44\1\uffff\1\124\2"+
        "\uffff\1\44\1\105\1\44\1\105\1\123\1\uffff\1\44\1\56\1\uffff\1\116"+
        "\1\uffff\1\103\2\44\2\uffff\2\44\1\uffff\1\114\2\uffff\1\124\1\uffff"+
        "\1\44\1\uffff\1\44\1\uffff\2\44\1\uffff\1\44\1\124\4\uffff\2\44"+
        "\5\uffff\1\44\3\uffff";
    static final String DFA23_maxS =
        "\1\175\1\164\1\163\1\171\1\157\1\151\1\170\1\165\1\162\1\141\1\163"+
        "\1\157\1\151\2\165\1\145\1\151\1\157\1\162\1\163\1\141\1\150\1\151"+
        "\1\71\11\uffff\1\76\1\uffff\1\75\1\uffff\1\55\1\57\2\uffff\1\145"+
        "\5\uffff\1\163\1\uffff\1\154\1\171\2\172\1\164\1\165\3\163\1\151"+
        "\1\163\1\144\2\154\2\157\1\166\2\172\1\151\1\146\1\153\1\164\1\154"+
        "\1\164\1\172\1\164\1\172\1\162\1\147\1\155\1\164\1\145\1\160\1\165"+
        "\1\151\1\144\1\151\1\154\1\145\1\156\1\170\13\uffff\1\145\2\uffff"+
        "\3\172\1\uffff\1\172\1\uffff\1\167\1\156\1\145\1\164\1\145\1\143"+
        "\1\163\1\145\1\172\1\163\1\154\1\155\1\165\1\151\1\uffff\2\145\1"+
        "\157\1\uffff\1\156\1\164\1\145\1\172\1\154\1\165\1\uffff\2\145\1"+
        "\uffff\1\143\1\150\1\145\1\172\1\145\1\156\1\172\1\145\1\156\1\141"+
        "\1\161\1\165\1\162\2\172\1\145\4\uffff\1\145\1\164\1\172\1\151\1"+
        "\164\1\172\1\164\1\172\1\uffff\1\145\2\172\1\160\1\156\2\162\4\172"+
        "\1\uffff\1\172\3\162\1\145\1\164\1\172\1\uffff\1\143\1\172\1\uffff"+
        "\1\172\1\147\1\164\1\156\1\165\1\145\1\172\1\145\2\uffff\2\145\1"+
        "\172\1\uffff\1\156\1\145\1\uffff\1\163\1\uffff\1\172\2\uffff\1\172"+
        "\1\147\1\164\1\172\5\uffff\1\141\2\172\1\156\1\172\1\uffff\1\164"+
        "\2\uffff\1\172\1\145\1\172\1\145\1\163\1\uffff\1\172\1\145\1\uffff"+
        "\1\156\1\uffff\1\143\2\172\2\uffff\2\172\1\uffff\1\154\2\uffff\1"+
        "\164\1\uffff\1\172\1\uffff\1\172\1\uffff\2\172\1\uffff\1\172\1\164"+
        "\4\uffff\2\172\5\uffff\1\172\3\uffff";
    static final String DFA23_acceptS =
        "\30\uffff\1\71\1\72\1\73\1\75\1\76\1\77\1\100\1\101\1\102\1\uffff"+
        "\1\104\1\uffff\1\111\2\uffff\1\114\1\115\1\uffff\1\120\1\122\1\123"+
        "\1\125\1\1\1\uffff\1\74\52\uffff\1\70\1\117\1\103\1\105\1\106\1"+
        "\107\1\110\1\124\1\112\1\113\1\116\1\uffff\1\3\1\2\3\uffff\1\7\1"+
        "\uffff\1\12\16\uffff\1\27\3\uffff\1\33\6\uffff\1\43\2\uffff\1\42"+
        "\20\uffff\1\4\1\5\1\6\1\10\10\uffff\1\20\13\uffff\1\40\7\uffff\1"+
        "\51\2\uffff\1\55\10\uffff\1\66\1\65\3\uffff\1\13\2\uffff\1\15\1"+
        "\uffff\1\17\1\uffff\1\24\1\23\4\uffff\1\32\1\34\1\35\1\36\1\41\5"+
        "\uffff\1\52\1\uffff\1\53\1\54\5\uffff\1\63\2\uffff\1\121\1\uffff"+
        "\1\67\3\uffff\1\22\1\25\2\uffff\1\30\1\uffff\1\44\1\45\1\uffff\1"+
        "\47\1\uffff\1\61\1\uffff\1\56\2\uffff\1\64\2\uffff\1\14\1\21\1\26"+
        "\1\31\2\uffff\1\50\1\60\1\57\1\62\1\11\1\uffff\1\37\1\46\1\16";
    static final String DFA23_specialS =
        "\u0118\uffff}>";
    static final String[] DFA23_transitionS = {
            "\2\55\2\uffff\1\55\22\uffff\1\55\1\42\1\54\2\uffff\1\50\1\uffff"+
            "\1\52\1\31\1\32\1\47\1\44\1\30\1\45\1\27\1\46\12\51\1\36\1\37"+
            "\1\41\1\40\1\43\1\35\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1"+
            "\11\1\12\1\13\1\53\1\14\1\26\1\15\1\16\1\17\1\53\1\20\1\21\1"+
            "\22\1\23\1\24\1\25\3\53\1\54\5\uffff\1\2\1\3\1\4\1\5\1\6\1\7"+
            "\1\10\1\11\1\12\1\13\1\53\1\14\1\26\1\15\1\16\1\17\1\53\1\20"+
            "\1\21\1\22\1\23\1\24\1\25\3\53\1\1\1\34\1\33",
            "\1\56\17\uffff\1\57",
            "\1\61\1\uffff\1\62\4\uffff\1\63\30\uffff\1\61\1\uffff\1\62"+
            "\4\uffff\1\63",
            "\1\65\23\uffff\1\64\13\uffff\1\65\23\uffff\1\64",
            "\1\67\15\uffff\1\66\21\uffff\1\67\15\uffff\1\66",
            "\1\71\3\uffff\1\70\33\uffff\1\71\3\uffff\1\70",
            "\1\73\1\uffff\1\74\11\uffff\1\72\23\uffff\1\73\1\uffff\1\74"+
            "\11\uffff\1\72",
            "\1\75\20\uffff\1\77\2\uffff\1\76\13\uffff\1\75\20\uffff\1\77"+
            "\2\uffff\1\76",
            "\1\100\37\uffff\1\100",
            "\1\101\37\uffff\1\101",
            "\1\102\4\uffff\1\103\32\uffff\1\102\4\uffff\1\103",
            "\1\104\37\uffff\1\104",
            "\1\105\3\uffff\1\106\33\uffff\1\105\3\uffff\1\106",
            "\1\111\15\uffff\1\107\5\uffff\1\110\13\uffff\1\111\15\uffff"+
            "\1\107\5\uffff\1\110",
            "\1\114\3\uffff\1\112\2\uffff\1\113\30\uffff\1\114\3\uffff\1"+
            "\112\2\uffff\1\113",
            "\1\115\37\uffff\1\115",
            "\1\116\37\uffff\1\116",
            "\1\120\11\uffff\1\117\25\uffff\1\120\11\uffff\1\117",
            "\1\121\6\uffff\1\122\2\uffff\1\123\25\uffff\1\121\6\uffff\1"+
            "\122\2\uffff\1\123",
            "\1\126\1\uffff\1\125\2\uffff\1\124\32\uffff\1\126\1\uffff\1"+
            "\125\2\uffff\1\124",
            "\1\127\37\uffff\1\127",
            "\1\130\37\uffff\1\130",
            "\1\132\7\uffff\1\131\27\uffff\1\132\7\uffff\1\131",
            "\12\134",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\136\1\135",
            "",
            "\1\140",
            "",
            "\1\142",
            "\1\142\4\uffff\1\142",
            "",
            "",
            "\1\134\1\uffff\12\146\13\uffff\1\134\37\uffff\1\134",
            "",
            "",
            "",
            "",
            "",
            "\1\147",
            "",
            "\1\151\37\uffff\1\151",
            "\1\152\24\uffff\1\153\12\uffff\1\152\24\uffff\1\153",
            "\1\53\13\uffff\12\53\7\uffff\2\53\1\155\27\53\4\uffff\1\53"+
            "\1\uffff\2\53\1\155\27\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\157\37\uffff\1\157",
            "\1\160\37\uffff\1\160",
            "\1\161\37\uffff\1\161",
            "\1\162\37\uffff\1\162",
            "\1\163\6\uffff\1\164\30\uffff\1\163\6\uffff\1\164",
            "\1\165\37\uffff\1\165",
            "\1\166\37\uffff\1\166",
            "\1\167\37\uffff\1\167",
            "\1\170\37\uffff\1\170",
            "\1\171\37\uffff\1\171",
            "\1\172\37\uffff\1\172",
            "\1\173\37\uffff\1\173",
            "\1\174\37\uffff\1\174",
            "\1\53\13\uffff\12\53\7\uffff\15\53\1\177\4\53\1\176\1\u0080"+
            "\6\53\4\uffff\1\53\1\uffff\15\53\1\177\4\53\1\176\1\u0080\6"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u0082\37\uffff\1\u0082",
            "\1\u0083\37\uffff\1\u0083",
            "\1\u0084\37\uffff\1\u0084",
            "\1\u0085\37\uffff\1\u0085",
            "\1\u0086\37\uffff\1\u0086",
            "\1\u0087\37\uffff\1\u0087",
            "\1\53\13\uffff\12\53\7\uffff\3\53\1\u0089\26\53\4\uffff\1\53"+
            "\1\uffff\3\53\1\u0089\26\53",
            "\1\u008a\37\uffff\1\u008a",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u008c\37\uffff\1\u008c",
            "\1\u008d\37\uffff\1\u008d",
            "\1\u008e\37\uffff\1\u008e",
            "\1\u0090\7\uffff\1\u008f\27\uffff\1\u0090\7\uffff\1\u008f",
            "\1\u0091\37\uffff\1\u0091",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\37\uffff\1\u0094",
            "\1\u0095\37\uffff\1\u0095",
            "\1\u0096\37\uffff\1\u0096",
            "\1\u0097\37\uffff\1\u0097",
            "\1\u0098\37\uffff\1\u0098",
            "\1\u0099\37\uffff\1\u0099",
            "\1\u009a\37\uffff\1\u009a",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\134\1\uffff\12\u009b\13\uffff\1\134\37\uffff\1\134",
            "",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\u00a2\37\uffff\1\u00a2",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\u00a4\37\uffff\1\u00a4",
            "\1\u00a5\37\uffff\1\u00a5",
            "\1\u00a6\37\uffff\1\u00a6",
            "\1\u00a7\37\uffff\1\u00a7",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00a9\37\uffff\1\u00a9",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\u00ab\37\uffff\1\u00ab",
            "\1\u00ac\37\uffff\1\u00ac",
            "\1\u00ad\37\uffff\1\u00ad",
            "",
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b0\37\uffff\1\u00b0",
            "",
            "\1\u00b1\37\uffff\1\u00b1",
            "\1\u00b2\37\uffff\1\u00b2",
            "\1\u00b3\37\uffff\1\u00b3",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00b5\37\uffff\1\u00b5",
            "\1\u00b6\37\uffff\1\u00b6",
            "",
            "\1\u00b7\37\uffff\1\u00b7",
            "\1\u00b8\37\uffff\1\u00b8",
            "",
            "\1\u00b9\37\uffff\1\u00b9",
            "\1\u00ba\37\uffff\1\u00ba",
            "\1\u00bb\37\uffff\1\u00bb",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00bd\37\uffff\1\u00bd",
            "\1\u00be\37\uffff\1\u00be",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00c0\37\uffff\1\u00c0",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c2\37\uffff\1\u00c2",
            "\1\u00c3\1\uffff\1\u00c4\35\uffff\1\u00c3\1\uffff\1\u00c4",
            "\1\u00c5\37\uffff\1\u00c5",
            "\1\u00c6\3\uffff\1\u00c7\33\uffff\1\u00c6\3\uffff\1\u00c7",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\134\1\uffff\12\u00ca\13\uffff\1\134\37\uffff\1\134",
            "",
            "",
            "",
            "",
            "\1\u00cb\37\uffff\1\u00cb",
            "\1\u00cc\37\uffff\1\u00cc",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00ce\37\uffff\1\u00ce",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\u00d3\37\uffff\1\u00d3",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00d6\37\uffff\1\u00d6",
            "\1\u00d7\37\uffff\1\u00d7",
            "\1\u00d8\37\uffff\1\u00d8",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00df\37\uffff\1\u00df",
            "\1\u00e0\37\uffff\1\u00e0",
            "\1\u00e1\37\uffff\1\u00e1",
            "\1\u00e2\37\uffff\1\u00e2",
            "\1\u00e3\37\uffff\1\u00e3",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\u00e5\37\uffff\1\u00e5",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00e8\37\uffff\1\u00e8",
            "\1\u00e9\37\uffff\1\u00e9",
            "\1\u00ea\37\uffff\1\u00ea",
            "\1\u00eb\37\uffff\1\u00eb",
            "\1\u00ec\37\uffff\1\u00ec",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00ee\37\uffff\1\u00ee",
            "",
            "",
            "\1\u00f0\1\134\1\uffff\12\u00ef\13\uffff\1\134\37\uffff\1\134",
            "\1\u00f1\37\uffff\1\u00f1",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\u00f3\37\uffff\1\u00f3",
            "\1\u00f4\37\uffff\1\u00f4",
            "",
            "\1\u00f5\37\uffff\1\u00f5",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00f8\37\uffff\1\u00f8",
            "\1\u00f9\37\uffff\1\u00f9",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "",
            "",
            "",
            "",
            "\1\u00fb\37\uffff\1\u00fb",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u00fe\37\uffff\1\u00fe",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\u0100\37\uffff\1\u0100",
            "",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u0102\37\uffff\1\u0102",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u0104\37\uffff\1\u0104",
            "\1\u0105\37\uffff\1\u0105",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\134\1\uffff\12\u00ef\13\uffff\1\134\37\uffff\1\134",
            "",
            "\1\u0107\37\uffff\1\u0107",
            "",
            "\1\u0108\37\uffff\1\u0108",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\u010d\37\uffff\1\u010d",
            "",
            "",
            "\1\u010e\37\uffff\1\u010e",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\u0114\37\uffff\1\u0114",
            "",
            "",
            "",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "",
            "",
            "",
            "",
            "\1\53\13\uffff\12\53\7\uffff\32\53\4\uffff\1\53\1\uffff\32"+
            "\53",
            "",
            "",
            ""
    };

    static final short[] DFA23_eot = DFA.unpackEncodedString(DFA23_eotS);
    static final short[] DFA23_eof = DFA.unpackEncodedString(DFA23_eofS);
    static final char[] DFA23_min = DFA.unpackEncodedStringToUnsignedChars(DFA23_minS);
    static final char[] DFA23_max = DFA.unpackEncodedStringToUnsignedChars(DFA23_maxS);
    static final short[] DFA23_accept = DFA.unpackEncodedString(DFA23_acceptS);
    static final short[] DFA23_special = DFA.unpackEncodedString(DFA23_specialS);
    static final short[][] DFA23_transition;

    static {
        int numStates = DFA23_transitionS.length;
        DFA23_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA23_transition[i] = DFA.unpackEncodedString(DFA23_transitionS[i]);
        }
    }

    class DFA23 extends DFA {

        public DFA23(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 23;
            this.eot = DFA23_eot;
            this.eof = DFA23_eof;
            this.min = DFA23_min;
            this.max = DFA23_max;
            this.accept = DFA23_accept;
            this.special = DFA23_special;
            this.transition = DFA23_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__114 | T__115 | T__116 | ALL | AND | ANY | AS | ASC | BETWEEN | BY | CASE | DELETE | DESC | DISTINCT | ELSE | END | EXISTS | FALSE | FROM | FULL | GROUP | HAVING | IN | INNER | INSERT | INTO | IS | JOIN | LEFT | LIKE | NATURAL | NOT | NULL | ON | OR | ORDER | OUTER | PERCENT | RIGHT | SELECT | SET | SOME | THEN | TRUE | TOP | UNION | UNIQUE | UPDATE | USING | VALUES | WHEN | WHERE | MAX | MIN | COUNT | DOT | COMMA | LPAREN | RPAREN | LCURLY | RCURLY | STRCAT | QUESTION | COLON | SEMI | EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT | PLUS | MINUS | DIVIDE | STAR | MOD | Integer | Float | String | Timestamp | Identifier | QuotedIdentifier | Comment | Whitespace );";
        }
    }
 

}