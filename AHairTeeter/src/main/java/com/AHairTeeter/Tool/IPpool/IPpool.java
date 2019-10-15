package com.AHairTeeter.Tool.IPpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.AHairTeeter.Tool.Tool;

/**
 * ip代理池
 * 
 * @author 好人
 *
 */
public class IPpool {
	private static final Logger logger = LogManager.getLogger(IPpool.class.getName());

	Tool Tool = new Tool();
	private String ChinaIPCryp = "https://www.xicidaili.com/nn/";//国内高匿ip
	private String IPTest = "http://pv.sohu.com/cityjson?ie=utf-8";//ip测试页

	/**
	 * 保存至 IP库
	 * 
	 * @param IPmap map集合数据
	 * @param type  对应ZDI类型
	 */
	public String save_IP(Map<String, String> IPmap, String type) {
		String sql = "INSERT INTO ippool (ZDI,IP,PORT,AREA,MSEC,UPDATETIME,TYPE) VALUES";
		sql += "(" + Tool.GetNewZDInum(type) + ",'" + IPmap.get("ip") + "'," + IPmap.get("port") + ",'"
				+ IPmap.get("area") + "'," + IPmap.get("msec") + ",'" + Tool.GetNewDateTime(2) + "','" + type + "')";
		return sql;
	}

	/**
	 * 获取国内高匿ip
	 * 
	 * @throws InterruptedException
	 */
	public List<Map<String, String>> Get61ChinaIPCryp(int pagination) {
		// 爬取国内免费高匿ip:https://www.xicidaili.com/nn/
		String url = "";// 存储当前页url
		String text = "";// 存储爬取到的页面源码

		List<Map<String, String>> ListIP = new ArrayList<Map<String, String>>();
		// 爬取前20页ip数据
		for (int i = 1; i <= pagination; i++) {
			url = ChinaIPCryp + i;
			text = Tool.BriefnessAcquire(url);
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

	/**
	 * 测试ip方法(不推荐手动调用,方法执行时间较长)
	 * 
	 * @param ChinaIPList
	 * @return 测试通过ip map键ip地址:"ip",端口号:"port",地名:"area",响应时间(毫秒):"msec"
	 * 
	 */
	public Map<String, String> GetPerfectCHIP(Map<String, String> ChinaIPList, String present) {
		List<String> list = new ArrayList<String>();
		String Iptext = "";
		System.out.println("正在测试的是" + ChinaIPList.get("area") + "--" + ChinaIPList.get("ip") + "--" + ChinaIPList.get("port"));
		long startTime = System.currentTimeMillis(); // 获取开始时间
		System.getProperties().setProperty("http.proxyHost", ChinaIPList.get("ip"));
		System.getProperties().setProperty("http.proxyPort", ChinaIPList.get("port"));
		Iptext = Tool.BriefnessAcquire(IPTest);// 测试接口
		long endTime = System.currentTimeMillis();
		// 首先判断连接消耗的时间,再判断
		Long time = endTime - startTime;
		// 是否为本机ip
		if (!Iptext.contains(present)) {
			Map<String, String> retuenListMap = new HashMap<String, String>();
			logger.info("true	测试通过==IP-----" + ChinaIPList.get("ip") + ":" + ChinaIPList.get("port") + ":" + ChinaIPList.get("area") + ":"
					+ time + "毫秒");
			retuenListMap.put("ip", ChinaIPList.get("ip"));
			retuenListMap.put("port", ChinaIPList.get("port"));
			retuenListMap.put("area", ChinaIPList.get("area"));
			if(time == 0) {
				retuenListMap.put("msec", "1");
			}else {
				retuenListMap.put("msec", time.toString());
			}
			
			
			
			return retuenListMap;
		} else {
			logger.info("false	测试失败==IP-----" + ChinaIPList.get("ip") + ":" + ChinaIPList.get("port") + ":" + ChinaIPList.get("area") + ":"
					+ time / 1000 + "秒");
			return null;
		}
		
	}

	/**
	 * 获取本机ip端口地区
	 * 
	 */
	public String Localip() {
		String text = Tool.BriefnessAcquire("http://pv.sohu.com/cityjson?ie=utf-8");// 测试接口
		int beginIndex;
		int endIndex;
		beginIndex = text.indexOf("cip");
		endIndex = text.indexOf("\",");
		logger.info("本地ip省市IP为:" + text.substring(beginIndex + 7, endIndex));
		return text;
	}

/////////////////////////// 爬取//////////////////////

	public int num = 1;
	Set<String> setlist = new HashSet<String>();

	public List<Map<String, String>> Getiplist(String text) {
		List<Map<String, String>> ListIP = new ArrayList<Map<String, String>>();
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
			Map<String, String> map = new HashMap<String, String>();
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
				map.put("ip", textno4[1]);
				map.put("port", textno4[2]);
				map.put("area", area);
				if (!setlist.contains(textno4[1] + ":" + textno4[2] + ":" + area)) {
					setlist.add(textno4[1] + ":" + textno4[2] + ":" + area);
					ListIP.add(map);
					num++;
				}
			}
		}
		return ListIP;
	}

}
