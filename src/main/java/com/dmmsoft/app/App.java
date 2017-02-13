package com.dmmsoft.app;


public class App {
    public static void main(String[] args) throws Exception {

        System.out.println("Hello World!");

        // quick test of AppConfiguration (to remove)

        AppConfiguration con = new AppConfiguration("ConfigurationJSON.json");

        System.out.println("\n*** Paths from AppConfiguration object:");
        System.out.println("\nfundFilePath: " + con.getFundFilePath());
        System.out.print("\ncurrencyFilePath: " + con.getCurrencyFilePath());

    }

}
