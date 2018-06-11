package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年05月30日 下午14:02:22
 */
public class RunAsyncTest {

    public static final int MILLIS = 3000;

    @Test
    public void run_async() {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            Assert.assertTrue(Thread.currentThread().isDaemon());
            try {
                Thread.sleep(MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Assert.assertFalse(completableFuture.isDone());

        try {
            Thread.sleep(MILLIS + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertTrue(completableFuture.isDone());
    }
}
