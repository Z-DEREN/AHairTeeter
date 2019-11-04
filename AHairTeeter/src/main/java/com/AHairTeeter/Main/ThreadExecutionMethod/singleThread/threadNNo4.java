package com.AHairTeeter.Main.ThreadExecutionMethod.singleThread;

import java.util.List;
import java.util.Map;
import java.util.Queue;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.AHairTeeter.Main.ThreadExecutionMethod.Threadlater.ParaphraseMethod;

public class threadNNo4 extends Thread {
	private static final Logger logger = LogManager.getLogger(threadNNo4.class.getName());
	ParaphraseMethod ThExeMet = new ParaphraseMethod();
	private volatile boolean finished = false;
	int missionlong;// ִ�д����뼯���±�洢λ
	String name = "";// ��󱣴����洢λ(�ݶ�)
	Queue<Integer> queue;
	List<Map<String, Object>> mission;// ִ�����ݴ洢λ
	int type;// ���
	String url;//��������ַ

	public void stopMe() {
		finished = true;
	}

	public threadNNo4(Queue<Integer> queue, int missionlong, List<Map<String, Object>> mission,
			String name, int type, String url) {
		logger.info(" threadNNo4�߳�����------------------------------------------------------------"); // info�������Ϣ
		this.queue = queue;
		this.missionlong = missionlong;
		this.mission = mission;
		this.type = type;
		this.name = name;
		this.url = url;
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis(); // ��ȡ��ʼʱ��
		Long time = 0L;
		while (!finished) {
			synchronized (queue) {
				try {
					Thread.sleep(200);
				} catch (Exception e) {
				}
				if (missionlong == 0) {
					long endTime = System.currentTimeMillis();
					logger.info("threadNNo4�߳�ִ�н���,����ʱ" + (endTime - startTime) + "����"); // info�������Ϣ
					stopMe();
				}
				/////////////////////////////////////////
				ThExeMet.method(mission.get(missionlong), type, name ,url );
				/////////////////////////////////////////
				logger.info("threadNNo4�߳�ִ������:����" + missionlong + "��ִ��"); // info�������Ϣ
				missionlong--;
			}
		}
	}

}