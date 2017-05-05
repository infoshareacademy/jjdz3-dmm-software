package com.dmmsoft.app.analyzer.analyses.extremes;

import com.dmmsoft.app.model.Quotation;

public class ExtremesResult {

    private Quotation lowest;
    private Quotation highest;

    public ExtremesResult(Quotation lowest, Quotation highest) {
        this.lowest = lowest;
        this.highest = highest;
    }

    public Quotation getLowest() {
        return lowest;
    }

    public Quotation getHighest() {
        return highest;
    }
}
