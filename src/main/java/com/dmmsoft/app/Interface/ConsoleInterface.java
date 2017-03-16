package com.dmmsoft.app.Interface;

import java.io.Console;
import java.io.PrintStream;

class ConsoleInterface {

    Console console;
    PrintStream output;
    PrintStream errorOutput;

    ConsoleInterface() {
        console = System.console();
        output = System.out;
        errorOutput = System.err;
    }
}
