package com.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abc.entity.Giohang;
import com.abc.entity.Giohang_ID;

public interface GiohangRepository extends JpaRepository<Giohang, Giohang_ID>{
	
	@Query(nativeQuery = true, value = "select * from Giohang where makh = ?1")
	List<Giohang> getGiohangByMakh(String makh);
	
	@Query(nativeQuery = true, value = "delete from Giohang where makh = ?1")
	List<Giohang> deleteGiohangByMakh(String username);
	
	@Query(nativeQuery = true, value = "delete from Giohang where makh = ?1 and masp = ?2")
	List<Giohang> deleteGiohangByMakhAndMasp(String makh, String masp);
}
