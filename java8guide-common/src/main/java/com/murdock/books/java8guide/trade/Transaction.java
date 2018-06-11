package com.murdock.books.java8guide.trade;

/**
 * @author weipeng2k 2018年02月03日 下午20:37:46
 */
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
    private final Currency currency;

    public Transaction(Trader trader, int year, int value, Currency currency) {
        this.trader = trader;
        this.year = year;
        this.value = value;
        this.currency = currency;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                ", currency=" + currency +
                '}';
    }
}
