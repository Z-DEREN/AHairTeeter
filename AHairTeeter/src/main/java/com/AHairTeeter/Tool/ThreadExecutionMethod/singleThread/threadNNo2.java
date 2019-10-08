package com.AHairTeeter.Tool.ThreadExecutionMethod.singleThread;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import com.AHairTeeter.Tool.ThreadExecutionMethod.ThExeMet;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class threadNNo2 extends Thread {
//	private static final Logger logger = LogManager.getLogger(threadNNo1.class.getName());
	ThExeMet ThExeMet = new ThExeMet();
	private volatile boolean finished = false;
	int missionlong;// 执行次数与集合下标存储位
	String name;// 最后保存名存储位(暂定)
	Queue<Integer> queue;
	List<Map<String, Object>> mission;// 执行数据存储位
	int type;// 类别

	public void stopMe() {
		finished = true;
	}

	public threadNNo2(Queue<Integer> queue, int missionlong, List<Map<String, Object>> mission, String name, int type) {
//		logger.info(" threadNNo1线程启动------------------------------------------------------------"); // info级别的信息
		System.out.println("threadNNo1线程启动");
		this.queue = queue;
		this.missionlong = missionlong;
		this.mission = mission;
		this.name = name;
		this.type = type;
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		Long time = 0L;
		while (!finished) {
			synchronized (queue) {

				try {
					Thread.sleep(200);
				} catch (Exception e) {
				}

				if (missionlong == 0) {
					long endTime = System.currentTimeMillis();
					System.out.println("threadNNo2线程执行结束,共耗时" + (endTime - startTime) + "毫秒");
					stopMe();
				}
				/////////////////////////////////////////
				ThExeMet.method(mission.get(missionlong), type, name);
				/////////////////////////////////////////
				System.out.println("threadNNo2线程执行任务:还有" + missionlong + "次执行");
				missionlong--;
			}
		}
	}

}
