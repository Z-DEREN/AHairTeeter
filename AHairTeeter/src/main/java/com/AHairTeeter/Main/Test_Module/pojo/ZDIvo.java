package com.AHairTeeter.Main.Test_Module.pojo;

public class ZDIvo {

	/**
	 * ����,˵��
	 */
	public Integer ZDI;
	/**
	 * ����,˵��
	 */
	public String ZNAME;
	/**
	 * ����ʱ��
	 */
	public String DATETIME;

	/**
	 * ����ʱ��
	 */
	public String UPDATETIME;
	/**
	 * MD5��
	 */
	public String MD5DI;
	/**
	 * ����,����
	 */
	public String TYPE;
	/**
	 * �������
	 */

	public String MODEL;

	public ZDIvo() {
		
	}
	
	public ZDIvo(Integer zDI, String zNAME, String dATETIME, String uPDATETIME, String mD5DI, String tYPE,
			String mODEL) {
		super();
		ZDI = zDI;
		ZNAME = zNAME;
		DATETIME = dATETIME;
		UPDATETIME = uPDATETIME;
		MD5DI = mD5DI;
		TYPE = tYPE;
		MODEL = mODEL;
	}

	public Integer getZDI() {
		return ZDI;
	}

	public void setZDI(Integer zDI) {
		ZDI = zDI;
	}

	public String getZNAME() {
		return ZNAME;
	}

	public void setZNAME(String zNAME) {
		ZNAME = zNAME;
	}

	public String getDATETIME() {
		return DATETIME;
	}

	public void setDATETIME(String dATETIME) {
		DATETIME = dATETIME;
	}

	public String getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(String uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}

	public String getMD5DI() {
		return MD5DI;
	}

	public void setMD5DI(String mD5DI) {
		MD5DI = mD5DI;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getMODEL() {
		return MODEL;
	}

	public void setMODEL(String mODEL) {
		MODEL = mODEL;
	}

}
