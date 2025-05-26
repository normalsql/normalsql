package normalsql.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

public class BNF
{
    public static void main( String[] args ) throws Exception
    {
        var sw = new StringWriter();
        int         b;
        boolean     eof = false;
//        var sr  = new StringReader( "CREATE ROLE name [ [ WITH ] option [ ... ] ]" );
        while( !eof && ( b =  System.in.read() ) != -1 )
//        while( ( b =  sr.read() ) != -1 )
        {
//            char c = (char) b ;
//            System.out.println( "octal: " + Integer.toOctalString( b ));
//            System.out.println(  "hex: " + Integer.toHexString( b ));
//            System.out.println(  "int: " + Integer.valueOf( b ));
            var d = switch( b )
            {
//                case -1 -> "";
                case '(' -> "'('";
                case ')' -> "')'";

                case '[' -> "(";
                case ']' -> ")?";

                case '{' -> "(";
                case '}' -> ")*";

                case '*' -> "'*'";
//                case '.' -> "'.'";

                case '\t' -> "\t";
                case '\r' -> "\r";
                case '\n' -> "\n";
                case ' ' -> " ";
                case '|' -> "|";
                case ',' -> "','";
                case '/' -> { eof = true; yield ""; }

//                case "::=" -> ":";
//                case ",...n" -> "(',' '' )*";
                default ->
                {
                    var result = new StringWriter();
                    var word = new StringWriter();
                    if( Character.isAlphabetic( b ) || b == '_' )
                    {
                        word.append( (char) b );
                        char e;
                        while( true )
                        {
                            e = (char) System.in.read();
                            if( Character.isAlphabetic( e ) || e == '_' )
                            {
                                word.append( e );
                                continue;
                            }
                            else if( e == ']' )
                            {
                                e = ')';
                            }
                            else if( e == '}' )
                            {
                                e = ')';
                            }
                            var g = word.toString();
                            if( g.toUpperCase().equals( g ))
                            {
                                result.append(  '\'' );
                                result.append(  g );
                                result.append(  '\'' );
                            }
                            else
                            {
                                result.append(  g );
                            }
                            result.append( e );
                            break;
                        }
                    }
                    else
                    {
                        result.append( (char) b );
                    }
                    yield result.toString();
                }
            };
            sw.append( d );
        }
        System.out.println( sw );
    }
}
