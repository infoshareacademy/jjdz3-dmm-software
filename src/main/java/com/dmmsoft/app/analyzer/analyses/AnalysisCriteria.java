package com.dmmsoft.app.analyzer.analyses;

/**
 * Created by milo on 30.04.17.
 */
public class AnalysisCriteria {

    protected String investmentName;
    protected Boolean isFavourite = false;
    protected Boolean isModifiedBySuggester = false;


    public Boolean getModifiedBySuggester() {
        return isModifiedBySuggester;
    }

    public void setModifiedBySuggester(Boolean modifiedBySuggester) {
        isModifiedBySuggester = modifiedBySuggester;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public String getInvestmentName() {
        return investmentName;
    }








}
