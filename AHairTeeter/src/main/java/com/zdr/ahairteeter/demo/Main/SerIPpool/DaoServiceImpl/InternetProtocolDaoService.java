package com.zdr.ahairteeter.demo.Main.SerIPpool.DaoServiceImpl;

import java.util.Map;

public interface InternetProtocolDaoService {

	/**
	 * 测试ip可用性
	 * 
	 * @return
	 */
	public void UseTestIP();
	
	/**
	 * 删除不可用ip
	 * @return
	 */
	public Boolean DelTestIP(Map<String, Object> map);


}
