package com.murdock.books.java8guide.async;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年03月11日 下午12:16:47
 */
public class ThenComposeTest {

    @Test
    public void compose() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "String";
        });

        CompletableFuture<Integer> integerCompletableFuture = stringCompletableFuture.thenCompose(
                s -> CompletableFuture.supplyAsync(s::length));

        System.out.println(integerCompletableFuture.join());
    }
}
