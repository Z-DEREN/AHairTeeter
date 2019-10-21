package com.AHairTeeter.Tool.Crawler.pickpocket;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;

/**
 * 多样爬虫工具
 * 
 * @author 好人
 *
 */
public class Spiders {

	/**
	 * 
	 * 
	 * 1.静态爬虫 1.1.爬取接口 1.1.1.高匿动态用户 1.1.2.高匿 1.1.3.动态用户 1.1.999.裸奔 1.2.爬取页面
	 * 1.2.1.高匿动态用户 1.2.2.高匿 1.2.3.动态用户 1.2.999.裸奔
	 * 
	 * 
	 * 2.动态爬虫 2.1.爬取动态页面 1.2.1.高匿用户 1.2.999.裸奔
	 * 
	 * WebDrivers 模拟浏览器动态爬取
	 * 
	 * 
	 * @param url
	 * @param num
	 * @return
	 * @throws InterruptedException
	 */
	public String spiders(String url, int num) {
		Connection conn = null;
		String Sdoc = null;
		try {
			switch (num) {
			case 99999:
				conn = Jsoup.connect(url);
				conn.header("user-agent",
						"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.7 Safari/537.36");
				conn.ignoreContentType(true);
				conn.method(Method.GET);
				Response response;
				response = conn.execute();
				Sdoc = response.body();
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sdoc = unicode(Sdoc);
		return Sdoc;
	}
	
	public  String unicode(String asciicode) {
		String[] asciis = asciicode.split("\\\\u");
		String nativeValue = asciis[0];
		try {
			for (int i = 1; i < asciis.length; i++) {
				String code = asciis[i];
				nativeValue += (char) Integer.parseInt(code.substring(0, 4), 16);
				if (code.length() > 4) {
					nativeValue += code.substring(4, code.length());
				}
			}
		} catch (NumberFormatException e) {
			return asciicode;
		}
		return nativeValue;
	}

}
