package com.AHairTeeter.Tool;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mysql.fabric.xmlrpc.base.Array;

@Repository
public class Tool {

	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LogManager.getLogger(Tool.class.getName());

	private static SimpleDateFormat DT1 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat DT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat DT3 = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat DT4 = new SimpleDateFormat("HH:mm");
	MD5 md5 = new MD5();
	private static Map<String, SimpleDateFormat> DTMap = new HashMap<String, SimpleDateFormat>();

	public String GetNewZDInum(String type,String data ) {
		
		
		Object[] array = new Object[7];

		String sql = "";
		String savesql = "INSERT INTO tonuminvi (ZDI,ZNAME,DATETIME,UPDATETIME,MD5DI,TYPE,MODEL) VALUES (?,?,?,?,?,?,?)";

		if (type.equals("61")) {
			System.out.println("��ȡ���������ip��DI��");
			logger.info(" ��ȡ���������ip��DI��------------------------------------------------------------"); // info�������Ϣ
			array[1] = "���������ip";
			
			sql = " SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '?%' ";

			array[5] = "1";
			array[6] = "1";
			
			
		} else if (type.equals("62")) {
			System.out.println("��ȡ�������ip��DI��");
			logger.info(" ��ȡ�������ip��DI��------------------------------------------------------------"); // info�������Ϣ

			sql = " SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '?%' ";
			
			
		} else if (type.equals("xx")) {
			System.out.println("��ȡ���������ip��DI��");
		} else if (type.equals("xx")) {
			System.out.println("��ȡ���������ip��DI��");
		} else if (type.equals("xx")) {
			System.out.println("��ȡ���������ip��DI��");
		} else if (type.equals("xx")) {
			System.out.println("��ȡ���������ip��DI��");
		} else if (type.equals("xx")) {
			System.out.println("��ȡ���������ip��DI��");
		} else if (type.equals("xx")) {
			System.out.println("��ȡ���������ip��DI��");
		} else if (type.equals("xx")) {
			System.out.println("��ȡ���������ip��DI��");
		}

		//��ȡ��Ӧ���͵�DI��
		String ZDI = GetArrayListSql(sql, new Object[]{type}, "String").toString();
		int NewZDI = Integer.parseInt(ZDI)+1;
		//���±�0�滻Ϊ��DI��
		array[0] = NewZDI;
		array[2] = GetNewDateTime(2);
		array[3] = GetNewDateTime(2);
		array[4] = md5.saltMD5(NewZDI+GetNewDateTime(2)+array[1].toString()+array[5].toString()+array[6].toString());
		
		
		int retnum = jdbcTemplate.update(savesql,array);
		if(retnum>0) {
			logger.info(" ��DI�����ɹ�------------------------------------------------------------"); // info�������Ϣ
			System.out.println("��DI�����ɹ�");
		}
		
		
		return NewZDI+"";
	}

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
	}

	// ���ݿ�����������

	/**
	 * Sql��ѯ����
	 * <p>
	 * ListMap
	 * </p>
	 * <p>
	 * ArrayObject
	 * </p>
	 * <p>
	 * HashMap
	 * </p>
	 * <p>
	 * ArrayList
	 * </p>
	 * <p>
	 * String
	 * </p>
	 * 
	 * @param Sql
	 * @param Value
	 * @return
	 */
	public Object GetArrayListSql(String sql, Object[] Value, String type) {
		// listmap
		List<Map<String, Object>> ListMap = new ArrayList<Map<String, Object>>();
		// list
		List<Object> ArrayObject = new ArrayList<Object>();
		// map
		Map<String, Object> HashMap = new HashMap<String, Object>();
		// array
		Object[] ArrayList = null;

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (Value == null) {
			System.out.println("ִ�е�SQLΪ:" + sql);
			logger.info("ִ�е�SQLΪ:" + sql); // info�������Ϣ
			list = jdbcTemplate.queryForList(sql);
		} else {
			System.out.println("ִ�е�SQLΪ:" + sql);
			String sqlvalue = "ִ�е�SQLΪ:" + sql + "; ֵΪ:";
			for (Object value : Value) {
				sqlvalue += value + " , ";
			}
			logger.info(sqlvalue); // info�������Ϣ
			list = jdbcTemplate.queryForList(sql, Value);
		}

		for (Map<String, Object> map : list) {
			Map<String, Object> AddMap = new HashMap<String, Object>();
			Set<Entry<String, Object>> entries = map.entrySet();
			if (entries != null) {
				Iterator<Entry<String, Object>> iterator = entries.iterator();
				while (iterator.hasNext()) {
					Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
					Object key = entry.getKey();
					Object value = entry.getValue();
					System.out.println(key + ":" + value);
					AddMap.put(key.toString(), value);
					HashMap.put(key.toString(), value);
					ArrayObject.add(value);
				}
			}
			ListMap.add(AddMap);
		}

		switch (type) {
		case "ListMap":
			return ListMap;
		case "ArrayObject":
			ArrayList = new Object[ArrayObject.size()];
			ArrayObject.toArray(ArrayList);
			return ArrayObject;
		case "HashMap":
			return HashMap;
		case "ArrayList":
			return ArrayList;
		case "String":
			return ArrayObject.get(0);
		default:
			System.out.println(" Tool Sql��ѯ���� - ��������ֵ���� ");
			logger.info(" Tool Sql��ѯ���� - ��������ֵ���� "); // info�������Ϣ
			return "null";
		}
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
	 * ����ȡ����(ֻ�����ڼ���ȡ)
	 * 
	 * @param url
	 * @return ��Ӧ��ַԴ��
	 */
	public String BriefnessAcquire(String url) {

		try {
			doc = Jsoup.connect(url)
					.header("user-agent",
							"Mozilla/5.0 (Windows NT 10.0; WOW64) " + "AppleWebKit/537.36 (KHTML, like Gecko) "
									+ "Chrome/47.0.2526.106 BIDUBrowser/8.7 Safari/537.36")
					.ignoreContentType(true).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sdoc = doc.html();
		return Sdoc;
	}

}
