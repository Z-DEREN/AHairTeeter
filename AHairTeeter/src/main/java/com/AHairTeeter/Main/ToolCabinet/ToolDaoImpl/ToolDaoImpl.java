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
import com.AHairTeeter.Tool.MD5;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.IPpool.IPpool;

public class ToolDaoImpl implements ToolDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LogManager.getLogger(ToolDaoImpl.class.getName());

	
	Tool Tool = new Tool();
	IPpool IPpool = new IPpool();
	/**
	 * 	批量执行sql语句
	 */
	@Override
	public int ListSaveUpdeteSql(List<String> list) {
		String[] strings = new String[list.size()];
		list.toArray(strings);
		int [] retu = jdbcTemplate.batchUpdate(strings);
		int num = 0;
		for(int ss : retu) {
			System.out.println("执行"+num+":"+list.get(num)+":"+ss);
			num++;
		}
		logger.info(" SaveListSql执行新增更新操作,执行结束"); // info级别的信息
		return 0;
	}
	
	/**
	 * 单条sql入库方法
	 */
	@Override
	public int SingleSaveUpdeteSql(String sql , Object [] value ) {
		int retnum = jdbcTemplate.update(sql, value);
		String text = sql+":";
		for(Object sss : value) {
			text+=sss+",";
		}
		if(retnum>0) {
			logger.info("成功  执行单条sql新增更新操作,执行成功:"+text); // info级别的信息
			return retnum;
		}else {
			logger.info("失败  执行单条sql新增更新操作,执行失败:"+text); // info级别的信息
			return -1;
		}
	}
	
	/**
	 * 测试数据库数据获取方法
	 * String sql 查询sql
	 * Object[] Value 参数,可为null
	 * String type 返回值类型
	 * ListMap : List<Map<String, Object>>
	 * ArrayObject : List<Object>
	 * HashMap : Map<String, Object>
	 * ArrayList : Object[]
	 * String : 第一个值
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
	 * 获取国内未测试高匿ip
	 * @return
	 */
	@Override
	public List<Map<String, String>> Get61ChinaIP() {
		List<Map<String, String>> ChinaIPList= IPpool.Get61ChinaIPCryp(5);
		return ChinaIPList;
	}


	/**
	 *测试ip可用性
	 * @return
	 */
	@Override
	public void UseTestIP(List<Map<String, String>> ip,String typenum) {
		List<Map<String, String>> ChinaIPList = new ArrayList<Map<String, String>>();
		int frequency = (ip.size() / 5) + 1;
		String present = IPpool.Localip();// 获取本机ip
		int num = frequency;
		for(int i = 0 ; i < ip.size() ; i++) {
			ChinaIPList.add(IPpool.GetPerfectCHIP(ip.get(i), present));
			if(i == num) {
				//入库
				SaveIPlistnum(present,ChinaIPList);
				num+=frequency;
				ChinaIPList.clear();
			}
		}
	}
	
	
	public Object GetintZDInum(String type , String retModel) {
		Tool Tool =new Tool();
		MD5 md5 = new MD5();
		Object[] array = new Object[7];
		String sql = "";
		String savesql = "INSERT INTO tonuminvi (ZDI,ZNAME,DATETIME,UPDATETIME,MD5DI,TYPE,MODEL) VALUES (?,?,?,?,?,?,?)";
		List<String> list = Tool.GetIPtypeDI(type);
		array[1] =list.get(0);
		array[5] =list.get(1);
		array[6] =list.get(2);
		String ZDI =GetSelObjsql(sql, null, retModel).toString();
		//获取对应类型的DI码
		int NewZDI = Integer.parseInt(ZDI)+1;
		//将下标0替换为新DI码
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(NewZDI+array[2].toString()+array[1].toString()+array[5].toString()+array[6].toString());
		
		//向总ZDI中插入新DI值
		int retnum = SingleSaveUpdeteSql(savesql,array);
		return NewZDI+"";
	}
	
	
	/**
	 * 批量ip入库方法
	 * @param typenum 对应DI段
	 * @param ip ip集合
	 * @return
	 */
	@Override
	public int SaveIPlistnum(String typenum, List<Map<String, String>> ip) {
		for(int i = 0 ; i < ip.size() ; i++) {
			if (ip.get(i) != null) {
				String sql = "INSERT INTO ippool (ZDI,IP,PORT,AREA,MSEC,UPDATETIME,TYPE) VALUES";
				sql += "(" + GetintZDInum(typenum,"String") + ",'" + 
						ip.get(i).get("ip") + "'," + 
						ip.get(i).get("port") + ",'"+ 
						ip.get(i).get("area") + "'," + 
						ip.get(i).get("msec") + ",'" + 
						Tool.GetNewDateTime(2) + "','" + 
						1 + "')";
				int num = SingleSaveUpdeteSql(sql, null);
			}
		}
		return 0;
	}
}
