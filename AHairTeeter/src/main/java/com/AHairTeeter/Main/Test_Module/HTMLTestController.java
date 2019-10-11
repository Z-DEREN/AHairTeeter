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
		int usernum = TestServiceImpl.GetSqlMain();
		if(usernum > 0) {
			model.addAttribute("test", "数据库连接成功");
		}
		model.addAttribute("Time", Tool.GetNewDateTime(2));
		return "/testhtml/test1";
	}
	
	
	@RequestMapping(value = "/test2", method = {RequestMethod.GET,RequestMethod.POST})
	public String TestSQL2(Model model) {
		int usernum = TestServiceImpl.GetSqlMain();
		model.addAttribute("Time", Tool.GetNewDateTime(2));
		//获取指定表DI码
		String test = TestServiceImpl.GetintZDInum("61", "String").toString();
		model.addAttribute("test", test);
		return "/testhtml/test1";
	}
	
	/**
	 * 获取IP数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/test3", method = {RequestMethod.GET,RequestMethod.POST})
	public String TestSQL3(Model model) {
		TestServiceImpl.GetSqlMain();
		model.addAttribute("Time", Tool.GetNewDateTime(2));
		int test = TestServiceImpl.SetIPPool();
		return "/testhtml/test1";
	}
	
	
	
	
}
