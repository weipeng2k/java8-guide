package com.murdock.books.java8guide;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/**
 * @author weipeng2k 2018年03月18日 上午10:57:16
 */
public class DurationTest {

    @Test
    public void create() {
        Duration duration = Duration.ofDays(100);
        System.out.println(duration);

        LocalDateTime localDate = LocalDateTime.now();
        System.out.println(localDate);

        LocalDateTime temporal = (LocalDateTime) duration.addTo(localDate);
        System.out.println(temporal);
    }
}
