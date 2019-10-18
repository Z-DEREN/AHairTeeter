package com.AHairTeeter.Main.ToolCabinet.ToolServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolService.ToolService;
import com.AHairTeeter.Tool.MD5;
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
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
@Service
public class ToolServiceImpl implements ToolService {
	private static final Logger logger = LogManager.getLogger(ToolServiceImpl.class.getName());

	@Autowired
	public ToolDaoImpl ToolDaoImpl;
	
	Tool Tool = new Tool();

	public Object GetintZDInum(String type, String retModel) {
		Tool Tool = new Tool();
		MD5 md5 = new MD5();
		Object[] array = new Object[7];
		String sql = "";
		String savesql = "INSERT INTO tonuminvi (ZDI,ZNAME,DATETIME,UPDATETIME,MD5DI,TYPE,MODEL) VALUES (?,?,?,?,?,?,?)";
		List<String> list = Tool.GetIPtypeDI(type);
		array[1] = list.get(0);
		array[5] = list.get(1);
		array[6] = list.get(2);
		String ZDI = ToolDaoImpl.GetSelObjsql(sql, null, retModel).toString();
		// 获取对应类型的DI码
		int NewZDI = Integer.parseInt(ZDI) + 1;
		// 将下标0替换为新DI码
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(
				NewZDI + array[2].toString() + array[1].toString() + array[5].toString() + array[6].toString());

		// 向总ZDI中插入新DI值
		Boolean retnum =  ToolDaoImpl.SingleSaveUpdeteSql(savesql, array);
		return NewZDI + "";
	}

	/**
	 * 执行ip流程
	 * 
	 * @param type
	 */
	@Override
	public void ToolIPSave(String type) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		switch (type) {
		case "61":
			list = ToolDaoImpl.Get61ChinaIP();
			break;
		default:
			break;
		}
		
		//进行入库操作
		List<String> sqllist =  ToolDaoImpl.SaveIPList(list);
		ToolDaoImpl.ListSaveUpdeteSql(sqllist);
		System.out.println("入库结束");
		//对数据库内数据进行操作
		
//		List<Map<String,String>> MapList = new ArrayList<Map<String,String>>();
//		//测试ip
//		ToolDaoImpl.UseTestIP(MapList, type);
		
		
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
			listmap = xvideos.videoNum(1, 20, "teen");
			name = "xvideosUrl";
			break;
		case 4:

			break;

		default:
//			logger.info(" ThExeMet选择多线程执行类别输入参数有误------------------------------------------------------------"); // info级别的信息
			System.out.println("ThExeMet选择多线程执行类别输入参数有误");
			break;
		}

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
	
	
	
	
	

}
