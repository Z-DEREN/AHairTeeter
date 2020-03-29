package com.zdr.ahairteeter.demo.Main.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zdr.ahairteeter.demo.Main.SerGentlemanCangku.CangkuDaoServiceImpl;
import com.zdr.ahairteeter.demo.Tool.Tool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ResponseBody
@RequestMapping("/Cangku")
public class CangkuController {
	private static final Logger logger = LogManager.getLogger(CangkuController.class.getName());
	@Autowired
	private Tool tool;
	@Autowired
	private CangkuDaoServiceImpl CangkuDaoServiceImpl;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public Object home(ModelAndView model) {
		model.addObject("Time", tool.GetNewDateTime(2));
		model.setViewName("main/cangku/Changku");
		return model;
	}

	/**
	 * 获取链接方式,并进行展示
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/GetListType", method = { RequestMethod.POST, RequestMethod.GET })
	public Object user(HttpServletResponse response, HttpServletRequest request, ModelAndView model,
			@RequestParam(value = "TYPE", required = false) String TYPE,
			@RequestParam(value = "MIMA", required = false) String MIMA) {
		System.out.println("调用一次 值为:" + TYPE);
		Map<String, String> map = CangkuDaoServiceImpl.seleminid(TYPE ,MIMA );// 获取id
		if (map != null) {
			model.addObject("retmap", map);
			model.setViewName("main/cangku/Changkupan");
			return model;
		} else {
			model.setViewName("redirect:/AHairTeeter/navigation");
			return model;
		}

	}
	/**
	 * 通过密码获取链接方式,并进行展示
	 * 
	 * @param response
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/GetListMima", method = { RequestMethod.POST, RequestMethod.GET })
	public Object Mimauser(HttpServletResponse response, HttpServletRequest request, ModelAndView model,
			@RequestParam(value = "mima", required = false) String mima) {
		System.out.println("调用一次 值为:" + mima);
		List<Map<String, Object>> TypeList = CangkuDaoServiceImpl.Alltype(mima);
		model.addObject("TypeList", TypeList);
		model.addObject("typevalue", mima);
		model.setViewName("main/cangku/Changku");
			return model;
	}
	

	/**
	 * 获取链接方式,并进行展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "/TypeList", method = { RequestMethod.POST, RequestMethod.GET })
	public Object TypeList(ModelAndView model) {
		System.out.println("集合");
		List<Map<String, Object>> TypeList = CangkuDaoServiceImpl.Alltype(null);
		model.addObject("TypeList", TypeList);
		model.setViewName("main/cangku/Changku");
		return model;
	}
	
	
	/**
	 * 获取链接方式,并进行展示
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/MimaList", method = { RequestMethod.POST, RequestMethod.GET })
	public Object MimaList(ModelAndView model) {
		System.out.println("集合");
		System.out.println("集合");
		List<Map<String, Object>> TypeList = CangkuDaoServiceImpl.AllMima();
		model.addObject("TypeList", TypeList);
		model.setViewName("main/cangku/ChangkuMima");
		return model;
	}
	

	/**
	 * 排除失效链接
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/excludelose", method = { RequestMethod.POST, RequestMethod.GET })
	public Object excludelose(ModelAndView model) {
		CangkuDaoServiceImpl.excludelose();
		model.setViewName("redirect:/AHairTeeter/navigation");
		return model;
		
	}

}
