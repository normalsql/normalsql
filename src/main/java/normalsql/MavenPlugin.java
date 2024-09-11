package normalsql;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.*;

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
    Config abc = new Config();

//    @Component
//    MavenProject project;

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
//    List<Dependency> dependencies = project.getDependencies();
//    for (Dependency dependency : dependencies) {
//        System.out.println(dependency.getGroupId() + ":" + dependency.getArtifactId() + ":" + dependency.getVersion());
//        System.out.println(dependency);
//    }

        try
        {
            Tool tool = new Tool();

            Path sourceDir = Paths.get( source ).toAbsolutePath();
            if( Files.notExists( sourceDir ))
			{
                // TODO add phase, id, goal for better context?
                throw new MojoExecutionException( "Source directory does not exist: " + sourceDir );
            }

            Path targetDir  = Paths.get( target ).toAbsolutePath();
//            if( Files.notExists( targetDir ))
//			{
//                Files.createDirectories( targetDir );
//            }

            if( url == null )
            {
                throw new MojoExecutionException( "JDBC URL is null" );
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

            tool.go( config );
        }
        catch( MojoExecutionException mee )
        {
            throw mee;
        }
        catch( Exception e )
        {
            throw new MojoExecutionException( e );
        }

        Map map = getPluginContext();
        map.forEach((key, value) -> {
            System.out.println( "\n" + key + ": " + value );
        });
    }

}
