package eu.codetopic.java.utils.exception;

import java.net.ConnectException;

public class WrongLoginDataException extends ConnectException {

    public WrongLoginDataException() {
        super();
    }

    public WrongLoginDataException(String detailMessage) {
        super(detailMessage);
    }
}
