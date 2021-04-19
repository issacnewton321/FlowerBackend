package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Sanpham;
import com.abc.repository.SanphamRepository;

@RestController
public class SanphamController {
	
	@Autowired
	SanphamRepository repo;
	
	@GetMapping("/sanpham")
	public List<Sanpham> getListSP(){
		return repo.findAll();
	}
}
