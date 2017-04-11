package com.dmmsoft.app.DataLoader;

import com.dmmsoft.app.POJO.Currency;
import com.dmmsoft.app.POJO.Quotation;
import com.dmmsoft.app.POJO.QuotationFactory;

import java.util.ArrayList;

/**
 * Created by Daniel on 06.03.2017.
 */
public class CurrencyLoader{


    private ArrayList<Currency> currencies;
    public ArrayList<Quotation> CurrencyQuotations;


    public ArrayList<Currency> getCurrencies() {
        return currencies;
    }

    public CurrencyLoader()
    {
        currencies = new ArrayList<Currency>();
        CurrencyQuotations = new ArrayList<Quotation>();
    }

    public int getNumberOfCurrencies() {
        return currencies.size();
    }



    public void createCurrenciesFromFile(String filePath) {
        try {
            QuotationFactory quotationData = new QuotationFactory();
            quotationData.loadDataFromFile(""+filePath);

            String name = quotationData.getQuotation(0).getName();
            int id = currencies.size();
            for (int i = 0; i < quotationData.getNumberOfQuotations(); i++) {
                CurrencyQuotations.add(quotationData.getQuotation(i));
            }
            ;
            Currency currency = new Currency(id, name, CurrencyQuotations);
            currencies.add(currency);



        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }



}
