package com.dmmsoft.app.analyzer.analyses.indicator;

import com.dmmsoft.app.analyzer.analyses.Analysis;
import com.dmmsoft.app.analyzer.analyses.AnalysisResult;
import com.dmmsoft.app.analyzer.analyses.IResult;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenue;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.app.model.Investment;
import com.dmmsoft.app.model.MainContainer;
import com.dmmsoft.app.model.Quotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;


/**
 * Created by milo on 14.04.17.
 */
public class Indicator extends Analysis implements IResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(Indicator.class);
    private IndicatorCriteria indicatorCriteria;



    public Indicator(MainContainer mainContainer, IndicatorCriteria indicatorCriteria) {
        this.mainContainer = mainContainer;
        this.indicatorCriteria = indicatorCriteria;
    }

    @Override
    public IndicatorResult getResult() throws NoDataForCriteria {
        try{
            Investment filteredInvestment = this.getInvestment();
            List<Quotation> quotations = this.getQuotations(filteredInvestment);

            IndicatorResult result = new IndicatorResult();
            result.setName(this.getMaxValue(quotations).getName());
            result.setMaxValueQuotation(this.getMaxValue(quotations));
            result.setMinValueQuotation(this.getMinValue(quotations));
            result.setMaxDeltaPlus(this.getMaxDeltaPlusValue(quotations));
            result.setMaxDeltaMinus(this.getMaxDeltaMinusValue(quotations));
            result.setFirstQuotation(this.getFirst(quotations));
            result.setLastQuotation(this.getLast(quotations));
            return result;

        }catch (NoDataForCriteria exception){
            LOGGER.error("Failed to calculate Investment indicators.");
            throw exception;
        }


    }
    private Investment getInvestment() throws  NoDataForCriteria{
        //Predicate<Investment> investmentPredicate = x -> x.getName().equals(indicatorCriteria.getName());

        return mainContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(indicatorCriteria.getName()))
                .findFirst().orElseThrow(NoDataForCriteria::new);
    }

    private List<Quotation> getQuotations(Investment filteredInvestment) throws NoDataForCriteria {
        return Optional.ofNullable(filteredInvestment.getQuotations())
                .filter(l -> !l.isEmpty())
                .orElseThrow(() -> new NoDataForCriteria("No Quotations for current Investment."));
    }

    private Quotation getMaxValue(List<Quotation> quotations) throws  NoDataForCriteria{

        Predicate<Quotation> maxValuePredicate = x -> x.getClose() == quotations.stream()
                .mapToDouble(Quotation::getClose)
                .reduce(Double::max)
                .getAsDouble();

        Optional<Quotation> quotation = quotations.stream()
                .filter(maxValuePredicate)
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getMinValue(List<Quotation> quotations) throws  NoDataForCriteria{

        Predicate<Quotation> minValuePredicate = x -> x.getClose() == quotations.stream()
                .mapToDouble(Quotation::getClose)
                .reduce(Double::min)
                .getAsDouble();

        Optional<Quotation> quotation = quotations.stream()
                .filter(minValuePredicate)
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getMaxDeltaPlusValue(List<Quotation> quotations) throws  NoDataForCriteria{

        Predicate<Quotation> maxDeltaPlusValuePredicate = x -> x.getDeltaClose() == quotations.stream()
                .mapToDouble(Quotation::getDeltaClose)
                .reduce(Double::max)
                .getAsDouble();

        Optional<Quotation> quotation = quotations.stream()
                .filter(maxDeltaPlusValuePredicate)
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getMaxDeltaMinusValue(List<Quotation> quotations) throws  NoDataForCriteria{

        Predicate<Quotation> maxDeltaMinusValuePredicate = x -> x.getDeltaClose() == quotations.stream()
                .mapToDouble(Quotation::getDeltaClose)
                .reduce(Double::min)
                .getAsDouble();

        Optional<Quotation> quotation = quotations.stream()
                .filter(maxDeltaMinusValuePredicate)
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getFirst(List<Quotation> quotations) throws NoDataForCriteria{
        Optional<Quotation> quotation = quotations.stream()
                .findFirst();
        return quotation.orElseThrow(NoDataForCriteria::new);
    }

    private Quotation getLast(List<Quotation> quotations) throws NoDataForCriteria{
        return quotations.stream()
                .sorted()
                .reduce((first, second)-> first)
                .orElseThrow(NoDataForCriteria::new);
    }


  /*
   public IndicatorResult getResult(List<Investment> invList, IndicatorCriteria indicatorCriteria) throws NoDataForCriteria {

        Predicate<Investment> investmentPredicate = x -> x.getName().equals(indicatorCriteria.getName());

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


        IndicatorResult result = new IndicatorResult();
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

    */




}
