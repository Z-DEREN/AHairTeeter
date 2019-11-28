package com.AHairTeeter.Main.Interceptor.SeekTheTruth;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Main.Vo.InterceptorUser;
import com.AHairTeeter.Main.Vo.ZUSER;
import com.AHairTeeter.Tool.Tool;

@Service
public class SeekTheTruth {

	private static final Logger logger = LogManager.getLogger(SeekTheTruth.class.getName());

	Tool Tool = new Tool();

	@Resource
	public SeekTheTruthDaoServiceImpl SeekTheTruthDaoServiceImpl;

	/**
	 * �ռ���������Ϣ ������Ᵽ��
	 * 
	 * @return
	 */
	public boolean FBIexamineWaterMeter(HttpServletRequest request, HttpSession session, ZUSER zuser) {
		boolean TF = false;

		// �����߶˿ں�
		String Port = request.getRemotePort() + "";
		// ������IP��ַ
		String HostIP = request.getRemoteHost();
		// ������Addr��ַ
		String AddrIP = request.getRemoteAddr();
		// ������SessionId
		String SessionId = request.getRequestedSessionId();
		// �����ߵ�ǰ���ʵ�URI
		String URI = request.getRequestURI();

		logger.info("��ӡ������Я����������Ϣ"); // info�������Ϣ
		logger.info("������"); // info�������Ϣ
		logger.info("������IP��ַ" + HostIP); // info�������Ϣ
		logger.info("������Addr��ַ" + AddrIP); // info�������Ϣ
		logger.info("�����߶˿ں�" + Port); // info�������Ϣ
		logger.info("������SessionId" + SessionId); // info�������Ϣ
		logger.info("�����ߵ�ǰ���ʵ�URI" + request.getRequestURI()); // info�������Ϣ
		logger.info("��ӡ������Я����������Ϣ����"); // info�������Ϣ

		// ��������Ϣʵ��
		InterceptorUser InterceptorUser = new InterceptorUser();

		ZUSER userInfo = (ZUSER) session.getAttribute("GuesswhoIam");
		if (userInfo != null && userInfo.getD7788b7e0ba4b6e3aa57b35bbf93dfc6() != null
				&& !userInfo.getD7788b7e0ba4b6e3aa57b35bbf93dfc6().equals("")) {
			logger.info("�����߻����Ȩ�ѵ�¼"); // info�������Ϣ

			// ����½��Ϣ��Ⲣ�ʼ�����,��������

			System.out.println(userInfo.getNAME());
			System.out.println(userInfo.getPass());

		} else {
			logger.info("������δ��¼"); // info�������Ϣ

			// ��ȡ������������˺�
			if (zuser != null && zuser.getNAME() != null) {
				InterceptorUser.setUsername(zuser.getNAME());
			} else {
				InterceptorUser.setUsername("");
			}

//			��ȡ�������Ϣ
			InterceptorUser = GetinvadeINFO(request, InterceptorUser);
			// ����ip
			InterceptorUser.setVsIP(HostIP);
			// ����˿ں�
			InterceptorUser.setVsPor(Port);

			// ���ݿ��ȡ��ip�Ƿ���ʹ�

			InterceptorUser = SeekTheTruthDaoServiceImpl.SeeVisitorIPrecord(InterceptorUser, HostIP);
			if( InterceptorUser.getCity() == null) {
			// ʹ�õ������Ľӿڻ�ȡip����ϸ��Ϣ
			Map<String, Object> map = Tool.GetIPJsonMaps(HostIP);
			InterceptorUser.setCity(map.get("City").toString());
			InterceptorUser.setCountry(map.get("Country").toString());
			InterceptorUser.setProvince(map.get("Province").toString());
			InterceptorUser.setIsp(map.get("Isp").toString());
			InterceptorUser.setDistrict(map.get("District").toString());
			}

//			InterceptorUser = IPpool.GetaccordingIP(request.getLocalAddr(), InterceptorUser);

			//����������������
			SeekTheTruthDaoServiceImpl.insert(InterceptorUser);

//			 ��������ʵip
//			getIpAddr(request);

		}
		String host = request.getRemoteHost();
		logger.info("IPΪ---->>> " + host + " <<<-----������ϵͳ"); // info�������Ϣ
//		System.out.println("��ӡcookie");
//		logger.info("��ӡ������Я��cookie"); // info�������Ϣ
//		Cookie[] zcookie = request.getCookies();
//		if (zcookie != null && zcookie.length > 0) {
//			for (int i = 0; i < zcookie.length; i++) {
//				logger.info("cookie��ӡ:--" + zcookie[i]); // info�������Ϣ
//			}
//		} else {
//			logger.info("<<<������δЯ��cookie>>>"); // info�������Ϣ
//		}
//		logger.info("��ӡcookie����"); // info�������Ϣ

		return TF;
	}

