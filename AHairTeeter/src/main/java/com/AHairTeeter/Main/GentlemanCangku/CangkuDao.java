package com.AHairTeeter.Main.GentlemanCangku;

import java.util.List;


public interface  CangkuDao {
	

    // ��ȡ����pan
    public List<CangkuVo> selectpan();

    // ɾ��������
    public int delelaji() ;

    // �������ͻ�ȡ��С������
    public List<CangkuVo> seleminid(String type) ;


}
