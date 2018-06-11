package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author weipeng2k 2018年06月01日 下午16:00:26
 */
public class CancelTest {

    @Test
    public void cancel() {
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("done");
        boolean cancel = completableFuture.cancel(true);
        System.out.println(cancel);
    }

    /**
     * <pre>
     *     等价于设置一个CancellationException
     * </pre>
     */
    @Test
    public void cancel_ex() {
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("done").thenApplyAsync(
                (value) -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return value.toUpperCase();
                });

        //        System.out.println(completableFuture.join()); DONE

        CompletableFuture<String> exceptionally = completableFuture.exceptionally((throwable -> {
            System.out.println(throwable);
            return "canceled message";
        }));

        boolean cancel = completableFuture.cancel(true);

        Assert.assertTrue(cancel);
        Assert.assertTrue(completableFuture.isCompletedExceptionally());
        Assert.assertEquals("canceled message", exceptionally.join());
    }

    @Test
    public void exceptional_get() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(new RuntimeException());

        Assert.assertTrue(completableFuture.isCompletedExceptionally());
        Assert.assertTrue(completableFuture.isDone());
    }

    /**
     * 创建一个CompletableFuture，它完成时将会通知下一个CompletableFuture进行exceptionally操作
     */
    @Test
    public void exceptionally() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        CompletableFuture<String> exceptionally = completableFuture.exceptionally((throwable -> {
            System.out.println(Thread.currentThread());
            return "got error";
        }));

        completableFuture.completeExceptionally(new RuntimeException());

        String join = exceptionally.join();

        Assert.assertEquals("got error", join);

        Assert.assertFalse(exceptionally.isCompletedExceptionally());
        Assert.assertTrue(exceptionally.isDone());
    }

    /**
     * handleAsync的BiFunction在common thread pool中运行后，完成结果的设置
     */
    @Test
    public void exceptionally_handle() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        CompletableFuture<String> handleAsync = completableFuture.handleAsync((v, ex) -> {
            System.out.println("function thread -> " + Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Got value : " + v);
            System.out.println("Got ex : " + ex);
            return "done";
        });

        Executors.newSingleThreadExecutor().execute(() -> {
            System.out.println("wait result");
            System.out.println("join thread -> " + Thread.currentThread());
            String join = handleAsync.join();
            System.out.println(join);

            Assert.assertEquals("done", join);
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("begin set exception");
        completableFuture.completeExceptionally(new RuntimeException());

        Assert.assertFalse(handleAsync.isCompletedExceptionally());
        Assert.assertFalse(handleAsync.isDone());

        try {
            Thread.sleep(1010);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertFalse(handleAsync.isCompletedExceptionally());
        Assert.assertTrue(handleAsync.isDone());
    }

}
