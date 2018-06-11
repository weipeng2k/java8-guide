package com.murdock.books.java8guide.async;

/**
 * @author weipeng2k 2018年03月08日 下午15:46:08
 */
public class InterruptedTest {

    public static void main(String[] args) {
        Object obj = new Object();

        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
                e.printStackTrace();
            }
        }

        System.out.println("exit.");
    }
}
