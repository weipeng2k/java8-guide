package com.murdock.books.java8guide;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author weipeng2k 2018年01月14日 下午20:22:53
 */
public class DefaultTest {

    @Test
    public void test() {
        Default de = new Default() {
        };

        System.out.println(de.s());
    }

}