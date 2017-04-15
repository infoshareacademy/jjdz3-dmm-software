package com.dmmsoft.app.Analyzer;

import com.dmmsoft.app.AppConfiguration.AppConfigurationProvider;
import com.dmmsoft.app.DataLoader.PortfolioLoader;
import com.dmmsoft.app.FileIO.Path.FilePath;
import com.dmmsoft.app.POJO.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by milo on 15.04.17.
 */
public class demo {

    public static void main(String[] args) {

        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        PortfolioLoader portfolioLoader = new PortfolioLoader(appCon);

        portfolioLoader.loadFunds();
        portfolioLoader.loadCurrencies();

        MainDataContainer mdc = portfolioLoader.getMainDataContainer();
        List<Investment> investments = mdc.getInvestments();

        List<Quotation> filteredQuotations = new ArrayList<>();

        // to test choose investment id:

        Investment f = (Investment) investments.get(5);
        List<Quotation> quots = f.getQuotations();
        Quotation q = quots.get(0);

        for (FilePath item : appCon.getFundFilePaths()) {
            System.out.println(item.getFilePath());
        }

        for (FilePath item : appCon.getCurrencyFilePaths()) {
            System.out.println(item.getFilePath());
        }

        System.out.println("1. number of Investments loaded: " + investments.size());
        System.out.println("2. name of first Investment: " + f.getName());
        System.out.println("3. number of Quotations of first Investment: " + quots.size());
        System.out.println("4. name of Investment extracted form Quotation: " + q.getName());

        investments.forEach((Investment investment) -> {
            List<Quotation> quotationsPerInvestment = investment.getQuotations().stream()
                    .filter(x -> x.getName().equals("AUD"))
                    .collect(Collectors.toList());
            filteredQuotations.addAll(quotationsPerInvestment);
            Collections.sort(filteredQuotations);
        });

        System.out.println(filteredQuotations);

    }

}
