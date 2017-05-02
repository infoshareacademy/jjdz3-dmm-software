package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.AnalysisInput;
import com.dmmsoft.app.pojo.MainContainer;

import java.time.LocalDate;

/**
 * Created by milo on 17.04.17.
 */
public class InvestmentRevenueInput extends AnalysisInput {


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

    public InvestmentRevenueInput() {
    }

    public InvestmentRevenueInput(Double investedCapital, LocalDate buyDate, LocalDate sellDate, String investmentName) {
        this.investedCapital = investedCapital;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.investmentName = investmentName;
    }

    public InvestmentRevenueInput(InvestmentRevenueInput copy) {
        this.investedCapital = copy.investedCapital;
        this.buyDate = copy.buyDate;
        this.sellDate = copy.sellDate;
        this.investmentName = copy.investmentName;
    }

}
