package com.AHairTeeter.Tool.Crawler.pickpocket;

import java.util.Scanner;
import com.AHairTeeter.Tool.Crawler.pickpocket.Spiders;
import com.AHairTeeter.Tool.fileIO.IOLocalFile;

public class pickpock {
	/**
	 * ��ȡ��վ
	 */
	public void PusotsUrl() {
		Scanner input = new Scanner(System.in);
		Spiders spiders = new Spiders();
		IOLocalFile ioLF = new IOLocalFile();
		System.out.println("��������Ҫ��ȡ����ַ:");
		String url = input.next();
		System.out.println("������ģʽ(1~5)");
		int num = input.nextInt();
		System.out.println("�ȴ�������");
		try {
			String HTTPurl = spiders.spiders(url, num);
			System.out.println(HTTPurl);
			if (HTTPurl == null || HTTPurl.equals("")) {
				System.out.println("��ȡʧ��,����Ϊ��");
				return;
			} else {
				// ������ȡ����
				System.out.println("�Ƿ񱣴浽����(Y/N)");
				String yn = input.next();
				if (yn.equals("Y") || yn.equals("y")) {
					System.out.println("���������Ϊ:");
					String test = input.next();
					ioLF.input(HTTPurl, test);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
