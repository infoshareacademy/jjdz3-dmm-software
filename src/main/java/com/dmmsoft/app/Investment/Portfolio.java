package com.dmmsoft.app.Investment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milo4321 on 2017-03-08.
 */
public class Portfolio {

    public List<Investment> investments = new ArrayList<>();

    public List<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }


    @Override
    public String toString() {
        return "Portfolio{" +
                "investments=" + investments +
                '}';
    }
}
