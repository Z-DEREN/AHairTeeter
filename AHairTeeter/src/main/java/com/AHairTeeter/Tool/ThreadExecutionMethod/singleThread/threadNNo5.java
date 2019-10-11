package com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import com.AHairTeeter.Tool.ThreadExecutionMethod.ThExeMet;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class threadNNo5 extends Thread {
//	private static final Logger logger = LogManager.getLogger(threadNNo1.class.getName());
	ThExeMet ThExeMet = new ThExeMet();
	private volatile boolean finished = false;
	int missionlong;// ִ�д����뼯���±�洢λ
	String name;// ��󱣴����洢λ(�ݶ�)
	Queue<Integer> queue;
	List<Map<String, Object>> mission;// ִ�����ݴ洢λ
	int type;// ���

	public void stopMe() {
		finished = true;
	}

	public threadNNo5(Queue<Integer> queue, int missionlong, List<Map<String, Object>> mission, String name, int type) {
//		logger.info(" threadNNo1�߳�����------------------------------------------------------------"); // info�������Ϣ
		System.out.println("threadNNo1�߳�����");
		this.queue = queue;
		this.missionlong = missionlong;
		this.mission = mission;
		this.name = name;
		this.type = type;
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
					System.out.println("threadNNo5�߳�ִ�н���,����ʱ" + (endTime - startTime) + "����");
					stopMe();
				}
				/////////////////////////////////////////
				ThExeMet.method(mission.get(missionlong), type, name);
				/////////////////////////////////////////
				System.out.println("threadNNo5�߳�ִ������:����" + missionlong + "��ִ��");
				missionlong--;
			}
		}
	}

}