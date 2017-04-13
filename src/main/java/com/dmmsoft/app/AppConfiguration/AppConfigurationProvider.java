package com.dmmsoft.app.AppConfiguration;

import com.dmmsoft.app.AppConfiguration.Exception.AppConfigurationProviderException;
import com.dmmsoft.app.FileIO.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dmmsoft.app.FileIO.Path.CurrencyFolderPath;
import com.dmmsoft.app.FileIO.Path.FilePath;
import com.dmmsoft.app.FileIO.Path.FundFolderPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* Created by Milo4321 on 2017-02-09.
 * reads configuration settings form Configuration.json and maps to AppConfiguration object
 */
public class AppConfigurationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfigurationProvider.class);
    private final String CONFIGURATION_FILE_PATH = "Configuration3.json";

    private FundFolderPath fundFolderPath;
    private CurrencyFolderPath currencyFolderPath;

    private List<FilePath> fundFilePaths = new ArrayList<>();
    private List<FilePath> currencyFilePaths = new ArrayList<>();


    public List<FilePath> getFundFilePaths() {return fundFilePaths;}
    public List<FilePath> getCurrencyFilePaths() {
        return currencyFilePaths;
    }

    private void setFundFolderPath(FundFolderPath fundFolderPath) {
        this.fundFolderPath = fundFolderPath;
    }
    private void setCurrencyFolderPath(CurrencyFolderPath currencyFolderPath) {
        this.currencyFolderPath = currencyFolderPath;
    }

    private FundFolderPath getFundFolderPath() {
        return fundFolderPath;
    }

    private CurrencyFolderPath getCurrencyFolderPath() {
        return currencyFolderPath;
    }

    public AppConfigurationProvider getConfiguration() {
        FileReader fileReader = new FileReader(CONFIGURATION_FILE_PATH);
        try {
            String jsonString = fileReader.getFileAsString();
            JSONMapper jsonMapper = new JSONMapper(jsonString);

            this.fundFilePaths = jsonMapper.getAppConfigurationFromJson().fundFilePaths;
            this.currencyFilePaths = jsonMapper.getAppConfigurationFromJson().currencyFilePaths;

            this.fundFolderPath = jsonMapper.getAppConfigurationFromJson().fundFolderPath;
            this.currencyFolderPath = jsonMapper.getAppConfigurationFromJson().currencyFolderPath;

            if(fundFilePaths.isEmpty()||fundFilePaths==null) {
                this.fundFilePaths = this.generateFilePaths(this.getFundFolderPath().getFolderPath(),
                        this.getFileNameList(fundFolderPath.getFolderPath()));
            }

            if(currencyFilePaths.isEmpty()||currencyFilePaths==null) {
                this.currencyFilePaths = this.generateFilePaths(currencyFolderPath.getFolderPath(),
                        this.getFileNameList(currencyFolderPath.getFolderPath()));
            }


        } catch (IOException e) {
            LOGGER.info("Error reading the file: " + e.getMessage());
        } catch (AppConfigurationProviderException e) {
            LOGGER.info("Error creating the AppConfiguration: " + e.getMessage());
        }
        return this;
    }



    private List<String> getFileNameList(String folderPath){
        List<String> fileNames = new ArrayList<>();

        File[] files = new File(folderPath).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }

    private List<FilePath> generateFilePaths(String folderPath, List<String>fileList){


        List<FilePath> filePaths = new ArrayList<>();
        for(String fileName : fileList){
            filePaths.add(new FilePath(folderPath.concat(fileName)))  ;
        }
        return filePaths;
    }


}
