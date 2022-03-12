package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByEmail(String email);
	
	// 指定のメールアドレスが存在するか
	boolean existsByEmail(String email);
	
	public AppUser deleteByEmail(String email);
}
