package com.dmmsoft.app.analyzer.suggester;

import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.model.Quotation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by milo on 01.05.17.
 */
public class InputCriteriaSuggester {
    private List<LocalDate> dateList = new ArrayList<>();

    public Optional<Quotation> getNearestQuotation(List<Quotation> quotations, LocalDate targetDate) {

        if (quotations != null && !quotations.isEmpty()) {
            for (Quotation quotation : quotations) {
                dateList.add(quotation.getDate());
            }
        }
        LocalDate nearestDate = this.getNearestPreviousDate(dateList, targetDate);

        return  quotations.stream()
                .filter(x -> x.getDate().equals(nearestDate))
                .limit(1)
                .findFirst();
    }

    private LocalDate getNearestPreviousDate(List<LocalDate> dates, LocalDate targetDate) {
       TreeSet<LocalDate> set = new TreeSet<>(dates);
        if(set.lower(targetDate)!=null) {
            return set.lower(targetDate);
        }
        else {
            throw new NoDataForCriteria();
        }
    }

}
