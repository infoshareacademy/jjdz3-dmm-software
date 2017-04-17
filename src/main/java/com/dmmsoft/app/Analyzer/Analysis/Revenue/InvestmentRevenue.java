package com.dmmsoft.app.Analyzer.Analysis.Revenue;

import com.dmmsoft.app.POJO.Investment;
import com.dmmsoft.app.POJO.MainContainer;
import com.dmmsoft.app.POJO.Quotation;
import org.joda.time.DateTime;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;

/**
 * Created by milo on 14.04.17.
 */
public class InvestmentRevenue {

    private MainContainer mc;

    public InvestmentRevenue(MainContainer mc) {
        this.mc = mc;
    }

    public InvestmentRevenureResult getEstimatedRevenue(InvestmentRevenueQuery query) {

        Investment filteredInvestments = mc.getInvestments().stream()
                .filter(x -> x.getName().equals(query.getInvestmentName()))
                .findFirst().get();

        List<Quotation> quotations = filteredInvestments.getQuotations();

        Quotation buyQuot = quotations.stream()
                .filter(x -> x.getDate().equals(query.getBuyDate()))
                .findFirst().get();

        Quotation sellQuot = quotations.stream()
                .filter(x -> x.getDate().equals(query.getSellDate()))
                .findFirst().get();

        Double deltaPrice = ((sellQuot.getClose() - buyQuot.getClose()) / buyQuot.getClose())*100;
        Double revenueValue = (deltaPrice/100) * query.getInvestedCapital();

        return new InvestmentRevenureResult(revenueValue, deltaPrice);
    }


}
