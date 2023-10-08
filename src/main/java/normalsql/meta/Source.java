// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser;
import normalsql.parse.NormalSQLParser.SubtermRowContext;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Source class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class Source
{
	public TableRef tableRef;
	public String alias;
	public List<SubtermRowContext> rows = new ArrayList<>();
}
