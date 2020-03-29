package com.zdr.ahairteeter.demo.Main.Controller;

import com.zdr.ahairteeter.demo.Main.SerIPpool.DaoServiceImpl.InternetProtocolDaoServiceImpl;
import com.zdr.ahairteeter.demo.Tool.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ResponseBody
@RequestMapping("/Tooltest")
public class ToolCabinetController {

	@Autowired	
	public InternetProtocolDaoServiceImpl InternetProtocolDaoServiceImpl;
	@Autowired
	private Route rou ;
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
