package com.murdock.books.java8guide.async;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author weipeng2k 2018年03月11日 上午11:31:49
 */
public class ThenApplyTest {

    @Test
    public void thenApply() {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "String",
                Executors.newSingleThreadExecutor());
        CompletableFuture<Integer> integerCompletableFuture = stringCompletableFuture.thenApply(String::length);
        System.out.println(integerCompletableFuture.join());
    }
}
