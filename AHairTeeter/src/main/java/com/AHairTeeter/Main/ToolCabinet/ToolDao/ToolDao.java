package com.AHairTeeter.Main.ToolCabinet.ToolDao;

import java.util.List;

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
	public int SingleSaveUpdeteSql(String sql , Object [] value );
	
	
	/**
	 * 测试数据库数据获取方法
	 * @param sql
	 * @param Value
	 * @param type
	 * @return
	 */
	public Object  GetSelObjsql(String sql, Object[] Value, String type);
	
	
	
	
	
	
	
	
	
	
}
