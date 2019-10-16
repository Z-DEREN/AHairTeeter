package com.AHairTeeter.Main.ToolCabinet.ToolController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.AHairTeeter.Main.ToolCabinet.ToolServiceImpl.ToolServiceImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.ThreadExecutionMethod.ThExeMet;

@Controller
@RequestMapping("/ToolThread")
public class ToolThread {
	@Autowired	
	public ToolServiceImpl ToolServiceImpl;
	
	Tool Tool =new Tool();
	
	ThExeMet ThExeMet = new ThExeMet();
	
	
	@RequestMapping(value = "/Testlist", method = RequestMethod.GET)
	public String Testlist(Model model) {
		model.addAttribute("text", "执行多线程Testlist");
		ThExeMet.middleman(0);
		return "/testhtml/test1";
	}
	
	@RequestMapping(value = "/Moeimg", method = RequestMethod.GET)
	public String Moeimg(Model model) {
		model.addAttribute("text", "执行多线程Moeimg");
		ThExeMet.middleman(2);
		return "/testhtml/test1";
	}
	
	@RequestMapping(value = "/SssGif", method = RequestMethod.GET)
	public String SssGif(Model model) {
		model.addAttribute("text", "执行多线程SssGif");
		ThExeMet.middleman(1);
		return "/testhtml/test1";
	}
	
	
	
	
	
	
	
}
