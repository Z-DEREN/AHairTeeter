package com.AHairTeeter.Main.IPpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AHairTeeter.Tool.Tool;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;
import com.AHairTeeter.Main.IPpool.DaoServiceImpl.InternetProtocolDaoServiceImpl;
import com.AHairTeeter.Main.Vo.InterceptorUser;

/**
 * ip�����
 * 
 * @author ����
 *
 */
@Component
public class IPpool extends IoHandlerAdapter {
	Tool Tool = new Tool();

	@Autowired
	protected InternetProtocolDaoServiceImpl InternetProtocolDaoServiceImpl;
	private static IPpool IPpool;

	@PostConstruct // ͨ��@PostConstructʵ�ֳ�ʼ��bean֮ǰ���еĲ���
	public void init() {
		IPpool = this;
		IPpool.InternetProtocolDaoServiceImpl = this.InternetProtocolDaoServiceImpl;
		// ��ʹ��ʱ���Ѿ�̬����testServiceʵ����
	}

	private static final Logger logger = LogManager.getLogger(IPpool.class.getName());

	Spiders Spiders = new Spiders();

	private String IPTest = "http://pv.sohu.com/cityjson?ie=utf-8";// ip����ҳ

/////////////////////////// ��ȡ//////////////////////

	private String ChinaIPCryp = "https://www.xicidaili.com/nn/";// ���ڸ���ip

