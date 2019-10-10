package com.AHairTeeter.Main.Test_Module.Dao;

import com.AHairTeeter.Main.Test_Module.pojo.ZDIvo;

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
	
}
