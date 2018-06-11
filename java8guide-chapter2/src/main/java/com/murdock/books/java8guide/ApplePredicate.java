package com.murdock.books.java8guide;

/**
 * @author weipeng2k 2018年01月14日 下午17:59:52
 */
public interface ApplePredicate {
    /**
     * 测试当前apple是否符合条件
     *
     * @param apple apple
     * @return true表示符合
     */
    boolean test(Apple apple);
}
