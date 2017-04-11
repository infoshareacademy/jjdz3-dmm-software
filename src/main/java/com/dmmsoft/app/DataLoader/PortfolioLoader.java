package com.dmmsoft.app.DataLoader;

import com.dmmsoft.app.AppConfigurationProvider.AppConfigurationProvider;
import com.dmmsoft.app.FileIO.FilePath;
import com.dmmsoft.app.POJO.Currency;
import com.dmmsoft.app.POJO.Fund;
import com.dmmsoft.app.POJO.Investment;
import com.dmmsoft.app.POJO.Portfolio;

import java.util.ArrayList;
import java.util.List;

public class PortfolioLoader {

    private AppConfigurationProvider appCon;
    private List<Fund> funds = new ArrayList<>();
    private List<Currency> currencies = new ArrayList<>() ;
    private List<Investment> investments = new ArrayList<>();
    private Portfolio portfolio = new Portfolio();
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

    public PortfolioLoader(AppConfigurationProvider appCon) {
        this.appCon = appCon;
    }

    public Portfolio getPortfolio() {
        portfolio.setInvestments(investments);
        portfolio.setFundsCount(fundLoader.getNumberOfFunds());
        portfolio.setCurrenciesCount(currencyLoader.getNumberOfCurrencies());
        return portfolio;
    }
}
