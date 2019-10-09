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
			System.out.println("获取高匿待测试ip段DI码");
			logger.info(" 获取高匿待测试ip段DI码------------------------------------------------------------"); // info级别的信息
			array[1] = "高匿待测试ip";
			
			sql = " SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '?%' ";

			array[5] = "1";
			array[6] = "1";
			
			
		} else if (type.equals("62")) {
			System.out.println("获取高匿可用ip段DI码");
			logger.info(" 获取高匿可用ip段DI码------------------------------------------------------------"); // info级别的信息

			sql = " SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '?%' ";
			
			
		} else if (type.equals("xx")) {
			System.out.println("获取高匿待测试ip段DI码");
		} else if (type.equals("xx")) {
			System.out.println("获取高匿待测试ip段DI码");
		} else if (type.equals("xx")) {
			System.out.println("获取高匿待测试ip段DI码");
		} else if (type.equals("xx")) {
			System.out.println("获取高匿待测试ip段DI码");
		} else if (type.equals("xx")) {
			System.out.println("获取高匿待测试ip段DI码");
		} else if (type.equals("xx")) {
			System.out.println("获取高匿待测试ip段DI码");
		} else if (type.equals("xx")) {
			System.out.println("获取高匿待测试ip段DI码");
		}

		//获取对应类型的DI码
		String ZDI = GetArrayListSql(sql, new Object[]{type}, "String").toString();
		int NewZDI = Integer.parseInt(ZDI)+1;
		//将下标0替换为新DI码
		array[0] = NewZDI;
		array[2] = GetNewDateTime(2);
		array[3] = GetNewDateTime(2);
		array[4] = md5.saltMD5(NewZDI+GetNewDateTime(2)+array[1].toString()+array[5].toString()+array[6].toString());
		
		
		int retnum = jdbcTemplate.update(savesql,array);
		if(retnum>0) {
			logger.info(" 新DI码插入成功------------------------------------------------------------"); // info级别的信息
			System.out.println("新DI码插入成功");
		}
		
		
		return NewZDI+"";
	}

	/**
	 * 静态代码块 (存入缓存当中)
	 */
	static {
		logger.info(" 静态代码块启动------------------------------------------------------------"); // info级别的信息
		// 初始化时间模式Map
		DTMap.put("DT1", DT1);
		DTMap.put("DT2", DT2);
		DTMap.put("DT3", DT3);
		DTMap.put("DT4", DT4);
	}

	// 数据库数据整理方法

	/**
	 * Sql查询方法
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
			System.out.println("执行的SQL为:" + sql);
			logger.info("执行的SQL为:" + sql); // info级别的信息
			list = jdbcTemplate.queryForList(sql);
		} else {
			System.out.println("执行的SQL为:" + sql);
			String sqlvalue = "执行的SQL为:" + sql + "; 值为:";
			for (Object value : Value) {
				sqlvalue += value + " , ";
			}
			logger.info(sqlvalue); // info级别的信息
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
			System.out.println(" Tool Sql查询方法 - 返回类型值有误 ");
			logger.info(" Tool Sql查询方法 - 返回类型值有误 "); // info级别的信息
			return "null";
		}
	}

	/**
	 * 新建时间格式化方法
	 * 
	 * @param type 对应类型
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
	 * @return String 时间
	 */
	public String GetNewDateTime(int type) {
		String DateTime = "";
		Date date = new Date();
		DateTime = DTMap.get("DT" + type).format(date);
		return DateTime;
	}

	/**
	 * 指定时间格式化方法
	 * 
	 * @param type 对应类型
	 * @param date 指定时间
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
	 * @return String 时间
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
	 * 简单爬取方法(只能用于简单爬取)
	 * 
	 * @param url
	 * @return 对应网址源码
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
