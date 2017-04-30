package com.dmmsoft.app.appconfiguration.exception;

public class AppConfigurationProviderException extends RuntimeException {
    private final static String message = "Error creating the appconfiguration: ";

    public AppConfigurationProviderException(Throwable previousException) {
        super(message + previousException.getMessage());
    }
}