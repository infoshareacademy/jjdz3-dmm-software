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

    public static AppConfiguration Create(String ConfigurationJSONFileName) throws Exception {
        AppConfiguration foo = new AppConfiguration();
        foo.Initialize(ConfigurationJSONFileName);
//        foo.doStufff();
//        doo external stuff

        return foo;
    }

    public AppConfiguration Initialize(String ConfigurationJSONFileName) throws  Exception{
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

        return this;
    }
}


class AppConfigurationFactory {
    public AppConfiguration Create(String configurationJsonFilePath) throws Exception {
        return new AppConfiguration().Initialize(configurationJsonFilePath);
    }
}



class Fund {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;
}

class BuildExample {
    void Main() {
        FundStorageFactory fundStorageFactory = new FundStorageFactory();

        // 1 - from file
        // 2 - from db
        int input = 0;
        FundStorageInterface storage = fundStorageFactory.create(input);

        List<Fund> funds = storage.get();
    }
}

class FundStorageFactory {
    FundStorageInterface create(int loaderType) {
        FundStorageInterface storage;

        switch (loaderType) {
            case 1:
                storage = new FileReaderFundStorage();
            case 2:
                storage = new DbFundStorage();
            default:
                storage = new FileReaderFundStorage();
        }

        return storage;
    }
}



interface FundStorageInterface {
    List<Fund> get();
}

class FileReaderFundStorage implements FundStorageInterface {

    @Override
    public List<Fund> get() {
        // read from file
        return null;
    }
}

class DbFundStorage implements FundStorageInterface{

    @Override
    public List<Fund> get() {
        // load from db
        return null;
    }
}