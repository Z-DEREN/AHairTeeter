package com.AHairTeeter.Main.ToolCabinet.ToolServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.AHairTeeter.Main.Test_Module.ServiceImpl.TestServiceImpl;
import com.AHairTeeter.Main.ToolCabinet.ToolService.ToolService;
import com.AHairTeeter.Tool.MD5;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.IPpool.IPpool;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;

public class ToolServiceImpl implements ToolService{
	private static final Logger logger = LogManager.getLogger(ToolServiceImpl.class.getName());

	private ToolDaoImpl ToolDaoImpl;
	Tool Tool = new Tool();
	
	/**
	 * IP爬取与测试及入库方法
	 * @return
	 */
	@Override
	public int SaveIP() {
		IPpool IPpool = new IPpool();

		// 获取国内高匿ip(61)
		List<Map<String, String>> ChinaIPList = new ArrayList<Map<String, String>>();
		ChinaIPList = IPpool.GetChinaIPCryp();
		String present = IPpool.Localip();// 获取本机ip
		// 测试ip,每有一条通过直接入库,国内高匿ip(61),可通过修改save_IP入库方法如缓存库或修改入库语句
		Map<String, String> PerfectCHIP = new HashMap<String, String>();
		
		for(Map<String, String> keyval : ChinaIPList) {
			PerfectCHIP = IPpool.GetPerfectCHIP(keyval, present);
			if(PerfectCHIP!=null) {
				String sql = "INSERT INTO ippool (ZDI,IP,PORT,AREA,MSEC,UPDATETIME,TYPE) VALUES";
				sql += "(" + GetintZDInum("62","String") + ",'" + 
						PerfectCHIP.get("ip") + "'," + 
						PerfectCHIP.get("port") + ",'"+ 
						PerfectCHIP.get("area") + "'," + 
						PerfectCHIP.get("msec") + ",'" + 
				Tool.GetNewDateTime(2) + "','" + 1 + "')";
				int num = ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
			}
		}
		return 0;
	}
	
	public Object GetintZDInum(String type , String retModel) {
		Tool Tool =new Tool();
		MD5 md5 = new MD5();
		Object[] array = new Object[7];
		String sql = "";
		String savesql = "INSERT INTO tonuminvi (ZDI,ZNAME,DATETIME,UPDATETIME,MD5DI,TYPE,MODEL) VALUES (?,?,?,?,?,?,?)";
		List<String> list = Tool.GetIPtypeDI(type);
		array[1] =list.get(0);
		array[5] =list.get(1);
		array[6] =list.get(2);
		String ZDI =ToolDaoImpl.GetSelObjsql(sql, null, retModel).toString();
		//获取对应类型的DI码
		int NewZDI = Integer.parseInt(ZDI)+1;
		//将下标0替换为新DI码
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(NewZDI+array[2].toString()+array[1].toString()+array[5].toString()+array[6].toString());
		
		//向总ZDI中插入新DI值
		int retnum = ToolDaoImpl.SingleSaveUpdeteSql(savesql,array);
		return NewZDI+"";
	}
}
