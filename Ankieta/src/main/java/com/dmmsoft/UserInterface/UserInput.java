package com.dmmsoft.UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Milo4321 on 2017-02-01.
 */
public class UserInput {

    public String ReadUserInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = null;
        try {
            s = br.readLine();
            // TODO validation here...
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

}



