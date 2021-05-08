package com.halim.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/index")
	public String getIndex(Model model)
	{
		model.addAttribute("name", " halim zaaim");
		return "index";
	}

}
