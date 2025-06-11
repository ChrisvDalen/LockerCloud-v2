package com.example.protocol;

/**
 * Thrown when a protocol message cannot be parsed.
 */
public class ProtocolParseException extends Exception {
    public ProtocolParseException(String message) {
        super(message);
    }

    public ProtocolParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
