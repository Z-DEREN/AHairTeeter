package com.zdr.ahairteeter.demo.Main.Vo;

import com.fasterxml.jackson.core.sym.Name;

public class InterceptorUser {

	// 访问者UserAgent
	public String UserAgent;

	// 访问者登录名称
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

	// 所在国家
	public String Country;

	// 所在省份、州
	public String Province;

	// 所在的城市
	public String City;

	// 运营商
	public String Isp;

	// 所在的地区
	public String District;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getIsp() {
		return Isp;
	}

	public void setIsp(String isp) {
		Isp = isp;
	}

	public String getDistrict() {
		return District;
	}

	public void setDistrict(String district) {
		District = district;
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
