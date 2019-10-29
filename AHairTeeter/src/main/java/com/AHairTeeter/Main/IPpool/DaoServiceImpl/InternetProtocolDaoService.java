package com.AHairTeeter.Main.IPpool.DaoServiceImpl;

import java.util.List;
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
