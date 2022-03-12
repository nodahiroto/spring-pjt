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
	
	public Input payment(Input input) {
		// 予算に反映
		
		
		input.setCreatedAt(LocalDateTime.now());
		inputRepository.save(input);
		return input;
	}
}
