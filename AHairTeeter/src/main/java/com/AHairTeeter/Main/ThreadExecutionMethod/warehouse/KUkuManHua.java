package com.AHairTeeter.Main.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;

public class KUkuManHua extends IoHandlerAdapter {

	private int ADI = 25; // 特殊DI头
	Tool Tool = new Tool();
	String url = "https://comic.kukudm.com/";

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static KUkuManHua KUkuManHua;

	@PostConstruct // 通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		KUkuManHua = this;
		KUkuManHua.ToolDaoImpl = this.ToolDaoImpl;
		// 初使化时将已静态化的testService实例化
	}

	private static final Logger logger = LogManager.getLogger(KUkuManHua.class.getName());

	/**
	 * 根据页码进行爬取
	 * 
	 * @param no1
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> first_no1(int no1, int no2, String Type) {
		/**
		 * 爬虫位 目前爬虫没有使用ip池,会被锁ip
		 */
		Spiders spiders = new Spiders();

		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		String text = "";
		for (int i = no1; i <= no2; i++) {
			System.out.println("正在进行第:" + i + "页数据爬取:" + url + Type + "/?page=" + i);
			try {
				text = spiders.spiders(url + Type + "/?page=" + i, 99999);
				listmap.addAll(first_no2(text));
				Thread.sleep(1000 * 10);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				continue;
			}
		}
		return listmap;
	}

	public List<Map<String, Object>> first_no2(String text) {
		
		
		
		
		
		
		return null;

	}

}
