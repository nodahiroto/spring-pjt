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
	
	public void update(Long id, Input input) {
		
		input.setInputId(id);
		input.setInDate(input.getInDate());
		input.setInContent(input.getInContent());
		input.setInPrice(input.getInPrice());
		input.setUpdatedAt(LocalDateTime.now());
		inputRepository.save(input);
	}
}
