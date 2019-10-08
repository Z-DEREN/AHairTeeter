package com.AHairTeeter.Tool.NetworkGraphic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.AHairTeeter.Tool.Route;

public class imageDownload {
	Route rou = new Route();
//	Scanner input = new Scanner(System.in);

	/**
	 * 垃圾图片防盗链
	 * 
	 * @param urlList
	 * @param imagerName
	 * @param referrer
	 * @return
	 */
	public String downloadPicture(String urlList, String imagerName, String referrer) {
		URL url = null;
		try {
			url = new URL(urlList);
			String houzui = urlList.substring(urlList.lastIndexOf("."));
			String name = urlList.substring(urlList.lastIndexOf("/"), urlList.lastIndexOf("."));
			String image = "";
			if (imagerName == null || imagerName.equals("")) {
				image = rou.一次性图片爬虫 + name + houzui;
			} else {
				image = imagerName + name + houzui;
			}
			File file = new File(image);
			File fileParent = file.getParentFile();
			if (!fileParent.exists()) {
				fileParent.mkdirs();
			}
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36 OPR/63.0.3368.94");
			if (!referrer.equals("") || referrer != null) {
				conn.addRequestProperty("referer", referrer);
			}
			InputStream is = conn.getInputStream();// 通过输入流获取图片数据
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			System.out.println("状态码：" + conn.getResponseCode());
			// 创建文件
			FileOutputStream out = new FileOutputStream(file);

			int i = 0;
			while ((i = is.read()) != -1) {
				out.write(i);
			}
			is.close();
			out.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			return null;
		}

	}
}