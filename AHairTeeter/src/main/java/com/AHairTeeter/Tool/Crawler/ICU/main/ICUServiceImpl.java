package com.AHairTeeter.Tool.Crawler.ICU.main;


import org.springframework.stereotype.Service;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;

@Service
public class ICUServiceImpl {
	
	Tool Tool =new Tool();
	
	/**
	 * ��ȡ����
	 * @param Text
	 * @return
	 */
	public String ICUQueryURL(String urls,int type) {
		Spiders spiders = new Spiders();
		String HTTPurl = spiders.spiders(urls, type);
		Tool.IOSaveFile(HTTPurl,"");//���浽����
		return null;
	}
	
	
	/**
	 * 
	 * @param typenum
	 */
	public void SwitchMethod(String typenum ) {
		System.out.println("��ȡ��վ(1),����ɸѡ���(2),ϵͳ��ȡ(3),ͼƬ��ȡ(4)");
		switch (typenum) {
		case "1":
			// 1��ȡ��վ
			break;
		case "2":
			// 2����ɸѡ���
			break;
		case "3":
			// 3ϵͳ��ȡ
			break;
		case "4":
			// 4ͼƬ��ȡ
			break;
		default:
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
}
