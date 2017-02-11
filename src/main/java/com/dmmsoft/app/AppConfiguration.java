package com.dmmsoft.app;

/**
 * Created by Milo4321 on 2017-02-09.
 */
public class AppConfiguration {

    String fundFilePath;
    String currencyFilePath;

    public String getFundFilePath() {
        return fundFilePath;
    }

    public void setFundFilePath(String fundFilePath) {
        this.fundFilePath = fundFilePath;
    }

    public String getCurrencyFilePath() {
        return currencyFilePath;
    }

    public void setCurrencyFilePath(String currencyFilePath) {
        this.currencyFilePath = currencyFilePath;
    }

    public AppConfiguration(String fundFilePath, String currencyFilePath) {
        this.fundFilePath = fundFilePath;
        this.currencyFilePath = currencyFilePath;
    }

}
