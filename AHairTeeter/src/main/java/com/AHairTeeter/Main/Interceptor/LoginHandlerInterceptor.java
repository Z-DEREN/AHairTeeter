package com.AHairTeeter.Main.Interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.AHairTeeter.Main.Controller.JumController;
import com.AHairTeeter.Main.Interceptor.SeekTheTruth.SeekTheTruthDaoServiceImpl;
import com.AHairTeeter.Main.Vo.ZUSER;

@Configuration
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LogManager.getLogger(LoginHandlerInterceptor.class.getName());

	
	@Autowired
	private SeekTheTruthDaoServiceImpl SeekTheTruth ;
	
	/**
	 * ���������������Ƚ���ķ��� ����false���ټ���ִ�� ����true�����ִ��
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		SeekTheTruth.FBIexamineWaterMeter(request,session);
		
		///////////////////////// ��¼��¼///////////////////////////
		ZUSER userInfo = (ZUSER) session.getAttribute("GuesswhoIam");
		if (userInfo.getD7788b7e0ba4b6e3aa57b35bbf93dfc6() != null && !userInfo.getD7788b7e0ba4b6e3aa57b35bbf93dfc6().equals("")) {
			System.out.println("�û��Ѿ���¼");
		}else {
			System.out.println("�û�û�е�¼");
			Map<String, Object> rtnMap = new HashMap<String, Object>();
			response.sendRedirect("/ufo233");
			return false;
		}
		return true;
	}
	
	  
	  
	/**
	 * ������ͼʱִ�У��������������쳣������¼����־��
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2,
			Exception exception) {
		// -----------------//
	}

	/**
	 * - ������ͼ֮ǰִ�У������޸�ModelAndView
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// ----------------------------//
	}
}
