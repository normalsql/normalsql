// $ANTLR 3.2 Sep 23, 2009 12:02:23 /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g 2011-03-09 21:30:19
 
package fado.parse; 


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class GenericSQLLexer extends Lexer {
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
    public static final int F=78;
    public static final int UNIQUE=10;
    public static final int G=83;
    public static final int A=61;
    public static final int B=68;
    public static final int C=67;
    public static final int ASC=34;
    public static final int L=62;
    public static final int M=81;
    public static final int N=63;
    public static final int O=80;
    public static final int H=85;
    public static final int I=74;
    public static final int NULL=42;
    public static final int J=88;
    public static final int ELSE=75;
    public static final int K=89;
    public static final int U=82;
    public static final int T=70;
    public static final int ON=27;
    public static final int W=71;
    public static final int V=86;
    public static final int LCURLY=97;
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
    public static final int SEMI=4;
    public static final int TRUE=91;
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

    public GenericSQLLexer() {;} 
    public GenericSQLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public GenericSQLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "/Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g"; }

    // $ANTLR start "T__107"
    public final void mT__107() throws RecognitionException {
        try {
            int _type = T__107;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:11:8: ( '{d' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:11:10: '{d'
            {
            match("{d"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__107"

    // $ANTLR start "T__108"
    public final void mT__108() throws RecognitionException {
        try {
            int _type = T__108;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:12:8: ( '{t' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:12:10: '{t'
            {
            match("{t"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__108"

    // $ANTLR start "T__109"
    public final void mT__109() throws RecognitionException {
        try {
            int _type = T__109;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:13:8: ( '{ts' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:13:10: '{ts'
            {
            match("{ts"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__109"

    // $ANTLR start "ALL"
    public final void mALL() throws RecognitionException {
        try {
            int _type = ALL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:318:11: ( A L L )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:318:13: A L L
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:319:11: ( A N D )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:319:13: A N D
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:320:11: ( A N Y )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:320:13: A N Y
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:321:11: ( A S )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:321:13: A S
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:322:11: ( A S C )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:322:13: A S C
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:323:11: ( B E T W E E N )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:323:13: B E T W E E N
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:324:11: ( B Y )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:324:13: B Y
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:325:11: ( C A S E )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:325:13: C A S E
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:331:11: ( D E L E T E )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:331:13: D E L E T E
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:332:11: ( D E S C )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:332:13: D E S C
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:333:11: ( D I S T I N C T )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:333:13: D I S T I N C T
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:334:11: ( E L S E )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:334:13: E L S E
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:335:11: ( E N D )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:335:13: E N D
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:337:11: ( E X I S T S )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:337:13: E X I S T S
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

    // $ANTLR start "FROM"
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:342:11: ( F R O M )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:342:13: F R O M
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:343:11: ( F U L L )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:343:13: F U L L
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:344:11: ( G R O U P )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:344:13: G R O U P
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:345:11: ( H A V I N G )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:345:13: H A V I N G
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:347:11: ( I N )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:347:13: I N
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:348:11: ( I N N E R )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:348:13: I N N E R
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:349:11: ( I N S E R T )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:349:13: I N S E R T
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:351:11: ( I N T O )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:351:13: I N T O
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:352:11: ( I S )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:352:13: I S
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:353:11: ( J O I N )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:353:13: J O I N
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:354:11: ( L E F T )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:354:13: L E F T
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:355:11: ( L I K E )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:355:13: L I K E
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

    // $ANTLR start "NOT"
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:358:11: ( N O T )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:358:13: N O T
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:359:11: ( N U L L )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:359:13: N U L L
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:360:11: ( O N )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:360:13: O N
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:361:11: ( O R )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:361:13: O R
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:362:11: ( O R D E R )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:362:13: O R D E R
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:363:11: ( O U T E R )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:363:13: O U T E R
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:364:11: ( P E R C E N T )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:364:13: P E R C E N T
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:365:11: ( R I G H T )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:365:13: R I G H T
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:367:11: ( S E L E C T )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:367:13: S E L E C T
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

    // $ANTLR start "SOME"
    public final void mSOME() throws RecognitionException {
        try {
            int _type = SOME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:369:11: ( S O M E )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:369:13: S O M E
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:371:11: ( T H E N )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:371:13: T H E N
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:372:11: ( T R U E )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:372:13: T R U E
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:374:11: ( T O P )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:374:13: T O P
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:375:11: ( U N I O N )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:375:13: U N I O N
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:376:11: ( U N I Q U E )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:376:13: U N I Q U E
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:377:11: ( U P D A T E )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:377:13: U P D A T E
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:378:11: ( U S I N G )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:378:13: U S I N G
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

    // $ANTLR start "WHEN"
    public final void mWHEN() throws RecognitionException {
        try {
            int _type = WHEN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:380:11: ( W H E N )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:380:13: W H E N
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:381:11: ( W H E R E )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:381:13: W H E R E
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

    // $ANTLR start "A"
    public final void mA() throws RecognitionException {
        try {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:384:11: ( ( 'a' | 'A' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:384:12: ( 'a' | 'A' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:385:11: ( ( 'b' | 'B' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:385:12: ( 'b' | 'B' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:386:11: ( ( 'c' | 'C' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:386:12: ( 'c' | 'C' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:387:11: ( ( 'd' | 'D' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:387:12: ( 'd' | 'D' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:388:11: ( ( 'e' | 'E' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:388:12: ( 'e' | 'E' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:389:11: ( ( 'f' | 'F' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:389:12: ( 'f' | 'F' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:390:11: ( ( 'g' | 'G' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:390:12: ( 'g' | 'G' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:391:11: ( ( 'h' | 'H' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:391:12: ( 'h' | 'H' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:392:11: ( ( 'i' | 'I' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:392:12: ( 'i' | 'I' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:393:11: ( ( 'j' | 'J' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:393:12: ( 'j' | 'J' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:394:11: ( ( 'k' | 'K' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:394:12: ( 'k' | 'K' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:395:11: ( ( 'l' | 'L' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:395:12: ( 'l' | 'L' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:396:11: ( ( 'm' | 'M' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:396:12: ( 'm' | 'M' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:397:11: ( ( 'n' | 'N' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:397:12: ( 'n' | 'N' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:398:11: ( ( 'o' | 'O' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:398:12: ( 'o' | 'O' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:399:11: ( ( 'p' | 'P' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:399:12: ( 'p' | 'P' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:400:11: ( ( 'q' | 'Q' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:400:12: ( 'q' | 'Q' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:401:11: ( ( 'r' | 'R' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:401:12: ( 'r' | 'R' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:402:11: ( ( 's' | 'S' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:402:12: ( 's' | 'S' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:403:11: ( ( 't' | 'T' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:403:12: ( 't' | 'T' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:404:11: ( ( 'u' | 'U' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:404:12: ( 'u' | 'U' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:405:11: ( ( 'v' | 'V' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:405:12: ( 'v' | 'V' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:406:11: ( ( 'w' | 'W' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:406:12: ( 'w' | 'W' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:407:11: ( ( 'x' | 'X' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:407:12: ( 'x' | 'X' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:408:11: ( ( 'y' | 'Y' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:408:12: ( 'y' | 'Y' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:409:11: ( ( 'z' | 'Z' ) )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:409:12: ( 'z' | 'Z' )
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:411:10: ( '.' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:411:12: '.'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:412:10: ( ',' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:412:12: ','
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:413:10: ( '(' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:413:12: '('
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:414:10: ( ')' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:414:12: ')'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:415:10: ( '{' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:415:12: '{'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:416:10: ( '}' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:416:12: '}'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:417:10: ( '||' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:417:12: '||'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:418:10: ( '?' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:418:12: '?'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:419:10: ( ':' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:419:12: ':'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:420:10: ( ';' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:420:12: ';'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:422:10: ( '=' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:422:12: '='
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:423:10: ( '<>' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:423:12: '<>'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:424:10: ( '!=' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:424:12: '!='
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:425:10: ( '<=' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:425:12: '<='
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:426:10: ( '<' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:426:12: '<'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:427:10: ( '>=' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:427:12: '>='
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:428:10: ( '>' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:428:12: '>'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:430:10: ( '+' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:430:12: '+'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:431:10: ( '-' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:431:12: '-'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:432:10: ( '/' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:432:12: '/'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:433:10: ( '*' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:433:12: '*'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:434:10: ( '%' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:434:12: '%'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:437:7: ( '0' .. '9' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:437:9: '0' .. '9'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:440:3: ( ( Digit )+ )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:440:5: ( Digit )+
            {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:440:5: ( Digit )+
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
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:440:7: Digit
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:444:3: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? | '.' ( '0' .. '9' )+ ( Exponent )? | ( '0' .. '9' )+ Exponent )
            int alt8=3;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:444:5: ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )?
                    {
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:444:5: ( '0' .. '9' )+
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
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:444:6: '0' .. '9'
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
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:444:21: ( '0' .. '9' )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:444:22: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:444:33: ( Exponent )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0=='E'||LA4_0=='e') ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:444:33: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:445:5: '.' ( '0' .. '9' )+ ( Exponent )?
                    {
                    match('.'); 
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:445:9: ( '0' .. '9' )+
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
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:445:10: '0' .. '9'
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:445:21: ( Exponent )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0=='E'||LA6_0=='e') ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:445:21: Exponent
                            {
                            mExponent(); 

                            }
                            break;

                    }


                    }
                    break;
                case 3 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:446:5: ( '0' .. '9' )+ Exponent
                    {
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:446:5: ( '0' .. '9' )+
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
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:446:6: '0' .. '9'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:451:3: ( ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+ )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:451:5: ( 'e' | 'E' ) ( '+' | '-' )? ( '0' .. '9' )+
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:451:15: ( '+' | '-' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0=='+'||LA9_0=='-') ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
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

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:451:26: ( '0' .. '9' )+
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
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:451:27: '0' .. '9'
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:455:3: ( '\\'' ( options {greedy=false; } : . )* '\\'' )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:455:5: '\\'' ( options {greedy=false; } : . )* '\\''
            {
            match('\''); 
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:455:10: ( options {greedy=false; } : . )*
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
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:455:38: .
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:459:3: ( Digit Digit Digit Digit '-' Digit Digit '-' Digit Digit ( 't' | ' ' ) Digit Digit ':' Digit Digit ':' Digit Digit )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:459:5: Digit Digit Digit Digit '-' Digit Digit '-' Digit Digit ( 't' | ' ' ) Digit Digit ':' Digit Digit ':' Digit Digit
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:466:3: ( ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '$' )* )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:466:5: ( 'a' .. 'z' | 'A' .. 'Z' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '$' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:466:25: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '$' )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='$'||(LA12_0>='0' && LA12_0<='9')||(LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='z')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:470:3: ( '[' ( options {greedy=false; } : . )* ']' | '\"' ( options {greedy=false; } : . )* '\"' )
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
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:470:5: '[' ( options {greedy=false; } : . )* ']'
                    {
                    match('['); 
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:470:9: ( options {greedy=false; } : . )*
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
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:470:37: .
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
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:471:5: '\"' ( options {greedy=false; } : . )* '\"'
                    {
                    match('\"'); 
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:471:9: ( options {greedy=false; } : . )*
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
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:471:37: .
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:475:3: ( '--' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n' | '/*' ( options {greedy=false; } : . )* '*/' )
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
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:475:5: '--' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    match("--"); 

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:475:10: (~ ( '\\n' | '\\r' ) )*
                    loop16:
                    do {
                        int alt16=2;
                        int LA16_0 = input.LA(1);

                        if ( ((LA16_0>='\u0000' && LA16_0<='\t')||(LA16_0>='\u000B' && LA16_0<='\f')||(LA16_0>='\u000E' && LA16_0<='\uFFFF')) ) {
                            alt16=1;
                        }


                        switch (alt16) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:475:10: ~ ( '\\n' | '\\r' )
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:475:24: ( '\\r' )?
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0=='\r') ) {
                        alt17=1;
                    }
                    switch (alt17) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:475:24: '\\r'
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
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:476:5: '//' (~ ( '\\n' | '\\r' ) )* ( '\\r' )? '\\n'
                    {
                    match("//"); 

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:476:10: (~ ( '\\n' | '\\r' ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( ((LA18_0>='\u0000' && LA18_0<='\t')||(LA18_0>='\u000B' && LA18_0<='\f')||(LA18_0>='\u000E' && LA18_0<='\uFFFF')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:476:10: ~ ( '\\n' | '\\r' )
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

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:476:24: ( '\\r' )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0=='\r') ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:476:24: '\\r'
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
                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:477:5: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 

                    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:477:10: ( options {greedy=false; } : . )*
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
                    	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:477:38: .
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
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:481:3: ( ( '\\t' | ' ' | '\\r' | '\\n' )+ )
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:481:5: ( '\\t' | ' ' | '\\r' | '\\n' )+
            {
            // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:481:5: ( '\\t' | ' ' | '\\r' | '\\n' )+
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
            	    // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:
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
        // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:8: ( T__107 | T__108 | T__109 | ALL | AND | ANY | AS | ASC | BETWEEN | BY | CASE | DELETE | DESC | DISTINCT | ELSE | END | EXISTS | FROM | FULL | GROUP | HAVING | IN | INNER | INSERT | INTO | IS | JOIN | LEFT | LIKE | NOT | NULL | ON | OR | ORDER | OUTER | PERCENT | RIGHT | SELECT | SOME | THEN | TRUE | TOP | UNION | UNIQUE | UPDATE | USING | WHEN | WHERE | DOT | COMMA | LPAREN | RPAREN | LCURLY | RCURLY | STRCAT | QUESTION | COLON | SEMI | EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT | PLUS | MINUS | DIVIDE | STAR | MOD | Integer | Float | String | Timestamp | Identifier | QuotedIdentifier | Comment | Whitespace )
        int alt23=78;
        alt23 = dfa23.predict(input);
        switch (alt23) {
            case 1 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:10: T__107
                {
                mT__107(); 

                }
                break;
            case 2 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:17: T__108
                {
                mT__108(); 

                }
                break;
            case 3 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:24: T__109
                {
                mT__109(); 

                }
                break;
            case 4 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:31: ALL
                {
                mALL(); 

                }
                break;
            case 5 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:35: AND
                {
                mAND(); 

                }
                break;
            case 6 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:39: ANY
                {
                mANY(); 

                }
                break;
            case 7 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:43: AS
                {
                mAS(); 

                }
                break;
            case 8 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:46: ASC
                {
                mASC(); 

                }
                break;
            case 9 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:50: BETWEEN
                {
                mBETWEEN(); 

                }
                break;
            case 10 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:58: BY
                {
                mBY(); 

                }
                break;
            case 11 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:61: CASE
                {
                mCASE(); 

                }
                break;
            case 12 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:66: DELETE
                {
                mDELETE(); 

                }
                break;
            case 13 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:73: DESC
                {
                mDESC(); 

                }
                break;
            case 14 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:78: DISTINCT
                {
                mDISTINCT(); 

                }
                break;
            case 15 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:87: ELSE
                {
                mELSE(); 

                }
                break;
            case 16 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:92: END
                {
                mEND(); 

                }
                break;
            case 17 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:96: EXISTS
                {
                mEXISTS(); 

                }
                break;
            case 18 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:103: FROM
                {
                mFROM(); 

                }
                break;
            case 19 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:108: FULL
                {
                mFULL(); 

                }
                break;
            case 20 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:113: GROUP
                {
                mGROUP(); 

                }
                break;
            case 21 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:119: HAVING
                {
                mHAVING(); 

                }
                break;
            case 22 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:126: IN
                {
                mIN(); 

                }
                break;
            case 23 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:129: INNER
                {
                mINNER(); 

                }
                break;
            case 24 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:135: INSERT
                {
                mINSERT(); 

                }
                break;
            case 25 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:142: INTO
                {
                mINTO(); 

                }
                break;
            case 26 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:147: IS
                {
                mIS(); 

                }
                break;
            case 27 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:150: JOIN
                {
                mJOIN(); 

                }
                break;
            case 28 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:155: LEFT
                {
                mLEFT(); 

                }
                break;
            case 29 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:160: LIKE
                {
                mLIKE(); 

                }
                break;
            case 30 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:165: NOT
                {
                mNOT(); 

                }
                break;
            case 31 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:169: NULL
                {
                mNULL(); 

                }
                break;
            case 32 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:174: ON
                {
                mON(); 

                }
                break;
            case 33 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:177: OR
                {
                mOR(); 

                }
                break;
            case 34 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:180: ORDER
                {
                mORDER(); 

                }
                break;
            case 35 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:186: OUTER
                {
                mOUTER(); 

                }
                break;
            case 36 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:192: PERCENT
                {
                mPERCENT(); 

                }
                break;
            case 37 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:200: RIGHT
                {
                mRIGHT(); 

                }
                break;
            case 38 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:206: SELECT
                {
                mSELECT(); 

                }
                break;
            case 39 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:213: SOME
                {
                mSOME(); 

                }
                break;
            case 40 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:218: THEN
                {
                mTHEN(); 

                }
                break;
            case 41 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:223: TRUE
                {
                mTRUE(); 

                }
                break;
            case 42 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:228: TOP
                {
                mTOP(); 

                }
                break;
            case 43 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:232: UNION
                {
                mUNION(); 

                }
                break;
            case 44 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:238: UNIQUE
                {
                mUNIQUE(); 

                }
                break;
            case 45 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:245: UPDATE
                {
                mUPDATE(); 

                }
                break;
            case 46 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:252: USING
                {
                mUSING(); 

                }
                break;
            case 47 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:258: WHEN
                {
                mWHEN(); 

                }
                break;
            case 48 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:263: WHERE
                {
                mWHERE(); 

                }
                break;
            case 49 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:269: DOT
                {
                mDOT(); 

                }
                break;
            case 50 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:273: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 51 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:279: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 52 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:286: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 53 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:293: LCURLY
                {
                mLCURLY(); 

                }
                break;
            case 54 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:300: RCURLY
                {
                mRCURLY(); 

                }
                break;
            case 55 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:307: STRCAT
                {
                mSTRCAT(); 

                }
                break;
            case 56 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:314: QUESTION
                {
                mQUESTION(); 

                }
                break;
            case 57 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:323: COLON
                {
                mCOLON(); 

                }
                break;
            case 58 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:329: SEMI
                {
                mSEMI(); 

                }
                break;
            case 59 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:334: EQ
                {
                mEQ(); 

                }
                break;
            case 60 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:337: NEQ1
                {
                mNEQ1(); 

                }
                break;
            case 61 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:342: NEQ2
                {
                mNEQ2(); 

                }
                break;
            case 62 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:347: LTE
                {
                mLTE(); 

                }
                break;
            case 63 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:351: LT
                {
                mLT(); 

                }
                break;
            case 64 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:354: GTE
                {
                mGTE(); 

                }
                break;
            case 65 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:358: GT
                {
                mGT(); 

                }
                break;
            case 66 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:361: PLUS
                {
                mPLUS(); 

                }
                break;
            case 67 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:366: MINUS
                {
                mMINUS(); 

                }
                break;
            case 68 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:372: DIVIDE
                {
                mDIVIDE(); 

                }
                break;
            case 69 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:379: STAR
                {
                mSTAR(); 

                }
                break;
            case 70 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:384: MOD
                {
                mMOD(); 

                }
                break;
            case 71 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:388: Integer
                {
                mInteger(); 

                }
                break;
            case 72 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:396: Float
                {
                mFloat(); 

                }
                break;
            case 73 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:402: String
                {
                mString(); 

                }
                break;
            case 74 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:409: Timestamp
                {
                mTimestamp(); 

                }
                break;
            case 75 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:419: Identifier
                {
                mIdentifier(); 

                }
                break;
            case 76 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:430: QuotedIdentifier
                {
                mQuotedIdentifier(); 

                }
                break;
            case 77 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:447: Comment
                {
                mComment(); 

                }
                break;
            case 78 :
                // /Users/jasonosgood/Projects/fado/source/fado/parse/GenericSQL.g:1:455: Whitespace
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
        "\2\uffff\1\2\1\3\1\1";
    static final String DFA8_specialS =
        "\5\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\4\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
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
            return "443:1: Float : ( ( '0' .. '9' )+ '.' ( '0' .. '9' )* ( Exponent )? | '.' ( '0' .. '9' )+ ( Exponent )? | ( '0' .. '9' )+ Exponent );";
        }
    }
    static final String DFA23_eotS =
        "\1\uffff\1\56\23\51\1\124\11\uffff\1\127\1\uffff\1\131\1\uffff\1"+
        "\133\1\134\2\uffff\1\136\5\uffff\1\140\1\uffff\2\51\1\144\1\51\1"+
        "\147\12\51\1\163\1\167\5\51\1\175\1\177\14\51\12\uffff\1\136\3\uffff"+
        "\1\u008d\1\u008e\1\u008f\1\uffff\1\u0090\1\51\1\uffff\5\51\1\u0097"+
        "\5\51\1\uffff\3\51\1\uffff\4\51\1\u00a4\1\uffff\1\51\1\uffff\5\51"+
        "\1\u00ab\6\51\1\136\4\uffff\1\51\1\u00b6\1\51\1\u00b8\2\51\1\uffff"+
        "\1\u00bb\1\u00bc\1\u00bd\2\51\1\u00c0\2\51\1\u00c3\1\u00c4\1\u00c5"+
        "\1\u00c6\1\uffff\5\51\1\u00cc\1\uffff\1\u00cd\1\u00ce\5\51\1\u00d4"+
        "\1\136\1\51\1\uffff\1\51\1\uffff\2\51\3\uffff\1\u00db\1\51\1\uffff"+
        "\1\51\1\u00de\4\uffff\1\u00df\1\u00e0\1\51\1\u00e2\1\51\3\uffff"+
        "\1\u00e4\1\51\1\u00e6\1\51\1\u00e8\1\uffff\1\136\1\uffff\2\51\1"+
        "\u00eb\1\u00ec\1\uffff\1\u00ed\1\u00ee\3\uffff\1\51\1\uffff\1\u00f0"+
        "\1\uffff\1\u00f1\1\uffff\1\u00f2\1\uffff\1\u00f3\1\51\4\uffff\1"+
        "\u00f5\4\uffff\1\u00f6\2\uffff";
    static final String DFA23_eofS =
        "\u00f7\uffff";
    static final String DFA23_minS =
        "\1\11\1\144\1\114\1\105\1\101\1\105\1\114\2\122\1\101\1\116\1\117"+
        "\1\105\1\117\1\116\1\105\1\111\1\105\1\110\1\116\1\110\1\60\11\uffff"+
        "\1\75\1\uffff\1\75\1\uffff\1\55\1\52\2\uffff\1\56\5\uffff\1\163"+
        "\1\uffff\1\104\1\114\1\44\1\124\1\44\2\123\1\114\1\111\1\104\1\123"+
        "\1\114\2\117\1\126\2\44\1\111\1\113\1\106\1\114\1\124\2\44\1\124"+
        "\1\122\1\107\1\114\1\115\1\120\1\105\1\125\1\111\1\104\1\111\1\105"+
        "\12\uffff\1\56\3\uffff\3\44\1\uffff\1\44\1\127\1\uffff\1\105\1\124"+
        "\1\103\1\105\1\123\1\44\1\105\1\114\1\115\1\125\1\111\1\uffff\1"+
        "\117\2\105\1\uffff\1\116\1\105\1\124\1\114\1\44\1\uffff\1\105\1"+
        "\uffff\1\105\1\103\1\110\2\105\1\44\1\116\1\105\1\116\1\101\1\117"+
        "\1\116\1\56\4\uffff\1\105\1\44\1\111\1\44\2\124\1\uffff\3\44\1\120"+
        "\1\116\1\44\2\122\4\44\1\uffff\2\122\1\105\1\124\1\103\1\44\1\uffff"+
        "\2\44\1\107\1\124\1\116\1\125\1\105\1\44\1\55\1\105\1\uffff\1\116"+
        "\1\uffff\1\105\1\123\3\uffff\1\44\1\107\1\uffff\1\124\1\44\4\uffff"+
        "\2\44\1\116\1\44\1\124\3\uffff\1\44\1\105\1\44\1\105\1\44\1\uffff"+
        "\1\56\1\uffff\1\116\1\103\2\44\1\uffff\2\44\3\uffff\1\124\1\uffff"+
        "\1\44\1\uffff\1\44\1\uffff\1\44\1\uffff\1\44\1\124\4\uffff\1\44"+
        "\4\uffff\1\44\2\uffff";
    static final String DFA23_maxS =
        "\1\175\1\164\1\163\1\171\1\141\1\151\1\170\1\165\1\162\1\141\1\163"+
        "\1\157\1\151\2\165\1\145\1\151\1\157\1\162\1\163\1\150\1\71\11\uffff"+
        "\1\76\1\uffff\1\75\1\uffff\1\55\1\57\2\uffff\1\145\5\uffff\1\163"+
        "\1\uffff\1\171\1\154\1\172\1\164\1\172\3\163\1\151\1\144\1\163\1"+
        "\154\2\157\1\166\2\172\1\151\1\153\1\146\1\154\1\164\2\172\1\164"+
        "\1\162\1\147\1\154\1\155\1\160\1\145\1\165\1\151\1\144\1\151\1\145"+
        "\12\uffff\1\145\3\uffff\3\172\1\uffff\1\172\1\167\1\uffff\1\145"+
        "\1\164\1\143\1\145\1\163\1\172\1\145\1\154\1\155\1\165\1\151\1\uffff"+
        "\1\157\2\145\1\uffff\1\156\1\145\1\164\1\154\1\172\1\uffff\1\145"+
        "\1\uffff\1\145\1\143\1\150\2\145\1\172\1\156\1\145\1\156\1\141\1"+
        "\161\1\162\1\145\4\uffff\1\145\1\172\1\151\1\172\2\164\1\uffff\3"+
        "\172\1\160\1\156\1\172\2\162\4\172\1\uffff\2\162\1\145\1\164\1\143"+
        "\1\172\1\uffff\2\172\1\147\1\164\1\156\1\165\1\145\1\172\2\145\1"+
        "\uffff\1\156\1\uffff\1\145\1\163\3\uffff\1\172\1\147\1\uffff\1\164"+
        "\1\172\4\uffff\2\172\1\156\1\172\1\164\3\uffff\1\172\1\145\1\172"+
        "\1\145\1\172\1\uffff\1\145\1\uffff\1\156\1\143\2\172\1\uffff\2\172"+
        "\3\uffff\1\164\1\uffff\1\172\1\uffff\1\172\1\uffff\1\172\1\uffff"+
        "\1\172\1\164\4\uffff\1\172\4\uffff\1\172\2\uffff";
    static final String DFA23_acceptS =
        "\26\uffff\1\62\1\63\1\64\1\66\1\67\1\70\1\71\1\72\1\73\1\uffff\1"+
        "\75\1\uffff\1\102\2\uffff\1\105\1\106\1\uffff\1\111\1\113\1\114"+
        "\1\116\1\1\1\uffff\1\65\44\uffff\1\110\1\61\1\74\1\76\1\77\1\100"+
        "\1\101\1\115\1\103\1\104\1\uffff\1\107\1\3\1\2\3\uffff\1\7\2\uffff"+
        "\1\12\13\uffff\1\26\3\uffff\1\32\5\uffff\1\41\1\uffff\1\40\15\uffff"+
        "\1\6\1\5\1\4\1\10\6\uffff\1\20\14\uffff\1\36\6\uffff\1\52\12\uffff"+
        "\1\13\1\uffff\1\15\2\uffff\1\17\1\23\1\22\2\uffff\1\31\2\uffff\1"+
        "\33\1\35\1\34\1\37\5\uffff\1\47\1\50\1\51\5\uffff\1\57\1\uffff\1"+
        "\112\4\uffff\1\24\2\uffff\1\27\1\42\1\43\1\uffff\1\45\1\uffff\1"+
        "\56\1\uffff\1\53\1\uffff\1\60\2\uffff\1\14\1\21\1\25\1\30\1\uffff"+
        "\1\46\1\55\1\54\1\11\1\uffff\1\44\1\16";
    static final String DFA23_specialS =
        "\u00f7\uffff}>";
    static final String[] DFA23_transitionS = {
            "\2\53\2\uffff\1\53\22\uffff\1\53\1\40\1\52\2\uffff\1\46\1\uffff"+
            "\1\50\1\27\1\30\1\45\1\42\1\26\1\43\1\25\1\44\12\47\1\34\1\35"+
            "\1\37\1\36\1\41\1\33\1\uffff\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1"+
            "\11\1\12\1\13\1\51\1\14\1\51\1\15\1\16\1\17\1\51\1\20\1\21\1"+
            "\22\1\23\1\51\1\24\3\51\1\52\5\uffff\1\2\1\3\1\4\1\5\1\6\1\7"+
            "\1\10\1\11\1\12\1\13\1\51\1\14\1\51\1\15\1\16\1\17\1\51\1\20"+
            "\1\21\1\22\1\23\1\51\1\24\3\51\1\1\1\32\1\31",
            "\1\54\17\uffff\1\55",
            "\1\60\1\uffff\1\57\4\uffff\1\61\30\uffff\1\60\1\uffff\1\57"+
            "\4\uffff\1\61",
            "\1\62\23\uffff\1\63\13\uffff\1\62\23\uffff\1\63",
            "\1\64\37\uffff\1\64",
            "\1\66\3\uffff\1\65\33\uffff\1\66\3\uffff\1\65",
            "\1\71\1\uffff\1\70\11\uffff\1\67\23\uffff\1\71\1\uffff\1\70"+
            "\11\uffff\1\67",
            "\1\73\2\uffff\1\72\34\uffff\1\73\2\uffff\1\72",
            "\1\74\37\uffff\1\74",
            "\1\75\37\uffff\1\75",
            "\1\76\4\uffff\1\77\32\uffff\1\76\4\uffff\1\77",
            "\1\100\37\uffff\1\100",
            "\1\102\3\uffff\1\101\33\uffff\1\102\3\uffff\1\101",
            "\1\104\5\uffff\1\103\31\uffff\1\104\5\uffff\1\103",
            "\1\106\3\uffff\1\105\2\uffff\1\107\30\uffff\1\106\3\uffff\1"+
            "\105\2\uffff\1\107",
            "\1\110\37\uffff\1\110",
            "\1\111\37\uffff\1\111",
            "\1\112\11\uffff\1\113\25\uffff\1\112\11\uffff\1\113",
            "\1\115\6\uffff\1\114\2\uffff\1\116\25\uffff\1\115\6\uffff\1"+
            "\114\2\uffff\1\116",
            "\1\121\1\uffff\1\120\2\uffff\1\117\32\uffff\1\121\1\uffff\1"+
            "\120\2\uffff\1\117",
            "\1\122\37\uffff\1\122",
            "\12\123",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\126\1\125",
            "",
            "\1\130",
            "",
            "\1\132",
            "\1\132\4\uffff\1\132",
            "",
            "",
            "\1\123\1\uffff\12\135\13\uffff\1\123\37\uffff\1\123",
            "",
            "",
            "",
            "",
            "",
            "\1\137",
            "",
            "\1\142\24\uffff\1\141\12\uffff\1\142\24\uffff\1\141",
            "\1\143\37\uffff\1\143",
            "\1\51\13\uffff\12\51\7\uffff\2\51\1\145\27\51\4\uffff\1\51"+
            "\1\uffff\2\51\1\145\27\51",
            "\1\146\37\uffff\1\146",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\150\37\uffff\1\150",
            "\1\151\37\uffff\1\151",
            "\1\153\6\uffff\1\152\30\uffff\1\153\6\uffff\1\152",
            "\1\154\37\uffff\1\154",
            "\1\155\37\uffff\1\155",
            "\1\156\37\uffff\1\156",
            "\1\157\37\uffff\1\157",
            "\1\160\37\uffff\1\160",
            "\1\161\37\uffff\1\161",
            "\1\162\37\uffff\1\162",
            "\1\51\13\uffff\12\51\7\uffff\15\51\1\166\4\51\1\165\1\164\6"+
            "\51\4\uffff\1\51\1\uffff\15\51\1\166\4\51\1\165\1\164\6\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\170\37\uffff\1\170",
            "\1\171\37\uffff\1\171",
            "\1\172\37\uffff\1\172",
            "\1\173\37\uffff\1\173",
            "\1\174\37\uffff\1\174",
            "\1\51\13\uffff\12\51\7\uffff\3\51\1\176\26\51\4\uffff\1\51"+
            "\1\uffff\3\51\1\176\26\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u0080\37\uffff\1\u0080",
            "\1\u0081\37\uffff\1\u0081",
            "\1\u0082\37\uffff\1\u0082",
            "\1\u0083\37\uffff\1\u0083",
            "\1\u0084\37\uffff\1\u0084",
            "\1\u0085\37\uffff\1\u0085",
            "\1\u0086\37\uffff\1\u0086",
            "\1\u0087\37\uffff\1\u0087",
            "\1\u0088\37\uffff\1\u0088",
            "\1\u0089\37\uffff\1\u0089",
            "\1\u008a\37\uffff\1\u008a",
            "\1\u008b\37\uffff\1\u008b",
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
            "\1\123\1\uffff\12\u008c\13\uffff\1\123\37\uffff\1\123",
            "",
            "",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u0091\37\uffff\1\u0091",
            "",
            "\1\u0092\37\uffff\1\u0092",
            "\1\u0093\37\uffff\1\u0093",
            "\1\u0094\37\uffff\1\u0094",
            "\1\u0095\37\uffff\1\u0095",
            "\1\u0096\37\uffff\1\u0096",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u0098\37\uffff\1\u0098",
            "\1\u0099\37\uffff\1\u0099",
            "\1\u009a\37\uffff\1\u009a",
            "\1\u009b\37\uffff\1\u009b",
            "\1\u009c\37\uffff\1\u009c",
            "",
            "\1\u009d\37\uffff\1\u009d",
            "\1\u009e\37\uffff\1\u009e",
            "\1\u009f\37\uffff\1\u009f",
            "",
            "\1\u00a0\37\uffff\1\u00a0",
            "\1\u00a1\37\uffff\1\u00a1",
            "\1\u00a2\37\uffff\1\u00a2",
            "\1\u00a3\37\uffff\1\u00a3",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\u00a5\37\uffff\1\u00a5",
            "",
            "\1\u00a6\37\uffff\1\u00a6",
            "\1\u00a7\37\uffff\1\u00a7",
            "\1\u00a8\37\uffff\1\u00a8",
            "\1\u00a9\37\uffff\1\u00a9",
            "\1\u00aa\37\uffff\1\u00aa",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00ac\37\uffff\1\u00ac",
            "\1\u00ad\37\uffff\1\u00ad",
            "\1\u00ae\37\uffff\1\u00ae",
            "\1\u00af\37\uffff\1\u00af",
            "\1\u00b0\1\uffff\1\u00b1\35\uffff\1\u00b0\1\uffff\1\u00b1",
            "\1\u00b3\3\uffff\1\u00b2\33\uffff\1\u00b3\3\uffff\1\u00b2",
            "\1\123\1\uffff\12\u00b4\13\uffff\1\123\37\uffff\1\123",
            "",
            "",
            "",
            "",
            "\1\u00b5\37\uffff\1\u00b5",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00b7\37\uffff\1\u00b7",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00b9\37\uffff\1\u00b9",
            "\1\u00ba\37\uffff\1\u00ba",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00be\37\uffff\1\u00be",
            "\1\u00bf\37\uffff\1\u00bf",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00c1\37\uffff\1\u00c1",
            "\1\u00c2\37\uffff\1\u00c2",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\u00c7\37\uffff\1\u00c7",
            "\1\u00c8\37\uffff\1\u00c8",
            "\1\u00c9\37\uffff\1\u00c9",
            "\1\u00ca\37\uffff\1\u00ca",
            "\1\u00cb\37\uffff\1\u00cb",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00cf\37\uffff\1\u00cf",
            "\1\u00d0\37\uffff\1\u00d0",
            "\1\u00d1\37\uffff\1\u00d1",
            "\1\u00d2\37\uffff\1\u00d2",
            "\1\u00d3\37\uffff\1\u00d3",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00d6\1\123\1\uffff\12\u00d5\13\uffff\1\123\37\uffff\1\123",
            "\1\u00d7\37\uffff\1\u00d7",
            "",
            "\1\u00d8\37\uffff\1\u00d8",
            "",
            "\1\u00d9\37\uffff\1\u00d9",
            "\1\u00da\37\uffff\1\u00da",
            "",
            "",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00dc\37\uffff\1\u00dc",
            "",
            "\1\u00dd\37\uffff\1\u00dd",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "",
            "",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00e1\37\uffff\1\u00e1",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00e3\37\uffff\1\u00e3",
            "",
            "",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00e5\37\uffff\1\u00e5",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00e7\37\uffff\1\u00e7",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\123\1\uffff\12\u00d5\13\uffff\1\123\37\uffff\1\123",
            "",
            "\1\u00e9\37\uffff\1\u00e9",
            "\1\u00ea\37\uffff\1\u00ea",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "",
            "",
            "\1\u00ef\37\uffff\1\u00ef",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "\1\u00f4\37\uffff\1\u00f4",
            "",
            "",
            "",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
            "",
            "",
            "",
            "",
            "\1\51\13\uffff\12\51\7\uffff\32\51\4\uffff\1\51\1\uffff\32"+
            "\51",
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
            return "1:1: Tokens : ( T__107 | T__108 | T__109 | ALL | AND | ANY | AS | ASC | BETWEEN | BY | CASE | DELETE | DESC | DISTINCT | ELSE | END | EXISTS | FROM | FULL | GROUP | HAVING | IN | INNER | INSERT | INTO | IS | JOIN | LEFT | LIKE | NOT | NULL | ON | OR | ORDER | OUTER | PERCENT | RIGHT | SELECT | SOME | THEN | TRUE | TOP | UNION | UNIQUE | UPDATE | USING | WHEN | WHERE | DOT | COMMA | LPAREN | RPAREN | LCURLY | RCURLY | STRCAT | QUESTION | COLON | SEMI | EQ | NEQ1 | NEQ2 | LTE | LT | GTE | GT | PLUS | MINUS | DIVIDE | STAR | MOD | Integer | Float | String | Timestamp | Identifier | QuotedIdentifier | Comment | Whitespace );";
        }
    }
 

}