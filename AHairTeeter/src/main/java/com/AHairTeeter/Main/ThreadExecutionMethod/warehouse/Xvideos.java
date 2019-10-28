package com.AHairTeeter.Main.ThreadExecutionMethod.warehouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;

@Component
public class Xvideos extends IoHandlerAdapter {

	private int ADI = 22; // 特殊DI头
	Tool Tool = new Tool();
	String fronturl = "https://www.xvideos.com/c/";
	String laterurl = "/Teen-13";
	String GetVideoUrl = "https://www.xvideos.com/video-download/";
	String classify = "https://www.xvideos.com/";

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static Xvideos Xvideos;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		Xvideos = this;
		Xvideos.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}

	private static final Logger logger = LogManager.getLogger(Xvideos.class.getName());

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
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = no1; i <= no2; i++) {
			try {
				String url = fronturl + i + laterurl;
				System.out.println("正在进行第:" + i + "页数据爬取:" + url);
				String text = spiders.spiders(url, 99999);
				listmap.addAll(videoWAP(text));
				Thread.sleep(1000 * 2);
			} catch (Exception e) {
				e.printStackTrace();
			}  finally {
				continue;
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

	public String GetVideoUrl(String ID) {
		try {
			Thread.sleep(1000 * 1);
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
			conn.header("Cookie",
					"html5_pref=%7B%22SQ%22%3Afalse%2C%22MUTE%22%3Afalse%2C%22VOLUME%22%3A1%2C%22FORCENOPICTURE%22%3Afalse%2C%22FORCENOAUTOBUFFER%22%3Afalse%2C%22FORCENATIVEHLS%22%3Afalse%2C%22PLAUTOPLAY%22%3Atrue%2C%22CHROMECAST%22%3Afalse%2C%22EXPANDED%22%3Afalse%2C%22FORCENOLOOP%22%3Afalse%7D; _stat=2406964485.3704294061.23479322.3707545801; wpn_ad_cookie=d85c44aac84bc1eed32fc84ecfbdaf34; chat_data_c=null; html5_networkspeed=797; multi_accounts=f732c5c2a531c266f2yzovKtR-t7qv_PeSyfo6UY1xpKtFU_6sU6Ud55cFYWAr0ZiGTXF8WU_gbLuZsU; thumbloadstats_vthumbs=%7B%222%22%3A%5B%7B%22s%22%3A2%2C%22d%22%3A667%7D%2C%7B%22s%22%3A2%2C%22d%22%3A1%7D%5D%2C%22last%22%3A%7B%22s%22%3A2%2C%22v%22%3A%5B667%2C1%5D%7D%7D; last_views=%5B%2250236573-1570713626%22%2C%2247566269-1570713646%22%2C%2251364645-1570713688%22%2C%2251149027-1570713689%22%2C%2251332425-1570713741%22%2C%2250347211-1570713751%22%2C%2250569867-1570713752%22%2C%2248486831-1570713725%22%2C%2247836109-1570713822%22%2C%2247856201-1570713838%22%2C%2247269609-1570713839%22%2C%2243421747-1570713923%22%2C%2237741995-1570713924%22%2C%2233146699-1570713938%22%2C%221702354-1570713955%22%2C%2250333147-1570713977%22%2C%2250333123-1570713992%22%2C%2248209040-1570714018%22%2C%2249746125-1570714036%22%2C%2248208946-1570714042%22%2C%2235912529-1570714071%22%2C%2222912311-1570714071%22%2C%2247527129-1570714099%22%2C%2217610121-1570714097%22%2C%2250310275-1570714154%22%2C%2251305669-1570714155%22%2C%2239072914-1570714155%22%2C%2250242055-1570714157%22%2C%2238569585-1570714175%22%2C%2238505531-1570714176%22%2C%2251119663-1570714213%22%2C%2251300967-1570714267%22%2C%2251230521-1570714262%22%2C%2250507917-1570714265%22%2C%2251230827-1570714264%22%2C%2250255409-1570714261%22%2C%2249433023-1570714308%22%2C%2250277493-1570714326%22%2C%2249202783-1570714325%22%2C%2250508745-1570714464%22%2C%2250454523-1570714472%22%2C%2251275167-1570714498%22%2C%2250550173-1570714546%22%2C%2251231079-1570714544%22%2C%2249203355-1570714547%22%2C%2238338325-1570714584%22%2C%2248586865-1570714607%22%2C%2251230657-1570714638%22%2C%2251230603-1570714654%22%2C%2251418583-1570972689%22%2C%2251284547-1570972770%22%2C%2239730479-1570972993%22%2C%2251155953-1570974131%22%2C%2251242393-1570974361%22%2C%2247678325-1570974368%22%2C%2239991669-1570974417%22%2C%2251438925-1570974431%22%2C%2248413723-1570974430%22%2C%2251027093-1570974433%22%2C%2249787423-1570974434%22%2C%2234803695-1570974461%22%2C%2221380409-1570974473%22%2C%2227336779-1570974479%22%2C%2225857923-1570974482%22%2C%2228400045-1570974494%22%2C%2217607663-1570974494%22%2C%2228141739-1570974505%22%2C%2230208963-1570974556%22%2C%224320612-1570974558%22%2C%2250819167-1570974615%22%2C%2247887561-1570974616%22%5D; HEXAVID_LOGIN=7ce4b16ffc39710brQyPnYrYy9qS2Nq82GFUyBo556H5YwjflrwrdWAwlcrs6Dt68cnP8ojT5PO7IPe0qv2caa1RrmDDZny0jfcJhKcKSCGKufBNmJh1Fni1c-P2rCG9-RJR3nEn94_NsmBRYWBq92r-S1wcb6cPtGV26cS3vr4z3Flh8rafBGL7E9FntTUh4D08RbxBSDoQ56oX0lxB0VOkRLTFSxpEdxH41hcw46TvQzAZQtEV3LIVl8h4ujlMjBJToTvqY_PrGyJeyzLCnrS8b1ojJJHtnEAkd3_OCDLNpLRq7NdL8-uOzoBxbHwXh5A7hOkc1dW6B3R6OYg12tzL-2IYQh42H-zqCcbFjRs6_L4sBC4aNTyDXHWNExwWuqh99YS5ZsWM4k5y5ltfjuIyPGk0L9_sPwxDUDPkRTqez7lMtADl2EoyLI7Ron9tdnZsqN5Nm4AjaieePRuccH5IcpXp84JpwP_Rlq0M82n3Yyx5Vx-cmo-30mrriecKpxTAiTM6e05hFVpkoZDYfb7gMpn9Gr4yNgLkRHcohjPGIFWcDCfLFLcsu7h51YEfHggU1ny52yFQfN1Hu0RGs5g9jL12KIU69FSvgw%3D%3D");
			conn.header("Host", "www.xvideos.com");
			conn.header("Upgrade-Insecure-Requests", "1");
			conn.header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
			conn.header("Referer", "https://www.xvideos.com/video-download/51155953/");
			conn.method(Method.GET);
//	    conn.followRedirects(false);
			conn.ignoreContentType(true);
			Response response;
			response = conn.execute();
			Sdoc = response.body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tiqushipinlianjie(Sdoc);
	}

	/**
	 * 获取视频链接
	 * 
	 * @param text
	 * @return
	 */
	public String tiqushipinlianjie(String text) {
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String videoNum = "";
		beginIndex = text.indexOf("\"URL\":\"");
		endIndex = text.indexOf("\",\"URL_LOW\"");
		textno1 = text.substring(beginIndex + 7, endIndex);
		textno1 = textno1.replace("\\/", "/");
//		System.out.println(textno1);
		return textno1;
	}

}
