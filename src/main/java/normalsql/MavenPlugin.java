package normalsql;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;

import static normalsql.LogAgain.Level.*;

/**
 *  Maven plugin. Gets populated with config. Then executed.
 *
 */

@Mojo(
    name = "normalsql",
    defaultPhase = LifecyclePhase.GENERATE_SOURCES,
//    requiresDependencyResolution = ResolutionScope.COMPILE
    requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME
)
public class MavenPlugin
    extends AbstractMojo
{
    @Parameter( defaultValue = "${project}", readonly = true )
    MavenProject project;

    @Parameter
    String description;

    @Parameter
    String url;

    @Parameter
    String username;

    @Parameter
    String password;

    @Parameter( defaultValue = "${project.basedir}/src/main/sql" )
    String source;

    @Parameter( defaultValue = "${project.build.directory}/generated-sources/normalsql" )
    String target;

    @Parameter( property = "package" )
    String pkg;

    @Parameter( defaultValue = "sql" )
    String extension;

    @Override
    public void execute()
        throws MojoExecutionException
    {
        var log = getLog();
        try
        {

            var config = new Config();
            config.description = description;
            config.url = url;
            config.username = username;
            config.password = password;
            config.source = source;
            config.target = target;
            config.pkg = pkg;
            config.extension = extension;
            config.validate();

//            log.debug( "normalsql config: " + Glorp.toMap( config ));

            // Make sure Maven finds our generated files
            project.addCompileSourceRoot( config.targetPath.toAbsolutePath().toString() );
            project.addCompileSourceRoot( config.target );
            project.addTestCompileSourceRoot( config.target );


            var tool = new Tool();
            // Use Maven's logger
//            tool.INFO  = new MavenEcho( "info", log );
//            tool.WARN  = new MavenEcho( "warn", log );
//            tool.DEBUG = new MavenEcho( "debug", log );
//            tool.ERROR = new MavenEcho( "error", log );

            tool.INFO  = new LogAgain( info, log );
            tool.WARN  = new LogAgain( warn, log );
            tool.DEBUG = new LogAgain( debug, log );
            tool.ERROR = new LogAgain( error, log );

            tool.INFO.log( "*** INFO" );
            tool.WARN.log( "*** WARN" );
            tool.DEBUG.log( "*** DEBUG" );
            tool.ERROR.log( "*** ERROR" );
//            tool.DANG = msg -> System.out::println;

//            tool.biff = (x) -> log.info( x );
//            tool.biff = (x) -> new MavenEcho( "info", log ).log( x );

            tool.generate( config );
        }
        catch( Exception e )
        {
            throw new MojoExecutionException( e );
        }
    }
}
