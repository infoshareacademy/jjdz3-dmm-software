package com.dmmsoft.app.Exception;

public class AppConfigurationProviderException extends RuntimeException {
    private final static String message = "Error creating the AppConfigurationProvider: ";

    public AppConfigurationProviderException(Throwable previousException) {
        super(message + previousException.getMessage());
    }
}