	/**
	 * ��ȡ���ڸ���ip
	 * 
	 * @throws InterruptedException
	 */
	public List<Map<String, Object>> Get61ChinaIPCryp(int pagination) {
		Spiders Spiders = new Spiders();
		IOLocalFile IOLocalFile = new IOLocalFile();
		List<Map<String, Object>> ListIP = new ArrayList<Map<String, Object>>();
//		ListIP.addAll(Getiplist(IOLocalFile.output("F:\\rdzgsq\\Laboratory\\����\\iptest1.txt")));
//		return ListIP;

		// ��ȡ������Ѹ���ip:https://www.xicidaili.com/nn/
		String url = "";// �洢��ǰҳurl
		String text = "";// �洢��ȡ����ҳ��Դ��
		// ��ȡǰ20ҳip����
		for (int i = 1; i <= pagination; i++) {
			url = ChinaIPCryp + i;
			text = Spiders.spiders(url, 99999);
			ListIP.addAll(Getiplist(text));
			try {
				Thread.sleep(1000 * 60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ListIP;
	}

	public int num = 1;
	Set<String> setlist = new HashSet<String>();

	public List<Map<String, Object>> Getiplist(String text) {
		List<Map<String, Object>> ListIP = new ArrayList<Map<String, Object>>();
		String area = "";// ����
		String textno1 = "";
		int beginIndex = 0;// ɸѡ�ô洢�±�λ
		int endIndex = 0;// ɸѡ�ô洢�±�λ
		beginIndex = text.indexOf("<tbody>");
		endIndex = text.indexOf("</tbody>");
		text = text.substring(beginIndex, endIndex);
//		System.out.println("Դ��" + text);
		String[] textno3 = text.split("</tr>");
		for (int i = 1; i < textno3.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String textarray = textno3[i];
			// ȥ��������
			textarray = textarray.replace("<td>", "");
			textarray = textarray.replace(" ", "");
			textarray = textarray.replace("	", "");
			textarray = textarray.replace("\n", "");
			textarray = textarray.replace("\r", "");
			textarray = textarray.replace("\t", "");
			String[] textno4 = textarray.split("</td>");
			if (textno4.length > 2) {
				endIndex = textno4[3].indexOf(">");
				if (endIndex > 0) {
					textno1 = textno4[3].substring(endIndex);
					endIndex = textno1.indexOf("</a>");
					area = textno1.substring(1, endIndex);
				} else {
					area = "δ֪";
				}
				logger.info("��ȡ����IP-----num:" + num + "---" + textno4[1] + ":" + textno4[2] + ":" + area);
//				map.put("SID",GetSID("ippoolinspect"));
				map.put("ADI", "62");
				map.put("ip", textno4[1]);
				map.put("port", textno4[2]);
				map.put("area", area);
				map.put("SAVETIME", Tool.GetNewDateTime(2));
				map.put("TYPE", "1");

				IPpool.InternetProtocolDaoServiceImpl.SaveIPList(map);

				num++;
			}
		}
		return ListIP;
	}

	///////////////////////////////////////// {����ip��}/////////////////////////////////////////////////////////////

	/**
	 * ����ip����(���Ƽ��ֶ�����,����ִ��ʱ��ϳ�)
	 * 
	 * @param ChinaIPList
	 * @return ����ͨ��ip map��ip��ַ:"ip",�˿ں�:"port",����:"area",��Ӧʱ��(����):"msec"
	 * 
	 */
	public void GetPerfectCHIP(Map<String, Object> ChinaIPList, String present, int number) {
		Spiders Spiders = new Spiders();
		List<String> list = new ArrayList<String>();
		String Iptext = "";
		logger.info("ʣ��:" + number + ",���ڲ��Ե���" + ChinaIPList.get("AREA") + "--" + ChinaIPList.get("IP") + "--"
				+ ChinaIPList.get("PORT"));
		long startTime = System.currentTimeMillis(); // ��ȡ��ʼʱ��
		System.getProperties().setProperty("http.proxyHost", ChinaIPList.get("IP").toString());
		System.getProperties().setProperty("http.proxyPort", ChinaIPList.get("PORT").toString());
		Iptext = Spiders.spiders(IPTest, 99999);// ���Խӿ�
		long endTime = System.currentTimeMillis();
		// �����ж��������ĵ�ʱ��,���ж�
		Long time = endTime - startTime;
		// �Ƿ�Ϊ����ip
		if (!Iptext.contains(present)) {
			logger.info("true	����ͨ��==IP-----" + ChinaIPList.get("IP") + "--" + ChinaIPList.get("PORT") + ":"
					+ ChinaIPList.get("AREA") + ":" + time + "����");
			IPpool.InternetProtocolDaoServiceImpl.SaveIPlistnum(time.toString(), ChinaIPList);
		} else {
			logger.info("false	����ʧ��==IP-----" + ChinaIPList.get("IP") + "--" + ChinaIPList.get("PORT") + ":"
					+ ChinaIPList.get("AREA") + ":" + time / 1000 + "��");
		}
		IPpool.InternetProtocolDaoServiceImpl.DelTestIP(ChinaIPList);
	}

	/**
	 * ��ȡ����ip�˿ڵ���
	 * 
	 */
	public String Localip() {

		String text = Spiders.spiders("http://pv.sohu.com/cityjson?ie=utf-8", 99999);// ���Խӿ�
		int beginIndex;
		int endIndex;
		beginIndex = text.indexOf("cip");
		endIndex = text.indexOf("\",");
		logger.info("����ipʡ��IPΪ:" + text.substring(beginIndex + 7, endIndex));
		return text;
	}
	///////////////////////////////////////// {����ip��}/////////////////////////////////////////////////////////////

//	/**
//	 * ��������ip��ѯip��ַ
//	 * 
//	 * @param ip              ָ��ip
//	 * @param InterceptorUser ʵ��
//	 * @return
//	 */
//	public InterceptorUser GetaccordingIP(String ip, InterceptorUser InterceptorUser) {
//		String text = Spiders.spiders("http://www.ip138.com/ips1388.asp?ip=" + ip + "&action=2", 99999);// ���Խӿ�
//		int beginIndex;
//		int endIndex;
//		String IPdata_no1 = "";
//		String IPdata_no2 = "";
//		String IPdata_no3 = "";
//		String compIPv6 = "";
//		String mapIPv6 = "";
//
//		beginIndex = text.indexOf("��վ����");
//		if (beginIndex > 0) {
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		IPdata_no1 = text.substring(5, endIndex);
//		InterceptorUser.setIPdata_no1(IPdata_no1);
//
//		beginIndex = text.indexOf("�ο�����1");
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		IPdata_no2 = text.substring(6, endIndex);
//		InterceptorUser.setIPdata_no2(IPdata_no2);
//
//		beginIndex = text.indexOf("�ο�����2");
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		IPdata_no3 = text.substring(6, endIndex);
//		InterceptorUser.setIPdata_no3(IPdata_no3);
//
//		beginIndex = text.indexOf("����IPv6��ַ");
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		compIPv6 = text.substring(11, endIndex);
//		InterceptorUser.setCompIPv6(compIPv6);
//
//		beginIndex = text.indexOf("ӳ��IPv6��ַ");
//		text = text.substring(beginIndex);
//		endIndex = text.indexOf("</li>");
//		mapIPv6 = text.substring(11, endIndex);
//		InterceptorUser.setMapIPv6(mapIPv6);
//		}else {
//			InterceptorUser.setIPdata_no1("");
//			InterceptorUser.setIPdata_no1("");
//			InterceptorUser.setIPdata_no1("");
//			InterceptorUser.setCompIPv6("");
//			InterceptorUser.setMapIPv6("");
//		}
//		
//		return InterceptorUser;
//	}

}
