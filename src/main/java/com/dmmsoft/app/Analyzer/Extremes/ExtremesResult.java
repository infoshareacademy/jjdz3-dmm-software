package com.dmmsoft.app.Analyzer.Extremes;

import com.dmmsoft.app.POJO.Quotation;

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
