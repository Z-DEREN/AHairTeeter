package com.AHairTeeter.Tool.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestList {

	/**
	 * 测试用遍历集合数�?
	 * 
	 * @param no1
	 * @param no2
	 * @return
	 */
	public List<Map<String, Object>> Testlist(int no1, int no2) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 200; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("testid", i);
			listmap.add(map);
		}
		return listmap;
	}

}
