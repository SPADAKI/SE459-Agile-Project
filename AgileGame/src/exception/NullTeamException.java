package exception;

/**
 * Created by Ryan on 10/22/2017.
 */
public class NullTeamException extends Exception {
    public NullTeamException() {}

    public NullTeamException(String message){super(message);}

    public NullTeamException(Throwable cause) {super(cause);}

    public NullTeamException(String message, Throwable cause){ super(message, cause); }

    public NullTeamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) { super(message, cause, enableSuppression, writableStackTrace);}
}
