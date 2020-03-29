package com.zdr.ahairteeter.demo.Main.Controller;

import com.zdr.ahairteeter.demo.Tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ResponseBody
@RequestMapping("/AHairTeeter")
public class HOMEController {
	@Autowired
	private Tool tool;
	/**
	 * 主页导航页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/navigation", method = RequestMethod.GET)
	public Object home(ModelAndView model) {
		model.addObject("Time", tool.GetNewDateTime(2));
		model.setViewName("main/HOME/navigation");
		return model;
	}


	@RequestMapping(value = "/cangku", method = RequestMethod.GET)
	public Object cabgkyu(ModelAndView model) {
		model.addObject("Time", tool.GetNewDateTime(2));
		model.setViewName("main/cangku/encryption/cangku");
		return model;
	}




}

