package com.AHairTeeter.Main.IPpool.DaoServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Main.IPpool.IPpool;

@Service
public class InternetProtocolDaoServiceImpl implements InternetProtocolDaoService {

	private static final Logger logger = LogManager.getLogger(InternetProtocolDaoServiceImpl.class.getName());
	
	@Resource
	public ToolDaoImpl ToolDaoImpl;
	
	public Tool Tool = new Tool();

	/**
	 * ִ��ip����
	 * 
	 * @param type
	 */
	public void ToolIPSave(String type) {
		IPpool IPpool = new IPpool();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		switch (type) {
		case "61":
			IPpool.Get61ChinaIPCryp(3);
			break;
		default:
			break;
		}
		System.out.println("��ǰip��ȡ���");

		// ����ip
		UseTestIP();

	}

	/**
	 * ����ip������
	 * 
	 * @return
	 */
	@Override
	public void UseTestIP() {
		IPpool IPpool = new IPpool();
		List<Map<String, Object>> IPList = (List<Map<String, Object>>) ToolDaoImpl
				.GetSelObjsql(" SELECT * FROM ippoolinspect", null, "ListMap");
		logger.info("��ȡȫ������ip ��:"+IPList.size()+"��");
		String present = IPpool.Localip();// ��ȡ����ip
		for (int i = 0; i < IPList.size(); i++) {
			IPpool.GetPerfectCHIP(IPList.get(i), present);
		}
	}

	
	
	/**
	 * ip��ⷽ��
	 * 
	 * @param typenum ��ӦDI��
	 * @param ip      ip����
	 * @return
	 */
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
			logger.info(retnum+"	������ip��� :"+sql);
		}
		return 0;
	}

	/**
	 * ������ip��װ���
	 * 
	 * @param list
	 */
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
		Boolean retnum = ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
		logger.info(retnum+"	��������ip��װ��� :"+sql);
		return 0;
	}
	
	
	
	/**
	 * ɾ��������ip
	 * @return
	 */
	@Override
	public Boolean DelTestIP(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM ippoolinspect	WHERE ";
		sql += " IP ='" + map.get("IP") + "'";
		sql += " AND PORT = " + map.get("PORT") + "";
		sql += " AND AREA = '" + map.get("AREA")+ "' ;";
		return ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
	}

}
