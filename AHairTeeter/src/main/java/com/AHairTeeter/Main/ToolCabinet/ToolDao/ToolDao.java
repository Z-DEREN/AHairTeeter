package com.AHairTeeter.Main.ToolCabinet.ToolDao;

import java.util.List;

public interface ToolDao {

	/**
	 * ������������sql���
	 * @param list
	 * @return
	 */
	public int SaveListSql(List<String> list);
	
	/**
	 * ������������sql���
	 * @param sql ���
	 * @param value ֵ(��Ϊnull)
	 * @return
	 */
	public int SaveSingleSql(String sql , Object [] value );
	
	
	
	
	
	
	
	
	
}
