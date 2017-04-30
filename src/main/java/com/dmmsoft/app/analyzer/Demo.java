package com.dmmsoft.app.analyzer;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueQuery;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenureResult;
import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.dataloader.MainContainerLoader;
import com.dmmsoft.app.pojo.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by milo on 15.04.17.
 */
public class Demo {

    public static void main(String[] args) {

        // just test values
        Double capital = 10000.00;
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
        LocalDate SELL_DATE = LocalDate.parse("20120530", formatter);
        String InvestmentName = "CHF";

        // application initialization
        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);

        // loading data
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();
        MainContainer mc = mainContainerLoader.getMainContainer();

        // extracting investments
        List<Investment> investments = mc.getInvestments();

        // example analysis usage
        InvestmentRevenueQuery query = new InvestmentRevenueQuery(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenureResult ir = new InvestmentRevenue(mc).getEstimatedRevenue(query);

        System.out.println(ir.getCapitalRevenueValue());
        System.out.println(ir.getCapitalRevenueDeltaPrecentValue());

    }

    // System.out.println("1. number of Investments loaded: " + investments.size());
    // ItemStatsResult s = new ItemStats().getResult(investments, new ItemStatsQuery("AIP001"));

    /*
        for (FilePath item : appCon.getFundFilePaths()) {
        System.out.println(item.getFilePath());
    }
        for (FilePath item : appCon.getCurrencyFilePaths()) {
        System.out.println(item.getFilePath());
    }*/



}
