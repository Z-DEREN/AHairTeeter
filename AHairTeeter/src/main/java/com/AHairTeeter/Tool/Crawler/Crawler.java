package com.AHairTeeter.Tool.Crawler;

import java.util.Scanner;

import com.AHairTeeter.Tool.Crawler.TestFilter.TestFilterMain;

/**
 * ���淽�� �ṩ 1.��ѡIP�� 2.��ѡuser_agent 3.��ȡ��ʽ
 * 
 * @author ����
 *
 */
public class Crawler {

	/**
	 * �ֶ����Լ�ʹ�÷���
	 * @param args
	 */
	public static void main(String[] args) {

		Crawler Crawler = new Crawler();
		Crawler.CMain();
	}
	
	public void CMain() {
		Scanner input = new Scanner(System.in);
		System.out.println("����ɸѡ���(1),��ȡ��վ(2),ϵͳ��ȡ(3),ͼƬ��ȡ(4)");
		 String yn = input.next();
		switch (yn) {
		case "1":
			// ����ɸѡ���
			TestFilterMain TestFilterMain = new TestFilterMain();
			TestFilterMain.Subscreen();
			break;
		case "2":
			// ��ȡ��վ
//			PusotsUrl();
			break;
		case "3":
			// ϵͳ��ȡ
//			pachongs pachongs = new pachongs();
//			pachongs.wanzhengpac();
			break;
		case "4":
			// ͼƬ��ȡ
//			PictLi();
			break;
		default:
			break;
		}
	}
	
	
	
	
	
	
	
	

}
