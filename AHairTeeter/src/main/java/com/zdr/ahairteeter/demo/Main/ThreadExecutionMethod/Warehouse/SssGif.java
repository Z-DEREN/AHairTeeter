package com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.zdr.ahairteeter.demo.Main.SerToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.zdr.ahairteeter.demo.Tool.Crawler.pickpocket.Spiders;
import com.zdr.ahairteeter.demo.Tool.Tool;
import com.zdr.ahairteeter.demo.Tool.fileIO.IOLocalFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 图片链接获取方法(V)
 * @author 好人
 */
@Component
public class SssGif extends IoHandlerAdapter implements WarehouseImpl{
	private int ADI = 20; // 特殊DI头
	@Autowired
	private Tool tool ;
	@Autowired
	private Spiders spiders;
	String primaryUrl = "https://www.sex.com";
	String qualifiedUrl = "https://www.sex.com";
	String IOurl = "https://images.sex.com";

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;

	private static SssGif SssGif;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		SssGif = this;
		SssGif.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}
	
	//预处理链接地址
	private void getGIFURL(String url) {
		this.qualifiedUrl = url;
	}
	private static final Logger logger = LogManager.getLogger(SssGif.class.getName());


	public static void main(String[] args) {
		SssGif SssGif = new SssGif();
		IOLocalFile IOLocalFile = new IOLocalFile();
		String text = IOLocalFile.output("E:\\Database\\爬虫\\IOSave_Gif_20200329140753.txt");
		SssGif.first_no2(text);
	}


	/**
	 * 根据页码进行爬取
	 * 
	 * @param no1
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> first_no1(int no1, int no2) {
		/**
		 * 爬虫位 目前爬虫没有使用ip池,会被锁ip
		 */
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String text = "";
		for (int i = no1; i <= no2; i++) {
			System.out.println("正在进行第:" + i + "页数据爬取:" + qualifiedUrl + "?page=" + i);
			try {
				text = spiders.spiders(qualifiedUrl + "?page=" + i, 99999);
				System.out.println(text);
				tool.IOSaveFile(text, "Gif");
				listmap.addAll(first_no2(text));
				Thread.sleep(1000 * 10);
			} catch (Exception e) {
				e.printStackTrace();
			}  finally {
				continue;
			}
		}
		return listmap;
	}

	public List<Map<String, Object>> first_no2(String text) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String urlvalue = "";
		String urlList = "";
		String type = "";
		String id ="";
		String returnurl ="";
		String setuptime ="";
		beginIndex = text.indexOf("masonry_box ad-big");
		if (beginIndex < 0) {
			logger.info(" 当前页数据未获取到------------------------------------------------------------"); // info级别的信息
			return listmap;
		}
		try {
			urlvalue = text.substring(beginIndex);
			String[] textno3 = urlvalue.split("masonry_box small_pin_box");
			for (int i = 1; i < textno3.length - 1; i++) {
				try {
				Map<String, Object> map = new HashMap<String, Object>();
				beginIndex = textno3[i].indexOf("data-src=\"");
				urlList = textno3[i].substring(beginIndex + 10);
				endIndex = urlList.indexOf("\"");
				urlList = urlList.substring(0, endIndex);
				type = urlList.substring(urlList.lastIndexOf(".") + 1);
				id = urlList.substring(urlList.lastIndexOf("/") + 1, urlList.lastIndexOf("."));
				returnurl = urlList.substring(urlList.lastIndexOf("images") - 1);
				setuptime = urlList.substring(urlList.lastIndexOf("pinporn") + 8, urlList.lastIndexOf("pinporn") + 18).replace("/", "-");
				// 数据判断是否存在
				boolean TF = true;
				if (TF) {
					map.put("SID", null);// 字符串id
					map.put("ADI", ADI);// 特殊DI头
					map.put("ZDI", null);// 特殊DI码
					map.put("type", 1);// 存储类型
					map.put("classify", primaryUrl);// 存储标识,区分数据源头
					map.put("title", "图片");// 存储标题
					map.put("line", type);// 存储行数据
					map.put("url", returnurl);// 链接
					map.put("uniqueid", id);// 存储数据自带id
					map.put("text", null);// 大容量主体数据存储体
					map.put("recorddate", setuptime);// 数据内时间
					map.put("acquiredate", tool.GetNewDateTime(2));// 爬取时间
//					if (SssGif.ToolDaoImpl.SaveOneCrawlersql(map)) {
					logger.info("清洗到第"+i+"条数据;剩余"+(textno3.length - 1 - i)+"条,数据为:" + returnurl ); // info级别的信息
						map.put("url", IOurl+returnurl);// 链接
						// 入单库双库判断 入库操作
						listmap.add(map);
//					}
				}
				} catch (Exception e) {
					continue;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return listmap;
		}
	}

	/**
	 * 根据页码进行爬取
	 * @param P1 页码1
	 * @param P2 页码2
	 * @param url 目标url
	 * @return
	 */
	@Override
	public List<Map<String, Object>> Webautomate(int P1, int P2, String url) {
		getGIFURL(url);
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String text = "";
		for (int i = P1; i <= P2; i++) {
			logger.info("正在进行第:" + i + "页数据爬取:" + url + "?page=" + i); // info级别的信息
			text = spiders.spiders(url + "?page=" + i, 99999);
			//源文件存储本地
			tool.IOSaveFile(text, "Gif");
			//清洗数据
			listmap.addAll(first_no2(text));
			//缓解目标压力
			tool.sleep(3);
		}
		return listmap;
	}
}
