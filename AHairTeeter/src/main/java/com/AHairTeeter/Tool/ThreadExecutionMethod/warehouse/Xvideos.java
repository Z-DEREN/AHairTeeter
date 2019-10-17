package com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

public class Xvideos {
	
	private int ADI = 22;	//特殊DI头
	Tool Tool = new Tool();
	String url = "https://www.xvideos.com/";

	/**
	 * 根据页码进行爬取
	 * 
	 * @param no1
	 * @param no2
	 * @return
	 */
	public List<Map<String, Object>> videoNum(int no1, int no2,String Type) {
		/**
		 * 爬虫位 目前爬虫没有使用ip池,会被锁ip
		 */
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = no1; i <= no2; i++) {
				System.out.println("正在进行第:" + i + "页数据爬取:" + url+"tags/"+Type+"/" + i);
				String text = spiders.spiders(url+"tags/"+Type+"/" + i, 1);
				listmap.addAll(videoWAP(text,Type));
				Thread.sleep(1000 * 10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listmap;
	}

//	public static void main(String[] args) {
//		IOLocalFile IOLocalFile = new IOLocalFile();
//		String text = IOLocalFile.output("F:\\rdzgsq\\Laboratory\\爬虫\\xvideostest1.txt");
//		Xvideos Xvideos = new Xvideos();
//		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
//		listmap.addAll(Xvideos.videoWAP(text,"2333"));
//	}

	/**
	 * 数据筛选
	 * @param text
	 * @return
	 */
	public List<Map<String, Object>> videoWAP(String text,String Type) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String videoNum = "";
		beginIndex = text.indexOf("<div id=\"content\">");
		textno1 = text.substring(beginIndex);
		endIndex= textno1.indexOf("<div class=\"pagination \">");
		textno1 = textno1.substring(0,endIndex);
		String[] textno3 = textno1.split("xv.thumbs.prepareVideo");
		for (int i = 1; i < textno3.length - 1; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			endIndex = textno3[i].indexOf(")");
			videoNum = textno3[i].substring(1,endIndex);
			map.put("videoNum", videoNum);
			map.put("type", Type);
			System.out.println(videoNum);
			
			map.put("SID", null);//字符串id
			map.put("ADI", ADI);//特殊DI头
			map.put("ZDI", null);//特殊DI码
			map.put("type", 1);//存储类型
			map.put("classify", url);//链接
			map.put("title", "视频id");//存储标题
			map.put("line", Type);//存储标题
			map.put("url", videoNum);//链接
			map.put("uniqueid", videoNum);//存储数据自带id
			map.put("text",null);//大容量主体数据存储体
			map.put("recorddate", null);//数据内时间
			map.put("acquiredate", Tool.GetNewDateTime(2));//爬取时间
			
			listmap.add(map);
		}
		return listmap;
	}
}
