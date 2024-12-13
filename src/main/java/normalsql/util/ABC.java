package normalsql.util;

import java.io.StringWriter;
import java.util.Scanner;

public class ABC
{
    public static void main( String[] args ) throws Exception
    {
        var sw = new StringWriter();
//        char x;
        var scan = new Scanner( System.in );
        while( scan.hasNext() )
        {
            var t = scan.next();
            if( t.equals( "xx" ))
            {
                System.out.println( sw );
                sw = new StringWriter();
            }
            var u = switch( t )
            {
                case "(" -> "'('";
                case ")" -> "')'";

                case "[" -> "(";
                case "]" -> ")?";

                case "{" -> "(";
                case "}" -> ")*";

                case "::=" -> ":";
                case "*" -> "'*'";
                case "." -> "'.'";
                case "|" -> "|";
                case "," -> "','";
                case ",...n" -> "(',' '' )*";
                default ->
                {
                    if( t.toUpperCase().equals(  t )) {
                        yield '\'' + t + '\'';
                    }
                    else
                    {
                        yield t;
                    }
                }
            };

            sw.append( u + ' ' );
        }


//        while( ( x = (char) System.in.read()) != 'x' )
//        {
//            sw.append( x );
//        }

    }
}
