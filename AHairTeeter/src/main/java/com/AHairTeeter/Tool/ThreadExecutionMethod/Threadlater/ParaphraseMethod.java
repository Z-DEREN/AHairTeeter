package com.AHairTeeter.Tool.ThreadExecutionMethod.Threadlater;

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
import org.springframework.stereotype.Service;

import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo1;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo2;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo3;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo4;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo5;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.Moeimg;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.SssGif;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.TestList;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.Xvideos;
import com.AHairTeeter.Main.Thread.ThreadServiceImpl;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.NetworkGraphic.imageDownload;

@Service
public class ParaphraseMethod {
	private static final Logger logger = LogManager.getLogger(ParaphraseMethod.class.getName());
	Tool Tool = new Tool();

	/**
	 * 获取 执行 返回
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ThreadServiceImpl ThExeMet = new ThreadServiceImpl();
		System.out.println("输入使用方法");
		System.out.println("0.TestList");
		System.out.println("1.SssGif");
		System.out.println("2.Moeimg");
		System.out.println("3.xvideos");
		ThExeMet.middleman(input.nextInt());
	}

	
	/**
	 *  图片下载方法(防盗链)
	 */
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
//			System.out.println(testid);
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
