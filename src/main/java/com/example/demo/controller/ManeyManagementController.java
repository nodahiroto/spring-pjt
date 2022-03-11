package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ManeyManagementController {

	@PostMapping("/reset")
	public void reset() {
		// 予算のリセット処理
	}
}
