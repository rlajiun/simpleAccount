package com.unvin.simpleaccount.models;

public class chartList {
    private int category;
    private String txt_cat;
    private String percent;
    private String cost;

    public chartList(int category, String txt_cat, String percent, String cost) {
        this.category = category;
        this.txt_cat = txt_cat;
        this.percent = percent;
        this.cost = cost;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTxt_cat() {
        return txt_cat;
    }

    public void setTxt_cat(String txt_cat) {
        this.txt_cat = txt_cat;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
