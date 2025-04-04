package com.learn.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	@GetMapping("/customloginpage")
	public String showCustomLoginPage() {
		return "customloginpage";
	}
	
	@GetMapping("/managers")
	public String showManagersPage() {
		return "managerpage";
	}
	
	@GetMapping("/hrpage")
	public String showHRPage() {
		return "hrpage";
	}
	
	@GetMapping("/accessdenied")
	public String showAccessDenied() {
		return "noaccess";
	}
}
