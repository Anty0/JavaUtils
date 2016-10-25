package eu.codetopic.java.utils.exception;

public class SourceUnavailableException extends RuntimeException {

    public SourceUnavailableException() {
    }

    public SourceUnavailableException(String s) {
        super(s);
    }

    public SourceUnavailableException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SourceUnavailableException(Throwable throwable) {
        super(throwable);
    }
}
