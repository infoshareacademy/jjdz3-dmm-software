package com.dmmsoft.app.Analyzer;

import com.dmmsoft.app.Analyzer.Analysis.Revenue.InvestmentRevenue;
import com.dmmsoft.app.Analyzer.Analysis.Revenue.InvestmentRevenueQuery;
import com.dmmsoft.app.Analyzer.Analysis.Revenue.InvestmentRevenureResult;
import com.dmmsoft.app.Analyzer.Analysis.Stats.ItemStats;
import com.dmmsoft.app.Analyzer.Analysis.Stats.ItemStatsQuery;
import com.dmmsoft.app.Analyzer.Analysis.Stats.ItemStatsResult;
import com.dmmsoft.app.AppConfiguration.AppConfigurationProvider;
import com.dmmsoft.app.DataLoader.MainContainerLoader;
import com.dmmsoft.app.FileIO.Path.FilePath;
import com.dmmsoft.app.POJO.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by milo on 15.04.17.
 */
public class demo {

    public static void main(String[] args) {

        // test values
        Double capital = 10000.00;
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
        LocalDate SELL_DATE = LocalDate.parse("20120530", formatter);
        String InvestmentName = "CHF";

        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        MainContainerLoader mainContainerLoader = new MainContainerLoader(appCon);

        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();


        for (FilePath item : appCon.getFundFilePaths()) {
            System.out.println(item.getFilePath());
        }
        for (FilePath item : appCon.getCurrencyFilePaths()) {
            System.out.println(item.getFilePath());
        }

        MainContainer mc = mainContainerLoader.getMainContainer();

        List<Investment> investments = mc.getInvestments();

        System.out.println("1. number of Investments loaded: " + investments.size());

        // ItemStatsResult s = new ItemStats().getResult(investments, new ItemStatsQuery("AIP001"));

        InvestmentRevenueQuery q = new InvestmentRevenueQuery(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenureResult ir = new InvestmentRevenue(mc).getEstimatedRevenue(q);

        System.out.println(ir.getCapitalRevenueValue());
        System.out.println(ir.getCapitalRevenueDeltaPrecentValue());

    }

}
