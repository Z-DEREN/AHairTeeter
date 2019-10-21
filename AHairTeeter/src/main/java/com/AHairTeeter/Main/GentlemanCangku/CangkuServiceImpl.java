package com.AHairTeeter.Main.GentlemanCangku;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
@Service
public class CangkuServiceImpl implements CangkuService {
	

	@Resource
	private CangkuDao CangkuMapper;
	
	@Resource
	private ToolDaoImpl ToolDaoImpl;
	
	/**
	 * ��ȡ����ȫ����Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<CangkuVo> selectpan()  {
		// TODO Auto-generated method stub
		List<CangkuVo> list = new ArrayList<CangkuVo>();
		list = CangkuMapper.selectpan();
		return list;
	}

	/**
	 * ɾ��������
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean delelaji(){
		String sql ="delete from cangku_pan where pan not like '%pan%' or pan not like '%baidu%'";
		Boolean TF = ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
		return TF;
	}
	
	/**
	 * �������ͻ�ȡ��С������
	 * 
	 * @param type_num
	 * @return
	 * @throws Exception
	 */
	public List<CangkuVo> seleminid(String type_num) {
		return CangkuMapper.seleminid(type_num);
	}
	
	/**
	 * ��ȡ�������͵�ʣ������
	 * @param type_num
	 * @return
	 */
	public String Gettypenum(String type_num) {
		String sql = "SELECT COUNT(pan) FROM `cangku_pan` WHERE state = 1 and TYPE = '"+type_num+"'";
		String num = ToolDaoImpl.GetSelObjsql(sql, null, "String").toString();
		return num;
	}
	
	
	
	

	/**
	 * �߼�ɾ��,��Ŀ����б��
	 */
	public boolean updateminid(String num){
		String sql = "update cangku_pan set state = '-1' where newid = '"+num+"'";
		Boolean TF = ToolDaoImpl.SingleSaveUpdeteSql(sql, null);
		return TF ;
	}

	/**
	 * ��ȡ��������
	 */
	public List<Map<String, Object>> Alltype() {
		String sql = "SELECT COUNT(pan) AS pan,TYPE FROM cangku_pan WHERE state = 1  GROUP BY TYPE;";
		List<Map<String, Object>> reutrnlist = (List<Map<String, Object>>) ToolDaoImpl.GetSelObjsql(sql,null,"ListMap");
		return reutrnlist;
	}

	
}
