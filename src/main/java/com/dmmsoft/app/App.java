package com.dmmsoft.app;

import com.dmmsoft.app.DataLoader.PorfolioLoader;
import com.dmmsoft.app.Investment.Portfolio;

public class App {
    public static void main(String[] args) throws Exception {

        // Demo
        Portfolio p = PorfolioLoader.getPortfolio();
        p.getInvestments().forEach(System.out::println);

        /**         ...as you have all data loaded and accessible form Portfolio
         *          TODO Analyzer processing (extreme methods and other stuff...)
         */
    }
}
