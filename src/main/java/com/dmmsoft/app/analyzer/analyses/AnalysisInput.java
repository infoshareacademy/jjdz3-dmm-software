package com.dmmsoft.app.analyzer.analyses;

/**
 * Created by milo on 30.04.17.
 */
public class AnalysisInput {

    protected String investmentName;
    protected Boolean isFavourite;

    public Boolean getFavourite() {
        return isFavourite;
    }
    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }
}
