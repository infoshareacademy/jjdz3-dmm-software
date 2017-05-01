package com.dmmsoft.app.analyzer.analyses.exception;

public class NoDataForCriteria extends RuntimeException {

    private final static String message = "Brak danych do analizy dla zadanych kryteriów.";

    public NoDataForCriteria() {
        super(message);
    }
}