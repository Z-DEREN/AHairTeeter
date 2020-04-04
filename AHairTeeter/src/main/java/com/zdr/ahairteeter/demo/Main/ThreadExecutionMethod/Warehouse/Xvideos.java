package com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Warehouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.zdr.ahairteeter.demo.Main.SerToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.zdr.ahairteeter.demo.Tool.Crawler.pickpocket.Spiders;
import com.zdr.ahairteeter.demo.Tool.Route;
import com.zdr.ahairteeter.demo.Tool.Tool;
import com.zdr.ahairteeter.demo.Tool.fileIO.IOLocalFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Xvideos extends IoHandlerAdapter implements WarehouseImpl {
	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	@Autowired
	private Spiders spiders;

	private static Xvideos Xvideos;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		Xvideos = this;
		Xvideos.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}

	@Autowired
	private Route rou;
	@Autowired
	private Tool Tool;

	private final int ADI = 22; // 特殊DI头
	private String GetVideoUrl = "https://www.xvideos.com/video-download/";
	private String classify = "https://www.xvideos.com/";
	private String fronturl = "";
	private String laterurl = "";
	private static final Logger logger = LogManager.getLogger(Xvideos.class.getName());


	public static void main(String[] args) {
		Xvideos Xvideos = new Xvideos();
		IOLocalFile IOLocalFile = new IOLocalFile();
		String text = IOLocalFile.output("F:\\rdzgsq\\Laboratory\\爬虫\\IOSave_XvideoRecommend_20200108124826.txt");
		Xvideos.video_Reco(null, text);
	}


	//获得指定url
	public void getxvideoURL(String url) {
		System.out.println(url.substring(0, url.indexOf("^")) + "//" + url.substring(url.indexOf("^") + 1));
		this.fronturl = url.substring(0, url.indexOf("^"));
		this.laterurl = url.substring(url.indexOf("^") + 1);
	}

	/**
	 * 根据页码进行爬取
	 *
	 * @param no1
	 * @param no2
	 * @return
	 */
	public List<Map<String, Object>> videoNum(int no1, int no2, String Type) {
		/**
		 * 爬虫位 目前爬虫没有使用ip池,会被锁ip
		 */
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String testtext = spiders.spiders(classify, 99999);
		if (!testtext.equals("")) {
			for (int i = no1; i <= no2; i++) {
				try {
					String url = fronturl + i + laterurl;
					System.out.println("正在进行第:" + i + "页数据爬取:" + url);
					String text = spiders.spiders(url, 99999);
					Tool.IOSaveFile(text, "video");
					listmap.addAll(videoWAP(text));
					Thread.sleep(1000 * 2);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					continue;
				}
			}
		}
		return listmap;
	}

	/**
	 * 数据筛选
	 *
	 * @param text
	 * @return
	 */
	public List<Map<String, Object>> videoWAP(String text) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String videoNum = "";
		beginIndex = text.indexOf("<div id=\"content\">");
		if (beginIndex < 0) {
			logger.info(" 当前页数据未获取到------------------------------------------------------------"); // info级别的信息
			return listmap;
		}
		try {
			textno1 = text.substring(beginIndex);
			endIndex = textno1.indexOf("<div class=\"pagination \">");
			textno1 = textno1.substring(0, endIndex);
			String[] textno3 = textno1.split("xv.thumbs.prepareVideo");
			for (int i = 1; i < textno3.length - 1; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				endIndex = textno3[i].indexOf(")");
				videoNum = textno3[i].substring(1, endIndex);
//				System.out.println(videoNum);
				map.put("SID", null);// 字符串id
				map.put("ADI", ADI);// 特殊DI头
				map.put("ZDI", null);// 特殊DI码
				map.put("type", 1);// 存储类型
				map.put("classify", classify);// 链接
				map.put("title", "视频id");// 存储标题
				map.put("line", null);// 存储标题
				map.put("url", videoNum);// 链接
				map.put("uniqueid", videoNum);// 存储数据自带id
				map.put("text", null);// 大容量主体数据存储体
				map.put("recorddate", null);// 数据内时间
				map.put("acquiredate", Tool.GetNewDateTime(2));// 爬取时间
				map.put("specialIO", GetVideoUrl(videoNum));// 特殊位,产生文件用

				if (Xvideos.ToolDaoImpl.SaveOneCrawlersql(map)) {
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

	/**
	 * 获取视频链接
	 */
	public String GetVideoUrl(String ID) {
		String returnstr = "";
		try {
			Thread.sleep(1000 * 3);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String Sdoc = null;
		Connection conn = null;
		try {
			conn = Jsoup.connect(GetVideoUrl + ID);
			conn.header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
			conn.header("Accept-Language", "zh-CN,zh;q=0.9");
			conn.header("Accept-Encoding", "gzip, deflate, br");
			conn.header("Connection", "keep-alive");
			conn.header("Cache-Control", "no-cache");
			conn.header("Cookie", rou.getVideosCookie());
			conn.header("Host", "www.xvideos.com");
			conn.header("Upgrade-Insecure-Requests", "1");
			conn.header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
			conn.header("Referer", "https://www.xvideos.com/video-download/51155953/");
			conn.method(Method.GET);
			conn.ignoreContentType(true);
			Response response;
			response = conn.execute();
			Sdoc = response.body();
			returnstr = tiqushipinlianjie(Sdoc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (returnstr.equals("")) {
				logger.info(" 当前网络不佳访问失败------------------------------------------------------------"); // info级别的信息
			}
			return returnstr;
		}
	}

	/**
	 * 获取视频链接
	 *
	 * @param text
	 * @return
	 */
	public String tiqushipinlianjie(String text) {
		String textno1 = "";
		if (text.indexOf("\"LOGGED\":false") > 0) {
			for (int i = 0; i < 10; i++) {
				logger.warn(" Xvideos--cookie已过期------------------------------------------------------------"); // info级别的信息
			}
		} else {
			int beginIndex = 0;
			int endIndex = 0;

			String videoNum = "";
			beginIndex = text.indexOf("\"URL\":\"");
			endIndex = text.indexOf("\",\"URL_LOW\"");
			textno1 = text.substring(beginIndex + 7, endIndex);
			textno1 = textno1.replace("\\/", "/");
			System.out.println(textno1);
		}
		return textno1;
	}


	/**
	 * 获取大量相关推荐
	 */
	public List<Map<String, Object>> video_Reco_multi(String url, String shoudTest) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String text = "";
		if (url == null) {
			text = shoudTest;
		} else {
			text = spiders.spiders(url, 99999);
		}
		Tool.IOSaveFile(text, "XvideoRecommend");
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String videoNum = "";
		beginIndex = text.indexOf("<script>var video_related");
		if (beginIndex < 0) {
			logger.info(" 当前页数据未获取到------------------------------------------------------------"); // info级别的信息
			return listmap;
		}
		textno1 = text.substring(beginIndex);
		endIndex = textno1.indexOf("</script>");
		textno1 = textno1.substring(0, endIndex);
		String[] textno3 = Tool.split(textno1, "},{");
		listmap = video_Reco(null, text);
		Tool.IOSaveFile(listmap);
		for (int i = 1; i <= 10; i++) {
			List<Map<String, Object>> oncelistmap = new ArrayList<Map<String, Object>>();
			endIndex = textno3[i].indexOf(",\"u\"");
			textno1 = textno3[i].substring(endIndex);
			endIndex = textno1.indexOf("\",\"");
			textno1 = textno1.substring(8, endIndex);
			textno1 = textno1.replace("\\", "");
			System.out.println(classify + textno1);
			oncelistmap = video_Reco(classify + textno1, null);
			Tool.IOSaveFile(oncelistmap);
			listmap.addAll(oncelistmap);
		}
		return listmap;
	}


	/**
	 * 相关推荐
	 *
	 * @param url
	 * @return shoudTest
	 */
	public List<Map<String, Object>> video_Reco_alone(String url, String shoudTest) {
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String videoNum = "";
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		beginIndex = url.indexOf("com/video");
		videoNum = url.substring(beginIndex + 9);
		videoNum = videoNum.substring(0, url.indexOf("/"));
		map.put("SID", null);// 字符串id
		map.put("ADI", ADI);// 特殊DI头
		map.put("ZDI", null);// 特殊DI码
		map.put("type", 1);// 存储类型
		map.put("classify", classify);// 链接
		map.put("title", "视频id");// 存储标题
		map.put("line", null);// 存储标题
		map.put("url", videoNum);// 链接
		map.put("uniqueid", videoNum);// 存储数据自带id
		map.put("text", null);// 大容量主体数据存储体
		map.put("recorddate", null);// 数据内时间
		map.put("acquiredate", Tool.GetNewDateTime(2));// 爬取时间
		map.put("specialIO", GetVideoUrl(videoNum));// 特殊位,产生文件用
		System.out.println("获取到的参数:" + videoNum);
		listmap.add(map);

		try {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return listmap;
		}
	}

	/**
	 * 相关推荐
	 *
	 * @param url
	 * @return shoudTest
	 */
	public List<Map<String, Object>> video_Reco(String url, String shoudTest) {
		String text = "";
		if (url == null) {
			text = shoudTest;
		} else {
			text = spiders.spiders(url, 99999);
		}

		Tool.IOSaveFile(text, "XvideoRecommend");
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String videoNum = "";
		beginIndex = text.indexOf("<script>var video_related");
		if (beginIndex < 0) {
			logger.info(" 当前页数据未获取到------------------------------------------------------------"); // info级别的信息
			return listmap;
		}
		textno1 = text.substring(beginIndex);
		endIndex = textno1.indexOf("</script>");
		textno1 = textno1.substring(0, endIndex);
		String[] textno3 = Tool.split(textno1, "},{");

		for (int i = 0; i < textno3.length - 1; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (i == 0) {
				beginIndex = url.indexOf("com/video");
				videoNum = url.substring(beginIndex + 9);
				videoNum = videoNum.substring(0, url.indexOf("/"));
			} else {
				beginIndex = textno3[i].indexOf("\"id\":");
				endIndex = textno3[i].indexOf(",\"u\"");
				videoNum = textno3[i].substring(beginIndex + 5, endIndex);
			}

			map.put("SID", null);// 字符串id
			map.put("ADI", ADI);// 特殊DI头
			map.put("ZDI", null);// 特殊DI码
			map.put("type", 1);// 存储类型
			map.put("classify", classify);// 链接
			map.put("title", "视频id");// 存储标题
			map.put("line", null);// 存储标题
			map.put("url", videoNum);// 链接
			map.put("uniqueid", videoNum);// 存储数据自带id
			map.put("text", null);// 大容量主体数据存储体
			map.put("recorddate", null);// 数据内时间
			map.put("acquiredate", Tool.GetNewDateTime(2));// 爬取时间
			map.put("specialIO", GetVideoUrl(videoNum));// 特殊位,产生文件用
			System.out.println("获取到的参数:" + videoNum);

//			if (Xvideos.ToolDaoImpl.SaveOneCrawlersql(map)) {
			logger.info("清洗到第"+i+"条数据;剩余"+(textno3.length - 1 - i)+"条,数据为:" + videoNum ); // info级别的信息
//			// 入库操作
//			// 入单库双库判断
			listmap.add(map);
//			}
		}
		try {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return listmap;
		}
	}



    public List<String> getXMLurl(){
        List<String> list = Tool.getDisplayColumns("Xurl");
        return list;
	}



	@Override
	public List<Map<String, Object>> Webautomate(int P1, int P2, String url) {




		return null;
	}




}
