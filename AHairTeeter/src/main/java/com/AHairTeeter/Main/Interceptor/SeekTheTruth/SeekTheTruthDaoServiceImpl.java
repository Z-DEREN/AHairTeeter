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
	public ZUSER Login(ZUSER zuser) {
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
				+ "Country, Province, City, Isp, District )" + "	VALUES"
				+ "	(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		Object[] value = { Tool.GetNewDateTime(2), InterceptorUser.getUsername(), InterceptorUser.getVsIP(),
				InterceptorUser.getVsPor(), InterceptorUser.getUserAgent(), InterceptorUser.getVsBroNamer(),
				InterceptorUser.getVsOS(), InterceptorUser.getVsOSName(), ToolDaoImpl.GetSID("crewalllog"),
				InterceptorUser.getCountry(), InterceptorUser.getProvince(), InterceptorUser.getCity(),
				InterceptorUser.getIsp(), InterceptorUser.getDistrict() };

		TF = ToolDaoImpl.SingleSaveUpdeteSql(sql, value);
		return TF;
	}

	/**
	 * 收集访问者信息 并且入库保存
	 * 
	 * @return
	 */
	public boolean FBIexamineWaterMeter(HttpServletRequest request, HttpSession session, ZUSER zuser) {
		boolean TF = false;

		// 访问者端口号
		String Port = request.getRemotePort() + "";
		// 访问者IP地址
		String HostIP = request.getRemoteHost();
//		/访问者Addr地址
		String AddrIP = request.getRemoteAddr();
		// 访问者SessionId
		String SessionId = request.getRequestedSessionId();
		// 访问者当前访问的URI
		String URI = request.getRequestURI();

		logger.info("打印访问者携带的其他信息"); // info级别的信息
		logger.info("访问者"); // info级别的信息
		logger.info("访问者IP地址" + HostIP); // info级别的信息
		logger.info("访问者Addr地址" + AddrIP); // info级别的信息
		logger.info("访问者端口号" + Port); // info级别的信息
		logger.info("访问者SessionId" + SessionId); // info级别的信息
		logger.info("访问者当前访问的URI" + request.getRequestURI()); // info级别的信息
		logger.info("打印访问者携带的其他信息结束"); // info级别的信息

		// 访问者信息
		InterceptorUser InterceptorUser = new InterceptorUser();
		ZUSER userInfo = (ZUSER) session.getAttribute("GuesswhoIam");
		if (userInfo != null && userInfo.getD7788b7e0ba4b6e3aa57b35bbf93dfc6() != null
				&& !userInfo.getD7788b7e0ba4b6e3aa57b35bbf93dfc6().equals("")) {
			logger.info("访问者获得授权已登录"); // info级别的信息
			System.out.println(userInfo.getNAME());
			System.out.println(userInfo.getPass());
		} else {
			logger.info("访问者未登录"); // info级别的信息
			if (zuser != null && zuser.getNAME() != null) {
				InterceptorUser.setUsername(zuser.getNAME());
			} else {
				InterceptorUser.setUsername("");
			}
//			获取浏览器信息
			InterceptorUser = GetinvadeINFO(request, InterceptorUser);
			// 保存ip
			InterceptorUser.setVsIP(HostIP);
			// 保存端口号
			InterceptorUser.setVsPor(Port);

			IPpool IPpool = new IPpool();

			// 查询访问者ip地址

			Map<String, Object> map = Tool.GetIPJsonMaps(HostIP);

			InterceptorUser.setCity(map.get("city").toString());
			InterceptorUser.setCountry(map.get("Country").toString());
			InterceptorUser.setProvince(map.get("Province").toString());
			InterceptorUser.setIsp(map.get("Isp").toString());
			InterceptorUser.setDistrict(map.get("District").toString());

//			InterceptorUser = IPpool.GetaccordingIP(request.getLocalAddr(), InterceptorUser);

			insert(InterceptorUser);

//			 访问者真实ip
//			getIpAddr(request);

		}
		String host = request.getRemoteHost();
		logger.info("IP为---->>> " + host + " <<<-----访问了系统"); // info级别的信息
//		System.out.println("打印cookie");
//		logger.info("打印访问者携带cookie"); // info级别的信息
//		Cookie[] zcookie = request.getCookies();
//		if (zcookie != null && zcookie.length > 0) {
//			for (int i = 0; i < zcookie.length; i++) {
//				logger.info("cookie打印:--" + zcookie[i]); // info级别的信息
//			}
//		} else {
//			logger.info("<<<访问者未携带cookie>>>"); // info级别的信息
//		}
//		logger.info("打印cookie结束"); // info级别的信息

		return TF;
	}

	/**
	 * 获取访问者的操作系统以及浏览器信息
	 * 
	 * @return
	 */
	public InterceptorUser GetinvadeINFO(HttpServletRequest request, InterceptorUser InterceptorUser) {
		String winUserAgent = request.getHeader("User-Agent");
		Usaeragent Usaeragent = new Usaeragent();
		System.out.println("浏览器组：" + Usaeragent.getBorderGroup(winUserAgent));
		System.out.println("浏览器名字：" + Usaeragent.getBorderName(winUserAgent));
		System.out.println("浏览器类型" + Usaeragent.getBorderType(winUserAgent));
		System.out.println("浏览器生产商：" + Usaeragent.getBrowserManufacturer(winUserAgent));
		System.out.println("浏览器版本：" + Usaeragent.getBrowserVersion(winUserAgent));
		System.out.println("设备生产厂商:" + Usaeragent.getDeviceManufacturer(winUserAgent));
		System.out.println("设备类型:" + Usaeragent.getDevicetype(winUserAgent));
		System.out.println("设备操作系统：" + Usaeragent.getOs(winUserAgent));
		System.out.println("操作系统的名字：" + Usaeragent.getOsName(winUserAgent));
		System.out.println("操作系统的版本号：" + Usaeragent.getOsVersion(winUserAgent));
		System.out.println("操作系统浏览器的渲染引擎:" + Usaeragent.getBorderRenderingEngine(winUserAgent));

		// 访问者UserAgent
		InterceptorUser.setUserAgent(winUserAgent);
		// 浏览器名字
		InterceptorUser.setVsBroNamer(Usaeragent.getBorderName(winUserAgent));
		// 浏览器版本
		InterceptorUser.setVsBroNamer(Usaeragent.getBrowserVersion(winUserAgent));
		// 访问者操作系统名
		InterceptorUser.setVsOS(Usaeragent.getOs(winUserAgent));
		// 访问者操作系统版本
		InterceptorUser.setVsOSName(Usaeragent.getOsName(winUserAgent));

		return InterceptorUser;
	}

	/**
	 * 获取访问者所有ip
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {

		System.out.println("测试方法一获取真实ip们");
		List<String> list = new ArrayList<String>();

		list.add(request.getHeader("X-Forwarded-For"));
		list.add(request.getHeader("Proxy-Client-IP"));
		list.add(request.getHeader("WL-Proxy-Client-IP"));
		list.add(request.getHeader("HTTP_CLIENT_IP"));
		list.add(request.getHeader("HTTP_X_FORWARDED_FOR"));
		for (int i = 0; i < list.size(); i++) {
			logger.info("真实ip:" + list.get(i)); // info级别的信息
		}

		System.out.println("测试方法一结束");

		String ipAddress = null;
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			System.out.println(ipAddress);
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
					// 根据网卡取本机配置的IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress = inet.getHostAddress();
				}
			}
			// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
			if (ipAddress != null && ipAddress.length() > 15) {
				System.out.println(ipAddress);
				// "***.***.***.***".length()
				// = 15
//				if (ipAddress.indexOf(",") > 0) {
//					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//				}
			}
		} catch (Exception e) {
			ipAddress = "";
		}
//		 ipAddress = this.getRequest().getRemoteAddr();
		return ipAddress;
	}

}
