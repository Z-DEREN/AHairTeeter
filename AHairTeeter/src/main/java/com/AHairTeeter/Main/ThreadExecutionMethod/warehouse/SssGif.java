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

/**
 * 
 * ͼƬ���ӻ�ȡ����(V)
 * 
 * @author ����
 * 
 */

@Component
public class SssGif extends IoHandlerAdapter {
	private int ADI = 20; // ����DIͷ
	Tool Tool = new Tool();
	String url = "https://www.sex.com/";

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static SssGif SssGif;

	@PostConstruct // ͨ��@PostConstructʵ�ֳ�ʼ��bean֮ǰ���еĲ���
	public void init() {
		SssGif = this;
		SssGif.ToolDaoImpl = this.ToolDaoImpl;
		// ��ʹ��ʱ���Ѿ�̬����testServiceʵ����
	}

	private static final Logger logger = LogManager.getLogger(SssGif.class.getName());

	
	
	/**
	 * ���������������
	 * @return
	 */
	public boolean TestNetworking() {
		boolean TF = false;
		Spiders spiders = new Spiders();
		String text = spiders.spiders(url, 99999);
		int beginIndex = text.indexOf("masonry_box ad-big");
		if (beginIndex > 0) {
			TF = true;
			logger.info("[�ɹ�] ������������ͨ��------------------------------------------------------------"); // info�������Ϣ
		}else {
			logger.info("[ʧ��] ������������ʧ��------------------------------------------------------------"); // info�������Ϣ
		}
		return TF;
	}
	
	
	
	
	/**
	 * ����ҳ�������ȡ
	 * 
	 * @param no1
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> first_no1(int no1, int no2, String Type) {
		/**
		 * ����λ Ŀǰ����û��ʹ��ip��,�ᱻ��ip
		 */
		Spiders spiders = new Spiders();
		
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String text = "";
		for (int i = no1; i <= no2; i++) {
			System.out.println("���ڽ��е�:" + i + "ҳ������ȡ:" + url + Type + "/?page=" + i);
			try {
				text = spiders.spiders(url + Type + "/?page=" + i, 99999);
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
		String textno1 = "";
		String urlList = "";
		beginIndex = text.indexOf("masonry_box ad-big");
		if (beginIndex < 0) {
			logger.info(" ��ǰҳ����δ��ȡ��------------------------------------------------------------"); // info�������Ϣ
			return listmap;
		}
		try {
			textno1 = text.substring(beginIndex);
			String[] textno3 = textno1.split("masonry_box small_pin_box");
			for (int i = 1; i < textno3.length - 1; i++) {
				beginIndex = textno3[i].indexOf("data-src=\"");
				urlList = textno3[i].substring(beginIndex + 10);
				endIndex = urlList.indexOf("\"");
				urlList = urlList.substring(0, endIndex);
				String type = urlList.substring(urlList.lastIndexOf(".") + 1);
				String id = urlList.substring(urlList.lastIndexOf("/") + 1, urlList.lastIndexOf("."));
				String returnurl = urlList.substring(urlList.lastIndexOf("images") - 1);
				String setuptime = urlList
						.substring(urlList.lastIndexOf("pinporn") + 8, urlList.lastIndexOf("pinporn") + 18)
						.replace("/", "-");
				Map<String, Object> map = new HashMap<String, Object>();

				// �����ж��Ƿ����
				boolean TF = true;
				if (TF) {
					map.put("SID", null);// �ַ���id
					map.put("ADI", ADI);// ����DIͷ
					map.put("ZDI", null);// ����DI��
					map.put("type", 1);// �洢����
					map.put("classify", url);// �洢��ʶ,��������Դͷ
					map.put("title", "ͼƬ");// �洢����
					map.put("line", type);// �洢������
					map.put("url", returnurl);// ����
					map.put("uniqueid", id);// �洢�����Դ�id
					map.put("text", null);// �������������ݴ洢��
					map.put("recorddate", setuptime);// ������ʱ��
					map.put("acquiredate", Tool.GetNewDateTime(2));// ��ȡʱ��
					if (SssGif.ToolDaoImpl.SaveOneCrawlersql(map)) {
						// ������
						// �뵥��˫���ж�
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

}