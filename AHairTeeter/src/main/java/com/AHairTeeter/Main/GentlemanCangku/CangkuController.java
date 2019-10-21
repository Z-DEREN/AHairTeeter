package com.AHairTeeter.Main.GentlemanCangku;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.AHairTeeter.Tool.Tool;

@Controller
@RequestMapping("/Cangku")
public class CangkuController {
	private static final Logger logger = LogManager.getLogger(CangkuController.class.getName());
	Tool Tool = new Tool();

	@Autowired
	private CangkuServiceImpl CangkuServiceImpl;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("Time", Tool.GetNewDateTime(2));
		return "/main/cangku/Changku";
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
		CangkuServiceImpl.delelaji();
		List<String> RetList = new ArrayList<String>();

		Map<String, String> map = new HashMap<String, String>();
		List<CangkuVo> list = new ArrayList<CangkuVo>();
		list = CangkuServiceImpl.seleminid(type);// ��ȡid
		map.put("num", CangkuServiceImpl.Gettypenum(type));

		if (list.size() > 0) {

			System.out.println("id:" + list.get(0).getNewid() + "\n" + "pan:" + list.get(0).getPan() + "\n" + "��ȡ��:"
					+ list.get(0).getTiqu() + "\n" + "����:" + list.get(0).getMima() + "\n" + "����:"
					+ list.get(0).getType() + "\n" + "ʱ��:" + list.get(0).getTime());
			logger.info(" �ֿ�" + " id:" + list.get(0).getNewid()); // info�������Ϣ
			map.put("id", list.get(0).getNewid());
			map.put("pan", list.get(0).getPan());
			map.put("tiqu", list.get(0).getTiqu());
			map.put("mima", list.get(0).getMima() + "_");
			if (list.get(0).getMima().equals("") || list.get(0).getMima() != null) {
				map.put("newmima", "����");
			}
			map.put("type", list.get(0).getType());
			map.put("time", list.get(0).getTime() + " ");
			CangkuServiceImpl.updateminid(list.get(0).getNewid());// �߼�ɾ��
			model.addObject("retmap", map);
			model.addObject("retvaluelist", RetList);
			model.setViewName("/main/cangku/Changkupan");
			return model;
		} else {
			return "redirect:/Cangku/TypeList";
		}

	}

	/**
	 * ��ȡ���ӷ�ʽ,������չʾ
	 * 
	 * @param response
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/TypeList", method = { RequestMethod.POST, RequestMethod.GET })
	public Object TypeList(ModelAndView model) {
		System.out.println("����");
		List<Map<String, Object>> TypeList = CangkuServiceImpl.Alltype();
		model.addObject("TypeList", TypeList);
		model.setViewName("/main/cangku/Changku");
		return model;
	}

}
