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
	private static final String ALIDM_SMTP_PORT = "80";// ��"80"
	Route Route = new Route();
	public  void SendEmail(String toMailAddress, String title, String text) throws UnsupportedEncodingException {
		// ���÷����ʼ��Ļ�������
		final Properties props = new Properties();
		// ��ʾSMTP�����ʼ�����Ҫ���������֤
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", ALIDM_SMTP_HOST);
		// props.put("mail.smtp.port", ALIDM_SMTP_PORT);
		// ���ʹ��ssl����ȥ��ʹ��25�˿ڵ����ã�������������,
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.port", "465");
		// �����˵��˺ţ���д����̨���õķ��ŵ�ַ,����xxx@xxx.com
		props.put("mail.user", Route.getMailUser());
		// ����SMTP����ʱ��Ҫ�ṩ������(�ڿ���̨ѡ���ŵ�ַ��������)
		props.put("mail.password", Route.getMailPassword());
		props.put("mail.smtp.socketFactory.fallback", "false"); // ֻ����SSL������,���ڷ�SSL�����Ӳ�������
		props.put("mail.smtp.ssl.enable", true);
		// ������Ȩ��Ϣ�����ڽ���SMTP���������֤
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// �û���������
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// ʹ�û������Ժ���Ȩ��Ϣ�������ʼ��Ự
		Session mailSession = Session.getInstance(props, authenticator);
		// mailSession.setDebug(true);
		// UUID uuid = UUID.randomUUID();
		// final String messageIDValue = "<" + uuid.toString() + ">";
		// �����ʼ���Ϣ
		MimeMessage message = new MimeMessage(mailSession) {
			// @Override
			// protected void updateMessageID() throws MessagingException {
			// �����Զ���Message-IDֵ
			// setHeader("Message-ID", messageIDValue);
			// }
		};
		try {
			// ���÷������ʼ���ַ�����ơ���д����̨���õķ��ŵ�ַ,����xxx@xxx.com���������mail.user����һ�¡������û������Զ�����д��
			InternetAddress from = new InternetAddress(Route.getMailUser(), "������");
			message.setFrom(from);
			// ��ѡ�����û��ŵ�ַ
			Address[] a = new Address[1];
			a[0] = new InternetAddress("***");
			message.setReplyTo(a);
			// �����ռ����ʼ���ַ������yyy@yyy.com
			InternetAddress to = new InternetAddress(toMailAddress);
			message.setRecipient(MimeMessage.RecipientType.TO, to);
			// ���ͬʱ�������ˣ��Ž����������滻Ϊ���£���Ϊ��������ϵͳ��һЩ���ƣ�����ÿ��Ͷ�ݸ�һ���ˣ�ͬʱ�������Ƶ��������͵�������30�ˣ���
//	    InternetAddress[] adds = new InternetAddress[2];
//	    adds[0] = new InternetAddress("941958131@qq.com");
//	    adds[1] = new InternetAddress("10554214@qq.com");
//	    message.setRecipients(Message.RecipientType.TO, adds);
			// String ccUser = "��������";
			// // ���ö�����͵�ַ
			// if (null != ccUser && !ccUser.isEmpty()) {
			// @SuppressWarnings("static-access")
			// InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);
			// message.setRecipients(Message.RecipientType.CC, internetAddressCC);
			// }
			// String bccUser = "��������";
			// // ���ö�����͵�ַ
			// if (null != bccUser && !bccUser.isEmpty()) {
			// @SuppressWarnings("static-access")
			// InternetAddress[] internetAddressBCC = new InternetAddress().parse(bccUser);
			// message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
			// }
			// �����ʼ�����
			message.setSubject(title);
			// �����ʼ���������
			message.setContent(text, "text/html;charset=UTF-8");
			Transport.send(message);
		} catch (MessagingException e) {
			String err = e.getMessage();
			// �����ﴦ��message���ݣ� ��ʽ�ǹ̶���
			System.out.println(err);
		}
	}
}