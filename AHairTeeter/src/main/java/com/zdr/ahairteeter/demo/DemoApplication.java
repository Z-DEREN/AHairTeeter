package com.zdr.ahairteeter.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling

//树莓派地址
//@PropertySource(value = {
//		"file:/home/pi/rdzgsq/Database/ALi120/application.properties" }, ignoreResourceNotFound = false, encoding = "UTF-8", name = "application.properties")

@PropertySource(value = {
		"file:F://rdzgsq//Database//ALi120//application.properties" }, ignoreResourceNotFound = false, encoding = "UTF-8", name = "application.properties")

    //@PropertySource(value = {
            //"file:E:/Database//ALi120//application.properties" }, ignoreResourceNotFound = false, encoding = "UTF-8", name = "application.properties")
//public class DemoApplication extends SpringBootServletInitializer {
    public class DemoApplication extends SpringBootServletInitializer implements WebMvcConfigurer {
        // log
        private static final Logger logger = LogManager.getLogger(DemoApplication.class.getName());

        /**
         * 打包用
         */
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
            logger.info("info level");
            logger.warn("warn level");
            logger.error("error level");
            logger.fatal("fatal level");
            logger.info("项目启动成功------------------------------"); // info级别的信息
            // 进行启动测试位
        }

        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        }
    }
