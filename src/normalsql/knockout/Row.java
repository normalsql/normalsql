package normalsql.knockout;

import java.util.ArrayList;
import java.util.List;

public class
    Row
extends
    Knockout<Row.Pattern>
{
    public enum Pattern
    {
        Literals
    }

    public Insert                   insert;
    public List<GlobbingRuleContext> literals = new ArrayList<>();

//    public Row( GlobbingRuleContext context, Insert insert )
//    {
//        super( context );
//        this.insert = insert;
//        pattern = Pattern.Literals;
//    }
    public Row( GlobbingRuleContext context )
    {
        super( context );
//        this.insert = insert;
        pattern = Pattern.Literals;
    }
}
