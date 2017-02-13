package com.dmmsoft.app;

import com.dmmsoft.app.Exception.AppConfigurationException;

import java.io.IOException;

/**
 * Created by Milo4321 on 2017-02-09.
 */
public class AppConfiguration {

    String fundFilePath;
    String currencyFilePath;

    public String getFundFilePath() {
        return fundFilePath;
    }

    public String getCurrencyFilePath() {
        return currencyFilePath;
    }

    public AppConfiguration() {

        // Note! Do not remove this "dummy" constructor (Jackson JSONMapper will stop working).
    }

    public AppConfiguration(String ConfigurationJSONFileName) throws Exception {
        FileReader fileReader = new FileReader(ConfigurationJSONFileName);
        try {
            String jsonString = fileReader.getFileAsString();
            JSONMapper jsonMapper = new JSONMapper(jsonString);

            this.fundFilePath= jsonMapper.getAppConfigurationFromJson().fundFilePath;
            this.currencyFilePath=jsonMapper.getAppConfigurationFromJson().currencyFilePath;

        } catch (IOException exception) {
            System.out.println("Error reading the file: " + exception.getMessage());
        } catch (AppConfigurationException exception) {
            System.out.println("Error creating the AppConfiguration: " + exception.getMessage());
        }
    }


}
