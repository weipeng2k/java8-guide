package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年06月04日 上午10:23:17
 */
public class RunAfterBothTest {

    /**
     * <pre>
     *     创建一个CompletableFuture，c1
     * </pre>
     */
    @Test
    public void run_after_both() {
        CompletableFuture<String> completableFuture1 = CompletableFutureUtils.make("upper", 1000, "DONE");
        CompletableFuture<String> completableFuture2 = CompletableFutureUtils.make("lower", 2000, "done");

        CompletableFuture<Void> voidCompletableFuture = completableFuture1.runAfterBoth(completableFuture2, () -> {
            System.out.println("Function thread " + Thread.currentThread());
            String j1 = completableFuture1.join();
            String j2 = completableFuture2.join();
            System.out.println(j1 + j2);
        });

        voidCompletableFuture.join();
    }

    @Test
    public void run_after_both_async() {
        CompletableFuture<String> completableFuture1 = CompletableFutureUtils.make("upper", 1000, "DONE");
        CompletableFuture<String> completableFuture2 = CompletableFutureUtils.make("lower", 2000, "done");

        CompletableFuture<Void> voidCompletableFuture = completableFuture1.runAfterBothAsync(completableFuture2, () -> {
            System.out.println("Function thread " + Thread.currentThread());
            String j1 = completableFuture1.join();
            String j2 = completableFuture2.join();
            System.out.println(j1 + j2);
        });

        voidCompletableFuture.join();
    }
}
