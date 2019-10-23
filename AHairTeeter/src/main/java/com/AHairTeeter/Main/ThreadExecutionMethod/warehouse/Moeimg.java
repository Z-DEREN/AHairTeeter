package com.AHairTeeter.Main.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.Date;
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

/**
 * ACGͼƬ���ӻ�ȡ����
 * 
 * @author ����
 *
 */
@Component
public class Moeimg extends IoHandlerAdapter {

	private final String url = "http://moeimg.net/";
	Tool Tool = new Tool();
	private int ADI = 21;

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static Moeimg Moeimg;

	@PostConstruct // ͨ��@PostConstructʵ�ֳ�ʼ��bean֮ǰ���еĲ���
	public void init() {
		Moeimg = this;
		Moeimg.ToolDaoImpl = this.ToolDaoImpl;
		// ��ʹ��ʱ���Ѿ�̬����testServiceʵ����
	}
	
	
	private static final Logger logger = LogManager.getLogger(Moeimg.class.getName());

	/**
	 * ����ҳ�������ȡ
	 * 
	 * @param     no1-10000
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> moeimg_img(int no1, int no2) {

		/**
		 * ����λ Ŀǰ����û��ʹ��ip��,�ᱻ��ip
		 */
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = no1; i <= no2; i++) {
			try {
				System.out.println("���ڽ��е�:" + i + "ҳ������ȡ:" + url + i);
				String text = spiders.spiders((url + i + ".html"), 1);
				listmap.addAll(moeimgSub(text, i, url + i));
				Thread.sleep(1000 * 10);
			} finally {
				continue;
			}
		}
		return listmap;
	}

	public List<Map<String, Object>> moeimgSub(String text, int num, String referrerurl) {
		Date date = new Date();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String urlList = "";
		beginIndex = text.indexOf("contents");
		if (beginIndex < 0) {
			logger.info(" ��ǰҳ����δ��ȡ��------------------------------------------------------------"); // info�������Ϣ
			return listmap;
		}
		try {
			textno1 = text.substring(beginIndex);
			beginIndex = textno1.indexOf("<div class=\"pc_ad\">");
			textno1 = textno1.substring(beginIndex);
			beginIndex = textno1.lastIndexOf("<div class=\"pc_ad\">");
			textno1 = textno1.substring(0, beginIndex);
			String[] textno3 = textno1.split("<div class=\"box\">");
			for (int i = 1; i < textno3.length - 1; i++) {
				beginIndex = textno3[i].indexOf("<a href=\"");
				urlList = textno3[i].substring(beginIndex + 9);
				endIndex = urlList.indexOf("\" target=\"_blank\"");
				urlList = urlList.substring(0, endIndex);
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("SID", null);// �ַ���id
				map.put("ADI", ADI);// ����DIͷ
				map.put("ZDI", null);// ����DI��
				map.put("type", 1);// �洢����
				map.put("classify", url);// ����
				map.put("title", "ͼƬ");// �洢����
				map.put("line", null);// �洢������
				map.put("url", urlList);// ����
				map.put("uniqueid", null);// �洢�����Դ�id
				map.put("text", null);// �������������ݴ洢��
				map.put("recorddate", null);// ������ʱ��
				map.put("acquiredate", Tool.GetNewDateTime(2));// ��ȡʱ��
				if(Moeimg.ToolDaoImpl.SaveOneCrawlersql(map)) {
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
}
