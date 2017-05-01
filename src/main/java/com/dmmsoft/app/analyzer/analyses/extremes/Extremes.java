package com.dmmsoft.app.analyzer.analyses.extremes;

import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.pojo.Investment;
import com.dmmsoft.app.pojo.Quotation;

import java.util.*;

public class Extremes {

    public enum TYPE {
        LOCAL, GLOBAL
    }

    protected List<Investment> investments;

    public Extremes(List<Investment> investments) {
        this.investments = investments;
    }

    public ExtremesResult getExtremes() throws NoDataForCriteria {
        List<Quotation> quotations = getQuotations();
        if (quotations.isEmpty()) {
            throw new NoDataForCriteria();
        }
        Optional<Quotation> lowest = getLowest(quotations);
        Optional<Quotation> highest = getHighest(quotations);
        if (!lowest.isPresent() || !highest.isPresent()) {
            throw new RuntimeException();
        }
        return new ExtremesResult(lowest.get(), highest.get());
    }

    private Optional<Quotation> getHighest(List<Quotation> quotations) {
        return quotations.stream()
            .sorted(Comparator.comparingDouble(Quotation::getHigh).reversed())
            .limit(1)
            .findFirst();
    }

    private Optional<Quotation> getLowest(List<Quotation> quotations) {
        return quotations.stream()
            .sorted(Comparator.comparingDouble(Quotation::getLow))
            .limit(1)
            .findFirst();
    }

    protected List<Quotation> getQuotations() {
        List<Quotation> quotations = new ArrayList<>();
        investments.forEach((Investment investment) -> quotations.addAll(investment.getQuotations()));
        return quotations;
    }
}
