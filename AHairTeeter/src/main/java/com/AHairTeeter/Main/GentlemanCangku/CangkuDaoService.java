package com.AHairTeeter.Main.GentlemanCangku;

import java.util.List;
import java.util.Map;

import com.AHairTeeter.Main.Vo.CangkuVo;


public interface CangkuDaoService {
    /**
	 * ɾ��������
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean delelaji();

	/**
	 * �������ͻ�ȡ��С������
	 * 
	 * @param type_num
	 * @return
	 * @throws Exception
	 */
	public Map<String, String>  seleminid(String type_num) ;


	/**
	 * �߼�ɾ��,ָ��id��Ŀ
	 * 
	 * @param newid
	 * @return
	 * @throws Exception
	 */
	public boolean updateminid(String newid);
	
	
	/**
	 * ����
	 * @param cangku
	 * @return
	 */
	public boolean SaveChangku(CangkuVo cangku);

	/**
	 * ��ѯ����pan�Ƿ����
	 * @param pan
	 * @return
	 */
	public boolean SelChangkuPan(String pan);


}
