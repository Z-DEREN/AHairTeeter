package com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

public class Xvideos {
	
	private int ADI = 22;	//����DIͷ
	Tool Tool = new Tool();
	String url = "https://www.xvideos.com/";

	/**
	 * ����ҳ�������ȡ
	 * 
	 * @param no1
	 * @param no2
	 * @return
	 */
	public List<Map<String, Object>> videoNum(int no1, int no2,String Type) {
		/**
		 * ����λ Ŀǰ����û��ʹ��ip��,�ᱻ��ip
		 */
		Spiders spiders = new Spiders();
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		try {
			for (int i = no1; i <= no2; i++) {
				System.out.println("���ڽ��е�:" + i + "ҳ������ȡ:" + url+"tags/"+Type+"/" + i);
				String text = spiders.spiders(url+"tags/"+Type+"/" + i, 1);
				listmap.addAll(videoWAP(text,Type));
				Thread.sleep(1000 * 10);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listmap;
	}

//	public static void main(String[] args) {
//		IOLocalFile IOLocalFile = new IOLocalFile();
//		String text = IOLocalFile.output("F:\\rdzgsq\\Laboratory\\����\\xvideostest1.txt");
//		Xvideos Xvideos = new Xvideos();
//		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
//		listmap.addAll(Xvideos.videoWAP(text,"2333"));
//	}

	/**
	 * ����ɸѡ
	 * @param text
	 * @return
	 */
	public List<Map<String, Object>> videoWAP(String text,String Type) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		int beginIndex = 0;
		int endIndex = 0;
		String textno1 = "";
		String videoNum = "";
		beginIndex = text.indexOf("<div id=\"content\">");
		textno1 = text.substring(beginIndex);
		endIndex= textno1.indexOf("<div class=\"pagination \">");
		textno1 = textno1.substring(0,endIndex);
		String[] textno3 = textno1.split("xv.thumbs.prepareVideo");
		for (int i = 1; i < textno3.length - 1; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			endIndex = textno3[i].indexOf(")");
			videoNum = textno3[i].substring(1,endIndex);
			map.put("videoNum", videoNum);
			map.put("type", Type);
			System.out.println(videoNum);
			
			map.put("SID", null);//�ַ���id
			map.put("ADI", ADI);//����DIͷ
			map.put("ZDI", null);//����DI��
			map.put("type", 1);//�洢����
			map.put("classify", url);//����
			map.put("title", "��Ƶid");//�洢����
			map.put("line", Type);//�洢����
			map.put("url", videoNum);//����
			map.put("uniqueid", videoNum);//�洢�����Դ�id
			map.put("text",null);//�������������ݴ洢��
			map.put("recorddate", null);//������ʱ��
			map.put("acquiredate", Tool.GetNewDateTime(2));//��ȡʱ��
			
			listmap.add(map);
		}
		return listmap;
	}
}
