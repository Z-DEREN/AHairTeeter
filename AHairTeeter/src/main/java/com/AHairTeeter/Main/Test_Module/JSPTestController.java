package com.AHairTeeter.Main.Test_Module;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ��������Controller
 * 
 * @author ����
 *
 */
@Controller
@EnableAutoConfiguration
@RequestMapping(value = "jsptest")
public class JSPTestController {

	// log
	private static final Logger logger = LogManager.getLogger(JSPTestController.class.getName());

	@RequestMapping(value = "welcome")
	public String helloJsp(Model model) {
		System.out.println("����ʹ");
		model.addAttribute("laji", "������");
		return "welcome";
	}

}
