package com.AHairTeeter.Main.TimeSchedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Main.ThreadExecutionMethod.ThreadServiceImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Main.IPpool.DaoServiceImpl.InternetProtocolDaoServiceImpl;

@Component
public class Schedule {
	private static final Logger logger = LogManager.getLogger(Schedule.class.getName());

	Tool tool = new Tool();
	
	@Autowired	
	public ThreadServiceImpl ThreadServiceImpl;
	
	@Autowired	
	public InternetProtocolDaoServiceImpl InternetProtocolDaoServiceImpl;

//	@Scheduled(fixedDelay = 1000) // ÿһ������һ��
//	public void Date1() {
//		try {
//			System.out.println("Date1:һ��ִ��һ��");
//			System.out.println(tool.GetNewDateTime(2));
//			logger.info("Date1:һ��ִ��һ��,��ǰ�߳�Ϊ" + Thread.currentThread().getName());
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//	}

//	@Scheduled(cron = "0 0/30 9-20 * * ?") // ÿһ������һ��
	public void Date2() {
		try {
			ThreadServiceImpl.middleman(24);
			logger.info("Date2:ÿ��9-20��ִ��ִ��һ��,��ǰ�߳�Ϊ" + Thread.currentThread().getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
//	@Scheduled(cron = "0 0 8-22/2 * * ? ") // ÿһ������һ��
	public void Date3() {
		try {
			InternetProtocolDaoServiceImpl.ToolIPSave("61");
			logger.info("Date3:ÿ��8-22��,��Сʱִ��һ��,��ǰ�߳�Ϊ" + Thread.currentThread().getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
