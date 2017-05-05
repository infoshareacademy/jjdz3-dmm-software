package com.dmmsoft.app.consoleapp.controller;

import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.extremes.Extremes;
import com.dmmsoft.app.analyzer.analyses.extremes.LocalExtremes;
import com.dmmsoft.app.analyzer.analyses.extremes.ExtremesResult;
import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.consoleapp.controller.exception.IllegalFlow;
import com.dmmsoft.app.consoleapp.userinterface.menu.*;
import com.dmmsoft.app.dataloader.MainContainerLoader;
import com.dmmsoft.app.model.MainContainer;
import com.dmmsoft.app.consoleapp.userinterface.Prompt;
import com.dmmsoft.app.consoleapp.userinterface.Statement;
import com.dmmsoft.app.consoleapp.valueobject.DateRange;

import java.util.Optional;

public final class MainController {

    private final Statement interfaceStatement = new Statement();
    private final Prompt interfacePrompt = new Prompt();
    private MainContainer mainContainer;

    public void init() {
        interfaceStatement.printSplashMessage();
        loadPortfolio();
        analyze();
    }

    private void loadPortfolio() {
        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);
        ItemId typeOfInvestment = askForInputFromMenu(new SelectTypeOfInvestmentMenu());
        switch (typeOfInvestment) {
            case CURRENCIES:
                mainContainerLoader.loadCurrencies();
                break;
            case FUNDS:
                mainContainerLoader.loadFunds();
                break;
        }
        mainContainer = mainContainerLoader.getMainContainer();
        interfaceStatement.printInvestmentCount(mainContainer);
    }


    private ItemId askForInputFromMenu(Menu menu) throws IllegalFlow {
        Optional<MenuItem> selectedItem = interfacePrompt.askForInputFromMenu(menu);
        if (!selectedItem.isPresent()) {
            throw new IllegalFlow();
        }
        return selectedItem.get().getItemId();
    }

    private void getGlobalExtremes() {
        Extremes globalExtremes = new Extremes(mainContainer.getInvestments());
        try {
            ExtremesResult globalExtremesResult = globalExtremes.getExtremes();
            interfaceStatement.printExtremesResult(globalExtremesResult, Extremes.TYPE.GLOBAL);
        } catch (NoDataForCriteria exception) {
            System.out.printf(exception.getMessage() + "\n");
        }
    }

    private void getLocalExtremes(DateRange dateRange) {
        Extremes localExtremes = new LocalExtremes(
            dateRange.getStartDate(),
            dateRange.getStopDate(),
            mainContainer.getInvestments()
        );
        try {
            ExtremesResult localExtremesResult = localExtremes.getExtremes();
            interfaceStatement.printExtremesResult(localExtremesResult, Extremes.TYPE.LOCAL);
        } catch (NoDataForCriteria exception) {
            System.out.printf(exception.getMessage() + "\n");
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
