package com.abc.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CTDH_ID implements Serializable {
	String madh;
	String masp;
	public String getMadh() {
		return madh;
	}
	public void setMadh(String madh) {
		this.madh = madh;
	}
	public String getMasp() {
		return masp;
	}
	public void setMasp(String masp) {
		this.masp = masp;
	}
	
}
