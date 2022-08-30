package com.deliveroo.args;

import javafx.util.Pair;

public enum CronDateTimeType {
    minute(new Pair<>(0, 59)),
    hour(new Pair<>(0, 23)),
    dayOfMonth(new Pair<>(1, 31)),
    month(new Pair<>(1, 12)),
    dayOfWeek(new Pair<>(1, 7));

    public final Pair<Integer, Integer> limits;

    CronDateTimeType(Pair<Integer, Integer> limits) {
        this.limits = new Pair<>(limits.getKey(), limits.getValue());
    }
}
