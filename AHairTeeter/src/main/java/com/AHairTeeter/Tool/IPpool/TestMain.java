package com.AHairTeeter.Tool.IPpool;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.AHairTeeter.Tool.Tool;

/**
 * ip���ӳ�, ��ȡʱ����һ����ִ��һ��,��ֹip���� http://pv.sohu.com/cityjson?ie=utf-8��������ip
 * 
 * @author ����
 *
 */
public class TestMain {

	private static final Logger logger = LogManager.getLogger(TestMain.class.getName());

	public static void main(String[] args) {
		TestMain TestMain = new TestMain();
		TestMain.GetChinaIPCryp();
		System.out.println("����");
	}

	Tool Tool = new Tool();
	String ChinaIPCryp = "https://www.xicidaili.com/nn/";

	/**
	 * ��Ϊ��ʱִ�л�ȡip����
	 */
	public void ChinaIPMain() {
		// ��ȡ���ڸ���ip(61)
		List<Map<String, String>> ChinaIPList = new ArrayList<Map<String, String>>();
		ChinaIPList = GetChinaIPCryp();
		//���
		String present = Localip();// �����洢����ip(�����������ip��У��,�Ƿ��Ѿ�����ip)
		// ����ip
		List<Map<String, String>> PerfectCHIP = new ArrayList<Map<String, String>>();
		PerfectCHIP = GetPerfectCHIP(ChinaIPList,present);
		//�����ڸ���ip(62)
		List<String> list62 = new ArrayList<String>();
		for(Map<String, String> keyval : PerfectCHIP) {
			list62.add(saveIP(keyval,"62"));
		}
		
		
	}
	
	/**
	 * ������ IP��
	 * 
	 * @param IPmap map��������
	 * @param type  ��ӦZDI����
	 */
	public String saveIP(Map<String, String> IPmap, String type) {
		String sql = "INSERT INTO ippool (ZDI,IP,PORT,AREA,MSEC,UPDATETIME,TYPE) VALUES";
		sql += "(" + Tool.GetNewZDInum(type) + ",'" + IPmap.get("ip") + "'," + IPmap.get("port") + ",'"
				+ IPmap.get("area") + "'," + IPmap.get("msec") + ",'" + Tool.GetNewDateTime(2) + "','" + type + "')";
		return sql;
	}
	/**
	 * ����ip����
	 * @param ChinaIPList
	 * @return ����ͨ��ip map��ip��ַ:"ip",�˿ں�:"port",����:"area",��Ӧʱ��(����):"msec"
	 * 
	 */
	public List<Map<String, String>> GetPerfectCHIP(List<Map<String, String>> ChinaIPList,String present) {
		List<Map<String, String>> PerfectCHIP = new ArrayList<Map<String, String>>();
		String Iptext = "";
		for (Map<String, String> map : PerfectCHIP) {
			System.out.println("���ڲ��Ե���" + map.get("area") + "--" + map.get("ip") + "--" + map.get("port"));
			long startTime = System.currentTimeMillis(); // ��ȡ��ʼʱ��
			System.getProperties().setProperty("http.proxyHost", map.get("ip"));
			System.getProperties().setProperty("http.proxyPort", map.get("port"));
			Iptext = Tool.BriefnessAcquire("http://pv.sohu.com/cityjson?ie=utf-8");// ���Խӿ�
			long endTime = System.currentTimeMillis();
			// �����ж��������ĵ�ʱ��,���ж�
			Long time = endTime - startTime;
			// �Ƿ�Ϊ����ip
			if (!Iptext.contains(present)) {
				Map<String, String> retuenListMap = new HashMap<String, String>();
				logger.info("true	����ͨ��==IP-----" + map.get("ip") + ":" + map.get("port") + ":" + map.get("area") + ":"
						+ time / 1000 + "��");
				retuenListMap.put("ip", map.get("ip"));
				retuenListMap.put("port", map.get("port"));
				retuenListMap.put("area", map.get("area"));
				retuenListMap.put("msec", time.toString());
				PerfectCHIP.add(retuenListMap);
				
				
				
			} else {
				logger.info("false	����ʧ��==IP-----" + map.get("ip") + ":" + map.get("port") + ":" + map.get("area") + ":"
						+ time / 1000 + "��");
			}
		}
		return PerfectCHIP;
	}

	/**
	 * ��ȡ����ip�˿ڵ���
	 * 
	 */
	public String Localip() {
		String text = Tool.BriefnessAcquire("http://pv.sohu.com/cityjson?ie=utf-8");// ���Խӿ�
		int beginIndex;
		int endIndex;
		beginIndex = text.indexOf("cip");
		endIndex = text.indexOf("\",");
		logger.info("����ipʡ��IPΪ:" + text.substring(beginIndex + 7, endIndex));
		return text;
	}

	/**
	 * ��ȡ���ڸ���ip
	 * 
	 * @throws InterruptedException
	 */
	public List<Map<String, String>> GetChinaIPCryp(){
		// ��ȡ������Ѹ���ip:https://www.xicidaili.com/nn/
		String url = "";// �洢��ǰҳurl
		String text = "";// �洢��ȡ����ҳ��Դ��

		List<Map<String, String>> ListIP = new ArrayList<Map<String, String>>();
		// ��ȡǰ20ҳip����
		for (int i = 1; i <= 20; i++) {
			url = ChinaIPCryp + i;
			text = Tool.BriefnessAcquire(url);
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

	Set<String> setlist = new HashSet<String>();
	private int num = 1;
	/**
	 * ɸѡ����
	 * @param text
	 * @return
	 */
	public List<Map<String, String>> Getiplist(String text) {
		List<Map<String, String>> ListIP = new ArrayList<Map<String, String>>();
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
			Map<String, String> map = new HashMap<String, String>();
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
				map.put("ip", textno4[1]);
				map.put("port", textno4[2]);
				map.put("area", area);
				if (!setlist.contains(textno4[1] + ":" + textno4[2] + ":" + area)) {
					setlist.add(textno4[1] + ":" + textno4[2] + ":" + area);
					ListIP.add(map);
					num++;
				}
			}
		}
		return ListIP;
	}

}
