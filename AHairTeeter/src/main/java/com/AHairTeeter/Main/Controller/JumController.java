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
	 * ��¼��֤,�ɹ���¼����ת��ҳ,�����¼ҳ
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ufo233")
	public Object go(HttpServletRequest request,HttpSession session,ModelAndView model) {
		logger.info(" �����¼ҳ��------------------------------------------------------------"); // info�������Ϣ
		//�ռ���������Ϣ
		SeekTheTruth.FBIexamineWaterMeter(request,session,null);
		model.addObject("Time", Tool.GetNewDateTime(2));
		ZUSER zuser = new ZUSER();
		zuser = (ZUSER) session.getAttribute("GuesswhoIam");
		if (zuser == null) { // �ж��û��Ƿ���ڣ������ڷ��ص�¼���棬�������أ�����ͨ�����أ����е�����ҳ��
			logger.info(" �û�δ��¼�����¼ҳ...------------------------------------------------------------"); // info�������Ϣ
			model.setViewName("main/GuesswhoIam/GuesswhoIam");
			return model;
		} else {
			logger.info(" �û��Ѿ���¼...------------------------------------------------------------"); // info�������Ϣ
			model.setViewName("main/HOME/navigation");
			return model;
		}
	}
	
	/**
	 * ��¼��Ϣ��֤
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
		logger.info("IPΪ---->>> " + host + " <<<-----������ϵͳ"); // info�������Ϣ
		logger.info(" �û���:" + zusername + " ����:" + zpassword
				+ "-----------------------------------------------------------"); // info�������Ϣ

		if (("".equals(zusername) || zusername == null) || ("".equals(zpassword) || zpassword == null)) {
			logger.info(" �û���������Ϊ��------------------------------------------------------------:"); // info�������Ϣ
			model.setViewName("login");// ���ص�¼ҳ
			return model;
		}
		ZUSER zuser = new ZUSER();
		zuser.setNAME(zusername);
		zuser.setPass(zpassword);
		//��¼��֤
		SeekTheTruth.FBIexamineWaterMeter(request,session,zuser);
		zuser = SeekTheTruthDaoServiceImpl.VerifyVisitorinfo(zuser);
		//�û�����Ϣ���ݿ���֤
		if (zuser.getD7788b7e0ba4b6e3aa57b35bbf93dfc6() != null && !"".equals(zuser.getD7788b7e0ba4b6e3aa57b35bbf93dfc6())) {
			logger.info(" �û���¼�ɹ�------------------------------------------------------------:"); // info�������Ϣ
			logger.info("��ӭ"+zuser.getNAME()+"��¼�ɹ�------------------------------------------------------------:"); // info�������Ϣ
		}else {
			logger.info(" �û������˺Ż��������------------------------------------------------------------:"); // info�������Ϣ
			model.setViewName("redirect:/ufo233");// ���ص�¼ҳ
			return model;
		}

		session.setAttribute("GuesswhoIam", zuser);
		logger.info(" ��ʹ�õ�¼ҳ������֤,�ӵ�¼ҳ������ת------------------------------------------------------------:"); // info�������Ϣ
		model.setViewName("redirect:/ufo233");// ���ص�¼ҳ
		return model;
	}
	
	/**
	 * ע����¼״̬
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Logout")
	public Object ZuserLogout(HttpSession session,ModelAndView model) {
		ZUSER zuser = (ZUSER) session.getAttribute("GuesswhoIam");
		
		logger.info(zuser.getNAME()+" �Ե�¼״̬ע��,ע������ת��¼ҳ------------------------------------------------------------:"); // info�������Ϣ
	
		session.removeAttribute("GuesswhoIam");
		model.setViewName("redirect:/ufo233");// ���ص�¼ҳ
		return model;
	}
	
	
	
	
	
	
	
	
	
	
	

}
