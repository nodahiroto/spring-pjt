package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.demo.model.Output;
import com.example.demo.model.beans.TotalMonth;
import com.example.demo.repository.OutputRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OutputService {

	private final OutputRepository outputRepository;
	
	public void expenditure(Output output) {

		output.setCreatedAt(LocalDateTime.now());
		outputRepository.save(output);

	}
	
	public void update(Output output) {
		
		output.setUpdatedAt(LocalDateTime.now());
		outputRepository.save(output);
	}
	
	public TotalMonth findSumMonthOutput() {

		ArrayList<Integer> monthList = new ArrayList<>();
		
		for(int i = 1; i < 13; i++) {
			int result = outputRepository.checkMonth(i);
			
			if(result == 0) {
				monthList.add(0);
			} else {
				monthList.add(outputRepository.getTotalMonthOutput(i));
			}
		}
		
		TotalMonth totalMonth = new TotalMonth();
		totalMonth.setJanuary(monthList.get(0));
		totalMonth.setFebruary(monthList.get(1));
		totalMonth.setMarch(monthList.get(2));
		totalMonth.setApril(monthList.get(3));
		totalMonth.setMay(monthList.get(4));
		totalMonth.setJune(monthList.get(5));
		totalMonth.setJuly(monthList.get(6));
		totalMonth.setAugust(monthList.get(7));
		totalMonth.setSeptember(monthList.get(8));
		totalMonth.setOctober(monthList.get(9));
		totalMonth.setNovember(monthList.get(10));
		totalMonth.setDecember(monthList.get(11));
		
		return totalMonth;
	}
	
	public boolean checkNowMonthOutput() {
		
		if (null == outputRepository.getNowMonthOutput()) {
			return false;
		}
		
		return true;
	}
	
	public Integer checkTotalNowMonthOutput() {
		
		if (null != outputRepository.getTotalNowMonthOutput()) {
			
			return outputRepository.getTotalNowMonthOutput();
		} else {
		
			return 0;
		}
	}
	
	public Integer checkTotalMonthOutput(int number) {
		
		if (null != outputRepository.getTotalMonthOutput(number)) {
			
			return outputRepository.getTotalMonthOutput(number);
		} else {
			
			return 0;
		}
	}
}
