package com.AHairTeeter.Test_Module.DaoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.AHairTeeter.Test_Module.Dao.TestDao;

/**
 * ��������Daoʵ����
 * 
 * @author ����
 *
 */

@Repository
public class TestDaoImpl implements TestDao {

	@Resource
	private JdbcTemplate jdbcTemplate;

	
	/**
	 * �������ݿ��������
	 */
	public int GetSqlMain() {
		String sql = " SELECT COUNT(ZDI) FROM userz";
		int sqlretu = 0;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list = jdbcTemplate.queryForList(sql);
		System.out.println(list.get(0));
		return sqlretu;
	}
	
	
	
}
