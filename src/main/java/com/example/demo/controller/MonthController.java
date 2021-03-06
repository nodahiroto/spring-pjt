package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.InputRepository;
import com.example.demo.repository.OutputRepository;
import com.example.demo.service.BudgetSevice;
import com.example.demo.service.InputService;
import com.example.demo.service.OutputService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MonthController {
	
	private final InputService inputService;
	private final OutputService outputService;
	private final InputRepository inputRepository;
	private final OutputRepository outputRepository;
	private final BudgetSevice budgetService;
	
	@GetMapping("/month")
	public String showMonth(@RequestParam("number") int number,
			@AuthenticationPrincipal User user, Model model) throws Exception {

		try {
			boolean result = budgetService.checkMonth(number);
			if (result) {
				model.addAttribute("monthInput", inputRepository.findMonthInput(number));
				model.addAttribute("monthOutput", outputRepository.findMonthOutput(number));
				
			} else {
				model.addAttribute("errorMonth", "1~12までの数値を入力してください。データがありません。");
				return "/detail";
			}
		} catch (Exception e) {
			model.addAttribute("errorMonth", "不正な値です");
			return "/detail";
		}
		
		try {
			model.addAttribute("TotalMonthInput", inputService.checkTotalMonthInput(number));
			model.addAttribute("TotalMonthOutput", outputService.checkTotalMonthOutput(number));
			model.addAttribute("totalAmmountMonth", "残高：" + budgetService.countMonthBudget(number));
		} catch (Exception e) {
			model.addAttribute("nullMonth", "データがありません。");
		}
		
		model.addAttribute("number", number + "月の詳細");
		
		model.addAttribute("today", budgetService.getToday());
		
		return "/month-detail";
	}

}
