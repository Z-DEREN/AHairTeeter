package com.AHairTeeter.Main.Test_Module.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.AHairTeeter.Main.Test_Module.DaoImpl.TestDaoImpl;
import com.AHairTeeter.Main.Test_Module.Service.TestService;

/**
 * ��������Serviceʵ����
 * @author ����
 *
 */

@Repository
public class TestServiceImpl implements TestService {

	@Resource
	public TestDaoImpl TestDaoImpl;
	
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
