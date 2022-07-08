package fado.parse;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.WritableToken;

import java.util.ArrayList;
import java.util.List;

public class
    GlobbingRuleContext
extends
    ParserRuleContext
{
    public GlobbingRuleContext( ParserRuleContext parent, int invokingStateNumber )
    {
        super( parent, invokingStateNumber );
    }

    public List<GlobbingRuleContext> find( String expression )
    {
        return find( expression, false );
    }

    public List<GlobbingRuleContext> findContexts( String expression )
    {
        List<GlobbingRuleContext> list = find( expression );
        ArrayList<GlobbingRuleContext> result = new ArrayList<>();
        for( Object ugh : list )
        {
            if( ugh instanceof GlobbingRuleContext )
            {
                result.add( (GlobbingRuleContext) ugh );
            }
        }
        return result;
    }

    // TODO: validate expression
    protected List<GlobbingRuleContext> find( String expression, boolean first )
    {
        if( expression == null )
        {
            throw new NullPointerException( "expression" );
        }

        ArrayList<String> query = new ArrayList<>();
        for( String atom : expression.split( "/" ))
        {
            atom = atom.trim();
            if( atom.length() == 0 )
            {
                throw new IllegalArgumentException( query + " contains empty match string" );
            }
            query.add( atom );
        }

        ArrayList<GlobbingRuleContext> result = new ArrayList<>();
        find( first, this, query, 0, false, result );
        return result;
    }

    private static void find( boolean first, ParserRuleContext parent, ArrayList<String> query, int nth, boolean seeking, ArrayList<GlobbingRuleContext> result  )
    {
        String spot = query.get( nth );
        if( "**".equals( spot ))
        {
            find( first, parent, query, nth + 1, true, result );
        }
        for( Object temp : parent.children )
        {
            if( temp instanceof GlobbingRuleContext )
            {
                boolean wildcard = "*".equals( spot );
                GlobbingRuleContext child = (GlobbingRuleContext) temp;
                boolean match = child.getRuleName().equals( spot );
                if( wildcard || match )
                {
                    if( nth + 1 < query.size() )
                    {
                        find( first, child, query, nth + 1, false, result );
                    }
                    else
                    {
                        result.add( child );
                    }
                }
                else if( seeking )
                {
                    find( first, child, query, nth, true, result );
                }
            }

            if( first && result.size() > 0 ) break;
        }
    }

    public GlobbingRuleContext findFirst( String expression )
    {
        GlobbingRuleContext result = null;
        List<GlobbingRuleContext> found = find( expression, true );
        if( found.size() > 0 )
        {
            result = found.get( 0 );
        }
        return result;
    }

//    public GlobbingRuleContext findFirstContext( String expression )
//    {
//        GlobbingRuleContext result = null;
//        Object first = findFirst( expression );
//        if( first instanceof GlobbingRuleContext )
//        {
//            result = (GlobbingRuleContext) first;
//        }
//        return result;
//    }

    public String findFirstString( String expression )
    {
        String result = null;
        Object first = findFirst( expression );
        if( first instanceof GlobbingRuleContext )
        {
            result = ((GlobbingRuleContext) first).getText();
        }
        return result;
    }

    // Replaces original value with a JDBC input parameter '?'
    // TODO: Handle unary numbers, eg -100
    // TODO: Knockout whole 'literal' rule context?
    public void convertToInputParam()
    {
        // Replace text of first "visible" (non whitespace) token, then exit
        Token start = getStart();
        if( start instanceof WritableToken )
        {
            ((WritableToken) start).setText( "?" );
        }
    }
}
