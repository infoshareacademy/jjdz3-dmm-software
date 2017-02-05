package com.dmmsoft;

import com.dmmsoft.Survey.Exception.SurveyCreationException;
import com.dmmsoft.Survey.Mapper.JSONMapper;
import com.dmmsoft.Survey.Survey;
import com.dmmsoft.UserInterface.InterfaceFlow;
import java.io.IOException;

/**
 * Survey by dmmsoft
 */
public class App {
    public static void main(String[] args) {

        FileReader fileReader = new FileReader("test.json");
        try {
            String jsonString = fileReader.getFileAsString();
            JSONMapper jsonMapper = new JSONMapper(jsonString);
            Survey survey = jsonMapper.getSurveyFromJson();

            InterfaceFlow.startFlow(survey);

            //TODO here: saving Survay object to JSON file with different file name

        } catch (IOException exception) {
            System.out.println("Error reading the file: " + exception.getMessage());
        } catch (SurveyCreationException exception) {
            System.out.println("Error creating the survey: " + exception.getMessage());
        }
    }
}
