package com.dmmsoft.app.DataLoader;

import com.dmmsoft.app.Investment.Fund;
import com.dmmsoft.app.Investment.Quotation;
import com.dmmsoft.app.Investment.QuotationFactory;

import java.util.ArrayList;

public class FundLoader {

    private ArrayList<Fund> funds = new ArrayList<>();
    private ArrayList<Quotation> fundQuotations = new ArrayList<>();

    public int getNumberOfFunds() {
        return funds.size();
    }

    public ArrayList<Fund> getFunds() {
        return funds;
    }  // added by Milo


    public void createFundsFromFile(String filePath) {
        try {
            QuotationFactory quotationData = new QuotationFactory();
            quotationData.loadDataFromFile(""+filePath);

            String name = quotationData.getQuotation(0).getName();
            int id = funds.size();
            for (int i = 0; i < quotationData.getNumberOfQuotations(); i++) {
                fundQuotations.add(quotationData.getQuotation(i));
            }

            Fund fund = new Fund(id, name, fundQuotations);
            funds.add(fund);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
