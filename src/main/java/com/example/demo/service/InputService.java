package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.model.Input;
import com.example.demo.repository.InputRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InputService {

	private final InputRepository inputRepository;
	
	public void payment(Input input) {
		
		input.setCreatedAt(LocalDateTime.now());
		inputRepository.save(input);
	}
	
	public void update(Input input) {
		
		input.setUpdatedAt(LocalDateTime.now());
		inputRepository.save(input);
	}

}
