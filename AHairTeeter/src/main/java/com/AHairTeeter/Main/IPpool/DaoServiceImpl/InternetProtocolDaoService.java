package com.AHairTeeter.Main.IPpool.DaoServiceImpl;

import java.util.List;
import java.util.Map;

public interface InternetProtocolDaoService {

	/**
	 * ����ip������
	 * 
	 * @return
	 */
	public void UseTestIP();
	
	/**
	 * ɾ��������ip
	 * @return
	 */
	public Boolean DelTestIP(Map<String, Object> map);


}
