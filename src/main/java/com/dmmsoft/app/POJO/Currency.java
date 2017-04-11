package com.dmmsoft.app.POJO;

import java.util.List;

/**
 * Created by Milo4321 on 2017-02-09.
 */
public class Currency extends Investment {

    int id;
    String name;
    List<Quotation> quotations;

    public Currency(int id, String name, List<Quotation> quotations) {
        super(id, name, quotations);
        this.id = id;
        this.name = name;
        this.quotations = quotations;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Quotation> getQuotations() {
        return quotations;
    }

    @Override
    public void setQuotations(List<Quotation> quotations) {
        this.quotations = quotations;
    }

    @Override
    public String toString() {
        return "Currency {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quotations=" + quotations +
                '}';
    }



}

