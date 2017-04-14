package com.dmmsoft.app.Analyzer.W01Stats;

import com.dmmsoft.app.POJO.Fund;
import com.dmmsoft.app.POJO.Investment;
import com.dmmsoft.app.POJO.Quotation;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by milo on 14.04.17.
 */
public class ItemStats {

    public ItemStatsResult getResult(List<Investment> invList, String investmentName){

        Predicate<Investment> investmentPredicate = x -> x.getName()==investmentName;

        Investment invToProcess = invList.stream()
                .filter(investmentPredicate)
                .findFirst().get();

        // max nominal value quotation

        Predicate<Quotation> maxValuePredicate = x -> x.getClose() == invToProcess.getQuotations().stream()
                .mapToDouble(y -> y.getClose())
                .max()
                .getAsDouble();

        Quotation maxValueQuotation =  invToProcess.getQuotations().stream()
                .filter(maxValuePredicate)
                .findFirst()
                .get();

        // min nominal value quotation

        Predicate<Quotation> minValuePredicate = x -> x.getClose() == invToProcess.getQuotations().stream()
                .mapToDouble(y -> y.getClose())
                .min()
                .getAsDouble();

        Quotation minValueQuotation =  invToProcess.getQuotations().stream()
                .filter(minValuePredicate)
                .findFirst()
                .get();

        // TODO actual quotation
        // TODO first quotation

        //  TODO in Quotation POJO extra field: deltaClose (min)
        //  TODO in Quotation POJO extra field  deltaClose (max)

        //  TODO isStillQuotable if ( DateTime.Now - LastQuotationDate) < 7 days

        // result
        ItemStatsResult result = new ItemStatsResult();
                result.setName(maxValueQuotation.getName());
                result.setMaxValueQuotation(maxValueQuotation);
                result.setMinValueQuotation(minValueQuotation);


        return result;
    }


}
