package com.abc.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.apache.naming.java.javaURLContextFactory;
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

import com.abc.entity.CTDH;
import com.abc.entity.CTDH_ID;
import com.abc.entity.Danhmuc;
import com.abc.entity.Donhang;
import com.abc.entity.Khachhang;
import com.abc.entity.Sanpham;
import com.abc.model.Dathang;
import com.abc.repository.CTDHRepository;
import com.abc.repository.DonhangRepositoty;
import com.abc.repository.GiohangRepository;
import com.abc.repository.SanphamRepository;

@RestController
public class DonhangController {
	@Autowired
	DonhangRepositoty repo;
	
	@Autowired
	CTDHRepository ctRepo;
	
	@Autowired
	GiohangRepository ghRepo;
	
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
	
	@PostMapping("/donhang/{makh}")
	public ResponseEntity<String> insertDonhang(@Validated @RequestBody List<Dathang> listDH,@PathVariable("makh") String makh) {
		try {
			Donhang donhang = new Donhang();
			String madh = "DH" +  System.currentTimeMillis() % 10000000;
			float tongtien = 0;
			for(Dathang dh : listDH) {
				tongtien += dh.getDongia() * dh.getSoluong();
			}
			
			donhang.setMadh(madh);
			donhang.setNgaydat(new Date(System.currentTimeMillis()));
			donhang.setHinhthucthanhtoan(1);
			donhang.setTrangthai(0);
			Khachhang khachhang = new Khachhang();
			khachhang.setMakh(makh);
			donhang.setKhachhang(khachhang);
			donhang.setTongtien(tongtien);
			//save donhang
			try {
				repo.save(donhang);
				
				//save ctdh
				for(Dathang dh : listDH) {
					CTDH ctdh = new CTDH();
					CTDH_ID id = new CTDH_ID(madh,dh.getMasp());
					ctdh.setDonhang(donhang);
					ctdh.setId(id);
					Sanpham sanpham = new Sanpham();
					sanpham.setMasp(dh.getMasp());
					ctdh.setSanpham(sanpham);
					ctdh.setSoluong(dh.getSoluong());
					ctRepo.save(ctdh);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<String>("không thể thêm đơn hàng",HttpStatus.BAD_REQUEST);
			}
			try {
				ghRepo.deleteGiohangByMakh(makh);
				return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
			}
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
