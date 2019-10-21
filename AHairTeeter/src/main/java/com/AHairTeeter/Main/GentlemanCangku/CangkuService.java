package com.AHairTeeter.Main.GentlemanCangku;

import java.util.List;

public interface CangkuService {

	
	/**
	 * 获取表内全部信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CangkuVo> selectpan();

	/**
	 * 删除无用项
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean delelaji();

	/**
	 * 根据类型获取最小的数据
	 * 
	 * @param type_num
	 * @return
	 * @throws Exception
	 */
	public List<CangkuVo> seleminid(String type_num) ;


	/**
	 * 逻辑删除,指定id条目
	 * 
	 * @param newid
	 * @return
	 * @throws Exception
	 */
	public boolean updateminid(String newid);

	
}
