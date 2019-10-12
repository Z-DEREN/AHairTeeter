package com.AHairTeeter.Main.ToolCabinet.ToolDao;

import java.util.List;

public interface ToolDao {

	/**
	 * 批量新增更新sql语句
	 * @param list
	 * @return
	 */
	public int SaveListSql(List<String> list);
	
	/**
	 * 单条新增更新sql语句
	 * @param sql 语句
	 * @param value 值(可为null)
	 * @return
	 */
	public int SaveSingleSql(String sql , Object [] value );
	
	
	
	
	
	
	
	
	
}
