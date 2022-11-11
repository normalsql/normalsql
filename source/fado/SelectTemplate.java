/*
 Fado - SelectTemplate.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Utility class for Select.vm and ResultSet.vm Velocity templates.
*/

package fado;

import fado.meta.*;
import fado.template.JavaHelper;
import fado.parse.GenericSQLParser;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.HashMap;

import static java.sql.Types.*;

public class SelectTemplate
{
	public static void main( String[] args ) throws Exception
	{
//		SelectTemplate self = new SelectTemplate();
//
//		Work work = new Work();
//
//		work.originalSQL = "SELECT id, lastATP, course_title, description, revision\n FROM Course \nWHERE department_abbrev = 'ENGL'";
//		work.preparedSQL = "SELECT id, lastATP, course_title, description, revision\n FROM Course \nWHERE department_abbrev = ?";
//
//		Comparison comparison = new Comparison( null, null );
//		comparison.columnName = "apple";
//		comparison.column = new Table.Column();
//		comparison.column.type = VARCHAR;
//		comparison.valueList.add( "cosmic crisp" );
//		work.termList.add( comparison );
//
////		Between between = new Between( null );
////		between.columnName = "banana";
////		between.column = new Table.Column();
////		between.column.type = INTEGER;
////		between.valueList.add( "1" );
////		between.valueList.add( "9" );
////		work.conditionList.add( between );
//
//		GenericSQLParser.ValueContext l1 = new GenericSQLParser.ValueContext( null, 0 );
//		GenericSQLParser.ValueContext l2 = new GenericSQLParser.ValueContext( null, 0 );
//		GenericSQLParser.ValueContext l3 = new GenericSQLParser.ValueContext( null, 0 );
//		IN in = new IN( null, l1, l2, l3 );
//		in.columnName = "cherry";
//		in.column = new Table.Column();
//		in.column.type = CHAR;
//		in.valueList.add( "bitter" );
//		in.valueList.add( "sweet" );
//		in.valueList.add( "tart" );
//		work.termList.add( in );
//
//		work.className = "Test";
//		work.packageName = "test";
//
////		self.merge( work );
	}

	public void merge( Work work ) throws IOException
	{
		try (
			OutputStreamWriter osw = new OutputStreamWriter( System.out );
			BufferedWriter writer = new BufferedWriter( osw );
		)
		{
			// TODO: This will have to be moved to common place, maybe into superclass
			VelocityEngine engine = new VelocityEngine();
			engine.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
			engine.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
			engine.setProperty( "runtime.introspector.uberspect", "org.apache.velocity.util.introspection.UberspectImpl,org.apache.velocity.util.introspection.UberspectPublicFields" );
			engine.init();

			// Specific to template
			HashMap<String, Object> parentMap = new HashMap<>();
			parentMap.put( "Comparison", Comparison.class );
			parentMap.put( "Between", Between.class );
			parentMap.put( "IN", IN.class );
			parentMap.put( "helper", JavaHelper.class );
			// TODO change to 'now', use same Date for all artifacts
			parentMap.put( "date", new Date() );
			VelocityContext parentContext = new VelocityContext( parentMap );

			// Related to statement
			HashMap<String, Object> childMap = work.asMap();
			VelocityContext childContext = new VelocityContext( childMap, parentContext );

			// TODO: Just one template instance
//			Template selectTemplate = engine.getTemplate( "fado/template/Select.vm" );
			Template selectTemplate = engine.getTemplate( "fado/template/JustBetweens.vm" );
			selectTemplate.merge( childContext, writer );
//			Template resultSetTemplate = engine.getTemplate( "fado/template/ResultSet.vm" );
//			resultSetTemplate.merge( childContext, writer );
		}
	}
}
