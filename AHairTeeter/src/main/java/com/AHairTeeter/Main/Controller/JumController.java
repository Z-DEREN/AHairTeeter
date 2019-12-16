package com.AHairTeeter.Main.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AHairTeeter.Main.Interceptor.SeekTheTruth.SeekTheTruthDaoServiceImpl;
import com.AHairTeeter.Main.Interceptor.SeekTheTruth.SeekTheTruth;
import com.AHairTeeter.Main.Vo.ZUSER;
import com.AHairTeeter.Tool.Tool;

@Controller
@ResponseBody
public class JumController {
	private static final Logger logger = LogManager.getLogger(JumController.class.getName());
	
	Tool Tool =new Tool();
	
	@Autowired
	private SeekTheTruthDaoServiceImpl SeekTheTruthDaoServiceImpl ;
	@Autowired
	private SeekTheTruth SeekTheTruth ;
	
	
	/**
	 * 登录验证,成功登录过跳转主页,否则登录页
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ufo233")
	public Object go(HttpServletRequest request,HttpSession session,ModelAndView model) {
		logger.info(" 进入登录页面------------------------------------------------------------"); // info级别的信息
		//收集访问者信息
		SeekTheTruth.FBIexamineWaterMeter(request,session,null);
		model.addObject("Time", Tool.GetNewDateTime(2));
		ZUSER zuser = new ZUSER();
		zuser = (ZUSER) session.getAttribute("GuesswhoIam");
		if (zuser == null) { // 判断用户是否存在，不存在返回登录界面，继续拦截，存在通过拦截，放行到访问页面
			logger.info(" 用户未登录进入登录页...------------------------------------------------------------"); // info级别的信息
			model.setViewName("main/GuesswhoIam/GuesswhoIam");
			return model;
		} else {
			logger.info(" 用户已经登录...------------------------------------------------------------"); // info级别的信息
			model.setViewName("main/HOME/navigation");
			return model;
		}
	}
	
	/**
	 * 登录信息验证
	 * @param zusername
	 * @param zpassword
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login/dataValidation")
	public ModelAndView go(@RequestParam(value = "02777dd215647ddc8b63844bc9f289cc", required = false) String zusername,
			@RequestParam(value = "d1320cebfd37f60f0faac45c613eb86c", required = false) String zpassword,
			HttpSession session, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		String host = request.getRemoteHost();
		logger.info("IP为---->>> " + host + " <<<-----访问了系统"); // info级别的信息
		logger.info(" 用户名:" + zusername + " 密码:" + zpassword
				+ "-----------------------------------------------------------"); // info级别的信息

		if (("".equals(zusername) || zusername == null) || ("".equals(zpassword) || zpassword == null)) {
			logger.info(" 用户名或密码为空------------------------------------------------------------:"); // info级别的信息
			model.setViewName("login");// 返回登录页
			return model;
		}
		ZUSER zuser = new ZUSER();
		zuser.setNAME(zusername);
		zuser.setPass(zpassword);
		//登录验证
		SeekTheTruth.FBIexamineWaterMeter(request,session,zuser);
		zuser = SeekTheTruthDaoServiceImpl.VerifyVisitorinfo(zuser);
		//用户数信息数据库验证
		if (zuser.getD7788b7e0ba4b6e3aa57b35bbf93dfc6() != null && !"".equals(zuser.getD7788b7e0ba4b6e3aa57b35bbf93dfc6())) {
			logger.info(" 用户登录成功------------------------------------------------------------:"); // info级别的信息
			logger.info("欢迎"+zuser.getNAME()+"登录成功------------------------------------------------------------:"); // info级别的信息
		}else {
			logger.info(" 用户输入账号或密码错误------------------------------------------------------------:"); // info级别的信息
			model.setViewName("redirect:/ufo233");// 返回登录页
			return model;
		}

		session.setAttribute("GuesswhoIam", zuser);
		logger.info(" 在使用登录页进行验证,从登录页进行跳转------------------------------------------------------------:"); // info级别的信息
		model.setViewName("redirect:/ufo233");// 返回登录页
		return model;
	}
	
	/**
	 * 注销登录状态
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Logout")
	public Object ZuserLogout(HttpSession session,ModelAndView model) {
		ZUSER zuser = (ZUSER) session.getAttribute("GuesswhoIam");
		
		logger.info(zuser.getNAME()+" 对登录状态注销,注销完跳转登录页------------------------------------------------------------:"); // info级别的信息
	
		session.removeAttribute("GuesswhoIam");
		model.setViewName("redirect:/ufo233");// 返回登录页
		return model;
	}
	
	
	
	
	
	
	
	
	
	
	

}
