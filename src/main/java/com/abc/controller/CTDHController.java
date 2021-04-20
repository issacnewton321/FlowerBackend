package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.CTDH;
import com.abc.repository.CTDHRepository;

@RestController
public class CTDHController {

	@Autowired
	CTDHRepository repo;

	@GetMapping("/ctdh/{madh}")
	public List<CTDH> getCTDH(@PathVariable("madh") String madh) {
		List<CTDH> list = repo.getCTDHByMadh(madh);
		return list;
	}

//	@PostMapping("/ctdh/{madh}")
//	public String postCTDH(@PathVariable ("madh") String madh) {
//		
//		List<CTDH> list = repo.postCTDHByMadh(madh);
//
//		
//	}

	@DeleteMapping("ctdh/{madh}")
	public String deleteCTDH(@PathVariable("madh") String madh) {

		try {
			repo.deleteCTDHByDonhang(madh);
		} catch (Exception e) {
			e.getMessage();
			return "false";
		}
		return "true";
	}
}
