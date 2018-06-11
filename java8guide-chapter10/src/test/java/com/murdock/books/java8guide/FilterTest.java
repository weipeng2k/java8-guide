package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author weipeng2k 2018年02月27日 下午21:42:10
 */
public class FilterTest {

    @Test
    public void filter() {
        Optional<String> stringOptional = Optional.of("String");
        Predicate<String> predicate = String::isEmpty;
        Predicate<String> negate = predicate.negate();
        stringOptional.filter(negate).ifPresent(System.out::println);
    }

    @Test
    public void filter_null() {
        Optional<String> stringOptional = Optional.ofNullable(null);
        Predicate<String> predicate = String::isEmpty;
        Predicate<String> negate = predicate.negate();
        String no = stringOptional.filter(negate).orElse("no");
        Assert.assertEquals("no", no);
    }
}
