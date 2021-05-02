package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Khachhang;
import com.abc.repository.KhachhangRepository;
import com.abc.repository.TaikhoanRepository;

@RestController
@CrossOrigin
public class KhachhangController {
	
	@Autowired
	KhachhangRepository repo;
	
	@Autowired
	TaikhoanRepository tkRepo;
	
	@GetMapping("/khachhang/{username}")
	public Khachhang getKhachHang(@PathVariable("username") String username) {
		return repo.getKhachHangByUsername(username); 
	}
	
	@GetMapping("/khachhang")
	public List<Khachhang> getListKH(){
		return repo.findAll();
	}
	
	@DeleteMapping("/khachhang/{matk}")
	public ResponseEntity<Boolean> deleteKH(@PathVariable("matk") int matk){
		try {
			repo.deleteKhachHangByMatk(matk);
			tkRepo.deleteById(matk);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
			
		}
	}
}
