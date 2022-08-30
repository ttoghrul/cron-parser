package com.deliveroo.expression;

import com.deliveroo.args.CronArg;
import com.deliveroo.args.CronDateTimeType;
import javafx.util.Pair;

import java.util.Set;
import java.util.TreeSet;

public class CronExpression {
    private final CronArg minute;
    private final CronArg hour;
    private final CronArg dayOfMonth;
    private final CronArg month;
    private final CronArg dayOfWeek;
    private final String command;

    public CronExpression(String[] arguments) throws IllegalArgumentException {
        this.minute = parseCronArg(arguments[0], CronDateTimeType.minute);
        this.hour = parseCronArg(arguments[1], CronDateTimeType.hour);
        this.dayOfMonth = parseCronArg(arguments[2], CronDateTimeType.dayOfMonth);
        this.month = parseCronArg(arguments[3], CronDateTimeType.month);
        this.dayOfWeek = parseCronArg(arguments[4], CronDateTimeType.dayOfWeek);
        this.command = arguments[5];
    }

    private static CronArg parseCronArg(String numbersStr, CronDateTimeType dateTimeType) throws IllegalArgumentException {
        if (numbersStr == null || numbersStr.isEmpty()) throw new IllegalArgumentException("Value format is not valid for Cron DateTime type: " + dateTimeType.toString());
        String[] numbersList = numbersStr.split(",");
        Set<Integer> valuesSet = new TreeSet<>();
        for (String number : numbersList) {
            valuesSet.addAll(parseValuesWithFreq(number, dateTimeType));
        }
        return new CronArg(dateTimeType, valuesSet);
    }

    private static Set<Integer> parseValuesWithFreq(String number, CronDateTimeType dateTimeType) throws IllegalArgumentException {
        Set<Integer> numbersWithFreq = new TreeSet<>();
        String[] numbers = number.split("/");
        if(numbers.length > 2) throw new IllegalArgumentException("Value format is not valid for Cron DateTime type: " + dateTimeType.toString());
        int freq = numbers.length == 2 ? Integer.parseInt(numbers[1]) : 1;
        Pair<Integer, Integer> range = parseValuesWithRange(numbers[0], dateTimeType);
        for (int i = range.getKey(); i <= range.getValue(); i += freq) {
            numbersWithFreq.add(i);
        }
        return numbersWithFreq;
    }

    private static Pair<Integer, Integer> parseValuesWithRange(String number, CronDateTimeType dateTimeType) throws IllegalArgumentException {
        String[] rangeValues = number.split("-");
        Pair<Integer, Integer> dateTimeTypeLimit = dateTimeType.limits;
        int start = dateTimeTypeLimit.getKey();
        int end = dateTimeTypeLimit.getValue();
        if(rangeValues.length > 2) throw new IllegalArgumentException("Value format is not valid for Cron DateTime type: " + dateTimeType);
        if (rangeValues.length == 1) {
            if (!rangeValues[0].equals("*")) {
                int singleValue = parseSingleValue(rangeValues[0], dateTimeType);
                start = singleValue;
                end = singleValue;
            }
        } else {
            start = Integer.parseInt(rangeValues[0]);
            end = Integer.parseInt(rangeValues[1]);
            if (start > end) {
                throw new IllegalArgumentException("Value format is not valid for Cron DateTime type: " + dateTimeType);
            }
        }
        return new Pair<>(start, end);
    }

    private static int parseSingleValue(String number, CronDateTimeType dateTimeType) throws IllegalArgumentException {
        int num = Integer.parseInt(number);
        if (!isNumberWithinLimit(num, dateTimeType)) {
            throw new IllegalArgumentException("Number is not within limits defined for Cron DateTime field: " + dateTimeType);
        }
        return num;
    }

    private static boolean isNumberWithinLimit(int number, CronDateTimeType dateTimeType) {
        return (number >= dateTimeType.limits.getKey() && number <= dateTimeType.limits.getValue());
    }

    @Override
    public String toString() {
        return "" + minute + hour + dayOfMonth + month + dayOfWeek + String.format("%-14s%s\n", "command", command);
    }
}
