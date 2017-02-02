package com.dmmsoft;
import junit.framework.TestCase;

import java.io.IOException;
import java.nio.charset.Charset;


/**
 * Created by Milo on 2017-01-29.
 */
public class FileReaderTest extends TestCase {

    public void testReadResourceFileResourceExistsShouldReturnString() throws Exception {
        FileReader fr = new FileReader("test.json");
        String test = fr.getFileAsString();
        assertNotNull(test);
    }

    public void testReadResourceFileResourceDoesntExistShouldThrowException() throws Exception {
        try {
            FileReader fr = new FileReader("abc");
            String test = fr.getFileAsString();
            fail();
        } catch (NullPointerException exception) {
        }
    }
}