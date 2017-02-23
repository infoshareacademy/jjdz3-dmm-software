package com.dmmsoft.app.Investment;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by Daniel on 23.02.2017.
 */
public class FundData {

    private ArrayList<Fund> funds;
    public FundData(){
        funds = new ArrayList<Fund>();
    }
    /* method with returns numbers of Funds */
    public int getNumberOfFunds(){
        return funds.size();
    }
    /*method with returns a single Fund */
    public Fund getFundNumber(int i){
        return funds.get(i);
    }
    /*method with presenting returing random variable of object Fund */
    public double getFundNumberVolume(int i){
        return funds.get(i).high;
    }
    public void loadDataFromFile(String filepath){
        try
        {
            Scanner scanner = new Scanner(new FileReader(filepath));
            String line;
            Fund fund;
            scanner.nextLine();
            while(scanner.hasNextLine())
            {
                String data[] = scanner.nextLine().split(",");
                String name = data[0];
                String date = data[1];
                double open = Double.parseDouble(data[2]);
                double high = Double.parseDouble(data[3]);
                double low = Double.parseDouble(data[4]);
                double close = Double.parseDouble(data[5]);
                double volume = Double.parseDouble(data[6]);
                fund = new Fund(name, date, open, high, low, close, volume);
                funds.add(fund);
            }
            scanner.close();
        } catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
