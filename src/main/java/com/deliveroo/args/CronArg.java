package com.deliveroo.args;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CronArg {
    final TreeSet<Integer> values;
    final CronDateTimeType dateTimeType;

    public CronArg(CronDateTimeType dateTimeType, Set<Integer> values) {
        this.dateTimeType = dateTimeType;
        this.values = new TreeSet<>(values);
    }

    @Override
    public String toString() {
        return String.valueOf(String.format("%-14s%s\n", dateTimeType.toString(), values.stream().map(Object::toString).collect(Collectors.joining(" "))));
    }
}
