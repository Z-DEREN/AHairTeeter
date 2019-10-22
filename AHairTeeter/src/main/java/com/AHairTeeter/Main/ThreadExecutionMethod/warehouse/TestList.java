package com.AHairTeeter.Main.ThreadExecutionMethod.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class TestList  extends IoHandlerAdapter  {

	
    @Autowired
    protected ToolDaoImpl ToolDaoImpl;
    private static TestList TestList;
    @PostConstruct //ͨ��@PostConstructʵ�ֳ�ʼ��bean֮ǰ���еĲ���
    public void init() {  
    	TestList = this;  
    	TestList.ToolDaoImpl = this.ToolDaoImpl;        
        // ��ʹ��ʱ���Ѿ�̬����testServiceʵ����
    }
	
	
	/**
	 * �����ñ�����������
	 * 
	 * @param no1
	 * @param no2
	 * @return
	 */
	public List<Map<String, Object>> Testlist(int no1, int no2) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < no2; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(TestList.ToolDaoImpl.GetSID("legal_information_heyhey"));
			map.put("testid", i);
			listmap.add(map);
		}
		return listmap;
	}
	
}
