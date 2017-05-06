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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalysisCriteria that = (AnalysisCriteria) o;

        if (!investmentName.equals(that.investmentName)) return false;
        if (!isFavourite.equals(that.isFavourite)) return false;
        return isModifiedBySuggester.equals(that.isModifiedBySuggester);

    }

    @Override
    public int hashCode() {
        int result = investmentName.hashCode();
        result = 31 * result + isFavourite.hashCode();
        result = 31 * result + isModifiedBySuggester.hashCode();
        return result;
    }
}
