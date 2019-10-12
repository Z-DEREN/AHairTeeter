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
 * 基础测试Service实现类
 * @author 好人
 *
 */

@Repository
public class TestServiceImpl implements TestService {

	@Resource
	public TestDaoImpl TestDaoImpl;
	
	Tool Tool = new Tool();
	private static final Logger logger = LogManager.getLogger(TestServiceImpl.class.getName());

	
	
	/**
	 * 测试数据库连接情况
	 */
	@Override
	public int GetSqlMain() {
		int retnum = 0;
		retnum = TestDaoImpl.GetSqlMain();
		System.out.println("获取到用户数:"+retnum);
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
			System.out.println("获取高匿待测试ip段DI码");
			logger.info(" 获取高匿待测试ip段DI码------------------------------------------------------------"); // info级别的信息
			sql = " SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '61%' ";
			array[1] = "高匿待测试ip";
			array[5] = "1";
			array[6] = "1";
		} else if (type.equals("62")) {
			System.out.println("获取高匿可用ip段DI码");
			logger.info(" 获取高匿可用ip段DI码------------------------------------------------------------"); // info级别的信息
			sql = " SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '62%' ";
			array[1] = "高匿可用ip段";
			array[5] = "1";
			array[6] = "1";
		} else if (type.equals("xx")) {
			System.out.println("获取高匿待测试ip段DI码");
		} 
		String ZDI =TestDaoImpl.GetintZDInum(sql, null, retModel).toString();
		//获取对应类型的DI码
		int NewZDI = Integer.parseInt(ZDI)+1;
		//将下标0替换为新DI码
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(NewZDI+array[2].toString()+array[1].toString()+array[5].toString()+array[6].toString());
		
		//向总ZDI中插入新DI值
		int retnum = TestDaoImpl.SaveZDIvalue(savesql,array);
		return NewZDI+"";
	}


	/**
	 * IP爬取与测试及入库方法
	 * @return
	 */
	@Override
	public int SetIPPool() {
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
				int num = TestDaoImpl.SaveSingleSql(sql, null);
			}
		}
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
