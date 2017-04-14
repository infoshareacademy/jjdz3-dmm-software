package com.dmmsoft.app.Analyzer.W01Stats;

import com.dmmsoft.app.POJO.Quotation;
import org.joda.time.DateTime;

/**
 * Created by milo on 14.04.17.
 */
public class ItemStatsResult {


    private String name;
    private Quotation actualValue;
    private Quotation maxValueQuotation;
    private Quotation minValueQuotation;
    private Quotation maxDeltaMinus;
    private Quotation maxDeltaPlus;
    private DateTime firstQuotationDate;
    private boolean isStillQuotable;

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

    public DateTime getFirstQuotationDate() {
        return firstQuotationDate;
    }

    public void setFirstQuotationDate(DateTime firstQuotationDate) {
        this.firstQuotationDate = firstQuotationDate;
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
}
