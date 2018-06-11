package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.function.IntFunction;

/**
 *
 * f(x) = x + 2
 * g(x) = 2 * f(x) + x
 *
 * g(1) = 2 * f(1) + 1 = 2 * 3 + 1 = 7
 *
 * @author weipeng2k 2018年01月21日 下午12:24:17
 */
public class MathTest {

    @Test
    public void test() {
        int g = g((x) -> x + 2, 1);
        System.out.println(g);
    }

    public int g(IntFunction<Integer> fx, int x) {
        return 2 * fx.apply(x) + x;
    }
}
