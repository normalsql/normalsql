// Copyright 2010-2024 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.postgresql;

import normalsql.grammar.PostgreSQLParser.*;

import java.util.ArrayList;
import java.util.List;

public class
    IN
extends
    Knockout<TermINContext, IN.Pattern>
{
    public enum Pattern
    {
        Literals
    }

    public TermColumnContext column;
    public List<TermLiteralContext> literals = new ArrayList<>();

    public IN( TermINContext context )
    {
        super( context );

        if( context.term() instanceof TermColumnContext col )
        {
            column = col;
        }

        if( context.terms() != null && context.terms().term() != null )
        {
            for( var term : context.terms().term() )
            {
                if( term instanceof TermLiteralContext literal )
                {
                    literals.add( literal );
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
