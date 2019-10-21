package com.AHairTeeter.Main.GentlemanCangku;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;

@Service
public class CangkuDaoImpl implements CangkuDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	


	private static final Logger logger = LogManager.getLogger(CangkuDaoImpl.class.getName());

	Tool Tool = new Tool();

	/**
	 * 获取一部分类型
	 */
	@Override
	public List<CangkuVo> selectpan(){
		// TODO Auto-generated method stub
		String sql = "select pan,newid,type,panName,tiqu,mima,timefrom cangku_panlimit 0,10";

		return null;
	}

	@Override
	public int delelaji(){
		// TODO Auto-generated method stub
		
		
		
		return 0;
	}

	/**
	 * \ 根据类型获取最小的数据
	 */
	@Override
	public List<CangkuVo> seleminid(String type){
		String sql = "SELECT pan,newid,TYPE,tiqu,mima,TIME FROM cangku_pan "
				+ "a WHERE newid=(SELECT MIN(newid) FROM cangku_pan WHERE state ='1' AND TYPE = '" + type + "'"
//						+ " and  mima = '' "
						+ ")";
		List<CangkuVo> users = jdbcTemplate.query(sql, new Object[] {}, new BeanPropertyRowMapper<>(CangkuVo.class));
		return users;
	}

	

}
