package normalsql.knockout;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Collection;
import java.util.List;

public class
    Row
extends
    Knockout<Row.Pattern>
{
    public enum Pattern
    {
        Literals;
    }

    public Insert          insert;
    public List<ParseTree> literals;

//    public Row( GlobbingRuleContext context, Insert insert )
//    {
//        super( context );
//        this.insert = insert;
//        pattern = Pattern.Literals;
//    }
    public Row( ParseTree context )
    {
        super( context );
//        this.insert = insert;
        pattern = Pattern.Literals;
    }
}
