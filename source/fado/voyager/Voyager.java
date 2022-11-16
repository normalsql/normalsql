package fado.voyager;

import fado.parse.GenericSQLLexer;
import fado.parse.GenericSQLParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Voyager
{
	public static void main( String[] args )
		throws Exception
	{
		Path sourceFile = Paths.get( "/Users/jasonosgood/Projects/fado/test/SelectCourseTestBetweens.sql" );

		String originalSQL = new String( Files.readAllBytes( sourceFile ));

		CharStream chars = CharStreams.fromString( originalSQL );
		GenericSQLLexer lexer = new GenericSQLLexer( chars );
		CommonTokenStream tokens = new CommonTokenStream( lexer );
		GenericSQLParser parser = new GenericSQLParser( tokens );
//		VoyagerListener listener = new VoyagerListener();
//		parser.addParseListener( listener );
		GenericSQLParser.ParseContext parse = parser.parse();
		VoyagerVisitor visitor = new VoyagerVisitor();
		Work work = visitor.visit( parse );
		System.out.println( work );

	}

}
