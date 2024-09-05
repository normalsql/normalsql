package normalsql.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

/**
 *  Runs NormalSQL's executable
 *
 */

@Mojo(
    name = "normalsql",
    defaultPhase = LifecyclePhase.GENERATE_SOURCES,
    requiresDependencyResolution = ResolutionScope.COMPILE,
    requiresProject = true
//    , threadSafe = true
)
public class NormalSQLMojo
    extends AbstractMojo
{
    @Parameter (property = "project", required = true, readonly = true)
    protected MavenProject project;

    @Parameter //( property = "normalsql.description", defaultValue = "Default description" )
    private String description;

    @Parameter // ( property = "normalsql.driver", defaultValue = "org.h2.Driver" )
    private String driver;

    @Parameter // ( property = "normalsql.url", defaultValue = "jdbc:h2:mem:" )
    private String url;

    @Parameter // ( property = "normalsql.username" )
    private String username;

    @Parameter // ( property = "normalsql.password" )
    private String password;

    @Parameter
    private String gorp = "gorp!";

    @Parameter( property = "normalsql.source", defaultValue = "${project.basedir}/src/main/sql" )
//    @Parameter( defaultValue = "${project.basedir}/src/main/sql" )
    private String source;

    //    @Parameter( property = "normalsql.source", defaultValue = "${project.basedir}/src/main/sql" )
    @Parameter( defaultValue = "${project.basedir}/src/main/sql" )
    private String target;


//        #package = dogbone

    public void execute()
       // throws MojoExecutionException
    {
        System.out.println( "\n\n\n\n$$$  I hate Maven 3 $$$\n\n\n\n" );
//        System.out.println( "\n\n\n\n$$$  gorp: " + gorp + " $$$\n\n\n\n" );
        System.out.println( this.toString() );
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NormalSQLMojo{");
        sb.append("\nproject=").append(project);
        sb.append(", \ndescription='").append(description).append('\'');
        sb.append(", \ndriver='").append(driver).append('\'');
        sb.append(", \nurl='").append(url).append('\'');
        sb.append(", \nusername='").append(username).append('\'');
        sb.append(", \npassword='").append(password).append('\'');
        sb.append(", \ngorp='").append(gorp).append('\'');
        sb.append(", \nsource='").append(source).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
