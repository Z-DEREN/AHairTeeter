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

	@PostConstruct // ͨ��@PostConstructʵ�ֳ�ʼ��bean֮ǰ���еĲ���
	public void init() {
		Kr36 = this;
		Kr36.ToolDaoImpl = this.ToolDaoImpl;
		// ��ʹ��ʱ���Ѿ�̬����testServiceʵ����
	}

	private static final Logger logger = LogManager.getLogger(Kr36.class.getName());

	/**
	 * ����ҳ�������ȡ
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
		} // �����ݿ����µ�ID
		NEWID = newID(); // ��ǰ��ַ������ID

		String url = newurl(ID+1, NEWID); // ������������ַ
//		String url = newurl(0, NEWID); // ������������ַ
		String Text = spiders.spiders((url), 99999);
//		String Text = spiders.spiders(("https://36kr.com/api/newsflash?b_id=187575&per_page=25"), 99999);

//		Tool.IOSaveFile(Text, "36");
		List<String> Clist = roughsaix(Text);
		listmap.addAll(ScreenText(Clist));// �������ݿⷽ��

		// ��¼��ǰ���Ż�ȡ����
		return listmap;
	}

	/**
	 * ���ݷָ��
	 * @param information Դ��
	 * @return �ָ��ļ���
	 */
	public List roughsaix(String information) {
		List<String> list = new ArrayList<String>();
		try {
			String[] Array = information.split("\\}\\,\\{");// ���ΪString����
			for (int i = 0; i < Array.length; i++) {
				list.add(Array[i]);// ת��Ϊ����
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return list;
		}
	}

	/**
	 * ����ɸѡ����
	 * @param Clist
	 * @return
	 */
	public List<Map<String, Object>> ScreenText(List<String> Clist) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int front;
		int later;
		// ����ȡ��ֵ
		String time;// created_at
		String ID;// id
		String title;// title
		String summary;// summary
		String newsurl;
		// ��¼��ǰ��ȡ����
		try {
			for (String information : Clist) {
				Map<String, Object> map = new HashMap<String, Object>();
				front = information.indexOf("title\":\"");
				later = information.indexOf("catch_title");
				if (front != -1 && later != -1) { // �ж��Ƿ�ΪҪ��ȡ��Ŀ��
					// title
					title = information.substring(front + 8, later - 3);
					
					front = information.indexOf("id\":");
					later = information.indexOf("project_id");
					// ID
					ID = information.substring(front + 4, later - 2);

					
					front = information.indexOf("description");
					later = information.indexOf("cover");
					// summary
					summary = information.substring(front + 14, later - 3);

					// url
					front = information.indexOf("\"news_url\":\"http");
					later = information.indexOf("user_id");
					if (front != -1) {
						newsurl = information.substring(front + 12, later - 3);
					} else {
						newsurl = null;
					}
					
					front = information.indexOf("created_at");
					later = information.indexOf("updated_at");
					// time
					time = information.substring(front + 13, later - 3);

					map.put("SID", null);// �ַ���id
					map.put("ADI", ADI);// ����DIͷ
					map.put("ZDI", null);// ����DI��
					map.put("type", 1);// �洢����
					map.put("classify", url);// �洢��ʶ,��������Դͷ
					map.put("title", "36Kr");// �洢����
					map.put("line", title);// �洢������
					map.put("url", newsurl);// ����
					map.put("uniqueid", ID);// �洢�����Դ�id
					map.put("text", summary);// �������������ݴ洢��
					map.put("recorddate", time);// ������ʱ��
					map.put("acquiredate", Tool.GetNewDateTime(2));// ��ȡʱ��
					// �����ж��Ƿ����
					if(Kr36.ToolDaoImpl.SaveOneCrawlersql(map)) {}{
						// ������
						// �뵥��˫���ж�1
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

	/**
	 * ��ǰ��ַ������ID 
	 * @return
	 */
	public int newID() {
		int front = 0;
		int later = 0;
		String textno1 = "";
		String newsURL = "https://36kr.com/newsflashes";
		try {
			// ��ȡ
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

	/**
	 * ������������ַ
	 * @param ID ҳ������ID
	 * @param NEWID ���ݿ�����ID
	 * @return
	 */
	public String newurl(int SQLID, int NEWID) {
		String http = "https://36kr.com/api/newsflash?b_id=AAAA&per_page=BBBB";
		String BBBB = "";
		if (SQLID == 0) {
			BBBB = "100";
		} else {
			BBBB = String.valueOf(NEWID - SQLID);
		}

		http = http.replace("AAAA", String.valueOf(NEWID));
		http = http.replace("BBBB", BBBB);
		logger.info(" ������ȡ��ַ" + http + "------------------------------------------------------------"); // info�������Ϣ
		return http;
	}
}
