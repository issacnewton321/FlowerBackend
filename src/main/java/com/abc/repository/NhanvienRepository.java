package com.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.entity.Nhanvien;

public interface NhanvienRepository extends JpaRepository<Nhanvien, String> {

}
