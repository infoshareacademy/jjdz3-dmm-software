package com.dmmsoft.app.analyzer.suggester;

import com.dmmsoft.app.pojo.Quotation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

/**
 * Created by milo on 01.05.17.
 */
public class InputSuggester {


    public Optional<Quotation> getNearestQuotation(List<Quotation> quotations, LocalDate targetDate) {


        List<LocalDate> dateList = new ArrayList<>();
        for (Quotation quotation : quotations) {
            dateList.add(quotation.getDate());
        }

        LocalDate nearestDate = this.getNearestDate(dateList, targetDate);

        return quotations.stream()
                .filter(x -> x.getDate().equals(nearestDate))
                .limit(1)
                .findFirst();

    }

    private LocalDate getNearestDate(List<LocalDate> dates, LocalDate targetDate) {
        return new TreeSet<LocalDate>(dates).lower(targetDate);
    }

}
