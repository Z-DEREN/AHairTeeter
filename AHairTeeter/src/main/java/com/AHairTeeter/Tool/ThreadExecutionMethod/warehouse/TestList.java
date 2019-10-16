package com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestList {

	/**
	 * 测试用遍历集合数字
	 * 
	 * @param no1
	 * @param no2
	 * @return
	 */
	public List<Map<String, Object>> Testlist(int no1, int no2) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < no2; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("testid", i);
			listmap.add(map);
		}
		return listmap;
	}

}
