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
	public String output(String diiz) {
		String txt1 = "";
		String txt2 = "";
		String no = "1";
		File file = new File(diiz);
		BufferedReader br = null;
		FileInputStream fis;
		if (file.isFile()) {
			try {
				fis = new FileInputStream(diiz);
				br = new BufferedReader(new InputStreamReader(fis, "gb2312"));
				while ((no = br.readLine()) != null) {
					txt1 = no;
					txt2 = txt2.concat(txt1.trim());
				}
			} catch (FileNotFoundException e) {
				// TODO 自动生成�? catch �?
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成�? catch �?
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成�? catch �?
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO 自动生成�? catch �?
						e.printStackTrace();
					}
				}

			}

		} else {
			System.out.println("文件不存�?");
		}
		return txt2;

	}

	public void input(String text, String test) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {

			String 地址 = rou.���ش洢��ַ + test + ".txt";
			fos = new FileOutputStream(地址);
			osw = new OutputStreamWriter(fos, "gb2312");
			osw.write(text);
			osw.close();
			System.out.println("保存完毕");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO 自动生成�? catch �?
					e.printStackTrace();
				}
			}

			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					// TODO 自动生成�? catch �?
					e.printStackTrace();
				}
			}
		}

	}

}
