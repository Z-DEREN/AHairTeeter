package com.AHairTeeter.Main.Test_Module.Service;

/**
 * 基础测试Service
 * 
 * @author 好人
 *
 */
public interface TestService {

	/**
	 * 测试数据库连接情况
	 */
	public int GetSqlMain();

	/**
	 * 测试数据库数据获取方法
	 * 
	 * @return
	 */
	public Object GetintZDInum(String type , String retModel);
	
	
	/**
	 * IP爬取与测试及入库方法
	 * 
	 * @return
	 */
	public int SetIPPool();
	
	

}
