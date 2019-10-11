package com.AHairTeeter.Main.TimeSchedule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Main.Test_Module.DaoImpl.TestDaoImpl;
import com.AHairTeeter.Tool.Tool;

@Component
public class Schedule {
	private static final Logger logger = LogManager.getLogger(TestDaoImpl.class.getName());

	Tool tool = new Tool();

	@Scheduled(fixedDelay = 1000) // 每一秒运行一次
	public void Date1() {
		try {
			System.out.println("Date1:一秒执行一次");
			System.out.println(tool.GetNewDateTime(2));
			logger.info("Date1:一秒执行一次,当前线程为" + Thread.currentThread().getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Scheduled(cron = "0/2 * * * * ? ") // 每一秒运行一次
	public void Date2() {
		try {
			System.out.println("Date2:两秒执行一次");
			System.out.println(tool.GetNewDateTime(2));
			logger.info("Date2:两秒执行一次,当前线程为" + Thread.currentThread().getName());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
