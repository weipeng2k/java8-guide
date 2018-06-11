package com.murdock.books.java8guide;

import java.util.Optional;

/**
 * @author weipeng2k 2018年02月25日 下午16:31:59
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
