package com.dmmsoft.app.Investment;

import java.time.LocalDate;

public class Quotation {

    private String name;
    private LocalDate date;
    private double open;
    private double high;
    private double low;
    private double close;
    private double volume;

    public Quotation(String name, LocalDate date, double open, double high , double low, double close, double volume) {
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

    @Override
    public String toString() {
        return "Quotation [name=" + name + ", date=" + date + ", open="
                + open + ", high=" + high + ", low=" +low+ ", close=" +close+" , volume=" +volume+ "]";
    }
}
