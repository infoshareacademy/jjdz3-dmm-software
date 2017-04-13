package com.dmmsoft.app.AppConfiguration.Exception;

public class AppConfigurationProviderException extends RuntimeException {
    private final static String message = "Error creating the AppConfiguration: ";

    public AppConfigurationProviderException(Throwable previousException) {
        super(message + previousException.getMessage());
    }
}