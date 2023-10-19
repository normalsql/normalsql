package normalsql.parse;

import normalsql.parse.NormalSQLParser.*;
import normalsql.parse.NormalSQLParser.TermContext;
import normalsql.parse.NormalSQLParser.SubtermContext;
import normalsql.parse.NormalSQLParser.PredicateINContext;

import java.util.ArrayList;
import java.util.List;

public class
    IN
extends
    Knockout<PredicateINContext, IN.Pattern>
{
    public enum Pattern
    {
        Literals
    }

    public SubtermContext column;
    public List<SubtermLiteralContext> literals = new ArrayList<>();

    public IN( PredicateINContext context )
    {
        super( context );

        // TODO: fix when parent is CASE
        column = (SubtermContext) context.parent.getChild(0);
        if( !( column instanceof SubtermColumnContext )) return;

        if( context.terms() != null && context.terms().term() != null )
        {
            for( TermContext term : context.terms().term() )
            {
                SubtermContext sc = term.subterm();
                if( sc != null && sc instanceof SubtermLiteralContext )
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
