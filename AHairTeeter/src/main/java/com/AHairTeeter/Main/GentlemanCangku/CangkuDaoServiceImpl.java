package com.AHairTeeter.Main.GentlemanCangku;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;

@Service
public class CangkuDaoServiceImpl implements CangkuDaoService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private ToolDaoImpl ToolDaoImpl;
	Tool Tool = new Tool();

	private static final Logger logger = LogManager.getLogger(CangkuDaoServiceImpl.class.getName());

	/**
	 * 删除无用项
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean delelaji() {
		String sql = "delete from cangku_pan where pan not like '%pan%' or pan not like '%baidu%'";
		Boolean TF = ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
		return TF;
	}

	/**
	 * 根据类型获取数据
	 * 
	 * @param type
	 * @return
	 */
	public List<CangkuVo> getlistCangku(String type) {
		String sql = "SELECT pan,newid,TYPE,tiqu,mima,TIME FROM cangku_pan "
				+ "a WHERE newid=(SELECT MIN(newid) FROM cangku_pan WHERE state ='1' AND TYPE = '" + type + "'"
//						+ " and  mima = '' "
				+ ")";
		List<CangkuVo> list = jdbcTemplate.query(sql, new Object[] {}, new BeanPropertyRowMapper<>(CangkuVo.class));
		return list;
	}

	/**
	 * 根据类型获取数据,进行数据处理
	 * 
	 * @param type_num
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> seleminid(String type_num) {
		delelaji();// 删除无用项
		Map<String, String> map = new HashMap<String, String>();
		List<CangkuVo> list = getlistCangku(type_num);// 获取对应数据
		if (list.size() > 0) {
			logger.info(" 仓库" + " id:" + list.get(0).getNewid()); // info级别的信息
			System.out.println("id:" + list.get(0).getNewid());
			System.out.println("pan:" + list.get(0).getPan());
			System.out.println("提取码:" + list.get(0).getTiqu());
			System.out.println("密码:" + list.get(0).getMima());
			System.out.println("类型:" + list.get(0).getType());
			System.out.println("时间:" + list.get(0).getTime());
			map.put("num", Gettypenum(type_num));
			map.put("id", list.get(0).getNewid());
			map.put("pan", list.get(0).getPan());
			map.put("tiqu", list.get(0).getTiqu());
			map.put("mima", list.get(0).getMima() + "_");
			if (list.get(0).getMima().equals("") || list.get(0).getMima() != null) {
				map.put("newmima", "密码");
			}
			map.put("type", list.get(0).getType());
			map.put("time", list.get(0).getTime() + " ");
			updateminid(list.get(0).getNewid());// 逻辑删除
			return map;
		} else {
			return null;
		}
	}

	/**
	 * 获取所给类型的剩余条数
	 * 
	 * @param type_num
	 * @return
	 */
	public String Gettypenum(String type_num) {
		String sql = "SELECT COUNT(pan) FROM `cangku_pan` WHERE state = 1 and TYPE = '" + type_num + "'";
		String num = ToolDaoImpl.GetSelObjsql(sql, null, "String").toString();
		return num;
	}

	/**
	 * 逻辑删除,对目标进行标记
	 */
	public boolean updateminid(String num) {
		String sql = "update cangku_pan set state = '-1' where newid = '" + num + "'";
		Boolean TF = ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
		return TF;
	}

	/**
	 * 获取所有类型
	 */
	public List<Map<String, Object>> Alltype() {
		String sql = "SELECT COUNT(pan) AS pan,TYPE FROM cangku_pan WHERE state = 1  GROUP BY TYPE;";
		List<Map<String, Object>> reutrnlist = (List<Map<String, Object>>) ToolDaoImpl.GetSelObjsql(sql, null,
				"ListMap");
		return reutrnlist;
	}

}
