package com.kimjinhyeok.tree.dreamtreeproject;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class reviewvo
{

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getMidx() {
        return Midx;
    }

    public void setMidx(int midx) {
        Midx = midx;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getReviewidx() {
        return reviewidx;
    }

    public void setReviewidx(int reviewidx) {
        this.reviewidx = reviewidx;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "reviewvo{" +
                "Midx=" + Midx +
                ", img='" + img + '\'' +
                ", id='" + id + '\'' +
                ", reviewidx=" + reviewidx +
                ", nickname='" + nickname + '\'' +
                ", password=" + password +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    int Midx;
    String img;
    String id;
    int reviewidx;
    String nickname;
    int password;
    String text;
    String time;

}
