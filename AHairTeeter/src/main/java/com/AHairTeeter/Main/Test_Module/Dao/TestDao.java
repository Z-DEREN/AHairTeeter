package com.AHairTeeter.Main.Test_Module.Dao;

import java.util.List;


/**
 * 基础测试Dao
 * @author 好人
 *
 */
public interface TestDao {

	
	
	//测试数据库连接情况
	public int GetSqlMain();
	
	//测试数据库数据获取方法
	public Object GetintZDInum(String sql, Object[] Value, String type);
	
	//测试数据库数据获取方法
	public int SaveZDIvalue(String sql,Object[] value);
	
	//批量执行sql语句
	public int SaveListSql(List<String> list);
	
	//单条执行sql语句
	public int SaveSingleSql(String sql , Object [] value );
	
	
	
}
