package com.AHairTeeter.Test_Module.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.AHairTeeter.Test_Module.Dao.TestDao;

/**
 * 基础测试Dao实现类
 * 
 * @author 好人
 *
 */

@Repository
public class TestDaoImpl implements TestDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	/**
	 * 测试数据库连接情况
	 */
	public int GetSqlMain() {
		String sql = "";
		int sqlretu = 0;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		sqlretu = jdbcTemplate.update(sql);
//				.queryForList(sql);
		return sqlretu;
	}

	
	
	
	
	
	
	
	
	
	
	
}
