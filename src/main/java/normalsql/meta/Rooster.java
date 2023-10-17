package normalsql.meta;

import normalsql.parse.NormalSQLParser.*;

import java.util.ArrayList;
import java.util.List;

public class
    Rooster
extends
    Predicate<InsertContext, Rooster.Pattern>
{
    public enum Pattern
    {
        Literals
    }

    public Insert insert;
//    public SubtermContext column;
    public List<SubtermLiteralContext> literals = new ArrayList<>();

    public Rooster( InsertContext context, Insert insert )
    {
        super( context );
        this.insert = insert;
        pattern = Pattern.Literals;
    }
}
