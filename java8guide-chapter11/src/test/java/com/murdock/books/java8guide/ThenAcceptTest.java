package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年05月31日 下午17:10:22
 */
public class ThenAcceptTest {

    @Test
    public void test() {
        StringBuilder sb = new StringBuilder();
        CompletableFuture.completedFuture("message").thenApply(String::toUpperCase).thenAccept(sb::append);

        System.out.println(sb);
    }
}
