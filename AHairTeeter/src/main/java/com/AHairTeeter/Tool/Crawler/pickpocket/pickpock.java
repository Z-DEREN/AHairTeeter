package com.AHairTeeter.Tool.Crawler.pickpocket;

import java.util.Scanner;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

public class pickpock {
	/**
	 * 爬取网站
	 */
	public void PusotsUrl() {
		Scanner input = new Scanner(System.in);
		Spiders spiders = new Spiders();
		IOLocalFile ioLF = new IOLocalFile();
		System.out.println("请输入想要爬取的网址:");
		String url = input.next();
		System.out.println("请输入模式(1~5)");
		int num = input.nextInt();
		System.out.println("等待处理结果");
		try {
			String HTTPurl = spiders.spiders(url, num);
			System.out.println(HTTPurl);
			if (HTTPurl == null || HTTPurl.equals("")) {
				System.out.println("爬取失败,返回为空");
				return;
			} else {
				// 测试爬取程序
				System.out.println("是否保存到本地(Y/N)");
				String yn = input.next();
				if (yn.equals("Y") || yn.equals("y")) {
					System.out.println("保存的名字为:");
					String test = input.next();
					ioLF.input(HTTPurl, test);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
