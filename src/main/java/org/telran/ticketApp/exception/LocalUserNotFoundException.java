package org.telran.ticketApp.exception;

public class LocalUserNotFoundException extends RuntimeException {
    public LocalUserNotFoundException(String message) {
        super(message);
    }
}
