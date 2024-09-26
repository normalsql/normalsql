package normalsql;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  Maven plugin. Gets populated with config. Then executed.
 *
 */

@Mojo(
    name = "normalsql",
    defaultPhase = LifecyclePhase.GENERATE_SOURCES,
    requiresDependencyResolution = ResolutionScope.COMPILE
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

    @Parameter( defaultValue = "${project.build.directory}/generated-sources/sql" )
    String target;

    @Parameter( property = "package" )
    String pkg;

    @Parameter( defaultValue = "sql" )
    String extension;

    public void execute()
        throws MojoExecutionException
    {
        try
        {
//            getLog().info( "source: " + source );
            Config config = new Config();
            config.description = description;
            config.url         = url;
            config.username    = username;
            config.password    = password;
            config.source      = source;
            config.target      = target;
            config.pkg         = pkg;
            config.extension   = extension;
            config.validate();

            // Make sure Maven finds our generated files
            project.addCompileSourceRoot( config.targetPath.toAbsolutePath().toString() );

            Tool tool = new Tool();
            tool.go( config );
        }
        catch( Exception e )
        {
            throw new MojoExecutionException( e );
        }
    }
}
