package com.zdr.ahairteeter.demo.Tool.EmalPop3;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;


@Component
public class EmailType {
	SampleMail SampleMail = new SampleMail();
	/**
	 * 36Kr邮件方式
	 */
	public void Kr36Email(List<Map<String, Object>> list){
		// 收件人
		String EtoMailAddress = "10554214@qq.com";
		// 多个收件人
		String EtoMailAddresss[] = { "10554214@qq.com"
//				, "ztdztd995@163.com"
				// , "941958131@qq.com"
				// , "409451000@qq.com"
		};
		// 标题
		String Etitle = "ZDR:36Kr：" + new Date().getTime() + ":" + list.size() + "条";
		// 内容
		String Etext = "";

		for (Map<String, Object> map : list) {
			Etext += map.get("uniqueid") + "<br>";
			Etext += map.get("line") + "<br>";
			Etext += map.get("text") + "<br>";
			if(map.get("url")!= null) {
				Etext += "<a href='" + map.get("url") + "' >外部链接</a>" + "<br>";
			}
			Etext +=map.get("recorddate") + "<br>";
		}
//		System.out.println(Etext);
		try {
			for (String Email : EtoMailAddresss) {
				SampleMail.SendEmail(Email, Etitle, Etext);
			}
			// SampleMail.SendEmail(EtoMailAddress, Etitle, Etext);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Custom(Map<String, Object> text) {
		Date time = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		// 收件人
		String EtoMailAddress = text.get("EtoMailAddress").toString();
		// 标题
		String Etitle = text.get("Etitle").toString() + "时间:" + ft.format(time);
		// 内容
		String Etext = text.get("Etext").toString();

		try {
			SampleMail.SendEmail(EtoMailAddress, Etitle, Etext);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 测试用定时邮件
	 * 
	 * @param text
	 * @throws MessagingException
	 */
	public void NewsEmail() throws MessagingException {

		// 收件人
		String EtoMailAddress = "10554214@qq.com";
		// 标题
		String Etitle = "测试:当前时间为: " + new Date();
		// 内容
		String Etext = "暂时无信息";

		try {
			SampleMail.SendEmail(EtoMailAddress, Etitle, Etext);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
