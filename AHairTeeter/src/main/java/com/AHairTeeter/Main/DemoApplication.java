package com.AHairTeeter.Main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
@PropertySource(value = {
		"file:F://rdzgsq//Database//ALi120//application.properties" }, ignoreResourceNotFound = false, encoding = "UTF-8", name = "application.properties")
public class DemoApplication extends SpringBootServletInitializer {
	// log
	private static final Logger logger = LogManager.getLogger(DemoApplication.class.getName());

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	/**
	 * 启动Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
		logger.info("项目启动成功------------------------------"); // info级别的信息
		// 进行启动测试位
	}

}
