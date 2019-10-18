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
		// ��ȡ��Ӧ���͵�DI��
		int NewZDI = Integer.parseInt(ZDI) + 1;
		// ���±�0�滻Ϊ��DI��
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(
				NewZDI + array[2].toString() + array[1].toString() + array[5].toString() + array[6].toString());

		// ����ZDI�в�����DIֵ
		Boolean retnum =  ToolDaoImpl.SingleSaveUpdeteSql(savesql, array);
		return NewZDI + "";
	}

	/**
	 * ִ��ip����
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
		
		//����������
		List<String> sqllist =  ToolDaoImpl.SaveIPList(list);
		ToolDaoImpl.ListSaveUpdeteSql(sqllist);
		System.out.println("������");
		//�����ݿ������ݽ��в���
		
//		List<Map<String,String>> MapList = new ArrayList<Map<String,String>>();
//		//����ip
//		ToolDaoImpl.UseTestIP(MapList, type);
		
		
	}
	
	
	public void middleman(int num) {
		logger.info(" ThExeMetѡ����߳�ִ�з���ִ��:" + num + "-----------------------------------------------------------"); // info�������Ϣ
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String name = "LaJiWenJIan";// Ĭ���ļ���
		String url = "";
		switch (num) {
		case 0:
			TestList TestList = new TestList();
			listmap = TestList.Testlist(1, 200);
			name = "�����ļ���";
			break;
		case 1:
			// �����ֶ�20
			SssGif img = new SssGif();
			listmap = img.first_no1(21, 30, "pics");
			name = "SssGif�ļ���";
			url = "https://www.sex.com/";
			break;
		case 2:
			// �����ֶ�21
			Moeimg moeimg = new Moeimg();
			listmap = moeimg.moeimg_img(10000, 10020);
			name = "Moeimg�ļ���";
			url = "http://moeimg.net/";
			break;
		case 3:
			// �����ֶ�22
			Xvideos xvideos = new Xvideos();
			listmap = xvideos.videoNum(1, 20, "teen");
			name = "xvideosUrl";
			break;
		case 4:

			break;

		default:
//			logger.info(" ThExeMetѡ����߳�ִ����������������------------------------------------------------------------"); // info�������Ϣ
			System.out.println("ThExeMetѡ����߳�ִ����������������");
			break;
		}

		// ��ⷽ��
		ToolDaoImpl.SaveCrawlersql(listmap);
		logger.info(" ThExeMet��������������------------------------------------------------------------"); // info�������Ϣ

		List<List<Map<String, Object>>> ROlist = Tool.SplitSet(listmap, 5);
		System.out.println("ÿ�̷߳�" + ROlist.get(0).size() + "������");

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
