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
	public int SingleSaveUpdeteSql(String sql , Object [] value );
	
	/**
	 * �������ݿ����ݻ�ȡ����
	 * @param sql
	 * @param Value
	 * @param type
	 * @return
	 */
	public Object  GetSelObjsql(String sql, Object[] Value, String type);
	
	/**
	 *��ȡ����δ���Ը���ip
	 * @return
	 */
	public List<Map<String,String>> Get61ChinaIP();
	
	
	/**
	 *����ip������
	 * @return
	 */
	public void UseTestIP(List<Map<String, String>> ip,String typenum);
	
	/**
	 * ����ip��ⷽ��
	 * @param typenum ��ӦDI��
	 * @param ip ip����
	 * @return
	 */
	public int SaveIPlistnum(String typenum,List<Map<String, String>> ip);
	
	
	/**
	 * List<Map<String,String>>תΪList<String> ���sql
	 * @param list
	 */
	public List<String> SaveIPList(List<Map<String,String>> list);
	

	/**
	 * ��֤������ip�� SIDΨһ
	 * @param table ����
	 * @param ADI �ַ���id
	 * @return
	 */
	public boolean TableSID(String table , String SID);

	
	
}
