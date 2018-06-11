package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年06月01日 下午14:58:53
 */
public class ThenAcceptAsyncTest {

    /**
     * <pre>
     *     对于CompletableFuture的thenAcceptAsync消费CompletableFuture的结果
     *     消费的动作在Async的线程中做
     *
     *     对于CompletableFuture的后续编排函数，会在：
     *        本线程
     *        异步线程
     *     中完成函数并返回
     *
     *
     * </pre>
     */
    @Test
    public void then_accept_async() {
        CompletableFuture<Void> done = CompletableFuture.completedFuture("done").thenAcceptAsync((value) -> {
            System.out.println(Thread.currentThread());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("got value: " + value);
        });

        done.join();
    }
}
