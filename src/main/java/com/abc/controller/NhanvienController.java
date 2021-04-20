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

import com.abc.entity.Nhanvien;
import com.abc.repository.NhanvienRepository;

@RestController
public class NhanvienController {
	@Autowired
	NhanvienRepository repo;
	
	@GetMapping("/nhanvien")
    public ResponseEntity<List<Nhanvien>> getNhanvien() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }
	
	@GetMapping("/nhanvien/{mand}")
	public Optional<Nhanvien> getIdNhanvien(@PathVariable("mand") String mand) {
		return repo.findById(mand);
	}
	
	@PostMapping("/nhanvien")
	public String postNhanvien(@Validated @RequestBody Nhanvien nv) {
		
		List<Nhanvien> listNv = repo.findAll();
		for (Nhanvien nv1 : listNv) {
			if (nv1.getManv().equalsIgnoreCase(nv.getManv())) {
				return "false";
			}
		}
		repo.save(nv);
		return "true";
	}
	
	@PutMapping("/nhanvien")
	public String putNhanvien(@Validated @RequestBody Nhanvien nv) {
		try {
			repo.save(nv);
		} catch (Exception ex) {
			ex.getMessage();
			return "false";
		}
		return "true";
	}
	
	@DeleteMapping("/nhanvien/{mand}")
	public String deleteIdNhanvien(@PathVariable("mand") String mand) {
		try {
			repo.deleteById(mand);
		} catch (Exception e) {
			e.getMessage();
			return "false";
			// TODO: handle exception
		}
		return "true";
	}
}
