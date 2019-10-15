package com.AHairTeeter.Tool.IPpool;

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
 * ip�����
 * 
 * @author ����
 *
 */
public class IPpool {
	private static final Logger logger = LogManager.getLogger(IPpool.class.getName());

	Tool Tool = new Tool();
	private String ChinaIPCryp = "https://www.xicidaili.com/nn/";//���ڸ���ip
	private String IPTest = "http://pv.sohu.com/cityjson?ie=utf-8";//ip����ҳ

	/**
	 * ������ IP��
	 * 
	 * @param IPmap map��������
	 * @param type  ��ӦZDI����
	 */
	public String save_IP(Map<String, String> IPmap, String type) {
		String sql = "INSERT INTO ippool (ZDI,IP,PORT,AREA,MSEC,UPDATETIME,TYPE) VALUES";
		sql += "(" + Tool.GetNewZDInum(type) + ",'" + IPmap.get("ip") + "'," + IPmap.get("port") + ",'"
				+ IPmap.get("area") + "'," + IPmap.get("msec") + ",'" + Tool.GetNewDateTime(2) + "','" + type + "')";
		return sql;
	}

	/**
	 * ��ȡ���ڸ���ip
	 * 
	 * @throws InterruptedException
	 */
	public List<Map<String, String>> Get61ChinaIPCryp(int pagination) {
		// ��ȡ������Ѹ���ip:https://www.xicidaili.com/nn/
		String url = "";// �洢��ǰҳurl
		String text = "";// �洢��ȡ����ҳ��Դ��

		List<Map<String, String>> ListIP = new ArrayList<Map<String, String>>();
		// ��ȡǰ20ҳip����
		for (int i = 1; i <= pagination; i++) {
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

	/**
	 * ����ip����(���Ƽ��ֶ�����,����ִ��ʱ��ϳ�)
	 * 
	 * @param ChinaIPList
	 * @return ����ͨ��ip map��ip��ַ:"ip",�˿ں�:"port",����:"area",��Ӧʱ��(����):"msec"
	 * 
	 */
	public Map<String, String> GetPerfectCHIP(Map<String, String> ChinaIPList, String present) {
		List<String> list = new ArrayList<String>();
		String Iptext = "";
		System.out.println("���ڲ��Ե���" + ChinaIPList.get("area") + "--" + ChinaIPList.get("ip") + "--" + ChinaIPList.get("port"));
		long startTime = System.currentTimeMillis(); // ��ȡ��ʼʱ��
		System.getProperties().setProperty("http.proxyHost", ChinaIPList.get("ip"));
		System.getProperties().setProperty("http.proxyPort", ChinaIPList.get("port"));
		Iptext = Tool.BriefnessAcquire(IPTest);// ���Խӿ�
		long endTime = System.currentTimeMillis();
		// �����ж��������ĵ�ʱ��,���ж�
		Long time = endTime - startTime;
		// �Ƿ�Ϊ����ip
		if (!Iptext.contains(present)) {
			Map<String, String> retuenListMap = new HashMap<String, String>();
			logger.info("true	����ͨ��==IP-----" + ChinaIPList.get("ip") + ":" + ChinaIPList.get("port") + ":" + ChinaIPList.get("area") + ":"
					+ time + "����");
			retuenListMap.put("ip", ChinaIPList.get("ip"));
			retuenListMap.put("port", ChinaIPList.get("port"));
			retuenListMap.put("area", ChinaIPList.get("area"));
			if(time == 0) {
				retuenListMap.put("msec", "1");
			}else {
				retuenListMap.put("msec", time.toString());
			}
			
			
			
			return retuenListMap;
		} else {
			logger.info("false	����ʧ��==IP-----" + ChinaIPList.get("ip") + ":" + ChinaIPList.get("port") + ":" + ChinaIPList.get("area") + ":"
					+ time / 1000 + "��");
			return null;
		}
		
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

/////////////////////////// ��ȡ//////////////////////

	public int num = 1;
	Set<String> setlist = new HashSet<String>();

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
