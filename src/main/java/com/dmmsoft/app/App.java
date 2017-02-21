package com.dmmsoft.app;


import com.dmmsoft.app.AppConfiguration.AppConfiguration;
import com.dmmsoft.app.FileIO.FilePath;

public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello World!");

        // quick demo of AppConfiguration usage (to remove)

        AppConfiguration appCon = new AppConfiguration().initialize();

        System.out.println("\n*** Paths from AppConfiguration object:");
        for (FilePath fp : appCon.getFundFilePaths())
            System.out.println(fp.getFilePath());

        for (FilePath fp : appCon.getCurrencyFilePaths())
            System.out.println(fp.getFilePath());
    }
}
