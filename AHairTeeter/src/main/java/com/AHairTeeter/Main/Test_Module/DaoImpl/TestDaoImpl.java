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
 * 基础测试Dao实现类
 * 
 * @author 好人
 *
 */

@Repository
public class TestDaoImpl implements TestDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LogManager.getLogger(TestDaoImpl.class.getName());

	/**
	 * 测试数据库连接情况
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
	 * 测试数据库数据获取方法
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
	 * 测试数据库数据获取方法
	 */
	@Override
	public int SaveZDIvalue(String sql, Object[] value) {
		int retnum = jdbcTemplate.update(sql, value);
		if(retnum>0) {
			logger.info(" 新DI码插入成功------------------------------------------------------------"); // info级别的信息
			System.out.println("新DI码插入成功");
			return retnum;
		}else {
			return -1;
		}
	}

	/**
	 * 	批量执行sql语句
	 */
	@Override
	public int SaveListSql(List<String> list) {
		String[] strings = new String[list.size()];
		list.toArray(strings);
		int [] retu = jdbcTemplate.batchUpdate(strings);
		return 0;
	}
	
	/**
	 * 单条sql入库方法
	 */
	@Override
	public int SaveOneSql(String sql , Object [] value ) {
		int retnum = jdbcTemplate.update(sql, value);
		if(retnum>0) {
			
			String text = sql+":";
			for(Object sss : value) {
				text+=sss+",";
			}
			logger.info(" 插入成功:"+text); // info级别的信息
			return retnum;
		}else {
			return -1;
		}
	}
	
	
	
	
	

}
