package com.AHairTeeter.Tool;

import java.util.List;

/**
 * 本地链接
 * 
 * @author 好人
 *
 */
public class Route {

	// 动态爬虫模块地址
	private static final String Address_dynamic_crawler_module;
	// 仓库name
	private static final String warehouse_cookie_name;
	// 仓库cookie
	private static final String warehouse_cookie_value;
	// 本地保存地址
	private static final String Local_storage_address;
	// 图片保存地址
	private static final String Picture_crawler;
	//邮箱账号
	private static final String mail_user;
	//邮箱密码
	private static final String mail_password;
	
	/**
	 * 静态代码块 (存入缓存当中)
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
		
//		for (String ss : Element) {
//			System.out.println(ss);
//		}
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