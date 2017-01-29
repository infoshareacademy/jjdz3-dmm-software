package com.dmmsoft;
import junit.framework.TestCase;
import java.nio.charset.Charset;


/**
 * Created by Milo on 2017-01-29.
 */
public class FileReaderTest extends TestCase {
    public void testReadTxtFile() throws Exception {


        String path = getClass().getResource("/test.json").getFile();
        System.out.println(path);

        FileReader fr = new FileReader();
        String test = fr.readTxtFile(path, Charset.forName("UTF-8"));
        System.out.println(test);

    }

    public void testReadTxtFileByRow() throws Exception {

    }

}