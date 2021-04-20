package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Giohang;
import com.abc.repository.GiohangRepository;

@RestController
public class GiohangController {
	@Autowired
	GiohangRepository repo;
	
	@GetMapping("/giohang/{makh}")
    public List<Giohang> getGiohang(@PathVariable("makh") String makh) {
		List<Giohang> list = repo.getGiohangByMakh(makh);
		return list;
    }
	
	@DeleteMapping("/giohang/{makh}")
	public String deleteGiohangOfKhachhang(@PathVariable("makh") String makh) {
		try {
			repo.deleteGiohangByMakh(makh);
		} catch (Exception e) {
			e.getMessage();
			return "false";
			// TODO: handle exception
		}
		return "true";
	}
	
	@DeleteMapping("/giohang/{makh}/{masp}")
	public String deleteGiohangById(@PathVariable("makh") String makh, @PathVariable("masp") String masp) {
		try {
			repo.deleteGiohangByMakhAndMasp(makh, masp);
		} catch (Exception e) {
			e.getMessage();
			return "false";
		}
		return "true";
	}
	
}
