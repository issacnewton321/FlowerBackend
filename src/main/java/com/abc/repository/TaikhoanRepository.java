package com.abc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.abc.entity.Taikhoan;

@EnableJpaRepositories
public interface TaikhoanRepository extends JpaRepository<Taikhoan, Integer> {
	Taikhoan findByUsername(String username);
}
