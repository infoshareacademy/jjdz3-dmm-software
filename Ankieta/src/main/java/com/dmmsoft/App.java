package com.dmmsoft;

import com.dmmsoft.Survey.Exception.SurveyCreationException;
import com.dmmsoft.Survey.Mapper.JSONMapper;

import com.dmmsoft.Survey.Survey;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!");

        FileReader fileReader = new FileReader("test.json");

        try {
            String jsonString = fileReader.getFileAsString();
            JSONMapper jsonMapper = new JSONMapper(jsonString);
            Survey survey = jsonMapper.getSurveyFromJson();
            System.out.println(survey.getId());
        } catch (IOException exception) {
            System.out.println("Error reading the file: " + exception.getMessage());
        } catch (SurveyCreationException exception) {
            System.out.println("Error creating the survey: " + exception.getMessage());
        }
    }
}
