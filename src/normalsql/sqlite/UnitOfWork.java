// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.sqlite;

import normalsql.Config;
import normalsql.Glorp;
import normalsql.template.PreparedStatementParameter;
import normalsql.template.ResultSetColumn;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the unit of work for processing one (1) SQL source file.
 *
 */

// TODO support multiple statements
// TODO support multiple resultsets
public class UnitOfWork
{
	public UnitOfWork() {}

	public UnitOfWork( Path file ) { sourceFile = file; }

	public UnitOfWork( Path file, Config config )
	{
		this.sourceFile = file;
		this.sourceDir = this.sourceFile.toAbsolutePath().getParent();

		// Duplicate (mirror? clone?) directory structure for output
		var packagePath = config.sourcePath.relativize( this.sourceDir );
		this.targetDir = config.targetPath.resolve( packagePath );
		if( Files.notExists( this.targetDir ))
		{
            try
            {
				// TODO move this to somewhere else
                Files.createDirectories( this.targetDir );
            }
            catch( IOException e )
            {
				this.oops = e;
//                throw new RuntimeException( e );
            }
        }

		// infers package name from directory structure, following javac's convention
		this.packageName = packagePath.toString().replace( File.separatorChar, '.' );

		var clazz = Glorp.getClassSimpleName( this.sourceFile );
		this.statementClassName = clazz;

		// TODO custom suffix for ResultSet
		this.resultSetClassName = this.statementClassName + "ResultSet";

		// TODO why isn't targetFile for ResultSet class also here?
		this.targetFile = this.targetDir.resolve( clazz + ".java" );
	}

	Statement root;

	public Path sourceDir;
	public Path sourceFile;
	public Path targetDir;
	public Path targetFile;
	public String packageName;
	public String statementClassName;
	public String resultSetClassName;
	public String originalSQL;
	public String preparedSQL;
	public String printfSQL;

	public List<PreparedStatementParameter> params = new ArrayList<>();
	public List<ResultSetColumn> columns = new ArrayList<>();

	public Exception oops;

	/**
	 * Class name of generated key returned by INSERT statements
	 */
	public String keyClassName = "not used";
}
