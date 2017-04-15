package com.dmmsoft.app.POJO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class QuotationFactory {

    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private List<Quotation> quotations = new ArrayList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(QuotationFactory.class);

    public int getNumberOfQuotations() {
        return quotations.size();
    }

    public Quotation getQuotation(int i) {
        return quotations.get(i);
    }

    public void loadDataFromFile(String filepath) {
        try {
            Scanner scanner = new Scanner(new FileReader(filepath));
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String data[] = scanner.nextLine().split(",");
                String name = data[0];
                LocalDate date = LocalDate.parse(data[1], formatter);
                double open = Double.parseDouble(data[2]);
                double high = Double.parseDouble(data[3]);
                double low = Double.parseDouble(data[4]);
                double close = Double.parseDouble(data[5]);
                double volume = Double.parseDouble(data[6]);

                Quotation quotation = new Quotation(name, date, open, high, low, close, volume);
                quotations.add(quotation);

            }

        scanner.close();

        } catch (Exception e) {
            LOGGER.info("Error parsing csv files " + e.getMessage());
        }
        finally {

        }
    }
}

