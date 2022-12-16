// Copyright 2010-2022 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.meta;

import normalsql.parse.NormalSQLParser.TableRefContext;

public class
	TableRef
{
	public TableRef( TableRefContext context )
	{
		schema = context.schema != null ? context.schema.getTrimmedText() : null;
		domain = context.domain != null ? context.domain.getTrimmedText() : null;
		table = context.table != null ? context.table.getTrimmedText() : null;
	}

	public String schema;
	public String domain;
	public String table;
}
