package com.AHairTeeter.Main.Interceptor.SeekTheTruth;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

public class Usaeragent {

//	private static final Logger logger = LogManager.getLogger(usaeragent.class.getName());

	/**
	 * ����http��ȡuserAgent��Ϣ
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserAgent(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		return userAgent;
	}

	/**
	 * ����request��ȡuserAgent��Ȼ�������osVersion
	 * 
	 * @param request
	 * @return
	 */
	public static String getOsVersion(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getOsVersion(userAgent);
	}

	/**
	 * ����userAgent������osVersion
	 * 
	 * @param userAgent
	 * @return
	 */
	public static String getOsVersion(String userAgent) {
		String osVersion = "";
		if (StringUtils.isBlank(userAgent))
			return osVersion;
		String[] strArr = userAgent.substring(userAgent.indexOf("(") + 1, userAgent.indexOf(")")).split(";");
		if (null == strArr || strArr.length == 0)
			return osVersion;

		osVersion = strArr[1];
//		logger.info("osVersion is:{}", osVersion);
		return osVersion;
	}

	/**
	 * ��ȡ����ϵͳ����
	 * 
	 * @param request
	 * @return
	 */
	private static OperatingSystem getOperatingSystem(String userAgent) {
		UserAgent agent = UserAgent.parseUserAgentString(userAgent);
		OperatingSystem operatingSystem = agent.getOperatingSystem();
		return operatingSystem;
	}

	/**
	 * ��ȡos��Windows/ios/Android
	 * 
	 * @param request
	 * @return
	 */
	public static String getOs(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getOs(userAgent);
	}

	/**
	 * ��ȡos��Windows/ios/Android
	 * 
	 * @param request
	 * @return
	 */
	public static String getOs(String userAgent) {
		OperatingSystem operatingSystem = getOperatingSystem(userAgent);
		String os = operatingSystem.getGroup().getName();
//		logger.info("os is:{}", os);
		return os;
	}

	/**
	 * ��ȡdeviceType
	 * 
	 * @param request
	 * @return
	 */
	public static String getDevicetype(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getDevicetype(userAgent);
	}

	/**
	 * ��ȡdeviceType
	 * 
	 * @param request
	 * @return
	 */
	public static String getDevicetype(String userAgent) {
		OperatingSystem operatingSystem = getOperatingSystem(userAgent);
		String deviceType = operatingSystem.getDeviceType().toString();
//		logger.info("deviceType is:{}", deviceType);
		return deviceType;
	}

	/**
	 * ��ȡ����ϵͳ������
	 * 
	 * @param request
	 * @return
	 */
	public static String getOsName(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getOsName(userAgent);
	}

	/**
	 * ��ȡ����ϵͳ������
	 * 
	 * @param request
	 * @return
	 */
	public static String getOsName(String userAgent) {
		OperatingSystem operatingSystem = getOperatingSystem(userAgent);
		String osName = operatingSystem.getName();
//		logger.info("osName is:{}", osName);
		return osName;
	}

	/**
	 * ��ȡdevice����������
	 * 
	 * @param request
	 */
	public static String getDeviceManufacturer(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getDeviceManufacturer(userAgent);
	}

	/**
	 * ��ȡdevice����������
	 * 
	 * @param request
	 */
	public static String getDeviceManufacturer(String userAgent) {
		OperatingSystem operatingSystem = getOperatingSystem(userAgent);
		String deviceManufacturer = operatingSystem.getManufacturer().toString();
//		logger.info("deviceManufacturer is:{}", deviceManufacturer);
		return deviceManufacturer;
	}

	/**
	 * ��ȡ���������
	 * 
	 * @param request
	 * @return
	 */
	public static Browser getBrowser(String agent) {
		UserAgent userAgent = UserAgent.parseUserAgentString(agent);
		Browser browser = userAgent.getBrowser();
		return browser;
	}

	/**
	 * ��ȡbrowser name
	 * 
	 * @param request
	 * @return
	 */
	public static String getBorderName(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBorderName(userAgent);
	}

	/**
	 * ��ȡbrowser name
	 * 
	 * @param request
	 * @return
	 */
	public static String getBorderName(String userAgent) {
		Browser browser = getBrowser(userAgent);
		String borderName = browser.getName();
//		logger.info("borderName is:{}", borderName);
		return borderName;
	}

