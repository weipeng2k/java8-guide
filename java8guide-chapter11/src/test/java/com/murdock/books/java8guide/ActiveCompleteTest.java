package com.murdock.books.java8guide;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author weipeng2k 2018年06月09日 下午19:19:02
 */
public class ActiveCompleteTest {

    @Test
    public void get() {
        CompletableFuture<String> make = CompletableFutureUtils.make("get", 1000, "GET");

        try {
            Assert.assertEquals("GET", make.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get_timeout() {
        CompletableFuture<String> make = CompletableFutureUtils.make("get", 1000, "GET");

        try {
            make.get(500, TimeUnit.MILLISECONDS);
            TestCase.fail();
        } catch (InterruptedException | TimeoutException | ExecutionException ignored) {
        }
    }

    /**
     * 如果没有完成，返回默认的值
     */
    @Test
    public void get_now() {
        CompletableFuture<String> make = CompletableFutureUtils.make("get", 1000, "GET");

        String zk = make.getNow("ZK");
        Assert.assertEquals("ZK", zk);

        try {
            Thread.sleep(1050);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String zk1 = make.getNow("ZK");
        Assert.assertEquals("GET", zk1);
    }

    @Test
    public void join() {
        CompletableFuture<String> make = CompletableFutureUtils.make("get", 1000, "GET");
        String join = make.join();
        Assert.assertEquals("GET", join);
    }

    /**
     * 如果是执行Exception，会包装成为ExecutionException
     */
    @Test
    public void get_exception() {
        CompletableFuture<String> error = CompletableFutureUtils.error("get", 1000, "GET");

        try {
            error.get();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof ExecutionException);
            Assert.assertTrue(e.getCause() instanceof RuntimeException);
        }
    }

    /**
     * 对于计算会产生的异常，将会包装在CompletionException中
     */
    @Test(expected = CompletionException.class)
    public void join_exception() {
        CompletableFuture<String> error = CompletableFutureUtils.error("get", 1000, "GET");

        String join = error.join();
    }

    @Test(expected = CompletionException.class)
    public void join_exception_on_function() {
        CompletableFuture<String> error = CompletableFutureUtils.error("get", 1000, "GET");
        CompletableFuture<Object> objectCompletableFuture = error.thenApply((str) -> {
            throw new RuntimeException();
        });

        objectCompletableFuture.join();
    }

}
