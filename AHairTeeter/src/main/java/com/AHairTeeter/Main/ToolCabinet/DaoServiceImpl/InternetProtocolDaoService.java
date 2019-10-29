package com.AHairTeeter.Main.ToolCabinet.DaoServiceImpl;

import java.util.List;
import java.util.Map;

public interface InternetProtocolDaoService {
	/**
	 * ִ��ip����
	 * 
	 * @param type
	 */
	public void ToolIPSave(String type);

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

	/**
	 * ������ip��װ���
	 * 
	 * @param typenum ��ӦDI��
	 * @param ip      ip����
	 * @return
	 */
	public int SaveIPlistnum(String typenum, Map<String, Object> ip);

	/**
	 * ������ip��װ���
	 * 
	 * @param list
	 */
	public int SaveIPList(Map<String, Object> map);

}
