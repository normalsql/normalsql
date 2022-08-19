package fado;
import static fado.parse.GenericSQLParser.*;

public class IN extends Condition
{
	public boolean horse = true;
	public IN( ColumnRefContext columnRef, LiteralContext... literals )
	{
		super( columnRef, literals );
	}
}
