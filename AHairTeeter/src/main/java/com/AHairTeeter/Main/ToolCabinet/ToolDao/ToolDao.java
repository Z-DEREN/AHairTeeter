package com.AHairTeeter.Main.ToolCabinet.ToolDao;

import java.util.List;

public interface ToolDao {

	/**
	 * ������������sql���
	 * @param list
	 * @return
	 */
	public int ListSaveUpdeteSql(List<String> list);
	
	/**
	 * ������������sql���
	 * @param sql ���
	 * @param value ֵ(��Ϊnull)
	 * @return
	 */
	public int SingleSaveUpdeteSql(String sql , Object [] value );
	
	
	/**
	 * �������ݿ����ݻ�ȡ����
	 * @param sql
	 * @param Value
	 * @param type
	 * @return
	 */
	public Object  GetSelObjsql(String sql, Object[] Value, String type);
	
	
	
	
	
	
	
	
	
	
}
