package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertSame;

/**
 * @author weipeng2k 2018年05月31日 下午17:31:06
 */
public class CompleteExceptionTest {

    /**
     * <pre>
     * 使用CompletableFuture.handle方法，可以通过传入一个函数
     * BiFunction来完成正常计算和异常结果的获取
     *
     * CompletableFuture是一个传递异步计算的数据结构，作为调用者获取到CompletableFuture
     * 可以获取到计算的结果值，而结果值是由另外线程进行设置
     * </pre>
     */
    @Test
    public void test() {
        Executor executor = Executors.newSingleThreadExecutor();

        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync((v) ->
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("got value");
                    return v.toUpperCase();
                },
                executor);

        CompletableFuture exceptionHandler = cf.handle((s, th) -> {
            return (th != null) ? "message upon cancel" : "";
        });
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        assertTrue("Was not completed exceptionally", cf.isCompletedExceptionally());
        try {
            cf.join();
            fail("Should have thrown an exception");
        } catch (CompletionException ex) { // just for testing
            assertEquals("completed exceptionally", ex.getCause().getMessage());
        }

        assertEquals("message upon cancel", exceptionHandler.join());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void handle_exception() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        CompletableFuture<Integer> handle = completableFuture.handle((v, ex) -> {
            if (ex != null) {
                return 0;
            } else {
                return 1;
            }
        });
        completableFuture.completeExceptionally(new RuntimeException());

        assertSame(0, handle.join());
    }

    @Test
    public void handle_stall() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        CompletableFuture<Boolean> handle = completableFuture.handle((value, ex) -> true);

        Thread thread = new Thread(() -> {
            System.out.println("begin stall");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("set value");
            completableFuture.complete("done.");
        });
        thread.start();

        handle.join();

    }
}
