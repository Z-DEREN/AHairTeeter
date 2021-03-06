package com.zdr.ahairteeter.demo.Main.Interceptor;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zdr.ahairteeter.demo.Main.Interceptor.SeekTheTruth.SeekTheTruth;
import com.zdr.ahairteeter.demo.Main.Interceptor.SeekTheTruth.SeekTheTruthDaoServiceImpl;
import com.zdr.ahairteeter.demo.Main.Vo.ZUSER;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.zdr.ahairteeter.demo.Main.Interceptor.SeekTheTruth.SeekTheTruthDaoServiceImpl;
import com.zdr.ahairteeter.demo.Main.Interceptor.SeekTheTruth.SeekTheTruth;

@Configuration
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LogManager.getLogger(LoginHandlerInterceptor.class.getName());

	
	@Autowired
	private SeekTheTruthDaoServiceImpl SeekTheTruthDaoServiceImpl;
	
	@Autowired
	private  SeekTheTruth SeekTheTruth;
	
	/**
	 * 进入拦截器后首先进入的方法 返回false则不再继续执行 返回true则继续执行
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		SeekTheTruth.FBIexamineWaterMeter(request,session,null);
		
		///////////////////////// 登录记录///////////////////////////
		ZUSER userInfo = (ZUSER) session.getAttribute("GuesswhoIam");
		if (userInfo != null && userInfo.getD7788b7e0ba4b6e3aa57b35bbf93dfc6() != null && !"".equals(userInfo.getD7788b7e0ba4b6e3aa57b35bbf93dfc6())) {
			System.out.println("用户已经登录");
		}else {
			System.out.println("用户没有登录");
			Map<String, Object> rtnMap = new HashMap<String, Object>();
			response.sendRedirect("/ufo233");
			return false;
		}
		return true;
	}
	
	  
	  
	/**
	 * 生成视图时执行，可以用来处理异常，并记录在日志中
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2,
			Exception exception) {
		// -----------------//
	}

	/**
	 * - 生成视图之前执行，可以修改ModelAndView
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
		// ----------------------------//
	}
}
