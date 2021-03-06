package com.dmmsoft.app.model;

import junit.framework.TestCase;

import java.io.File;

/**
 * Created by milo on 15.04.17.
 */
public class QuotationFactoryTest extends TestCase {

    File resourcesDirectory = new File("src/test/resources/csv/currencies/EUR.txt");
    private final int NUMBEROFROWS = 4622;
    QuotationFactory quotationFactory = new QuotationFactory();


    public void testGetNumberOfQuotations() throws Exception {


        String filePath = resourcesDirectory.getAbsolutePath();

        System.out.println("filepath: " + filePath);

        quotationFactory.loadDataFromFile(filePath);

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