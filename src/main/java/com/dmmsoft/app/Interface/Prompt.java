package com.dmmsoft.app.Interface;

import com.dmmsoft.app.Interface.Menu.*;
import com.dmmsoft.app.Validator.AccessKeyValidator;
import com.dmmsoft.app.Validator.DateRangeValidator;
import com.dmmsoft.app.ValueObject.DateRange;

import java.util.Optional;

public class Prompt extends ConsoleInterface {

    private static final String USER_DATE_FORMAT = "RRRR-MM-DD";
    private static final String ENTER_START_DATE = "Podaj datę rozpoczęcia notowań (" + USER_DATE_FORMAT + "):";
    private static final String ENTER_STOP_DATE = "Podaj datę zakończenia notowań (" + USER_DATE_FORMAT + "):";

    public Prompt() {
        super();
    }

    public DateRange askForStartStopDates() {
        DateRangeValidator validator = new DateRangeValidator();
        while (true) {
            validator.clearMessages();
            validator.setStartDate(getInputFromUser(ENTER_START_DATE))
                .setStopDate(getInputFromUser(ENTER_STOP_DATE));
            if (validator.validate()) {
                return new DateRange(validator.getStartDate(), validator.getStopDate());
            }
            validator.getMessages().forEach(errorOutput::println);
        }
    }

    public Optional<MenuItem> askForInputFromMenu(Menu menu) {
        AccessKeyValidator validator = new AccessKeyValidator();
        validator.setAllowedAccessKeys(menu.getAccessKeys());
        output.println(menu.getPromptMessage());
        while (true) {
            String enteredString = getInputFromUser(menu.getPrintableMenu()).trim();
            validator.clearMessages();
            validator.setEnteredString(enteredString);
            if (validator.validate()) {
                return menu.getItemByAccessKey(enteredString);
            }
            validator.getMessages().forEach(errorOutput::println);
        }
    }

    private String getInputFromUser(String prompt) {
        String enteredString = "";
        while (enteredString.trim().isEmpty()) {
            enteredString = console.readLine(prompt + " ");
        }
        return enteredString;
    }
}
