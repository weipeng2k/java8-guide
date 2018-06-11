package com.murdock.books.java8guide.async;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年03月11日 下午13:52:41
 */
public class ReturnTest {

    @Test
    public void one_of() {
        CompletableFuture<String> a = CompletableFuture.supplyAsync(() -> print("A"));
        CompletableFuture<String> b = CompletableFuture.supplyAsync(() -> print("B"));
        CompletableFuture<String> c = CompletableFuture.supplyAsync(() -> print("C"));
        CompletableFuture<String> d = CompletableFuture.supplyAsync(() -> print("D"));

        CompletableFuture.anyOf(a, b, c, d).thenAccept(System.out::println);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String print(String value) {
        int sleepTime = 0;
        try {
            Random random = new Random();
            sleepTime = 500 + random.nextInt(2000);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + ":" + value + " sleep time "+ sleepTime);
        return value;
    }
}
