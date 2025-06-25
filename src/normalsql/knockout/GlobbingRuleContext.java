// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

/*
 NormalSQL - GlobbingRuleContext.java

 Custom RuleContext superclass. Used by SQL parser grammar.

 Inlines globbing path expressions. Alternative to ANTLR's stock separate XPath
 query expression thing.

 Utility methods (removing quotes, setText), because I don't know where else to put them.

*/
package normalsql.knockout;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.WritableToken;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>GlobbingRuleContext class.</p>
 *
 * @author jasonosgood
 * @version $Id: $Id
 */
public class
GlobbingRuleContext
extends
        ParserRuleContext
        implements Iterable<GlobbingRuleContext>
{
    /**
     * <p>Constructor for GlobbingRuleContext.</p>
     */
    public GlobbingRuleContext()
    {
    }



    /**
     * <p>Constructor for GlobbingRuleContext.</p>
     *
     * @param parent              a {@link org.antlr.v4.runtime.ParserRuleContext} object
     * @param invokingStateNumber a int
     */
    public GlobbingRuleContext( ParserRuleContext parent, int invokingStateNumber )
    {
        super( parent, invokingStateNumber );
    }

    /**
     * <p>find.</p>
     *
     * @param query a {@link java.lang.String} object
     * @return a {@link java.util.List} object
     */
    public List<GlobbingRuleContext> find( String query )
    {
        return find( query, false );
    }

    /**
     * <p>find.</p>
     *
     * @param type  a {@link java.lang.Class} object
     * @param query a {@link java.lang.String} object
     * @param <T>   a T class
     * @return a {@link java.util.List} object
     */
    public <T extends GlobbingRuleContext> List<T> find( Class<? extends T> type, String query )
    {
        List<GlobbingRuleContext> found   = find( query );
        List<T>                   results = new ArrayList<>();
        for( GlobbingRuleContext o : found )
        {
            if( type.isInstance( o ) )
            {
                results.add( type.cast( o ) );
            }
        }
        return results;
    }

    /**
     * <p>findFirst.</p>
     *
     * @param type       a {@link java.lang.Class} object
     * @param expression a {@link java.lang.String} object
     * @param <T>        a T class
     * @return a T object
     */
    public <T extends GlobbingRuleContext> T findFirst( Class<? extends T> type, String expression )
    {
        T       result = null;
        List<T> found  = find( type, expression );
        if( found.size() > 0 )
        {
            result = found.get( 0 );
        }
        return result;
    }

    // TODO: validate globbing query expression

    /**
     * <p>find.</p>
     *
     * @param query a {@link java.lang.String} object
     * @param first a boolean
     * @return a {@link java.util.List} object
     */
    protected List<GlobbingRuleContext> find( String query, boolean first )
    {
        if( query == null )
        {
            throw new NullPointerException( "expression" );
        }

        ArrayList<String> split = new ArrayList<>();
        for( String atom : query.split( "/" ) )
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

    private static void find( boolean first, ParserRuleContext parent, ArrayList<String> query, int nth, boolean seeking, ArrayList<GlobbingRuleContext> result )
    {
        String spot = query.get( nth );
        if( "**".equals( spot ) )
        {
            find( first, parent, query, nth + 1, true, result );
        }
        for( Object temp : parent.children )
        {
            if( temp instanceof GlobbingRuleContext child )
            {
                boolean wildcard = "*".equals( spot );
                boolean match    = child.getRuleName().equals( spot );
//                boolean match = false;
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


    public String name;

    public String getRuleName()
    {
        if( name == null )
        {
            name = this.getClass().getSimpleName()
                    // TODO just lowercase first character, to preserve camelcase rule names
                    .replaceFirst( "Context$", "" ).toLowerCase();
        }
        return name;
    }

    /**
     * <p>findFirst.</p>
     *
     * @param expression a {@link java.lang.String} object
     * @return a {@link normalsql.knockout.GlobbingRuleContext} object
     */
    public GlobbingRuleContext findFirst( String expression )
    {
        GlobbingRuleContext       result = null;
        List<GlobbingRuleContext> found  = find( expression, true );
        if( found.size() > 0 )
        {
            result = found.get( 0 );
        }
        return result;
    }

    /**
     * <p>findFirstString.</p>
     *
     * @param expression a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     */
//    public String findFirstString( String expression )
//    {
//        GlobbingRuleContext first = findFirst( expression );
//        return first != null ? first.getTrimmedText() : null;
//    }

    /**
     * <p>trimQuotes.</p>
     *
     * @param text a {@link java.lang.String} object
     * @return a {@link java.lang.String} object
     */
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

    /**
     * <p>getTrimmedText.</p>
     *
     * @return a {@link java.lang.String} object
     */
//    public String getTrimmedText()
//    {
//        return trimQuotes( getText() );
//    }

    // TODO: Handle unary numbers, eg -100

    /**
     * <p>setStartTokenText.</p>
     *
     * @param text a {@link java.lang.String} object
     */
    public void setStartTokenText( String text )
    {
        // Replace text of first "visible" (non whitespace) token, then exit
        WritableToken start = (WritableToken) getStart();
        start.setText( text );
    }

    /**
     * <p>toString.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String toString()
    {
        return getText();
    }

    /**
     * @return 
     */
    @Override
    public Iterator<GlobbingRuleContext> iterator()
    {
        ArrayList<GlobbingRuleContext> kids = new ArrayList<>();
        for( ParseTree child : children )
        {
            if( child instanceof GlobbingRuleContext kid )
            {
                kids.add( kid );
            }
        }

        return kids.iterator();
    }
}
