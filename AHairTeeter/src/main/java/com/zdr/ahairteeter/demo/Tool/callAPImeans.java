package com.zdr.ahairteeter.demo.Tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class callAPImeans {
	
	private static final Logger logger = LogManager.getLogger(callAPImeans.class.getName());
	public String GetPOSTAPIinfo(String url, String query) throws Exception {
		URL restURL = new URL(url);
		/*
		 * 此处的urlConnection对象实际上是根据URL的请求协议(此处是http)生成的URLConnection类
		 * 的子类HttpURLConnection
		 */
		HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
		// 请求方式
		conn.setRequestMethod("POST");
		// 设置是否从httpUrlConnection读入，默认情况下是true; httpUrlConnection.setDoInput(true);
		conn.setRequestProperty("Charsert", "UTF-8");
		
		conn.setDoOutput(true);
		// allowUserInteraction 如果为 true，则在允许用户交互（例如弹出一个验证对话框）的上下文中对此 URL 进行检查。
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
		logger.info("调用接口返回数据:"+resultStr); // info级别的信息
		return resultStr;
	}
	
	
	
	
	

}
