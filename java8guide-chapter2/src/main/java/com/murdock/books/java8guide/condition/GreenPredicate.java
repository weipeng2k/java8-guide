package com.murdock.books.java8guide.condition;

import com.murdock.books.java8guide.Apple;
import com.murdock.books.java8guide.ApplePredicate;
import com.murdock.books.java8guide.Color;

/**
 * @author weipeng2k 2018年01月14日 下午18:01:32
 */
public class GreenPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getColor() == Color.GREEN;
    }
}
