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
	public int SingleSaveUpdeteSql(String sql , Object [] value );
	
	/**
	 * 测试数据库数据获取方法
	 * @param sql
	 * @param Value
	 * @param type
	 * @return
	 */
	public Object  GetSelObjsql(String sql, Object[] Value, String type);
	
	/**
	 *获取国内未测试高匿ip
	 * @return
	 */
	public List<Map<String,String>> Get61ChinaIP();
	
	
	/**
	 *测试ip可用性
	 * @return
	 */
	public void UseTestIP(List<Map<String, String>> ip,String typenum);
	
	/**
	 * 批量ip入库方法
	 * @param typenum 对应DI段
	 * @param ip ip集合
	 * @return
	 */
	public int SaveIPlistnum(String typenum,List<Map<String, String>> ip);
	
	
	/**
	 * List<Map<String,String>>转为List<String> 入库sql
	 * @param list
	 */
	public List<String> SaveIPList(List<Map<String,String>> list);
	

	/**
	 * 验证待测试ip表 SID唯一
	 * @param table 表名
	 * @param ADI 字符串id
	 * @return
	 */
	public boolean TableSID(String table , String SID);

	
	
}
