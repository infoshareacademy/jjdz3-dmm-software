package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.AnalysisResult;

/**
 * Created by milo on 15.04.17.
 */
public class InvestmentRevenureResult extends AnalysisResult {

  private  double capitalRevenueValue;
  private  double capitalRevenueDeltaPrecentValue;

    public double getCapitalRevenueValue() {
        return capitalRevenueValue;
    }

    public double getCapitalRevenueDeltaPrecentValue() {
        return capitalRevenueDeltaPrecentValue;
    }

    public InvestmentRevenureResult(double capitalRevenueValue, double capitalRevenueDeltaPrecentValue) {
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPrecentValue = capitalRevenueDeltaPrecentValue;
    }
}
