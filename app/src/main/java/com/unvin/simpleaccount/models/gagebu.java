package com.unvin.simpleaccount.models;

public class gagebu {
    private int category;
    private String txt_category;
    private String cost;
    private String datetime;

    public gagebu(int category, String txt_category, String cost, String datetime) {
        this.category=category;
        this.txt_category=txt_category;
        this.cost=cost;
        this.datetime=datetime;
    }
    public int getCategory() {
        return this.category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTxt_category() {
        return this.txt_category;
    }

    public void setTxt_category(String txt_category) {
        this.txt_category = txt_category;
    }

    public String getCost() {
        return this.cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
