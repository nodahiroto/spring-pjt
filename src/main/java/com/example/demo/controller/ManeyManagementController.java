package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Budget;
import com.example.demo.model.Input;
import com.example.demo.model.Output;
import com.example.demo.repository.InputRepository;
import com.example.demo.repository.OutputRepository;
import com.example.demo.service.BudgetSevice;
import com.example.demo.service.InputService;
import com.example.demo.service.OutputService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class ManeyManagementController {

	private final InputService inputService;
	private final OutputService outputService;
	private final BudgetSevice budgetService;
	private final InputRepository inputRepository;
	private final OutputRepository outputRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		int allInput = inputRepository.getAllInput();
		int allOutput = outputRepository.getAllOutput();
		
		Budget budget = budgetService.countBudget(allInput, allOutput);
		model.addAttribute("budgetRemain", budget.getNowBudget());
		return "/home";
	}
	
	@PostMapping("/reset")
	public String reset() {
		return null;
		// 予算のリセット処理
	}
	
	@GetMapping("/input")
	public String inputForm(@ModelAttribute Input input) {
		return "/input";
	}
	
	@PostMapping("/input")
	public String input(@Validated @ModelAttribute Input input, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "/redirect:input";
		}
		
		// 入金処理
		inputService.payment(input);
		redirectAttributes.addFlashAttribute("message", "入金しました。");
		
		return "/home";
	}
	
	@GetMapping("/output")
	public String outputForm(@ModelAttribute Output output) {
		return "/output";
	}
	
	@PostMapping("/output")
	public String output(@Validated @ModelAttribute Output output, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "/redirect:output";
		}
		
		// 支出処理
		outputService.expenditure(output);
		redirectAttributes.addFlashAttribute("message", "支出しました。");

		return "/home";
	}
}
