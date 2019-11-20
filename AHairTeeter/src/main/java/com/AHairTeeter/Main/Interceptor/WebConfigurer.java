package com.AHairTeeter.Main.Interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

	@Autowired
	private LoginHandlerInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
		// 拦截路径
		loginRegistry.addPathPatterns("/**");// .excludePathPatterns("/view/toLogin","/login","/view/toReg","/loginout");
		// 排除路径
		loginRegistry.excludePathPatterns("/login");
		loginRegistry.excludePathPatterns("/login/**");
		loginRegistry.excludePathPatterns("/login/dataValidation");
		
		loginRegistry.excludePathPatterns("/login/ufo233");
		loginRegistry.excludePathPatterns("/ufo233");

	}

	// 这个方法是用来配置静态资源的，比如html，js，css，等等
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

//		registry.addResourceHandler("");
	}
}
