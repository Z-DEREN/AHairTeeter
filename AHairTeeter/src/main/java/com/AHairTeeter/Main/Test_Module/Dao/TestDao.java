package com.AHairTeeter.Main.Test_Module.Dao;

import com.AHairTeeter.Main.Test_Module.pojo.ZDIvo;

/**
 * ��������Dao
 * @author ����
 *
 */
public interface TestDao {

	
	
	//�������ݿ��������
	public int GetSqlMain();
	
	//�������ݿ����ݻ�ȡ����
	public Object GetintZDInum(String sql, Object[] Value, String type);
	
	//�������ݿ����ݻ�ȡ����
	public int SaveZDIvalue(String sql,Object[] value);
	
}
