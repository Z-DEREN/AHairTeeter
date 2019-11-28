package com.AHairTeeter.Main.IPpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;
import com.AHairTeeter.Main.IPpool.DaoServiceImpl.InternetProtocolDaoServiceImpl;
import com.AHairTeeter.Main.Vo.InterceptorUser;

/**
 * ip代理池
 * 
 * @author 好人
 *
 */
@Component
public class IPpool extends IoHandlerAdapter {
	Tool Tool = new Tool();

	@Autowired
	protected InternetProtocolDaoServiceImpl InternetProtocolDaoServiceImpl;
	private static IPpool IPpool;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		IPpool = this;
		IPpool.InternetProtocolDaoServiceImpl = this.InternetProtocolDaoServiceImpl;
		// 初使化时将已静态化的testService实例化
	}

	private static final Logger logger = LogManager.getLogger(IPpool.class.getName());

	Spiders Spiders = new Spiders();

	private String IPTest = "http://pv.sohu.com/cityjson?ie=utf-8";// ip测试页

/////////////////////////// 爬取//////////////////////

	private String ChinaIPCryp = "https://www.xicidaili.com/nn/";// 国内高匿ip

	/**
	 * 获取国内高匿ip
	 * 
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> Get61ChinaIPCryp(int pagination) {
		Spiders Spiders = new Spiders();
		IOLocalFile IOLocalFile = new IOLocalFile();
		List<Map<String, Object>> ListIP = new ArrayList<Map<String, Object>>();
//		ListIP.addAll(Getiplist(IOLocalFile.output("F:\\rdzgsq\\Laboratory\\爬虫\\iptest1.txt")));
//		return ListIP;

		// 爬取国内免费高匿ip:https://www.xicidaili.com/nn/
		String url = "";// 存储当前页url
		String text = "";// 存储爬取到的页面源码
		// 爬取前20页ip数据
		for (int i = 1; i <= pagination; i++) {
			url = ChinaIPCryp + i;
			text = Spiders.spiders(url, 99999);
			ListIP.addAll(Getiplist(text));
			try {
				Thread.sleep(1000 * 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ListIP;
	}

	public int num = 1;
	Set<String> setlist = new HashSet<String>();

	public List<Map<String, Object>> Getiplist(String text) {
		List<Map<String, Object>> ListIP = new ArrayList<Map<String, Object>>();
		String area = "";// 地区
		String textno1 = "";
		int beginIndex = 0;// 筛选用存储下标位
		int endIndex = 0;// 筛选用存储下标位
		beginIndex = text.indexOf("<tbody>");
		endIndex = text.indexOf("</tbody>");
		text = text.substring(beginIndex, endIndex);
//		System.out.println("源码" + text);
		String[] textno3 = text.split("</tr>");
		for (int i = 1; i < textno3.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String textarray = textno3[i];
			// 去除干扰项
			textarray = textarray.replace("<td>", "");
			textarray = textarray.replace(" ", "");
			textarray = textarray.replace("	", "");
			textarray = textarray.replace("\n", "");
			textarray = textarray.replace("\r", "");
			textarray = textarray.replace("\t", "");
			String[] textno4 = textarray.split("</td>");
			if (textno4.length > 2) {
				endIndex = textno4[3].indexOf(">");
				if (endIndex > 0) {
					textno1 = textno4[3].substring(endIndex);
					endIndex = textno1.indexOf("</a>");
					area = textno1.substring(1, endIndex);
				} else {
					area = "未知";
				}
				logger.info("获取国内IP-----num:" + num + "---" + textno4[1] + ":" + textno4[2] + ":" + area);
//				map.put("SID",GetSID("ippoolinspect"));
				map.put("ADI", "62");
				map.put("ip", textno4[1]);
				map.put("port", textno4[2]);
				map.put("area", area);
				map.put("SAVETIME", Tool.GetNewDateTime(2));
				map.put("TYPE", "1");

				IPpool.InternetProtocolDaoServiceImpl.SaveIPList(map);

				num++;
			}
		}
		return ListIP;
	}

	///////////////////////////////////////// {测试ip用}/////////////////////////////////////////////////////////////

	/**
	 * 测试ip方法(不推荐手动调用,方法执行时间较长)
	 * 
	 * @param ChinaIPList
	 * @return 测试通过ip map键ip地址:"ip",端口号:"port",地名:"area",响应时间(毫秒):"msec"
	 * 
	 */
	public void GetPerfectCHIP(Map<String, Object> ChinaIPList, String present, int number) {
		Spiders Spiders = new Spiders();
		List<String> list = new ArrayList<String>();
		String Iptext = "";
		logger.info("剩余:" + number + ",正在测试的是" + ChinaIPList.get("AREA") + "--" + ChinaIPList.get("IP") + "--"
				+ ChinaIPList.get("PORT"));
		long startTime = System.currentTimeMillis(); // 获取开始时间
		System.getProperties().setProperty("http.proxyHost", ChinaIPList.get("IP").toString());
		System.getProperties().setProperty("http.proxyPort", ChinaIPList.get("PORT").toString());
		Iptext = Spiders.spiders(IPTest, 99999);// 测试接口
		long endTime = System.currentTimeMillis();
		// 首先判断连接消耗的时间,再判断
		Long time = endTime - startTime;
		// 是否为本机ip
		if (!Iptext.contains(present)) {
			logger.info("true	测试通过==IP-----" + ChinaIPList.get("IP") + "--" + ChinaIPList.get("PORT") + ":"
					+ ChinaIPList.get("AREA") + ":" + time + "毫秒");
			IPpool.InternetProtocolDaoServiceImpl.SaveIPlistnum(time.toString(), ChinaIPList);
		} else {
			logger.info("false	测试失败==IP-----" + ChinaIPList.get("IP") + "--" + ChinaIPList.get("PORT") + ":"
					+ ChinaIPList.get("AREA") + ":" + time / 1000 + "秒");
		}
		IPpool.InternetProtocolDaoServiceImpl.DelTestIP(ChinaIPList);
	}

	/**
	 * 获取本机ip端口地区
	 * 
	 */
	public String Localip() {

		String text = Spiders.spiders("http://pv.sohu.com/cityjson?ie=utf-8", 99999);// 测试接口
		int beginIndex;
		int endIndex;
		beginIndex = text.indexOf("cip");
		endIndex = text.indexOf("\",");
		logger.info("本地ip省市IP为:" + text.substring(beginIndex + 7, endIndex));
		return text;
	}
	///////////////////////////////////////// {测试ip用}/////////////////////////////////////////////////////////////

