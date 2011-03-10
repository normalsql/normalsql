package fado;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

public class FadoOptions 
{
	public FadoOptions()
	{
		
	}
	public final static String DRIVER = "driver";
	public final static String URL = "url";
	public final static String USERNAME = "username";
	public final static String PASSWORD = "password";
	public final static String PACKAGENAME = "pkg";
	public final static String SOURCE = "source";
	public final static String TARGET = "target";
	
	// TODO: Load from fado.properties, before command line parsing
	// TODO: Command line option for source fado.properties
	// TODO: validate
	// TODO: print help
	
	public void parse( String[] args )
	{
		OptionParser parser = new OptionParser();
		parser.accepts( DRIVER ).withRequiredArg().defaultsTo( driver );
		parser.accepts( URL ).withRequiredArg();
		parser.accepts( USERNAME ).withRequiredArg();
		parser.accepts( PASSWORD ).withRequiredArg();

		parser.accepts( PACKAGENAME ).withRequiredArg();
		parser.accepts( SOURCE ).withRequiredArg();
		parser.accepts( TARGET ).withRequiredArg();
		
		OptionSet options = parser.parse( args );
		
		driver = (String) options.valueOf( DRIVER );
		url = (String) options.valueOf( URL );
		username = (String) options.valueOf( USERNAME );
		password = (String) options.valueOf( PASSWORD );
		packageName = (String) options.valueOf( PACKAGENAME );
		source = (String) options.valueOf( SOURCE );
		target = (String) options.valueOf( TARGET );
	}
	
	String driver = "org.h2.Driver";
	String url = "jdbc:h2:tcp://localhost/~/hydra";
	String username = "sa";
	String password = "";
	
	String packageName = "ganesh";
	String source = "./demo/ganesh/";
	
	String target = "./generated/";

}
