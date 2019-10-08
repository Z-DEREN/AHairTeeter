package com.AHairTeeter.Tool.multithreading.singleThread;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class threadNNo1 extends Thread {
	private static final Logger logger = LogManager.getLogger(threadNNo1.class.getName());

	Queue<Integer> queue;
	int missionlong;
	List<Map<String,Object>> mission;
	
	private volatile boolean finished = false;
	
	public void stopMe() {
        finished = true;  
    }
	
	public threadNNo1(Queue<Integer> queue,int missionlong,List<Map<String,Object>> mission) {
		logger.info(" threadNNo1�߳�����------------------------------------------------------------"); // info�������Ϣ
		System.out.println("threadNNo1�߳�����");
		this.queue = queue;
		this.missionlong = missionlong;
		this.mission = mission;
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
				
				if (missionlong == 0 ) {
					long endTime = System.currentTimeMillis();
						System.out.println("threadNNo1�߳�ִ�н���,����ʱ"+(endTime - startTime)+"����");
						stopMe();
				}
				/////////////////////////////////////////
				method(missionlong);
				/////////////////////////////////////////
				System.out.println("threadNNo1�߳�ִ������:����" + missionlong+"��ִ��");
				missionlong--;
			}
		}
	}
	/**
	 * ִ�з���
	 * @param missionlong
	 */
	public void method(int missionlong) {
		String data = mission.get(missionlong).get("").toString();
		
		
		
		
		
	}
	
	
}
