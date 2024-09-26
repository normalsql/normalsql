package normalsql;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Config
{
    public String description;
    public String url;
    public String username;
    public String password;
    public String source;
    public Path   sourcePath;
    public String target;
    public Path   targetPath;
    public String pkg;
    public String extension = ".sql";

    public void validate() throws Exception
    {
        if( url == null )
        {
            throw new NullPointerException( "JDBC URL is null" );
        }

        // TODO re-verify need to use absolute path for later steps
        sourcePath = Paths.get( source ).toAbsolutePath();
        if( Files.notExists( sourcePath ))
        {
            // TODO add phase, id, goal for better context?
            throw new FileNotFoundException( "Source directory does not exist: " + sourcePath );
        }

        targetPath = ( target == null ? sourcePath : Paths.get( target ));

        if( Files.notExists( targetPath ))
        {
            Files.createDirectories( targetPath );
        }
    }
}
