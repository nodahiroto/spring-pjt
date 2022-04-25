package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.AppUser;
import com.example.demo.model.Budget;
import com.example.demo.model.Input;
import com.example.demo.model.Output;
import com.example.demo.model.beans.TotalMonth;
import com.example.demo.repository.AppUserRepository;
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
	private final AppUserRepository appUserRepository;
	private final InputRepository inputRepository;
	private final OutputRepository outputRepository;
	
	@GetMapping("/home")
	public String home(@AuthenticationPrincipal User user, Model model) {

		AppUser appuser = appUserRepository.findByEmail(user.getUsername());

		// 現在の予算
		Budget budget = budgetService.countBudget();
		model.addAttribute("appuser", appUserRepository.findById(appuser.getUserId()));
		model.addAttribute("budgetRemain", budget.getNowBudget());
		
		// 現在の日付を取得
		model.addAttribute("today", budgetService.getToday());
		
		// 今月の入金データ一覧を取得
		model.addAttribute("nowMonthInput", inputRepository.findNowMonthInput());
		// 今月の入金合計額
		model.addAttribute("totalNowMonthInput", inputRepository.getTotalNowMonthInput());
		
		// 今月の支出データ一覧を取得
		model.addAttribute("nowMonthOutput", outputRepository.findNowMonthOutput());
		// 今月の支出金額合計
		model.addAttribute("totalNowMonthOutput", outputRepository.getTotalNowMonthOutput());
		
		// 今月の予算
		model.addAttribute("totalNowAmmountMonth", budgetService.countNowMonthBudget());
		
		return "/home";
	}
	
	@GetMapping("/input")
	public String inputForm(@ModelAttribute Input input, @AuthenticationPrincipal User user, Model model) {
		
		AppUser appuser = appUserRepository.findByEmail(user.getUsername());
		model.addAttribute("appuser", appUserRepository.findById(appuser.getUserId()));
		return "/input";
	}
	
	@PostMapping("/input")
	public String input(@Validated @ModelAttribute Input input, BindingResult result,
			@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			
			return "/input";
		}
		
		// 入金処理
		inputService.payment(input);
		redirectAttributes.addFlashAttribute("message", "入金記録をしました。");
		
		return "redirect:/home";
	}
	
	@GetMapping("/output")
	public String outputForm(@ModelAttribute Output output, @AuthenticationPrincipal User user, Model model) {
		
		AppUser appuser = appUserRepository.findByEmail(user.getUsername());
		model.addAttribute("appuser", appUserRepository.findById(appuser.getUserId()));
		return "/output";
	}
	
	@PostMapping("/output")
	public String output(@Validated @ModelAttribute Output output, BindingResult result,
			@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {

			return "/output";
		}
		
		// 支出処理
		outputService.expenditure(output);
		redirectAttributes.addFlashAttribute("message", "支出記録をしました。");

		return "redirect:/home";
	}
	
	@GetMapping("/detail")
	public String detailList(@RequestParam(name = "sort", defaultValue = "1") int sort, @AuthenticationPrincipal User user, Model model) {
		
		AppUser appuser = appUserRepository.findByEmail(user.getUsername());
		
		// 今日の日付を取得
		model.addAttribute("today", budgetService.getToday());
		
		if (sort == 1) {
			// 入金履歴の取得(履歴順)
			List<Input> inputList = inputRepository.findAll();
			// 支出履歴の取得(履歴順)
			List<Output> outputList = outputRepository.findAll();
			model.addAttribute("inputList", inputList);
			model.addAttribute("outputList", outputList);
		}

		if (sort == 2) {
			// 入金履歴の取得(日付 昇順)
			List<Input> inputListUp = inputRepository.findInputUp();
			model.addAttribute("inputList", inputListUp);
			// 支出履歴の取得(日付 昇順)
			List<Output> outputListUp = outputRepository.findOutputUp();
			model.addAttribute("outputList", outputListUp);

		}
		
		if (sort == 3) {
			// 入金履歴の取得(日付 降順)
			List<Input> inputListDown = inputRepository.findInputDown();
			model.addAttribute("inputList", inputListDown);
			// 支出履歴の取得(日付 降順)
			List<Output> outputListDown = outputRepository.findOutputDown();
			model.addAttribute("outputList", outputListDown);

		} 
		
		// 入金の合計を取得
		int allInput = inputRepository.getAllInput();
		// 支出の合計を取得
		int allOutput = outputRepository.getAllOutput();
		model.addAttribute("allInput", allInput);
		model.addAttribute("allOutput", allOutput);
		
		model.addAttribute("appuser", appUserRepository.findById(appuser.getUserId()));
		
		// 月別の支出金額を取得
		TotalMonth totalMonth = outputService.findSumMonthOutput();
		
		model.addAttribute("January", totalMonth.getJanuary());
		model.addAttribute("February", totalMonth.getFebruary());
		model.addAttribute("March", totalMonth.getMarch());
		model.addAttribute("April", totalMonth.getApril());
		model.addAttribute("May", totalMonth.getMay());
		model.addAttribute("June", totalMonth.getJune());
		model.addAttribute("July", totalMonth.getJuly());
		model.addAttribute("August", totalMonth.getAugust());
		model.addAttribute("September", totalMonth.getSeptember());
		model.addAttribute("October", totalMonth.getOctober());
		model.addAttribute("November", totalMonth.getNovember());
		model.addAttribute("December", totalMonth.getDecember());
		
		return "/detail";
	}
	
	@GetMapping("/input/edit/{id}")
	public String inputEdit(@PathVariable Long id, @AuthenticationPrincipal User user, Model model) {
		
		AppUser appuser = appUserRepository.findByEmail(user.getUsername());
		
		Input input = inputRepository.findById(id).get();
		model.addAttribute("input", input);
		model.addAttribute("appuser", appUserRepository.findById(appuser.getUserId()));
		
		return "/input-edit";
	}
	
	@PostMapping("/input/update/{id}")
	public String inputUpdate(@Validated @ModelAttribute Input input, BindingResult result,
			@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {

			return "/input-edit";
		}
		
		inputService.update(input);
		redirectAttributes.addFlashAttribute("message", "データを更新しました。");
		
		return "redirect:/home";
	}
	
	@GetMapping("/input/delete/{id}")
	public String inputDelete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		inputRepository.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "データを削除しました。");
		
		return "redirect:/home";
	}
	
	@GetMapping("/output/edit/{id}")
	public String outputEdit(@PathVariable Long id, @AuthenticationPrincipal User user, Model model) {
		
		AppUser appuser = appUserRepository.findByEmail(user.getUsername());
		
		Output output = outputRepository.findById(id).get();
		model.addAttribute("output", output);
		model.addAttribute("appuser", appUserRepository.findById(appuser.getUserId()));
		
		return "/output-edit";
	}
	
	@PostMapping("/output/update/{id}")
	public String outputUpdate(@Validated @ModelAttribute Output output, BindingResult result,
			@AuthenticationPrincipal User user, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			
			return "/output-edit";
		}
		
		outputService.update(output);
		redirectAttributes.addFlashAttribute("message", "データを更新しました。");
		
		return "redirect:/home";
	}
	
	@GetMapping("/output/delete/{id}")
	public String outputDelete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		outputRepository.deleteById(id);
		redirectAttributes.addFlashAttribute("message", "データを削除しました。");
		
		return "redirect:/home";
	}
}
