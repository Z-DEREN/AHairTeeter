package com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.AHairTeeter.Main.Test_Module.DaoImpl.TestDaoImpl;
import com.AHairTeeter.Main.ToolCabinet.ToolDao.ToolDao;

public class ToolDaoImpl implements ToolDao {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	private static final Logger logger = LogManager.getLogger(ToolDaoImpl.class.getName());

	/**
	 * 	批量执行sql语句
	 */
	@Override
	public int SaveListSql(List<String> list) {
		String[] strings = new String[list.size()];
		list.toArray(strings);
		int [] retu = jdbcTemplate.batchUpdate(strings);
		int num = 0;
		for(int ss : retu) {
			System.out.println("执行"+num+":"+list.get(num)+":"+ss);
			num++;
		}
		logger.info(" SaveListSql执行新增更新操作,执行结束"); // info级别的信息
		return 0;
	}
	
	/**
	 * 单条sql入库方法
	 */
	@Override
	public int SaveSingleSql(String sql , Object [] value ) {
		int retnum = jdbcTemplate.update(sql, value);
		if(retnum>0) {
			String text = sql+":";
			for(Object sss : value) {
				text+=sss+",";
			}
			logger.info(" SaveSingleSql执行新增更新操作,执行成功:"+text); // info级别的信息
			return retnum;
		}else {
			return -1;
		}
	}
	
	
}
