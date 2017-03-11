package com.dmmsoft.app.FileIO;

import com.sun.istack.internal.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class FileReader {

    public String resourceFilePath;

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

    public void readFile() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        String filePath = classLoader.getResource(resourceFilePath).getFile();
        File file = new File(filePath);

        List<String> lines = Files.readAllLines(file.toPath());

        for (String line : lines){
            System.out.println(line);
        }

    }
}
