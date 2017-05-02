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

    private InvestmentRevenueCriteria inputCriteria;
    private InvestmentRevenueCriteria finalInputCriteria;

    public InvestmentRevenue(MainContainer mainContainer, InvestmentRevenueCriteria inputCriteria) {
        this.mainContainer = mainContainer;
        this.inputCriteria = inputCriteria;
        finalInputCriteria = new InvestmentRevenueCriteria(inputCriteria);
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
        Double revenueValue = (deltaPrice / 100) * inputCriteria.getInvestedCapital();

        doCheckFinalInput();

        return new InvestmentRevenueResult(revenueValue, deltaPrice, finalInputCriteria);
    }

    private Investment getFilteredInvestment() {
        return mainContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(inputCriteria.getInvestmentName()))
                .findFirst().get();
    }

    private Optional<Quotation> getSellQuotation(List<Quotation> quotations) {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(inputCriteria.getSellDate()))
                .limit(1)
                .findFirst();

        if (!quotation.isPresent()) {
            quotation = suggester.getNearestQuotation(quotations, inputCriteria.getSellDate());
            finalInputCriteria.setSellDate(quotation.get().getDate());
        }
        return quotation;
    }

    private Optional<Quotation> getBuyQuotation(List<Quotation> quotations) {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(inputCriteria.getBuyDate()))
                .limit(1)
                .findFirst();

        if (!quotation.isPresent()) {
            quotation = suggester.getNearestQuotation(quotations, inputCriteria.getBuyDate());
            finalInputCriteria.setBuyDate(quotation.get().getDate());
        }
        return quotation;
    }

    private void doCheckFinalInput(){
        if (!this.inputCriteria.equals(this.finalInputCriteria)){
            this.finalInputCriteria.setModifiedBySuggester(true);
        }
    }


}
