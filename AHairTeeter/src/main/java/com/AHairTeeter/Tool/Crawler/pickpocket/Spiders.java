package com.AHairTeeter.Tool.Crawler.pickpocket;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
 * @author 好人
 *
 */
public class Spiders {

	
	public String spiders(String url, int num) throws InterruptedException {
		TypeTest user = new TypeTest();
//		List<Object> list = user.Seluser();
//		Random random = new Random();
//		int n = random.nextInt(list.size());
//		Map<String, String> passmap = (Map<String, String>) list.get(n);
//		System.out.println(passmap.get("useragent"));
		Document doc = null;
		Connection.Response res = null;
		String Sdoc = null;
		try {
			switch (num) {
			case 1:
				doc = Jsoup.connect(url).header("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) " + "AppleWebKit/537.36 (KHTML, like Gecko) "
						+ "Chrome/47.0.2526.106 BIDUBrowser/8.7 Safari/537.36").ignoreContentType(true).get();
				Sdoc = doc.html();
				break;
			case 2:
//				doc = Jsoup.connect(url).header("user-agent", passmap.get("useragent")).get();
				Sdoc = doc.html();
				break;
			case 3:
//				res = Jsoup.connect(url).header("user-agent", passmap.get("useragent")).method(Method.POST).execute();
				res.toString();
				break;
			case 4:
				WebDrivers wd = new WebDrivers();
				Sdoc = wd.Dynamic(url);
				break;
			case 5:
				jsoupnew jsoupnew = new jsoupnew();
				Sdoc = jsoupnew.webParseHtml(url);
				break;
			}
			Sdoc = unicode.unicode(Sdoc);

		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return Sdoc;

	}
	
	
	
	
	
}
