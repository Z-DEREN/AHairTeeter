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
@RequestMapping("/Tooltest")
public class Tooltest {

	@Autowired	
	public ToolServiceImpl ToolServiceImpl;
	
	Tool Tool =new Tool();
	
	/**
	 * 获取IP数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toolIP61", method = {RequestMethod.GET,RequestMethod.POST})
	public String TestSQL3(Model model) {
		ToolServiceImpl.ToolIPSave("61");
		return "/main/HOME/test1";
	}
	
	
}
