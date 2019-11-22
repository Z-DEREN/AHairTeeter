package com.AHairTeeter.Tool;

import java.io.File;
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
import org.dom4j.Document;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Main.Vo.ZUSER;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Tool {
	private static final Logger logger = LogManager.getLogger(Tool.class.getName());
	private static SimpleDateFormat DT1 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat DT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat DT3 = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat DT4 = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat DT5 = new SimpleDateFormat("yyyyMMddHHmmss");
	ShaMD5 shamd5 = new ShaMD5();
	MD5 md5 = new MD5();
	IOLocalFile IOLocalFile = new IOLocalFile();
	private static Map<String, SimpleDateFormat> DTMap = new HashMap<String, SimpleDateFormat>();
	private final static String osp_home = "F:\\rdzgsq\\Database\\ALi120\\configuration.xml";
//	private final static String osp_home = "/home/pi/rdzgsq/Database/ALi120/configuration.xml";

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
		DTMap.put("DT5", DT5);
		logger.info(" ��̬����鸳ֵ����------------------------------------------------------------"); // info�������Ϣ
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
	 * ��ȡ��ӦipDI���Լ���ز���
	 * 
	 * @param typenum ����DIͷ(ADI)
	 * @return
	 */
	public List<String> GetIPtypeDI(String ADI) {
		List<String> list = new ArrayList<String>();
		String sql = "";

		switch (ADI) {
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
		case "20":
			logger.info(" ��ȡSssGif:20��DI��------------------------------------------------------------"); // info�������Ϣ
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '20%' ");
			list.add("SssGifͼƬ");
			list.add("1");
			list.add("1");
			break;
		case "21":
			logger.info(" ��ȡMoeimg:21��DI��------------------------------------------------------------"); // info�������Ϣ
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '21%' ");
			list.add("MoeimgͼƬ");
			list.add("1");
			list.add("1");
			break;
		case "22":
			logger.info(" ��ȡXvideos:22��DI��------------------------------------------------------------"); // info�������Ϣ
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '22%' ");
			list.add("Xvideos��Ƶ");
			list.add("1");
			list.add("1");
			break;
		case "23":
			logger.info(" ��ȡ�ֿ�����:23��DI��------------------------------------------------------------"); // info�������Ϣ
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '23%' ");
			list.add("�ֿ�AGC");
			list.add("1");
			list.add("1");
			break;
		case "24":
			logger.info(" ��ȡ36Kr:24��DI��------------------------------------------------------------"); // info�������Ϣ
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '24%' ");
			list.add("36Kr");
			list.add("1");
			list.add("1");
			break;

		default:
			logger.info("��Ӧ��������:" + ADI); // info�������Ϣ
			break;
		}

		return list;
	}

	/**
	 * ������������several �� Olist ���Ͻ��зָ� ���� ���ڶ��̷߳�������
	 * 
	 * @param Olist
	 * @param several
	 * @return
	 */
	public List<List<Map<String, Object>>> SplitSet(List<Map<String, Object>> Olist, int several) {
		List<List<Map<String, Object>>> ROlist = new ArrayList<List<Map<String, Object>>>();
		///////////////
		System.out.println(Olist.size());
		System.out.println(several);
		//////////////////
		if (Olist.size() > 0 && Olist.size() > several) {
			int nums = Olist.size() / several;
			int start = 0;
			int terminus = nums;
			for (int i = 0; i < several; i++) {
				if (i == 0) {
					ROlist.add(Olist.subList(start, terminus));
				} else {
					if (i == (several - 1)) {
						ROlist.add(Olist.subList(start, Olist.size()));
					} else {
						ROlist.add(Olist.subList(start, terminus));
					}
				}
				start += nums;
				terminus += nums;
			}
			return ROlist;
		}
		return null;
	}

	/**
	 * ����������λ���ݱ���������
	 * 
	 * @param listmap
	 */
	public void IOSaveFile(List<Map<String, Object>> listmap) {
		String text = "";
		Boolean TF = false;
		for (Map<String, Object> map : listmap) {
			if (map.containsKey("specialIO") && map.get("specialIO") != null) {
				TF = true;
				text += map.get("specialIO").toString() + "\r\n";
			}
		}
		if (TF) {
			IOSaveFile(text, "");
		}
	}

	/**
	 * �����ݱ��浽����
	 * 
	 * @param text
	 * @param name
	 */
	public void IOSaveFile(String text, String name) {
		IOLocalFile.input(text, "IOSave_" + name + "_" + GetNewDateTime(5));
	}

	/////////////////////////////////////////////// ��ȡ�ⲿ�ļ�����/////////////////////////////////////////////////////

	/**
	 * ��ѯָ���ļ���Ҫ��ʾ���ֶ�
	 * 
	 * @param tableId
	 * @return
	 */
//	protected List<String> getDisplayColumns(String tableId) {
	public static List<String> getDisplayColumns(String tableId) {
		List<Element> element = loadXml();
		List<String> list = new ArrayList<String>();
		for (Element elem : element) {
			String thisTableId = elem.attributeValue("id");
			if (thisTableId.equals(tableId)) {
				list = readNode(elem, list);
			}
		}
		return list;
	}

	/**
	 * ��ȡ���õ�ͨ������Ϣ
	 * 
	 * @return
	 * @date 2017-5-2 ����11:38:27
	 * @author
	 */
	public static List<Element> loadXml() {
		SAXReader reader = new SAXReader();

		File file = new File(osp_home);
		try {
			Document document = reader.read(file);
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			// �鿴�����˵�ÿ�ű����ʾ��
			List<String> list = new ArrayList<String>();
			return elements;
		} catch (DocumentException e) {
			System.out.println("��ȡͨ�������ļ��쳣�������Ƿ���ȷ����" + e);
			return new ArrayList<Element>();
		}
	}

	/**
	 * ѭ���������ļ��е��豸��Ҫ��ʾ����
	 * 
	 * @param element
	 * @param map
	 * @return
	 * @date 2017-5-2 ����5:05:25
	 * @author
	 */
	public static List<String> readNode(Element element, List<String> list) {
		// System.out.println("---��������" + element.getName());

		if (!(element.getTextTrim().equals(""))) {
			// System.out.println("�ı����ݣ�������" + element.getText());
			if ("column".equals(element.getName())) {
				list.add(element.getText().trim());
			}
		}

		List<Element> childElems = element.elements();
		if (childElems != null && childElems.size() != 0) {
			List<List<String>> tableNode = new ArrayList<List<String>>();
			for (Element elem : childElems) {
				// System.out.println("aaaa�ı����ݣ�������========"+element.getText());
				tableNode.add(readNode(elem, list));
			}
		}
		return list;
	}

///////////////////////////////////////////////���ݼ��ܽ��ܷ���/////////////////////////////////////////////////////

	/**
	 * MD5
	 */
	public String Md5(String pass) {
		String MD5 = md5.Encode(pass);
		return MD5;
	}

	/**
	 * MD5����
	 */
	public String Md5(String pass, String sha) {
		ShaMD5 encoderSha = new ShaMD5(sha, "SHA");// �ȼ���
		String MD5 = encoderSha.encode(pass);// ��Ӽ���ֵ
		return MD5;
	}

	/**
	 * puzzlekey �̶� SPARE1~5 MD5DI ÿ��¼һ�θ���һ�� NAME �û���
	 * 
	 * @param EPKEY ��Կ
	 * @return
	 * 
	 * 
	 *         ���ݿⱣ��һ��md5 �����˺������
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *         �˺��������
	 * 
	 *         ���ݿ�md5����
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public ZUSER GetInfoEpilepsy(List<Object> EPKEY, String puzzlekey, ZUSER zuser) {
		codec codec = new codec();
		String MD5 = Md5(zuser.getPass());
		String key = "";
		String[] puzzKey = puzzlekey.split("");
		String passMD5 = MD5;
		for (int num = 0; num < puzzKey.length; num++) {
			passMD5 = codec.puzzEncrypt(EPKEY.get(num).toString(), passMD5, puzzKey[num]);
		}
		String shaMD5 = Md5(zuser.getPass() + MD5, passMD5);
		System.out.println(shaMD5);

		if (zuser.getZMD5().equals(shaMD5)) {
			System.out.println("��¼��֤�ɹ�");
			String USERTYPE = shaMD5;
			for (int num = 0; num < puzzKey.length; num++) {
				USERTYPE = codec.puzzEncrypt(EPKEY.get(num).toString(), USERTYPE, puzzKey[num]);
			}

			System.out.println("�û���¼״̬��ʶ:" + USERTYPE);
			System.out.println("�û���¼״̬��ʶ:" + USERTYPE.substring(0, zuser.getPass().length()));
			zuser.setD7788b7e0ba4b6e3aa57b35bbf93dfc6(USERTYPE.substring(0, zuser.getPass().length()));
		} else {
			System.out.println("��¼��֤ʧ��,�Է����߽��м���");

		}
		zuser.setZMD5("233333333");
		zuser.setPass("233333333");

		return zuser;
	}

	///////////////////////////////// �����������split����0///////////////////////////////////////////

	/**
	 * �����������split����
	 * @param text ��Ҫ��ֵ��ַ���
	 * @param regex ���������ַ������
	 * @return
	 */
	public String[] split(String text, String regex) {
		List<String> Listtestno3 = new ArrayList<String>();
		String split = "},{";
		Boolean TF = true;
		do {
			if (text.indexOf(split) > 0) {
				Listtestno3.add(text.substring(0, text.indexOf(split)));
				text = text.substring(text.indexOf(split) + split.length());
			} else {
				Listtestno3.add(text);
				TF = false;
			}
		} while (TF);

		String[] textno3 = new String[Listtestno3.size()];
		Listtestno3.toArray(textno3);
		return textno3;
	}

}
