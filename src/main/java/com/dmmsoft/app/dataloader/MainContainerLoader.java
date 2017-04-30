package com.dmmsoft.app.dataloader;

import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.fileIO.path.FilePath;
import com.dmmsoft.app.pojo.Currency;
import com.dmmsoft.app.pojo.Fund;
import com.dmmsoft.app.pojo.Investment;
import com.dmmsoft.app.pojo.MainContainer;

import java.util.ArrayList;
import java.util.List;

public class MainContainerLoader {

    private AppConfigurationProvider appCon;
    private List<Fund> funds = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>() ;
    private List<Investment> investments = new ArrayList<>();
    private MainContainer mainContainer = new MainContainer();
    private FundLoader fundLoader = new FundLoader();
    private CurrencyLoader currencyLoader = new CurrencyLoader();

    public void loadFunds() {
        appCon.getFundFilePaths().forEach((FilePath filePath) -> {
            fundLoader.createFundsFromFile(filePath.getFilePath());
        });
        funds = fundLoader.getFunds();
        investments.addAll(funds);
    }

    public void loadCurrencies() {
        appCon.getCurrencyFilePaths().forEach((FilePath filePath) -> {
            currencyLoader.createCurrenciesFromFile(filePath.getFilePath());
        });
        currencies = currencyLoader.getCurrencies();
        investments.addAll(currencies);
    }

    public MainContainerLoader(AppConfigurationProvider appCon) {
        this.appCon = appCon;
    }

    public MainContainer getMainContainer() {
        mainContainer.setInvestments(investments);
        mainContainer.setFundsCount(fundLoader.getNumberOfFunds());
        mainContainer.setCurrenciesCount(currencyLoader.getNumberOfCurrencies());
        return mainContainer;
    }
}
