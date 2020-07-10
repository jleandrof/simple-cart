package com.example.simpleecommerce.auth.exceptions;

public class AuthTokenParsingException extends Exception{
    public AuthTokenParsingException(String message) {
        super(message);
    }

    public AuthTokenParsingException(String message, Throwable cause) {
        super(message, cause);
    }
}
