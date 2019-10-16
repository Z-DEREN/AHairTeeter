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

@Service
public class ToolDaoImpl implements ToolDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	private static final Logger logger = LogManager.getLogger(ToolDaoImpl.class.getName());

	Tool Tool = new Tool();
	IPpool IPpool = new IPpool();

	/**
	 * ����ִ��sql���
	 */
	@Override
	public int ListSaveUpdeteSql(List<String> list) {
		if ( list.size() > 0) {
			try {
				String[] strings = new String[list.size()];
				list.toArray(strings);
				int[] retu = jdbcTemplate.batchUpdate(strings);
				int num = 0;
				for (int ss : retu) {
					logger.info("ִ��" + num + ":" + list.get(num) + ":" + ss);
					num++;
				}
				logger.info(" SaveListSqlִ���������²���,ִ�н���"); // info�������Ϣ
			} catch (Exception e) {
				logger.info("����ִ��sql�쳣!!!!!!!!!!!!!!!!!!!!!!"); // info�������Ϣ
				logger.info(e); // info�������Ϣ
				return -1;
			}
			return 1;
		} else {
			logger.info("����ִ��sql����Ϊ0û������   !!!!!!!!!!!!!!!!!!!!!!"); // info�������Ϣ
			return 2;
		}
	}

	/**
	 * ����sql��ⷽ��
	 */
	@Override
	public int SingleSaveUpdeteSql(String sql, Object[] value) {
		int retnum = jdbcTemplate.update(sql, value);
		String text = sql + ":";
		for (Object sss : value) {
			text += sss + ",";
		}
		if (retnum > 0) {
			logger.info("�ɹ�  ִ�е���sql�������²���,ִ�гɹ�:" + text); // info�������Ϣ
			return retnum;
		} else {
			logger.info("ʧ��  ִ�е���sql�������²���,ִ��ʧ��:" + text); // info�������Ϣ
			return -1;
		}
	}

	/**
	 * �������ݿ����ݻ�ȡ���� String sql ��ѯsql Object[] Value ����,��Ϊnull String type ����ֵ����
	 * ListMap : List<Map<String, Object>> ArrayObject : List<Object> HashMap :
	 * Map<String, Object> ArrayList : Object[] String : ��һ��ֵ
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
			logger.info("ִ�е�SQLΪ:" + sql); // info�������Ϣ
			list = jdbcTemplate.queryForList(sql);
		} else {
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
			logger.info(" Tool Sql��ѯ���� - ��������ֵ���� "); // info�������Ϣ
			return "null";
		}
	}

	/**
	 * ��ȡ����δ���Ը���ip
	 * 
	 * @return
	 */
	@Override
	public List<Map<String, String>> Get61ChinaIP() {
		List<Map<String, String>> ChinaIPList = IPpool.Get61ChinaIPCryp(5);
		return ChinaIPList;
	}

	/**
	 * ����ip������
	 * 
	 * @return
	 */
	@Override
	public void UseTestIP(List<Map<String, String>> ip, String typenum) {
		List<Map<String, String>> ChinaIPList = new ArrayList<Map<String, String>>();
		int frequency = (ip.size() / 5) + 1;
		String present = IPpool.Localip();// ��ȡ����ip
		int num = frequency;
		for (int i = 0; i < ip.size(); i++) {
			ChinaIPList.add(IPpool.GetPerfectCHIP(ip.get(i), present));
			if (i == num) {
				// ���
				SaveIPlistnum(present, ChinaIPList);
				num += frequency;
				ChinaIPList.clear();
			}
		}
	}

	/**
	 * ��ȡZDI��
	 * @param type
	 * @param retModel
	 * @return
	 */
	public Object GetintZDInum(String type, String retModel) {
		Tool Tool = new Tool();
		MD5 md5 = new MD5();
		Object[] array = new Object[7];
		String sql = "";
		String savesql = "INSERT INTO tonuminvi (ZDI,ZNAME,DATETIME,UPDATETIME,MD5DI,TYPE,MODEL) VALUES (?,?,?,?,?,?,?)";
		List<String> list = Tool.GetIPtypeDI(type);
		array[1] = list.get(0);
		array[5] = list.get(1);
		array[6] = list.get(2);
		String ZDI = GetSelObjsql(sql, null, retModel).toString();
		// ��ȡ��Ӧ���͵�DI��
		int NewZDI = Integer.parseInt(ZDI) + 1;
		// ���±�0�滻Ϊ��DI��
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(
				NewZDI + array[2].toString() + array[1].toString() + array[5].toString() + array[6].toString());

		// ����ZDI�в�����DIֵ
		int retnum = SingleSaveUpdeteSql(savesql, array);
		return NewZDI + "";
	}

	/**
	 * ����ip��ⷽ��
	 * 
	 * @param typenum ��ӦDI��
	 * @param ip      ip����
	 * @return
	 */
	@Override
	public int SaveIPlistnum(String typenum, List<Map<String, String>> ip) {
		for (int i = 0; i < ip.size(); i++) {
			if (ip.get(i) != null) {
				String sql = "INSERT INTO ippool (ZDI,IP,PORT,AREA,MSEC,UPDATETIME,TYPE) VALUES";
				sql += "(" + GetintZDInum(typenum, "String") + ",'" + ip.get(i).get("ip") + "'," + ip.get(i).get("port")
						+ ",'" + ip.get(i).get("area") + "'," + ip.get(i).get("msec") + ",'" + Tool.GetNewDateTime(2)
						+ "','" + 1 + "')";
				int num = SingleSaveUpdeteSql(sql, null);
			}
		}
		return 0;
	}

	/**
	 * ��֤������ip�� SIDΨһ
	 * 
	 * @param table ����
	 * @param ADI   �ַ���id
	 * @return
	 */
	@Override
	public boolean TableSID(String table, String SID) {
		String sql = "select COUNT(ADI) FROM " + table + " where SID = '" + SID + "'";
		boolean TF = true;
		String TFnum = GetSelObjsql(sql, null, "String").toString();
		if (!TFnum.equals("1")) {
			TF = false;
		}
		return TF;
	}

	/**
	 * List<Map<String,String>>תΪList<String> ���sql
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
	 * ��ȡΨһADI �ַ���id
	 * 
	 * @param table
	 * @return
	 */
	public String GetSID(String table) {
		logger.info(" ��ȡ�����ַ���id------------------------------------------------------------"); // info�������Ϣ
		String SID = "";
		boolean tf = true;
		do {
			SID = RandomStringUtils.randomAlphanumeric(18);
			tf = TableSID(table, SID);
		} while (tf);
		return SID;
	}

}