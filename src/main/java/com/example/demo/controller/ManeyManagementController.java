package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		// 入金の合計
		int allInput = inputRepository.getAllInput();
		// 支出の合計
		int allOutput = outputRepository.getAllOutput();
		
		// 現在の予算
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
	
	@GetMapping("/detail")
	public String detailList(Model model) {
		// 入金履歴の取得
		List<Input> inputList = inputRepository.findAll();
		// 支出履歴の取得
		List<Output> outputList = outputRepository.findAll();
		
		// 入金の合計を取得
		int allInput = inputRepository.getAllInput();
		// 支出の合計を取得
		int allOutput = outputRepository.getAllOutput();
		
		model.addAttribute("inputList", inputList);
		model.addAttribute("outputList", outputList);
		model.addAttribute("allInput", allInput);
		model.addAttribute("allOutput", allOutput);
		
		return "/detail";
	}
	
	@GetMapping("/input/edit/{id}")
	public String inputEdit(@PathVariable Long id, Model model) {
		Input input = inputRepository.findById(id).get();
		model.addAttribute("input", input);
		
		return "/input-edit";
	}
	
	@PostMapping("/input/update/{id}")
	public String inputUpdate(@PathVariable Long id, @ModelAttribute Input input,
			RedirectAttributes redirectAttributes) {
		
		inputRepository.save(input);
		redirectAttributes.addFlashAttribute("message", "データを更新しました。");
		
		return "/home";
	}
	
	@GetMapping("/input/delete/{id}")
	public String inputDelete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		inputRepository.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "データを削除しました。");
		
		return "/home";
	}
}
