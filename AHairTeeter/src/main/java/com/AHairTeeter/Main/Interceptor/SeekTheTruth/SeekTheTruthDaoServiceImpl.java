package com.AHairTeeter.Main.Interceptor.SeekTheTruth;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.IPpool.IPpool;
import com.AHairTeeter.Main.IPpool.DaoServiceImpl.InternetProtocolDaoServiceImpl;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Main.Vo.InterceptorUser;
import com.AHairTeeter.Main.Vo.ZUSER;
import com.AHairTeeter.Tool.Tool;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

@Service
public class SeekTheTruthDaoServiceImpl {

	private static final Logger logger = LogManager.getLogger(SeekTheTruthDaoServiceImpl.class.getName());

	@Resource
	public ToolDaoImpl ToolDaoImpl;

	Tool Tool = new Tool();

	/**
	 * 核实登录的人员信息 获取数据
	 */
	public Map<String, Object> USERJDBCINFO(String Zname) {
		Map<String, Object> MAP = new HashMap<String, Object>();

		logger.info("用户--" + Zname + "--即将登录,获取用户UserEPKEY");
		String UserEPKEYSql = "SELECT SPARE1,SPARE2,SPARE3,SPARE4,SPARE5  FROM userz WHERE NAME = ? ";
		List<Object> UserEPKEYS = (List<Object>) ToolDaoImpl.GetSelObjsql(UserEPKEYSql, new Object[] { Zname },
				"ArrayObject");
		MAP.put("UserEPKEY", UserEPKEYS);
		logger.info("用户--" + Zname + "--即将登录,获取用户UserPuzzlekey");
		String UserPuzzlekeySql = "SELECT puzzlekey FROM userz WHERE NAME = ? ";
		String Puzzlekey = (String) ToolDaoImpl.GetSelObjsql(UserPuzzlekeySql, new Object[] { Zname }, "String");
		MAP.put("UserPuzzlekey", Puzzlekey);
		logger.info("用户--" + Zname + "--即将登录,获取用户MD5DI");
		String MD5DISql = "SELECT MD5DI FROM userz WHERE NAME = ? ";
		String MD5DI = (String) ToolDaoImpl.GetSelObjsql(MD5DISql, new Object[] { Zname }, "String");

		MAP.put("MD5DI", MD5DI);

		return MAP;
	}

	/**
	 * 核实登录的人员信息 获取数据
	 */
	public boolean Userzusername(String Zname) {
		boolean TF = false;
		logger.info("用户--" + Zname + "--即将登录,获取用户UserPuzzlekey");
		String userSeekSeleSql = "SELECT COUNT(NAME)  FROM userz WHERE  NAME =  ? ";
		Long name = (Long) ToolDaoImpl.GetSelObjsql(userSeekSeleSql, new Object[] { Zname }, "String");
		if (name == 1) {
			TF = true;
		}
		return TF;
	}

	/**
	 * 核实登录的人员信息 获取数据
	 */
	public ZUSER VerifyVisitorinfo(ZUSER zuser) {
		if (Userzusername(zuser.getNAME())) {
			logger.info("访问者输入用户名为--" + zuser.getNAME() + "--进行登录");
			Map<String, Object> MAP = USERJDBCINFO(zuser.getNAME());
			zuser.setZMD5(MAP.get("MD5DI").toString());
			List<Object> EPKEY = (List<Object>) MAP.get("UserEPKEY");
			String puzzlekey = (String) MAP.get("UserPuzzlekey");
			zuser = Tool.GetInfoEpilepsy(EPKEY, puzzlekey, zuser);
		}
		return zuser;
	}

	/**
	 * 保存访问者的数据信息
	 * 
	 * 
	 */
	public boolean insert(InterceptorUser InterceptorUser) {
		boolean TF = false;
		String sql = "INSERT INTO crewalllog "
				+ "	(DATETIME, NAME, vsIP, vsPor, UserAgent, vsBroNamer, vsOS, vsOSName, SID , "
				+ "Country, Province, City, Isp, District )" + "	VALUES" + "	(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		Object[] value = { Tool.GetNewDateTime(2), InterceptorUser.getUsername(), InterceptorUser.getVsIP(),
				InterceptorUser.getVsPor(), InterceptorUser.getUserAgent(), InterceptorUser.getVsBroNamer(),
				InterceptorUser.getVsOS(), InterceptorUser.getVsOSName(), ToolDaoImpl.GetSID("crewalllog"),
				InterceptorUser.getCountry(), InterceptorUser.getProvince(), InterceptorUser.getCity(),
				InterceptorUser.getIsp(), InterceptorUser.getDistrict() };
		TF = ToolDaoImpl.SingleSaveUpdeteSql(sql, value);
		return TF;
	}

	
	
	/**
	 * 数据库获取该ip是否访问过
	 * @param InterceptorUser
	 * @return
	 */
	public InterceptorUser SeeVisitorIPrecord(InterceptorUser InterceptorUser,String HostIP) {
		String sql = " SELECT country, province,city,isp,district FROM crewalllog WHERE vsip=? AND country IS NOT NULL LIMIT 0, 1;";
		Object[] value = { HostIP };
		Map<String, Object>  HashMap = (Map<String, Object>) ToolDaoImpl.GetSelObjsql(sql, value, "HashMap");
		if(HashMap != null && HashMap.size() > 0) {
			logger.info("数据库拥有该ip信息,该访问者非新访问者");
			InterceptorUser.setCity(HashMap.get("city").toString());
			InterceptorUser.setCountry(HashMap.get("country").toString());
			InterceptorUser.setProvince(HashMap.get("province").toString());
			InterceptorUser.setIsp(HashMap.get("isp").toString());
			InterceptorUser.setDistrict(HashMap.get("district").toString());
		}else {
			logger.info("未获取到访问者访问记录,该访问者未新访问者");
		}
		
		return InterceptorUser;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
