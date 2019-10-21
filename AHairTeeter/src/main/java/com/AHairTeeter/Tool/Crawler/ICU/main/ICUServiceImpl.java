package com.AHairTeeter.Tool.Crawler.ICU.main;


import org.springframework.stereotype.Service;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;

@Service
public class ICUServiceImpl {
	
	Tool Tool =new Tool();
	
	/**
	 * 爬取方法
	 * @param Text
	 * @return
	 */
	public String ICUQueryURL(String urls,int type) {
		Spiders spiders = new Spiders();
		String HTTPurl = spiders.spiders(urls, type);
		Tool.IOSaveFile(HTTPurl,"");//保存到本地
		return null;
	}
	
	
	/**
	 * 
	 * @param typenum
	 */
	public void SwitchMethod(String typenum ) {
		System.out.println("爬取网站(1),测试筛选语句(2),系统爬取(3),图片爬取(4)");
		switch (typenum) {
		case "1":
			// 1爬取网站
			break;
		case "2":
			// 2测试筛选语句
			break;
		case "3":
			// 3系统爬取
			break;
		case "4":
			// 4图片爬取
			break;
		default:
			break;
		}
	}
	
	
	
	
	
	
	
	
	
	
}
