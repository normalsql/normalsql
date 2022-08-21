/*
 Fado - SelectTemplate.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Utility class for Velocity template.
*/

package fado;

import fado.meta.*;
import fado.template.JavaHelper;
import fado.parse.GenericSQLParser;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import static java.sql.Types.*;

public class SelectTemplate
{
	public static void main( String[] args ) throws Exception
	{
		SelectTemplate self = new SelectTemplate();

		String originalSQL = "SELECT id, lastATP, course_title, description, revision\n FROM Course \nWHERE department_abbrev = 'ENGL'";

		ArrayList<Condition> conditions = new ArrayList<>();

		Comparison comparison = new Comparison( null, null );
		comparison.columnName = "apple";
		comparison.column = new Column();
		comparison.column.type = VARCHAR;
		comparison.valueList.add( "cosmic crisp" );
		conditions.add( comparison );


		Between between = new Between( null );
		between.columnName = "banana";
		between.column = new Column();
		between.column.type = INTEGER;
		between.valueList.add( "1" );
		between.valueList.add( "9" );
		conditions.add( between );

		GenericSQLParser.LiteralContext l1 = new GenericSQLParser.LiteralContext( null, 0 );
		GenericSQLParser.LiteralContext l2 = new GenericSQLParser.LiteralContext( null, 0 );
		GenericSQLParser.LiteralContext l3 = new GenericSQLParser.LiteralContext( null, 0 );
		IN in = new IN( null, l1, l2, l3 );
		in.columnName = "cherry";
		in.column = new Column();
		in.column.type = CHAR;
		in.valueList.add( "bitter" );
		in.valueList.add( "sweet" );
		in.valueList.add( "tart" );
		conditions.add( in );

		String className = "Test";
		String packageName = "test";

		self.merge( className, packageName, originalSQL, conditions );
	}

	public void merge( String className, String packageName, String originalSQL, List<Condition> conditionList ) throws IOException
	{
		try (
			OutputStreamWriter osw = new OutputStreamWriter( System.out );
			BufferedWriter writer = new BufferedWriter( osw );
		)
		{
			// TODO: This will have to be moved
			Velocity.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
			Velocity.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
			Velocity.setProperty( "runtime.introspector.uberspect", "org.apache.velocity.util.introspection.UberspectImpl,org.apache.velocity.util.introspection.UberspectPublicFields" );
			Velocity.init();

			// TODO: Just one template instance
			Template template = Velocity.getTemplate( "fado/template/Select.vm" );

			HashMap<String, Object> map = new HashMap<>();
			map.put( "Comparison", Comparison.class );
			map.put( "Between", Between.class );
			map.put( "IN", IN.class );
			map.put( "packageName", packageName );
			map.put( "className", className );
			map.put( "date", new Date() );
//			map.put( "originalFile", originalFile );
			map.put( "originalSQL", originalSQL );
//			map.put( "preparedSQL", preparedSQL );
//			map.put( "printfSQL", printfSQL );
			map.put( "conditionList", conditionList );
			map.put( "helper", JavaHelper.class );

			VelocityContext context = new VelocityContext( map );
			template.merge( context, writer );
		}
	}
}
