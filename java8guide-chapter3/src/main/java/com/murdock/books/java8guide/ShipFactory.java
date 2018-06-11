package com.murdock.books.java8guide;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author weipeng2k 2018年01月21日 上午11:19:16
 */
public class ShipFactory {

    public static Ship get(String name) {
        Ship ship = null;
        switch (name) {
            case "warship":
                ship = new Warship();
                break;
            case "destroyer":
                ship = new Destroyer();
                break;
        }

        return ship;
    }

    private static Map<String, Supplier<Ship>> repos = new HashMap<>();

    static {
        repos.put("warship", Warship::new);
        repos.put("destroyer", Destroyer::new);
    }

    public static Ship getByLambda(String name) {
        return repos.get(name).get();
    }

}
