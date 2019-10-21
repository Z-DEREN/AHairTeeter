package com.AHairTeeter.Tool.ThreadExecutionMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo1;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo2;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo3;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo4;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo5;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.Moeimg;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.SssGif;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.TestList;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.Xvideos;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.NetworkGraphic.imageDownload;

@Component
public class ThExeMet {
	private static final Logger logger = LogManager.getLogger(ThExeMet.class.getName());
	Tool Tool = new Tool();

//	@Resource
//	private  ToolDaoImpl ToolDaoImpl = new ToolDaoImpl();

	@Autowired
	private ToolDaoImpl ToolDaoImplservice;

	private static ToolDaoImpl ToolDaoImpl;

	@PostConstruct
	public void init() {
		ToolDaoImpl = ToolDaoImplservice;
	}

	/**
	 * 获取 执行 返回
	 * 
	 * 
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ThExeMet ThExeMet = new ThExeMet();
		System.out.println("输入使用方法");
		System.out.println("0.TestList");
		System.out.println("1.SssGif");
		System.out.println("2.Moeimg");
		System.out.println("3.xvideos");
		ThExeMet.middleman(input.nextInt());
	}

	public void middleman(int num) {
		logger.info(" ThExeMet选择多线程执行方法执行:" + num + "-----------------------------------------------------------"); // info级别的信息
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String name = "LaJiWenJIan";// 默认文件名
		String url = "";
		switch (num) {
		case 0:
			TestList TestList = new TestList();
			listmap = TestList.Testlist(1, 200);
			name = "测试文件夹";
			break;
		case 1:
			// 特殊字段20
			SssGif img = new SssGif();
			listmap = img.first_no1(21, 30, "pics");
			name = "SssGif文件夹";
			url = "https://www.sex.com/";
			break;
		case 2:
			// 特殊字段21
			Moeimg moeimg = new Moeimg();
			listmap = moeimg.moeimg_img(10000, 10020);
			name = "Moeimg文件夹";
			url = "http://moeimg.net/";
			break;
		case 3:
			// 特殊字段22
			Xvideos xvideos = new Xvideos();
			listmap = xvideos.videoNum(1, 10, "teen");
			name = "xvideosUrl";
			break;
		case 4:

			break;

		default:
//			logger.info(" ThExeMet选择多线程执行类别输入参数有误------------------------------------------------------------"); // info级别的信息
			System.out.println("ThExeMet选择多线程执行类别输入参数有误");
			break;
		}

		//保存本地文件方法
		Tool.IOSaveFile(listmap);
		
		// 入库方法
		ToolDaoImpl.SaveCrawlersql(listmap);
		logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息

		
		
		
		List<List<Map<String, Object>>> ROlist = Tool.SplitSet(listmap, 5);
		System.out.println("每线程分" + ROlist.get(0).size() + "个数据");

		Queue<Integer> queueno1 = new LinkedBlockingQueue<>();
		queueno1.add(ROlist.get(0).size());
		new threadNNo1(queueno1, ROlist.get(0).size() - 1, ROlist.get(0), name, num, url).start();

		Queue<Integer> queueno2 = new LinkedBlockingQueue<>();
		queueno2.add(ROlist.get(1).size());
		new threadNNo2(queueno2, ROlist.get(1).size() - 1, ROlist.get(1), name, num, url).start();

		Queue<Integer> queueno3 = new LinkedBlockingQueue<>();
		queueno3.add(ROlist.get(2).size());
		new threadNNo3(queueno3, ROlist.get(2).size() - 1, ROlist.get(2), name, num, url).start();

		Queue<Integer> queueno4 = new LinkedBlockingQueue<>();
		queueno4.add(ROlist.get(3).size());
		new threadNNo4(queueno4, ROlist.get(3).size() - 1, ROlist.get(3), name, num, url).start();

		Queue<Integer> queueno5 = new LinkedBlockingQueue<>();
		queueno5.add(ROlist.get(4).size());
		new threadNNo5(queueno5, ROlist.get(4).size() - 1, ROlist.get(4), name, num, url).start();
	}

	// 图片下载方法(防盗链)
	imageDownload imageDownload = new imageDownload();

	/**
	 * 多线程执行方法
	 * 
	 * @param map  参数
	 * @param type 执行对应方法
	 * @param name 文件夹名
	 */
	public void method(Map<String, Object> map, int type, String name, String url) {

		switch (type) {
		case 0:
			String testid = map.get("testid").toString();
			System.out.println(testid);
			break;
		case 1:
		case 2:
			String urlList = map.get("urlList").toString();
			imageDownload.downloadPicture(urlList, "E:\\image\\" + name + "\\", url);
			break;
		case 3:

			break;

		default:
			break;
		}

	}

}
