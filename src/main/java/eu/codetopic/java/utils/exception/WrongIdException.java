package eu.codetopic.java.utils.exception;

public class WrongIdException extends RuntimeException {

    public WrongIdException() {
        super();
    }

    public WrongIdException(String detailMessage) {
        super(detailMessage);
    }

    public WrongIdException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public WrongIdException(Throwable throwable) {
        super(throwable);
    }
}
