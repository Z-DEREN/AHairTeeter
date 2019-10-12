package com.AHairTeeter.Main.Test_Module.ServiceImpl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.AHairTeeter.Main.Test_Module.DaoImpl.TestDaoImpl;
import com.AHairTeeter.Main.Test_Module.Service.TestService;
import com.AHairTeeter.Tool.MD5;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.IPpool.IPpool;

/**
 * ��������Serviceʵ����
 * @author ����
 *
 */

@Repository
public class TestServiceImpl implements TestService {

	@Resource
	public TestDaoImpl TestDaoImpl;
	
	Tool Tool = new Tool();
	private static final Logger logger = LogManager.getLogger(TestServiceImpl.class.getName());

	
	
	/**
	 * �������ݿ��������
	 */
	@Override
	public int GetSqlMain() {
		int retnum = 0;
		retnum = TestDaoImpl.GetSqlMain();
		System.out.println("��ȡ���û���:"+retnum);
		return retnum;
	}

	
	
	@Override
	public Object GetintZDInum(String type , String retModel) {
		Tool Tool =new Tool();
		MD5 md5 = new MD5();
		Object[] array = new Object[7];
		String sql = "";
		String savesql = "INSERT INTO tonuminvi (ZDI,ZNAME,DATETIME,UPDATETIME,MD5DI,TYPE,MODEL) VALUES (?,?,?,?,?,?,?)";

		if (type.equals("61")) {
			System.out.println("��ȡ���������ip��DI��");
			logger.info(" ��ȡ���������ip��DI��------------------------------------------------------------"); // info�������Ϣ
			sql = " SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '61%' ";
			array[1] = "���������ip";
			array[5] = "1";
			array[6] = "1";
		} else if (type.equals("62")) {
			System.out.println("��ȡ�������ip��DI��");
			logger.info(" ��ȡ�������ip��DI��------------------------------------------------------------"); // info�������Ϣ
			sql = " SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '62%' ";
			array[1] = "�������ip��";
			array[5] = "1";
			array[6] = "1";
		} else if (type.equals("xx")) {
			System.out.println("��ȡ���������ip��DI��");
		} 
		String ZDI =TestDaoImpl.GetintZDInum(sql, null, retModel).toString();
		//��ȡ��Ӧ���͵�DI��
		int NewZDI = Integer.parseInt(ZDI)+1;
		//���±�0�滻Ϊ��DI��
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(NewZDI+array[2].toString()+array[1].toString()+array[5].toString()+array[6].toString());
		
		//����ZDI�в�����DIֵ
		int retnum = TestDaoImpl.SaveZDIvalue(savesql,array);
		return NewZDI+"";
	}


	/**
	 * IP��ȡ����Լ���ⷽ��
	 * @return
	 */
	@Override
	public int SetIPPool() {
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
				int num = TestDaoImpl.SaveSingleSql(sql, null);
			}
		}
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
