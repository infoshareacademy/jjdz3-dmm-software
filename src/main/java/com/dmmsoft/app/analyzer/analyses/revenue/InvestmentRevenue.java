package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.Analysis;
import com.dmmsoft.app.analyzer.analyses.IResult;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;

import com.dmmsoft.app.model.Investment;
import com.dmmsoft.app.model.MainContainer;
import com.dmmsoft.app.model.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Optional;

/**
 * Created by milo on 14.04.17.
 */
public class InvestmentRevenue extends Analysis implements IResult {

    private InvestmentRevenueCriteria inputCriteria;
    private InvestmentRevenueCriteria finalInputCriteria;
    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentRevenue.class);

    public InvestmentRevenue(MainContainer mainContainer, InvestmentRevenueCriteria inputCriteria) {
        this.mainContainer = mainContainer;
        this.inputCriteria = inputCriteria;
        finalInputCriteria = new InvestmentRevenueCriteria(inputCriteria);
    }

    public InvestmentRevenueResult getResult() throws NoDataForCriteria {

        Optional<Investment> filteredInvestment = getFilteredInvestment();
        if (!filteredInvestment.isPresent()) {
            LOGGER.error("No investment for current criteria");
            throw new NoDataForCriteria();
        }

        List<Quotation> quotations = filteredInvestment.get().getQuotations();
        if (quotations == null || quotations.isEmpty()) {
            LOGGER.error("No quotations for current criteria");
            throw new NoDataForCriteria();
        }

        Optional<Quotation> buyQuot = getBuyQuotation(quotations);
        Optional<Quotation> sellQuot = getSellQuotation(quotations);

        //TODO check for proper Quotation dates order (should be: buyQuot.date < sellQuot.date)

        if (buyQuot.isPresent() && sellQuot.isPresent()) {
            Double deltaPrice = ((sellQuot.get().getClose() - buyQuot.get().getClose()) / buyQuot.get().getClose()) * 100;
            Double revenueValue = (deltaPrice / 100) * inputCriteria.getInvestedCapital();

            doCheckFinalInput();
            return new InvestmentRevenueResult(revenueValue, deltaPrice, finalInputCriteria);

        } else {
            LOGGER.error("No quotations for current criteria");
            throw new NoDataForCriteria();
        }
    }

    private Optional<Investment> getFilteredInvestment() {

        return mainContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(inputCriteria.getInvestmentName()))
                .findFirst();
    }

    private Optional<Quotation> getSellQuotation(List<Quotation> quotations) {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(inputCriteria.getSellDate()))
                .limit(1)
                .findFirst();

        if (!quotation.isPresent()) {
            quotation = suggester.getNearestQuotation(quotations, inputCriteria.getSellDate());
            if (quotation.isPresent())
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
            if (quotation.isPresent())
                finalInputCriteria.setBuyDate(quotation.get().getDate());
        }
        return quotation;
    }

    private void doCheckFinalInput() {
        if (!this.inputCriteria.equals(this.finalInputCriteria)) {
            this.finalInputCriteria.setModifiedBySuggester(true);
        }
    }

}
