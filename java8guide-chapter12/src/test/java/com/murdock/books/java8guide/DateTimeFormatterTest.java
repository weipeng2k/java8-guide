package com.murdock.books.java8guide;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * @author weipeng2k 2018年03月18日 下午13:18:43
 */
public class DateTimeFormatterTest {

    @Test
    public void create() {
        DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH).appendLiteral("~")
                .appendText(ChronoField.MONTH_OF_YEAR).appendLiteral("#")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.US);

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.format(dateTimeFormatter));

        LocalDate parse = LocalDate.parse("18~March#2018", dateTimeFormatter);
        System.out.println(parse);
    }
}
