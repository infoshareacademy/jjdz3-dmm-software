package com.dmmsoft.app.dataloader;

import com.dmmsoft.app.model.Quotation;
import com.dmmsoft.app.model.QuotationFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        for (Quotation quotation : quotations) {
            if ((quotations.indexOf(quotation) > 0 && quotations.indexOf(quotation) < quotations.size())) {

                BigDecimal previousValue = quotations.get(quotations.indexOf(quotation) - 1).getClose()
                        .setScale(2, RoundingMode.HALF_EVEN);
                BigDecimal actualValue = quotation.getClose()
                        .setScale(2, RoundingMode.HALF_EVEN);

                BigDecimal deltaClose;

                deltaClose = ((actualValue.subtract(previousValue)))
                        .divide(previousValue, 2, RoundingMode.HALF_EVEN)
                        .multiply(new BigDecimal(100.0))
                        .setScale(2, RoundingMode.HALF_EVEN);

                quotation.setDeltaClose(deltaClose);
            } else if (quotations.indexOf(quotation) == 0) {
                quotation.setDeltaClose(new BigDecimal(0.0));
            }
        }
    }
}
