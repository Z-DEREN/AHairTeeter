package com.AHairTeeter.Main.ToolCabinet.DaoServiceImpl;

import java.util.List;
import java.util.Map;

public interface InternetProtocolDaoService {
	/**
	 * 执行ip流程
	 * 
	 * @param type
	 */
	public void ToolIPSave(String type);

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

	/**
	 * 待测试ip组装入库
	 * 
	 * @param typenum 对应DI段
	 * @param ip      ip集合
	 * @return
	 */
	public int SaveIPlistnum(String typenum, Map<String, Object> ip);

	/**
	 * 待测试ip组装入库
	 * 
	 * @param list
	 */
	public int SaveIPList(Map<String, Object> map);

}
