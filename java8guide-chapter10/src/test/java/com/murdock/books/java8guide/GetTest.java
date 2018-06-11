package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author weipeng2k 2018年02月27日 下午21:11:01
 */
public class GetTest {

    @Test
    public void get() {
        Optional<Insurance> insuranceOptional = Optional.of(new Insurance());
        Insurance insurance = insuranceOptional.get();
        Assert.assertNotNull(insurance);
    }

    @Test(expected = NoSuchElementException.class)
    public void get_null() {
        Optional<Object> optionalO = Optional.ofNullable(null);
        optionalO.get();
    }

    @Test
    public void get_or_else() {
        Optional<String> optionalStr = Optional.ofNullable(null);
        String orElse = optionalStr.orElse("Default");
        Assert.assertEquals("Default", orElse);
    }

    @Test
    public void get_supply() {
        Optional<String> stringOptional = Optional.ofNullable(null);
        final long current = System.currentTimeMillis();
        String s = stringOptional.orElseGet(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return String.valueOf(System.currentTimeMillis() - current);
        });

        long l = Long.parseLong(s);
        Assert.assertTrue(l >= 1000);
    }

}
