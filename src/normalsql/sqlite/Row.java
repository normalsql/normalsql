package normalsql.sqlite;

import normalsql.grammar.PostgreSQLParser.InsertContext;
import normalsql.grammar.PostgreSQLParser.TermLiteralContext;

import java.util.ArrayList;
import java.util.List;

public class
    Row
extends
        Knockout<InsertContext, Row.Pattern>
{
    public enum Pattern
    {
        Literals
    }

    public Insert                   insert;
    public List<TermLiteralContext> literals = new ArrayList<>();

    public Row( InsertContext context, Insert insert )
    {
        super( context );
        this.insert = insert;
        pattern = Pattern.Literals;
    }
}
