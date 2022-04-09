package com.example.demo.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.example.demo.model.Budget;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.repository.InputRepository;
import com.example.demo.repository.OutputRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BudgetSevice {

	private final BudgetRepository budgetRepository;
	private final InputRepository inputRepository;
	private final OutputRepository outputRepository;
	
	public Budget countBudget() {
		// 入金の合計
		int allInput = inputRepository.getAllInput();
		// 支出の合計
		int allOutput = outputRepository.getAllOutput();
		
		int totalAmmount = allInput - allOutput;
		Budget budget = new Budget();
		budget.setNowBudget(totalAmmount);
		budgetRepository.save(budget);
		
		return budget;
	}
	
	public int countMonthBudget() {
		// 入金の合計
		int totalMonthInput = inputRepository.getTotalMonthInput();
		// 支出の合計
		int totoalMonthOutput = outputRepository.getTotalMonthOutput();
		
		int totalAmmountMonth = totalMonthInput - totoalMonthOutput;
		return totalAmmountMonth;
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
	
	public int getMonth() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH) + 1;
		
		return month;
	}
}
