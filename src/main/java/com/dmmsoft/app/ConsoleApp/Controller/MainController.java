package com.dmmsoft.app.ConsoleApp.Controller;

import com.dmmsoft.app.Analyzer.Analysis.S1Extremes.Exception.NoDataForCriteria;
import com.dmmsoft.app.Analyzer.Analysis.S1Extremes.Extremes;
import com.dmmsoft.app.Analyzer.Analysis.S1Extremes.LocalExtremes;
import com.dmmsoft.app.Analyzer.Analysis.S1Extremes.ExtremesResult;
import com.dmmsoft.app.AppConfiguration.AppConfigurationProvider;
import com.dmmsoft.app.ConsoleApp.Controller.Exception.IllegalFlow;
import com.dmmsoft.app.ConsoleApp.UserInterface.Menu.*;
import com.dmmsoft.app.DataLoader.MainContainerLoader;
import com.dmmsoft.app.POJO.MainContainer;
import com.dmmsoft.app.ConsoleApp.UserInterface.Prompt;
import com.dmmsoft.app.ConsoleApp.UserInterface.Statement;
import com.dmmsoft.app.ConsoleApp.ValueObject.DateRange;

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