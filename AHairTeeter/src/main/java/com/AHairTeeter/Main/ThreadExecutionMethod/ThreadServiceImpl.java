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
	 * ���߳�ִ��������
	 * 
	 * @param num
	 */
	public void middleman(int num) {
		logger.info(" ThExeMetѡ����߳�ִ�з���ִ��:" + num + "-----------------------------------------------------------"); // info�������Ϣ
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String name = "LaJiWenJIan";// Ĭ���ļ���
		String url = "";
		switch (num) {
		case 0:
			TestList TestList = new TestList();
			listmap = TestList.Testlist(1, 20);
			name = "�����ļ���";
			break;
		case 20:
			// �����ֶ�20
			//�ȶ�������м��
			
//			if() {
				SssGif img = new SssGif();
				listmap = img.first_no1(21, 30, "pics");
				name = "SssGif�ļ���";
				url = "https://www.sex.com/";
				AllocationThreadExecute(listmap,name,num,url);
				logger.info(" ThExeMet��������������------------------------------------------------------------"); // info�������Ϣ
//			}
			break;
		case 21:
			// �����ֶ�21
			Moeimg moeimg = new Moeimg();
			listmap = moeimg.moeimg_img(13800, 13801);
			name = "Moeimg�ļ���";
			url = "http://moeimg.net/";
			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet��������������------------------------------------------------------------"); // info�������Ϣ
			break;
		case 22:
			// �����ֶ�22
			Xvideos xvideos = new Xvideos();
			listmap = xvideos.videoNum(1, 5, "teen");
			name = "xvideosUrl";
			Tool.IOSaveFile(listmap);
//			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet��������������------------------------------------------------------------"); // info�������Ϣ
			break;
		case 24:
			// �����ֶ�24
			Kr36 Kr36 = new Kr36();
			listmap = Kr36.Kr36news();
			name = "Kr36";
			EmailType.Kr36Email(listmap);
//			AllocationThreadExecute(listmap,name,num,url);
			logger.info(" ThExeMet��������������------------------------------------------------------------"); // info�������Ϣ
			break;
		case 23:
			//�ֿ�
			ChangKu cangku = new ChangKu();
			cangku.first_no1(6, 100);
			break;

		default:
			logger.info(" ThExeMetѡ����߳�ִ����������������------------------------------------------------------------"); // info�������Ϣ
			break;
		}

		// ���汾���ļ�����
//		Tool.IOSaveFile(listmap);
		//�߳�ִ�з���
//		AllocationThreadExecute(listmap,name,num,url);
		//ִ���ʼ����
		
		
	}
	
	
	/**
	 * ���䲢ִ���߳�
	 * @param listmap
	 * @param name
	 * @param num
	 * @param url
	 */
	public void AllocationThreadExecute(List<Map<String, Object>> listmap,String name ,int num , String url) {
		if (listmap.size() > 0) {

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
		}else {
			logger.info(" ThExeMet��������û�г���------------------------------------------------------------"); // info�������Ϣ
		}
	}
	
	
	
	
	
	
	
	
	
	

}
