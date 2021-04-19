package com.abc.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Taikhoan {
	@Id
	int matk;
	String username;
	String password;
	
	@ManyToOne
	@JoinColumn(name = "maquyen")
	Quyen quyen;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<Khachhang> listKH;
	
	@JsonIgnore
	@OneToMany(mappedBy = "taikhoan")
	List<Nhanvien> listNV;

	public int getMatk() {
		return matk;
	}

	public void setMatk(int matk) {
		this.matk = matk;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Quyen getQuyen() {
		return quyen;
	}

	public void setQuyen(Quyen quyen) {
		this.quyen = quyen;
	}

	public List<Khachhang> getListKH() {
		return listKH;
	}

	public void setListKH(List<Khachhang> listKH) {
		this.listKH = listKH;
	}

	public List<Nhanvien> getListNV() {
		return listNV;
	}

	public void setListNV(List<Nhanvien> listNV) {
		this.listNV = listNV;
	}
	
	
}