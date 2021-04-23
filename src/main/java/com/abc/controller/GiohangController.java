package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Danhmuc;
import com.abc.entity.Giohang;
import com.abc.repository.GiohangRepository;

@RestController
public class GiohangController {
	@Autowired
	GiohangRepository repo;
	
	@GetMapping("/giohang/{makh}")
	public List<Giohang> getListGH(){
		return repo.findAll();
	}
	
	@DeleteMapping("/giohang/{makh}")
	public ResponseEntity<String> deleteGiohangOfKhachhang(@PathVariable("makh") String makh) {
		try {
			repo.deleteGiohangByMakh(makh);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/giohang/{makh}/{masp}")
	public ResponseEntity<String> deleteGiohangById(@PathVariable("makh") String makh, @PathVariable("masp") String masp) {
		try {
			repo.deleteGiohangByMakhAndMasp(makh, masp);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	
}
