package normalsql.util;

import java.io.StringWriter;
import java.util.Scanner;

public class ABC
{
    public static void main( String[] args ) throws Exception
    {
        var sw = new StringWriter();
//        var scan = new Scanner( "abc \t \n 132" ).useDelimiter("[ ]+");
        var scan = new Scanner( System.in ).useDelimiter("[ ]+");
//        while( scan.hasNext() )
//        {
//            var t = scan.next();
//            System.out.println( t );
//        }
        while( scan.hasNext() )
        {
            var t = scan.next();
            System.out.println( "ffs: " + t );
            if( t.contains( "xx" ))
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

                case "\t" -> "\t";
                case "\r" -> "\r";
                case "\n" -> "\n";
                case "|" -> "|";
                case "," -> "','";

                case ",...n" -> "(',' '' )*";
                default ->
                {
                    if( t.isBlank() )
                    {
                        yield t;
                    }
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


    }
}
