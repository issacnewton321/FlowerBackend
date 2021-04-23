package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.CTDH;
import com.abc.entity.Sanpham;
import com.abc.repository.CTDHRepository;

@RestController
public class CTDHController {

	@Autowired
	CTDHRepository repo;

	@GetMapping("/ctdh/{madh}")
	public List<CTDH> getListCTDH(){
		return repo.findAll();
	}


	@DeleteMapping("ctdh/{madh}")
	public ResponseEntity<String> deleteCTDH(@PathVariable("madh") String madh) {
		try {
			repo.deleteCTDHByDonhang(madh);
			return new ResponseEntity<String>("successed !!!" , HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
}
