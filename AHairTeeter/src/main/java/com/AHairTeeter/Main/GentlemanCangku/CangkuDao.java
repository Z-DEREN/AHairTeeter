package com.AHairTeeter.Main.GentlemanCangku;

import java.util.List;


public interface  CangkuDao {
	

    // 获取所有pan
    public List<CangkuVo> selectpan();

    // 删除无用项
    public int delelaji() ;

    // 根据类型获取最小的数据
    public List<CangkuVo> seleminid(String type) ;


}
