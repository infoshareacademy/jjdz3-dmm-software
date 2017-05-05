package com.dmmsoft.app.analyzer.analyses.extremes;

import com.dmmsoft.app.model.Investment;
import com.dmmsoft.app.model.Quotation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class LocalExtremes extends Extremes {

    private LocalDate startDate;
    private LocalDate stopDate;

    public LocalExtremes(LocalDate startDate, LocalDate stopDate, List<Investment> investments) {
        super(investments);
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    private boolean isDateWithinRange(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
            (date.isEqual(stopDate) || date.isBefore(stopDate));
    }

    protected List<Quotation> getQuotations() {
        List<Quotation> filteredQuotations = new ArrayList<>();
        investments.forEach((Investment investment) -> {
            List<Quotation>quotationsPerInvestment = investment.getQuotations().stream()
                .filter((Quotation quotation) -> isDateWithinRange(quotation.getDate()))
                .collect(Collectors.toList());
            filteredQuotations.addAll(quotationsPerInvestment);
        });
        return filteredQuotations;
    }
}
