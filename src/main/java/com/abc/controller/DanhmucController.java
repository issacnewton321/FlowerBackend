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
import com.abc.repository.DanhmucRepository;

@RestController
public class DanhmucController {
	@Autowired
	DanhmucRepository repo;
	
	@GetMapping("/danhmuc")
	public ResponseEntity<List<Danhmuc>> all() {
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}

	@PostMapping("/danhmuc")
	public String postDanhmuc(@Validated @RequestBody Danhmuc dm) {
		List<Danhmuc> danhmuc = repo.findAll();
		for (Danhmuc d: danhmuc) {
			if (d.getMadm().equalsIgnoreCase(d.getMadm())) {
				return "false";
			}
		}
		repo.save(dm);
		return "true";
	}

	@DeleteMapping("/danhmuc/{madm}")
	public String deleteIdKhachhang(@PathVariable String madm) {
		try {
			repo.deleteById(madm);
		} catch (Exception e) {
			e.getMessage();
			return "false";
		}
		return "true";
	}

	@PutMapping("/danhmuc")
	public String putDanhmuc(@Validated @RequestBody Danhmuc dm) {
		try {
			repo.save(dm);
		} catch (Exception ex) {
			ex.getMessage();
			return "false";
		}
		return "true";
	}

	@GetMapping("/danhmuc/{madm}")
	public Optional<Danhmuc> getIdDanhmuc(@PathVariable String madm) {
		return repo.findById(madm);
	}
}
