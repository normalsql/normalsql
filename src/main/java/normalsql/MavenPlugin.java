package normalsql;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;

import static normalsql.MavenLogShim.Level.*;

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
//            config.pkg = pkg;
            config.extension = extension;
            config.validate();

            // Add our generated files to this project's depencencies
            project.addCompileSourceRoot( config.targetPath.toAbsolutePath().toString() );
            project.addCompileSourceRoot( config.target );
            project.addTestCompileSourceRoot( config.target );


            var tool = new Tool( config );
            tool.INFO  = new MavenLogShim( info, log );
            tool.WARN  = new MavenLogShim( warn, log );
            tool.DEBUG = new MavenLogShim( debug, log );
            tool.ERROR = new MavenLogShim( error, log );

            tool.generate( config );
        }
        catch( Exception e )
        {
            throw new MojoExecutionException( e );
        }
    }
}
