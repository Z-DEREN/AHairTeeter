package com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolDao.ToolDao;
import com.AHairTeeter.Tool.MD5;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.IPpool.IPpool;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

@Service
public class ToolDaoImpl implements ToolDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LogManager.getLogger(ToolDaoImpl.class.getName());

	Tool Tool = new Tool();

	IPpool IPpool = new IPpool();

	/**
	 * 批量执行sql语句
	 */
	@Override
	public int ListSaveUpdeteSql(List<String> list) {
		if (list.size() > 0) {
			try {
				String[] strings = new String[list.size()];
				list.toArray(strings);
				int[] retu = jdbcTemplate.batchUpdate(strings);
				int num = 0;
				for (int ss : retu) {
					logger.info("执行" + num + ":" + list.get(num) + ":" + ss);
					num++;
				}
				logger.info(" SaveListSql执行新增更新操作,执行结束"); // info级别的信息
			} catch (Exception e) {
				logger.info("批量执行sql异常!!!!!!!!!!!!!!!!!!!!!!"); // info级别的信息
				logger.info(e); // info级别的信息
				return -1;
			}
			return 1;
		} else {
			logger.info("批量执行sql长度为0没有数据   !!!!!!!!!!!!!!!!!!!!!!"); // info级别的信息
			return 2;
		}
	}

	/**
	 * 单条sql入库方法
	 */
	@Override
	public Boolean SingleSaveUpdeteSql(String sql, Object[] value) {
		String text = sql + ":";
		int retnum = -1;
		if (value != null) {
			for (Object sss : value) {
				text += sss + ",";
			}
		}
		System.out.println("执行的sql为:" + text);

		retnum = jdbcTemplate.update(sql, value);
		if (retnum > 0) {
			logger.info("成功  执行单条sql新增更新操作,执行成功:" + text); // info级别的信息
			return true;
		} else {
			System.out.println(retnum);
			logger.info("失败  执行单条sql新增更新操作,执行失败:" + text); // info级别的信息
			return false;
		}

	}

	/**
	 * 测试数据库数据获取方法 String sql 查询sql Object[] Value 参数,可为null String type 返回值类型
	 * ListMap : List<Map<String, Object>> ArrayObject : List<Object> HashMap :
	 * Map<String, Object> ArrayList : Object[] String : 第一个值
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
			logger.info("执行的SQL为:" + sql); // info级别的信息
			list = jdbcTemplate.queryForList(sql);
		} else {
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
			logger.info(" Tool Sql查询方法 - 返回类型值有误 "); // info级别的信息
			return "null";
		}
	}

	/**
	 * 获取国内未测试高匿ip
	 * 
	 * @return
	 */
	@Override
	public List<Map<String, String>> Get61ChinaIP() {
		List<Map<String, String>> ChinaIPList = IPpool.Get61ChinaIPCryp(5);
		return ChinaIPList;
	}

	/**
	 * 测试ip可用性
	 * 
	 * @return
	 */
	@Override
	public void UseTestIP(List<Map<String, String>> ip, String typenum) {
		List<Map<String, String>> ChinaIPList = new ArrayList<Map<String, String>>();
		int frequency = (ip.size() / 5) + 1;
		String present = IPpool.Localip();// 获取本机ip
		int num = frequency;
		for (int i = 0; i < ip.size(); i++) {
			ChinaIPList.add(IPpool.GetPerfectCHIP(ip.get(i), present));
			if (i == num) {
				// 入库
				SaveIPlistnum(present, ChinaIPList);
				num += frequency;
				ChinaIPList.clear();
			}
		}
	}

	/**
	 * 获取ZDI码
	 * 
	 * @param type
	 * @param retModel
	 * @return
	 */
	public Object GetintZDInum(String type) {
		Tool Tool = new Tool();
		List<String> list = Tool.GetIPtypeDI(type);
		String ZDI = GetSelObjsql(list.get(0), null, "String").toString();
		// 获取对应类型的DI码
		int NewZDI = Integer.parseInt(ZDI) + 1;
		return NewZDI + "";
	}

	/**
	 * 根据ZDI码删除数据
	 * 
	 * @param type
	 * @param retModel
	 * @return
	 */
	public boolean DelZDInum(String type) {
		Object[] array = new Object[1];
		String savesql = "DELETE FROM tonuminvi WHERE ZDI = ? ;";
		List<String> list = Tool.GetIPtypeDI(type);
		array[0] = type;
		// 向总ZDI中插入新DI值
		Boolean retnum = SingleSaveUpdeteSql(savesql, array);
		return retnum;
	}

	/**
	 * 批量ip入库方法
	 * 
	 * @param typenum 对应DI段
	 * @param ip      ip集合
	 * @return
	 */
	@Override
	public int SaveIPlistnum(String typenum, List<Map<String, String>> ip) {
		for (int i = 0; i < ip.size(); i++) {
			if (ip.get(i) != null) {
				String sql = "INSERT INTO ippool (ZDI,IP,PORT,AREA,MSEC,UPDATETIME,TYPE) VALUES";
				sql += "(" + GetintZDInum(typenum) + ",'" + ip.get(i).get("ip") + "'," + ip.get(i).get("port") + ",'"
						+ ip.get(i).get("area") + "'," + ip.get(i).get("msec") + ",'" + Tool.GetNewDateTime(2) + "','"
						+ 1 + "')";
				Boolean retnum = SingleSaveUpdeteSql(sql, null);
			}
		}
		return 0;
	}

	/**
	 * 验证待测试ip表 SID唯一
	 * 
	 * @param table 表名
	 * @param ADI   字符串id
	 * @return
	 */
	@Override
	public boolean TableSID(String table, String SID) {
		String sql = "select COUNT(SID) FROM " + table + " where SID = '" + SID + "'";
		boolean TF = true;
		String TFnum = GetSelObjsql(sql, null, "String").toString();
		int num = Integer.parseInt(TFnum);
		if (num == 0) {
			TF = false;
		}
		return TF;
	}

	/**
	 * List<Map<String,String>>转为List<String> 入库sql
	 * 
	 * @param list
	 */
	@Override
	public List<String> SaveIPList(List<Map<String, String>> list) {
		List<String> sqllist = new ArrayList<String>();
		for (Map<String, String> map : list) {
			String sql = "INSERT INTO `lonewolfedgelaw`.`ippoolinspect` "
					+ "( `SID`, `ADI`, `IP`, `PORT`, `AREA`, `SAVETIME`, `TYPE` ) VALUES ( ";
			sql += "'" + GetSID("ippoolinspect") + "',";
			sql += "" + map.get("ADI") + ",";
			sql += "'" + map.get("ip") + "',";
			sql += "" + map.get("port") + ",";
			sql += "'" + map.get("area") + "',";
			sql += "'" + map.get("SAVETIME") + "',";
			sql += "'" + map.get("TYPE") + "')";
			sqllist.add(sql);
		}
		return sqllist;
	}

	/**
	 * 获取唯一ADI 字符串id
	 * 
	 * @param table
	 * @return
	 */
	public String GetSID(String table) {
		logger.info(" 获取最新字符串id------------------------------------------------------------"); // info级别的信息
		String SID = "";
		boolean tf = true;
		do {
			SID = RandomStringUtils.randomAlphanumeric(18);
			tf = TableSID(table, SID);
		} while (tf);
		return SID;
	}

	// 对单条数据进行入库操作

	public boolean SaveOneCrawlersql(Map<String, Object> map) {
		boolean TF = false;
		String ZDI = "";
		String SID = "";

		SID = GetSID("legal_information_heyhey");
		ZDI = GetintZDInum(map.get("ADI").toString()).toString();

		if (SID != null && !SID.equals("") && ZDI != null && !ZDI.equals("")) {
			logger.info(" 爬取数据准备入库------------------------------------------------------------"); // info级别的信息
			String TFSQL = " SELECT ZDI FROM legal_information_heyhey WHERE classify = '" + map.get("classify")
					+ "' AND uniqueid = '" + map.get("uniqueid") + "';";
			String sql = "insert into legal_information_heyhey (";
			String value = ") VALUES (";
			// 字符串id
			sql += "SID,";
			value += "'" + SID + "',";
			if (map.containsKey("ADI") && map.get("ADI") != null) {
				// 特殊DI头
				sql += "ADI,";
				value += "'" + map.get("ADI") + "',";
				// 特殊DI码
				sql += "ZDI,";
				value += "'" + GetintZDInum(map.get("ADI").toString()) + "',";
			}

			if (map.containsKey("type") && map.get("type") != null) {
				// 存储类型
				sql += "type,";
				value += "" + map.get("type") + ",";
			}

			if (map.containsKey("classify") && map.get("classify") != null) {
				// 存储标识,区分数据源头
				sql += "classify,";
				value += "'" + map.get("classify") + "',";
			}

			if (map.containsKey("title") && map.get("title") != null) {
				// 存储标题
				sql += "title,";
				value += "'" + map.get("title") + "',";
			}

			if (map.containsKey("line") && map.get("line") != null) {
				// 存储行数据
				sql += "line,";
				value += "'" + map.get("line") + "',";
			}

			if (map.containsKey("url") && map.get("url") != null) {
				// 链接
				sql += "url,";
				value += "'" + map.get("url") + "',";
			}

			if (map.containsKey("uniqueid") && map.get("uniqueid") != null) {
				// 存储数据自带id
				sql += "uniqueid,";
				value += "'" + map.get("uniqueid") + "',";
			}

			if (map.containsKey("text") && map.get("text") != null) {
				// 大容量主体数据存储体
				sql += "text,";
				value += "'" + map.get("text") + "',";
			}

			if (map.containsKey("recorddate") && map.get("recorddate") != null) {
				// 数据内时间
				sql += "recorddate,";
				value += "'" + map.get("recorddate") + "',";
			}

			// 备用SPARE1~SPARE6

			// 爬取时间
			sql += "acquiredate ";
			value += "'" + map.get("acquiredate") + "')";

			// 入库
			TF = SingleSaveUpdeteSql(sql + value, null);
			if (!TF) {
				logger.info(" 数据入库失败------------------------------------------------------------"); // info级别的信息
				DelZDInum(ZDI);
			}
		}
		return TF;
	}

	/**
	 * 对数据进行入库操作
	 * 
	 * @param listmap
	 */
	public void SaveCrawlersql(List<Map<String, Object>> listmap) {
		String ZDI = "";
		String SID = "";
		for (Map<String, Object> map : listmap) {

			SID = GetSID("legal_information_heyhey");
			ZDI = GetintZDInum(map.get("ADI").toString()).toString();
			if (SID != null && !SID.equals("") && ZDI != null && !ZDI.equals("")) {
				logger.info(" 爬取数据准备入库------------------------------------------------------------"); // info级别的信息
				String TFSQL = " SELECT ZDI FROM legal_information_heyhey WHERE classify = '" + map.get("classify")
						+ "' AND uniqueid = '" + map.get("uniqueid") + "';";
				String sql = "insert into legal_information_heyhey (";
				String value = ") VALUES (";
				// 字符串id
				sql += "SID,";
				value += "'" + SID + "',";
				if (map.containsKey("ADI") && map.get("ADI") != null) {
					// 特殊DI头
					sql += "ADI,";
					value += "'" + map.get("ADI") + "',";
					// 特殊DI码
					sql += "ZDI,";
					value += "'" + GetintZDInum(map.get("ADI").toString()) + "',";
				}

				if (map.containsKey("type") && map.get("type") != null) {
					// 存储类型
					sql += "type,";
					value += "" + map.get("type") + ",";
				}

				if (map.containsKey("classify") && map.get("classify") != null) {
					// 存储标识,区分数据源头
					sql += "classify,";
					value += "'" + map.get("classify") + "',";
				}

				if (map.containsKey("title") && map.get("title") != null) {
					// 存储标题
					sql += "title,";
					value += "'" + map.get("title") + "',";
				}

				if (map.containsKey("line") && map.get("line") != null) {
					// 存储行数据
					sql += "line,";
					value += "'" + map.get("line") + "',";
				}

				if (map.containsKey("url") && map.get("url") != null) {
					// 链接
					sql += "url,";
					value += "'" + map.get("url") + "',";
				}

				if (map.containsKey("uniqueid") && map.get("uniqueid") != null) {
					// 存储数据自带id
					sql += "uniqueid,";
					value += "'" + map.get("uniqueid") + "',";
				}

				if (map.containsKey("text") && map.get("text") != null) {
					// 大容量主体数据存储体
					sql += "text,";
					value += "'" + map.get("text") + "',";
				}

				if (map.containsKey("recorddate") && map.get("recorddate") != null) {
					// 数据内时间
					sql += "recorddate,";
					value += "'" + map.get("recorddate") + "',";
				}

				// 备用SPARE1~SPARE6

				// 爬取时间
				sql += "acquiredate ";
				value += "'" + map.get("acquiredate") + "')";

//				入库
				boolean TF = SingleSaveUpdeteSql(sql + value, null);

				if (!TF) {
					logger.info(" 数据入库失败------------------------------------------------------------"); // info级别的信息
					DelZDInum(ZDI);
				}
			}
		}

	}

}
