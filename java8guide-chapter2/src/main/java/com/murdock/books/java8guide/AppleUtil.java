package com.murdock.books.java8guide;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weipeng2k 2018年01月14日 下午17:51:00
 */
public class AppleUtil {

    public static List<Apple> filterGreenApple(List<Apple> appleList) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            if (apple.getColor() == Color.GREEN) {
                apples.add(apple);
            }
        }

        return apples;
    }

    public static List<Apple> filterApple(List<Apple> appleList, Color color) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            if (apple.getColor() == color) {
                apples.add(apple);
            }
        }

        return apples;
    }

    public static List<Apple> filterApple(List<Apple> appleList, ApplePredicate applePredicate) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : appleList) {
            if (applePredicate.test(apple)) {
                apples.add(apple);
            }
        }

        return apples;
    }
}
