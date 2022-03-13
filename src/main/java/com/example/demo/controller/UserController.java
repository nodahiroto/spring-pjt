package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.AppUser;
import com.example.demo.model.Budget;
import com.example.demo.service.AppUserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

	private final AppUserService appUserService;
	
	@GetMapping("/")
	public String first (@ModelAttribute AppUser appUser, Authentication loginUser) {
		if(loginUser != null) {
			return "/home";
		}
		return "/register";
	}
	
	@GetMapping("/login")
	public String login(@ModelAttribute AppUser appUser) {
		return "/login";
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute AppUser appUser) {
		return "/register";
	}
	
	@PostMapping("/register")
	public String save(@Validated @ModelAttribute AppUser appUser, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("message", result);
			return "redirect:/register";
		}
		
		boolean RegistrationResult = appUserService.RegistrationUser(appUser);
		
		if(RegistrationResult) {
			Budget budget = new Budget();
			budget.setNowBudget(0);
			model.addAttribute("budget", budget);
			redirectAttributes.addFlashAttribute("success", "登録しました。");
			return "/home";
		} else {
			model.addAttribute("message", "登録に失敗しました。");
			return "redirect:/register";
		}
	}
}
