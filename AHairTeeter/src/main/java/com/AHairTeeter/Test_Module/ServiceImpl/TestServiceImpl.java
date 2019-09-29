package com.AHairTeeter.Test_Module.ServiceImpl;

import org.springframework.stereotype.Repository;
import com.AHairTeeter.Test_Module.DaoImpl.TestDaoImpl;
import com.AHairTeeter.Test_Module.Service.TestService;

/**
 * 基础测试Service实现类
 * @author 好人
 *
 */

@Repository
public class TestServiceImpl implements TestService {

	
	public TestDaoImpl TestDaoImpl = new TestDaoImpl();
	
	/**
	 * 测试数据库连接情况
	 */
	@Override
	public int GetSqlMain() {
		int retnum = 0;
		retnum = TestDaoImpl.GetSqlMain();
		System.out.println("获取到用户数:"+retnum);
		return retnum;
	}

}
