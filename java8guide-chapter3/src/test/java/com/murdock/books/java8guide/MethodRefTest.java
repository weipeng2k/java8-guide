package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author weipeng2k 2018年01月20日 下午21:04:00
 */
public class MethodRefTest {

    @Test
    public void test() {
        Consumer<String> consumer = s -> {
            System.out.println(s);
        };

        consumer.accept("a");
    }

    @Test
    public void method_ref() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("xxx");
    }

    @Test
    public void constructor_ref() {
        Supplier<P> p = P::new;
        System.out.println(p.get());
    }

    @Test
    public void constructor_ref_1() {
        BiFunction<Integer, Color, Apple> biFunction = Apple::new;
        Apple apply = biFunction.apply(1, Color.RED);
        System.out.println(apply);
    }
}
