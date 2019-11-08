package com.AHairTeeter.Main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AHairTeeter.Tool.Tool;
@Controller
@ResponseBody
@RequestMapping("/AHairTeeter")
public class HOMEController {
	Tool Tool =new Tool();
	
	/**
	 * Ö÷Ò³µ¼º½Ò³
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/navigation", method = RequestMethod.GET)
	public Object home(ModelAndView model) {
		model.addObject("Time", Tool.GetNewDateTime(2));
		model.setViewName("main/HOME/navigation");
		return model;
	}
}

