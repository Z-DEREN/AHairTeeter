package com.AHairTeeter.Main.GentlemanCangku;

import java.util.List;

public interface CangkuService {

	
	/**
	 * ��ȡ����ȫ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CangkuVo> selectpan();

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
	public List<CangkuVo> seleminid(String type_num) ;


	/**
	 * �߼�ɾ��,ָ��id��Ŀ
	 * 
	 * @param newid
	 * @return
	 * @throws Exception
	 */
	public boolean updateminid(String newid);

	
}
