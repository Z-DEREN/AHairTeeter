package com.AHairTeeter.Tool;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import com.AHairTeeter.Main.ToolCabinet.ToolDaoImpl.ToolDaoImpl;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Tool {
	private static final Logger logger = LogManager.getLogger(Tool.class.getName());
	private static SimpleDateFormat DT1 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat DT2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat DT3 = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat DT4 = new SimpleDateFormat("HH:mm");
	private static SimpleDateFormat DT5 = new SimpleDateFormat("yyyyMMddHHmmss");
	MD5 md5 = new MD5();
	IOLocalFile IOLocalFile = new IOLocalFile();
	private static Map<String, SimpleDateFormat> DTMap = new HashMap<String, SimpleDateFormat>();

	/**
	 * 静态代码块 (存入缓存当中)
	 */
	static {
		logger.info(" 静态代码块启动------------------------------------------------------------"); // info级别的信息
		// 初始化时间模式Map
		DTMap.put("DT1", DT1);
		DTMap.put("DT2", DT2);
		DTMap.put("DT3", DT3);
		DTMap.put("DT4", DT4);
		DTMap.put("DT5", DT5);

	}

	/**
	 * 新建时间格式化方法
	 * 
	 * @param type 对应类型
	 *             <p>
	 *             1 yyyy-MM-dd
	 *             </p>
	 *             <p>
	 *             2 yyyy-MM-dd HH:mm:ss
	 *             </p>
	 *             <p>
	 *             3 HH:mm:ss
	 *             </p>
	 *             <p>
	 *             4 HH:mm:ss
	 *             </p>
	 * @return String 时间
	 */
	public String GetNewDateTime(int type) {
		String DateTime = "";
		Date date = new Date();
		DateTime = DTMap.get("DT" + type).format(date);
		return DateTime;
	}

	/**
	 * 指定时间格式化方法
	 * 
	 * @param type 对应类型
	 * @param date 指定时间
	 *             <p>
	 *             1 yyyy-MM-dd
	 *             </p>
	 *             <p>
	 *             2 yyyy-MM-dd HH:mm:ss
	 *             </p>
	 *             <p>
	 *             3 HH:mm:ss
	 *             </p>
	 *             <p>
	 *             4 HH:mm:ss
	 *             </p>
	 * @return String 时间
	 */
	public String GetNewDateTime(int type, String date) {
		String DateTime = "";
		try {
			Date sDate = DT2.parse(date);
			DateTime = DTMap.get("DT" + type).format(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DateTime;
	}

	private static Document doc = null;
	private static String Sdoc = null;


	/**
	 * 获取对应ipDI码以及相关参数
	 * 
	 * @param typenum 特殊DI头(ADI)
	 * @return
	 */
	public List<String> GetIPtypeDI(String ADI) {
		List<String> list = new ArrayList<String>();
		String sql = "";

		switch (ADI) {
		case "61":
			logger.info(" 获取高匿待测试ip段DI码------------------------------------------------------------"); // info级别的信息
			list.add(" SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '61%' ");
			list.add("高匿待测试ip");
			list.add("1");
			list.add("1");
			break;
		case "62":
			logger.info(" 获取高匿可用ip段DI码------------------------------------------------------------"); // info级别的信息
			list.add(" SELECT MAX(ZDI) AS ZDI FROM tonuminvi WHERE ZDI LIKE '62%' ");
			list.add("高匿可用ip段");
			list.add("1");
			list.add("1");
			break;
		case "20":
			logger.info(" 获取SssGif:20段DI码------------------------------------------------------------"); // info级别的信息
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '20%' ");
			list.add("SssGif图片");
			list.add("1");
			list.add("1");
			break;
		case "21":
			logger.info(" 获取Moeimg:21段DI码------------------------------------------------------------"); // info级别的信息
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '21%' ");
			list.add("Moeimg图片");
			list.add("1");
			list.add("1");
			break;
		case "22":
			logger.info(" 获取Xvideos:22段DI码------------------------------------------------------------"); // info级别的信息
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '22%' ");
			list.add("Xvideos视频");
			list.add("1");
			list.add("1");
			break;
		case "23":
			logger.info(" 获取仓库数据:23段DI码------------------------------------------------------------"); // info级别的信息
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '23%' ");
			list.add("仓库AGC");
			list.add("1");
			list.add("1");
			break;
		case "24":
			logger.info(" 获取36Kr:24段DI码------------------------------------------------------------"); // info级别的信息
			list.add(" SELECT MAX(ZDI) AS ZDI FROM legal_information_heyhey WHERE ZDI LIKE '24%' ");
			list.add("36Kr");
			list.add("1");
			list.add("1");
			break;

		default:
			logger.info("对应参数有误:" + ADI); // info级别的信息
			break;
		}

		return list;
	}

	/**
	 * 按照所给分数several 对 Olist 集合进行分割 份数 用于多线程分配数据
	 * 
	 * @param Olist
	 * @param several
	 * @return
	 */
	public List<List<Map<String, Object>>> SplitSet(List<Map<String, Object>> Olist, int several) {
		List<List<Map<String, Object>>> ROlist = new ArrayList<List<Map<String, Object>>>();
		///////////////
		System.out.println(Olist.size());
		System.out.println(several);
		//////////////////
		if (Olist.size() > 0 && Olist.size() > several) {
			int nums = Olist.size() / several;
			int start = 0;
			int terminus = nums;
			for (int i = 0; i < several; i++) {
				if (i == 0) {
					ROlist.add(Olist.subList(start, terminus));
				} else {
					if (i == (several - 1)) {
						ROlist.add(Olist.subList(start, Olist.size()));
					} else {
						ROlist.add(Olist.subList(start, terminus));
					}
				}
				start += nums;
				terminus += nums;
			}
			return ROlist;
		}
		return null;
	}

	/**
	 * 将数据特殊位数据保存至本地
	 * 
	 * @param listmap
	 */
	public void IOSaveFile(List<Map<String, Object>> listmap) {
		String text = "";
		Boolean TF = false;
		for (Map<String, Object> map : listmap) {
			if (map.containsKey("specialIO") && map.get("specialIO") != null) {
				TF = true;
				text += map.get("specialIO").toString() + "\r\n";
			}
		}
		if (TF) {
			IOSaveFile(text, "");
		}
	}

	/**
	 * 将数据保存到本地
	 * 
	 * @param text
	 * @param name
	 */
	public void IOSaveFile(String text, String name) {
		IOLocalFile.input(text, "IOSave_" + name + "_" + GetNewDateTime(5));
	}

	/**
	 * 查询指定表号需要显示的字段
	 * 
	 * @param tableId
	 * @return
	 */
	protected List<String> getDisplayColumns(String tableId) {
		List<Element> element = loadXml();
		List<String> list = new ArrayList<String>();
		for (Element elem : element) {
			String thisTableId = elem.attributeValue("id");
			if (thisTableId.equals(tableId)) {
				list = readNode(elem, list);
			}
		}
		return list;
	}

	/**
	 * 读取配置的通道的信息
	 * 
	 * @return
	 * @date 2017-5-2 上午11:38:27
	 * @author zhangmin
	 */
	protected List<Element> loadXml() {
		SAXReader reader = new SAXReader();
		String osp_home = System.getenv("OSP_HOME") + "/conf/TableColumsDisplayConfig.xml";
		// System.out.println(osp_home);
		File file = new File(osp_home);
		try {
			Document document = reader.read(file);
			Element root = document.getRootElement();
			List<Element> elements = root.elements();

			// 查看配置了的每张表的显示列
			List<String> list = new ArrayList<String>();

			return elements;
		} catch (DocumentException e) {
			System.out.println("读取通道配置文件异常，请检查是否正确配置" + e);
			return new ArrayList<Element>();
		}
	}
	
	/**
	 * 循环出配置文件中的设备表要显示的列
	 * 
	 * @param element
	 * @param map
	 * @return
	 * @date 2017-5-2 下午5:05:25
	 * @author zhangmin
	 */
	protected List<String> readNode(Element element, List<String> list) {
		// System.out.println("---：：：：" + element.getName());

		if (!(element.getTextTrim().equals(""))) {
			// System.out.println("文本内容：：：：" + element.getText());
			if ("column".equals(element.getName())) {
				list.add(element.getText().trim());
			}
		}

		List<Element> childElems = element.elements();
		if (childElems != null && childElems.size() != 0) {
			List<List<String>> tableNode = new ArrayList<List<String>>();
			for (Element elem : childElems) {
				// System.out.println("aaaa文本内容：：：：========"+element.getText());
				tableNode.add(readNode(elem, list));
			}
		}
		return list;
	}

}
