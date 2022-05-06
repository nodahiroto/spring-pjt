package com.example.demo.service;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	// 全体の計算
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
	
	// 今月の計算
	public int countNowMonthBudget() {
		int totalNowAmmountMonth = 0;
		int totalNowMonthInput = 0;
		int totoalNowMonthOutput = 0;
		
		// 入金の合計
		if(null != inputRepository.getTotalNowMonthInput()) {
			totalNowMonthInput = inputRepository.getTotalNowMonthInput();
		}
		
		// 支出の合計
		if(null != outputRepository.getTotalNowMonthOutput()) {
			totoalNowMonthOutput = outputRepository.getTotalNowMonthOutput();
		}
		
		totalNowAmmountMonth = totalNowMonthInput - totoalNowMonthOutput;
		
		if(totalNowAmmountMonth < 0) {
			totoalNowMonthOutput = 0;
		}
		
		return totalNowAmmountMonth;
	}
	
	// 特定の月の計算
	public int countMonthBudget(int number) {
		// 入金の合計
		int totalMonthInput = inputRepository.getTotalMonthInput(number);
		// 支出の合計
		int totalMonthOutput = outputRepository.getTotalMonthOutput(number);
		
		int totalAmmountMonth = totalMonthInput - totalMonthOutput;
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
	
	// 自作バリデーション(1~12の数字かチェック)
	public boolean checkMonth(int number) {
		String srtNum = String.valueOf(number);
		
		Pattern pattern = Pattern.compile("^[1-9]|[1][0-2]+$");
		Matcher matcher = pattern.matcher(srtNum);
		
		boolean result = matcher.matches();
		return result;
	}
}
