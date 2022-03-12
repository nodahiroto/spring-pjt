package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.model.Output;
import com.example.demo.repository.OutputRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OutputService {

	private final OutputRepository outputRepository;
	
	public Output expenditure(Output output) {
		// 予算に反映
		
		
		
		output.setCreatedAt(LocalDateTime.now());
		outputRepository.save(output);
		return output;
	}
}
