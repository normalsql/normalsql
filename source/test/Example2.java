package test;

import java.io.StringWriter;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import org.apache.velocity.util.introspection.UberspectPublicFields;
import org.apache.velocity.util.introspection.UberspectImpl;

public class Example2
{
    public String getName() { return "rooster"; }
    public   String gorp = "knopt";

    public static void main( String args[] )
    {
        /* first, we init the runtime engine.  Defaults are fine. */
        VelocityEngine engine = new VelocityEngine();
//        engine.setProperty( RuntimeConstants.RESOURCE_LOADER, "classpath" );
//        engine.setProperty( "classpath.resource.loader.class", ClasspathResourceLoader.class.getName() );
//        String spa = UberspectPublicFields.class.getName();
        engine.setProperty( "runtime.introspector.uberspect", "org.apache.velocity.util.introspection.UberspectPublicFields, org.apache.velocity.util.introspection.UberspectImpl" );
        engine.init();

        
//        Velocity.init();

        /* lets make a Context and put data into it */

        VelocityContext context = new VelocityContext();

        context.put("name", "Velocity");
        context.put("project", "Jakarta");
        context.put("example", new Example2() );

        /* lets render a template */

        StringWriter w = new StringWriter();

//        Velocity.mergeTemplate("testtemplate.vm", null, context, w );
//        System.out.println(" template : " + w );

        /* lets make our own string to render */

        String s = "We are using $project $name to render this. $example.name ${example.gorp}";
//        Velocity.evaluate( context, w, "mystring", s );
        engine.evaluate( context, w, "mystring", s );
        System.out.println(" string : " + w );
    }
}