package com.AHairTeeter.Main.ToolCabinet.DaoServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.IPpool.IPpool;

public class InternetProtocolDaoServiceImpl implements InternetProtocolDaoService {

	@Autowired
	public ToolDaoImpl ToolDaoImpl;

	Tool Tool = new Tool();

	IPpool IPpool = new IPpool();

	/**
	 * 执行ip流程
	 * 
	 * @param type
	 */
	@Override
	public void ToolIPSave(String type) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		switch (type) {
		case "61":
			IPpool.Get61ChinaIPCryp(3);
			break;
		default:
			break;
		}
		System.out.println("当前ip获取完成");

		// 测试ip
		UseTestIP();

	}

	/**
	 * 测试ip可用性
	 * 
	 * @return
	 */
	@Override
	public void UseTestIP() {
		List<Map<String, Object>> IPList = (List<Map<String, Object>>) ToolDaoImpl
				.GetSelObjsql(" SELECT * FROM ippoolinspect", null, "ListMap");
		String present = IPpool.Localip();// 获取本机ip
		for (int i = 0; i < IPList.size(); i++) {
			IPpool.GetPerfectCHIP(IPList.get(i), present);
		}
	}

	
	
	
	
	/**
	 * ip入库方法
	 * 
	 * @param typenum 对应DI段
	 * @param ip      ip集合
	 * @return
	 */
	@Override
	public int SaveIPlistnum(String typenum, Map<String, Object> ip) {
		if (ip.get("IP") != null) {
			String sql = "INSERT INTO ippool (ZDI,IP,PORT,AREA,MSEC,UPDATETIME,TYPE) VALUES";
			sql += "(" + 
					ToolDaoImpl.GetintZDInum(ip.get("ADI").toString()) + ",'" + 
					ip.get("IP") + "'," + 
					ip.get("PORT")+ ",'" + 
					ip.get("AREA") + "'," + 
					typenum + ",'" + 
					Tool.GetNewDateTime(2)+ "','" + 
					1 + "')";
			Boolean retnum = ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
		}
		return 0;
	}

	/**
	 * 待测试ip组装入库
	 * 
	 * @param list
	 */
	@Override
	public int SaveIPList(Map<String, Object> map) {
		String sql = "INSERT INTO `lonewolfedgelaw`.`ippoolinspect` "
				+ "( `SID`, `ADI`, `IP`, `PORT`, `AREA`, `SAVETIME`, `TYPE` ) VALUES ( ";
		sql += "'" + ToolDaoImpl.GetSID("ippoolinspect") + "',";
		sql += "" + map.get("ADI") + ",";
		sql += "'" + map.get("ip") + "',";
		sql += "" + map.get("port") + ",";
		sql += "'" + map.get("area") + "',";
		sql += "'" + map.get("SAVETIME") + "',";
		sql += "'" + map.get("TYPE") + "')";
		ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
		return 0;
	}
	
	
	
	/**
	 * 删除不可用ip
	 * @return
	 */
	@Override
	public Boolean DelTestIP(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM ippoolinspect	WHERE ";
		sql += " IP ='" + map.get("IP") + "',";
		sql += " AND PORT = " + map.get("PORT") + ",";
		sql += " AND AREA = '" + map.get("AREA")+ "'";
		return ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
	}

}
