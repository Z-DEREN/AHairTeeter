package com.AHairTeeter.Main.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AHairTeeter.Main.ThreadExecutionMethod.ThreadServiceImpl;

import com.AHairTeeter.Main.ToolCabinet.ToolServiceImpl.ToolServiceImpl;
import com.AHairTeeter.Tool.Tool;

@Controller
@ResponseBody
@RequestMapping("/Thread")
public class ToolThreadController {
	@Autowired	
	public ToolServiceImpl ToolServiceImpl;
	
	@Autowired	
	public ThreadServiceImpl ThreadServiceImpl;
	
	Tool Tool =new Tool();
	
	@RequestMapping(value = "/Testlist", method = RequestMethod.GET)
	public Object Testlist(ModelAndView model) {
		model.addObject("text", "执行多线程Testlist");
		ThreadServiceImpl.middleman(0);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}
	
	@RequestMapping(value = "/Moeimg", method = RequestMethod.GET)
	public Object Moeimg(ModelAndView model) {
		model.addObject("text", "执行多线程Moeimg");
		ThreadServiceImpl.middleman(21);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}
	
	@RequestMapping(value = "/SssGif", method = RequestMethod.GET)
	public Object SssGif(ModelAndView model) {
		model.addObject("text", "执行多线程SssGif");
		ThreadServiceImpl.middleman(20);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}
	
	@RequestMapping(value = "/Xvideos", method = RequestMethod.GET)
	public Object Xvideos(ModelAndView model) {
		model.addObject("text", "执行多线程Xvideos");
		ThreadServiceImpl.middleman(22);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}
	
	@RequestMapping(value = "/Kr36", method = RequestMethod.GET)
	public Object Kr36(ModelAndView model) {
		model.addObject("text", "执行多线程Kr36");
		ThreadServiceImpl.middleman(24);
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
	}
	
	
	
	
	
}
