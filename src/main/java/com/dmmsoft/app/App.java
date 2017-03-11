package com.dmmsoft.app;

import com.dmmsoft.app.AppConfigurationProvider.AppConfigurationProvider;
import com.dmmsoft.app.DataLoader.PorfolioLoader;
import com.dmmsoft.app.Investment.Portfolio;

public class App {
    public static void main(String[] args) throws Exception {

        // Demo
        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        Portfolio p = PorfolioLoader.getPortfolio(appCon);
        p.getInvestments().forEach(System.out::println);

        /**         ...as you have all data loaded and accessible form Portfolio
         *          TODO Analyzer processing (extreme methods and other stuff...)
         */
    }
}
