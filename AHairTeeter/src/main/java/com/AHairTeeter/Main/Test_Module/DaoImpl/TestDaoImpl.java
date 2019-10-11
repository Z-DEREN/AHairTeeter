package com.AHairTeeter.Main.Test_Module.DaoImpl;

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
import org.springframework.stereotype.Repository;
import com.AHairTeeter.Main.Test_Module.Dao.TestDao;
import com.AHairTeeter.Main.Test_Module.pojo.ZDIvo;
import com.AHairTeeter.Tool.Tool;

/**
 * ��������Daoʵ����
 * 
 * @author ����
 *
 */

@Repository
public class TestDaoImpl implements TestDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LogManager.getLogger(TestDaoImpl.class.getName());

	/**
	 * �������ݿ��������
	 */
	public int GetSqlMain() {
//		String sql = " SELECT COUNT(ZDI) as ZDI FROM userz";
		String sql = "SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '61%'";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = jdbcTemplate.queryForList(sql);
		int num = Integer.parseInt(list.get(0).get("ZDI").toString());
		return num;
	}

	/**
	 * �������ݿ����ݻ�ȡ����
	 */
	@Override
	public Object GetintZDInum(String sql, Object[] Value, String type) {
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
	 * �������ݿ����ݻ�ȡ����
	 */
	@Override
	public int SaveZDIvalue(String sql, Object[] value) {
		int retnum = jdbcTemplate.update(sql, value);
		if(retnum>0) {
			logger.info(" ��DI�����ɹ�------------------------------------------------------------"); // info�������Ϣ
			System.out.println("��DI�����ɹ�");
			return retnum;
		}else {
			return -1;
		}
	}

	/**
	 * 	����ִ��sql���
	 */
	@Override
	public int SaveListSql(List<String> list) {
		String[] strings = new String[list.size()];
		list.toArray(strings);
		int [] retu = jdbcTemplate.batchUpdate(strings);
		return 0;
	}
	
	/**
	 * ����sql��ⷽ��
	 */
	@Override
	public int SaveOneSql(String sql , Object [] value ) {
		int retnum = jdbcTemplate.update(sql, value);
		if(retnum>0) {
			
			String text = sql+":";
			for(Object sss : value) {
				text+=sss+",";
			}
			logger.info(" ����ɹ�:"+text); // info�������Ϣ
			return retnum;
		}else {
			return -1;
		}
	}
	
	
	
	
	

}
