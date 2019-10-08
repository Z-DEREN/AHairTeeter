package com.AHairTeeter.Tool.ThreadExecutionMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo1;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo2;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo3;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo4;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo5;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.Moeimg;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.SssGif;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.TestList;
import com.AHairTeeter.Tool.NetworkGraphic.imageDownload;

public class ThExeMet {

	/**
	 * ��ȡ ִ�� ����
	 * 
	 * 
	 */

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ThExeMet ThExeMet = new ThExeMet();
		System.out.println("����ʹ�÷���");
		System.out.println("0.TestList");
		System.out.println("1.SssGif");
		System.out.println("2.Moeimg");
		ThExeMet.middleman(input.nextInt());
	}

	public void middleman(int num) {
//		logger.info(" ThExeMetѡ����߳�ִ�з���ִ��------------------------------------------------------------"); // info�������Ϣ
		System.out.println("ThExeMetѡ����߳�ִ�з���ִ��" + "--ִ�в���" + num);
		List<Map<String, Object>> testlist = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String name = "LaJiWenJIan";// Ĭ���ļ���
		switch (num) {
		case 0:
			TestList TestList = new TestList();
			listmap = TestList.Testlist(1, 2);
			name = "�����ļ���";
			break;
		case 1:
			SssGif img = new SssGif();
			listmap = img.first_no1(21, 30);
			name = "SssGif�ļ���";
			break;
		case 2:
			Moeimg moeimg = new Moeimg();
			listmap = moeimg.moeimg_img(10000, 10020);
			name = "Moeimg�ļ���";
			break;
		case 3:

			break;
		case 4:

			break;

		default:
//			logger.info(" ThExeMetѡ����߳�ִ����������������------------------------------------------------------------"); // info�������Ϣ
			System.out.println("ThExeMetѡ����߳�ִ����������������");
			break;
		}

		int nums = listmap.size() / 5;
		System.out.println("ÿ�̷߳�" + nums + "������");

		Queue<Integer> queueno1 = new LinkedBlockingQueue<>();
		testlist = listmap.subList(0, nums);
		queueno1.add(testlist.size() - 1);
		new threadNNo1(queueno1, testlist.size() - 1, testlist, name, num).start();
		testlist = null;

		Queue<Integer> queueno2 = new LinkedBlockingQueue<>();
		testlist = listmap.subList(nums + 1, nums * 2);
		queueno2.add(testlist.size() - 1);
		new threadNNo2(queueno2, testlist.size() - 1, testlist, name, num).start();
		testlist = null;

		Queue<Integer> queueno3 = new LinkedBlockingQueue<>();
		testlist = listmap.subList(nums * 2 + 1, nums * 3);
		queueno3.add(testlist.size() - 1);
		new threadNNo3(queueno3, testlist.size() - 1, testlist, name, num).start();
		testlist = null;

		Queue<Integer> queueno4 = new LinkedBlockingQueue<>();
		testlist = listmap.subList(nums * 3 + 1, nums * 4);
		queueno4.add(testlist.size() - 1);
		new threadNNo4(queueno4, testlist.size() - 1, testlist, name, num).start();
		testlist = null;

		Queue<Integer> queueno5 = new LinkedBlockingQueue<>();
		testlist = listmap.subList(nums * 4 + 1, listmap.size());
		queueno5.add(testlist.size() - 1);
		new threadNNo5(queueno5, testlist.size() - 1, testlist, name, num).start();
		testlist = null;
	}

	// ͼƬ���ط���(������)
	imageDownload imageDownload = new imageDownload();

	public void method(Map<String, Object> map, int type, String name) {

		switch (type) {
		case 0:
			String testid = map.get("testid").toString();
			System.out.println(testid);
			break;
		case 1:
		case 2:
			String urlList = map.get("urlList").toString();
			imageDownload.downloadPicture(urlList, "E:\\image\\" + name + "\\", "https://www.sex.com/");
			break;
		case 3:

			break;

		default:
			break;
		}

	}

}
