package fado;

public class 
	FadoException 
extends 
	Exception 
{
	public FadoException( String message )
	{
		super( message );
	}
	public FadoException( String message, Throwable t )
	{
		super( message, t );
	}

}
