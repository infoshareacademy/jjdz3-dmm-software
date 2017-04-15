package com.dmmsoft.app.POJO;

import junit.framework.TestCase;

import java.util.IntSummaryStatistics;

/**
 * Created by milo on 15.04.17.
 */
public class QuotationFactoryTest extends TestCase {

    private final String TESTFILEPATH ="/home/milo/bossa/funds/AGI001.txt";
    private final int NUMBEROFROWS = 405;

    QuotationFactory quotationFactory = new QuotationFactory();


    public void testGetNumberOfQuotations() throws Exception {
        quotationFactory.loadDataFromFile(TESTFILEPATH);

        System.out.println("ilość notowań: " +quotationFactory.getNumberOfQuotations());
        if (quotationFactory.getNumberOfQuotations()!=NUMBEROFROWS){
            throw new IllegalArgumentException();
        }


    }



    public void testGetQuotation() throws Exception {

    }

    public void testLoadDataFromFile() throws Exception {

    }

}