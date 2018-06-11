package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年06月04日 下午13:57:21
 */
public class ThenComposeTest {

    @Test
    public void then_compose() {
        CompletableFuture<Integer> one = CompletableFutureUtils.make("one", 1000, 1);

        CompletableFuture<Integer> two = one.thenCompose(value -> {
            System.out.println("func thread " + Thread.currentThread());
            return CompletableFutureUtils.make("two", 2000, value + 1);
        }).thenCompose(value -> {
            System.out.println("func thread " + Thread.currentThread());
            return CompletableFutureUtils.make("three", 3000, value + 1);
        });

        two.join();

    }
}
