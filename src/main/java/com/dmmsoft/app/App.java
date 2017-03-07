package com.dmmsoft.app;


import com.dmmsoft.app.AppConfigurationProvider.AppConfigurationProvider;
import com.dmmsoft.app.FileIO.FilePath;
import com.dmmsoft.app.FileIO.FileReader;
import com.dmmsoft.app.Investment.Fund;
import com.dmmsoft.app.Investment.Quotation;
import com.dmmsoft.app.Investment.QuotationData;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class App {
    public static void main(String[] args) throws Exception {


        ArrayList<Quotation> list = new ArrayList<Quotation>();


        QuotationData quotationData = new QuotationData();
        quotationData.loadDataFromFile("/Users/Daniel/Desktop/AGI003.txt");
        String name = quotationData.getQuotation(0).getName();

        for(int i=0; i<quotationData.getNumberOfQuotations(); i++)
        {
            list.add(quotationData.getQuotation(i));

        };

//        Fund fund = new Fund(1, name, list);
//
//         test
//         System.out.print(""+ fund.getQuotations());
//          System.out.print(""+ fund.getName());


        // quick demo of AppConfigurationProvider usage (to remove)
        AppConfigurationProvider appCon = new AppConfigurationProvider().getConfiguration();
        // quick test of AppConfiguration (to remove)

        out.println("\n*** Paths from AppConfiguration object:");
        for (FilePath fp : appCon.getFundFilePaths()) {
            out.println(fp.getFilePath());
        }
        for (FilePath fp : appCon.getCurrencyFilePaths()) {
            out.println(fp.getFilePath());
        }
    }

}
