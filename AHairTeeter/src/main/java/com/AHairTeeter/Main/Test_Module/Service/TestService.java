package com.AHairTeeter.Main.Test_Module.Service;

/**
 * ��������Service
 * 
 * @author ����
 *
 */
public interface TestService {

	/**
	 * �������ݿ��������
	 */
	public int GetSqlMain();

	/**
	 * �������ݿ����ݻ�ȡ����
	 * 
	 * @return
	 */
	public Object GetintZDInum(String type , String retModel);
	
	
	/**
	 * IP��ȡ����Լ���ⷽ��
	 * 
	 * @return
	 */
	public int SetIPPool();
	
	

}
