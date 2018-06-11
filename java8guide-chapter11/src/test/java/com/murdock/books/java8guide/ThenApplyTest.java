package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年05月30日 下午14:02:22
 */
public class ThenApplyTest {

    /**
     * <pre>
     * 这里可以看到进行thenApply计算时，会根据方法要求返回一个CompletableFuture
     * 在这个方法中会获取当前CompletableFuture的结果，然后进行Function计算，并将计算的结果
     * 设置到返回的CompletableFuture中
     * </pre>
     */
    @Test
    public void then_apply() {
        CompletableFuture<String> message = CompletableFuture.completedFuture("message").thenApply(result -> {
            System.out.println(Thread.currentThread());
            return result.toUpperCase();
        });

        Assert.assertEquals("MESSAGE", message.join());
    }
}
