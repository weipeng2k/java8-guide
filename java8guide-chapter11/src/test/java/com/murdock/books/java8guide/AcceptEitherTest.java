package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author weipeng2k 2018年06月03日 下午16:42:56
 */
public class AcceptEitherTest {

    /**
     * <pre>
     *     构建一个CompletableFuture c1
     *     构建一个CompletableFuture c2
     *     当c1.acceptEither(c2, Function)时，会返回一个CompletableFuture c3
     *     c3会等待c1和c2中先返回的结果，然后运用Function，得到结果并返回
     * </pre>
     */
    @Test
    public void accept_either() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " set value");
            completableFuture.complete("done");
        });

        CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " set value");
            completableFuture1.complete("DONE");
        });

        CompletableFuture<Void> voidCompletableFuture = completableFuture.acceptEither(completableFuture1, (value) -> {
            System.out.println("function thread " + Thread.currentThread());
            System.out.println("got value " + value);
        });

        voidCompletableFuture.join();
    }

    @Test
    public void accept_either_async() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " set value");
            completableFuture.complete("done");
        });

        CompletableFuture<String> completableFuture1 = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + " set value");
            completableFuture1.complete("DONE");
        });

        CompletableFuture<Void> voidCompletableFuture = completableFuture.acceptEitherAsync(completableFuture1, (value) -> {
            System.out.println("function thread " + Thread.currentThread());
            System.out.println("got value " + value);
        });

        voidCompletableFuture.join();
    }
}
