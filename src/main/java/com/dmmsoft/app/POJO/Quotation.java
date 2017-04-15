package com.dmmsoft.app.POJO;

import java.time.LocalDate;

public class Quotation implements Comparable<Quotation> {

    private String name;
    private LocalDate date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;


    private double deltaClose;

    public Quotation(String name, LocalDate date, double open, double high , double low, double close, double volume) {
        this.name = name;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        // this.deltaClose =deltaClose;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }


    public double getDeltaClose() {
        return deltaClose;
    }

    public void setDeltaClose(double deltaClose) {
        this.deltaClose = deltaClose;
    }





    @Override
    public String toString() {
        return "Quotation [name=" + name + ", date=" + date +
              //  ", open=" + open +
              //  ", high=" + high +
              //  ", low=" +low+
                ", " + "close=" +close+
               // " , volume=" +volume+
                ", deltaClose="+deltaClose+"%] \n";

    }


    @Override
    public int compareTo(Quotation o) {
        if (getDate() == null || o.getDate() == null)
            return 0;
        return getDate().compareTo(o.getDate());
    }

}
