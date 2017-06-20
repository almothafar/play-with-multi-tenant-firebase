package controllers.base;

public class ErrorResult {
    private int errorCode = 1;
    private String message;
    private String stackTrace;


    public int getErrorCode() {
        return errorCode;
    }

    public ErrorResult setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public ErrorResult setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }
}
