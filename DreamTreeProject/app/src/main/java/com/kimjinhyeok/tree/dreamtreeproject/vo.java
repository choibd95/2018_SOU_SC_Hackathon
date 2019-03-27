package com.kimjinhyeok.tree.dreamtreeproject;

import static com.kimjinhyeok.tree.dreamtreeproject.viewFragment.Midx;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class vo
{


    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getGu() {
        return Gu;
    }

    public void setGu(String gu) {
        Gu = gu;
    }

    public String getDong() {
        return Dong;
    }

    public void setDong(String dong) {
        Dong = dong;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getTEL() {
        return TEL;
    }

    public void setTEL(String TEL) {
        this.TEL = TEL;
    }

    public String getKategorie() {
        return Kategorie;
    }

    public void setKategorie(String kategorie) {
        Kategorie = kategorie;
    }

    public int getMidx() {
        return Midx;
    }

    public void setMidx(int Midx) {
        this.Midx = Midx;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "vo{" +
                "Number=" + Number +
                ", Gu='" + Gu + '\'' +
                ", Dong='" + Dong + '\'' +
                ", Name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", TEL='" + TEL + '\'' +
                ", Kategorie='" + Kategorie + '\'' +
                ", Midx=" + Midx +
                ", datatime='" + datatime + '\'' +
                ", distance=" + distance +
                '}';
    }

    int Number;
    String Gu;
    String Dong;
    String Name;
    String Address;
    double Latitude;
    double Longitude;
    String  TEL;
    String Kategorie;
    int Midx;
    String datatime;
    double distance;

    public int getMinus() {
        return minus;
    }

    public void setMinus(int minus) {
        this.minus = minus;
    }

    int minus;

}
