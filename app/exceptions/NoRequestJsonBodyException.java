package exceptions;

import play.api.UsefulException;

/**
 * if teh api that supposed to have a body but received with empty
 */
public class NoRequestJsonBodyException extends UsefulException {

    public NoRequestJsonBodyException(String message) {
        super(message);
    }

    public NoRequestJsonBodyException(String message, Throwable cause) {
        super(message, cause);
    }
}
