package com.AHairTeeter.Main.ToolCabinet.ToolDao;

import java.util.List;
import java.util.Map;

public interface ToolDao {

	/**
	 * 批量新增更新sql语句
	 * @param list
	 * @return
	 */
	public int ListSaveUpdeteSql(List<String> list);
	
	/**
	 * 单条新增更新sql语句
	 * @param sql 语句
	 * @param value 值(可为null)
	 * @return
	 */
	public Boolean SingleSaveUpdeteSql(String sql , Object [] value );
	
	/**
	 * 测试数据库数据获取方法
	 * @param sql
	 * @param Value
	 * @param type
	 * @return
	 */
	public Object  GetSelObjsql(String sql, Object[] Value, String type);
	
	
	

	/**
	 * 验证待测试ip表 SID唯一
	 * @param table 表名
	 * @param ADI 字符串id
	 * @return
	 */
	public boolean TableSID(String table , String SID);

	
	
}
