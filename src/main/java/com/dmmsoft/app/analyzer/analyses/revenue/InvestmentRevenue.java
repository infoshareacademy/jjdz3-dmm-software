package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.AnalysisInput;
import com.dmmsoft.app.analyzer.analyses.IResult;
import com.dmmsoft.app.pojo.Investment;
import com.dmmsoft.app.pojo.MainContainer;
import com.dmmsoft.app.pojo.Quotation;

import java.util.List;

/**
 * Created by milo on 14.04.17.
 */
public class InvestmentRevenue implements IResult {

    private MainContainer mainContainer;
    private InvestmentRevenueInput input;




    public InvestmentRevenue(MainContainer mainContainer, InvestmentRevenueInput input) {
        this.mainContainer = mainContainer;
        this.input = input;
    }

    public InvestmentRevenureResult getResult() {

        Investment filteredInvestments = mainContainer.getInvestments().stream()
                .filter(x -> x.getName().equals(input.getInvestmentName()))
                .findFirst().get();

        List<Quotation> quotations = filteredInvestments.getQuotations();

        Quotation buyQuot = quotations.stream()
                .filter(x -> x.getDate().equals(input.getBuyDate()))
                .findFirst().get();

        Quotation sellQuot = quotations.stream()
                .filter(x -> x.getDate().equals(input.getSellDate()))
                .findFirst().get();

        Double deltaPrice = ((sellQuot.getClose() - buyQuot.getClose()) / buyQuot.getClose())*100;
        Double revenueValue = (deltaPrice/100) * input.getInvestedCapital();

        return new InvestmentRevenureResult(revenueValue, deltaPrice);
    }


}
