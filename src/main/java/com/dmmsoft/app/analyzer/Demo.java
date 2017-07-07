package com.dmmsoft.app.analyzer;

import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.indicator.Indicator;
import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorCriteria;
import com.dmmsoft.app.analyzer.analyses.indicator.IndicatorResult;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueResult;
import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.file.RemoteDownloader;
import com.dmmsoft.app.model.loader.MainContainerLoader;
import com.dmmsoft.app.model.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by milo on 15.04.17.
 */
public class Demo {

    public static void main(String[] args) throws NoDataForCriteria, IOException {

        // just Test values (e.g. from .jsp form)
        BigDecimal capital = new BigDecimal(1512000.25);
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
        LocalDate SELL_DATE = LocalDate.parse("20170402", formatter);
        String InvestmentName = "CHF";

        // application initialization
        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        System.out.println(appCon.getCurrencyBackupFolderPath().getFolderPath());
        System.out.println(appCon.getFundBackupFolderPath().getFolderPath());
        System.out.println(appCon.getCurrencyUrl().getFileUrl());
        System.out.println(appCon.getFundUrl().getFileUrl());
        System.out.println(appCon.getSmtpConfigFilePath());

        MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);

        // loading data
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();
        MainContainer mc = mainContainerLoader.getMainContainer();

        new RemoteDownloader().getModelFilesFromRemoteLocation();

        // extracting investments
        List<Investment> investments = mc.getInvestments();

        // example analysis usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();

        IndicatorCriteria criteria = new IndicatorCriteria("CHF");

        IndicatorResult result = new Indicator(mc, criteria).getResult();
        System.out.println(result.getName());
        System.out.println(result.getMinValueQuotation().getClose());
        System.out.println(result.getFirstQuotation().getClose());
        System.out.println(result.getLastQuotation().getClose());
        System.out.println(result.getMaxDeltaMinus().getDeltaClose());
        System.out.println(result.getMaxDeltaPlus().getDeltaClose());
        System.out.println(result.getMaxValueQuotation().getClose());
        System.out.println(result.getMinValueQuotation().getClose());

        System.out.println("inputValues(buy date, sell date)");
        System.out.println(input.getBuyDate());
        System.out.println(input.getSellDate());
        System.out.println(input.getModifiedBySuggester());
        System.out.println(input.getFavourite());

        System.out.println("resultValues(buy date, sell date)");
        InvestmentRevenueCriteria finallyEvaluatedInput =(InvestmentRevenueCriteria)ir.getFinallyEvaluatedInput();

        System.out.println(finallyEvaluatedInput.getBuyDate());
        System.out.println(finallyEvaluatedInput.getSellDate());
        System.out.println(finallyEvaluatedInput.getModifiedBySuggester());
        System.out.println(input.getFavourite());

        System.out.println(ir.getCapitalRevenueValue());
        System.out.println(ir.getCapitalRevenueDeltaPrecentValue());
    }
}
