package com.dmmsoft.app.analyzer.analyses;


import com.dmmsoft.app.analyzer.analyses.exception.NoDataForCriteria;

/**
 * Created by milo on 30.04.17.
 */
public interface IResult {

   AnalysisResult getResult() throws NoDataForCriteria;


}
