package com.dmmsoft.app.DataLoader;

import com.dmmsoft.app.Investment.Fund;
import com.dmmsoft.app.Investment.Quotation;
import com.dmmsoft.app.Investment.QuotationData;

import java.util.ArrayList;

import static java.lang.System.out;

/**
 * Created by Daniel on 06.03.2017.
 */
public class FundLoader {


    private ArrayList<Fund> funds;
    public ArrayList<Quotation> FundQuotations;


    public FundLoader()
    {
        funds = new ArrayList<Fund>();
        FundQuotations = new ArrayList<Quotation>();
    }

    public int getNumberOfFunds()
    {
        return funds.size();
    }





    public void CreateFundsFromFile (String filePath) {
        try {
            QuotationData quotationData = new QuotationData();
            quotationData.loadDataFromFile(""+filePath);

            String name = quotationData.getQuotation(0).getName();
            int id = funds.size();
            for (int i = 0; i < quotationData.getNumberOfQuotations(); i++) {
                FundQuotations.add(quotationData.getQuotation(i));
            }
            ;
            Fund fund = new Fund(id, name, FundQuotations);
            funds.add(fund);


        } catch (Exception e) {
        System.out.println("Error:" + e.getMessage());
    }
    }



}
