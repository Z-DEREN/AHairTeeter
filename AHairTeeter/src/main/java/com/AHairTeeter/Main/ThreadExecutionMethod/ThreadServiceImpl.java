package com.AHairTeeter.Main.ThreadExecutionMethod;

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
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo1;
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo2;
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo3;
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo4;
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo5;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.ChangKu;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.Kr36;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.Moeimg;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.SssGif;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.TestList;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.Xvideos;
import com.AHairTeeter.Tool.EmalPop3.EmailType;

@Service
public class ThreadServiceImpl {

	private static final Logger logger = LogManager.getLogger(ThreadServiceImpl.class.getName());

	private Tool Tool = new Tool();
	private EmailType EmailType = new  EmailType();;
	
	@Resource
	private ToolDaoImpl ToolDaoImpl;

	
	
	/**
	 * 多线程执行主方法
	 * 
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
		case 20:
			// 特殊字段20
			//先对网络进行检测
			
//			if() {
				SssGif img = new SssGif();
				listmap = img.first_no1(21, 30, "pics");
				name = "SssGif文件夹";
				url = "https://www.sex.com/";
				AllocationThreadExecute(listmap,name,num,url);
				logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
//			}
			break;
		case 21:
			// 特殊字段21
			Moeimg moeimg = new Moeimg();
			listmap = moeimg.moeimg_img(13800, 13801);
			name = "Moeimg文件夹";
			url = "http://moeimg.net/";
			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 22:
			// 特殊字段22
			Xvideos xvideos = new Xvideos();
			listmap = xvideos.videoNum(1, 5, "teen");
			name = "xvideosUrl";
			Tool.IOSaveFile(listmap);
//			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 24:
			// 特殊字段24
			Kr36 Kr36 = new Kr36();
			listmap = Kr36.Kr36news();
			name = "Kr36";
			EmailType.Kr36Email(listmap);
//			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 23:
			//仓库
			ChangKu cangku = new ChangKu();
			cangku.first_no1(6, 100);
			break;

		default:
			logger.info(" ThExeMet选择多线程执行类别输入参数有误------------------------------------------------------------"); // info级别的信息
			break;
		}

		// 保存本地文件方法
//		Tool.IOSaveFile(listmap);
		//线程执行方法
//		AllocationThreadExecute(listmap,name,num,url);
		//执行邮件插件
		
		
	}
	
	
	/**
	 * 分配并执行线程
	 * @param listmap
	 * @param name
	 * @param num
	 * @param url
	 */
	public void AllocationThreadExecute(List<Map<String, Object>> listmap,String name ,int num , String url) {
		if (listmap.size() > 0) {

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
		}else {
			logger.info(" ThExeMet返回数据没有长度------------------------------------------------------------"); // info级别的信息
		}
	}
	
	
	
	
	
	
	
	
	
	

}
