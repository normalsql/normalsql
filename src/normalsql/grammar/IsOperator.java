package normalsql.grammar;

import java.util.regex.Pattern;

public class IsOperator
{
    public static void main( String[] args )
    {
        Pattern p = Pattern.compile( "^[^\\~!@\\#%\\^&\\|\\`\\?]*\\+$" );
//        Pattern p = Pattern.compile( "^(?!.*!).*[+-]$" );
//        Pattern p = Pattern.compile( "^(?!.*[!/]).*[+-]$" );
//        Pattern p = Pattern.compile( ".*^[0-9]+.*[+-]" );
        var victims = new String[] {
                "abc!"
                , "!abc"
                , "!abc+"
                , "abc!+"
                , "abc!def+"
                , "abc123abc+"
                , "abc!123abc+"
                , "9abc+"
        };
        for( var victim : victims )
        {
            System.out.println( victim + ": " + p.matcher( victim ).matches() );
        }
    }
}
