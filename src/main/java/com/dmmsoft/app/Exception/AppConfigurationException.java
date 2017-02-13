package com.dmmsoft.app.Exception;

public class AppConfigurationException extends RuntimeException {
    private final static String message = "Error creating the AppConfiguration: ";

    public AppConfigurationException(Throwable previousException) {
        super(message + previousException.getMessage());
    }
}