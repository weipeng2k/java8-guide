package com.murdock.books.java8guide;

import com.murdock.books.java8guide.condition.GreenPredicate;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author weipeng2k 2018年01月14日 下午17:54:13
 */
public class AppleUtilTest {

    private static final List<Apple> APPLE_LIST = new ArrayList<>();

    @BeforeClass
    public static void init() {
        for (int i = 0; i < 1000; i++) {
            Random random = new Random();
            int weight = random.nextInt(300);
            Color color = i % 2 == 0 ? Color.RED : Color.GREEN;
            APPLE_LIST.add(new Apple(weight, color));
        }
    }

    @Test
    public void filterGreenApple() {
        List<Apple> apples = AppleUtil.filterGreenApple(APPLE_LIST);
        Assert.assertTrue(apples.size() > 0);
    }

    @Test
    public void filterApple() {
        List<Apple> apples = AppleUtil.filterApple(APPLE_LIST, Color.RED);
        Assert.assertTrue(apples.size() > 0);
    }

    @Test
    public void filterApple1() {
        List<Apple> apples = AppleUtil.filterApple(APPLE_LIST, new GreenPredicate());
        Assert.assertTrue(apples.size() > 0);
    }

    @Test
    public void filterAppleInnerClass() {
        List<Apple> apples = AppleUtil.filterApple(APPLE_LIST, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.isBig();
            }
        });
        Assert.assertTrue(apples.size() > 0);
    }

    @Test
    public void filterAppleLambda() {
        // 大红苹果
        List<Apple> apples = AppleUtil.filterApple(APPLE_LIST, apple -> apple.getColor() == Color.RED && apple.isBig());
        System.out.println(apples.size());
        Assert.assertTrue(apples.size() > 0);
    }

    @Test
    public void filter() {
    }


}