	/**
	 * ��ȡ�����������
	 * 
	 * @param request
	 * @return
	 */
	public static String getBorderType(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBorderType(userAgent);
	}

	/**
	 * ��ȡ�����������
	 * 
	 * @param request
	 * @return
	 */
	public static String getBorderType(String userAgent) {
		Browser browser = getBrowser(userAgent);
		String borderType = browser.getBrowserType().getName();
//		logger.info("borderType is:{}", borderType);
		return borderType;
	}

	/**
	 * ��ȡ������飺 CHROME��IE
	 * 
	 * @param request
	 * @return
	 */
	public static String getBorderGroup(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBorderGroup(userAgent);
	}

	/**
	 * ��ȡ������飺 CHROME��IE
	 * 
	 * @param request
	 * @return
	 */
	public static String getBorderGroup(String userAgent) {
		Browser browser = getBrowser(userAgent);
		String browerGroup = browser.getGroup().getName();
//		logger.info("browerGroup is:{}", browerGroup);
		return browerGroup;
	}

	/**
	 * ��ȡ���������������
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowserManufacturer(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBrowserManufacturer(userAgent);
	}

	/**
	 * ��ȡ���������������
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowserManufacturer(String userAgent) {
		Browser browser = getBrowser(userAgent);
		String browserManufacturer = browser.getManufacturer().getName();
//		logger.info("browserManufacturer is:{}", browserManufacturer);
		return browserManufacturer;
	}

	/**
	 * ��ȡ�����ʹ�õ���Ⱦ����
	 * 
	 * @param request
	 * @return
	 */
	public static String getBorderRenderingEngine(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBorderRenderingEngine(userAgent);
	}

	/**
	 * ��ȡ�����ʹ�õ���Ⱦ����
	 * 
	 * @param request
	 * @return
	 */
	public static String getBorderRenderingEngine(String userAgent) {
		Browser browser = getBrowser(userAgent);
		String renderingEngine = browser.getRenderingEngine().name();
//		logger.info("renderingEngine is:{}", renderingEngine);
		return renderingEngine;
	}

	/**
	 * ��ȡ������汾
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowserVersion(HttpServletRequest request) {
		String userAgent = getUserAgent(request);
		return getBrowserVersion(userAgent);
	}

	/**
	 * ��ȡ������汾
	 * 
	 * @param request
	 * @return
	 */
	public static String getBrowserVersion(String userAgent) {
		Browser browser = getBrowser(userAgent);
		String borderVersion = browser.getVersion(userAgent).toString();
		return borderVersion;
	}

	public static void main(String[] args) {
//		String androidUserAgent = "Mozilla/5.0 (Linux; Android 8.0; LON-AL00 Build/HUAWEILON-AL00; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/57.0.2987.132 MQQBrowser/6.2 TBS/044204 Mobile Safari/537.36 V1_AND_SQ_7.7.8_908_YYB_D QQ/7.7.8.3705 NetType/WIFI WebP/0.3.0 Pixel/1440";
//		String iosUserAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 12_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/16A366 QQ/7.7.8.421 V1_IPH_SQ_7.7.8_1_APP_A Pixel/750 Core/UIWebView Device/Apple(iPhone 6s) NetType/WIFI QBWebViewType/1";
//		String winUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win10; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";
//		String winUserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3730.400 QQBrowser/10.5.3805.400";
		String winUserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36";

		System.out.println("������飺" + getBorderGroup(winUserAgent));
		System.out.println("��������֣�" + getBorderName(winUserAgent));
		System.out.println("���������" + getBorderType(winUserAgent));
		System.out.println("����������̣�" + getBrowserManufacturer(winUserAgent));
		System.out.println("������汾��" + getBrowserVersion(winUserAgent));
		System.out.println("�豸��������:" + getDeviceManufacturer(winUserAgent));
		System.out.println("�豸����:" + getDevicetype(winUserAgent));
		System.out.println("�豸����ϵͳ��" + getOs(winUserAgent));
		System.out.println("����ϵͳ�����֣�" + getOsName(winUserAgent));
		System.out.println("����ϵͳ�İ汾�ţ�" + getOsVersion(winUserAgent));
		System.out.println("����ϵͳ���������Ⱦ����:" + getBorderRenderingEngine(winUserAgent));

	}

}