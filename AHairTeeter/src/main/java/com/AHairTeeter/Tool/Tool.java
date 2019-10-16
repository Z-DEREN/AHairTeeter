package com.AHairTeeter.Tool;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Tool {
	private static final Logger logger = LogManager.getLogger(Tool.class.getName());

	
	
	private static SimpleDateFormat DT1 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat DT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat DT3 = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat DT4 = new SimpleDateFormat("HH:mm");
	MD5 md5 = new MD5();
	private static Map<String, SimpleDateFormat> DTMap = new HashMap<String, SimpleDateFormat>();


	
	
	/**
	 * ��̬����� (���뻺�浱��)
	 */
	static {
		logger.info(" ��̬���������------------------------------------------------------------"); // info�������Ϣ
		// ��ʼ��ʱ��ģʽMap
		DTMap.put("DT1", DT1);
		DTMap.put("DT2", DT2);
		DTMap.put("DT3", DT3);
		DTMap.put("DT4", DT4);
	}


	
	
	
	/**
	 * �½�ʱ���ʽ������
	 * 
	 * @param type ��Ӧ����
	 *             <p>
	 *             1 yyyy-MM-dd
	 *             </p>
	 *             <p>
	 *             2 yyyy-MM-dd HH:mm:ss
	 *             </p>
	 *             <p>
	 *             3 HH:mm:ss
	 *             </p>
	 *             <p>
	 *             4 HH:mm:ss
	 *             </p>
	 * @return String ʱ��
	 */
	public String GetNewDateTime(int type) {
		String DateTime = "";
		Date date = new Date();
		DateTime = DTMap.get("DT" + type).format(date);
		return DateTime;
	}

	/**
	 * ָ��ʱ���ʽ������
	 * 
	 * @param type ��Ӧ����
	 * @param date ָ��ʱ��
	 *             <p>
	 *             1 yyyy-MM-dd
	 *             </p>
	 *             <p>
	 *             2 yyyy-MM-dd HH:mm:ss
	 *             </p>
	 *             <p>
	 *             3 HH:mm:ss
	 *             </p>
	 *             <p>
	 *             4 HH:mm:ss
	 *             </p>
	 * @return String ʱ��
	 */
	public String GetNewDateTime(int type, String date) {
		String DateTime = "";
		try {
			Date sDate = DT2.parse(date);
			DateTime = DTMap.get("DT" + type).format(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DateTime;
	}

	private static Document doc = null;
	private static String Sdoc = null;

	/**
	 * ����ȡ����(ֻ�����ڼ���ȡ)
	 * 
	 * @param url
	 * @return ��Ӧ��ַԴ��
	 */
	public String BriefnessAcquire(String url) {

		try {
			doc = Jsoup.connect(url)
					.header("user-agent",
							"Mozilla/5.0 (Windows NT 10.0; WOW64) " + "AppleWebKit/537.36 (KHTML, like Gecko) "
									+ "Chrome/47.0.2526.106 BIDUBrowser/8.7 Safari/537.36")
					.ignoreContentType(true).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sdoc = doc.html();
		return Sdoc;
	}

	/**
	 * ��ȡ��ӦipDI���Լ���ز���
	 * @param typenum
	 * @return
	 */
	public List<String> GetIPtypeDI(String typenum) {
		List<String> list = new ArrayList<String>();
		String sql = "";
		
		switch (typenum) {
		case "61":
			logger.info(" ��ȡ���������ip��DI��------------------------------------------------------------"); // info�������Ϣ
			list.add(" SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '61%' ");
			list.add("���������ip");
			list.add("1");
			list.add("1");
			break;
		case "62":
			logger.info(" ��ȡ�������ip��DI��------------------------------------------------------------"); // info�������Ϣ
			list.add(" SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '62%' ");
			list.add("�������ip��");
			list.add("1");
			list.add("1");
			break;
		default:
			logger.info("��Ӧ��������:"+typenum); // info�������Ϣ
			break;
		}

		return list;
	}
	

}
