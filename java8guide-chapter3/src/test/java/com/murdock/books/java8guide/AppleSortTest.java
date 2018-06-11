package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author weipeng2k 2018年01月21日 上午11:27:26
 */
public class AppleSortTest {
    @Test
    public void sort() {
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Apple apple = new Apple(i % 4, (i % 3) == 0 ? Color.RED : Color.GREEN);
            apples.add(apple);
        }

        apples.sort(Comparator.comparing(Apple::getWeight).thenComparing(Apple::getColor));
        apples.forEach(System.out::println);
    }
}
