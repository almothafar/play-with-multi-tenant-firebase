package exceptions;

import play.api.UsefulException;

/**
 * For internal application exceptions, usually it is a critical
 */
public class ApplicationException extends UsefulException {

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }


}
