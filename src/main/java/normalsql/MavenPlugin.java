package normalsql;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

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
            // TODO re-verify need to use absolute path for later steps
            Path sourceDir = Paths.get( source ).toAbsolutePath();
            getLog().info( "source: " + source );
            if( Files.notExists( sourceDir ))
			{
                // TODO add phase, id, goal for better context?
                throw new FileNotFoundException( "Source directory does not exist: " + sourceDir );
            }

            Path targetDir  = Paths.get( target ).toAbsolutePath();
            if( Files.notExists( targetDir ))
			{
                Files.createDirectories( targetDir );
                project.addCompileSourceRoot( targetDir.toAbsolutePath().toString() );
            }

            if( url == null )
            {
                throw new NullPointerException( "JDBC URL is null" );
            }

            // TODO convert to a record?
            Config config = new Config();
            config.description = description;
            config.url = url;
            config.username = username;
            config.password = password;
            config.source = sourceDir;
            config.target = targetDir;
            config.pkg = pkg;
            config.extension = extension;

            Tool tool = new Tool();
            tool.go( config );
        }
//        catch( MojoExecutionException mee )
//        {
//            throw mee;
//        }
        catch( Exception e )
        {
            throw new MojoExecutionException( e );
        }
    }
}
