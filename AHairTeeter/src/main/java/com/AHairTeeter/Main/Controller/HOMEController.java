package com.AHairTeeter.Main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.AHairTeeter.Tool.Tool;
@Controller
@RequestMapping("/AHairTeeter")
public class HOMEController {
	Tool Tool =new Tool();
	
	/**
	 * ��ҳ����ҳ
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/navigation", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("Time", Tool.GetNewDateTime(2));
		return "/main/HOME/navigation";
	}
}
