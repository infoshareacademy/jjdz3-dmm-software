package com.dmmsoft;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Milo on 2017-01-29.
 */

public class FileReader {
    // zwraca całą zawartość pliku jako String

    public String readTxtFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    // zwraca wiersze pliku tekstowego w List<String>

    public List<String> readTxtFileRowByRow(String path, Charset encoding) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path), encoding);
        for (String line : lines) {
            System.out.println(line);
        }
        return lines;

    }

}
