package normalsql.parse;

import normalsql.parse.NormalSQLParser.*;
import normalsql.parse.NormalSQLParser.TermContext;
import normalsql.parse.NormalSQLParser.SubtermContext;
import normalsql.parse.NormalSQLParser.PredicateINContext;
import org.antlr.v4.runtime.tree.ParseTree;

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
    public List<SubtermValueContext> literals = new ArrayList<>();

    public IN( SubtermINContext context )
    {
        super( context );

        column = context.subterm();
        if( !( column instanceof SubtermValueContext )) return;

        if( context.terms() != null && context.terms().term() != null )
        {
            for( TermContext term : context.terms().term() )
            {
                SubtermContext sc = term.subterm();
                if( sc instanceof SubtermValueContext )
                {
                    literals.add( (SubtermValueContext) sc );
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
