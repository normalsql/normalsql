/*
 Fado - GloggingRuleContext.java

 Copyright 2022, 2014, 2011, 2010 Jason Osgood

 Custom RuleContext superclass. Used by SQL parser grammar.

 Inlines globbing path expressions. Alternative to ANTLR's stock separate XPath
 query expression thing.

 TODO: Add find*String globbing methods to eliminate .getText() in client code.

 Utility methods (removing quotes, setText), because I don't know where else to put them.

*/
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

    public List<GlobbingRuleContext> find( String query )
    {
        return find( query, false );
    }

    public <T extends GlobbingRuleContext> List<T> find( Class<? extends T> type, String query ) {
        List<GlobbingRuleContext> found = find( query );
        List<T> results = new ArrayList<>();
        for( GlobbingRuleContext o : found )
        {
            if( type.isInstance( o ))
            {
                results.add( type.cast( o ));
            }
        }
        return results;
    }

    public <T extends GlobbingRuleContext> T findFirst( Class<? extends T> type, String expression )
    {
        T result = null;
        List<T> found = find( type, expression );
        if( found.size() > 0 )
        {
            result = found.get( 0 );
        }
        return result;
    }

    // TODO delete this
//    public List<GlobbingRuleContext> findContexts( String query )
//    {
//        List<GlobbingRuleContext> list = find( query );
//        ArrayList<GlobbingRuleContext> result = new ArrayList<>();
//        for( Object ugh : list )
//        {
//            if( ugh instanceof GlobbingRuleContext )
//            {
//                result.add( (GlobbingRuleContext) ugh );
//            }
//        }
//        return result;
//    }

    // TODO: validate expression
    protected List<GlobbingRuleContext> find( String query, boolean first )
    {
        if( query == null )
        {
            throw new NullPointerException( "expression" );
        }

        ArrayList<String> split = new ArrayList<>();
        for( String atom : query.split( "/" ))
        {
            atom = atom.trim();
            if( atom.length() == 0 )
            {
                throw new IllegalArgumentException( query + " contains empty match string" );
            }
            split.add( atom );
        }

        ArrayList<GlobbingRuleContext> result = new ArrayList<>();
        find( first, this, split, 0, false, result );
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

    public String trimQuotes( String text )
    {
        if( text == null ) return null;
        // Parser ensures token has quotes front and back
        if( text.indexOf( '[' ) > -1 || text.indexOf( '"' ) > -1 || text.indexOf( '\'' ) > -1 )
        {
            text = text.substring( 1, text.length() - 1 );
        }
        return text;
    }

    // Replaces original literal value with a JDBC input parameter '?'
    // TODO: Handle unary numbers, eg -100
    // TODO: Knockout whole 'literal' rule context?
    // TODO: Change this method to setText( ... )?
//    static int param = 0;
    public void convertToInputParam()
    {
        // Replace text of first "visible" (non whitespace) token, then exit
        Token start = getStart();
        if( start instanceof WritableToken )
        {
//            ((WritableToken) start).setText( "?" + param++ );
            ((WritableToken) start).setText( "?" );
        }
    }
}
