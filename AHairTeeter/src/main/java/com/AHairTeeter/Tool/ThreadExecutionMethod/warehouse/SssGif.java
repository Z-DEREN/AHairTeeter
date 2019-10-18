package com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;


/**
 * 
 * 图片链接获取方法(V)
 * @author 好人
 * 
 */
public class SssGif {
	private int ADI = 20;	//特殊DI头
	Tool Tool = new Tool();
	String url = "https://www.sex.com/";
	/**
	 * 根据页码进行爬取
	 * 
	 * @param no1
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> first_no1(int no1, int no2,String Type) {
		/**
		 * 爬虫位
		 * 目前爬虫没有使用ip池,会被锁ip
		 */
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = no1; i <= no2; i++) {
				System.out.println("正在进行第:" + i + "页数据爬取:" + url+Type+"/?page=" + i);
				String text = spiders.spiders(url+Type+"/?page=" + i, 1);
//				System.out.println(text);
				listmap.addAll(first_no2(text));
				Thread.sleep(1000 * 10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			String returnurl = urlList.substring(urlList.lastIndexOf("images") - 1);
			String setuptime = urlList
					.substring(urlList.lastIndexOf("pinporn") + 8, urlList.lastIndexOf("pinporn") + 18)
					.replace("/", "-");
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("SID", null);//字符串id
			map.put("ADI", ADI);//特殊DI头
			map.put("ZDI", null);//特殊DI码
			map.put("type", 1);//存储类型
			map.put("classify", url);//存储标识,区分数据源头
			map.put("title", "图片");//存储标题
			map.put("line", type);//存储行数据
			map.put("url", returnurl);//链接
			map.put("uniqueid", id);//存储数据自带id
			map.put("text",null);//大容量主体数据存储体
			map.put("recorddate", setuptime);//数据内时间
			map.put("acquiredate", Tool.GetNewDateTime(2));//爬取时间
			
			listmap.add(map);
		}
		return listmap;
	}

}
