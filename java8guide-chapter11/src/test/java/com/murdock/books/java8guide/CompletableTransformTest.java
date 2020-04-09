package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author weipeng2k 2020年04月09日 下午13:32:46
 */
public class CompletableTransformTest {

    private static final Executor A = Executors.newSingleThreadExecutor(new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("A");
            return thread;
        }
    });

    private static final Executor B = Executors.newSingleThreadExecutor(new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("B");
            return thread;
        }
    });

    private static final Executor C = Executors.newSingleThreadExecutor(new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("C");
            return thread;
        }
    });

    @Test
    public void done() {
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("ABC");

        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFuture1 = completableFuture.thenApplyAsync(this::transferA, A)
                .thenApplyAsync(this::transferB, B)
                .thenApplyAsync(this::transferC, C);
        System.out.println("result:" + completableFuture1.join());
        System.out.println("Cost:" + (System.currentTimeMillis() - start));
    }

    public String transferA(String a) {
        System.out.println("transferA@" + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a.replace("A", "a");
    }

    public String transferB(String a) {
        System.out.println("transferB@" + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a.replace("B", "b");
    }

    public String transferC(String a) {
        System.out.println("transferC@" + Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return a.replace("C", "c");
    }
}
