package com.dmmsoft.app.analyzer;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueInput;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueResult;
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

        // just Test values (e.g. from .jsp form)
        Double capital = 10000.00;
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
        LocalDate SELL_DATE = LocalDate.parse("20170402", formatter);
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
        InvestmentRevenueInput input = new InvestmentRevenueInput(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();

        System.out.println("inputValues(buy date, sell date)");
        System.out.println(input.getBuyDate());
        System.out.println(input.getSellDate());

        System.out.println("resultValues(buy date, sell date)");
        InvestmentRevenueInput finallyEvaluatedInput =(InvestmentRevenueInput)ir.getFinallyEvaluatedInput();

        System.out.println(finallyEvaluatedInput.getBuyDate());
        System.out.println(finallyEvaluatedInput.getSellDate());


        System.out.println(ir.getCapitalRevenueValue());
        System.out.println(ir.getCapitalRevenueDeltaPrecentValue());




        System.out.println("oryginal mc hashcode"+mc.hashCode());
        System.out.println("oryginal mc fundcount "+mc.getFundsCount());

        Test t = new Test(mc);
        t.getHash();

        Boolean b = mc.equals(t.getHash());

        System.out.println("czy są rowne " +b);

        System.out.println("oryginal mc hashcode after try of change fundcount:"+mc.getFundsCount());



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
