package normalsql.meta;

import normalsql.parse.NormalSQLParser;
import normalsql.parse.NormalSQLParser.*;
import normalsql.parse.NormalSQLParser.TermContext;
import normalsql.parse.NormalSQLParser.PredicateINContext;

import java.util.ArrayList;
import java.util.List;

public class
    IN
extends
    Predicate<PredicateINContext, IN.Pattern>
{
    public enum Pattern
    {
        Literals
    }

    public TermContext column;
    public List<TermLiteralContext> literals = new ArrayList<>();

    public IN( PredicateINContext context )
    {
        super( context );

        // TODO: fix when parent is CASE
        column = (TermContext) context.parent.getChild(0);
        if( !( column instanceof TermColumnContext )) return;

        if( context.terms() != null && context.terms().term() != null )
        {
            for( TermContext term : context.terms().term() )
            {
//                TermContext sc = term.term();
                if( term != null && term instanceof TermLiteralContext )
                {
                    literals.add( (TermLiteralContext) term );
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
