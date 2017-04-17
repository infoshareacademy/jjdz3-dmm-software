package com.dmmsoft.app.Analyzer.Analysis.Revenue;

import com.dmmsoft.app.POJO.MainContainer;

import java.time.LocalDate;

/**
 * Created by milo on 17.04.17.
 */
public class InvestmentRevenueQuery {

    private MainContainer mc;
    private Double investedCapital;
    private LocalDate buyDate;
    private LocalDate sellDate;
    private String investmentName;

    public double getInvestedCapital() {
        return investedCapital;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public InvestmentRevenueQuery(Double investedCapital, LocalDate buyDate, LocalDate sellDate, String investmentName) {
        this.investedCapital = investedCapital;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.investmentName = investmentName;
    }
}
