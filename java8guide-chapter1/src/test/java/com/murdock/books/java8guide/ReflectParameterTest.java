package com.murdock.books.java8guide;

import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author weipeng2k 2018年05月14日 上午10:24:09
 */
public class ReflectParameterTest {

    public String echo(String content) {
        return content;
    }

    @Test
    public void test() throws Exception {
        Method method = ReflectParameterTest.class.getMethod("echo", String.class);
        Parameter[] parameters = method.getParameters();
        System.out.println(parameters[0]);
    }
}
