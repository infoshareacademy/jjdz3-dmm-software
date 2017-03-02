package com.dmmsoft.app.Investment;

import java.io.FileReader;
import java.util.*;

/**
 * Created by Daniel on 01.03.2017.
 */
public class QuotationData {

    private ArrayList<Quotation> quotations;


    public QuotationData()
    {
        quotations = new ArrayList<Quotation>();
    }

    public int getNumberOfQuotations()
    {
        return quotations.size();
    }
    public Quotation getQuotation(int i)
    {
        return quotations.get(i);
    }





    public void loadDataFromFile(String filepath) {
        try {
            Scanner scanner = new Scanner(new FileReader(filepath));
            String line;
            Quotation quotation;
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String data[] = scanner.nextLine().split(",");
                String name = data[0];
                String date = data[1];
                double open = Double.parseDouble(data[2]);
                double high = Double.parseDouble(data[3]);
                double low = Double.parseDouble(data[4]);
                double close = Double.parseDouble(data[5]);
                double volume = Double.parseDouble(data[6]);
                quotation = new Quotation(name, date, open, high, low, close, volume);
                quotations.add(quotation);
            }
            scanner.close();
            System.out.println("Loaded");


        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

    }




}

