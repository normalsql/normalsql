package normalsql;

import java.util.function.*;

public class PreCLI {

    record Option( int id, String name, Consumer c ) {
        public Option()
        {
            this( 1,
            "",
            null
            );
        }
    }

    public static void main(String[] args) {
            PreCLI cli = new PreCLI();
            cli.setup();
    }

    public void setup()
    {
        Option p = new Option(1, "John", x -> this.vorgon = (Integer) x );
        Option p2 = new Option(1, "John", x -> this.vorgon = (int) x );

        p.c.accept( 2 );

        System.out.println( "vorgon: " + vorgon );
        p2.c.accept( 3 );

        System.out.println( "vorgon: " + vorgon );
    }

    public int vorgon = 1;

}
