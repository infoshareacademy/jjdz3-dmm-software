package com.dmmsoft.Survey.Exception;

public class SurveyCreationException extends RuntimeException {

    private final static String message = "There was an error creating the survey: ";

    public SurveyCreationException(Throwable previousException) {
        super(message + previousException.getMessage());
    }
}
