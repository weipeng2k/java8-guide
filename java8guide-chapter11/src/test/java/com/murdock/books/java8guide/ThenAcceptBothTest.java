package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年06月04日 上午11:02:41
 */
public class ThenAcceptBothTest {

    @Test
    public void then_accept_both() {
        CompletableFuture<String> c1 = CompletableFutureUtils.make("upper", 1000, "DONE");
        CompletableFuture<String> c2 = CompletableFutureUtils.make("lower", 2000, "done");

        CompletableFuture<Void> c3 = c1.thenAcceptBoth(c2, (v1, v2) -> {
            System.out.println("function thread " + Thread.currentThread());

            System.out.println(v1 + v2);
        });

        c3.join();
    }
}
