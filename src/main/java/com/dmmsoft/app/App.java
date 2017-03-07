package com.dmmsoft.app;


import com.dmmsoft.app.AppConfiguration.AppConfiguration;
import com.dmmsoft.app.DataLoader.CurrencyLoader;
import com.dmmsoft.app.DataLoader.FundLoader;
import com.dmmsoft.app.FileIO.FilePath;


import static java.lang.System.*;

public class App {
    public static void main(String[] args) throws Exception {


        FundLoader Funds = new FundLoader();
        CurrencyLoader Currencies = new CurrencyLoader();



        AppConfiguration appCon = new AppConfiguration("Configuration.json");

        out.println("\n*** Paths from AppConfiguration object:");


        for (FilePath fp : appCon.getFundFilePaths())
        {

            out.println(fp.getFilePath());
            Funds.CreateFundsFromFile(fp.getFilePath());


        }
        System.out.println(""+Funds.getNumberOfFunds());



        for (FilePath fp : appCon.getCurrencyFilePaths())
        {
            out.println(fp.getFilePath());
            Currencies.CreateCurrencysFromFile(fp.getFilePath());


        }
        System.out.println(""+Currencies.GetNumberOfCurrencies());

    }

}


