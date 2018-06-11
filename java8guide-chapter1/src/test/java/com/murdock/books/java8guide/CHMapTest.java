package com.murdock.books.java8guide;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author weipeng2k 2018年04月28日 下午15:45:52
 */
public class CHMapTest {

    ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    @Test
    public void test() {
        Thread a = new Thread(() -> {
            addA();
            System.out.println(map.get("a"));
        });
        Thread b = new Thread(() -> {
            addA();
            System.out.println(map.get("a"));
        });
        Thread c = new Thread(() -> {
            addA();
            System.out.println(map.get("a"));
        });
        a.start();
        b.start();
        c.start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void addA() {
        map.computeIfAbsent("a", (key) -> {
            try {
                System.out.println(key);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return key + Thread.currentThread();
        });
    }
}
