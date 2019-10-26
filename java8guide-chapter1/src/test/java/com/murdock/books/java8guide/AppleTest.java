package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author weipeng2k 2018年01月13日 下午21:23:05
 */
public class AppleTest {

    @Test
    public void test() {
        List<Apple> apples = Arrays.asList(new Apple(160, Color.RED), new Apple(190, Color.GREEN), new Apple(140, Color.GREEN),
                new Apple(170, Color.GREEN), new Apple(170, Color.RED));

        Map<Color, List<Apple>> collect = apples.stream().filter(Apple::isBig).collect(groupingBy(Apple::getColor));
        System.out.println(collect);
    }

    @Test
    public void parallel() {
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            int weight = new Random().nextInt(300);
            Color color  = weight % 2 == 0 ? Color.RED : Color.GREEN;
            apples.add(new Apple(weight, color));
        }

        long start = System.currentTimeMillis();
        Map<Color, List<Apple>> collect = apples.stream().filter(Apple::isBig).collect(groupingBy(Apple::getColor));
        collect.forEach((type, appleList) -> System.out.println(type + " size: " + appleList.size()) );
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        Map<Color, List<Apple>> parallelCollect = apples.parallelStream().filter(Apple::isBig).collect(groupingBy(Apple::getColor));
        parallelCollect.forEach((type, appleList) -> System.out.println(type + " size: " + appleList.size()) );
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void staticMethod() {
        List<Apple> apples = Arrays.asList(new Apple(160, Color.RED), new Apple(140, Color.GREEN),
                new Apple(170, Color.GREEN));

        Map<Color, List<Apple>> collect = apples.stream().filter(AppleUtil::isTest).collect(groupingBy(Apple::getColor));
        System.out.println(collect);
    }

    @Test
    public void lambda() {
        List<Apple> apples = Arrays.asList(new Apple(160, Color.RED), new Apple(140, Color.GREEN),
                new Apple(170, Color.GREEN));

        Map<Color, List<Apple>> collect = apples.stream().filter((apple) -> !apple.isBig()).collect(groupingBy(Apple::getColor));
        System.out.println(collect);
    }
}
