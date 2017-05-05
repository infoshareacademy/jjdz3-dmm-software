package com.dmmsoft.app.dataloader;

import com.dmmsoft.app.model.Quotation;
import com.dmmsoft.app.model.QuotationFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 15.04.17.
 */
public abstract class Loader {


    protected List<Quotation> getQuotationsList(String filePath) {

        QuotationFactory quotationData = new QuotationFactory();
        quotationData.loadDataFromFile("" + filePath);

        List<Quotation> quotations = new ArrayList<>();
        for (int i = 0; i < quotationData.getNumberOfQuotations(); i++) {
            quotations.add(quotationData.getQuotation(i));
        }
        this.deltaValueOfClose(quotations);
        return quotations;
    }


    protected void deltaValueOfClose(List<Quotation> quotations) {

        for (Quotation q : quotations) {
            if ((quotations.indexOf(q) > 0 && quotations.indexOf(q) < quotations.size())) {

                double previousValue = quotations.get(quotations.indexOf(q) - 1).getClose();
                double actualValue = q.getClose();

                q.setDeltaClose(((actualValue - previousValue) / previousValue) * 100);
            } else if (quotations.indexOf(q) == 0) {
                q.setDeltaClose(0.0);
            }
        }
    }
}
