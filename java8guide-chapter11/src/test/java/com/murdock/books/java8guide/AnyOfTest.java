package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年06月04日 下午14:28:43
 */
public class AnyOfTest {

    /**
     * <pre>
     *     组合若干的CompletableFuture，如果其中一个完成计算
     * </pre>
     */
    @Test
    public void any_of() {
        CompletableFuture<Integer> one = CompletableFutureUtils.make("one", 1000, 1);
        CompletableFuture<Integer> two = CompletableFutureUtils.make("two", 2000, 2);
        CompletableFuture<Integer> three = CompletableFutureUtils.make("three", 3000, 3);
        CompletableFuture<Integer> four = CompletableFutureUtils.make("four", 4000, 4);

        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(one, two, three, four);

        System.out.println("already create anyOf");
        objectCompletableFuture.thenAccept((value) -> {
            System.out.println("func thread " + Thread.currentThread());
            System.out.println(value);
        }).join();

    }


}
