package com.murdock.books.java8guide.async;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年03月11日 下午12:28:53
 */
public class ThenCombineTest {

    private static int print(String value) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + ":" + value);
        return value.length();
    }

    @Test
    public void combine() {
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> print("A"));


        CompletableFuture<Integer> integerCompletableFuture = a.thenCombine(
                CompletableFuture.supplyAsync(() -> print("B")), Integer::sum)
                .thenCombine(CompletableFuture.supplyAsync(() -> print("C")), Integer::sum).thenCombine(
                        CompletableFuture.supplyAsync(() -> print("D")), Integer::sum).thenCombine(
                        CompletableFuture.supplyAsync(() -> print("E")), Integer::sum).thenCombine(
                        CompletableFuture.supplyAsync(() -> print("F")),
                        Integer::sum);

        System.out.println(integerCompletableFuture.join());
    }
}
