package com.murdock.books.java8guide;

import org.junit.Test;

import java.time.Instant;

/**
 * @author weipeng2k 2018年03月18日 上午10:39:45
 */
public class InstantTest {

    @Test
    public void create() {
        Instant instant = Instant.ofEpochMilli(1000);
        System.out.println(instant);
    }

}
