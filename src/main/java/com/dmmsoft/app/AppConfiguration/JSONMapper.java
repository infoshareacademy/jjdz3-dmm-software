package com.dmmsoft.app.AppConfiguration;

import java.io.IOException;

import com.dmmsoft.app.AppConfiguration.Exception.AppConfigurationProviderException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONMapper {

    private final String jsonString;

    public JSONMapper(String jsonString) {
        this.jsonString = jsonString;
    }

    public AppConfigurationProvider getAppConfigurationFromJson() throws AppConfigurationProviderException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, AppConfigurationProvider.class);
        } catch (IOException e) {
            throw new AppConfigurationProviderException(e);
        }
    }


}
