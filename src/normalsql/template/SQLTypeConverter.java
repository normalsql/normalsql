package normalsql.template;

import normalsql.Glorp;

import static java.sql.Types.*;


public class SQLTypeConverter
{

    public static void main( String[] args )
    {
        int sqlType = VARCHAR;
        System.out.println( Glorp.getJavaClassName( sqlType ));
    }
}
