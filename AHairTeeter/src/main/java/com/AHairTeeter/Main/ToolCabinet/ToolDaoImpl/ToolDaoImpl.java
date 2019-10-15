package com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.AHairTeeter.Main.Test_Module.DaoImpl.TestDaoImpl;
import com.AHairTeeter.Main.ToolCabinet.ToolDao.ToolDao;

public class ToolDaoImpl implements ToolDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LogManager.getLogger(ToolDaoImpl.class.getName());

	/**
	 * 	����ִ��sql���
	 */
	@Override
	public int ListSaveUpdeteSql(List<String> list) {
		String[] strings = new String[list.size()];
		list.toArray(strings);
		int [] retu = jdbcTemplate.batchUpdate(strings);
		int num = 0;
		for(int ss : retu) {
			System.out.println("ִ��"+num+":"+list.get(num)+":"+ss);
			num++;
		}
		logger.info(" SaveListSqlִ���������²���,ִ�н���"); // info�������Ϣ
		return 0;
	}
	
	/**
	 * ����sql��ⷽ��
	 */
	@Override
	public int SingleSaveUpdeteSql(String sql , Object [] value ) {
		int retnum = jdbcTemplate.update(sql, value);
		if(retnum>0) {
			String text = sql+":";
			for(Object sss : value) {
				text+=sss+",";
			}
			logger.info(" SaveSingleSqlִ���������²���,ִ�гɹ�:"+text); // info�������Ϣ
			return retnum;
		}else {
			return -1;
		}
	}
	
	/**
	 * �������ݿ����ݻ�ȡ����
	 * String sql ��ѯsql
	 * Object[] Value ����,��Ϊnull
	 * String type ����ֵ����
	 * ListMap : List<Map<String, Object>>
	 * ArrayObject : List<Object>
	 * HashMap : Map<String, Object>
	 * ArrayList : Object[]
	 * String : ��һ��ֵ
	 */
	@Override
	public Object GetSelObjsql(String sql, Object[] Value, String type) {
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
	
	
}
