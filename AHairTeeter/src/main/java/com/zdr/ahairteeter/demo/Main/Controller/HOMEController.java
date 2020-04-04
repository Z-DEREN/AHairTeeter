package com.zdr.ahairteeter.demo.Main.Controller;

import com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.ThreadServiceImpl;
import com.zdr.ahairteeter.demo.Tool.DatabseConfiguration.DatabseConfiguration;
import com.zdr.ahairteeter.demo.Tool.IConnect;
import com.zdr.ahairteeter.demo.Tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.ThreadServiceImpl;

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
	public ThreadServiceImpl ThreadServiceImpl;
	@RequestMapping(value = "/test1", method = RequestMethod.GET)
	public Object test1(ModelAndView model) {
		ThreadServiceImpl.alone_use_way("22_4", null);
		model.addObject("Time", tool.GetNewDateTime(2));
		model.setViewName("main/HOME/navigation");
		return model;
	}
}

