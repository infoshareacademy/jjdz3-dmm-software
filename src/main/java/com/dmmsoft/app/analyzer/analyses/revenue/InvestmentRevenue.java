package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.pojo.Investment;
import com.dmmsoft.app.pojo.MainContainer;
import com.dmmsoft.app.pojo.Quotation;

import java.util.List;

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
