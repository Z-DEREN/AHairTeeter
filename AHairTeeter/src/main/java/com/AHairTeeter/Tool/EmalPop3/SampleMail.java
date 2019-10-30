package com.AHairTeeter.Tool.EmalPop3;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.AHairTeeter.Tool.Route;

import javax.mail.*;

public class SampleMail {
	private static final String ALIDM_SMTP_HOST = "SMTP.163.com";
	private static final String ALIDM_SMTP_PORT = "80";// 或"80"
	Route Route = new Route();
	public  void SendEmail(String toMailAddress, String title, String text) throws UnsupportedEncodingException {
		// 配置发送邮件的环境属性
		final Properties props = new Properties();
		// 表示SMTP发送邮件，需要进行身份验证
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", ALIDM_SMTP_HOST);
		// props.put("mail.smtp.port", ALIDM_SMTP_PORT);
		// 如果使用ssl，则去掉使用25端口的配置，进行如下配置,
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.port", "465");
		// 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
		props.put("mail.user", Route.getMailUser());
		// 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
		props.put("mail.password", Route.getMailPassword());
		props.put("mail.smtp.socketFactory.fallback", "false"); // 只处理SSL的连接,对于非SSL的连接不做处理
		props.put("mail.smtp.ssl.enable", true);
		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// mailSession.setDebug(true);
		// UUID uuid = UUID.randomUUID();
		// final String messageIDValue = "<" + uuid.toString() + ">";
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession) {
			// @Override
			// protected void updateMessageID() throws MessagingException {
			// 设置自定义Message-ID值
			// setHeader("Message-ID", messageIDValue);
			// }
		};
		try {
			// 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
			InternetAddress from = new InternetAddress(Route.getMailUser(), "服务器");
			message.setFrom(from);
			// 可选。设置回信地址
			Address[] a = new Address[1];
			a[0] = new InternetAddress("***");
			message.setReplyTo(a);
			// 设置收件人邮件地址，比如yyy@yyy.com
			InternetAddress to = new InternetAddress(toMailAddress);
			message.setRecipient(MimeMessage.RecipientType.TO, to);
			// 如果同时发给多人，才将上面两行替换为如下（因为部分收信系统的一些限制，尽量每次投递给一个人；同时我们限制单次允许发送的人数是30人）：
//	    InternetAddress[] adds = new InternetAddress[2];
//	    adds[0] = new InternetAddress("941958131@qq.com");
//	    adds[1] = new InternetAddress("10554214@qq.com");
//	    message.setRecipients(Message.RecipientType.TO, adds);
			// String ccUser = "抄送邮箱";
			// // 设置多个抄送地址
			// if (null != ccUser && !ccUser.isEmpty()) {
			// @SuppressWarnings("static-access")
			// InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);
			// message.setRecipients(Message.RecipientType.CC, internetAddressCC);
			// }
			// String bccUser = "密送邮箱";
			// // 设置多个密送地址
			// if (null != bccUser && !bccUser.isEmpty()) {
			// @SuppressWarnings("static-access")
			// InternetAddress[] internetAddressBCC = new InternetAddress().parse(bccUser);
			// message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
			// }
			// 设置邮件标题
			message.setSubject(title);
			// 设置邮件的内容体
			message.setContent(text, "text/html;charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException e) {
			String err = e.getMessage();
			// 在这里处理message内容， 格式是固定的
			System.out.println(err);
		}
	}
}