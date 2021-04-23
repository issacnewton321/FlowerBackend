package com.abc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Danhmuc;
import com.abc.entity.Donhang;
import com.abc.entity.Sanpham;
import com.abc.repository.DonhangRepositoty;

@RestController
public class DonhangController {
	@Autowired
	DonhangRepositoty repo;
	
	@GetMapping("/donhang")
	public List<Donhang> getListDH(){
		return repo.findAll();
	}
	
	@GetMapping("/donhang/{makh}/{madh}")// ?username làm biến ảo (không trùng với các method get khác) có thể nhập sai trường username :)
	public Optional<Donhang> getIdDonhangByMakh(@PathVariable("madh") String madh) {
		return repo.findById(madh);
	}
	
	@GetMapping("/donhang/{manv}/{madh}")
	public Optional<Donhang> getIdDonhangByManv(@PathVariable("madh") String madh) {
		return repo.findById(madh);
	}
	
	@GetMapping("/donhang/{makh}")
	public List<Donhang> getDonhangByMakh(@PathVariable("makh") String makh){
		return repo.getDonhangByMakh(makh);
	}
	
	@GetMapping("/donhang/{makv}")
	public List<Donhang> getDonhangByManv(@PathVariable("manv") String manv){
		return repo.getDonhangByManv(manv);
	}
	
	@PutMapping("/donhang")
	public ResponseEntity<String> updateDonhang(@Validated @RequestBody Donhang donhang) {

		try {
			List<Donhang> listDH = repo.findAll();
			for(Donhang dh : listDH) {
				if (dh.getMadh().equalsIgnoreCase(donhang.getMadh())) {
					repo.save(donhang);
					return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/donhang")
	public ResponseEntity<String> insertDonhang(@Validated @RequestBody Donhang dh) {
		try {
			repo.save(dh);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/donhang/{madh}")
	public ResponseEntity<String> deleteIdDonhang(@PathVariable("madh") String madh) {
		try {
			repo.deleteById(madh);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
}
