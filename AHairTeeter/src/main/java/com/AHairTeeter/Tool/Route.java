package com.AHairTeeter.Tool;

import java.util.List;

/**
 * ��������
 * 
 * @author ����
 *
 */
public class Route {

	// ��̬����ģ���ַ
	private static final String Address_dynamic_crawler_module;
	// �ֿ�name
	private static final String warehouse_cookie_name;
	// �ֿ�cookie
	private static final String warehouse_cookie_value;
	// ���ر����ַ
	private static final String Local_storage_address;
	// ͼƬ�����ַ
	private static final String Picture_crawler;
	//�����˺�
	private static final String mail_user;
	//��������
	private static final String mail_password;
	//��������
	private static final String IPAPI_KEY;
	
	
	/**
	 * ��̬����� (���뻺�浱��)
	 */
	static {
		Tool Tool = new Tool();
		List<String> Element = Tool.getDisplayColumns("route");
		Address_dynamic_crawler_module = Element.get(0);
		warehouse_cookie_name = Element.get(1);
		warehouse_cookie_value = Element.get(2);
		Local_storage_address = Element.get(3);
		Picture_crawler = Element.get(4);
		mail_user = Element.get(5);
		mail_password = Element.get(6);
		IPAPI_KEY = Element.get(7);
		
//		for (String ss : Element) {
//			System.out.println(ss);
//		}
	}
	
	
	public  String getIPAPI_KEY() {
		return IPAPI_KEY;
	}

	public  String getAddressDynamicCrawlerModule() {
		return Address_dynamic_crawler_module;
	}

	public  String getWarehouseCookieName() {
		return warehouse_cookie_name;
	}

	public  String getWarehouseCookieValue() {
		return warehouse_cookie_value;
	}

	public  String getLocalStorageAddress() {
		return Local_storage_address;
	}

	public  String getPictureCrawler() {
		return Picture_crawler;
	}

	public  String getMailUser() {
		return mail_user;
	}

	public  String getMailPassword() {
		return mail_password;
	}

}