package com.AHairTeeter.Main.Test_Module.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import com.AHairTeeter.Main.Test_Module.DaoImpl.TestDaoImpl;
import com.AHairTeeter.Main.Test_Module.Service.TestService;

/**
 * 基础测试Service实现类
 * @author 好人
 *
 */

@Repository
public class TestServiceImpl implements TestService {

	@Resource
	public TestDaoImpl TestDaoImpl;
	
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
