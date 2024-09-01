package normalsql.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 *  Runs NormalSQL's executable
 *
 */

@Mojo( name = "normalsql", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class NormalSQLMojo
    extends AbstractMojo
{
    /**
     * Location of the file.
     */
//    @Parameter( defaultValue = "${project.build.directory}", property = "outputDir", required = true )
//    private File outputDirectory;

    public void execute()
       // throws MojoExecutionException
    {
        System.out.println( "\n\n\n\n$$$  I hate Maven  TOO $$$\n\n\n\n" );
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
