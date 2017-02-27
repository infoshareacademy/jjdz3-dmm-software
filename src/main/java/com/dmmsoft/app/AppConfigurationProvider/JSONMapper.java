package com.dmmsoft.app.AppConfigurationProvider;

import java.io.IOException;

import com.dmmsoft.app.Exception.AppConfigurationException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONMapper {

    private final String jsonString;

    public JSONMapper(String jsonString) {
        this.jsonString = jsonString;
    }

    public AppConfigurationProvider getAppConfigurationFromJson() throws AppConfigurationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, AppConfigurationProvider.class);
        } catch (IOException e) {
            throw new AppConfigurationException(e);
        }
    }
}
