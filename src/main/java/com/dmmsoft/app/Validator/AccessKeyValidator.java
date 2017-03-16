package com.dmmsoft.app.Validator;

import java.util.List;

public class AccessKeyValidator extends Validator {

    private List<String> allowedAccessKeys;
    private String enteredString;

    public AccessKeyValidator setAllowedAccessKeys(List<String> allowedAccessKeys) {
        this.allowedAccessKeys = allowedAccessKeys;
        return this;
    }

    public AccessKeyValidator setEnteredString(String enteredString) {
        this.enteredString = enteredString;
        return this;
    }

    public boolean validate() {
        String regex = "^(?i)[" + String.join("|", allowedAccessKeys) + "]";
        return enteredString.matches(regex);
    }
}
