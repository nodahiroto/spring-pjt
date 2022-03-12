package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.Input;
import com.example.demo.model.Output;
import com.example.demo.service.InputService;
import com.example.demo.service.OutputService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class ManeyManagementController {

	private final InputService inputService;
	private final OutputService outputService;
	
	@PostMapping("/reset")
	public String reset() {
		return null;
		// 予算のリセット処理
	}
	
	@PostMapping("/input")
	public String input(@Validated @ModelAttribute Input input, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "/redirect:home";
		}
		// 入金処理
		inputService.payment(input);
		redirectAttributes.addFlashAttribute("message", "入金しました。");
		return "/redirect:home";
	}
	
	@PostMapping("/output")
	public String output(@Validated @ModelAttribute Output output, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			System.out.println(result);
			return "/redirect:home";
		}
		// 支出処理
		outputService.expenditure(output);
		redirectAttributes.addFlashAttribute("message", "支出しました。");
		return "/redirect:home";
	}
}