	/**
	 * ��ȡ�����ߵĲ���ϵͳ�Լ��������Ϣ ��ȡHttpServletRequest��Ϣ
	 * 
	 * @return
	 */
	public InterceptorUser GetinvadeINFO(HttpServletRequest request, InterceptorUser InterceptorUser) {
		String winUserAgent = request.getHeader("User-Agent");
		Usaeragent Usaeragent = new Usaeragent();

		logger.info("������飺" + Usaeragent.getBorderGroup(winUserAgent)); // info�������Ϣ
		logger.info("��������֣�" + Usaeragent.getBorderName(winUserAgent)); // info�������Ϣ
		logger.info("���������" + Usaeragent.getBorderType(winUserAgent)); // info�������Ϣ
		logger.info("����������̣�" + Usaeragent.getBrowserManufacturer(winUserAgent)); // info�������Ϣ
		logger.info("������汾��" + Usaeragent.getBrowserVersion(winUserAgent)); // info�������Ϣ
		logger.info("�豸��������:" + Usaeragent.getDeviceManufacturer(winUserAgent)); // info�������Ϣ
		logger.info("�豸����:" + Usaeragent.getDevicetype(winUserAgent)); // info�������Ϣ
		logger.info("�豸����ϵͳ��" + Usaeragent.getOs(winUserAgent)); // info�������Ϣ
		logger.info("����ϵͳ�����֣�" + Usaeragent.getOsName(winUserAgent)); // info�������Ϣ
		logger.info("����ϵͳ�İ汾�ţ�" + Usaeragent.getOsVersion(winUserAgent)); // info�������Ϣ
		logger.info("����ϵͳ���������Ⱦ����:" + Usaeragent.getBorderRenderingEngine(winUserAgent)); // info�������Ϣ

		// ������UserAgent
		InterceptorUser.setUserAgent(winUserAgent);
		// ���������
		InterceptorUser.setVsBroNamer(Usaeragent.getBorderName(winUserAgent));
		// ������汾
		InterceptorUser.setVsBroNamer(Usaeragent.getBrowserVersion(winUserAgent));
		// �����߲���ϵͳ��
		InterceptorUser.setVsOS(Usaeragent.getOs(winUserAgent));
		// �����߲���ϵͳ�汾
		InterceptorUser.setVsOSName(Usaeragent.getOsName(winUserAgent));

		return InterceptorUser;
	}

	/**
	 * ��ȡ����������ip
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {

		System.out.println("���Է���һ��ȡ��ʵip��");
		List<String> list = new ArrayList<String>();

		list.add(request.getHeader("X-Forwarded-For"));
		list.add(request.getHeader("Proxy-Client-IP"));
		list.add(request.getHeader("WL-Proxy-Client-IP"));
		list.add(request.getHeader("HTTP_CLIENT_IP"));
		list.add(request.getHeader("HTTP_X_FORWARDED_FOR"));
		for (int i = 0; i < list.size(); i++) {
			logger.info("��ʵip:" + list.get(i)); // info�������Ϣ
		}

		System.out.println("���Է���һ����");

		String ipAddress = null;
		try {
			ipAddress = request.getHeader("x-forwarded-for");
			System.out.println(ipAddress);
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
				ipAddress = request.getRemoteAddr();
				if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
					// ��������ȡ�������õ�IP
					InetAddress inet = null;
					try {
						inet = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					ipAddress = inet.getHostAddress();
				}
			}
			// ����ͨ�����������������һ��IPΪ�ͻ�����ʵIP,���IP����','�ָ�
			if (ipAddress != null && ipAddress.length() > 15) {
				System.out.println(ipAddress);
				// "***.***.***.***".length()
				// = 15
//				if (ipAddress.indexOf(",") > 0) {
//					ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
//				}
			}
		} catch (Exception e) {
			ipAddress = "";
		}
//		 ipAddress = this.getRequest().getRemoteAddr();
		return ipAddress;
	}
}
