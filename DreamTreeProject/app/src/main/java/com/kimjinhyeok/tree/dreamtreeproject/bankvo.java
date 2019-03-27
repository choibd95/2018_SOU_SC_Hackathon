package com.kimjinhyeok.tree.dreamtreeproject;

public class bankvo {


	int seq;//시퀀스
	int money;//금액
	String con;//사용처
	String intime;//등록일
	String div;//구분
	String del_bool;//삭제여부

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

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

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getDel_bool() {
		return del_bool;
	}

	public void setDel_bool(String del_bool) {
		this.del_bool = del_bool;
	}
}