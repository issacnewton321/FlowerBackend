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

import com.abc.entity.Binhluan;
import com.abc.repository.BinhluanRepository;

@RestController
public class BinhluanController {
	@Autowired
	BinhluanRepository repo;
	
	@GetMapping("/binhluan")
    public ResponseEntity<List<Binhluan>> getBinhluan() {
        return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
    }
	
	@GetMapping("/binhluan/{id}")
	public Optional<Binhluan> getIdBinhluan(@PathVariable("id") int id) {
		return repo.findById(id);
	}
	
	@PostMapping("/binhluan")
	public String postBinhluan(@Validated @RequestBody Binhluan bl) {
		
		List<Binhluan> listBl = repo.findAll();
		for (Binhluan bl1 : listBl) {
			if (bl1.getId() == bl.getId()) {
				return "false";
			}
		}
		repo.save(bl);
		return "true";
	}
	
	@PutMapping("/binhluan")
	public String putBinhluan(@Validated @RequestBody Binhluan bl) {
		try {
			repo.save(bl);
		} catch (Exception ex) {
			ex.getMessage();
			return "false";
		}
		return "true";
	}
	
	@DeleteMapping("/binhluan/{id}")
	public String deleteIdBinhluan(@PathVariable("id") int id) {
		try {
			repo.deleteById(id);
		} catch (Exception e) {
			e.getMessage();
			return "false";
			// TODO: handle exception
		}
		return "true";
	}
}
