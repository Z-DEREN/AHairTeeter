package com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.singleThread;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Threadlater.ParaphraseMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class threadNNo3 extends Thread {
	private static final Logger logger = LogManager.getLogger(threadNNo3.class.getName());
	ParaphraseMethod ThExeMet = new ParaphraseMethod();
	private volatile boolean finished = false;
	int missionlong;// 执行次数与集合下标存储位
	String name;// 最后保存名存储位(暂定)
	Queue<Integer> queue;
	List<Map<String, Object>> mission;// 执行数据存储位
	int type;// 类别
	String url;//防盗链地址

	public void stopMe() {
		finished = true;
	}

	public threadNNo3(Queue<Integer> queue, int missionlong, List<Map<String, Object>> mission,
			String name, int type, String url) {
		logger.info(" threadNNo3线程启动------------------------------------------------------------"); // info级别的信息
		this.queue = queue;
		this.missionlong = missionlong;
		this.mission = mission;
		this.name = name;
		this.type = type;
		this.url = url;
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
					logger.info("threadNNo3线程执行结束,共耗时" + (endTime - startTime) + "毫秒"); // info级别的信息
					stopMe();
				}
				/////////////////////////////////////////
				ThExeMet.method(mission.get(missionlong), type, name ,url );/////////////////////////////////////////
				logger.info("threadNNo3线程执行任务:还有" + missionlong + "次执行"); // info级别的信息
				missionlong--;
			}
		}
	}

}
