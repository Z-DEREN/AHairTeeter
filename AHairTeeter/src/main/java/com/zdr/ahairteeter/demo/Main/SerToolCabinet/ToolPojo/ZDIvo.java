package com.zdr.ahairteeter.demo.Main.SerToolCabinet.ToolPojo;

public class ZDIvo {

	/**
	 * 名字,说明
	 */
	public Integer ZDI;
	/**
	 * 名字,说明
	 */
	public String ZNAME;
	/**
	 * 创建时间
	 */
	public String DATETIME;

	/**
	 * 更新时间
	 */
	public String UPDATETIME;
	/**
	 * MD5码
	 */
	public String MD5DI;
	/**
	 * 类型,分类
	 */
	public String TYPE;
	/**
	 * 更多分类
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
