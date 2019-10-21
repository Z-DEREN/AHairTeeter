package com.AHairTeeter.Tool.Crawler.ICU.main;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;

@Controller
@RequestMapping("/initcriturge")
public class ICUController {
	Tool Tool =new Tool();
	@Autowired
	public ICUServiceImpl ICUServiceImpl ;
	
	
	public static void main(String[] args) {
		Spiders spiders = new Spiders();
		Scanner input = new Scanner(System.in);
		System.out.println("��������Ҫ��ȡ����ַ:");
		String url = input.next();
		System.out.println("������ģʽ(1~5)");
		int num = input.nextInt();
		System.out.println("�ȴ�������");
		String HTTPurl = spiders.spiders(url, num);
		System.out.println(HTTPurl);
		
	}

	/**
	 * ��ȡ���
	 * 
	 * @param model
	 * @param urls
	 * @return
	 */
	@RequestMapping(value = "/QueryURL/{urls}/{type}", method = { RequestMethod.POST, RequestMethod.GET })
	public Object QueryURL(ModelAndView model, @PathVariable String urls, @PathVariable int type) {
		ICUServiceImpl.ICUQueryURL(urls, type);
		
		return model;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
