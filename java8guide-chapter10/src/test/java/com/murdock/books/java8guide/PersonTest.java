package com.murdock.books.java8guide;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * @author weipeng2k 2018年02月25日 下午16:32:27
 */
public class PersonTest {

    @Test
    public void get_name() {
        Insurance insurance = new Insurance();
        insurance.setName("Test");

        Car car = new Car();
        car.setInsurance(Optional.ofNullable(insurance));

        Person person = new Person();
        person.setCar(Optional.ofNullable(car));

        Optional<Person> personOptional = Optional.ofNullable(person);
        Optional<String> stringOptional = personOptional
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName);

        stringOptional.ifPresent(System.out::println);
        Assert.assertEquals("Test", stringOptional.get());
    }

    @Test
    public void null_name() {
        Car car = new Car();
        car.setInsurance(Optional.empty());

        Person person = new Person();
        person.setCar(Optional.ofNullable(car));

        Optional<Person> personOptional = Optional.ofNullable(person);
        String name = personOptional
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");

        Assert.assertEquals("Unknown", name);
    }

    @Test
    public void null_car() {
        Person person = new Person();
        person.setCar(Optional.empty());

        Optional<Person> personOptional = Optional.ofNullable(person);
        String name = personOptional
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");

        Assert.assertEquals("Unknown", name);
    }

    @Test(expected = NullPointerException.class)
    public void null_exception() {
        Person person = new Person();

        Optional<Person> personOptional = Optional.of(person);
        String name = personOptional
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
        System.out.println(name);
    }
}
