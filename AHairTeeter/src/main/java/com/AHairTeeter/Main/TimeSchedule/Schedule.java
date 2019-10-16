package com.AHairTeeter.Main.TimeSchedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Tool.Tool;

@Component
public class Schedule {
	private static final Logger logger = LogManager.getLogger(Schedule.class.getName());

	Tool tool = new Tool();

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

	@Scheduled(cron = "0 0,30 0 * * ?") // ÿһ������һ��
	public void Date2() {
		try {
			logger.info("Date2:����ִ��һ��,��ǰ�߳�Ϊ" + Thread.currentThread().getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}