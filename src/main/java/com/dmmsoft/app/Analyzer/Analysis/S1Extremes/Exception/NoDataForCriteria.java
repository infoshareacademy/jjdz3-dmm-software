package com.dmmsoft.app.Analyzer.Analysis.S1Extremes.Exception;

public class NoDataForCriteria extends RuntimeException {

    private final static String message = "Brak danych do analizy dla zadanych kryteriów.";

    public NoDataForCriteria() {
        super(message);
    }
}