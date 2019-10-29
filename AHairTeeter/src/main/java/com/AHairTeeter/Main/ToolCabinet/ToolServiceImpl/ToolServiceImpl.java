package com.AHairTeeter.Main.ToolCabinet.ToolServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolService.ToolService;
import com.AHairTeeter.Tool.MD5;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo1;
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo2;
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo3;
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo4;
import com.AHairTeeter.Main.ThreadExecutionMethod.singleThread.threadNNo5;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.Moeimg;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.SssGif;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.TestList;
import com.AHairTeeter.Main.ThreadExecutionMethod.warehouse.Xvideos;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
@Service
public class ToolServiceImpl  {
	private static final Logger logger = LogManager.getLogger(ToolServiceImpl.class.getName());

	@Autowired
	public ToolDaoImpl ToolDaoImpl;
	
	Tool Tool = new Tool();


	/**
	 * 获取到总DI码
	 * @param type
	 * @param retModel
	 * @return
	 */
	public Object GetintZDInum(String type, String retModel) {
		Tool Tool = new Tool();
		MD5 md5 = new MD5();
		Object[] array = new Object[7];
		String sql = "";
		String savesql = "INSERT INTO tonuminvi (ZDI,ZNAME,DATETIME,UPDATETIME,MD5DI,TYPE,MODEL) VALUES (?,?,?,?,?,?,?)";
		List<String> list = Tool.GetIPtypeDI(type);
		array[1] = list.get(0);
		array[5] = list.get(1);
		array[6] = list.get(2);
		String ZDI = ToolDaoImpl.GetSelObjsql(sql, null, retModel).toString();
		// 获取对应类型的DI码
		int NewZDI = Integer.parseInt(ZDI) + 1;
		// 将下标0替换为新DI码
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(
				NewZDI + array[2].toString() + array[1].toString() + array[5].toString() + array[6].toString());

		// 向总ZDI中插入新DI值
		Boolean retnum =  ToolDaoImpl.SingleSaveUpdeteSql(savesql, array);
		return NewZDI + "";
	}

}
