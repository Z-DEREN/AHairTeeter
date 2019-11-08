package com.AHairTeeter.Main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AHairTeeter.Main.IPpool.DaoServiceImpl.InternetProtocolDaoServiceImpl;
import com.AHairTeeter.Tool.Route;
import com.AHairTeeter.Tool.Tool;
@Controller
@ResponseBody
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
	public Object TestSQL3(ModelAndView model) {
		InternetProtocolDaoServiceImpl.ToolIPSave("61");
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}
	
	Route rou = new Route();
	
	/**
	 * 获取IP数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/TestElement", method = {RequestMethod.GET,RequestMethod.POST})
	public Object TestElement(ModelAndView model) {
		model.setViewName("redirect:/AHairTeeter/navigation");
		System.out.println(rou.getWarehouseCookieName());
		return model;
	}
	
	
	
}
