package com.murdock.books.java8guide.async;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author weipeng2k 2018年03月04日 下午12:14:06
 */
public class ShopAsyncTest {

    @Test
    public void async_get_price() {
        Shop shop = new Shop("test");
        Future<Double> doubleFuture = shop.getPrice("item1");
        System.out.println("after getPrice item1");
        try {
            Double aDouble = doubleFuture.get();
            System.out.println("got item1 " + aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void async_get_price_error() {
        Shop shop = new Shop("test");
        Future<Double> doubleFuture = shop.getPrice("error");
        System.out.println("after getPrice item1");
        try {
            Double aDouble = doubleFuture.get();
            System.out.println("got item1 " + aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        }
    }

    @Test
    public void async_get_price2() {
        Shop shop = new Shop("test");
        Future<Double> doubleFuture = shop.getPrice2("item2");
        System.out.println("after getPrice item1");
        try {
            Double aDouble = doubleFuture.get();
            System.out.println("got item2 " + aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void async_get_price2_error() {
        Shop shop = new Shop("test");
        Future<Double> doubleFuture = shop.getPrice2("error");
        System.out.println("after getPrice item1");
        try {
            Double aDouble = doubleFuture.get();
            System.out.println("got item2 " + aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            System.out.println(e.getCause());
        }
    }

}
