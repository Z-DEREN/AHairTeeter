package com.AHairTeeter.Test_Module.ServiceImpl;

import org.springframework.stereotype.Repository;
import com.AHairTeeter.Test_Module.DaoImpl.TestDaoImpl;
import com.AHairTeeter.Test_Module.Service.TestService;

/**
 * ��������Serviceʵ����
 * @author ����
 *
 */

@Repository
public class TestServiceImpl implements TestService {

	
	public TestDaoImpl TestDaoImpl = new TestDaoImpl();
	
	/**
	 * �������ݿ��������
	 */
	@Override
	public int GetSqlMain() {
		int retnum = 0;
		retnum = TestDaoImpl.GetSqlMain();
		System.out.println("��ȡ���û���:"+retnum);
		return retnum;
	}

}
