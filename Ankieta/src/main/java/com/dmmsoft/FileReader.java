package com.dmmsoft;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by Milo on 2017-01-29.
 */

public class FileReader {

    private String resourceFilePath;

    public FileReader(String resourceFilePath) {
        this.resourceFilePath = resourceFilePath;
    }

    public String getFileAsString() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.resourceFilePath);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
