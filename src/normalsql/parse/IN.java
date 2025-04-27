// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.parse;

import normalsql.grammar.NormalSQLParser.*;
import normalsql.grammar.NormalSQLParser.SubtermContext;

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

        if( context.subterm() instanceof SubtermColumnContext )
        {
            column = context.subterm();
            if( context.terms() != null && context.terms().term() != null )
            {
                for( var term : context.terms().term() )
                {
                    var sc = term.subterm();
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
}
