// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package test;

import normalsql.parse.NormalSQLLexer;
import normalsql.parse.NormalSQLParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;

import java.nio.file.Path;
import java.util.ArrayList;

import static normalsql.parse.NormalSQLParser.*;

public class Profiler
{
	public static void main( String... args )
	{
		String sql =
		"""
		select * from (SELECT 1);
		"""
		;

		var chars = CharStreams.fromString( sql );
		var lexer = new NormalSQLLexer( chars );
		var tokens = new CommonTokenStream( lexer );
		var parser = new NormalSQLParser( tokens );
		parser.setProfile( true );
parser.getInterpreter().setPredictionMode( PredictionMode.LL_EXACT_AMBIG_DETECTION);
	    var script = parser.script();

		var pi = parser.getParseInfo();

		var di = pi.getDecisionInfo();

	}

}
