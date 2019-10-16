package com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;


/**
 * 
 * 图片链接获取方法(V)
 * @author 好人
 * 
 */
public class SssGif {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 根据页码进行爬取
	 * 
	 * @param no1
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> first_no1(int no1, int no2) {
		/**
		 * 爬虫位
		 * 目前爬虫没有使用ip池,会被锁ip
		 */
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = no1; i <= no2; i++) {
				System.out.println("正在进行第:" + i + "页数据爬取:" + "https://www.sex.com/pics/?page=" + i);
				String text = spiders.spiders("https://www.sex.com/pics/?page=" + i, 1);
//				System.out.println(text);
				listmap.addAll(first_no2(text, i, "https://www.sex.com/?page=" + i));
				Thread.sleep(1000 * 10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listmap;
	}

	public List<Map<String, Object>> first_no2(String text, int num, String referrerurl) {
		Date date = new Date();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String captutime = df.format(date);
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String urlList = "";
		beginIndex = text.indexOf("masonry_box ad-big");
		textno1 = text.substring(beginIndex);
		String[] textno3 = textno1.split("masonry_box small_pin_box");
		for (int i = 1; i < textno3.length - 1; i++) {
			beginIndex = textno3[i].indexOf("data-src=\"");
			urlList = textno3[i].substring(beginIndex + 10);
			endIndex = urlList.indexOf("\"");
			urlList = urlList.substring(0, endIndex);
//			System.out.println(urlList);
			String type = urlList.substring(urlList.lastIndexOf(".") + 1);
			String id = urlList.substring(urlList.lastIndexOf("/") + 1, urlList.lastIndexOf("."));
			String url = urlList.substring(urlList.lastIndexOf("images") - 1);
			String setuptime = urlList
					.substring(urlList.lastIndexOf("pinporn") + 8, urlList.lastIndexOf("pinporn") + 18)
					.replace("/", "-");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("urlList", urlList);
			map.put("id", id);
			map.put("type", type);
			map.put("url", url);
			map.put("setuptime", setuptime);
			map.put("captutime", captutime);
			listmap.add(map);
		}
		return listmap;
	}

}
