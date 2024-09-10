package normalsql.maven;

import normalsql.Config;
import normalsql.Tool;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.List;
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
public class NormalSQLMojo
    extends AbstractMojo
{
//    @Parameter( property = "project", required = true, readonly = true )
    @Component
    MavenProject project;

    @Parameter
    String description;

    @Parameter
    String driver;

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

    @Parameter
    File gorp;

    @Parameter( property = "package" )
    String pkg;

    @Parameter( defaultValue = "sql" )
    String extension;

    public Config toConfig()
    {
        Config config = new Config();
        config.description = description;
        config.driver = driver;
        config.url = url;
        config.username = username;
        config.password = password;
        config.source = source;
        config.target = target;
        config.pkg = pkg;
        config.extension = extension;
        return config;
    }

    public void execute()
        throws MojoExecutionException
    {
    List<Dependency> dependencies = project.getDependencies();
    for (Dependency dependency : dependencies) {
        System.out.println(dependency.getGroupId() + ":" + dependency.getArtifactId() + ":" + dependency.getVersion());
        System.out.println(dependency);
    }
    // Process dependencies or add them to the classpath

        try
        {
            Tool tool = new Tool();
            Config config = toConfig();
            tool.go( config );
        }
        catch( Exception e )
        {
            throw new MojoExecutionException( e );
        }

//        System.out.println( "\n\n\n\n$$$  I hate Maven 3 $$$\n\n\n\n" );
////        System.out.println( "\n\n\n\n$$$  gorp: " + gorp + " $$$\n\n\n\n" );
//        System.out.println( this );
//        System.out.println( getPluginContext() );
//
//        Map map = getPluginContext();
//        map.forEach((key, value) -> {
//            System.out.println( "\n" + key + ": " + value );
//        });
//        File f = outputDirectory;
//
//        if ( !f.exists() )
//        {
//            f.mkdirs();
//        }
//
//        File touch = new File( f, "touch.txt" );
//
//        FileWriter w = null;
//        try
//        {
//            w = new FileWriter( touch );
//
//            w.write( "touch.txt" );
//        }
//        catch ( IOException e )
//        {
//            throw new MojoExecutionException( "Error creating file " + touch, e );
//        }
//        finally
//        {
//            if ( w != null )
//            {
//                try
//                {
//                    w.close();
//                }
//                catch ( IOException e )
//                {
//                    // ignore
//                }
//            }
//        }
    }

}