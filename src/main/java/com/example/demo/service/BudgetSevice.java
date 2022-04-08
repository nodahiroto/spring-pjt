package com.example.demo.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.example.demo.model.Budget;
import com.example.demo.repository.BudgetRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BudgetSevice {

	private final BudgetRepository budgetRepository;
	
	public Budget countBudget(int allInput, int allOutput) {
		int totalAmmount = allInput - allOutput;
		Budget budget = new Budget();
		budget.setNowBudget(totalAmmount);
		budgetRepository.save(budget);
		
		return budget;
	}
	
	public String getToday() {
		
		String[] week_name = {"日", "月", "火", "水", "木", "金", "土"};
		
		Calendar calendar = Calendar.getInstance();
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		
		String todaysDate = year + "年" + month + "月" + day + "日" + "(" + week_name[week] + ")";
		return todaysDate;
	}
}
