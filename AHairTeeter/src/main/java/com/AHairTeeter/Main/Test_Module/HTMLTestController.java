package com.AHairTeeter.Main.Test_Module;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.AHairTeeter.Main.Test_Module.ServiceImpl.TestServiceImpl;
import com.AHairTeeter.Tool.Tool;

@Controller
@RequestMapping("/htmltest")
public class HTMLTestController {
	
	
	@Autowired
	public TestServiceImpl TestServiceImpl;
	
	Tool Tool =new Tool();
	
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public String passParam(Model model) {
		model.addAttribute("Time", Tool.GetNewDateTime(2));
		return "test1";
	}
	
	
	@RequestMapping(value = "/test2", method = {RequestMethod.GET,RequestMethod.POST})
	public String TestSQL1(Map<String, Object> map) {
		TestServiceImpl.GetSqlMain();
		return null;
	}
	
	@RequestMapping(value = "/test3", method = {RequestMethod.GET,RequestMethod.POST})
	public void TestSQL2(Map<String, Object> map) {
		TestServiceImpl.GetSqlMain();
	}
	
	
	
	
	
}
