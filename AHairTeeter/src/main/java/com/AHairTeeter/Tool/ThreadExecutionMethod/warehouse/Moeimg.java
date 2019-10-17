package com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;


/**
 * ACG图片链接获取方法
 * @author 好人
 *
 */
public class Moeimg {

	private final String url= "http://moeimg.net/";
	Tool Tool = new Tool();
	private int ADI = 21;
	/**
	 * 根据页码进行爬取
	 * 
	 * @param no1-10000
	 * @param no2 
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> moeimg_img(int no1, int no2) {
		
		/**
		 * 爬虫位
		 * 目前爬虫没有使用ip池,会被锁ip
		 */
		
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = no1; i <= no2; i++) {
				System.out.println("正在进行第:" + i + "页数据爬取:" + url + i);
				String text = spiders.spiders((url + i +".html"), 1);
//				System.out.println(text);
				listmap.addAll(moeimgSub(text, i, url + i));
				Thread.sleep(1000 * 10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		textno1 = text.substring(beginIndex);
		beginIndex = textno1.indexOf("<div class=\"pc_ad\">");
		textno1 = textno1.substring(beginIndex);
		beginIndex = textno1.lastIndexOf("<div class=\"pc_ad\">");
		textno1 = textno1.substring(0,beginIndex);
		String[] textno3 = textno1.split("<div class=\"box\">");
		for (int i = 1; i < textno3.length - 1; i++) {
			beginIndex = textno3[i].indexOf("<a href=\"");
			urlList = textno3[i].substring(beginIndex + 9);
			endIndex = urlList.indexOf("\" target=\"_blank\"");
			urlList = urlList.substring(0, endIndex);
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("SID", null);//字符串id
			map.put("ADI", ADI);//特殊DI头
			map.put("ZDI", null);//特殊DI码
			map.put("type", 1);//存储类型
			map.put("classify", url);//链接
			map.put("title", "图片");//存储标题
			map.put("line", null);//存储行数据
			map.put("url", urlList);//链接
			map.put("uniqueid", null);//存储数据自带id
			map.put("text",null);//大容量主体数据存储体
			map.put("recorddate", null);//数据内时间
			map.put("acquiredate", Tool.GetNewDateTime(2));//爬取时间
			listmap.add(map);
		}
		return listmap;
	}
}
