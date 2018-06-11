package com.murdock.books.java8guide;

/**
 * @author weipeng2k 2018年01月14日 下午20:22:00
 */
public interface Default {

    default String s() {
        return "S";
    }

    default int i(String s) {
        return s.length();
    }
}
