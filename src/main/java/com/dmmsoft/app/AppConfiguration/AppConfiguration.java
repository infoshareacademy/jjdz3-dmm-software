package com.dmmsoft.app.AppConfiguration;

import com.dmmsoft.app.Exception.AppConfigurationException;
import com.dmmsoft.app.FileIO.FilePath;
import com.dmmsoft.app.FileIO.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* Created by Milo4321 on 2017-02-09.
 *
 * reads configuration settings form Configuration.json and maps to AppConfiguration object
 *
 */
public class AppConfiguration {

    List<FilePath> fundFilePaths = new ArrayList<>();
    List<FilePath> currencyFilePaths = new ArrayList<>();

    public List<FilePath> getFundFilePaths() {
        return fundFilePaths;
    }

    public List<FilePath> getCurrencyFilePaths() {
        return currencyFilePaths;
    }

    public AppConfiguration() {
        // Note! Do not remove this "dummy" constructor (com.fasterxml.jackson JSONMapper will stop working).
    }

    public AppConfiguration(String ConfigurationJSONFileName) throws Exception {
        FileReader fileReader = new FileReader(ConfigurationJSONFileName);
        try {
            String jsonString = fileReader.getFileAsString();
            JSONMapper jsonMapper = new JSONMapper(jsonString);

            this.fundFilePaths = jsonMapper.getAppConfigurationFromJson().fundFilePaths;
            this.currencyFilePaths = jsonMapper.getAppConfigurationFromJson().currencyFilePaths;

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (AppConfigurationException e) {
            System.out.println("Error creating the AppConfiguration: " + e.getMessage());
        }
    }


}