package com.dmmsoft.app.AppConfigurationProvider;

import com.dmmsoft.app.Exception.AppConfigurationException;
import com.dmmsoft.app.FileIO.FilePath;
import com.dmmsoft.app.FileIO.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Created by Milo4321 on 2017-02-09.
 * reads configuration settings form Configuration.json and maps to AppConfigurationProvider object
 */
public class AppConfigurationProvider {

    private final String CONFIGURATION_FILE_PATH = "Configuration.json";

    private List<FilePath> fundFilePaths = new ArrayList<>();
    private List<FilePath> currencyFilePaths = new ArrayList<>();

    public List<FilePath> getFundFilePaths() {
        return fundFilePaths;
    }
    public List<FilePath> getCurrencyFilePaths() {
        return currencyFilePaths;
    }


    public AppConfigurationProvider getConfiguration() {
        FileReader fileReader = new FileReader(CONFIGURATION_FILE_PATH);
        try {
            String jsonString = fileReader.getFileAsString();
            JSONMapper jsonMapper = new JSONMapper(jsonString);

            this.fundFilePaths = jsonMapper.getAppConfigurationFromJson().fundFilePaths;
            this.currencyFilePaths = jsonMapper.getAppConfigurationFromJson().currencyFilePaths;

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (AppConfigurationException e) {
            System.out.println("Error creating the AppConfigurationProvider: " + e.getMessage());
        }
        return this;
    }


}
