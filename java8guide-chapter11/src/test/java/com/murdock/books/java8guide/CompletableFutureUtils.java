package com.murdock.books.java8guide;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author weipeng2k 2018年06月04日 上午10:32:45
 */
public class CompletableFutureUtils {

    static <T> CompletableFuture<T> make(String threadName, long waitMills, T value) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            System.out.println(threadName + " " + Thread.currentThread() + " wait " + waitMills + " mills");
            try {
                Thread.sleep(waitMills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " " + Thread.currentThread() + " set value " + value);
            completableFuture.complete(value);
        });
        return completableFuture;
    }

    static <T> CompletableFuture<T> error(String threadName, long waitMills, T value) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            System.out.println(threadName + " " + Thread.currentThread() + " wait " + waitMills + " mills");
            try {
                Thread.sleep(waitMills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + " " + Thread.currentThread() + " set exception ");
            completableFuture.completeExceptionally(new RuntimeException());
        });
        return completableFuture;
    }
}
