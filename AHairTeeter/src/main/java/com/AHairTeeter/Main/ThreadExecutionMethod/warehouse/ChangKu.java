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
	private int ADI = 23; // ����DIͷ
	Route rou = new Route();
	Tool Tool = new Tool();
	CangkuVo CangkuVo = new CangkuVo();

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static ChangKu ChangKu;

	@PostConstruct // ͨ��@PostConstructʵ�ֳ�ʼ��bean֮ǰ���еĲ���
	public void init() {
		ChangKu = this;
		ChangKu.ToolDaoImpl = this.ToolDaoImpl;
		// ��ʹ��ʱ���Ѿ�̬����testServiceʵ����
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
		System.setProperty("webdriver.chrome.driver", rou.��̬����ģ���ַ);
		WebDriver webDriver = new ChromeDriver();
		WebElement webElement = null;
		String cookiename = rou.�ֿ�cookiename;
		String cookievalue = rou.�ֿ�ookievalue;
		Cookie Cookies = new Cookie(cookiename, cookievalue);
		webDriver.manage().addCookie(Cookies);
		webDriver.get(url);
		webDriver.navigate().refresh();
		String urltext = "https://cangku.moe/page/";

		for (int zhi = begin; zhi <= end; zhi++) {
			System.out.println("��ǰҳ��Ϊ:" + zhi);
			try {
				urltext = urltext + zhi;
				// ��ȡ
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
	 * ҳ���ȡ����
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
				System.out.println("��ǰID:" + textno2 + "::" + new Date());
				urltext = "https://cangku.moe/archives/" + textno2;
				// ��ȡ
				text = GetWebDriver(webDriver, urltext);

				if (text.contains("404 not found")) {
					System.out.println("404 not found");
					continue;
				} else if (text.contains("NaN-NaN-NaN")) {
					System.out.println("NaN-NaN-NaN");
					continue;
				} else if (text.contains("��Դ����ʧ��")) {
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
			// ��������
			textno1 = text.substring(beginIndex, endIndex);

			// ����and����
			beginIndex = textno1.indexOf("<h1>");
			endIndex = textno1.indexOf("</h1>");
			textno2 = textno1.substring(beginIndex, endIndex);

			// ID
			newid = zhi;
			// System.out.println("ID==" + newid);

			// ����
			beginIndex = textno2.indexOf("[");
			endIndex = textno2.indexOf("]");

			if (beginIndex < 0 || endIndex < 0) {
				beginIndex = textno2.indexOf("��");
				endIndex = textno2.indexOf("��");
			}

			if (beginIndex < 0 || endIndex < 0) {
				type = null;
			} else {
				type = textno2.substring(beginIndex + 1, endIndex);
			}

			// System.out.println("����==" + type);

			// ʱ��//class="item release_date
			endIndex = textno1.indexOf("class=\"item release_date");
			textno2 = textno1.substring(0, endIndex);
			endIndex = textno2.lastIndexOf("<span");
			textno2 = textno2.substring(endIndex);
			beginIndex = textno2.indexOf("\"");
			endIndex = textno2.lastIndexOf("\"");
			time = textno2.substring(beginIndex + 1, endIndex);

			// System.out.println("ʱ��==" + time);

			textno3 = textno1.split("<div class=\"dl-box\">");

			for (int i = 1; i < textno3.length; i++) {

				// ������
				beginIndex = textno3[i].indexOf("text-truncate");
				endIndex = textno3[i].indexOf("</div>");
				panName = textno3[i].substring(beginIndex + 15, endIndex);

				beginIndex = textno3[i].indexOf("info");
				textno1 = textno3[i].substring(beginIndex);
				beginIndex = textno1.indexOf("</span>");
				textno1 = textno1.substring(0, beginIndex);

				textno1 = textno1.replace(" ", "");
				textno1 = textno1.replace("&nbsp;", "");
				// System.out.println("������:=====" + textno1);
				if (textno1.contains("����")) {

					beginIndex = textno1.indexOf("����");
					mima = textno1.substring(beginIndex + 3);

					textno1 = textno1.substring(0, beginIndex);

				} else {
					mima = "";
				}
				beginIndex = textno1.indexOf(":");
				if (beginIndex < 0) {
					beginIndex = textno1.indexOf("��");
				}

				if (textno1.length() >= beginIndex + 5) {
					tiqu = textno1.substring(beginIndex + 1, beginIndex + 5);
					System.out.println(textno1 + "/����ȡ��:" + tiqu);
				} else {
					tiqu = "";
					System.out.println(textno1 + "/����ȡ��");
				}

				beginIndex = textno3[i].indexOf("href=\"");
				endIndex = textno3[i].indexOf("target");
				pan = textno3[i].substring(beginIndex + 6, endIndex - 2);

				System.out.println("pan==" + pan);
				// System.out.println("ID==" + newid);
				// System.out.println("����==" + type);
				// System.out.println("ʱ��==" + time);
				// System.out.println("����==" + mima);
				// System.out.println("��ȡ��==" + tiqu);
				// System.out.println("����==" + panName);

				CangkuVo.setType(type);// ����
				CangkuVo.setNewid(newid + "");// id
				CangkuVo.setPanName(panName);// ����
				CangkuVo.setTiqu(tiqu);// ��ȡ��
				CangkuVo.setMima(mima);// ����
				CangkuVo.setTime(time);// ����ʱ��
				CangkuVo.setPan(pan);// ����
				// ���

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("SID", null);// �ַ���id
				map.put("ADI", ADI);// ����DIͷ
				map.put("ZDI", null);// ����DI��
				map.put("type", 1);// �洢����
				map.put("classify", url);// �洢��ʶ,��������Դͷ
				map.put("title", "ACG");// �洢����
				map.put("line", type);// �洢������
				map.put("url", pan);// ����
				map.put("uniqueid", newid);// �洢�����Դ�id
				map.put("text", null);// �������������ݴ洢��
				map.put("recorddate", time);// ������ʱ��
				map.put("acquiredate", Tool.GetNewDateTime(2));// ��ȡʱ��
				if (ChangKu.ToolDaoImpl.SaveOneCrawlersql(map)) {
					// ������
					// �뵥��˫���ж�
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
	 * ��̬��ȡ��ҳ
	 * 
	 * @param webDriver
	 * @param url
	 * @return
	 */
	public String GetWebDriver(WebDriver webDriver, String url) {
		webDriver.get(url);// д����Ҫץȡ����ַ
		// ��ȡ��ǰҳ����
		String handle = webDriver.getWindowHandle();
		// ��ȡ����ҳ��ľ������ѭ���жϲ��ǵ�ǰ�ľ��
		for (String handles : webDriver.getWindowHandles()) {
			if (handles.equals(handle))
				continue;
			webDriver.switchTo().window(handles);
		}
		// ˯��
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement webElement = webDriver.findElement(By.xpath("/html"));// ��ȡҳ��ȫ����Ϣ
		String text = webElement.getAttribute("outerHTML");
		return text;
	}

}
