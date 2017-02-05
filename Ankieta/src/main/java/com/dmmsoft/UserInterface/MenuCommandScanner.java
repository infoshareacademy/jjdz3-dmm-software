package com.dmmsoft.UserInterface;
import static com.dmmsoft.App.main;
/**
 * Created by Milo4321 on 2017-02-03.
 */
public class MenuCommandScanner {


    public static void ScanForMenuCommand(String s) {
        if (s.equals("q")){System.out.println("Zamykam program!");System.exit(0);}

        String[] args = null;
        if (s.equals("w")) restart(args);
    }
    private static void restart(String[] ArgsString) {
        System.out.println("");
        System.out.println("Ankieta została zrestartowana!");
        main(ArgsString);
    }
}