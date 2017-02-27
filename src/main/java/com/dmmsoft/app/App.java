package com.dmmsoft.app;


import com.dmmsoft.app.AppConfigurationProvider.AppConfigurationProvider;
import com.dmmsoft.app.FileIO.FilePath;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello World!");

        // quick demo of AppConfigurationProvider usage (to remove)

        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();

        System.out.println("\n*** Paths from AppConfigurationProvider object:");
        for (FilePath fp : appCon.getFundFilePaths())
            System.out.println(fp.getFilePath());

        for (FilePath fp : appCon.getCurrencyFilePaths())
            System.out.println(fp.getFilePath());
    }
}
