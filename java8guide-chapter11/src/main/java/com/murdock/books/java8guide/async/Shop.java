package com.murdock.books.java8guide.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author weipeng2k 2018年03月04日 下午12:17:22
 */
public class Shop {
    private String name;

    public Shop(String name) {
        this.name = name;
    }


    public Future<Double> getPrice(String item1) {
        Future<Double> doubleFuture = Executors.newSingleThreadExecutor().submit(() -> syncGetPrice(item1));
        return doubleFuture;
    }

    public Future<Double> getPrice2(String item1) {
        return CompletableFuture.supplyAsync(() -> syncGetPrice(item1));
    }

    private Double syncGetPrice(String item) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if ("error".equalsIgnoreCase(item)) {
            throw new RuntimeException("got item error.");
        }

        return (double) item.length();
    }
}
