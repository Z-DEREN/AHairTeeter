package com.AHairTeeter.Tool.fileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import com.AHairTeeter.Tool.Route;


public class IOLocalFile {
	Route rou = new Route();
	
	
	/**
	 * ��ȡ��������
	 * @param address �ļ���ַ
	 * @return
	 */
	public String output(String address) {
		String txt1 = "";
		String txt2 = "";
		String no = "1";
		File file = new File(address);
		BufferedReader br = null;
		FileInputStream fis;
		if (file.isFile()) {
			try {
				fis = new FileInputStream(address);
				br = new BufferedReader(new InputStreamReader(fis, "gb2312"));
				while ((no = br.readLine()) != null) {
					txt1 = no;
					txt2 = txt2.concat(txt1.trim());
				}
			} catch (FileNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}

			}

		} else {
			System.out.println("�ļ�������");
		}
		return txt2;
	}

	
	/**
	 * IO���ݱ��汾�� 
	 * @param H5data ��Ҫ���汾�ص�����
	 * @param filename �ļ�����
	 */
	public void input(String H5data, String filename) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {

			String address = rou.���ش洢��ַ + filename + ".txt";
			fos = new FileOutputStream(address);
			osw = new OutputStreamWriter(fos, "gb2312");
			osw.write(H5data);
			osw.close();
			System.out.println("�������");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}

			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		}

	}

}
