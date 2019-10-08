package com.AHairTeeter.Tool.Crawler.TestFilter;

import java.util.Scanner;

import com.AHairTeeter.Tool.Route;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

public class TestFilterMain {
	/**
	 * 测试筛选语句 是将爬取到的页面源代码保存到本地后进行离线数据获取测试使用
	 * 
	 * @param text
	 */
	public void Subscreen() {
		Route rou = new Route();
		Scanner input = new Scanner(System.in);
		IOLocalFile ioLF = new IOLocalFile();
		System.out.println("文件名为:");
		String url = input.next();
		url = ioLF.output(rou.本地存储地址 + url + ".txt");
		System.out.println("是否开始筛选值(Y/N)");
		String yn = input.next();
		if (yn.equals("Y") || yn.equals("y")) {
			System.out.println("进行筛选测试!!");
			Filtrate(url);
		}
	}

	public void Filtrate(String test) {
		Scanner input = new Scanner(System.in);
		System.out.println("筛选方法方法:");
		System.out.println("1.");
		System.out.println("3.");
		System.out.println("4.");
		String nom = input.next();
		switch (nom) {
		case "1":
			break;
		case "2":
			break;
		case "3":
			break;
		case "4":
			break;
		case "5":
			break;
		case "6":
			break;
		case "8":
			break;
		case "9":
			break;
		default:
			break;
		}
	}
}
