package com.AHairTeeter.Main.Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo1;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo2;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo3;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo4;
import com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread.threadNNo5;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.Moeimg;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.SssGif;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.TestList;
import com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse.Xvideos;

@Service
public class ThreadServiceImpl {
	
	private static final Logger logger = LogManager.getLogger(ThreadServiceImpl.class.getName());

	Tool Tool =new Tool();
	
	@Resource
	private ToolDaoImpl ToolDaoImpl;
	
	
	/**
	 * 多线程执行主方法
	 * @param num
	 */
	public void middleman(int num) {
		logger.info(" ThExeMet选择多线程执行方法执行:" + num + "-----------------------------------------------------------"); // info级别的信息
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String name = "LaJiWenJIan";// 默认文件名
		String url = "";
		switch (num) {
		case 0:
			TestList TestList = new TestList();
			listmap = TestList.Testlist(1, 20);
			name = "测试文件夹";
			break;
		case 1:
			// 特殊字段20
			SssGif img = new SssGif();
			listmap = img.first_no1(21, 30, "pics");
			name = "SssGif文件夹";
			url = "https://www.sex.com/";
			ToolDaoImpl.SaveCrawlersql(listmap);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 2:
			// 特殊字段21
			Moeimg moeimg = new Moeimg();
			listmap = moeimg.moeimg_img(10000, 10020);
			name = "Moeimg文件夹";
			url = "http://moeimg.net/";
			ToolDaoImpl.SaveCrawlersql(listmap);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 3:
			// 特殊字段22
			Xvideos xvideos = new Xvideos();
			listmap = xvideos.videoNum(1, 10, "teen");
			name = "xvideosUrl";
			ToolDaoImpl.SaveCrawlersql(listmap);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 4:

			break;

		default:
			logger.info(" ThExeMet选择多线程执行类别输入参数有误------------------------------------------------------------"); // info级别的信息
			break;
		}

		//保存本地文件方法
		Tool.IOSaveFile(listmap);
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
	

}
