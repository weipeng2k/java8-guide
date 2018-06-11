package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author weipeng2k 2018年01月21日 上午11:27:26
 */
public class AppleSearchTest {
    @Test
    public void find() {
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Apple apple = new Apple(i % 4, (i % 3) == 0 ? Color.RED : Color.GREEN);
            apples.add(apple);
        }

        Predicate<Apple> applePredicate = new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor() == Color.RED;
            }
        };

        Predicate<Apple> applePredicate1 = (Apple apple) -> {return apple.getColor() == Color.RED;};

        Predicate<Apple> applePredicate2 = (Apple apple) -> apple.getColor() == Color.RED;

        Predicate<Apple> applePredicate3 = apple -> apple.getColor() == Color.RED;

        BiFunction<Apple, Apple, Boolean> as = (a , b) -> a.getWeight() > b.getWeight();

        apples.stream().filter(applePredicate.and(apple -> apple.getWeight() > 1)).collect(Collectors.toList()).forEach(
                System.out::println);
    }

    Predicate<Apple> m() {
        return apple -> apple.isBig();
    }
}
