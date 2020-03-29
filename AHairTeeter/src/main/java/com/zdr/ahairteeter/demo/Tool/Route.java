package com.zdr.ahairteeter.demo.Tool;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 本地链接
 * 
 * @author 好人
 *
 */
@Component
public class Route {

	// 动态爬虫模块地址
	private static final String Address_dynamic_crawler_module;
	// 仓库name
	private static final String warehouse_cookie_name;
	// 仓库cookie
	private static final String warehouse_cookie_value;
	// 仓库页码
	private static final String warehouse_pagination;
	// 本地保存地址
	private static final String Local_storage_address;
	// 图片保存地址
	private static final String Picture_crawler;
	//邮箱账号
	private static final String mail_user;
	//邮箱密码
	private static final String mail_password;
	//邮箱密码
	private static final String IPAPI_KEY;
	// videosCookie
	private static final String Xvideos_Cookie_value;




	
	/**
	 * 静态代码块 (存入缓存当中)
	 */
	static {
		Tool Tool = new Tool();
		List<String> route = Tool.getDisplayColumns("route");
		Address_dynamic_crawler_module = route.get(0);
		warehouse_cookie_name = route.get(1);
		warehouse_cookie_value = route.get(2);
		Local_storage_address = route.get(3);
		Picture_crawler = route.get(4);
		mail_user = route.get(5);
		mail_password = route.get(6);
		IPAPI_KEY = route.get(7);
		Xvideos_Cookie_value = route.get(8);
		
		List<String> valueS = Tool.getDisplayColumns("valueS");
		warehouse_pagination = valueS.get(0);
		
		
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
	
	public  String getVideosCookie() {
		return Xvideos_Cookie_value;
	}
	
	public  String getWarehouse_pagination() {
		return warehouse_pagination;
	}
	

}