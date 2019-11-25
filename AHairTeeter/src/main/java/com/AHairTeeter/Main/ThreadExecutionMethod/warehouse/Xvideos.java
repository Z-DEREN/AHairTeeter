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
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

@Component
public class Xvideos extends IoHandlerAdapter {

	private int ADI = 22; // 特殊DI头
	Tool Tool = new Tool();
	String fronturl = "https://www.xvideos.com/?k=台灣高中生+taiwan&p=";
	String laterurl = "";
//	String laterurl = "/Teen-13";
	// https://www.xvideos.com/?k=cosplay&p=3
//	https://www.xvideos.com/c/
//	https://www.xvideos.com/lang/chinese
//		https://www.xvideos.com/?k=chinese
//			https://www.xvideos.com/?k=chinese%20teen&top
//				https://www.xvideos.com/?k=chinese%20big%20tits&top
//					https://www.xvideos.com/?k=chinese+homemade&related
//						https://www.xvideos.com/?k=japanese%20teen&top
//							https://www.xvideos.com/?k=japanese+teen+schoolgirl&related
//	
//	https://www.xvideos.com/c/Oiled-22
//		https://www.xvideos.com/tags/3d-sex-game
//			https://www.xvideos.com/tags/china
//				https://www.xvideos.com/tags/creampie
//					https://www.xvideos.com/tags/young-petite-porn
//						https://www.xvideos.com/tags/cumshot
//							https://www.xvideos.com/tags/petite-teen
//								https://www.xvideos.com/tags/party

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
					"hexatrade=87xee84UYydoPmuCrrSozYYwoqn31xep; html5_pref=%7B%22SQ%22%3Afalse%2C%22MUTE%22%3Afalse%2C%22VOLUME%22%3A1%2C%22FORCENOPICTURE%22%3Afalse%2C%22FORCENOAUTOBUFFER%22%3Afalse%2C%22FORCENATIVEHLS%22%3Afalse%2C%22PLAUTOPLAY%22%3Atrue%2C%22CHROMECAST%22%3Afalse%2C%22EXPANDED%22%3Afalse%2C%22FORCENOLOOP%22%3Afalse%7D; _stat=2406964485.3704294061.23479322.3707545801; wpn_ad_cookie=fcb8b3e1f20e571a749d92074972732f; hexavid_lastsubscheck=1; multi_accounts=6c161f649a2e25b7pl0OFhB3gxl13IaFSnsQRp7ca8B1nM_Olm6nR2Q6LVcP6534OXwAI_VOJmbsz62x; last_views=%5B%2248208946-1570714042%22%2C%2235912529-1570714071%22%2C%2222912311-1570714071%22%2C%2247527129-1570714099%22%2C%2217610121-1570714097%22%2C%2250310275-1570714154%22%2C%2251305669-1570714155%22%2C%2239072914-1570714155%22%2C%2250242055-1570714157%22%2C%2238569585-1570714175%22%2C%2238505531-1570714176%22%2C%2251119663-1570714213%22%2C%2251300967-1570714267%22%2C%2251230521-1570714262%22%2C%2250507917-1570714265%22%2C%2251230827-1570714264%22%2C%2250255409-1570714261%22%2C%2249433023-1570714308%22%2C%2250277493-1570714326%22%2C%2249202783-1570714325%22%2C%2250508745-1570714464%22%2C%2250454523-1570714472%22%2C%2251275167-1570714498%22%2C%2250550173-1570714546%22%2C%2251231079-1570714544%22%2C%2249203355-1570714547%22%2C%2238338325-1570714584%22%2C%2248586865-1570714607%22%2C%2251230657-1570714638%22%2C%2251230603-1570714654%22%2C%2251418583-1570972689%22%2C%2251284547-1570972770%22%2C%2239730479-1570972993%22%2C%2251155953-1570974131%22%2C%2251242393-1570974361%22%2C%2247678325-1570974368%22%2C%2239991669-1570974417%22%2C%2251438925-1570974431%22%2C%2248413723-1570974430%22%2C%2251027093-1570974433%22%2C%2249787423-1570974434%22%2C%2234803695-1570974461%22%2C%2221380409-1570974473%22%2C%2227336779-1570974479%22%2C%2225857923-1570974482%22%2C%2228400045-1570974494%22%2C%2217607663-1570974494%22%2C%2228141739-1570974505%22%2C%2230208963-1570974556%22%2C%224320612-1570974558%22%2C%2250819167-1570974615%22%2C%2247887561-1570974616%22%2C%2248912255-1571581487%22%2C%2248956627-1571581495%22%2C%2248955677-1571581496%22%2C%2248782315-1571581503%22%2C%2246247747-1571581513%22%2C%2233495899-1571581518%22%2C%2245760823-1571582363%22%2C%2234747899-1572357221%22%2C%2251758353-1572358690%22%2C%2241847031-1573362826%22%2C%2251905407-1573362836%22%2C%2231375029-1573362839%22%2C%2251820619-1573362849%22%2C%2251876563-1573362851%22%2C%2251796881-1573362864%22%2C%2251464077-1573363491%22%2C%2252189267-1574462251%22%2C%2225512799-1574462254%22%2C%2243668479-1574462255%22%5D; HEXAVID_LOGIN=40cba157f6a411ca3dQtdC2OERz4p-m3U4l85KWEsGpxtr7I7bf3Jq0k2KMoMY7OGyuIihXXuJC_QF4szkg61DgO6vuM4moB6nbSe2g0tK2371EtqFTIW5JYnbCXDwOmw09EhdW3wLhkWW7rvDbbLqsHcipXCIxTL0Anpnnq4gzPOMEp6PPGS8wcdrKMxQGM5zKcd5dHj_eXwViX90n0fgCvBgh9Usxp6C-q_G9D2WhnNGyajXAx2rg6nfUj5dXPPCSJ94dBigvI22SeL5rYzuPPFRpGP8fSu0UxN5-mgHkAax0Yl4_p_8_WmSMUyNH3fJzKJOdcj4WwU6iPcHxFHi6TRiVPbDPshvoGGuWzZLgTRUph-OTF2zG-G0sEfOnuVbWrReaQZBglKar2M_yqphQ9eAb0zm99Zqb4tRUGdHRgOtt_bi9zJ1F4zVFeZ8IYJgfr7HPLJzpD7yOYHyja1rDr-gLZ4keenhkEdEJWT5jr8zC93L-ifgFWtEuj7MW7pOm4ge3eyiK5qyiiX0NhLXLuGCd2re2B-Y21H0CNrPgDEdNzFT1vsNDiylHlrGkW8hb1TYAuobQOpb7xqT3q9jHaIvEeGPqERnNYpwXTEWKFUEup_df8QLsavHs%3D");
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
		String textno1 = "";
		if (text.indexOf("\"LOGGED\":false") > 0) {
			System.out.println("cookie已过期");
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

	public static void main(String[] args) {
		Xvideos Xvideos = new Xvideos();
		IOLocalFile IOLocalFile = new IOLocalFile();
		String text = IOLocalFile.output("F:\\rdzgsq\\Laboratory\\爬虫\\xvideosXiangGuan.txt");
		Xvideos.videoWAP_correlation(text);
	}

	/**
	 * 相关推荐
	 * 
	 * @param text
	 * @return
	 */
	public List<Map<String, Object>> videoWAP_correlation(String url) {
		Spiders spiders = new Spiders();
		String text = spiders.spiders(url, 99999);
		
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
		String[] textno3 = Tool.split(textno1,"},{");
		
		for (int i = 1; i < textno3.length - 1; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			beginIndex = textno3[i].indexOf("\"id\":");
			endIndex = textno3[i].indexOf(",\"u\"");
			videoNum = textno3[i].substring(beginIndex+5, endIndex);
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

			System.out.println(videoNum);

			if (Xvideos.ToolDaoImpl.SaveOneCrawlersql(map)) {
//				// 入库操作
//				// 入单库双库判断
				listmap.add(map);
			}
		}
		try {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return listmap;
		}
	}

}
