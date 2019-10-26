package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author weipeng2k 2018年08月02日 上午11:49:11
 */
public class SumQueryTest {

    private static long[] sleepArray = new long[]{1000, 1000, 2000, 3000, 4000, 8000, 7000, 4000, 1000, 50};
    private Executor executor = Executors.newFixedThreadPool(10);

    private static String convert(Integer id) {
        long timeout = sleepArray[id - 1];

        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "convert:" + id.toString() + " timeout " + timeout;
    }

    @Test
    public void sum_query() {
        // Init
        Map<Integer, String> result = new LinkedHashMap<>();
        IntStream.range(1, 11).forEach(value -> result.put(value, null));

        // start
        List<CompletableFuture<Void>> completableFutureList = new ArrayList<>();
        result.forEach((key, value) ->
                completableFutureList.add(CompletableFuture.completedFuture(key)
                        .thenApplyAsync(SumQueryTest::convert, executor)
                        .thenAccept(v -> result.put(key, v)))
        );
        CompletableFuture<Void> future = CompletableFuture.allOf(
                completableFutureList.toArray(new CompletableFuture[]{}));

        try {
            Void aVoid = future.get(5000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.forEach((key, value) -> System.out.println(key + " : " + value));

    }

    @Test
    public void sum_query_version_2() {
        // Init
        Map<Integer, String> result = new LinkedHashMap<>();
        IntStream.range(1, 11).forEach(value -> result.put(value, null));

        List<CompletableFuture<Void>> collect = IntStream.range(1, 11)
                .mapToObj(key -> CompletableFuture.completedFuture(key)
                        .thenApplyAsync(SumQueryTest::convert, executor)
                        .thenAccept(v -> result.put(key, v)))
                .collect(Collectors.toList());
        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(
                collect.toArray(new CompletableFuture[]{}));
        try {
            completableFuture.get(5000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    @Test
    public void sum_query_version_3() {
        // Init
        Map<Integer, String> result = new LinkedHashMap<>();
        IntStream.range(1, 11).forEach(value -> result.put(value, null));

        CompletableFuture<Void> completableFuture = CompletableFuture.allOf(IntStream.range(1, 11)
                .mapToObj(key -> CompletableFuture.completedFuture(key)
                        .thenApplyAsync(SumQueryTest::convert, executor)
                        .thenAccept(v -> result.put(key, v)))
                .toArray(CompletableFuture[]::new));
        try {
            completableFuture.get(5000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.forEach((key, value) -> System.out.println(key + " : " + value));
    }

}
