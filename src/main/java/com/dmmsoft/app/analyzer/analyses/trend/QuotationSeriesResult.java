package com.dmmsoft.app.analyzer.analyses.trend;

import com.dmmsoft.app.analyzer.analyses.AnalysisResult;
import com.dmmsoft.app.model.Quotation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 12.07.17.
 */
public class QuotationSeriesResult extends AnalysisResult {

    List<Quotation> quotationList = new ArrayList<>();

    public QuotationSeriesResult(List<Quotation> quotationList) {
        this.quotationList = quotationList;
    }

    public List<Quotation> getQuotationList() {
        return quotationList;
    }
}
