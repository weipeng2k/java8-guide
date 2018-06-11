package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author weipeng2k 2018年03月04日 上午09:46:19
 */
public class FutureGetTest {

    /**
     * 超时获取
     */
    @Test
    public void get_timeout() throws ExecutionException, InterruptedException {
        System.out.println(System.getProperty("_enable_optimized_hessian"));
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> stringFuture1 = executorService.submit(this::waitWhile);

        oneSecond(3);

        try {
            String value = stringFuture1.get(3, TimeUnit.SECONDS);
            System.out.println(value);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        Future<String> future = executorService.submit(() -> "fast");
        long start = System.currentTimeMillis();
        String s = future.get();
        System.out.println(s);
        long duration = System.currentTimeMillis() - start;
        System.out.println("fast cost:" + duration);
        Assert.assertTrue(duration > 3000);
    }

    @Test
    public void get_timeout_with_interrupted() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> stringFuture1 = executorService.submit(this::waitWhile);

        oneSecond(3);

        try {
            String value = stringFuture1.get(3, TimeUnit.SECONDS);
            System.out.println(value);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            stringFuture1.cancel(true);
        }

        Future<String> future = executorService.submit(() -> "fast");
        long start = System.currentTimeMillis();
        String s = future.get();
        System.out.println(s);
        long duration = System.currentTimeMillis() - start;
        System.out.println("fast cost:" + duration);
        Assert.assertTrue(duration < 2);
    }

    private void oneSecond(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    String waitWhile() {
        oneSecond(10);

        return "" + System.currentTimeMillis();
    }
}
