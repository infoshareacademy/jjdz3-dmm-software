package com.dmmsoft.app.Investment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milo4321 on 2017-02-09.
 */
public abstract class Investment {

    public int Id;
    public String name;
    public List<Quotation> quotations = new ArrayList<Quotation>();

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quotation> getQuotations() {
        return quotations;
    }

    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    public Investment(int id, String name, List<Quotation> quotations) {
        Id = id;
        this.name = name;
        this.quotations = quotations;


        /*for (Quotation quotation : this.quotations = quotations) {
            quotation.getId();
            quotation.getDate();
            quotation.getOpen();
            quotation.getHigh();
            quotation.getLow();
            quotation.getClose();
            quotation.getVolume();
        }
        ;*/

    }
}
