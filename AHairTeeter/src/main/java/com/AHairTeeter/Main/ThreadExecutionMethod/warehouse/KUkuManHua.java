package com.AHairTeeter.Main.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Route;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;
import com.AHairTeeter.Tool.IMGNetworkGraphic.imageDownload;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

public class KUkuManHua extends IoHandlerAdapter {

	private int ADI = 25; // 特殊DI头
	Tool Tool = new Tool();
	String url = "https://comic.kukudm.com/";

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	imageDownload imageDownload = new imageDownload();
	Route Route = new Route();
	private static KUkuManHua KUkuManHua;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		KUkuManHua = this;
		KUkuManHua.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}

	private static final Logger logger = LogManager.getLogger(KUkuManHua.class.getName());

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
		System.out.println("正在进行页数获取:" + url + "/" + Type + "/?page=");
		try {
			text = spiders.spiders(Type, 99999);
			listmap.addAll(first_no2(text));
//			Thread.sleep(1000 * 10);
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
		Spiders spiders = new Spiders();
		int time = 0;
		try {
			text = spiders.spiders(paginationurl, 99999);
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
//			time = new Random().nextInt(5 - 1) + 1;
//			Thread.sleep(1000 * (5+time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		pagination++;
		if (textno2.indexOf("exit") < 0) {
			map.putAll(Get_cartoon_map(url + textno2));
		}

		return map;
	}

	public List<Map<String, Object>> first_no2(String text) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String textno2 = "";
		String[] textno3;
		String urlList = "";

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
		for (int i = 13; i < textno3.length; i++) {
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

	public void kukudmImgDownload() {

	}

	public static void main(String[] args) {

		KUkuManHua KUkuManHua = new KUkuManHua();
		KUkuManHua.test();

	}

	public void test() {

		IOLocalFile IOLocalFile = new IOLocalFile();
		String text = IOLocalFile.output("F:\\rdzgsq\\Laboratory\\爬虫\\漫画章节页.txt");
		System.out.println(text);
		first_no2(text);

//		Map<String,Object> map = new HashMap<String,Object>();

//		map.putAll(Get_cartoon_map("https://comic.kukudm.com/comiclist/2093/46758/1.htm"));

//		System.out.println(32333);
	}

}