//	/**
//	 * 根据所给ip查询ip地址
//	 * 
//	 * @param ip              指定ip
//	 * @param InterceptorUser 实体
//	 * @return
//	 */
//	public InterceptorUser GetaccordingIP(String ip, InterceptorUser InterceptorUser) {
//		String text = Spiders.spiders("http://www.ip138.com/ips1388.asp?ip=" + ip + "&action=2", 99999);// 测试接口
//		int beginIndex;
//		int endIndex;
//		String IPdata_no1 = "";
//		String IPdata_no2 = "";
//		String IPdata_no3 = "";
//		String compIPv6 = "";
//		String mapIPv6 = "";
//
//		beginIndex = text.indexOf("本站数据");
//		if (beginIndex > 0) {
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		IPdata_no1 = text.substring(5, endIndex);
//		InterceptorUser.setIPdata_no1(IPdata_no1);
//
//		beginIndex = text.indexOf("参考数据1");
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		IPdata_no2 = text.substring(6, endIndex);
//		InterceptorUser.setIPdata_no2(IPdata_no2);
//
//		beginIndex = text.indexOf("参考数据2");
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		IPdata_no3 = text.substring(6, endIndex);
//		InterceptorUser.setIPdata_no3(IPdata_no3);
//
//		beginIndex = text.indexOf("兼容IPv6地址");
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		compIPv6 = text.substring(11, endIndex);
//		InterceptorUser.setCompIPv6(compIPv6);
//
//		beginIndex = text.indexOf("映射IPv6地址");
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		mapIPv6 = text.substring(11, endIndex);
//		InterceptorUser.setMapIPv6(mapIPv6);
//		}else {
//			InterceptorUser.setIPdata_no1("");
//			InterceptorUser.setIPdata_no1("");
//			InterceptorUser.setIPdata_no1("");
//			InterceptorUser.setCompIPv6("");
//			InterceptorUser.setMapIPv6("");
//		}
//		
//		return InterceptorUser;
//	}

}
