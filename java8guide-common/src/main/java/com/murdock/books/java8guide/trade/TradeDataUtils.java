package com.murdock.books.java8guide.trade;

import java.util.Arrays;
import java.util.List;

/**
 * @author weipeng2k 2018年02月03日 下午20:38:47
 */
public class TradeDataUtils {

    public static List<Transaction> transactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        return Arrays.asList(new Transaction(brian, 2011, 300, Currency.RMB),
                new Transaction(raoul, 2012, 1000, Currency.DOLLARS),
                new Transaction(raoul, 2011, 400, Currency.RMB),
                new Transaction(mario, 2012, 710, Currency.DOLLARS),
                new Transaction(mario, 2012, 700, Currency.DOLLARS),
                new Transaction(alan, 2012, 950, Currency.RMB));

    }
}
