package com.dmmsoft.app.analyzer.analyses.stats;

import com.dmmsoft.app.model.Quotation;

/**
 * Created by milo on 14.04.17.
 */
public class ItemStatsResult {


    private String name;
    private Quotation firstQuotation;
    private Quotation lastQuotation;
    private Quotation maxValueQuotation;
    private Quotation minValueQuotation;
    private Quotation maxDeltaPlus;
    private Quotation maxDeltaMinus;
    private Quotation actualValue;

    private boolean isStillQuotable;

    public Quotation getFirstQuotation() {
        return firstQuotation;
    }

    public void setFirstQuotation(Quotation firstQuotation) {
        this.firstQuotation = firstQuotation;
    }

    public Quotation getLastQuotation() {
        return lastQuotation;
    }

    public void setLastQuotation(Quotation lastQuotation) {
        this.lastQuotation = lastQuotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quotation getActualValue() {
        return actualValue;
    }

    public void setActualValue(Quotation actualValue) {
        this.actualValue = actualValue;
    }

    public Quotation getMaxValueQuotation() {
        return maxValueQuotation;
    }

    public void setMaxValueQuotation(Quotation maxValueQuotation) {
        this.maxValueQuotation = maxValueQuotation;
    }

    public Quotation getMaxDeltaMinus() {
        return maxDeltaMinus;
    }

    public void setMaxDeltaMinus(Quotation maxDeltaMinus) {
        this.maxDeltaMinus = maxDeltaMinus;
    }

    public Quotation getMaxDeltaPlus() {
        return maxDeltaPlus;
    }

    public void setMaxDeltaPlus(Quotation maxDeltaPlus) {
        this.maxDeltaPlus = maxDeltaPlus;
    }


    public boolean isStillQuotable() {
        return isStillQuotable;
    }

    public void setStillQuotable(boolean stillQuotable) {
        isStillQuotable = stillQuotable;
    }

    public Quotation getMinValueQuotation() {
        return minValueQuotation;
    }

    public void setMinValueQuotation(Quotation minValueQuotation) {
        this.minValueQuotation = minValueQuotation;
    }


    @Override
    public String toString() {
        return "ItemStatsResult{" +
                "name='" + name + '\'' +
                ", \nfirstQuotation=" + firstQuotation +
                ", \nlastQuotation=" + lastQuotation +
                ", \nmaxValueQuotation=" + maxValueQuotation +
                ", \nminValueQuotation=" + minValueQuotation +
                ", \nmaxDeltaPlus=" + maxDeltaPlus +
                ", \nmaxDeltaMinus=" + maxDeltaMinus +
                ", \nactualValue=" + actualValue +
                ", \nisStillQuotable=" + isStillQuotable +
                '}';
    }
}

