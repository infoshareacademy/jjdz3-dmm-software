package com.dmmsoft.app.DataLoader;

import com.dmmsoft.app.AppConfigurationProvider.AppConfigurationProvider;
import com.dmmsoft.app.FileIO.FilePath;
import com.dmmsoft.app.Investment.Investment;
import com.dmmsoft.app.Investment.Portfolio;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * Created by Milo4321 on 2017-03-08.
 */
public class PorfolioLoader {

    public static Portfolio getPortfolio() {

        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        FundLoader Funds = new FundLoader();
        CurrencyLoader Currencies = new CurrencyLoader();

        List<Investment> temp = new ArrayList<>();
        Portfolio p = new Portfolio();

        for (FilePath fp : appCon.getFundFilePaths()) {
            out.println(fp.getFilePath());
            Funds.CreateFundsFromFile(fp.getFilePath());
        }

        for (FilePath fp : appCon.getCurrencyFilePaths()) {
            out.println(fp.getFilePath());
            Currencies.CreateCurrencysFromFile(fp.getFilePath());
        }
        temp.addAll(Funds.getFunds());
        temp.addAll(Currencies.getCurrencies());
        p.setInvestments(temp);

        System.out.println("Loaded number of Funds from JSON:" + Funds.GetNumberOfFunds());
        System.out.println("Loaded number of Currencies from JSON:" + Currencies.GetNumberOfCurrencies());

        return p;
    }


}
