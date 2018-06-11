package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

/**
 * @author weipeng2k 2018年06月04日 下午14:36:26
 */
public class AllOfTest {

    @Test
    public void all_of() {
        CompletableFuture<Integer> one = CompletableFutureUtils.make("one", 1000, 1);
        CompletableFuture<Integer> two = CompletableFutureUtils.make("two", 2000, 2);
        CompletableFuture<Integer> three = CompletableFutureUtils.make("three", 3000, 3);
        CompletableFuture<Integer> four = CompletableFutureUtils.make("four", 4000, 4);

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(one, two, three, four);
        voidCompletableFuture.whenComplete((v, ex) -> {
            System.out.println(v);
            System.out.println(ex);
        }).join();
    }

    @Test(expected = CompletionException.class)
    public void all_of_ex() {
        CompletableFuture<Integer> one = CompletableFutureUtils.make("one", 100, 1);
        CompletableFuture<Integer> two = CompletableFutureUtils.make("two", 200, 2);
        CompletableFuture<Integer> three = CompletableFutureUtils.make("three", 300, 3);
        CompletableFuture<Integer> four = CompletableFutureUtils.error("four", 400, 4);


        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(one, two, three, four);
        voidCompletableFuture.whenComplete((v, ex) -> {
            System.out.println(v);
            System.out.println(ex);
        }).join();

    }
}
