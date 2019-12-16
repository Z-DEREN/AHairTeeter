package com.AHairTeeter.Main.ThreadExecutionMethod.Threadlater;

import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ThreadExecutionMethod.ThreadServiceImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.IMGNetworkGraphic.imageDownload;
import com.AHairTeeter.Tool.Route;

@Service
public class ParaphraseMethod {
	private static final Logger logger = LogManager.getLogger(ParaphraseMethod.class.getName());
	Tool Tool = new Tool();

	Route Route = new Route();

//	/**
//	 * ��ȡ ִ�� ����
//	 */
//	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//		ThreadServiceImpl ThExeMet = new ThreadServiceImpl();
//		System.out.println("����ʹ�÷���");
//		System.out.println("0.TestList");
//		System.out.println("1.SssGif");
//		System.out.println("2.Moeimg");
//		System.out.println("3.xvideos");
//		System.out.println("4.�ҵĲֿ�");
//		ThExeMet.middleman(input.nextInt());
//	}

	/**
	 * ͼƬ���ط���(������)
	 */
	imageDownload imageDownload = new imageDownload();

	/**
	 * ���߳�ִ�з���
	 * 
	 * @param map  ����
	 * @param type ִ�ж�Ӧ����
	 * @param name �ļ�����
	 */
	public void method(Map<String, Object> map, int type, String name, String url) {

		switch (type) {
		case 0:
			String testid = map.get("testid").toString();
//			System.out.println(testid);
			break;
		case 20:
		case 21:
			String urlList = map.get("url").toString();
			imageDownload.downloadPicture(urlList, Route.getPictureCrawler()+name+"\\", url,null);
			break;
		case 3:

			break;

		default:
			break;
		}

	}

}
