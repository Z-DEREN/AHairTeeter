package com.AHairTeeter.Main.Interceptor.SeekTheTruth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.IPpool.DaoServiceImpl.InternetProtocolDaoServiceImpl;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Main.Vo.ZUSER;
import com.AHairTeeter.Tool.Tool;

@Service
public class SeekTheTruthDaoServiceImpl {
	
	private static final Logger logger = LogManager.getLogger(SeekTheTruthDaoServiceImpl.class.getName());
	
	@Resource
	public ToolDaoImpl ToolDaoImpl;
	
	Tool Tool = new Tool();
	
	/**
	 * ��ʵ��¼����Ա��Ϣ
	 *��ȡ����
	 */
	public Map<String,Object> USERJDBCINFO(String Zname){
		Map<String,Object> MAP = new HashMap<String,Object>();
		
		logger.info("�û�--"+Zname+"--������¼,��ȡ�û�UserEPKEY");
		String UserEPKEYSql =  "SELECT SPARE1,SPARE2,SPARE3,SPARE4,SPARE5  FROM userz WHERE NAME = ? "; 
		List<Object> UserEPKEYS = (List<Object>) ToolDaoImpl.GetSelObjsql(UserEPKEYSql,new Object[] {Zname}, "ArrayObject");
		
		MAP.put("UserEPKEY", UserEPKEYS);
		
		logger.info("�û�--"+Zname+"--������¼,��ȡ�û�UserPuzzlekey");
		String UserPuzzlekeySql =  "SELECT puzzlekey FROM userz WHERE NAME = ? "; 
		String Puzzlekey = (String) ToolDaoImpl.GetSelObjsql(UserPuzzlekeySql,new Object[] {Zname}, "String");
		
		MAP.put("UserPuzzlekey", Puzzlekey);
		
		logger.info("�û�--"+Zname+"--������¼,��ȡ�û�MD5DI");
		String MD5DISql =  "SELECT MD5DI FROM userz WHERE NAME = ? "; 
		String MD5DI = (String) ToolDaoImpl.GetSelObjsql(MD5DISql,new Object[] {Zname}, "String");
		
		MAP.put("MD5DI", MD5DI);
		
		
		return MAP; 
	}
	
	/**
	 * ��ʵ��¼����Ա��Ϣ
	 *��ȡ����
	 */
	public boolean Userzusername(String Zname) {
		boolean TF = false;
		logger.info("�û�--"+Zname+"--������¼,��ȡ�û�UserPuzzlekey");
		String userSeekSeleSql =  "SELECT COUNT(NAME)  FROM userz WHERE  NAME =  ? "; 
		Long name = (Long) ToolDaoImpl.GetSelObjsql(userSeekSeleSql,new Object[] {Zname}, "String");
		if(name==1) {
			TF = true;
		}
		return TF;
	}
	
	
	
	/**
	 * ��ʵ��¼����Ա��Ϣ
	 *��ȡ����
	 */
	public ZUSER Login(ZUSER zuser) {
		if(Userzusername(zuser.getNAME())) {
			logger.info("�����������û���Ϊ--"+zuser.getNAME()+"--���е�¼");
			Map<String,Object> MAP = USERJDBCINFO(zuser.getNAME());
			zuser.setZMD5(MAP.get("MD5DI").toString());
			List<Object> EPKEY = (List<Object>) MAP.get("UserEPKEY");
			String puzzlekey = (String) MAP.get("UserPuzzlekey");
			zuser = Tool.GetInfoEpilepsy(EPKEY,puzzlekey,zuser);
			
		}
		return zuser;
	}
	
	
	
	
	
	
	/**
	 * �ռ���������Ϣ
	 * ������Ᵽ��
	 * @return
	 */
	public boolean FBIexamineWaterMeter(HttpServletRequest request,HttpSession session) {
		boolean TF = false;
		
		
		ZUSER userInfo = (ZUSER) session.getAttribute("GuesswhoIam");
		if (userInfo == null) {
			logger.info("������δ��¼"); // info�������Ϣ
			
			
		}else {
			logger.info("�����߻����Ȩ�ѵ�¼"); // info�������Ϣ
			
			
		}
		
		String host = request.getRemoteHost();
		logger.info("IPΪ---->>> " + host + " <<<-----������ϵͳ"); // info�������Ϣ
		System.out.println("��ӡcookie");
		logger.info("��ӡ������Я��cookie"); // info�������Ϣ
		Cookie[] zcookie = request.getCookies();
		if(zcookie != null && zcookie.length > 0) {
			for (int i = 0; i < zcookie.length; i++) {
				logger.info("cookie��ӡ:--" + zcookie[i]); // info�������Ϣ
			}
		}else {
			logger.info("<<<������δЯ��cookie>>>"); // info�������Ϣ
		}
		logger.info("��ӡcookie����"); // info�������Ϣ
		
		
		logger.info("��ӡ������Я����������Ϣ"); // info�������Ϣ
		Object loginName = request.getSession().getAttribute("user");
		List<String> listStrings = new ArrayList<String>();
		listStrings.add(request.changeSessionId());
		listStrings.add(request.getAuthType());
		listStrings.add(request.getCharacterEncoding());
		listStrings.add(request.getCharacterEncoding());
		listStrings.add(request.getContentLength() + "");
		listStrings.add(request.getContentLengthLong() + "");
		listStrings.add(request.getContentType());
		listStrings.add(request.getLocalAddr());
		listStrings.add(request.getLocalName());
		listStrings.add(request.getLocalPort() + "");
		listStrings.add(request.getMethod());
		listStrings.add(request.getPathInfo());
		listStrings.add(request.getPathTranslated());
		listStrings.add(request.getProtocol());
//		listStrings.add(e);
		for (int i = 0; i < listStrings.size(); i++) {
			logger.info("request��Ϣ:"+listStrings.get(i)); // info�������Ϣ
		}
		logger.info("��ӡ������Я����������Ϣ����"); // info�������Ϣ
		
		
		return TF;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
