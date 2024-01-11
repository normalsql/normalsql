// Copyright 2010-2023 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.parse.NormalSQLParser.*;
import normalsql.parse.NormalSQLParser.TermContext;
import normalsql.parse.NormalSQLParser.SubtermContext;

import java.util.ArrayList;
import java.util.List;

public class
    IN
extends
    Knockout<SubtermINContext, IN.Pattern>
{
    public enum Pattern
    {
        Literals
    }

    public SubtermContext column;
    public List<SubtermLiteralContext> literals = new ArrayList<>();

    public IN( SubtermINContext context )
    {
        super( context );

        column = context.subterm();
        if( !( column instanceof SubtermLiteralContext )) return;

        if( context.terms() != null && context.terms().term() != null )
        {
            for( TermContext term : context.terms().term() )
            {
                SubtermContext sc = term.subterm();
                if( sc instanceof SubtermLiteralContext )
                {
                    literals.add( (SubtermLiteralContext) sc );
                }
            }

            // Verify all the terms are literals
            if( context.terms().term().size() == literals.size() )
            {
                pattern = Pattern.Literals;
            }
        }
    }
}
