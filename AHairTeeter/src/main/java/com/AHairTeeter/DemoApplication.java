package com.AHairTeeter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
//��ݮ�ɵ�ַ
//@PropertySource(value = {
//		"file:/home/pi/rdzgsq/Database/ALi120/application.properties" }, ignoreResourceNotFound = false, encoding = "UTF-8", name = "application.properties")
@PropertySource(value = {
		"file:F://rdzgsq//Database//ALi120//application.properties" }, ignoreResourceNotFound = false, encoding = "UTF-8", name = "application.properties")
//public class DemoApplication extends SpringBootServletInitializer {
public class DemoApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
	// log
	private static final Logger logger = LogManager.getLogger(DemoApplication.class.getName());




	/**
	 * �����
	 */
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	/**
	 * ����Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
		logger.info("��Ŀ�����ɹ�------------------------------"); // info�������Ϣ
		// ������������λ
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
}
