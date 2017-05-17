package com.dmmsoft.app.analyzer.analyses.revenue;

import com.dmmsoft.app.analyzer.analyses.AnalysisCriteria;
import com.dmmsoft.app.analyzer.analyses.AnalysisResult;

import java.math.BigDecimal;

/**
 * Created by milo on 15.04.17.
 */
public class InvestmentRevenueResult extends AnalysisResult {

  private BigDecimal capitalRevenueValue;
  private BigDecimal capitalRevenueDeltaPrecentValue;

    public BigDecimal getCapitalRevenueValue() {
        return capitalRevenueValue;
    }

    public BigDecimal getCapitalRevenueDeltaPrecentValue() {
        return capitalRevenueDeltaPrecentValue;
    }

    public InvestmentRevenueResult(BigDecimal capitalRevenueValue, BigDecimal capitalRevenueDeltaPrecentValue, AnalysisCriteria evaluatedInput) {
        this.capitalRevenueValue = capitalRevenueValue;
        this.capitalRevenueDeltaPrecentValue = capitalRevenueDeltaPrecentValue;
        super.finallyEvaluatedInput = evaluatedInput;
    }
}
