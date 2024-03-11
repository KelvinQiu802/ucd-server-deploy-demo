package com.example.springsecurejwtv2.exception;

public class UserNameAlreadyExistsException extends RuntimeException {

    public UserNameAlreadyExistsException(String message) {
        super(message);
    }

    public UserNameAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}

