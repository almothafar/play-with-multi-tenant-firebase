package exceptions;

import play.api.UsefulException;

/**
 * For invalid authorization exceptions, a common exception
 */
public class AuthorizationException extends UsefulException {

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
