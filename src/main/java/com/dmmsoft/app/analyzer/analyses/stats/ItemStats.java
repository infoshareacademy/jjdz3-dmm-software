package com.dmmsoft.app.analyzer.analyses.stats;

import com.dmmsoft.app.model.Investment;
import com.dmmsoft.app.model.Quotation;

import java.util.List;
import java.util.function.Predicate;


/**
 * Created by milo on 14.04.17.
 */
public class ItemStats {

    private final int DAYS_SINCE_LAST_QUOTATION = 7;

    public ItemStatsResult getResult(List<Investment> invList, ItemStatsCriteria query) {


        Predicate<Investment> investmentPredicate = x -> x.getName().equals(query.getName());

        Investment invToProcess = invList.stream()
                .filter(investmentPredicate)
                .findFirst().get();

        List<Quotation> quotations = invToProcess.getQuotations();

        // max nominal value quotation

        Predicate<Quotation> maxValuePredicate = x -> x.getClose() == quotations.stream()
                .mapToDouble(y -> y.getClose())
                .max()
                .getAsDouble();

        Quotation maxValueQuotation = quotations.stream()
                .filter(maxValuePredicate)
                .findFirst()
                .get();

        // min nominal value quotation

        Predicate<Quotation> minValuePredicate = x -> x.getClose() == quotations.stream()
                .mapToDouble(y -> y.getClose())
                .min()
                .getAsDouble();

        Quotation minValueQuotation = invToProcess.getQuotations().stream()
                .filter(minValuePredicate)
                .findFirst()
                .get();

        Predicate<Quotation> maxDeltaPlusValuePredicate = x -> x.getDeltaClose() == quotations.stream()
                .mapToDouble(y -> y.getDeltaClose())
                .max()
                .getAsDouble();

        Quotation maxDeltaPlusValue = invToProcess.getQuotations().stream()
                .filter(maxDeltaPlusValuePredicate)
                .findFirst()
                .get();

        Predicate<Quotation> maxDeltaMinusValuePredicate = x -> x.getDeltaClose() == quotations.stream()
                .mapToDouble(y -> y.getDeltaClose())
                .min()
                .getAsDouble();

        Quotation maxDeltaMinusValue = invToProcess.getQuotations().stream()
                .filter(maxDeltaMinusValuePredicate)
                .findFirst()
                .get();


        Quotation firstlQuotation = quotations.stream().findFirst().get();

        Quotation lastlQuotation = quotations.get(quotations.size() - 1);

        boolean isStillQuotable = true;
       //  if (ChronoUnit.DAYS.between((Temporal) DateTime.now().toDate(), lastlQuotation.getDate()) > DAYS_SINCE_LAST_QUOTATION) {
       //     isStillQuotable = false;
      //  }


        ItemStatsResult result = new ItemStatsResult();
        result.setName(maxValueQuotation.getName());
        result.setMaxValueQuotation(maxValueQuotation);
        result.setMinValueQuotation(minValueQuotation);
        result.setMaxDeltaPlus(maxDeltaPlusValue);
        result.setMaxDeltaMinus(maxDeltaMinusValue);
        result.setFirstQuotation(firstlQuotation);
        result.setLastQuotation(lastlQuotation);
        result.setStillQuotable(isStillQuotable);

        return result;
    }


}
