package com.dmmsoft.app.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DateRangeValidator extends Validator {

    private static final String INVALID_START_DATE = "Data rozpoczęcia notowań jest nieprawidłowa.";
    private static final String INVALID_STOP_DATE = "Data zakończenia notowań jest nieprawidłowa.";
    private static final String INVALID_DATE_ORDER = "Data zakończenia nie może być przed datą rozpoczęcia.";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private String startDateStr;
    private String stopDateStr;
    private LocalDate startDate;
    private LocalDate stopDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }

    public DateRangeValidator setStartDate(String startDateStr) {
        this.startDateStr = startDateStr;
        return this;
    }

    public DateRangeValidator setStopDate(String stopDateStr) {
        this.stopDateStr = stopDateStr;
        return this;
    }

    public boolean validate() {
        boolean isStartDateValid = validateStartDate();
        boolean isStopDateValid = validateStopDate();
        boolean isDateOrderValid = false;
        if (isStartDateValid && isStopDateValid) {
            isDateOrderValid = validateDateOrder();
        }
        return isStartDateValid && isStopDateValid && isDateOrderValid;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    private boolean validateStartDate() {
        try {
            startDate = LocalDate.parse(startDateStr, formatter);
            return true;
        } catch (DateTimeParseException exception) {
            messages.add(INVALID_START_DATE);
            return false;
        }
    }

    private boolean validateStopDate() {
        try {
            stopDate = LocalDate.parse(stopDateStr, formatter);
            return true;
        } catch (DateTimeParseException exception) {
            messages.add(INVALID_STOP_DATE);
            return false;
        }
    }

    private boolean validateDateOrder() {
        if (stopDate.isBefore(startDate)) {
            messages.add(INVALID_DATE_ORDER);
            return false;
        }
        return true;
    }
}
