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
import com.abc.entity.Nhanvien;
import com.abc.entity.Sanpham;
import com.abc.repository.NhanvienRepository;

@RestController
public class NhanvienController {
	@Autowired
	NhanvienRepository repo;
	
	@GetMapping("/nhanvien")
	public List<Nhanvien> getListNV(){
		return repo.findAll();
	}
	
	@GetMapping("/nhanvien/{mand}")
	public Optional<Nhanvien> getIdNhanvien(@PathVariable("mand") String mand) {
		return repo.findById(mand);
	}
	
	@PutMapping("/nhanvien")
	public ResponseEntity<String> updateNhanvien(@Validated @RequestBody Nhanvien nhanvien) {

		try {
			List<Nhanvien> listNV = repo.findAll();
			for(Nhanvien nv : listNV) {
				if(nv.getManv().equalsIgnoreCase(nhanvien.getManv())) {
					repo.save(nhanvien);
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
	
	@PostMapping("/nhanvien")
	public ResponseEntity<String> insertNhanvien(@Validated @RequestBody Nhanvien nv) {
		try {
			repo.save(nv);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/nhanvien/{mand}")
	public ResponseEntity<String> deleteIdNhanvien(@PathVariable("mand") String mand) {
		try {
			repo.deleteById(mand);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
}
