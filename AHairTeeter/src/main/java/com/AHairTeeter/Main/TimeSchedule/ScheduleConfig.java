package com.AHairTeeter.Main.TimeSchedule;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Tool.Tool;

/**
 * ��ʱ���� ���߳�
 * 
 * @author ����
 *
 */
@Configuration
@EnableScheduling
public class ScheduleConfig  implements SchedulingConfigurer{
	

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
	}
	
	@Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10); //ָ���̳߳ش�С
    }
}
