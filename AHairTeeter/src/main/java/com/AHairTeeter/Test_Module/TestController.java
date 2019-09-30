package com.AHairTeeter.Test_Module;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AHairTeeter.Test_Module.ServiceImpl.TestServiceImpl;

import com.AHairTeeter.Tool.Tool;

/**
 * ��������Controller
 * 
 * @author ����
 *
 */
@Controller
@RequestMapping(value = "area")
public class TestController {

	// log
	private static final Logger logger = LogManager.getLogger(TestController.class.getName());

	@Resource
	public TestServiceImpl TestServiceImpl;

	// ������
	Tool Tool = new Tool();

	@RequestMapping(value = "/indeasdasdx", method = RequestMethod.GET)
//	@GetMapping("/templates")
	@ResponseBody
	public String home() {
		logger.info(" �����������ҳ��------------------------------------------------------------"); // info�������Ϣ
		System.out.println(Tool.GetNewDateTime(2) + ":��ʼ");
		int num = TestServiceImpl.GetSqlMain();
		System.out.println(num + ":����");
		return "/index";
	}

	@GetMapping(value = "index")
	public String index() {
		return "area/index.jsp";
	}
	

}
