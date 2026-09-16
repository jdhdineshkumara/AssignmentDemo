package com.assignment.application.demo.common.exception;

public class UtilityNotFoundException extends RuntimeException {

    public UtilityNotFoundException(String message, Throwable cause) {
        super("Utility Not found " + message, cause);
    }

}
