package com.example.demo.service;

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
}
