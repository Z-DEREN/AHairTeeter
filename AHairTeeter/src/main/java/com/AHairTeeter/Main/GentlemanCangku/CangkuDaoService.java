package com.AHairTeeter.Main.GentlemanCangku;

import java.util.List;
import java.util.Map;


public interface CangkuDaoService {
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
	public Map<String, String>  seleminid(String type_num) ;


	/**
	 * 逻辑删除,指定id条目
	 * 
	 * @param newid
	 * @return
	 * @throws Exception
	 */
	public boolean updateminid(String newid);


}
