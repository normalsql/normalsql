package normalsql;

import org.apache.maven.plugin.logging.Log;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MavenLogShim implements LogShim
{
    Consumer<CharSequence>             a;
    Consumer<Throwable>                b;
    BiConsumer<CharSequence,Throwable> c;
    Supplier<Boolean>                           d;

    public enum Level{ debug, info, warn, error }

    public final Level level;

    MavenLogShim( Level level, Log log )
    {
        this.level = level;

        switch( level )
        {
            case debug -> {
                a = log::debug;
                b = log::debug;
                c = log::debug;
                d = log::isDebugEnabled;
            }
            case info -> {
                a = log::info;
                b = log::info;
                c = log::info;
                d = log::isInfoEnabled;
            }
            case warn -> {
                a = log::warn;
                b = log::warn;
                c = log::warn;
                d = log::isWarnEnabled;
            }
            case error -> {
                a = log::error;
                b = log::error;
                c = log::error;
                d = log::isErrorEnabled;
            }
        }
    }
    /**
     * Log message
     *
     * @param msg
     */
    public void log( CharSequence msg ) { a.accept( msg ); }

    /**
     * Log Throwable (exception)
     *
     * @param t
     */
    public void log( Throwable t ) { b.accept( t ); }

    /**
     * Log message and Throwable (exception)
     *
     * @param msg
     * @param t
     */
    public void log( CharSequence msg, Throwable t ) { c.accept( msg, t ); }

    /**
     *  Is enabled
     */
    public boolean isEnabled() { return d.get(); }

}
