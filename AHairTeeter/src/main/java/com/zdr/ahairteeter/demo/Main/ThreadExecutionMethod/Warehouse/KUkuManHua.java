package com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.zdr.ahairteeter.demo.Main.SerToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.zdr.ahairteeter.demo.Tool.Crawler.pickpocket.Spiders;
import com.zdr.ahairteeter.demo.Tool.IMGNetworkGraphic.imageDownload;
import com.zdr.ahairteeter.demo.Tool.Route;
import com.zdr.ahairteeter.demo.Tool.Tool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KUkuManHua extends IoHandlerAdapter {

	private int ADI = 25; // 特殊DI头
	@Autowired
	private Tool Tool ;
	@Autowired
	private Spiders spiders;
	@Autowired
	protected  ToolDaoImpl ToolDaoImpl;
	@Autowired
	private  imageDownload imageDownload;
	@Autowired
	private  Route Route;

	String url = "https://comic.kukudm.com/";
	String targetURL = "";

	private static KUkuManHua KUkuManHua;
	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		KUkuManHua = this;
		KUkuManHua.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}
	public void getxvideoURL(String url) {
		System.out.println(url);
		this.targetURL = url;
	}

	private static final Logger logger = LogManager.getLogger(KUkuManHua.class.getName());

	/**
	 * 根据页码进行爬取
	 * @param no1
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> first_no1(int no1, int no2, String Type) {
		/**
		 * 爬虫位 目前爬虫没有使用ip池,会被锁ip
		 */
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String text = "";
		System.out.println("正在进行页数获取:" + targetURL);
		try {
			text = spiders.spiders(targetURL, 99999);
			listmap.addAll(first_no2(text,1));
//			Thread.sleep(1000 * 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listmap;
	}

	int pagination = 1;

	public Map<String, Object> Get_cartoon_map(String paginationurl) {
		Map<String, Object> map = new HashMap<String, Object>();
		int beginIndex = 0;
		int endIndex = 0;
		String text = "";
		String textno1 = "";
		String textno2 = "";
		String[] textno3;
		String qianurl = "https://s2.kukudm.com/";
		int time = 0;
		try {
			text = spiders.spiders(paginationurl, 99999);
			Thread.sleep(1000 * 3);
			beginIndex = text.indexOf("newkuku");
			textno1 = text.substring(beginIndex);
			endIndex = textno1.indexOf("'>");
			textno2 = textno1.substring(0, endIndex);
			System.out.println("p" + pagination + ":" + qianurl + textno2);
			map.put("p" + pagination, qianurl + textno2);
			textno3 = textno1.split("<a href=\"");
			if (textno3.length < 3) {
				endIndex = textno3[1].indexOf("\">");
				textno2 = textno3[1].substring(1, endIndex);
			} else {
				endIndex = textno3[2].indexOf("\">");
				textno2 = textno3[2].substring(1, endIndex);
			}
			System.out.println(url + textno2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pagination++;
		if (textno2.indexOf("exit") < 0) {
			map.putAll(Get_cartoon_map(url + textno2));
		}

		return map;
	}

	public List<Map<String, Object>> first_no2(String text , int num) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String textno2 = "";
		String[] textno3;

		beginIndex = text.indexOf("<dl id=\"comiclistn\">");
		if (beginIndex < 0) {
			logger.info(" 当前页数据未获取到------------------------------------------------------------"); // info级别的信息
			return listmap;
		}
		endIndex = text.indexOf("</dl><br><script src=\"/js/comic-b8.js\"></script></td>");
		textno1 = text.substring(beginIndex, endIndex);
		textno3 = textno1.split("<dd>");

		// 获取每章节的地址
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = num; i < textno3.length; i++) {
			pagination = 1;
			Map<String, Object> map = new HashMap<String, Object>();
			textno1 = textno3[i];
			beginIndex = textno1.indexOf("<a href=\"");
			endIndex = textno1.indexOf("\" target=");
			textno2 = textno1.substring(beginIndex + 10, endIndex);
			map.put("url", url + textno2);
			System.out.println(url + textno2);
			beginIndex = textno1.indexOf("_blank\">");
			endIndex = textno1.indexOf("</a>");
			textno2 = textno1.substring(beginIndex + 8, endIndex);
			map.put("name", textno2);
			System.out.println(textno2);
			list.add(map);
			System.out.println();
			System.out.println("即将下载图片");
			System.out.println();
			map.putAll(Get_cartoon_map(map.get("url").toString()));
			for (int ii = 1; ii < map.size(); ii++) {
				if (map.containsKey("p" + ii)) {
					imageDownload.downloadPicture(map.get("p" + ii).toString(),
							Route.getPictureCrawler() + map.get("name") + "\\", url, "000" + ii);
				}
			}
		}
		return list;
	}
}