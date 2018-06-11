package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * @author weipeng2k 2018年02月25日 下午13:51:11
 */
public class OptionalSimpleTest {

    @Test
    public void create() {
        Optional<Object> objectOptional = Optional.of(new Object());
        Assert.assertTrue(objectOptional.isPresent());
        objectOptional.ifPresent(System.out::println);
    }

    @Test
    public void nullCreate() {
        Optional<Object> objectOptional = Optional.ofNullable(null);
        Assert.assertFalse(objectOptional.isPresent());
        objectOptional.ifPresent(obj -> System.out.println(obj));
    }
}
