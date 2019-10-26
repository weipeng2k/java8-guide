package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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

        List<String> message = new ArrayList<>();
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Set<Boolean> resultMap = message.stream()
                .map(CompletableFuture::completedFuture)
                .map(cf -> cf.thenApplyAsync(this::handleMessage, threadPool))
                .map(CompletableFuture::join)
                .collect(Collectors.toSet());

        if (resultMap.size() == 1) {

        }

    }

    @Test
    public void add() {
        List<String> message = new ArrayList<>();
        message.add("1");
        message.add("2");
        message.add("3");
        message.add("4");
        message.add("5");
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        long start = System.currentTimeMillis();
        CompletableFuture[] completableFutures = message.stream()
                .map(CompletableFuture::completedFuture)
                .map(cf -> cf.thenApplyAsync(this::handleMessage, threadPool))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(completableFutures)
                .join();

        System.out.println(System.currentTimeMillis() - start);
    }

    public boolean handleMessage(String message) {
        int i = Integer.parseInt(message);
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}
