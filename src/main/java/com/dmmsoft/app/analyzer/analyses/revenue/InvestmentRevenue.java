package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.Analysis;
import com.dmmsoft.app.analyzer.analyses.IResult;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.pojo.Investment;
import com.dmmsoft.app.pojo.MainContainer;
import com.dmmsoft.app.pojo.Quotation;


import java.util.List;
import java.util.Optional;

/**
 * Created by milo on 14.04.17.
 */
public class InvestmentRevenue extends Analysis implements IResult {

    private InvestmentRevenueInput input;
    private InvestmentRevenueInput finalInput;

    public InvestmentRevenue(MainContainer mainContainer, InvestmentRevenueInput input) {
        this.mainContainer = mainContainer;     // what if super.mainContainer
        this.input = input;
        finalInput = new InvestmentRevenueInput(input);
    }

    public InvestmentRevenueResult getResult() throws NoDataForCriteria {

        Investment filteredInvestments = getFilteredInvestment();
        if (filteredInvestments == null) {
            throw new NoDataForCriteria();
        }

        List<Quotation> quotations = filteredInvestments.getQuotations();
        if (quotations.isEmpty()) {
            throw new NoDataForCriteria();
        }

        Optional<Quotation> buyQuot = getBuyQuotation(quotations);
        Optional<Quotation> sellQuot = getSellQuotation(quotations);
        if (!buyQuot.isPresent() || !sellQuot.isPresent()) {
            throw new NoDataForCriteria();
        }

        Double deltaPrice = ((sellQuot.get().getClose() - buyQuot.get().getClose()) / buyQuot.get().getClose()) * 100;
        Double revenueValue = (deltaPrice / 100) * input.getInvestedCapital();

        return new InvestmentRevenueResult(revenueValue, deltaPrice, finalInput);
    }

    private Investment getFilteredInvestment() {
        return mainContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(input.getInvestmentName()))
                .findFirst().get();
    }

    private Optional<Quotation> getSellQuotation(List<Quotation> quotations) {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(input.getSellDate()))
                .limit(1)
                .findFirst();

        if (!quotation.isPresent()) {
            quotation = suggester.getNearestQuotation(quotations, input.getSellDate());

            //TODO message processing that imput vales were invalid and were changed to nrearest vlalid
            finalInput.setSellDate(quotation.get().getDate());
        }
        return quotation;
    }

    private Optional<Quotation> getBuyQuotation(List<Quotation> quotations) {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(input.getBuyDate()))
                .limit(1)
                .findFirst();

        if (!quotation.isPresent()) {
            quotation = suggester.getNearestQuotation(quotations, input.getBuyDate());

            //TODO message processing that imput vales were invalid and were changed to nrearest vlalid
            finalInput.setBuyDate(quotation.get().getDate());
        }
        return quotation;
    }


}
