package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author weipeng2k 2018年06月03日 下午15:57:02
 */
public class ApplyToEitherTest {

    @Test
    public void apply_to_either() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            System.out.println("prepare to set value");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("set value");
            completableFuture.complete("Done");
        });

        CompletableFuture<String> thenApplyAsync = completableFuture.thenApplyAsync((value) -> {
            System.out.println("begin upper");
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("upper thread " + Thread.currentThread());
            return value.toUpperCase();
        });

        CompletableFuture<String> thenApplyAsync1 = completableFuture.thenApplyAsync((value) -> {
            System.out.println("begin lower");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("lower thread " + Thread.currentThread());
            return value.toLowerCase();
        });

        CompletableFuture<String> applyToEither = thenApplyAsync.applyToEither(thenApplyAsync1, (value) -> {
            System.out.println("function thread " + Thread.currentThread());
            System.out.println("got value " + value);
            return value + " from applyToEither";
        });

        System.out.println(applyToEither.join());
        Assert.assertTrue(applyToEither.join().endsWith("applyToEither"));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void apply_either() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("done");
        });

        CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture1.complete("DONE");
        });

        CompletableFuture<String> completableFuture2 = completableFuture.applyToEither(completableFuture1,
                (value) -> value + "applyToEither");

        System.out.println(completableFuture2.join());

    }
}
