package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.AnalysisCriteria;

import java.time.LocalDate;

/**
 * Created by milo on 17.04.17.
 */
public class InvestmentRevenueCriteria extends AnalysisCriteria {


    private Double investedCapital;
    private LocalDate buyDate;
    private LocalDate sellDate;


    public double getInvestedCapital() {
        return investedCapital;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public InvestmentRevenueCriteria() {
    }

    public InvestmentRevenueCriteria(Double investedCapital, LocalDate buyDate, LocalDate sellDate, String investmentName, Boolean isFavourite) {
        this.investmentName = investmentName;
        this.investedCapital = investedCapital;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.isFavourite = isFavourite;

    }

    public InvestmentRevenueCriteria(InvestmentRevenueCriteria itemToCopy) {
        this.investmentName = itemToCopy.investmentName;
        this.investedCapital = itemToCopy.investedCapital;
        this.buyDate = itemToCopy.buyDate;
        this.sellDate = itemToCopy.sellDate;
        this.isModifiedBySuggester = itemToCopy.isModifiedBySuggester;
        this.isFavourite = itemToCopy.isFavourite;

    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof InvestmentRevenueCriteria))
            return false;

        InvestmentRevenueCriteria criteria = (InvestmentRevenueCriteria) obj;

        return (this.investmentName.equals(criteria.investmentName) &&
                this.investedCapital.equals(criteria.investedCapital) &&
                this.buyDate.equals(criteria.buyDate) &&
                this.sellDate.equals(criteria.sellDate) &&
                this.isFavourite.equals(criteria.isFavourite) &&
                this.isModifiedBySuggester.equals(criteria.isModifiedBySuggester));

    }

}
