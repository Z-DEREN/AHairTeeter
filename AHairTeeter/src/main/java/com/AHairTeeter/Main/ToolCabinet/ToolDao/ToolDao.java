package com.AHairTeeter.Main.ToolCabinet.ToolDao;

import java.util.List;
import java.util.Map;

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
	public Boolean SingleSaveUpdeteSql(String sql , Object [] value );
	
	/**
	 * �������ݿ����ݻ�ȡ����
	 * @param sql
	 * @param Value
	 * @param type
	 * @return
	 */
	public Object  GetSelObjsql(String sql, Object[] Value, String type);
	
	
	

	/**
	 * ��֤������ip�� SIDΨһ
	 * @param table ����
	 * @param ADI �ַ���id
	 * @return
	 */
	public boolean TableSID(String table , String SID);

	
	
}
