// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql;

import normalsql.template.ResultSetColumn;
import normalsql.template.PreparedStatementParameter;
import normalsql.parse.Statement;

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
//	public UnitOfWork() {}

	public UnitOfWork( Path file ) { sourceFile = file; }

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
	/**
	 * Class name of generated key returned by INSERT statements
	 */
	public String keyClassName = "not used";
}
