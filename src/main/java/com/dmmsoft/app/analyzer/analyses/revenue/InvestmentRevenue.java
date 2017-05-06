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

        try {
            Investment filteredInvestment = getInvestment();

            List<Quotation> quotations = getQuotations(filteredInvestment);

            Quotation buyQuot = getBuyQuotation(quotations);
            Quotation sellQuot = getSellQuotation(quotations);
            checkQuotationOrder(buyQuot, sellQuot);

            return getInvestmentRevenueResult(buyQuot, sellQuot);

        } catch (NoDataForCriteria exception) {
            LOGGER.error("InvestmentRevelnue failure.", exception);
            throw new NoDataForCriteria();
        }
        catch (IllegalArgumentException exception){
            LOGGER.error("sellDate cannot be before buyDate", exception);
            throw new IllegalArgumentException();
        }
    }

    private Investment getInvestment() {
        Optional<Investment> filteredInvestment = mainContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(inputCriteria.getInvestmentName()))
                .findFirst();

        if (!filteredInvestment.isPresent()) {
            throw new NoDataForCriteria();
        }
        return filteredInvestment.get();
    }

    private List<Quotation> getQuotations(Investment filteredInvestment) {
        List<Quotation> quotations = filteredInvestment.getQuotations();
        if (quotations == null || quotations.isEmpty()) {
            throw new NoDataForCriteria();
        }
        return quotations;
    }

    private Quotation getBuyQuotation(List<Quotation> quotations) {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(inputCriteria.getBuyDate()))
                .limit(1)
                .findFirst();

        if (!quotation.isPresent()) {
            quotation = suggester.getNearestQuotation(quotations, inputCriteria.getBuyDate());
            if (quotation.isPresent())
                finalInputCriteria.setBuyDate(quotation.get().getDate());
        }
        return quotation.get();
    }

    private Quotation getSellQuotation(List<Quotation> quotations) {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(inputCriteria.getSellDate()))
                .limit(1)
                .findFirst();

        if (!quotation.isPresent()) {
            quotation = suggester.getNearestQuotation(quotations, inputCriteria.getSellDate());
            if (quotation.isPresent())
                finalInputCriteria.setSellDate(quotation.get().getDate());
        }
        return quotation.get();
    }

    private void checkQuotationOrder(Quotation buyQuot,Quotation sellQuot){

            if (sellQuot.getDate().isBefore(buyQuot.getDate())){
                throw  new IllegalArgumentException();
            }
    }

    private InvestmentRevenueResult getInvestmentRevenueResult(Quotation buyQuot, Quotation sellQuot) {
        if (buyQuot!=null && sellQuot!=null) {
            Double deltaPrice = ((sellQuot.getClose() - buyQuot.getClose()) / buyQuot.getClose()) * 100;
            Double revenueValue = (deltaPrice / 100) * inputCriteria.getInvestedCapital();

            doCheckIfInputWasModeratedBySuggester();
            return new InvestmentRevenueResult(revenueValue, deltaPrice, finalInputCriteria);

        } else {
            throw new NoDataForCriteria();
        }
    }

    private void doCheckIfInputWasModeratedBySuggester() {
        if (!this.inputCriteria.equals(this.finalInputCriteria)) {
            this.finalInputCriteria.setModifiedBySuggester(true);
        }
    }

}
