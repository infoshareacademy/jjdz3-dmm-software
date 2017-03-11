package com.dmmsoft.app.Investment;

import java.util.Date;

/**
 * Created by Milo4321 on 2017-02-09.
 */
public class Quotation {

    String name;
    String date;
    double open;
    double high;
    double low;
    double close;
    double volume;

    public Quotation(String name, String date, double open, double high ,double low, double close, double volume)
    {
        this.name = name;
        this.date = date;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    @Override
    public String toString() {
        return "Quotation [name=" + name + ", date=" + date + ", open="
                + open + ", high=" + high + ", low=" +low+ ", close=" +close+" , volume=" +volume+ "]";
    }
}
