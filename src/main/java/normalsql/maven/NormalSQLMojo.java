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
    requiresDependencyResolution = ResolutionScope.COMPILE //,
//    requiresProject = true
//    , threadSafe = true
)
public class NormalSQLMojo
    extends AbstractMojo
{
    @Parameter (property = "project", required = true, readonly = true)
    protected MavenProject project;

    @Parameter
    private String description;

    @Parameter
    private String driver;

    @Parameter
    private String url;

    @Parameter
    private String username;

    @Parameter
    private String password;

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
        System.out.println( this );
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
        String sb =
            "NormalSQLMojo{" + "\nproject=" + project +
            ", \ndescription='" + description + '\'' +
            ", \ndriver='" + driver + '\'' +
            ", \nurl='" + url + '\'' +
            ", \nusername='" + username + '\'' +
            ", \npassword='" + password + '\'' +
            ", \nsource='" + source + '\'' +
            ", \ntarget='" + target + '\'' +
            '}';
        return sb;
    }
}
