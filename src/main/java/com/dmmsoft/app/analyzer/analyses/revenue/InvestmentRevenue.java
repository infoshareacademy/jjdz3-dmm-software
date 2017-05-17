package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.Analysis;
import com.dmmsoft.app.analyzer.analyses.IResult;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;

import com.dmmsoft.app.model.Investment;
import com.dmmsoft.app.model.MainContainer;
import com.dmmsoft.app.model.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * Created by milo on 14.04.17.
 */
public class InvestmentRevenue extends Analysis implements IResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvestmentRevenue.class);
    private InvestmentRevenueCriteria inputCriteria;
    private InvestmentRevenueCriteria finalInputCriteria;


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
            throw exception;
        } catch (IllegalArgumentException exception) {
            LOGGER.error("sellDate cannot be before buyDate", exception);
            throw new IllegalArgumentException();
        }
    }

    private Investment getInvestment() throws NoDataForCriteria {
        return mainContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(inputCriteria.getInvestmentName()))
                .findFirst().orElseThrow(NoDataForCriteria::new);
    }

    private List<Quotation> getQuotations(Investment filteredInvestment) throws NoDataForCriteria {
        List<Quotation> quotations = filteredInvestment.getQuotations();

        if (quotations == null || quotations.isEmpty()) {
            throw new NoDataForCriteria("No Quotations for current Investment.");
        }
        return quotations;
    }

    private Quotation getBuyQuotation(List<Quotation> quotations) throws NoDataForCriteria {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(inputCriteria.getBuyDate()))
                .limit(1)
                .findFirst();

        if (!quotation.isPresent()) {
            quotation = suggester.getNearestQuotation(quotations, inputCriteria.getBuyDate());
            quotation.ifPresent(x -> {
                finalInputCriteria.setBuyDate(x.getDate());
            });
        }
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getSellQuotation(List<Quotation> quotations) throws NoDataForCriteria {

        Optional<Quotation> quotation = quotations.stream()
                .filter(x -> x.getDate().equals(inputCriteria.getSellDate()))
                .limit(1)
                .findFirst();

        if (!quotation.isPresent()) {
            quotation = suggester.getNearestQuotation(quotations, inputCriteria.getSellDate());
            quotation.ifPresent(x -> {
                finalInputCriteria.setSellDate(x.getDate());
            });
        }
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private void checkQuotationOrder(Quotation buyQuot, Quotation sellQuot) {

        if (sellQuot.getDate().isBefore(buyQuot.getDate())) {
            throw new IllegalArgumentException("Wrong input data. Quotation BuyDate must be before Quotation SellDate.");
        }
    }

    private InvestmentRevenueResult getInvestmentRevenueResult(Quotation buyQuot, Quotation sellQuot) throws NoDataForCriteria {
        if (buyQuot != null && sellQuot != null) {

            BigDecimal deltaPrice = new BigDecimal(((sellQuot.getClose() - buyQuot.getClose()) / buyQuot.getClose()) * 100);
            BigDecimal deltaPriceRounded = deltaPrice.setScale(4, RoundingMode.HALF_EVEN);

            BigDecimal revenueValue = ((deltaPriceRounded.divide(new BigDecimal(100)))
                    .multiply(inputCriteria.getInvestedCapital()))
                    .setScale(2, RoundingMode.HALF_EVEN);

            doCheckIfInputWasModeratedBySuggester();
            return new InvestmentRevenueResult(revenueValue, deltaPriceRounded, finalInputCriteria);

        } else {
            throw new NoDataForCriteria("Failed to calculate InvestmentRevenue.");
        }
    }

    private void doCheckIfInputWasModeratedBySuggester() {
        if (!this.inputCriteria.equals(this.finalInputCriteria)) {
            this.finalInputCriteria.setModifiedBySuggester(true);
            LOGGER.info("InvestmentInputCriteria was moderated by Suggester."
                    + "InputCriteria: " + inputCriteria.hashCode()
                    + "FinalInputCriteria:" + finalInputCriteria.hashCode());
        }
    }

}
