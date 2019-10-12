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
	 * 	����ִ��sql���
	 */
	@Override
	public int SaveListSql(List<String> list) {
		String[] strings = new String[list.size()];
		list.toArray(strings);
		int [] retu = jdbcTemplate.batchUpdate(strings);
		int num = 0;
		for(int ss : retu) {
			System.out.println("ִ��"+num+":"+list.get(num)+":"+ss);
			num++;
		}
		logger.info(" SaveListSqlִ���������²���,ִ�н���"); // info�������Ϣ
		return 0;
	}
	
	/**
	 * ����sql��ⷽ��
	 */
	@Override
	public int SaveSingleSql(String sql , Object [] value ) {
		int retnum = jdbcTemplate.update(sql, value);
		if(retnum>0) {
			String text = sql+":";
			for(Object sss : value) {
				text+=sss+",";
			}
			logger.info(" SaveSingleSqlִ���������²���,ִ�гɹ�:"+text); // info�������Ϣ
			return retnum;
		}else {
			return -1;
		}
	}
	
	
}
