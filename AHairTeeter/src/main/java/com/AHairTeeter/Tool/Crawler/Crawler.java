package com.AHairTeeter.Tool.Crawler;

import java.util.Scanner;

import com.AHairTeeter.Tool.Crawler.TestFilter.TestFilterMain;

/**
 * 爬虫方法 提供 1.可选IP池 2.可选user_agent 3.爬取方式
 * 
 * @author 好人
 *
 */
public class Crawler {

	/**
	 * 手动测试及使用方法
	 * @param args
	 */
	public static void main(String[] args) {

		Crawler Crawler = new Crawler();
		Crawler.CMain();
	}
	
	public void CMain() {
		Scanner input = new Scanner(System.in);
		System.out.println("测试筛选语句(1),爬取网站(2),系统爬取(3),图片爬取(4)");
		 String yn = input.next();
		switch (yn) {
		case "1":
			// 测试筛选语句
			TestFilterMain TestFilterMain = new TestFilterMain();
			TestFilterMain.Subscreen();
			break;
		case "2":
			// 爬取网站
//			PusotsUrl();
			break;
		case "3":
			// 系统爬取
//			pachongs pachongs = new pachongs();
//			pachongs.wanzhengpac();
			break;
		case "4":
			// 图片爬取
//			PictLi();
			break;
		default:
			break;
		}
	}
	
	
	
	
	
	
	
	

}
