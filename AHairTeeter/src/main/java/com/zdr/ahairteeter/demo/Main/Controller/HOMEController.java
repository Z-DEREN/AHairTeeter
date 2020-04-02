package com.zdr.ahairteeter.demo.Main.Controller;

import com.zdr.ahairteeter.demo.Tool.IConnect;
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


	/////////////////////////////测试接口用////////////////////////////////
	@Autowired
	private IConnect IConnect;

	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public Object cabgkyu(ModelAndView model) {
		IConnect.connect();
		model.addObject("Time", tool.GetNewDateTime(2));
		model.setViewName("main/HOME/navigation");
		return model;
	}






}

