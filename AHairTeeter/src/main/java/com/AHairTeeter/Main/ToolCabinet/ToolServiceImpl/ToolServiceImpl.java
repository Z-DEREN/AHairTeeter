package com.AHairTeeter.Main.ToolCabinet.ToolServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolService.ToolService;
import com.AHairTeeter.Tool.MD5;
import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
@Service
public class ToolServiceImpl implements ToolService {

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
		// ��ȡ��Ӧ���͵�DI��
		int NewZDI = Integer.parseInt(ZDI) + 1;
		// ���±�0�滻Ϊ��DI��
		array[0] = NewZDI;
		array[2] = Tool.GetNewDateTime(2);
		array[3] = array[2];
		array[4] = md5.saltMD5(
				NewZDI + array[2].toString() + array[1].toString() + array[5].toString() + array[6].toString());

		// ����ZDI�в�����DIֵ
		int retnum = ToolDaoImpl.SingleSaveUpdeteSql(savesql, array);
		return NewZDI + "";
	}

	/**
	 * ִ��ip����
	 * 
	 * @param type
	 */
	@Override
	public void ToolIPSave(String type) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		switch (type) {
		case "61":
			list = ToolDaoImpl.Get61ChinaIP();
			ToolDaoImpl.UseTestIP(list, type);
			break;

		default:
			break;
		}
		ToolDaoImpl.Get61ChinaIP();

	}

}
