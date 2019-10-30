package com.AHairTeeter.Main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.AHairTeeter.Main.IPpool.DaoServiceImpl.InternetProtocolDaoServiceImpl;
import com.AHairTeeter.Tool.Route;
import com.AHairTeeter.Tool.Tool;
@Controller
@RequestMapping("/Tooltest")
public class ToolCabinetController {
	@Autowired	
	public InternetProtocolDaoServiceImpl InternetProtocolDaoServiceImpl;
	
	Tool Tool =new Tool();
	
	/**
	 * 获取IP数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toolIP61", method = {RequestMethod.GET,RequestMethod.POST})
	public String TestSQL3(Model model) {
		InternetProtocolDaoServiceImpl.ToolIPSave("61");
		return "redirect:/AHairTeeter/navigation";
	}
	
	Route rou = new Route();
	
	/**
	 * 获取IP数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/TestElement", method = {RequestMethod.GET,RequestMethod.POST})
	public String TestElement(Model model) {
		System.out.println(rou.getWarehouseCookieName());
		return "redirect:/AHairTeeter/navigation";
	}
	
	
	
}
