package com.deliveroo.expression;

public class Main {

    public static void main(String[] args) {
        try {
            CronExpression cronExpression = parseCronExpression(args);
            System.out.println(cronExpression);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static CronExpression parseCronExpression(String[] arguments) {
        if (arguments.length != 6) {
            throw new IllegalArgumentException("The number of provided arguments is " + arguments.length + " instead of 6");
        }
        return new CronExpression(arguments);
    }
}
