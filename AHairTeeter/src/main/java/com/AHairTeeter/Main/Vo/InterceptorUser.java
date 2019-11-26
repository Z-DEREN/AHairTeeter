package com.AHairTeeter.Main.Vo;

import com.fasterxml.jackson.core.sym.Name;

public class InterceptorUser {

	// 访问者UserAgent
	public String UserAgent;

	//访问者登录名称
	public String username;
	
	// 访问者ip
	public String vsIP;

	// 访问者端口
	public String vsPor;

	// 访问者浏览器名字
	public String vsBroNamer;

	// 访问者操作系统名
	public String vsOS;

	// 访问者操作系统版本
	public String vsOSName;

	// 本站数据
	public String IPdata_no1;
	
	// 参考数据1
	public String IPdata_no2;
	
	// 参考数据2
	public String IPdata_no3;
	
	// 兼容IPv6地址
	public String compIPv6;
	
	// 映射IPv6地址
	public String mapIPv6;
	
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIPdata_no1() {
		return IPdata_no1;
	}

	public void setIPdata_no1(String iPdata_no1) {
		IPdata_no1 = iPdata_no1;
	}

	public String getIPdata_no2() {
		return IPdata_no2;
	}

	public void setIPdata_no2(String iPdata_no2) {
		IPdata_no2 = iPdata_no2;
	}

	public String getIPdata_no3() {
		return IPdata_no3;
	}

	public void setIPdata_no3(String iPdata_no3) {
		IPdata_no3 = iPdata_no3;
	}

	public String getCompIPv6() {
		return compIPv6;
	}

	public void setCompIPv6(String compIPv6) {
		this.compIPv6 = compIPv6;
	}

	public String getMapIPv6() {
		return mapIPv6;
	}

	public void setMapIPv6(String mapIPv6) {
		this.mapIPv6 = mapIPv6;
	}

	public String getUserAgent() {
		return UserAgent;
	}

	public void setUserAgent(String userAgent) {
		UserAgent = userAgent;
	}

	public String getVsOS() {
		return vsOS;
	}

	public void setVsOS(String vsOS) {
		this.vsOS = vsOS;
	}

	public String getVsOSName() {
		return vsOSName;
	}

	public void setVsOSName(String vsOSName) {
		this.vsOSName = vsOSName;
	}

	public String getVsIP() {
		return vsIP;
	}

	public void setVsIP(String vsIP) {
		this.vsIP = vsIP;
	}

	public String getVsPor() {
		return vsPor;
	}

	public void setVsPor(String vsPor) {
		this.vsPor = vsPor;
	}

	public String getVsBroNamer() {
		return vsBroNamer;
	}

	public void setVsBroNamer(String vsBroNamer) {
		this.vsBroNamer = vsBroNamer;
	}

}
