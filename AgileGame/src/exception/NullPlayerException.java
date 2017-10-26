package exception;

/**
 * Created by Ryan on 10/22/2017.
 */
public class NullPlayerException extends Exception {
    public NullPlayerException() {}

    public NullPlayerException(String message){super(message);}

    public NullPlayerException(Throwable cause) {super(cause);}

    public NullPlayerException(String message, Throwable cause){ super(message, cause); }

    public NullPlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) { super(message, cause, enableSuppression, writableStackTrace);}
}
