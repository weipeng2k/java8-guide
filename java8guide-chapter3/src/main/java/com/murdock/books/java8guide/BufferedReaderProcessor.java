package com.murdock.books.java8guide;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author weipeng2k 2018年01月14日 下午22:08:58
 */
@FunctionalInterface
public interface BufferedReaderProcessor {
    /**
     * 输入处理
     *
     * @param bufferedReader reader
     */
    void process(BufferedReader bufferedReader) throws IOException;

    static void m() {
    }
}
