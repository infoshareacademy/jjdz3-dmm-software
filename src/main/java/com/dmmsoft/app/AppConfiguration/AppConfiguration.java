package com.dmmsoft.app;

import com.dmmsoft.app.Exception.AppConfigurationException;

import java.io.IOException;
import java.sql.RowIdLifetime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milo4321 on 2017-02-09.
 */
public class AppConfiguration {

    List<FilePath> fundFilePaths = new ArrayList<FilePath>();
    List<FilePath> currencyFilePaths = new ArrayList<FilePath>();

    public List<FilePath> getFundFilePaths() {
        return fundFilePaths;
    }

    public List<FilePath> getCurrencyFilePaths() {
        return currencyFilePaths;
    }

    public AppConfiguration() {

        // Note! Do not remove this "dummy" constructor (Jackson JSONMapper will stop working).
    }

    public AppConfiguration(String ConfigurationJSONFileName) throws Exception {
        FileReader fileReader = new FileReader(ConfigurationJSONFileName);
        try {
            String jsonString = fileReader.getFileAsString();
            JSONMapper jsonMapper = new JSONMapper(jsonString);

            this.fundFilePaths = jsonMapper.getAppConfigurationFromJson().fundFilePaths;
            this.currencyFilePaths = jsonMapper.getAppConfigurationFromJson().currencyFilePaths;

        } catch (IOException exception) {
            System.out.println("Error reading the file: " + exception.getMessage());
        } catch (AppConfigurationException exception) {
            System.out.println("Error creating the AppConfiguration: " + exception.getMessage());
        }
    }


}
