package exceptions;

import play.api.UsefulException;

/**
 * For business flow exception, usually is common to happen
 */
public class BusinessException extends UsefulException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
