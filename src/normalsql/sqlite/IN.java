// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.sqlite;

import normalsql.grammar.PostgreSQLParser.TermColumnContext;
import normalsql.grammar.PostgreSQLParser.TermINContext;
import normalsql.grammar.PostgreSQLParser.TermLiteralContext;

import java.util.ArrayList;
import java.util.List;

public class
    IN
extends
    KnockoutL<IN.Pattern>
{
    public enum Pattern
    {
        Literals
    }

    public GlobbingRuleContext column;
    public List<GlobbingRuleContext> literals = new ArrayList<>();

    public IN( GlobbingRuleContext context )
    {
        super( context );

        var column = context.findFirst( "term" );
        if( column.getRuleName().equals( "column" ) )
        {
            this.column = column;
        }

        var terms = context.find( "terms/term" );
        for( var term : terms )
        {
            if( term.getRuleName().equals( "literal" ) )
            {
                literals.add( term );
            }
        }

        // Verify all the terms are literals
        if( terms.size() == literals.size() )
        {
            pattern = Pattern.Literals;
        }
    }
}
