package com.murdock.books.java8guide;

import org.junit.Test;

import java.time.LocalTime;

/**
 * @author weipeng2k 2018年03月18日 上午10:20:53
 */
public class LocalTimeTest {

    @Test
    public void create() {
        LocalTime localTime = LocalTime.of(5, 1, 2);
        System.out.println(localTime);
    }
}
