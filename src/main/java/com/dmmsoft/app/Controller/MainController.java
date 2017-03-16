package com.dmmsoft.app.Controller;

import com.dmmsoft.app.Analyzer.Extremes.Exception.NoDataForCriteria;
import com.dmmsoft.app.Analyzer.Extremes.Extremes;
import com.dmmsoft.app.Analyzer.Extremes.LocalExtremes;
import com.dmmsoft.app.Analyzer.Extremes.ExtremesResult;
import com.dmmsoft.app.AppConfigurationProvider.AppConfigurationProvider;
import com.dmmsoft.app.Controller.Exception.IllegalFlow;
import com.dmmsoft.app.DataLoader.PortfolioLoader;
import com.dmmsoft.app.Interface.Menu.*;
import com.dmmsoft.app.Interface.Prompt;
import com.dmmsoft.app.Interface.Statement;
import com.dmmsoft.app.Investment.Portfolio;
import com.dmmsoft.app.ValueObject.DateRange;

import java.util.Optional;

public final class MainController {

    private final Statement interfaceStatement = new Statement();
    private final Prompt interfacePrompt = new Prompt();
    private Portfolio portfolio;

    public void init() {
        interfaceStatement.printSplashMessage();
        loadPortfolio();
        analyze();
    }

    private void loadPortfolio() {
        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        PortfolioLoader portfolioLoader = new PortfolioLoader(appCon);
        ItemId typeOfInvestment = askForInputFromMenu(new SelectTypeOfInvestmentMenu());
        switch (typeOfInvestment) {
            case CURRENCIES:
                portfolioLoader.loadCurrencies();
                break;
            case FUNDS:
                portfolioLoader.loadFunds();
                break;
        }
        portfolio = portfolioLoader.getPortfolio();
        interfaceStatement.printInvestmentCount(portfolio);
    }


    private ItemId askForInputFromMenu(Menu menu) throws IllegalFlow {
        Optional<MenuItem> selectedItem = interfacePrompt.askForInputFromMenu(menu);
        if (!selectedItem.isPresent()) {
            throw new IllegalFlow();
        }
        return selectedItem.get().getItemId();
    }

    private void getGlobalExtremes() {
        Extremes globalExtremes = new Extremes(portfolio.getInvestments());
        try {
            ExtremesResult globalExtremesResult = globalExtremes.getExtremes();
            interfaceStatement.printExtremesResult(globalExtremesResult, Extremes.TYPE.GLOBAL);
        } catch (NoDataForCriteria exception) {
            System.out.printf(exception.getMessage());
        }
    }

    private void getLocalExtremes(DateRange dateRange) {
        Extremes localExtremes = new LocalExtremes(
            dateRange.getStartDate(),
            dateRange.getStopDate(),
            portfolio.getInvestments()
        );
        try {
            ExtremesResult localExtremesResult = localExtremes.getExtremes();
            interfaceStatement.printExtremesResult(localExtremesResult, Extremes.TYPE.LOCAL);
        } catch (NoDataForCriteria exception) {
            System.out.printf(exception.getMessage());
        }
    }

    private void analyze() {
        ItemId typeOfAnalysis = askForInputFromMenu(new SelectTypeOfAnalysisMenu());
        switch (typeOfAnalysis) {
            case GLOBAL_EXTREMES:
                getGlobalExtremes();
                break;
            case LOCAL_EXTREMES:
                DateRange dateRange = interfacePrompt.askForStartStopDates();
                getLocalExtremes(dateRange);
                break;
        }
    }
}
