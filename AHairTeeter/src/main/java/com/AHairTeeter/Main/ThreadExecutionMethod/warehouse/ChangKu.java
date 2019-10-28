package com.AHairTeeter.Main.ThreadExecutionMethod.warehouse;

import com.AHairTeeter.Tool.Route;
import com.AHairTeeter.Tool.Tool;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Main.GentlemanCangku.CangkuDaoServiceImpl;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Main.Vo.CangkuVo;

@Component
public class ChangKu extends IoHandlerAdapter {
	private int ADI = 23; // 特殊DI头
	Route rou = new Route();
	Tool Tool = new Tool();
	CangkuVo CangkuVo = new CangkuVo();

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static ChangKu ChangKu;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		ChangKu = this;
		ChangKu.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}

	int beginIndex;
	int endIndex;
	String textno1;
	String textno2;
	String[] textno3;
	String text;
	String url = "https://cangku.moe";

	public List<Map<String, Object>> first_no1(int begin, int end) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		System.setProperty("webdriver.chrome.driver", rou.动态爬虫模块地址);
		WebDriver webDriver = new ChromeDriver();
		WebElement webElement = null;
		String cookiename = rou.仓库cookiename;
		String cookievalue = rou.仓库ookievalue;
		Cookie Cookies = new Cookie(cookiename, cookievalue);
		webDriver.manage().addCookie(Cookies);
		webDriver.get(url);
		webDriver.navigate().refresh();
		String urltext = "https://cangku.moe/page/";

		for (int zhi = begin; zhi <= end; zhi++) {
			System.out.println("当前页数为:" + zhi);
			try {
				urltext = urltext + zhi;
				// 爬取
				text = GetWebDriver(webDriver, urltext);
				listmap.addAll(Pagination(urltext, webDriver));
			}  catch (Exception e) {
				e.printStackTrace();
			} finally {
				continue;
			}
		}

		return listmap;
	}

	/**
	 * 页码获取数据
	 * 
	 * @return
	 */
	public List<Map<String, Object>> Pagination(String urltext, WebDriver webDriver) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		beginIndex = text.indexOf("post-list");
		endIndex = text.indexOf("page-pagination");
		if (beginIndex < 0 || endIndex < 0) {
			return listmap;
		}
		try {
			textno1 = text.substring(beginIndex, endIndex);
			String[] testno3 = textno1.split("title-wrap");
			for (int i = 1; i < testno3.length; i++) {
				beginIndex = testno3[i].indexOf("archives");
				textno2 = testno3[i].substring(beginIndex + 9);
				beginIndex = textno2.indexOf("\"");
				textno2 = textno2.substring(0, beginIndex);
				System.out.println("当前ID:" + textno2 + "::" + new Date());
				urltext = "https://cangku.moe/archives/" + textno2;
				// 爬取
				text = GetWebDriver(webDriver, urltext);

				if (text.contains("404 not found")) {
					System.out.println("404 not found");
					continue;
				} else if (text.contains("NaN-NaN-NaN")) {
					System.out.println("NaN-NaN-NaN");
					continue;
				} else if (text.contains("资源加载失败")) {
					System.out.println("NaN-NaN-NaN");
					continue;
				} else {
					listmap.addAll(first_no3(text, endIndex));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return listmap;
		}
	}

	public List<Map<String, Object>> first_no3(String text, int zhi) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String type;
		int newid;
		String panName;
		String tiqu;
		String mima;
		String time;
		String pan;

		beginIndex = text.indexOf("</aside>");
		endIndex = text.indexOf("tag-wrap");

		if (beginIndex < 0 || endIndex < 0) {
			return listmap;
		}
		try {
			// 所需区域
			textno1 = text.substring(beginIndex, endIndex);

			// 类型and名字
			beginIndex = textno1.indexOf("<h1>");
			endIndex = textno1.indexOf("</h1>");
			textno2 = textno1.substring(beginIndex, endIndex);

			// ID
			newid = zhi;
			// System.out.println("ID==" + newid);

			// 类型
			beginIndex = textno2.indexOf("[");
			endIndex = textno2.indexOf("]");

			if (beginIndex < 0 || endIndex < 0) {
				beginIndex = textno2.indexOf("【");
				endIndex = textno2.indexOf("】");
			}

			if (beginIndex < 0 || endIndex < 0) {
				type = null;
			} else {
				type = textno2.substring(beginIndex + 1, endIndex);
			}

			// System.out.println("类型==" + type);

			// 时间//class="item release_date
			endIndex = textno1.indexOf("class=\"item release_date");
			textno2 = textno1.substring(0, endIndex);
			endIndex = textno2.lastIndexOf("<span");
			textno2 = textno2.substring(endIndex);
			beginIndex = textno2.indexOf("\"");
			endIndex = textno2.lastIndexOf("\"");
			time = textno2.substring(beginIndex + 1, endIndex);

			// System.out.println("时间==" + time);

			textno3 = textno1.split("<div class=\"dl-box\">");

			for (int i = 1; i < textno3.length; i++) {

				// 盘名称
				beginIndex = textno3[i].indexOf("text-truncate");
				endIndex = textno3[i].indexOf("</div>");
				panName = textno3[i].substring(beginIndex + 15, endIndex);

				beginIndex = textno3[i].indexOf("info");
				textno1 = textno3[i].substring(beginIndex);
				beginIndex = textno1.indexOf("</span>");
				textno1 = textno1.substring(0, beginIndex);

				textno1 = textno1.replace(" ", "");
				textno1 = textno1.replace("&nbsp;", "");
				// System.out.println("问题码:=====" + textno1);
				if (textno1.contains("密码")) {

					beginIndex = textno1.indexOf("密码");
					mima = textno1.substring(beginIndex + 3);

					textno1 = textno1.substring(0, beginIndex);

				} else {
					mima = "";
				}
				beginIndex = textno1.indexOf(":");
				if (beginIndex < 0) {
					beginIndex = textno1.indexOf("：");
				}

				if (textno1.length() >= beginIndex + 5) {
					tiqu = textno1.substring(beginIndex + 1, beginIndex + 5);
					System.out.println(textno1 + "/有提取码:" + tiqu);
				} else {
					tiqu = "";
					System.out.println(textno1 + "/无提取码");
				}

				beginIndex = textno3[i].indexOf("href=\"");
				endIndex = textno3[i].indexOf("target");
				pan = textno3[i].substring(beginIndex + 6, endIndex - 2);

				System.out.println("pan==" + pan);
				// System.out.println("ID==" + newid);
				// System.out.println("类型==" + type);
				// System.out.println("时间==" + time);
				// System.out.println("密码==" + mima);
				// System.out.println("提取码==" + tiqu);
				// System.out.println("名字==" + panName);

				CangkuVo.setType(type);// 分类
				CangkuVo.setNewid(newid + "");// id
				CangkuVo.setPanName(panName);// 名字
				CangkuVo.setTiqu(tiqu);// 提取码
				CangkuVo.setMima(mima);// 密码
				CangkuVo.setTime(time);// 数据时间
				CangkuVo.setPan(pan);// 链接
				// 入库

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("SID", null);// 字符串id
				map.put("ADI", ADI);// 特殊DI头
				map.put("ZDI", null);// 特殊DI码
				map.put("type", 1);// 存储类型
				map.put("classify", url);// 存储标识,区分数据源头
				map.put("title", "ACG");// 存储标题
				map.put("line", type);// 存储行数据
				map.put("url", pan);// 链接
				map.put("uniqueid", newid);// 存储数据自带id
				map.put("text", null);// 大容量主体数据存储体
				map.put("recorddate", time);// 数据内时间
				map.put("acquiredate", Tool.GetNewDateTime(2));// 爬取时间
				if (ChangKu.ToolDaoImpl.SaveOneCrawlersql(map)) {
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
	 * 动态爬取网页
	 * 
	 * @param webDriver
	 * @param url
	 * @return
	 */
	public String GetWebDriver(WebDriver webDriver, String url) {
		webDriver.get(url);// 写入你要抓取的网址
		// 获取当前页面句柄
		String handle = webDriver.getWindowHandle();
		// 获取所有页面的句柄，并循环判断不是当前的句柄
		for (String handles : webDriver.getWindowHandles()) {
			if (handles.equals(handle))
				continue;
			webDriver.switchTo().window(handles);
		}
		// 睡眠
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement webElement = webDriver.findElement(By.xpath("/html"));// 获取页面全部信息
		String text = webElement.getAttribute("outerHTML");
		return text;
	}

}
