package com.AHairTeeter.Test_Module;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.AHairTeeter.Test_Module.ServiceImpl.TestServiceImpl;


import com.AHairTeeter.Tool.Tool;


/**
 * ��������Controller
 * @author ����
 *
 */
@Controller
//@RequestMapping("/TestController")
public class TestController {

	// log
	private static final Logger logger = LogManager.getLogger(TestController.class.getName());
	
	@Resource
	public TestServiceImpl TestServiceImpl;
	
	//������
	Tool Tool = new Tool();
	
	
//	@RequestMapping("/test")
	@GetMapping("/templates")  
	public String home() {
		logger.info(" �����������ҳ��------------------------------------------------------------"); // info�������Ϣ
		System.out.println(Tool.GetNewDateTime(2)+":��ʼ");
		int num = TestServiceImpl.GetSqlMain();
		System.out.println(num+":����");
		 return "/index";  
	}
	
}
