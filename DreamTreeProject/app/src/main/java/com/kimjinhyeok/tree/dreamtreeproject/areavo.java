package com.kimjinhyeok.tree.dreamtreeproject;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class areavo
{

    public int getNunber() {
        return Number;
    }

    public void setNunber(int nunber) {
        this.Number = nunber;
    }

    public String getGu() {
        return Gu;
    }

    public void setGu(String Gu) {
        this.Gu = Gu;
    }

    public String getDong() {
        return Dong;
    }

    public void setDong(String Dong) {
        this.Dong = Dong;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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

    @Override
    public String toString() {
        return "vo{" +
                "Nunber=" + Number +
                ", Gu='" + Gu + '\'' +
                ", Dong='" + Dong + '\'' +
                ", name='" + Name + '\'' +
                ", Address='" + Address + '\'' +
                ", Latitude=" + Latitude +
                ", Longitude=" + Longitude +
                ", TEL='" + TEL + '\'' +
                ", Kategorie='" + Kategorie + '\'' +
                ", Midx=" + Midx +
                ", datatime='" + datatime + '\'' +
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




}
