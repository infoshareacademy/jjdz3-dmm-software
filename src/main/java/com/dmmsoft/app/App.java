package com.dmmsoft.app;


import com.dmmsoft.app.AppConfiguration.AppConfiguration;
import com.dmmsoft.app.FileIO.FilePath;
import com.dmmsoft.app.Investment.Fund;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello World!");

        // quick test of AppConfiguration (to remove)

        AppConfiguration appCon = new AppConfiguration().Initialize("Configuration.json");

        AppConfiguration configuration = AppConfiguration.Create("Configuration.json");

        System.out.println("\n*** Paths from AppConfiguration object:");
        for(FilePath fp : appCon.getFundFilePaths()) {
            System.out.println(fp.getFilePath());
        }
        for(FilePath fp : appCon.getCurrencyFilePaths()) {
            System.out.println(fp.getFilePath());
        }
    }


}
