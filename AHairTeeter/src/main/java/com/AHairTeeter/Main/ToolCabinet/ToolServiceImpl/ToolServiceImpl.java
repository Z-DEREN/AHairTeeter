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
	 * IP��ȡ����Լ���ⷽ��
	 * @return
	 */
	@Override
	public int SaveIP() {
		IPpool IPpool = new IPpool();

		// ��ȡ���ڸ���ip(61)
		List<Map<String, String>> ChinaIPList = new ArrayList<Map<String, String>>();
		ChinaIPList = IPpool.GetChinaIPCryp();
		String present = IPpool.Localip();// ��ȡ����ip
		// ����ip,ÿ��һ��ͨ��ֱ�����,���ڸ���ip(61),��ͨ���޸�save_IP��ⷽ���绺�����޸�������
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
		//��ȡ��Ӧ���͵�DI��
		int NewZDI = Integer.parseInt(ZDI)+1;
		//���±�0�滻Ϊ��DI��
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(NewZDI+array[2].toString()+array[1].toString()+array[5].toString()+array[6].toString());
		
		//����ZDI�в�����DIֵ
		int retnum = ToolDaoImpl.SingleSaveUpdeteSql(savesql,array);
		return NewZDI+"";
	}
}
