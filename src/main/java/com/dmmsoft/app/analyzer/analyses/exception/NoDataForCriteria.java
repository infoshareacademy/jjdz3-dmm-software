package com.dmmsoft.app.analyzer.analyses.exception;

public class NoDataForCriteria extends Exception {

   private final static String message = "No data for current criteria.";

    public NoDataForCriteria() {
       super(message);
    }
}