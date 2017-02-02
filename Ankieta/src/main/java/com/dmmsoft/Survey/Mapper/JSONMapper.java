package com.dmmsoft.Survey.Mapper;

import com.dmmsoft.Survey.Exception.SurveyCreationException;
import com.dmmsoft.Survey.Survey;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONMapper {

    private final String jsonString;

    public JSONMapper(String jsonString) {
        this.jsonString = jsonString;
    }

    public Survey getSurveyFromJson() throws SurveyCreationException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, Survey.class);
        } catch (IOException exception) {
            throw new SurveyCreationException(exception);
        }
    }
}
