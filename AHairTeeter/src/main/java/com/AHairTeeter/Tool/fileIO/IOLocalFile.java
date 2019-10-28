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
	 * 读取本地数据
	 * @param address 文件地址
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}

			}

		} else {
			System.out.println("文件不存在");
		}
		return txt2;
	}

	
	/**
	 * IO数据保存本地 
	 * @param H5data 将要保存本地的数据
	 * @param filename 文件名称
	 */
	public void input(String H5data, String filename) {
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		try {

			String address = rou.本地存储地址 + filename + ".txt";
			fos = new FileOutputStream(address);
			osw = new OutputStreamWriter(fos, "gb2312");
			osw.write(H5data);
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
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}

			if (osw != null) {
				try {
					osw.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}

	}

}
