package com.abc.controller;

import java.util.List;
<<<<<<< HEAD
import java.util.Optional;
=======
>>>>>>> 854b748513beec41c1cef300627fa7932c4deab2

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> 854b748513beec41c1cef300627fa7932c4deab2
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
<<<<<<< HEAD
public class DanhmucController {
=======
@CrossOrigin
public class DanhmucController {

>>>>>>> 854b748513beec41c1cef300627fa7932c4deab2
	@Autowired
	DanhmucRepository repo;
	
	@GetMapping("/danhmuc")
<<<<<<< HEAD
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
=======
	public List<Danhmuc> getListDM(){
		return repo.findAll();
	}
	
	@PostMapping("/danhmuc")
	public ResponseEntity<String> insertDM(@Validated @RequestBody Danhmuc danhmuc){
		try {
			repo.save(danhmuc);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/danhmuc/{madm}")
	public ResponseEntity<String> deleteDM(@PathVariable("madm") String madm){
		try {
			repo.deleteById(madm);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	@PutMapping("/danhmuc")
	public ResponseEntity<String> updateDM(@Validated @RequestBody Danhmuc danhmuc){
		try {
			List<Danhmuc> listDM = repo.findAll();
			for(Danhmuc dm:listDM) {
				if(dm.getMadm().equalsIgnoreCase(danhmuc.getMadm())) {
					repo.save(danhmuc);
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
	
>>>>>>> 854b748513beec41c1cef300627fa7932c4deab2
}
