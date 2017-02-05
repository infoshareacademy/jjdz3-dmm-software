package com.dmmsoft.UserInterface;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Milo4321  on 2017-02-03.
 */
public class AnswerMapper {

    // TODO move this to config file

    public static int convertInputCharToId(String s) {

        MenuCommandScanner.ScanForMenuCommand(s);  // scans for 'q' or 'w' user input

        Map<String, Integer> charTointeger = new HashMap<String, Integer>();
        charTointeger.put("a", 0);
        charTointeger.put("b", 1);
        charTointeger.put("c", 2);
        charTointeger.put("d", 3);
        charTointeger.put("e", 4);
        charTointeger.put("f", 5);
        charTointeger.put("g", 6);
        charTointeger.put("h", 7);
        charTointeger.put("i", 8);
        return charTointeger.get(s);
    }

    public static String convertIdToChar(int i) {

        Map<Integer, String> charTointeger = new HashMap<Integer, String>();
        charTointeger.put(0, "a) ");
        charTointeger.put(1, "b) ");
        charTointeger.put(2, "c) ");
        charTointeger.put(3, "d) ");
        charTointeger.put(4, "e) ");
        charTointeger.put(5, "f) ");
        charTointeger.put(6, "g) ");
        charTointeger.put(7, "h) ");
        charTointeger.put(8, "i) ");

        return charTointeger.get(i);

    }


}
