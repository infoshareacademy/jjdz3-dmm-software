package com.dmmsoft;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!");

        // przykład użycia klasy FileReader

        FileReader fr = new FileReader();
        String test = null;
        try {
            test = fr.readTxtFile("resources/test.json", Charset.forName("UTF-8") );
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(test);

    }





}
