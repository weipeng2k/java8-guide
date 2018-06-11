package com.murdock.books.java8guide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

/**
 * @author weipeng2k 2018年01月14日 下午22:11:05
 */
public class IOUtils {

    public static void processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("test.txt")))) {
            processor.process(br);
        }

        AStatic.m();
    }

    public static void processFile1(Consumer<BufferedReader> processor) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("test.txt")))) {
            processor.accept(br);
        }
    }
}
