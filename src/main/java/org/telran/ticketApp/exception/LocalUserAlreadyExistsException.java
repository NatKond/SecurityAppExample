package org.telran.ticketApp.exception;

public class LocalUserAlreadyExistsException extends RuntimeException {
    public LocalUserAlreadyExistsException(String message) {
        super(message);
    }
}
