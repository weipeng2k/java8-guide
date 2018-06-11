package com.murdock.books.java8guide;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author weipeng2k 2018年01月14日 下午22:13:09
 */
public class IOUtilsTest {

    @Test
    public void processFile() throws Exception {
        IOUtils.processFile((processor) -> System.out.println(processor.readLine()));
    }

    @Test
    public void processFile1() throws Exception {
        IOUtils.processFile((processor) -> {
            String line = null;
            while((line = processor.readLine()) != null) {
                System.out.println(line);
            }
        });
    }

    @Test
    public void processFile2() throws Exception {
        IOUtils.processFile1((processor) -> {
            String line = null;
            try {
                while ((line = processor.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}