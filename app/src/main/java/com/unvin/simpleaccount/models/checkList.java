package com.unvin.simpleaccount.models;

public class checkList {
    private int money;
    private String who;
    private String cost;
    private  String what;
    private String datetime;

    public checkList(int money, String who, String cost, String what, String datetime) {
        this.money = money;
        this.who = who;
        this.cost = cost;
        this.what = what;
        this.datetime = datetime;
    }

    public int getMoney() {
        return this.money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getWhat() {
        return this.what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
