package com.murdock.books.java8guide;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * @author weipeng2k 2018年03月18日 上午10:31:31
 */
public class LocalDateTimeTest {

    @Test
    public void create_pattern() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, Month.MARCH, 18, 10, 32, 10);
        System.out.println(localDateTime);

        System.out.println("localDateTime.toLocalDate():" + localDateTime.toLocalDate());
        System.out.println("localDateTime.toLocalTime():" + localDateTime.toLocalTime());
    }

    @Test
    public void test() {
        LocalDate localDate = LocalDate.of(2019, Month.NOVEMBER, 1);

        System.out.println(localDate.getDayOfYear());
    }


}
