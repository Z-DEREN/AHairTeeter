package com.AHairTeeter.Main.Vo;

import com.fasterxml.jackson.core.sym.Name;

public class InterceptorUser {

	// ������UserAgent
	public String UserAgent;

	// �����ߵ�¼����
	public String username;

	// ������ip
	public String vsIP;

	// �����߶˿�
	public String vsPor;

	// ���������������
	public String vsBroNamer;

	// �����߲���ϵͳ��
	public String vsOS;

	// �����߲���ϵͳ�汾
	public String vsOSName;

	// ���ڹ���
	public String Country;

	// ����ʡ�ݡ���
	public String Province;

	// ���ڵĳ���
	public String City;

	// ��Ӫ��
	public String Isp;

	// ���ڵĵ���
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
