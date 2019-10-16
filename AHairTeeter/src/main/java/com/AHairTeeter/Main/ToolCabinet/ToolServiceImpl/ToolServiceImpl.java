package com.AHairTeeter.Main.ToolCabinet.ToolServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolService.ToolService;
import com.AHairTeeter.Tool.MD5;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
@Service
public class ToolServiceImpl implements ToolService {

	@Autowired
	public ToolDaoImpl ToolDaoImpl;
	
	Tool Tool = new Tool();

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
		int retnum = ToolDaoImpl.SingleSaveUpdeteSql(savesql, array);
		return NewZDI + "";
	}

	/**
	 * 执行ip流程
	 * 
	 * @param type
	 */
	@Override
	public void ToolIPSave(String type) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		switch (type) {
		case "61":
			list = ToolDaoImpl.Get61ChinaIP();
			break;
		default:
			break;
		}
		
		//进行入库操作
		List<String> sqllist =  ToolDaoImpl.SaveIPList(list);
		ToolDaoImpl.ListSaveUpdeteSql(sqllist);
		System.out.println("入库结束");
		//对数据库内数据进行操作
		
//		List<Map<String,String>> MapList = new ArrayList<Map<String,String>>();
//		//测试ip
//		ToolDaoImpl.UseTestIP(MapList, type);
		
		
	}

}
