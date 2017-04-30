package com.dmmsoft.app.dataloader;

import com.dmmsoft.app.pojo.Currency;
import com.dmmsoft.app.pojo.Quotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 06.03.2017.
 */
public class CurrencyLoader extends Loader {

    private List<Currency> currencies = new ArrayList<>();

    public int getNumberOfCurrencies() {
        return currencies.size();
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void createCurrenciesFromFile(String filePath) {
        try {
            List<Quotation> quotationList = this.getQuotationsList(filePath);
            String name = quotationList.get(0).getName();
            int id = currencies.size();

            Currency currency = new Currency(id, name, quotationList);
            currencies.add(currency);

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }


}
