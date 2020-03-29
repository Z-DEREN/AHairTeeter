package com.zdr.ahairteeter.demo.Main.SerGentlemanCangku;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zdr.ahairteeter.demo.Main.SerToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.zdr.ahairteeter.demo.Main.Vo.CangkuVo;
import com.zdr.ahairteeter.demo.Tool.Crawler.pickpocket.Spiders;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CangkuDaoServiceImpl implements CangkuDaoService {

	@Resource
	private JdbcTemplate jdbcTemplate;
	@Resource
	private ToolDaoImpl ToolDaoImpl;
	@Autowired
	private Spiders spiders ;

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
	 *  and mima = 'cangku.moe'  
	 * 
	 * @param type
	 * @return
	 */
	public List<CangkuVo> getlistCangku(String type , String mima) {
		String sql = "SELECT pan,newid,TYPE,tiqu,mima,TIME FROM cangku_pan "
				+ "a WHERE newid=(SELECT MAX(newid) FROM cangku_pan WHERE state ='1' AND TYPE = '" + type + "'"
//						+ " and  mima = 'gmgard.com' "
				+ ")";
		if(mima != null) {
			 sql = "SELECT pan,newid,TYPE,tiqu,mima,TIME FROM cangku_pan "
						+ "a WHERE newid=(SELECT MAX(newid) FROM cangku_pan WHERE state ='1' AND TYPE = '" + type + "'"
								+ " and  mima = '"+mima+"' "
						+ ")";
		}
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
	public Map<String, String> seleminid(String type_num,String mima) {
		delelaji();// 删除无用项
		Map<String, String> map = new HashMap<String, String>();
		List<CangkuVo> list = getlistCangku(type_num , mima);// 获取对应数据
		if (list.size() > 0) {
			logger.info(" 仓库" + " id:" + list.get(0).getNewid()); // info级别的信息
			logger.info(" 仓库" + " pan:" + list.get(0).getPan()); // info级别的信息
			logger.info(" 仓库" + " 提取码:" + list.get(0).getTiqu()); // info级别的信息
			logger.info(" 仓库" + " 密码:" + list.get(0).getMima()); // info级别的信息
			logger.info(" 仓库" + " 类型:" + list.get(0).getType()); // info级别的信息
			logger.info(" 仓库" + " 时间:" + list.get(0).getTime()); // info级别的信息
			map.put("num", Gettypenum(type_num));
			map.put("id", list.get(0).getNewid());
			map.put("pan", list.get(0).getPan());
			map.put("tiqu", list.get(0).getTiqu());
			map.put("mima", list.get(0).getMima() + "_");
			if ("".equals(list.get(0).getMima()) || list.get(0).getMima() != null) {
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
	 *  and mima = ''  
	 *  HRLM
	 *  cangku.moe
	 */
	public List<Map<String, Object>> Alltype(String mima) {
		String sql = "SELECT COUNT(pan) AS pan,TYPE FROM cangku_pan WHERE state = 1  "
				+ "  GROUP BY TYPE;";
		if(mima != null) {
			 sql = "SELECT COUNT(pan) AS pan,TYPE FROM cangku_pan WHERE state = 1  "
					+ " and mima = '"+mima+"' "
					+ "  GROUP BY TYPE;";
		}
		List<Map<String, Object>> reutrnlist = (List<Map<String, Object>>) ToolDaoImpl.GetSelObjsql(sql, null,
				"ListMap");
		return reutrnlist;
	}
	
	/**
	 * 获取密码以及密码对应数量
	 *  and mima = ''  
	 *  HRLM
	 *  cangku.moe
	 */
	public List<Map<String, Object>> AllMima() {
		String sql = "SELECT DISTINCT mima , COUNT(mima) AS pan FROM   cangku_pan WHERE state = 1 GROUP BY mima";
		List<Map<String, Object>> reutrnlist = (List<Map<String, Object>>) ToolDaoImpl.GetSelObjsql(sql, null,"ListMap");
		return reutrnlist;
	}
	
	/**
	 * 增加
	 * @param cangku
	 * @return
	 */
	@Override
	public boolean SaveChangku(CangkuVo cangku) {
		String sql = "insert into Cangku_pan(" + "pan," + "newid," + "type," + "panName," + "tiqu," + "mima,"
				+ "time) values(?,?,?,?,?,?,?)";
		Object[] param = {
				cangku.getPan(), cangku.getNewid(), cangku.getType(), cangku.getPanName(), cangku.getTiqu(),
				cangku.getMima(), cangku.getTime()
		};
		boolean TF = ToolDaoImpl.SingleSaveUpdeteSql(sql, param);
		return TF;
	}
	
	
	/**
	 * 查询主键pan是否存在
	 * @param pan
	 * @return
	 */
	@Override
	public boolean SelChangkuPan(String pan) {
		String sql = "select newid from cangku_pan where pan = ?";
		Object[] param = { pan };
		boolean TF = ToolDaoImpl.SingleSaveUpdeteSql(sql, param);
		return TF;
	}
	
	
	

	
	/**
	 * 排除失效链接
	 */
	public void excludelose() {
		String sqlsise = "SELECT COUNT(pan) FROM cangku_pan WHERE  takestock = 0 AND state = 1";
		String sise = ToolDaoImpl.GetSelObjsql(sqlsise, null,"String").toString();
		String sql = "";
		if(sise.equals("0")) {
			//已经盘点过一次了
			sql = "update cangku_pan set takestock = 0 where state = 1";
			ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
		}
		sql = "SELECT pan , newid  FROM cangku_pan WHERE state = 1 and takestock = 0";
		
		List<Map<String, Object>> reutrnlist = (List<Map<String, Object>>) ToolDaoImpl.GetSelObjsql(sql, null,"ListMap");
		
		boolean tf = false;
		for(Map<String, Object> map : reutrnlist) {
			logger.info("盘点仓库中 .. .. .."); // info级别的信息
			tf = false;
			String newnum = spiders.spiders((map.get("pan").toString()), 99999);
			if (newnum.indexOf("此链接分享内容可能因为涉及侵权") > 0) {
				logger.info(map.get("newid")+":该链接被查封"); // info级别的信息
				tf = true;
			}else if (newnum.indexOf("你来晚了") > 0) {
				logger.info(map.get("newid")+":该链接失效"); // info级别的信息
				tf = true;
			}else if(newnum.equals("")) {
				logger.info("网络连接有误,检查网络"); // info级别的信息
			}else {
				logger.info("链接良好"); // info级别的信息
			}
			if(tf) {
				String updatesql = "update cangku_pan set state = '-2' where newid = '" + map.get("newid") + "'";
				Boolean TF = ToolDaoImpl.SingleSaveUpdeteSql(updatesql, null);
			}else {
				String updatesql = "update cangku_pan set  takestock = 1 where newid = '" + map.get("newid") + "'";
				Boolean TF = ToolDaoImpl.SingleSaveUpdeteSql(updatesql, null);
			}
			try {
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	

}
