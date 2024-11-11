// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0


package normalsql;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>Config class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
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
    public Path cwd;

    public void validate() throws Exception
    {
        if( url == null || url.isEmpty() )
        {
            throw new NullPointerException( "JDBC URL cannot be null or empty" );
        }

        sourcePath = Paths.get( source ).toAbsolutePath().normalize();
        if( Files.notExists( sourcePath ))
        {
            throw new FileNotFoundException( "Source directory does not exist: " + sourcePath );
        }

        targetPath = ( target == null ? sourcePath : Paths.get( target ));

        if( Files.notExists( targetPath ))
        {
            Files.createDirectories( targetPath );
        }

        if( password != null && !password.isEmpty() )
        {
            if( username == null || username.isEmpty() )
            {
                throw new NullPointerException( "using password also requires username" );
            }
        }
    }

    public String toString()
    {
        return Glorp.toMap( this ).toString();
    }
}
