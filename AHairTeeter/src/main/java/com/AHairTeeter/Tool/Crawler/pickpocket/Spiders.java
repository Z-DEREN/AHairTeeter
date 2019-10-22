package com.AHairTeeter.Tool.Crawler.pickpocket;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;


/**
 * �������湤��
 * 
 * @author ����
 *
 */
public class Spiders {

	
	private static final Logger logger = LogManager.getLogger(Spiders.class.getName());
	
	
	/**
	 * 
	 * 
	 * 1.��̬���� 1.1.��ȡ�ӿ� 1.1.1.���䶯̬�û� 1.1.2.���� 1.1.3.��̬�û� 1.1.999.�㱼 1.2.��ȡҳ��
	 * 1.2.1.���䶯̬�û� 1.2.2.���� 1.2.3.��̬�û� 1.2.999.�㱼
	 * 
	 * 
	 * 2.��̬���� 2.1.��ȡ��̬ҳ�� 1.2.1.�����û� 1.2.999.�㱼
	 * 
	 * WebDrivers ģ���������̬��ȡ
	 * 
	 * 
	 * @param url
	 * @param num
	 * @return
	 * @throws InterruptedException
	 */
	public String spiders(String url, int num) {
		Connection conn = null;
		String Sdoc = "";
		boolean TF = true;
		try {
			switch (num) {
			case 99999:
				conn = Jsoup.connect(url);
				conn.header("user-agent",
						"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 BIDUBrowser/8.7 Safari/537.36");
				conn.ignoreContentType(true);
				conn.method(Method.GET);
				Sdoc = conn.execute().body();
				logger.info(" ��ȡ:"+url+":����:"+num+"------------------------------"); // info�������Ϣ
				break;
			default:
				TF = false;
				logger.info(" ��ȡ����spiders��������:"+num+"------------------------------"); // info�������Ϣ
				break;
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(TF) {
			Sdoc = unicode(Sdoc);
		}
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
