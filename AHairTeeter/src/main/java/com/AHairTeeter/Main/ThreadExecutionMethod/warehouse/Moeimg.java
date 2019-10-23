package com.AHairTeeter.Main.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;

/**
 * ACG图片链接获取方法
 * 
 * @author 好人
 *
 */
@Component
public class Moeimg extends IoHandlerAdapter {

	private final String url = "http://moeimg.net/";
	Tool Tool = new Tool();
	private int ADI = 21;

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static Moeimg Moeimg;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		Moeimg = this;
		Moeimg.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}
	
	
	private static final Logger logger = LogManager.getLogger(Moeimg.class.getName());

	/**
	 * 根据页码进行爬取
	 * 
	 * @param     no1-10000
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> moeimg_img(int no1, int no2) {

		/**
		 * 爬虫位 目前爬虫没有使用ip池,会被锁ip
		 */
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = no1; i <= no2; i++) {
			try {
				System.out.println("正在进行第:" + i + "页数据爬取:" + url + i);
				String text = spiders.spiders((url + i + ".html"), 1);
				listmap.addAll(moeimgSub(text, i, url + i));
				Thread.sleep(1000 * 10);
			} finally {
				continue;
			}
		}
		return listmap;
	}

	public List<Map<String, Object>> moeimgSub(String text, int num, String referrerurl) {
		Date date = new Date();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String urlList = "";
		beginIndex = text.indexOf("contents");
		if (beginIndex < 0) {
			logger.info(" 当前页数据未获取到------------------------------------------------------------"); // info级别的信息
			return listmap;
		}
		try {
			textno1 = text.substring(beginIndex);
			beginIndex = textno1.indexOf("<div class=\"pc_ad\">");
			textno1 = textno1.substring(beginIndex);
			beginIndex = textno1.lastIndexOf("<div class=\"pc_ad\">");
			textno1 = textno1.substring(0, beginIndex);
			String[] textno3 = textno1.split("<div class=\"box\">");
			for (int i = 1; i < textno3.length - 1; i++) {
				beginIndex = textno3[i].indexOf("<a href=\"");
				urlList = textno3[i].substring(beginIndex + 9);
				endIndex = urlList.indexOf("\" target=\"_blank\"");
				urlList = urlList.substring(0, endIndex);
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("SID", null);// 字符串id
				map.put("ADI", ADI);// 特殊DI头
				map.put("ZDI", null);// 特殊DI码
				map.put("type", 1);// 存储类型
				map.put("classify", url);// 链接
				map.put("title", "图片");// 存储标题
				map.put("line", null);// 存储行数据
				map.put("url", urlList);// 链接
				map.put("uniqueid", null);// 存储数据自带id
				map.put("text", null);// 大容量主体数据存储体
				map.put("recorddate", null);// 数据内时间
				map.put("acquiredate", Tool.GetNewDateTime(2));// 爬取时间
				if(Moeimg.ToolDaoImpl.SaveOneCrawlersql(map)) {
					// 入库操作
					// 入单库双库判断
					listmap.add(map);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return listmap;
		}
	}
}
