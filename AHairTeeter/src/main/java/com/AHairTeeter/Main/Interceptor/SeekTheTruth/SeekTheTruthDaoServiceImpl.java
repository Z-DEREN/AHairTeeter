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
	 * 核实登录的人员信息
	 *获取数据
	 */
	public Map<String,Object> USERJDBCINFO(String Zname){
		Map<String,Object> MAP = new HashMap<String,Object>();
		
		logger.info("用户--"+Zname+"--即将登录,获取用户UserEPKEY");
		String UserEPKEYSql =  "SELECT SPARE1,SPARE2,SPARE3,SPARE4,SPARE5  FROM userz WHERE NAME = ? "; 
		List<Object> UserEPKEYS = (List<Object>) ToolDaoImpl.GetSelObjsql(UserEPKEYSql,new Object[] {Zname}, "ArrayObject");
		
		MAP.put("UserEPKEY", UserEPKEYS);
		
		logger.info("用户--"+Zname+"--即将登录,获取用户UserPuzzlekey");
		String UserPuzzlekeySql =  "SELECT puzzlekey FROM userz WHERE NAME = ? "; 
		String Puzzlekey = (String) ToolDaoImpl.GetSelObjsql(UserPuzzlekeySql,new Object[] {Zname}, "String");
		
		MAP.put("UserPuzzlekey", Puzzlekey);
		
		logger.info("用户--"+Zname+"--即将登录,获取用户MD5DI");
		String MD5DISql =  "SELECT MD5DI FROM userz WHERE NAME = ? "; 
		String MD5DI = (String) ToolDaoImpl.GetSelObjsql(MD5DISql,new Object[] {Zname}, "String");
		
		MAP.put("MD5DI", MD5DI);
		
		
		return MAP; 
	}
	
	/**
	 * 核实登录的人员信息
	 *获取数据
	 */
	public boolean Userzusername(String Zname) {
		boolean TF = false;
		logger.info("用户--"+Zname+"--即将登录,获取用户UserPuzzlekey");
		String userSeekSeleSql =  "SELECT COUNT(NAME)  FROM userz WHERE  NAME =  ? "; 
		Long name = (Long) ToolDaoImpl.GetSelObjsql(userSeekSeleSql,new Object[] {Zname}, "String");
		if(name==1) {
			TF = true;
		}
		return TF;
	}
	
	
	
	/**
	 * 核实登录的人员信息
	 *获取数据
	 */
	public ZUSER Login(ZUSER zuser) {
		if(Userzusername(zuser.getNAME())) {
			logger.info("访问者输入用户名为--"+zuser.getNAME()+"--进行登录");
			Map<String,Object> MAP = USERJDBCINFO(zuser.getNAME());
			zuser.setZMD5(MAP.get("MD5DI").toString());
			List<Object> EPKEY = (List<Object>) MAP.get("UserEPKEY");
			String puzzlekey = (String) MAP.get("UserPuzzlekey");
			zuser = Tool.GetInfoEpilepsy(EPKEY,puzzlekey,zuser);
			
		}
		return zuser;
	}
	
	
	
	
	
	
	/**
	 * 收集访问者信息
	 * 并且入库保存
	 * @return
	 */
	public boolean FBIexamineWaterMeter(HttpServletRequest request,HttpSession session) {
		boolean TF = false;
		
		
		ZUSER userInfo = (ZUSER) session.getAttribute("GuesswhoIam");
		if (userInfo == null) {
			logger.info("访问者未登录"); // info级别的信息
			
			
		}else {
			logger.info("访问者获得授权已登录"); // info级别的信息
			
			
		}
		
		String host = request.getRemoteHost();
		logger.info("IP为---->>> " + host + " <<<-----访问了系统"); // info级别的信息
		System.out.println("打印cookie");
		logger.info("打印访问者携带cookie"); // info级别的信息
		Cookie[] zcookie = request.getCookies();
		if(zcookie != null && zcookie.length > 0) {
			for (int i = 0; i < zcookie.length; i++) {
				logger.info("cookie打印:--" + zcookie[i]); // info级别的信息
			}
		}else {
			logger.info("<<<访问者未携带cookie>>>"); // info级别的信息
		}
		logger.info("打印cookie结束"); // info级别的信息
		
		
		logger.info("打印访问者携带的其他信息"); // info级别的信息
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
			logger.info("request信息:"+listStrings.get(i)); // info级别的信息
		}
		logger.info("打印访问者携带的其他信息结束"); // info级别的信息
		
		
		return TF;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
