package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;
import com.example.demo.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AppUserService {
	
	@Autowired
	private final AppUserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public boolean RegistrationUser(AppUser appUser) {
		
		if(userRepository.existsByEmail(appUser.getEmail()) == true) {
			return false;
		}
		
		appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
		if(appUser.isAdmin()) {
			appUser.setRole(Role.ADMIN.name());
		} else {
			appUser.setRole(Role.USER.name());
		}
		
		// データベースに反映
		userRepository.save(appUser);
		return true;
	}
	
	// ユーザーの削除
	public void delete(String email) {
		userRepository.deleteByEmail(email);
	}
}
