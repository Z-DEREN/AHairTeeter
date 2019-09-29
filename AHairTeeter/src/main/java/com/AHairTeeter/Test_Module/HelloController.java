package com.AHairTeeter.Test_Module;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	@RequestMapping("/hello")
	public String helloJsp(Model model) {
		System.out.println("HelloController.helloJsp().hello=hello");
		model.addAttribute("hello", "ÄãºÃ");
		return "hello";
	}
}