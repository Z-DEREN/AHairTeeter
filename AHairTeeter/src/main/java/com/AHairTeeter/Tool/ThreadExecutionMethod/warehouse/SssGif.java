package com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;


/**
 * 
 * ͼƬ���ӻ�ȡ����(V)
 * @author ����
 * 
 */
public class SssGif {
	private int ADI = 20;	//����DIͷ
	Tool Tool = new Tool();
	String url = "https://www.sex.com/";
	/**
	 * ����ҳ�������ȡ
	 * 
	 * @param no1
	 * @param no2
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> first_no1(int no1, int no2,String Type) {
		/**
		 * ����λ
		 * Ŀǰ����û��ʹ��ip��,�ᱻ��ip
		 */
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = no1; i <= no2; i++) {
				System.out.println("���ڽ��е�:" + i + "ҳ������ȡ:" + url+Type+"/?page=" + i);
				String text = spiders.spiders(url+Type+"/?page=" + i, 1);
//				System.out.println(text);
				listmap.addAll(first_no2(text));
				Thread.sleep(1000 * 10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		textno1 = text.substring(beginIndex);
		String[] textno3 = textno1.split("masonry_box small_pin_box");
		for (int i = 1; i < textno3.length - 1; i++) {
			beginIndex = textno3[i].indexOf("data-src=\"");
			urlList = textno3[i].substring(beginIndex + 10);
			endIndex = urlList.indexOf("\"");
			urlList = urlList.substring(0, endIndex);
//			System.out.println(urlList);
			String type = urlList.substring(urlList.lastIndexOf(".") + 1);
			String id = urlList.substring(urlList.lastIndexOf("/") + 1, urlList.lastIndexOf("."));
			String returnurl = urlList.substring(urlList.lastIndexOf("images") - 1);
			String setuptime = urlList
					.substring(urlList.lastIndexOf("pinporn") + 8, urlList.lastIndexOf("pinporn") + 18)
					.replace("/", "-");
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("SID", null);//�ַ���id
			map.put("ADI", ADI);//����DIͷ
			map.put("ZDI", null);//����DI��
			map.put("type", 1);//�洢����
			map.put("classify", url);//�洢��ʶ,��������Դͷ
			map.put("title", "ͼƬ");//�洢����
			map.put("line", type);//�洢������
			map.put("url", returnurl);//����
			map.put("uniqueid", id);//�洢�����Դ�id
			map.put("text",null);//�������������ݴ洢��
			map.put("recorddate", setuptime);//������ʱ��
			map.put("acquiredate", Tool.GetNewDateTime(2));//��ȡʱ��
			
			listmap.add(map);
		}
		return listmap;
	}

}
