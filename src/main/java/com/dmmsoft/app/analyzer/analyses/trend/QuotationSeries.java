package com.dmmsoft.app.analyzer.analyses.trend;

import com.dmmsoft.app.analyzer.analyses.Analysis;
import com.dmmsoft.app.analyzer.analyses.AnalysisResult;
import com.dmmsoft.app.analyzer.analyses.IResult;
import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;

/**
 * Created by milo on 14.04.17.
 */
public class QuotationSeries extends Analysis implements IResult {

    @Override
    public AnalysisResult getResult() throws NoDataForCriteria {

        // TODO: data series provider for charts
        return null;
    }
}
