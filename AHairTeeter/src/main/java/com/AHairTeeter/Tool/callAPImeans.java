package com.AHairTeeter.Tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class callAPImeans {
	
	private static final Logger logger = LogManager.getLogger(callAPImeans.class.getName());
	public String load(String url, String query) throws Exception {
		URL restURL = new URL(url);
		/*
		 * �˴���urlConnection����ʵ�����Ǹ���URL������Э��(�˴���http)���ɵ�URLConnection��
		 * ������HttpURLConnection
		 */
		HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
		// ����ʽ
		conn.setRequestMethod("POST");
		// �����Ƿ��httpUrlConnection���룬Ĭ���������true; httpUrlConnection.setDoInput(true);
		conn.setRequestProperty("Charsert", "UTF-8");
		
		conn.setDoOutput(true);
		// allowUserInteraction ���Ϊ true�����������û����������絯��һ����֤�Ի��򣩵��������жԴ� URL ���м�顣
		conn.setAllowUserInteraction(false);

		PrintStream ps = new PrintStream(conn.getOutputStream());
		ps.print(query);

		ps.close();

		BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String line, resultStr = "";

		while (null != (line = bReader.readLine())) {
			line = new String(line.getBytes(), "utf-8");
			resultStr += line;
		}
		
		bReader.close();
		logger.info("���ýӿڷ�������:"+resultStr); // info�������Ϣ
		return resultStr;
	}
	
	
	
	
	

}
