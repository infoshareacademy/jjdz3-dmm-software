package com.dmmsoft.app;


import com.dmmsoft.app.AppConfiguration.AppConfiguration;
import com.dmmsoft.app.FileIO.FilePath;
import com.dmmsoft.app.Investment.FundData;

public class App {
    public static void main(String[] args) throws Exception {


        System.out.println("Hello World!");

        FundData fundData = new FundData();
        fundData.loadDataFromFile("/Users/Daniel/Desktop/AGI001.txt");
       /* examples for methods */
        System.out.println(fundData.getNumberOfFunds());
        System.out.println(fundData.getFundNumber(0));
        System.out.println(fundData.getFundNumberVolume(0));


        // quick test of AppConfiguration (to remove)

        AppConfiguration appCon = new AppConfiguration("Configuration.json");

        System.out.println("\n*** Paths from AppConfiguration object:");
        for (FilePath fp : appCon.getFundFilePaths()) {
            System.out.println(fp.getFilePath());
        }
        for (FilePath fp : appCon.getCurrencyFilePaths()) {
            System.out.println(fp.getFilePath());
        }
    }
}


