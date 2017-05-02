package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.dataloader.MainContainerLoader;
import com.dmmsoft.app.pojo.Investment;
import com.dmmsoft.app.pojo.MainContainer;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by milo on 02.05.17.
 */
public class InvestmentRevenueTest extends TestCase {

    // just Test values (e.g. from .jsp form)
    Double capital = 10000.00;
    DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
    LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);
    String InvestmentName = "CHF";
    // application initialization
    AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
    MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);



    public void testGetResultWhenUserInputOutOfRange() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20170402", formatter);

        // loading data
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();
        MainContainer mc = mainContainerLoader.getMainContainer();

        // extracting investments
        List<Investment> investments = mc.getInvestments();

        // example analysis usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();

        //analysis input
        System.out.println("inputValues(buy date, sell date)");
        System.out.println(input.getBuyDate());
        System.out.println(input.getSellDate());
        System.out.println(input.getModifiedBySuggester());
        System.out.println(input.getFavourite());

        // anlysis results
        System.out.println("resultValues(buy date, sell date)");
        InvestmentRevenueCriteria finallyEvaluatedInput =(InvestmentRevenueCriteria)ir.getFinallyEvaluatedInput();

        System.out.println(finallyEvaluatedInput.getBuyDate());
        System.out.println(finallyEvaluatedInput.getSellDate());
        System.out.println(finallyEvaluatedInput.getModifiedBySuggester());
        System.out.println(input.getFavourite());
        System.out.println(ir.getCapitalRevenueValue());
        System.out.println(ir.getCapitalRevenueDeltaPrecentValue());
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenMissingData() {

        // loading data
      //  mainContainerLoader.loadFunds();
      //  mainContainerLoader.loadCurrencies();

        MainContainer mc = mainContainerLoader.getMainContainer();

        // extracting investments
        //List<Investment> investments = mc.getInvestments();

        List<Investment> investments = mc.getInvestments();

        // example analysis usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
/*
        //analysis input
        System.out.println("inputValues(buy date, sell date)");
        System.out.println(input.getBuyDate());
        System.out.println(input.getSellDate());
        System.out.println(input.getModifiedBySuggester());
        System.out.println(input.getFavourite());

        // anlysis results
        System.out.println("resultValues(buy date, sell date)");
        InvestmentRevenueCriteria finallyEvaluatedInput =(InvestmentRevenueCriteria)ir.getFinallyEvaluatedInput();

        System.out.println(finallyEvaluatedInput.getBuyDate());
        System.out.println(finallyEvaluatedInput.getSellDate());
        System.out.println(finallyEvaluatedInput.getModifiedBySuggester());
        System.out.println(input.getFavourite());
        System.out.println(ir.getCapitalRevenueValue());
        System.out.println(ir.getCapitalRevenueDeltaPrecentValue());
*/
    }


}