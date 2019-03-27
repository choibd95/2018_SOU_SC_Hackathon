package com.kimjinhyeok.tree.dreamtreeproject;

/**
 * Created by aqoong on 2017. 10. 18..
 */

public class ListItem {

    private String text = null;
    private int imgId = 0;
    private boolean isChecked = false;


    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBanking() {
        return banking;
    }

    public void setBanking(int banking) {
        this.banking = banking;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    String con;
    String intime;
    int money;
    int banking;
    int seq;

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }

    int minus;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    String div;


    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
