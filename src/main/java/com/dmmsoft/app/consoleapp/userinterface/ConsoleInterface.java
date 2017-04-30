package com.dmmsoft.app.consoleapp.userinterface;

import java.io.PrintStream;
import java.util.Scanner;

class ConsoleInterface {

    Scanner scanner;
    PrintStream output;
    PrintStream errorOutput;

    ConsoleInterface() {
        scanner = new Scanner(System.in);
        output = System.out;
        errorOutput = System.err;
    }
}
