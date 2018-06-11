package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author weipeng2k 2018年06月04日 上午11:19:32
 */
public class ThenCombineTest {

    /**
     * <pre>
     * 对于BiFunction的设置是遵循语义的
     * CompletableFuture c1
     * CompletableFuture c2
     * c1.thenCombine(c2, BiFunction)
     * 其中BiFunction对c1和c2的值进行应用，顺序与c1, c2一致，与先后返回无关
     * 但是运行Function的线程是其中慢的这个
     * </pre>
     */
    @Test
    public void then_combine() {
        CompletableFuture<String> c1 = CompletableFutureUtils.make("upper", 1000, "DONE");
        CompletableFuture<String> c2 = CompletableFutureUtils.make("lower", 3000, "done");

        CompletableFuture<String> completableFuture = c1.thenCombine(c2, (v1, v2) -> {
            System.out.println("func thread " + Thread.currentThread());

            return v1 + v2;
        });

        Assert.assertEquals("DONEdone", completableFuture.join());
    }

    @Test
    public void then_combine_async() {
        CompletableFuture<String> c1 = CompletableFutureUtils.make("upper", 1000, "DONE");
        CompletableFuture<String> c2 = CompletableFutureUtils.make("lower", 3000, "done");

        CompletableFuture<String> completableFuture = c1.thenCombineAsync(c2, (v1, v2) -> {
            System.out.println("func thread " + Thread.currentThread());

            return v1 + v2;
        });

        Assert.assertEquals("DONEdone", completableFuture.join());
    }
}
