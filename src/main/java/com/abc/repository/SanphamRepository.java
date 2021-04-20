package com.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abc.entity.Sanpham;

public interface SanphamRepository extends JpaRepository<Sanpham, String>{
	
	@Query(nativeQuery = true, value = "select * from sanpham where tensp= ?1")
	List<Sanpham> getSanphamByTensp(String tensp);
	
	@Query(nativeQuery = true, value ="selcet * from sanpham where madm=?1")
	List<Sanpham> getSanphamByMadm(String madm);

}
