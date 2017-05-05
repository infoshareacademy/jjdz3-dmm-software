package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.appconfiguration.AppConfigurationProvider;
import com.dmmsoft.app.dataloader.MainContainerLoader;
import com.dmmsoft.app.model.Investment;
import com.dmmsoft.app.model.MainContainer;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by milo on 02.05.17.
 */
public class InvestmentRevenueTest  {

    // Test values (just as from .jsp form)
    private final  Double capital = 10000.00;
    private final   DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    private final   LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
    private final   LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);
    private final   String InvestmentName = "CHF";
    // application initialization
    private final    AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
    private final    MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);

    private MainContainer getMainContainerWithLoadeData(){
        // loading data
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();
        return mainContainerLoader.getMainContainer();
    }

    @Test
    public void testGetResultWhenUserInputOutOfRange() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20170402", formatter);

        MainContainer mc = this.getMainContainerWithLoadeData();

        // example analysis usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();

        //analysis input
        System.out.println("\ninputValues(buy date, sell date)");
        System.out.println(input.getBuyDate());
        System.out.println(input.getSellDate());
        System.out.println(input.getModifiedBySuggester());
        System.out.println(input.getFavourite());

        // anlysis results
        System.out.println("\nresultValues(buy date, sell date)");
        InvestmentRevenueCriteria finallyEvaluatedInput =(InvestmentRevenueCriteria)ir.getFinallyEvaluatedInput();

        System.out.println(finallyEvaluatedInput.getBuyDate());
        System.out.println(finallyEvaluatedInput.getSellDate());
        System.out.println(finallyEvaluatedInput.getModifiedBySuggester());
        System.out.println(input.getFavourite());
        System.out.println(ir.getCapitalRevenueValue());
        System.out.println(ir.getCapitalRevenueDeltaPrecentValue());
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenMissingInvestments() throws Exception {

        //  ESSENTIAL creating empty MainContainer (no loaded data)
        MainContainer mc = mainContainerLoader.getMainContainer();

        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);

        // should throw exception
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenMissingQuotations() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);

        MainContainer mc = this.getMainContainerWithLoadeData();

        // ESSENTIAL: removing quotations
        mc.getInvestments().forEach(x -> x.setQuotations(null));

        // example analysis usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultSuggesterFailureByDateOutOfBounds() throws Exception {

        // ESSENTIAL: Suggester (for dates) scans for available dates on left side of timeline
        // target date out of left bound will always couse Suggester fialure
        LocalDate SELL_DATE = LocalDate.parse("19850315", formatter);

        MainContainer mc = this.getMainContainerWithLoadeData();

        // extracting investments
        List<Investment> investments = mc.getInvestments();

        // example analysis usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
    }

}