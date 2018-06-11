package com.murdock.books.java8guide;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author weipeng2k 2018年01月21日 上午11:20:53
 */
public class ShipFactoryTest {

    @Test
    public void get() {
        Ship ship = ShipFactory.get("destroyer");
        System.out.println(ship);
        assertNotNull(ship);
    }

    @Test
    public void get_lambda() {
        Ship ship = ShipFactory.getByLambda("warship");
        System.out.println(ship);
        assertNotNull(ship);
    }
}