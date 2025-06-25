// Copyright 2010-2025 Jason Osgood
// SPDX-License-Identifier: Apache-2.0

package normalsql.knockout;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class
    IN
extends
    Knockout<IN.Pattern>
{
    public enum Pattern
    {
        Literals
    }

    public ParseTree column;
    public List<ParseTree> literals = new ArrayList<>();

    public IN( RuleContext context, Parser parser )
    {
        super( context );
        var gah = XPath.findAll( context, "/term/term", parser );
        Iterator<ParseTree> iter = gah.iterator();
        var ddd = iter.next();

        while( iter.hasNext() )
        {
            literals.add(  iter.next() );
        }

        // TODO make this work

//        var column = context.findFirst( "term" );
//        if( column.getRuleName().equals( "column" ) )
//        {
//            this.column = column;
//        }
//
//        var terms = context.find( "terms/term" );
//        for( var term : terms )
//        {
//            if( term.getRuleName().equals( "literal" ) )
//            {
//                literals.add( term );
//            }
//        }
//
//        // Verify all the terms are literals
//        if( terms.size() == literals.size() )
//        {
//            pattern = Pattern.Literals;
//        }
    }
}
