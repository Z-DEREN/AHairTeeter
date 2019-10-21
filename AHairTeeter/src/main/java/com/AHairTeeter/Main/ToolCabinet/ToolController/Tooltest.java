package com.AHairTeeter.Main.ToolCabinet.ToolController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.AHairTeeter.Main.ToolCabinet.ToolServiceImpl.ToolServiceImpl;

import com.AHairTeeter.Tool.Tool;
@Controller
@RequestMapping("/AHairTeeter")
public class Tooltest {

	@Autowired	
	public ToolServiceImpl ToolServiceImpl;
	
	Tool Tool =new Tool();
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("Time", Tool.GetNewDateTime(2));
		return "/testhtml/test1";
	}
	
	
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String passParam(Model model) {
		model.addAttribute("Time", Tool.GetNewDateTime(2));
		return "/testhtml/test1";
	}
	
	
	@RequestMapping(value = "/test2", method = {RequestMethod.GET,RequestMethod.POST})
	public String TestSQL2(ModelAndView model) {
		model.addObject("test", "test2");
		return "/testhtml/test1";
	}
	
	/**
	 * 获取IP数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/test3", method = {RequestMethod.GET,RequestMethod.POST})
	public String TestSQL3(Model model) {
		ToolServiceImpl.ToolIPSave("61");
		return "/testhtml/test1";
	}
	
	
}
