package com.unvin.simpleaccount.models;

public class day {
    private String day;
    private String total;
    private String income;
    private String consume;

    public day(String day, String total, String income, String consume) {
        this.day = day;
        this.total = total;
        this.income = income;
        this.consume = consume;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getConsume() {
        return consume;
    }

    public void setConsume(String consume) {
        this.consume = consume;
    }
}
