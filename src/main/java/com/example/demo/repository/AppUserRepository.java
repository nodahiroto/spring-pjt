package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByEmail(String email);
	
	boolean existsByEmail(String email);
}
