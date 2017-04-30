package com.dmmsoft.app.dataloader;

import com.dmmsoft.app.pojo.Fund;
import com.dmmsoft.app.pojo.Quotation;


import java.util.ArrayList;
import java.util.List;

public class FundLoader extends Loader {

    private List<Fund> funds = new ArrayList<>();

    public int getNumberOfFunds() {
        return funds.size();
    }

    public List<Fund> getFunds() {
        return funds;
    }


    public void createFundsFromFile(String filePath) {
        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();
            int id = funds.size();

            //....//

            Fund fund = new Fund(id, name, quotationList);
            funds.add(fund);

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}



            /*
            QuotationFactory quotationData = new QuotationFactory();
            quotationData.loadDataFromFile("" + filePath);

            String name = quotationData.getQuotation(0).getName();
            int id = funds.size();

            ArrayList<Quotation> fundQuotations = new ArrayList<>();

            for (int i = 0; i < quotationData.getNumberOfQuotations(); i++) {
                fundQuotations.add(quotationData.getQuotation(i));
            }
            this.deltaValueOfClose(fundQuotations);
            */