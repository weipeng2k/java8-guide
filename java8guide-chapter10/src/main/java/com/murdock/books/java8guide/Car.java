package com.murdock.books.java8guide;

import java.util.Optional;

/**
 * @author weipeng2k 2018年02月25日 下午16:28:56
 */
public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }

    public void setInsurance(Optional<Insurance> insurance) {
        this.insurance = insurance;
    }
}
