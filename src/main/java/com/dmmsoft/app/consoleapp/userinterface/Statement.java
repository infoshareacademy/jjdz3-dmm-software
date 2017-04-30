package com.dmmsoft.app.consoleapp.userinterface;

import com.dmmsoft.app.analyzer.analyses.extremes.Extremes;
import com.dmmsoft.app.analyzer.analyses.extremes.ExtremesResult;
import com.dmmsoft.app.pojo.MainContainer;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

public class Statement extends ConsoleInterface {

    private static final String SPLASH_MESSAGE = "DMM Software Financial analyzer v1.0";
    private static final String EXTREMES_RESULT = "min: %s (%s w dniu %s), max: %s (%s w dniu %s)";
    private static final String LOADED_INVESTMENTS_COUNT = "Załadowanych plików z danymi funduszy: %s, walut: %s";
    private static final String LOCAL_EXTREMES_RESULT = "Ekstrema lokalne: " + EXTREMES_RESULT;
    private static final String GLOBAL_EXTREMES_RESULT = "Global lokalne: " + EXTREMES_RESULT;

    private static final NumberFormat numberFormatter = new DecimalFormat("#0.00");
    private static final String PRINT_DATE_FORMAT = "yyyy-MM-dd";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(PRINT_DATE_FORMAT);

    public Statement() {
        super();
    }

    public void printSplashMessage() {
        output.println(SPLASH_MESSAGE + "\n");
    }

    public void printInvestmentCount(MainContainer mainContainer) {
        output.printf(
            LOADED_INVESTMENTS_COUNT + "\n\n",
            mainContainer.getFundsCount(),
            mainContainer.getCurrenciesCount()
        );
    }

    public void printExtremesResult(ExtremesResult extremesResult, Extremes.TYPE type) {
        String resultMessage = "";
        switch (type) {
            case LOCAL:
                resultMessage = LOCAL_EXTREMES_RESULT;
                break;
            case GLOBAL:
                resultMessage = GLOBAL_EXTREMES_RESULT;
                break;
        }
        output.printf(
            resultMessage + "\n",
            numberFormatter.format(extremesResult.getLowest().getLow()),
            extremesResult.getLowest().getName(),
            extremesResult.getLowest().getDate().format(dateFormatter),
            numberFormatter.format(extremesResult.getHighest().getHigh()),
            extremesResult.getHighest().getName(),
            extremesResult.getHighest().getDate().format(dateFormatter)
        );
    }
}
