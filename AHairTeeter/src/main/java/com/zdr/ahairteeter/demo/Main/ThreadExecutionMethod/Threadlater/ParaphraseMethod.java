package com.zdr.ahairteeter.demo.Main.ThreadExecutionMethod.Threadlater;

import java.util.Map;
import java.util.Scanner;

import com.zdr.ahairteeter.demo.Tool.IMGNetworkGraphic.imageDownload;
import com.zdr.ahairteeter.demo.Tool.Route;
import com.zdr.ahairteeter.demo.Tool.Tool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zdr.ahairteeter.demo.Tool.IMGNetworkGraphic.imageDownload;

/**
 * 多线程
 */
@Service
public class ParaphraseMethod {
	private static final Logger logger = LogManager.getLogger(ParaphraseMethod.class.getName());
	@Autowired
	private  Route Route ;
	@Autowired
	private imageDownload imageDownload ;

	/**
	 * 多线程执行方法
	 * 
	 * @param map  参数
	 * @param type 执行对应方法
	 * @param name 文件夹名
	 */
	public void method(Map<String, Object> map, int type, String name, String url) {

		switch (type) {
		case 0:
			String testid = map.get("testid").toString();
//			System.out.println(testid);
			break;
		case 20:
		case 21:
			String urlList = map.get("url").toString();
			imageDownload.downloadPicture(urlList, Route.getPictureCrawler()+name+"\\", url,null);
			break;
		case 3:

			break;

		default:
			break;
		}

	}

}
