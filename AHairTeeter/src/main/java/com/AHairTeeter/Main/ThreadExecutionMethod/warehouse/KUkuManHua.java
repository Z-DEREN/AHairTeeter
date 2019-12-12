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

	private int ADI = 25; // ����DIͷ
	Tool Tool = new Tool();
	String url = "https://comic.kukudm.com/";

	@Autowired
	protected ToolDaoImpl ToolDaoImpl;
	private static KUkuManHua KUkuManHua;

	@PostConstruct // ͨ��@PostConstructʵ�ֳ�ʼ��bean֮ǰ���еĲ���
	public void init() {
		KUkuManHua = this;
		KUkuManHua.ToolDaoImpl = this.ToolDaoImpl;
		// ��ʹ��ʱ���Ѿ�̬����testServiceʵ����
	}

	private static final Logger logger = LogManager.getLogger(KUkuManHua.class.getName());

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
