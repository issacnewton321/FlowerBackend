package com.abc.controller;

import java.util.List;

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

import com.abc.entity.Sanpham;
import com.abc.repository.SanphamRepository;

@RestController
@CrossOrigin
public class SanphamController {
	
	@Autowired
	SanphamRepository repo;
	
	@GetMapping("/sanpham")
	public ResponseEntity<List<Sanpham>> all() {
		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}

	@PostMapping("/sanpham")
	public String postSanpham(@Validated @RequestBody Sanpham sp) {
		List<Sanpham> sanpham = repo.findAll();
		for (Sanpham s : sanpham) {
			if (s.getMasp().equalsIgnoreCase(sp.getMasp())) {
				return "false";
			}
		}
		repo.save(sp);
		return "true";
	}

	@DeleteMapping("/sanpham/{masp}")
	public String deleteIdSanpham(@PathVariable String masp) {
		try {
			repo.deleteById(masp);
		} catch (Exception e) {
			e.getMessage();
			return "false";
		}
		return "true";
	}

	@PutMapping("/sanpham")
	public String putSanpham(@Validated @RequestBody Sanpham sp) {
		try {
			repo.save(sp);
		} catch (Exception ex) {
			ex.getMessage();
			return "false";
		}
		return "true";
	}
	
	@GetMapping("/sanpham/{tensp}")
	public List<Sanpham> getTenSanpham(@PathVariable("tensp") String tensp){
		return repo.getSanphamByTensp(tensp);
	}
	
	@GetMapping("sanpham/{madm}")
	public List<Sanpham> getSanphamByMadm(@PathVariable("madm") String madm){
		return repo.getSanphamByMadm(madm);
	}
	@PostMapping("/sanpham")
	public ResponseEntity<String> insertSanpham(@Validated @RequestBody Sanpham sanpham){
		try {
			repo.save(sanpham);
			return new ResponseEntity<String>("successed !!!" , HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/sanpham/{masp}")
	public ResponseEntity<String> deleteSanpham(@PathVariable("masp") String masp){
		try {
			repo.deleteById(masp);
			return new ResponseEntity<String>("successed !!!" , HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
	@PutMapping("/sanpham")
	public ResponseEntity<String> updateSanpham(@Validated @RequestBody Sanpham sanpham){
		try {
			List<Sanpham> listSP = repo.findAll();
			for(Sanpham sp:listSP) {
				if(sp.getMasp().equalsIgnoreCase(sanpham.getMasp())) {
					repo.save(sanpham);
					return new ResponseEntity<String>("Successed" , HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
	
}
