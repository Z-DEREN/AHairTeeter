package com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.annotation.Resource;

import com.zdr.ahairteeter.demo.Main.SuperFactory.Factory;
import com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.singleThread.*;
import com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Warehouse.*;
import com.zdr.ahairteeter.demo.Main.SerToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.zdr.ahairteeter.demo.Tool.EmalPop3.EmailType;
import com.zdr.ahairteeter.demo.Tool.Route;
import com.zdr.ahairteeter.demo.Tool.Tool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThreadServiceImpl {

	private static final Logger logger = LogManager.getLogger(ThreadServiceImpl.class.getName());
	@Autowired
	private Route route;
	@Autowired
	private Tool Tool;
	@Autowired
	private EmailType EmailType ;
	@Autowired
	private DetectionList detectionList;
	@Autowired
	private SssGif sssGif;
	@Autowired
	private Moeimg moeimg ;
	@Autowired
	private Xvideos xvideos;
	@Autowired
	private Kr36 kr36;
	@Autowired
	private ChangKu cangku;


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
			listmap = detectionList.Testlist(1, 20);
			name = "测试文件夹";
			break;
		case 20:
			// 特殊字段20
			// 先对网络进行检测
			listmap = sssGif.first_no1(1, 5);
			name = "SssGif文件夹";
			url = "https://www.sex.com/";
			AllocationThreadExecute(listmap, name, num, url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 21:
			// 特殊字段21
			listmap = moeimg.moeimg_img(13800, 13801);
			name = "Moeimg文件夹";
			url = "http://moeimg.net/";
			AllocationThreadExecute(listmap, name, num, url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 22:
			// 特殊字段22
			listmap = xvideos.videoNum(1, 5, "");
			name = "xvideosUrl";
			Tool.IOSaveFile(listmap);
//			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 24:
			// 特殊字段24
			listmap = kr36.Kr36news();
			name = "Kr36";
			EmailType.Kr36Email(listmap);
//			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case 23:
			// 仓库
			//获取指定页码
			String []  PA = route.getWarehouse_pagination().split("P");
			cangku.first_no1(Integer.parseInt(PA[0]), Integer.parseInt(PA[1]));
			break;
			

		default:
			logger.info(" ThExeMet选择多线程执行类别输入参数有误------------------------------------------------------------"); // info级别的信息
			break;
		}

		// 保存本地文件方法
//		Tool.IOSaveFile(listmap);
		// 线程执行方法
//		AllocationThreadExecute(listmap,name,num,url);
		// 执行邮件插件

	}

	/**
	 * 分配并执行线程
	 * 
	 * @param listmap
	 * @param name
	 * @param num
	 * @param url
	 */
	public void AllocationThreadExecute(List<Map<String, Object>> listmap, String name, int num, String url) {
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
		} else {
			logger.info(" ThExeMet返回数据没有长度------------------------------------------------------------"); // info级别的信息
		}
	}


	/**
	 * 重写单独调用方法
	 */
	public void alone_use_way_v2(String typeName, String url) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String name = "LaJiWenJIan";// 默认文件名
		try {
			WarehouseImpl wareho = Factory.GetThreadService(typeName);

			route.get

			wareho.Webautomate(1,2,url);
			int num = Integer.parseInt(typeName);
			AllocationThreadExecute(listmap, name, num, url);



		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
	}




	/**
	 * 单独调用方法
	 * 
	 * @param type
	 * @param url
	 */
	public void alone_use_way(String type, String url) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String name = "LaJiWenJIan";// 默认文件名
		switch (type) {
		case "0":
			break;
		case "20":
			listmap = sssGif.first_no1(1, 10);
			name = "SssGif文件夹";
			url = "https://www.sex.com/";
			int num = Integer.parseInt(type);
			AllocationThreadExecute(listmap, name, num, url);

			break;
		case "21":
			break;
		case "22_1":
			xvideos.getxvideoURL(url);
			listmap = xvideos.videoNum(1, 5, "");
			name = "xvideosUrl";
			Tool.IOSaveFile(listmap);
//			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case "22_2":

			listmap = xvideos.video_Reco_multi(url, null);
			name = "xvideosUrl_multi";
			Tool.IOSaveFile(listmap);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case "22_3":
			
			listmap = xvideos.video_Reco_alone(url, null);
			name = "xvideosUrl";
			Tool.IOSaveFile(listmap);
//			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case "22":
			listmap = xvideos.video_Reco(url, null);
			name = "xvideosUrl";
			Tool.IOSaveFile(listmap);
//			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet数据入库操作结束------------------------------------------------------------"); // info级别的信息
			break;
		case "23":
			break;
		case "24":
			break;
		case "25":
			KUkuManHua KUkuManHua = new KUkuManHua();
			KUkuManHua.getxvideoURL(url);
			KUkuManHua.first_no1(0, 0, null);
			break;

		default:
			logger.info(" ThExeMet选择单独调用方法别输入参数有误------------------------------------------------------------"); // info级别的信息
			break;
		}

	}

}
