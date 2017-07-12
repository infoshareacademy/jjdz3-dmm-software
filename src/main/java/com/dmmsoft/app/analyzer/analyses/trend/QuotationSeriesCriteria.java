package com.dmmsoft.app.analyzer.analyses.trend;

import com.dmmsoft.app.analyzer.analyses.AnalysisCriteria;
import java.time.LocalDate;

/**
 * Created by milo on 12.07.17.
 */

public class QuotationSeriesCriteria extends AnalysisCriteria {

    private String investmentName;
    private LocalDate startDate;
    private LocalDate endDate;

    public QuotationSeriesCriteria() {
    }

    public QuotationSeriesCriteria(String investmentName, LocalDate startDate, LocalDate endDate) {
        this.investmentName = investmentName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getInvestmentName() {
        return investmentName;
    }

    @Override
    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
