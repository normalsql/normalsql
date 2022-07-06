package fado.parse;

import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.WritableToken;

import java.util.ArrayList;
import java.util.List;

public class //
    GlobbingRuleContext
extends
    ParserRuleContext
{
    public GlobbingRuleContext( ParserRuleContext parent, int invokingStateNumber )
    {
        super( parent, invokingStateNumber );
    }

//    public GlobbingRuleContext() { }

    public List<Object> find(String expression )
    {
        return find( expression, false );
    }

    // TODO: Rename 'findNodes' et al 'findContexts'
    public List<GlobbingRuleContext> findNodes( String expression )
    {
        List<Object> list = find( expression );
        ArrayList<GlobbingRuleContext> result = new ArrayList<GlobbingRuleContext>();
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
    protected List<Object> find( String expression, boolean first )
    {
        if( expression == null )
        {
            throw new NullPointerException( "expression" );
        }

        ArrayList<String> query = new ArrayList<String>();
        for( String atom : expression.split( "/" ))
        {
            atom = atom.trim();
            if( atom.length() == 0 )
            {
                throw new IllegalArgumentException( query + " contains empty match string" );
            }
            query.add( atom );
        }

        ArrayList<Object> result = new ArrayList<Object>();
        find( first, this, query, 0, false, result );
        return result;
    }

    private static void find( boolean first, ParserRuleContext parent, ArrayList<String> query, int nth, boolean seeking, ArrayList<Object> result  )
    {
        String spot = query.get( nth );
        if( "**".equals( spot ))
        {
            find( first, parent, query, nth + 1, true, result );
        }
        for( Object child : parent.children )
        {
            boolean wildcard = "*".equals( spot );
            boolean tokenQuery = Character.isUpperCase( spot.charAt( 0 ));
            boolean nodeQuery = Character.isLowerCase( spot.charAt( 0 ));

            if( child instanceof Token && ( wildcard || tokenQuery ) )
            {
                Token token = (Token) child;
                int type = getLexType( spot );
                if( token.getType() == type )
                {
                    result.add( token );
                }
            }
            else if( child instanceof ParserRuleContext && ( wildcard || nodeQuery ) )
            {
                ParserRuleContext childNode = (ParserRuleContext) child;
                if( wildcard || childNode.getRuleName().equals( spot ))
                {
                    if( nth + 1 < query.size() )
                    {
                        find( first, childNode, query, nth + 1, false, result );
                    }
                    else
                    {
                        result.add( childNode );
                    }
                }
//				else
                else if( seeking )
                {
                    find( first, childNode, query, nth, true, result );
                }
            }

            if( first && result.size() > 0 ) break;
        }
    }

    private static int getLexType( String spot )
    {
        return 0;
    }

    public Object findFirst( String expression )
    {
        Object result = null;
        List<Object> found = find( expression, true );
        if( found.size() > 0 )
        {
            result = found.get( 0 );
        }
        return result;
    }

    public GlobbingRuleContext findFirstNode( String expression )
    {
        GlobbingRuleContext result = null;
        Object first = findFirst( expression );
        if( first instanceof GlobbingRuleContext )
        {
            result = (GlobbingRuleContext) first;
        }
        return result;
    }

    public String findFirstString( String expression )
    {
        String result = null;
        Object first = findFirst( expression );
        if( first instanceof Token)
        {
            String ugh = first.toString();
            result = ((Token) first).getText();
        }
        else if( first instanceof GlobbingRuleContext )
        {
            result = ((GlobbingRuleContext) first).getText();
        }
        return result;
    }

    // Replace original token literal value with a JDBC input parameter '?'
    // TODO: Handle unary numbers, eg -100
    // TODO: Knockout whole 'literal' rule context?
    // TODO: Rename method to something like 'convertToInputParameter
    public void convertToJDBCParam()
    {
        // Replace text of first "visible" (non whitespace) token, then exit
        Token start = getStart();
        if( start instanceof WritableToken )
        {
            ((WritableToken) start).setText( "?" );
        }

//        for( Object o : children )
//        {
//            if( o instanceof CommonToken )
//            {
//                CommonToken token = (CommonToken) o;
//                if( token.getChannel() != Token.HIDDEN_CHANNEL )
//                {
//                    token.setText( "?" );
//                    break;
//                }
//            }
//        }
    }
}
