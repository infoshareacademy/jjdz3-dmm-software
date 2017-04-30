package com.dmmsoft.app.consoleapp.validator;

import java.util.ArrayList;
import java.util.List;

public class Validator {

    protected List<String> messages = new ArrayList<>();

    public List<String> getMessages() {
        return messages;
    }

    public void clearMessages() {
        this.messages = new ArrayList<>();
    }
}
