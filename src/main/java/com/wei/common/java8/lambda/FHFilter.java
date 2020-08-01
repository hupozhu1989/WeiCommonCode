package com.wei.common.java8.lambda;

import java.util.ArrayList;
import java.util.List;

public class FHFilter {
    public <T> List<T> filter(List<T> inventory, FilterPredicate<T> predicate) {
        List<T> result = new ArrayList<T>();
        for (T t : inventory) {
            if (predicate.filter(t)) {
                result.add(t);
            }
        }
        return result;
    }
}