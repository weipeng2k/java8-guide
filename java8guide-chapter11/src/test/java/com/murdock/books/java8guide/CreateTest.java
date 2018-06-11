package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * 演示创建一个CompletableFuture
 *
 * @author weipeng2k 2018年06月06日 下午21:54:10
 */
public class CreateTest {

    @Test
    public void async_create() {
        CompletableFuture<Void> done = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("done");
        });

        done.join();
    }

    @Test(expected = CompletionException.class)
    public void async_create_ex() {
        CompletableFuture<Void> done = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException();
        });

        while (!done.isDone()) {

        }

        Assert.assertTrue(done.isCompletedExceptionally());
        done.join();
    }

    @Test
    public void async_apply() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "done";
        });

        Assert.assertEquals("done", completableFuture.join());
    }


}
