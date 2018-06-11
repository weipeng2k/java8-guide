package com.murdock.books.java8guide;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * @author weipeng2k 2018年03月17日 下午19:29:30
 */
public class LocalDateTest {

    @Test
    public void create() {
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        System.out.println("Year:" + localDate.getYear());
        System.out.println("Month:" + localDate.getMonth());
        System.out.println("DayOfYear:" + localDate.getDayOfYear());
        System.out.println("DayOfMonth:" + localDate.getDayOfMonth());
        System.out.println("DayOfWeek:" + localDate.getDayOfWeek());
        System.out.println("LengthOfMonth:" + localDate.lengthOfMonth());
        System.out.println("LengthOfYear:" + localDate.lengthOfYear());
        System.out.println("LeapYear:" + localDate.isLeapYear());
    }

    @Test
    public void get_by_temporal_field() {
        LocalDate now = LocalDate.now();

        LocalTime time = LocalTime.now();

        System.out.println("now.get(ChronoField.YEAR): " + now.get(ChronoField.YEAR));
        System.out.println("now.get(ChronoField.HOUR_OF_DAY): " + time.get(ChronoField.HOUR_OF_DAY));
    }

    @Test
    public void parse() {
        LocalDate date = LocalDate.parse("18-03-17", DateTimeFormatter.ofPattern("yy-MM-dd", Locale.US));
        System.out.println(date);
        System.out.println(date.getChronology());
        System.out.println(date.getEra());
    }

    @Test
    public void change_content() {
        LocalDate now = LocalDate.now();
        System.out.println("now:" + now);

        LocalDate with = now.with(ChronoField.YEAR, 2020);

        System.out.println("now.with(ChronoField.YEAR, 2020);");
        System.out.println("now:" + now);
        System.out.println("with:" + with);

        LocalDate with1 = with.plusMonths(200);
        LocalDate with2 = with.plus(1, ChronoUnit.DAYS);
        System.out.println("with1:" + with1);
        System.out.println("with2:" + with2);
    }

    @Test
    public void change_using_with() {
        LocalDate now = LocalDate.now();
        LocalDate with = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("now.with(TemporalAdjusters.lastDayOfMonth())");
        System.out.println("with:" + with);
    }

    @Test
    public void next_working_day() {
        LocalDate now = LocalDate.now();
        LocalDate with = now.with(new NextWorkingDay());
        System.out.println("now:" + now + ", with:" + with);

        now = LocalDate.of(2018, 3, 15);
        with = now.with(new NextWorkingDay());
        System.out.println("now:" + now + ", with:" + with);

        now = LocalDate.of(2018, 3, 17);
        with = now.with(new NextWorkingDay());
        System.out.println("now:" + now + ", with:" + with);

        now = LocalDate.of(2018, 3, 16);
        with = now.with(new NextWorkingDay());
        System.out.println("now:" + now + ", with:" + with);
    }

    class NextWorkingDay implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            int dayOfWeek = temporal.get(ChronoField.DAY_OF_WEEK);
            switch (dayOfWeek) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 7:
                    temporal = temporal.plus(1, ChronoUnit.DAYS);
                    break;
                case 5:
                    temporal = temporal.plus(3, ChronoUnit.DAYS);
                    break;
                case 6:
                    temporal = temporal.plus(2, ChronoUnit.DAYS);
                    break;
            }

            return temporal;
        }
    }
}
