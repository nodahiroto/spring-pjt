package com.example.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.AppUser;
import com.example.demo.repository.AppUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AppUserService {
	
	private final AppUserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public boolean RegistrationUser(AppUser appUser) {
		
		if(userRepository.existsByEmail(appUser.getEmail()) == true) {
			return false;
		}
		
//		if(passwordEncoder) {
//			
//		}
		userRepository.save(appUser);
		return true;
		
	}
}
