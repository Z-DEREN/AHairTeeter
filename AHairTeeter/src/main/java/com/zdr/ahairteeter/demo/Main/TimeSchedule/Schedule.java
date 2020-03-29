package com.zdr.ahairteeter.demo.Main.TimeSchedule;

import com.zdr.ahairteeter.demo.Main.SerIPpool.DaoServiceImpl.InternetProtocolDaoServiceImpl;
import com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.ThreadServiceImpl;
import com.zdr.ahairteeter.demo.Tool.Tool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Schedule {
	private static final Logger logger = LogManager.getLogger(Schedule.class.getName());

	Tool tool = new Tool();
	
	@Autowired	
	public  ThreadServiceImpl ThreadServiceImpl;
	
	@Autowired	
	public  InternetProtocolDaoServiceImpl InternetProtocolDaoServiceImpl;

//	@Scheduled(fixedDelay = 1000) // 每一秒运行一次
//	public void Date1() {
//		try {
//			System.out.println("Date1:一秒执行一次");
//			System.out.println(tool.GetNewDateTime(2));
//			logger.info("Date1:一秒执行一次,当前线程为" + Thread.currentThread().getName());
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//	}

//	@Scheduled(cron = "0 0/30 9-20 * * ?") // 每半小时执行一次
	public void Date2() {
		try {
			ThreadServiceImpl.middleman(24);
			logger.info("Date2:每天9-20点执行执行一次,当前线程为" + Thread.currentThread().getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
//	@Scheduled(cron = "0 0 8-22/2 * * ? ") // 每一秒运行一次
	public void Date3() {
		try {
			InternetProtocolDaoServiceImpl.ToolIPSave("61");
			logger.info("Date3:每天8-22点,两小时执行一次,当前线程为" + Thread.currentThread().getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
