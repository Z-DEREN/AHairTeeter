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
import org.springframework.stereotype.Component;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;

@Component
public class Kr36 extends IoHandlerAdapter {
	private final String url = "https://36kr.com";
	Tool Tool = new Tool();
	private int ADI = 24;
	Spiders spiders = new Spiders();
	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static Kr36 Kr36;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		Kr36 = this;
		Kr36.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}

	private static final Logger logger = LogManager.getLogger(Kr36.class.getName());

	/**
	 * 根据页码进行爬取
	 * 
	 * @param     no1-10000
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> Kr36news() {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Spiders spiders = new Spiders();

		String StrID = "";
		int ID = 0;
		int NEWID = 0;
		try {
			StrID = Kr36.ToolDaoImpl.GetSelObjsql(" SELECT uniqueid FROM  legal_information_heyhey  WHERE  ZDI = ( SELECT MAX(ZDI) FROM legal_information_heyhey WHERE ZDI LIKE '24%' ) ", null, "String").toString();
			ID = Integer.parseInt(StrID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获数据库最新的ID
		NEWID = newID(); // 当前网址的最新ID

		String url = newurl(ID, NEWID); // 处理并返回新网址
//		String url = newurl(0, NEWID); // 处理并返回新网址
		String Text = spiders.spiders((url), 99999);
//		String Text = spiders.spiders(("https://36kr.com/api/newsflash?b_id=187575&per_page=25"), 99999);

//		Tool.IOSaveFile(Text, "36");
		List<String> Clist = roughsaix(Text);
		listmap.addAll(ScreenText(Clist));// 保存数据库方法

		// 记录当前新闻获取数量
		return listmap;
	}

	public List roughsaix(String information) {
		List<String> list = new ArrayList<String>();
		try {
			String[] Array = information.split("\\}\\,\\{");// 拆分为String数组
			for (int i = 0; i < Array.length; i++) {
				list.add(Array[i]);// 转化为集合
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return list;
		}
	}

	// 获取数据
	public List<Map<String, Object>> ScreenText(List<String> Clist) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();

		int front;
		int later;
		String newsurl;
		String textno1;
		// 所提取的值
		String time;// created_at
		String ID;// id
		String title;// title
		String summary;// summary
		String[] zhi;
		int NewsCount = 0;
		List<String> Xlist = new ArrayList<>();
		// 记录当前爬取详情
		try {
			for (String information : Clist) {
				Map<String, Object> map = new HashMap<String, Object>();
				front = information.indexOf("title\":\"");
				later = information.indexOf("catch_title");
				if (front != -1 && later != -1) { // 判断是否为要爬取的目标
					// title
					title = information.substring(front + 8, later - 3);

					// ID
					front = information.indexOf("id\":");
					later = information.indexOf("project_id");
					ID = information.substring(front + 4, later - 2);

					// summary
					front = information.indexOf("description");
					later = information.indexOf("cover");
					summary = information.substring(front + 14, later - 3);

					// url
					front = information.indexOf("\"news_url\":\"http");
					later = information.indexOf("user_id");
					if (front != -1) {
						newsurl = information.substring(front + 12, later - 3);
					} else {
						newsurl = null;
					}
					// time
					front = information.indexOf("created_at");
					later = information.indexOf("updated_at");
					time = information.substring(front + 13, later - 3);

					map.put("SID", null);// 字符串id
					map.put("ADI", ADI);// 特殊DI头
					map.put("ZDI", null);// 特殊DI码
					map.put("type", 1);// 存储类型
					map.put("classify", url);// 存储标识,区分数据源头
					map.put("title", "36Kr");// 存储标题
					map.put("line", title);// 存储行数据
					map.put("url", newsurl);// 链接
					map.put("uniqueid", ID);// 存储数据自带id
					map.put("text", summary);// 大容量主体数据存储体
					map.put("recorddate", time);// 数据内时间
					map.put("acquiredate", Tool.GetNewDateTime(2));// 爬取时间
					// 数据判断是否存在
					if(Kr36.ToolDaoImpl.SaveOneCrawlersql(map)) {}{
						// 入库操作
						// 入单库双库判断1
						listmap.add(map);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return listmap;
		}
	}

	// 当前网址的最新ID
	public int newID() {
		int front = 0;
		int later = 0;
		String textno1 = "";
		String newsURL = "https://36kr.com/newsflashes";
		try {
			// 爬取
			String newnum = spiders.spiders((newsURL), 99999);
			front = newnum.indexOf("newsflashList");
			textno1 = newnum.substring(front);
			front = textno1.indexOf("id");
			later = textno1.indexOf("project_id");
			textno1 = textno1.substring(front + 4, later - 2);
			front = Integer.parseInt(textno1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return front;
		}

	}

	// 处理并返回新网址
	public String newurl(int ID, int NEWID) {
		String http = "https://36kr.com/api/newsflash?b_id=AAAA&per_page=BBBB";
		String BBBB = "";
		if (ID == 0) {
			BBBB = "100";
		} else {
			BBBB = String.valueOf(NEWID - ID);
		}

		http = http.replace("AAAA", String.valueOf(NEWID));
		http = http.replace("BBBB", BBBB);
		logger.info(" 最终爬取地址" + http + "------------------------------------------------------------"); // info级别的信息
		return http;
	}
}
