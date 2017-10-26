package exception;

/**
 * Created by Ryan on 10/22/2017.
 */
public class DuplicatePlayerException extends Exception {
    public DuplicatePlayerException() {}

    public DuplicatePlayerException(String message){super(message);}

    public DuplicatePlayerException(Throwable cause) {super(cause);}

    public DuplicatePlayerException(String message, Throwable cause){ super(message, cause); }

    public DuplicatePlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) { super(message, cause, enableSuppression, writableStackTrace);}
}
