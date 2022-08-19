package fado;
import static fado.parse.GenericSQLParser.*;

public class Condition // implements Comparable<Condition>
{
	public ColumnRefContext columnRef;
	public String tableName;
	public String columnName;
	public From from;
	public Column column;
	public LiteralContext[] literals;

	public Condition( ColumnRefContext columnRef, LiteralContext... literals )
	{
		this.columnRef = columnRef;
		this.literals = literals;
		if( columnRef != null )
		{
			this.columnName = columnRef.trimQuotes( columnRef.columnName().getText() );
			// Null-safe query to get 'tableName'
			this.tableName = columnRef.trimQuotes( columnRef.findFirstString( "tableName" ));
		}
	}

//	@Override
//	public int compareTo( Condition that )
//	{
//		int a = this.columnRef.start.getStartIndex();
//		int b = that.columnRef.start.getStartIndex();
//		return a - b;
//	}
}
