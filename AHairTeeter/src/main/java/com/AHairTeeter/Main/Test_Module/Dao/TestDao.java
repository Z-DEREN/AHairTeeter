package com.AHairTeeter.Main.Test_Module.Dao;

import java.util.List;


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
	
	//����ִ��sql���
	public int SaveListSql(List<String> list);
	
	//����ִ��sql���
	public int SaveSingleSql(String sql , Object [] value );
	
	
	
}
