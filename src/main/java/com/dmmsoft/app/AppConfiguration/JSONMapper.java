package com.dmmsoft.app;

import java.io.IOException;

import com.dmmsoft.app.Exception.AppConfigurationException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONMapper {

    private final String jsonString;

    public JSONMapper(String jsonString) {
        this.jsonString = jsonString;
    }

    public AppConfiguration getAppConfigurationFromJson() throws AppConfigurationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, AppConfiguration.class);
        } catch (IOException exeption) {
            throw new AppConfigurationException(exeption);
        }
    }
}
