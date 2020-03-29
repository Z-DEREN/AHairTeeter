package com.AHairTeeter.Main.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AHairTeeter.Main.GentlemanCangku.CangkuDaoServiceImpl;

import com.AHairTeeter.Tool.Tool;

@Controller
@ResponseBody
@RequestMapping("/Cangku")
public class CangkuController {
	private static final Logger logger = LogManager.getLogger(CangkuController.class.getName());
	Tool Tool = new Tool();

	@Autowired
	private CangkuDaoServiceImpl CangkuDaoServiceImpl;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public Object home(ModelAndView model) {
		model.addObject("Time", Tool.GetNewDateTime(2));
		model.setViewName("main/cangku/Changku");
		return model;
	}

	/**
	 * ��ȡ���ӷ�ʽ,������չʾ
	 * 
	 * @param response
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/GetListType/{type}", method = { RequestMethod.POST, RequestMethod.GET })
	public Object user(HttpServletResponse response, HttpServletRequest request, ModelAndView model,
			@PathVariable String type) {
		System.out.println("����һ�� ֵΪ:" + type);
		Map<String, String> map = CangkuDaoServiceImpl.seleminid(type);// ��ȡid
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
	 * ��ȡ���ӷ�ʽ,������չʾ
	 * 

	 * @return
	 */
	@RequestMapping(value = "/TypeList", method = { RequestMethod.POST, RequestMethod.GET })
	public Object TypeList(ModelAndView model) {
		System.out.println("����");
		List<Map<String, Object>> TypeList = CangkuDaoServiceImpl.Alltype();
		model.addObject("TypeList", TypeList);
		model.setViewName("main/cangku/Changku");
		return model;
	}

}
