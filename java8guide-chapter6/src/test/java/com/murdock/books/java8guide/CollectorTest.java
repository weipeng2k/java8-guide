package com.murdock.books.java8guide;

import com.murdock.books.java8guide.trade.Currency;
import com.murdock.books.java8guide.trade.TradeDataUtils;
import com.murdock.books.java8guide.trade.Transaction;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author weipeng2k 2018年02月03日 下午20:50:57
 */
public class CollectorTest {

    @Test
    public void grouping() {
        Map<Currency, List<Transaction>> collect = TradeDataUtils.transactions().stream().collect(
                Collectors.groupingBy(Transaction::getCurrency));

        System.out.println(collect);
    }

    @Test
    public void counting() {
        Long collect = TradeDataUtils.transactions().stream().count();
        System.out.println(collect);
    }

    @Test
    public void sum_int_stream() {
        int sum = TradeDataUtils.transactions().stream()
                .mapToInt(Transaction::getValue)
                .sum();
        System.out.println(sum);
    }

    @Test
    public void max() {
        Optional<Transaction> collect = TradeDataUtils.transactions().stream().collect(
                Collectors.maxBy(Comparator.comparing(Transaction::getValue)));
        System.out.println(collect);

        List<Transaction> emptyList = new ArrayList<>();

        Optional<Transaction> collect1 = emptyList.stream().collect(
                Collectors.minBy(Comparator.comparing(Transaction::getYear)));
        System.out.println(collect1);
        System.out.println(collect1.isPresent());
    }

    @Test
    public void sum() {
        Integer collect = TradeDataUtils.transactions().stream().filter((t) -> t.getCurrency() == Currency.RMB).collect(
                Collectors.summingInt(Transaction::getValue));
        System.out.println(collect);

        List<Transaction> emptyList = new ArrayList<>();
        Integer collect1 = emptyList.stream().collect(Collectors.summingInt(Transaction::getValue));
        System.out.println(collect1);
    }

    @Test
    public void avg() {
        Double collect = TradeDataUtils.transactions().stream().filter((t) -> t.getCurrency() == Currency.RMB).collect(
                Collectors.averagingInt(Transaction::getValue));
        System.out.println(collect);

        List<Transaction> emptyList = new ArrayList<>();
        Double collect1 = emptyList.stream().collect(Collectors.averagingInt(Transaction::getValue));
        System.out.println(collect1);
    }

    @Test
    public void summary() {
        IntSummaryStatistics collect = TradeDataUtils.transactions().stream().filter(
                (t) -> t.getCurrency() == Currency.RMB).collect(
                Collectors.summarizingInt(Transaction::getValue));
        System.out.println(collect);

        List<Transaction> emptyList = new ArrayList<>();
        IntSummaryStatistics collect1 = emptyList.stream().collect(Collectors.summarizingInt(Transaction::getValue));
        System.out.println(collect1);
    }

    @Test
    public void join() {
        String s = TradeDataUtils.transactions().stream().map(Transaction::toString).collect(
                Collectors.joining("\n", "#", "#"));
        System.out.println(s);
    }

    @Test(expected = NullPointerException.class)
    public void for_null() {
        Arrays.asList("xx", null, "yy").stream()
            .map(String::toUpperCase)
            .forEach(System.out::println);
    }
}
