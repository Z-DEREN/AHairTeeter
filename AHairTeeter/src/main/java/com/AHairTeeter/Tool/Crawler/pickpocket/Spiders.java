package com.AHairTeeter.Tool.Crawler.pickpocket;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.nodes.Document;

/**
 * 多样爬虫工具
 * 
 * @author 好人
 *
 */
public class Spiders {

	private static final Logger logger = LogManager.getLogger(Spiders.class.getName());

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
		String Sdoc = "";
		Document doc = null;
		boolean TF = true;
		try {
			switch (num) {
			case 99999:
				doc = Jsoup.connect(url)
						.header("user-agent",
								"Mozilla/5.0 (Windows NT 10.0; WOW64) " + "AppleWebKit/537.36 (KHTML, like Gecko) "
										+ "Chrome/47.0.2526.106 BIDUBrowser/8.7 Safari/537.36")
						.ignoreContentType(true).get();
				Sdoc = doc.html();
				logger.info(" 爬取:" + url + ":结束:" + num + "------------------------------"); // info级别的信息
				break;
			default:
				TF = false;
				logger.info(" 爬取方法spiders参数有误:" + num + "------------------------------"); // info级别的信息
				break;
			}
			if (TF) {
				Sdoc = unicode(Sdoc);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return Sdoc;
		}
	}

	public String unicode(String asciicode) {
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
