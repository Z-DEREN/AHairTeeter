package com.AHairTeeter.Main.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;

/**
 * 
 * 图片链接获取方法(V)
 * 
 * @author 好人
 * 
 */
public class SssGif extends IoHandlerAdapter {
	private int ADI = 20; // 特殊DI头
	Tool Tool = new Tool();
	String url = "https://www.sex.com/";

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static SssGif SssGif;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		SssGif = this;
		SssGif.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}
	
	private static final Logger logger = LogManager.getLogger(SssGif.class.getName());

	/**
	 * 根据页码进行爬取
	 * 
	 * @param no1
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> first_no1(int no1, int no2, String Type) {
		/**
		 * 爬虫位 目前爬虫没有使用ip池,会被锁ip
		 */
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String text = "";
		for (int i = no1; i <= no2; i++) {
			System.out.println("正在进行第:" + i + "页数据爬取:" + url + Type + "/?page=" + i);
			try {
				text = spiders.spiders(url + Type + "/?page=" + i, 99999);
				listmap.addAll(first_no2(text));
				Thread.sleep(1000 * 10);
			} finally {
				continue;
			}
		}
		return listmap;
	}

	public List<Map<String, Object>> first_no2(String text) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String urlList = "";
		beginIndex = text.indexOf("masonry_box ad-big");
		if (beginIndex < 0) {
			logger.info(" 当前页数据未获取到------------------------------------------------------------"); // info级别的信息
			return listmap;
		}
		try {
			textno1 = text.substring(beginIndex);
			String[] textno3 = textno1.split("masonry_box small_pin_box");
			for (int i = 1; i < textno3.length - 1; i++) {
				beginIndex = textno3[i].indexOf("data-src=\"");
				urlList = textno3[i].substring(beginIndex + 10);
				endIndex = urlList.indexOf("\"");
				urlList = urlList.substring(0, endIndex);
				String type = urlList.substring(urlList.lastIndexOf(".") + 1);
				String id = urlList.substring(urlList.lastIndexOf("/") + 1, urlList.lastIndexOf("."));
				String returnurl = urlList.substring(urlList.lastIndexOf("images") - 1);
				String setuptime = urlList
						.substring(urlList.lastIndexOf("pinporn") + 8, urlList.lastIndexOf("pinporn") + 18)
						.replace("/", "-");
				Map<String, Object> map = new HashMap<String, Object>();

				// 数据判断是否存在
				boolean TF = true;
				if (TF) {
					map.put("SID", null);// 字符串id
					map.put("ADI", ADI);// 特殊DI头
					map.put("ZDI", null);// 特殊DI码
					map.put("type", 1);// 存储类型
					map.put("classify", url);// 存储标识,区分数据源头
					map.put("title", "图片");// 存储标题
					map.put("line", type);// 存储行数据
					map.put("url", returnurl);// 链接
					map.put("uniqueid", id);// 存储数据自带id
					map.put("text", null);// 大容量主体数据存储体
					map.put("recorddate", setuptime);// 数据内时间
					map.put("acquiredate", Tool.GetNewDateTime(2));// 爬取时间

					// 入库操作
					// 入单库双库判断
					SssGif.ToolDaoImpl.SaveOneCrawlersql(map);

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
